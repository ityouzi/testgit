package cn.tools.jsoup;

import java.util.Random;

public class RandemUtils {
	
	public static void main(String[] args) {
		System.out.println(getRdInt(1));
//		System.out.println(getRdStr(11));
	}
	
	/**
	 * 获取数字类型随机数
	 * @param length
	 * @return String
	 */
	public static String getRdInt(int length){
		Double a = Math.random()*9+1;
		for(int i = 1;i<length;i++){
			a = a*10;
		}
		return a.longValue()+"";
	}
	
	/**
	 * 获取字符类型随机数
	 * @param length
	 * @return String
	 */
	public static String getRdStr(int length){
		String val = "";  
        Random random = new Random();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
	}

}