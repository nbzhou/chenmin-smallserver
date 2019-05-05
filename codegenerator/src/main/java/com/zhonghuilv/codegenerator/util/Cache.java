package com.zhonghuilv.codegenerator.util;

import com.zhonghuilv.codegenerator.PropertiesProvider;
import com.zhonghuilv.codegenerator.model.Table;

import java.util.*;

/**
 * Created by 郑靖 on 2017/5/3 17:38.
 */
public class Cache {

    private static List<String> igones = Collections.synchronizedList(new ArrayList<>());

    private static Map<String, List<Table>> tables = new HashMap<>();

    private static Map<String, String> DYNAMIC_PARAMS = new HashMap<>();

    static {
        String nogenerate = PropertiesProvider.getProperties().getProperty("nogenerate");
        if (nogenerate != null && nogenerate.trim().length() > 0) {
            igones.addAll(Arrays.asList(nogenerate.trim().toLowerCase().split(",")));
        }
    }

    public static List<Table> getCache(String dbname) {
        if (tables.containsKey(dbname))
            return tables.get(dbname);
        return null;
    }

    public static Map<String, String> getDynamicParams() {
        return DYNAMIC_PARAMS;
    }

    public static boolean cache(String dbname, List<Table> table) {
        return tables.put(dbname, table) != null;
    }

    /**
     * 是否为忽略字段
     *
     * @param sqlName
     * @return
     */
    public static boolean isIgnore(String sqlName) {
        return igones.contains(sqlName.toLowerCase());
    }
}
