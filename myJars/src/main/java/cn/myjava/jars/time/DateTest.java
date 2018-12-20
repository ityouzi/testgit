package cn.myjava.jars.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		int i= compare_date("2018-08-22", "2018-08-22");
		System.out.println(i);
		
		System.out.println(dateAC("2018-08-22", 2, "d"));
	}
	
	public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
            	System.out.println("dt1和dt2相同");
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	
	public static String dateAC(String date,int ac,String yMdHms) {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long dd = d.getTime();
		if(yMdHms.equals("y")){
			dd = dd + 1000*60*60*24*365*ac;
		}else if(yMdHms.equals("d")){
			dd = dd + 1000*60*60*24*ac;
		}else if(yMdHms.equals("H")){
			dd = dd + 1000*60*60*ac;
		}else if(yMdHms.equals("m")){
			dd = dd + 1000*60*ac;
		}else if(yMdHms.equals("s")){
			dd = dd + 1000*ac;
		}
        return simpleDateFormat.format(dd);
	}
}
