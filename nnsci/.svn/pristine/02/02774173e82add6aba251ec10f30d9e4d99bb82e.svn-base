package com.fh.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fh.util.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyTools {
	
	public static String getDateR(){
		double rd = Math.random()*1000000;
		int rds = (int)rd;
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(date)+rds;
	}
	
	public static String getR(){
		double rd = Math.random()*1000000;
		int rds = (int)rd;
		return rds+"";
	}
	
	public static String getTime(){
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/** 
     * 将json字符串转为Map结构 
     * 如果json复杂，结果可能是map嵌套map 
     * @param jsonStr 入参，json格式字符串 
     * @return 返回一个map 
     */  
    public static Map<String, Object> json2Map(String jsonStr) {  
        Map<String, Object> map = new HashMap<>();  
        if(jsonStr != null && !"".equals(jsonStr)){  
            //最外层解析  
            JSONObject json = JSONObject.fromObject(jsonStr);  
            for (Object k : json.keySet()) {  
                Object v = json.get(k);  
                //如果内层还是数组的话，继续解析  
                if (v instanceof JSONArray) {  
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();  
                    while (it.hasNext()) {  
                        JSONObject json2 = it.next();  
                        list.add(json2Map(json2.toString()));  
                    }  
                    map.put(k.toString(), list);  
                } else {  
                    map.put(k.toString(), v);  
                }  
            }  
            return map;  
        }else{  
            return null;  
        }  
    }  
    
    /** 
     * 将json字符串转为PageData结构 
     * @param jsonStr 入参，json格式字符串 
     * @return 返回一个PageData 
     */  
    public static PageData pd(String jsonStr) {  
    	PageData pd = new PageData(); 
        if(jsonStr != null && !"".equals(jsonStr)){  
            //最外层解析  
            JSONObject json = JSONObject.fromObject(jsonStr);  
            for (Object k : json.keySet()) {  
                Object v = json.get(k);  
                //如果内层还是数组的话，继续解析  
                if (v instanceof JSONArray) {  
                    List<PageData> list = new ArrayList<PageData>();  
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();  
                    while (it.hasNext()) {  
                        JSONObject json2 = it.next();  
                        list.add(pd(json2.toString()));  
                    }  
                    pd.put(k.toString(), list);  
                } else {  
                    pd.put(k.toString(), v);  
                }  
            }  
            return pd;  
        }else{  
            return null;  
        }  
    }  
    
    /** 
     * 将一个PageData转为多个个 PageData
     * @return 返回一个List 
     */  
	public static List<PageData> getPds(PageData pd,List<String> pdNames) { 
    	List<PageData> list = new ArrayList<PageData>();
    	for(String pdName : pdNames){
    		System.out.println(pdName);
    		System.out.println(pd.get(pdName));
    		String jsonStr = pd.get(pdName).toString();
    		PageData p = pd(jsonStr);
    		list.add(p);
    	}
    	return list;
    }
    
    public static void main(String[] args) {
		String str = "{\"STATUS\":\"1\"}";
		json2Map(str);
	}

}
