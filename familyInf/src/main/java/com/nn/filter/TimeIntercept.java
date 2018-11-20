package com.nn.filter;

import java.util.Date;

public class TimeIntercept {
	static long longCount = 0;
	static Date datetime = null;
	
	public static void main(String[] args) throws InterruptedException {
		Date d = new Date();
		Thread.sleep(1000);
		Date dd = new Date();
		System.out.println();
		long ddLong = dd.getTime();
		long dLong = d.getTime();
		System.out.println(ddLong - dLong);
	}

}
