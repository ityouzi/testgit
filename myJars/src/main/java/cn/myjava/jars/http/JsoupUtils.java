package cn.myjava.jars.http;

import java.util.Iterator;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtils {
	
	/**
	 * jsoup发送get请求
	 * @param url
	 * @param headers
	 * @return Document
	 */
	public static Document getDocGet(String url,Map<String,String> headers){
		try{
			Connection con = Jsoup.connect(url);
	        if(headers != null && headers.size()>0){
	        	Iterator<String> iter = headers.keySet().iterator();
	        	while(iter.hasNext()){
	        		String key=iter.next();
	        		String value = headers.get(key);
	        		con.header(key, value);
	        	}
	        }
			Document doc = con.ignoreContentType(true).maxBodySize(0).timeout(10000).get();
			return doc;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * jsoup发送post请求
	 * @param url
	 * @param headers
	 * @param params
	 * @return Document
	 */
	public static Document getDocPost(String url,Map<String,String> headers,Map<String,String> params){
		try{
			Connection con = Jsoup.connect(url);
			if(params!=null){
				con.data(params);
			}
	        if(headers != null && headers.size()>0){
	        	Iterator<String> iter = headers.keySet().iterator();
	        	while(iter.hasNext()){
	        		String key=iter.next();
	        		String value = headers.get(key);
	        		con.header(key, value);
	        	}
	        }
			Document doc = con.ignoreContentType(true).maxBodySize(0).timeout(10000).post();
			return doc;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}