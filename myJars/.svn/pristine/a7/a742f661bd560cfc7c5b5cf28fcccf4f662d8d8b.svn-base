package cn.myjava.jars.http;

import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

//不建议使用
@SuppressWarnings("deprecation")
public class HttpClientUtils {
	
	//httpclient发送json方式post请求
	@SuppressWarnings({ "resource" })
	public static String getHttpclientJson(String url,Map<String,Object> params,Map<String,String> headers){
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		if(headers.size()>0){
			Iterator<String> iter = headers.keySet().iterator();
        	while(iter.hasNext()){
        		String key=iter.next();
        		String value = headers.get(key);
        		httpPost.addHeader(key, value);
        	}
		}
        //json方式
		if(params.size()>0){
			JSONObject jsonParam = new JSONObject();
			Iterator<String> iter = params.keySet().iterator();
        	while(iter.hasNext()){
        		String key=iter.next();
        		Object value = params.get(key);
        		jsonParam.put(key, value);
        	}
        	StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
		}
		String resData = "";
		try{
	        HttpResponse result = http.execute(httpPost);
	        // 请求结束，返回结果  
	        resData = EntityUtils.toString(result.getEntity());  
		}catch(Exception e){
			e.printStackTrace();
		}
        System.out.println(resData);
        return resData;
	}

}