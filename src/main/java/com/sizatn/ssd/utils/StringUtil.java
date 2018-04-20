package com.sizatn.ssd.utils;

/**
 * 
 * @desc 
 * @author sizatn
 * @date Apr 20, 2018
 */
public class StringUtil {

	public static boolean isNullOrEmpty(String str) {
		return null == str || "".equals(str) || "null".equals(str);
	}

	public static boolean isNullOrEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}
}
