package cn.tools.jsoup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	public static void main(String[] args) {
//		System.out.println(getTimeFormat("YYYY-MM-dd HH:mm:ss"));
//		System.out.println(getTimeFormat("YYYYMMddHHmmss"));
//		System.out.println(getTimeFormat("YYYYMMddHHmmssSSS"+RandemUtils.getRdInt(4)));
		System.out.println(updateTime("2018-06-13 16:00:22",1000*60*10));
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
	
	/**
	 * 获取时间前后的时间
	 * @param oleTime	传入的时间
	 * @param mm	传入的毫秒	如10秒后mm=10000	如10秒前mm=-10000
	 * @return
	 */
	public static String updateTime(String oleTime, int mm){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(oleTime);
			Calendar ca=Calendar.getInstance();
			ca.setTime(date);
			ca.add(Calendar.MILLISECOND, mm);
			String time = sdf.format(ca.getTime());
			return time;
		}catch(Exception e){
			return null;
		}
	}

}