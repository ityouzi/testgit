package cn.myjava.jars.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChange {
	
	public static void main(String[] args) throws ParseException {
		Date date = null;
		String date1 = "2018-05-15";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.parse(date1);
		long date2 = date.getTime();
		System.out.println(date2);
	}

}