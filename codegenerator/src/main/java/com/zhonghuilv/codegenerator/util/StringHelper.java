package com.zhonghuilv.codegenerator.util;

import com.zhonghuilv.codegenerator.PropertiesProvider;

/**
 * 
 * @author zhengjing
 */
public class StringHelper {
	
	/**
	 * @param sqlName
	 * @return String
	 *
	 */
	public static String makeAllWordFirstLetterUpperCase(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		String s = PropertiesProvider.props.getProperty("tableToClass");
		
		for(int i = Integer.parseInt(s)-1; i < strs.length; i++) {
			if(preStr.length() == 1) {
				result += strs[i];
			}else {	
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}
	
	/**
	 * @param sqlName
	 * @return String
	 *
	 */
	public static String makeAllcolWordFirstLetterUpperCase(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for(int i = 0; i < strs.length; i++) {
			if(preStr.length() == 1) {
				result += strs[i];
			}else {
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}
	
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}
	/**����ĸ��д,copy from spring*/
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}
	
	/**����ĸСд,copy from spring*/
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}
	/**copy from spring*/
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

    public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
    }
}
