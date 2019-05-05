package com.zhonghuilv.codegenerator;

import com.zhonghuilv.codegenerator.model.Table;
import com.zhonghuilv.codegenerator.util.Cache;
import com.zhonghuilv.codegenerator.util.FileHelper;
import com.zhonghuilv.codegenerator.util.IOHelper;
import com.zhonghuilv.codegenerator.util.StringTemplate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.beanutils.BeanUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhengjing
 */
public class Generator {
    private static final String WEBAPP_GENERATOR_INSERT_LOCATION = "webapp-generator-insert-location";
//	private static final String WEBAPP_GENERATOR_INSERT_BEFORE_LOCATION = WEBAPP_GENERATOR_INSERT_LOCATION+":before";
//	private static final String WEBAPP_GENERATOR_INSERT_AFTER_LOCATION = WEBAPP_GENERATOR_INSERT_LOCATION+":after";

    public Generator() {
    }

    public void generateAllTable() throws Exception {
        List tables = DbModelProvider.getInstance().getAllTables();
        for (int j = 0; j < tables.size(); j++) {
            generateTable((Table) tables.get(j));
        }
    }

    /**
     * 初始化TABLE
     *
     * @param tableName
     * @throws Exception
     */
    public void generateTable(String tableName, String remark) throws Exception {
        Table table = DbModelProvider.getInstance().getTable(tableName);
        if (remark != null && remark.trim().length() > 0) {
            table.setTableRemark(remark);
        }
        generateTable(table);
    }

    /**
     * 得到模板
     *
     * @param table
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws TemplateException
     */
    public void generateTable(Table table) throws IOException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, TemplateException {
        System.out.println("***************************************************************");
        System.out.println("* BEGIN generate table:" + table.getSqlName() + "[" + table.getTableRemark() + "]");
        System.out.println("***************************************************************");
        Configuration config = new Configuration();
        File templateRootDir = null;
        URL template = this.getClass().getClassLoader().getResource("template");
        templateRootDir = new File(template.getFile()).getAbsoluteFile();
        config.setDirectoryForTemplateLoading(templateRootDir);
        config.setNumberFormat("###############");
        config.setBooleanFormat("true,false");

        List files = new ArrayList();
        FileHelper.listFiles(templateRootDir, files);

        for (int i = 0; i < files.size(); i++) {
            File file = (File) files.get(i);
            String templateRelativePath = FileHelper.getRelativePath(templateRootDir, file);
            String outputFilePath = templateRelativePath;
            if (file.isDirectory() || file.isHidden())
                continue;
            if (templateRelativePath.trim().equals(""))
                continue;
            if (file.getName().toLowerCase().endsWith(".include")) {
                continue;
            }
            int testExpressionIndex = -1;
            if ((testExpressionIndex = templateRelativePath.indexOf('@')) != -1) {
                outputFilePath = templateRelativePath.substring(0, testExpressionIndex);
                String testExpressionKey = templateRelativePath.substring(testExpressionIndex + 1);
                Map map = getFilepathDataModel(table);
                Object expressionValue = map.get(testExpressionKey);
                if (!"true".equals(expressionValue.toString())) {
                    System.out.println("[not-generate]\t test expression '@" + testExpressionKey + "' is false," +
                            "template:" + templateRelativePath);
                    continue;
                }
            }
            try {
                generateFile(table, config, templateRelativePath, outputFilePath);
            } catch (Exception e) {
                e.printStackTrace();;
//                throw new RuntimeException("generate table '" + table.getSqlName() + "' oucur error,template is:" +
//                        templateRelativePath, e);
            }
        }
    }

    /**
     * 生成代码
     *
     * @param table
     * @param config
     * @param templateRelativePath
     * @param outputFilePath
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws TemplateException
     */
    private void generateFile(Table table, Configuration config, String templateRelativePath, String outputFilePath)
            throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            TemplateException {
        Template template = config.getTemplate(templateRelativePath);

        String targetFilename = getTargetFilename(table, outputFilePath);

        Map templateDataModel = getTemplateDataModel(table);
        File absoluteOutputFilePath = getAbsoluteOutputFilePath(targetFilename);
        if (absoluteOutputFilePath.exists()) {
            StringWriter newFileContentCollector = new StringWriter();
            if (isFoundInsertLocation(template, templateDataModel, absoluteOutputFilePath, newFileContentCollector)) {
                IOHelper.saveFile(absoluteOutputFilePath, newFileContentCollector.toString());
                return;
            }
        }
        saveNewOutputFileContent(template, templateDataModel, absoluteOutputFilePath);
    }

    private String getTargetFilename(Table table, String templateFilepath) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        Map fileModel = getFilepathDataModel(table);
        String targetFilename = resolveFile(templateFilepath, fileModel);
        return targetFilename;
    }

    /**
     * 得到生成"文件目录/文件路径"的Model
     **/
    private Map getFilepathDataModel(Table table) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        Map fileModel = BeanUtils.describe(table);
        fileModel.putAll(PropertiesProvider.getProperties());
        return fileModel;
    }

    private Map getTemplateDataModel(Table table) {
        return getTemplateDataModel(table, Cache.getDynamicParams());
    }

    /**
     * 得到FreeMarker的Model
     **/
    private Map getTemplateDataModel(Table table, Map<String, String> others) {
        Map model = new HashMap();
        model.putAll(PropertiesProvider.getProperties());
        model.put("table", table);
        if (Objects.nonNull(others)) {
            model.putAll(others);
        }
        return model;
    }

    private File getAbsoluteOutputFilePath(String targetFilename) {
        String outRoot = getOutRootDir();
        File outputFile = new File(outRoot, targetFilename);
        outputFile.getParentFile().mkdirs();
        return outputFile;
    }

    private boolean isFoundInsertLocation(Template template, Map model, File outputFile, StringWriter newFileContent)
            throws IOException, TemplateException {
        LineNumberReader reader = new LineNumberReader(new FileReader(outputFile));
        String line = null;
        boolean isFoundInsertLocation = false;

        PrintWriter writer = new PrintWriter(newFileContent);
        while ((line = reader.readLine()) != null) {
            writer.println(line);
            // only insert once
            if (!isFoundInsertLocation && line.indexOf(WEBAPP_GENERATOR_INSERT_LOCATION) >= 0) {
                template.process(model, writer);
                writer.println();
                isFoundInsertLocation = true;
            }
        }

        writer.close();
        reader.close();
        return isFoundInsertLocation;
    }

    private void saveNewOutputFileContent(Template template, Map model, File outputFile) throws IOException,
            TemplateException {

        OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(outputFile), "UTF-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.put("date", sdf.format(new Date()));
        template.process(model, out);//生成文件

        out.close();
    }

    private String resolveFile(String templateFilepath, Map fileModel) {
        return new StringTemplate(templateFilepath, fileModel).toString();
    }

    public void clean() {
        deleteFiels();
    }


    private static void deleteFiels() {
        String dir = PropertiesProvider.getProperties().getProperty("outRoot");
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            throw new RuntimeException("目标不是一个文件夹");
        }
    }

    private static void deleteDirectory(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
            file.delete();
        }
    }

    private String getOutRootDir() {
        return PropertiesProvider.getProperties().getProperty("outRoot");
    }

}
