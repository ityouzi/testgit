package cn.myjava.jars.cduq;

import java.util.ArrayList;
import java.util.List;

public class CDUQ {
	
	public String add(Object obj){
		try{
			//插入
			return "成功";
		}catch(Exception e){
			e.printStackTrace();
			return "失败";
		}
	}
	
	public String del(Object obj){
		try{
			//删除
			return "成功";
		}catch(Exception e){
			e.printStackTrace();
			return "失败";
		}
	}
	
	public String edit(Object obj){
		try{
			//更新
			return "成功";
		}catch(Exception e){
			e.printStackTrace();
			return "失败";
		}
	}
	
	public Object get(Object obj){
		return Object.class;
	}
	
	public List<Object> list(Object obj){
		return new ArrayList();
	}
	
	public static void main(String[] args) {
		for(int i = 0 ;i<10;i++){
			new Thread(){
				public void run(){
					while(true){
						System.out.println("进行"+Thread.currentThread().getId());
					}
				}
			}.start();
		}
	}
	
}