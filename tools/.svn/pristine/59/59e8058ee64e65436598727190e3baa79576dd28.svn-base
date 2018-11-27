package cn.tools.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class CloseableHttpClientUtils {

	/**
	 * 代理IP发送get请求
	 * @param url
	 * @param ip
	 * @param port
	 * @param headers
	 * @return String
	 */
	public static HttpEntity getCloseablehttpGetProxy(CloseableHttpClient http,String url,String ip,int port,Map<String,String> headers){
		HttpGet get = new HttpGet(url);
		
		if(!"".equals(ip) && null!=ip){
			HttpHost proxy=new HttpHost(ip, port);
		    RequestConfig requestConfig=RequestConfig.custom().setConnectTimeout(10000).setProxy(proxy).build();
		    get.setConfig(requestConfig);
		}
	    if(headers !=null && headers.size()>0){
	    	Iterator<String> iter = headers.keySet().iterator();
        	while(iter.hasNext()){
        		String key=iter.next();
        		String value = headers.get(key);
        		get.setHeader(key, value);
        	}
	    }
		try {
			CloseableHttpResponse response = http.execute(get);
			HttpEntity entity = response.getEntity();
			return entity;
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 代理IP发送post请求
	 * @param url
	 * @param ip
	 * @param port
	 * @param headers
	 * @param params
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static HttpEntity getCloseablehttpPostProxy(CloseableHttpClient http,String url,String ip,int port,Map<String,String> headers,Map<String,String> params){
		HttpPost post = new HttpPost(url);
		if(!"".equals(ip) && null!=ip){
			HttpHost proxy=new HttpHost(ip, port);
		    RequestConfig requestConfig=RequestConfig.custom().setConnectTimeout(10000).setProxy(proxy).build();
		    post.setConfig(requestConfig);
		}
	    if(headers.size()>0){
	    	Iterator<String> iter = headers.keySet().iterator();
        	while(iter.hasNext()){
        		String key=iter.next();
        		String value = headers.get(key);
        		post.setHeader(key, value);
        	}
	    }
	    List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
	    if(params.size()>0){
	    	Iterator<String> iter = params.keySet().iterator();
        	while(iter.hasNext()){
        		String key=iter.next();
        		String value = params.get(key);
        		parameters.add(new BasicNameValuePair(key, value));
        	}
	    }
		try{
			post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
			CloseableHttpResponse response = http.execute(post);
			HttpEntity entity = response.getEntity();
			return entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}