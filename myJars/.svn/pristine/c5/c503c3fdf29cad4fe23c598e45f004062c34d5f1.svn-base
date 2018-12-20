package cn.myjava.jars.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Secret {
	
//	public static void main(String[] args) {
//		String username = "admin";
//		String password = "1";
//		SimpleHash pass = new SimpleHash("SHA-1", username, password);
//		System.out.println(pass);
//	}
	
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list2.add("b");
		list2.add("c");
		list2.add("d");
//		for(String l1 : list1){
//			System.out.println(l1);
//			for(int i = 0;i<list2.size();i++){
//				String l2 = list2.get(i);
//				if(l1.equals(l2)){
//					list2.remove(i);
//				}
//			}
//		}
//		for(String ll2 : list2){
//			System.out.println(ll2);
//		}
		
		for(String l1 : list1){
			for(int i = 0;i<list2.size();i++){
				String l2 = list2.get(i);
				if(l1.equals(l2)){
					System.out.println("共有的"+l1);
					list2.remove(i);
					break;
				}else{
					if(i==list2.size()-1){
						System.err.println("list1有的"+l1);
					}
				}
			}
		}
		for(String ll2 : list2){
			System.err.println("list2有的"+ll2);
		}
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date end = null;
	    Date begin = new Date();
		try {
			end = dfs.parse("2018-08-03 17:50:24");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
		long c = (begin.getTime() - end.getTime())/1000/60;
		System.out.println(c);
		
	    if(c>5){
	    	System.err.println("这种不行");
	    }else{
	    	System.out.println("这种行的");
	    }
	}
}
