package com.fh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	public static void main(String[] args) {
		System.out.println(getTimeFormat("YYYY-MM-dd HH:mm:ss"));
		System.out.println(getTimeFormat("YYYYMMddHHmmss"));
		System.out.println(getTimeFormat("YYYYMMddHHmmssSSS"+RandemUtils.getRdInt(4)));
	}
	
	/**
	 * 获取格式化当前时间
	 * @param format
	 * @return String
	 */
	public static String getTimeFormat(String format){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

}