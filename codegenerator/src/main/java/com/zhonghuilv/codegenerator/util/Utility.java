package com.zhonghuilv.codegenerator.util;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhengjing
 */
public class Utility {

    public static String strFormatUsingDict(String template, Map<String, String> dict) {

        String patternString = "\\$\\{(" + String.join("|", dict.keySet()) + ")\\}";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, dict.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}


