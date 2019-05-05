package com.zhonghuilv.codegenerator.util;

import java.util.Iterator;
import java.util.Map;

/**
 * @author zhengjing
 */
public class StringTemplate {

	private String str;
	private Map params;
	
	public StringTemplate(String str, Map params) {
		this.str = str;
		this.params = params;
	}

	public String toString() {
		String result = str;
		for(Iterator it = params.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			String key = (String)entry.getKey();
			Object value = entry.getValue();
			String strValue = value == null ? "" : value.toString();
			result = StringHelper.replace(result, "${"+key+"}", strValue);
		}
		return result;
	}
	
	
}
