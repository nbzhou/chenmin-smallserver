package com.zhonghuilv.codegenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * <code>GeneratorMain</code>
 */
public class GeneratorMain {

    public static void main(String[] args) throws Exception {
        Generator g = new Generator();
        g.clean();//
        Map<String, String> map = new HashMap<>();

        PropertiesProvider.setProperty("jdbc.url", "jdbc:mysql://192.168.19.10:3306/order?useUnicode" +
                "=true&characterEncoding=utf-8&allowMultiQueries=true");
        PropertiesProvider.setProperty("basepackage", "com.zhonghuilv.aitravel");
        PropertiesProvider.setProperty("subpackage", "order");
        PropertiesProvider.setProperty("comm.po", "com.zhonghuilv.aitravel.common.pojo.po.MainPO");
//        PropertiesProvider.setProperty("ignore.prefix", "sys_");
        PropertiesProvider.setProperty("constantName", "OrderConstant");


        //表名，表注释
        map.put("integral_account", "积分账户");
        map.put("integral_goods", "积分商品");
        map.put("integral_history", "积分变化记录");
        map.put("integral_exchange", "积分兑换记录");

        //清空文件夹
        map.forEach((key, value) -> {
            try {
                g.generateTable(key, value);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(key + "出错");
            }
        });
        System.out.println("--完成--");

    }

}
