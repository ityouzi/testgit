package cn.tools.httpPostJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpPostJson {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		String url = "http://192.168.0.252:8080/jeeplus/a/login";
		@SuppressWarnings({ "resource" })
		HttpClient http = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("username","admin"));
        parameters.add(new BasicNameValuePair("password","admin"));
        parameters.add(new BasicNameValuePair("mobileLogin","true"));
        post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
		HttpResponse response = http.execute(post);
		HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine().getStatusCode());
		String send = EntityUtils.toString(entity,"UTF-8");
		JSONObject json = (JSONObject) JSONObject.fromObject(send).get("body");
		System.out.println(send);
		
		
		String url12 = "http://192.168.0.252:8080/jeeplus/a/menu/dispatching/dispatching/appSave;JSESSIONID="+json.getString("JSESSIONID");
		HttpPost httpPost = new HttpPost(url12);
		
		List<Entity> list = new ArrayList<Entity>();
		list.add(new Entity( "0", "8", "8", "8", "8"));
		list.add(new Entity( "0", "8", "8", "8", "8"));
       
        //json方式
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("checkBy", "8");
        jsonParam.put("orderId", "8");
        jsonParam.put("technician", "8");
        jsonParam.put("priceId", "8");
        jsonParam.put("delFlag", "0");
        jsonParam.put("dispatchingDetailList", list);
        StringEntity entity1 = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
        entity1.setContentEncoding("UTF-8");    
        entity1.setContentType("application/json");
        httpPost.setEntity(entity1);
        
        System.out.println(jsonParam);
        HttpResponse result = http.execute(httpPost);
        
        // 请求结束，返回结果  
        String resData = EntityUtils.toString(result.getEntity());  
        System.out.println(resData);
	}

}