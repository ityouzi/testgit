package cn.myjava.jars.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 帐号+cookie保存到静态list里
 * 竞价时判断帐号取cookie提交
 * 提交时判断是否有保存cookie
 * @author Administrator
 */
public class BidTest {
//	static String cookie = "";
	
	public static void main2(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient http = HttpClients.createDefault();
		String url = "https://pai.renrenche.com/api/user/login?os=android&sv=4.1.3";
		
		HttpPost post = new HttpPost(url);
	    
	    List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
	    parameters.add(new BasicNameValuePair("phone", "18639619359"));
	    
		post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
		CloseableHttpResponse response = http.execute(post);
		System.out.println(EntityUtils.toString(response.getEntity()));
		
	}
	
	public static void main(String[] args) throws Exception {
//		CloseableHttpClient http = HttpClients.createDefault();
//		String url = "https://pai.renrenche.com/api/user/login?os=android&sv=4.1.3";
//		
//		HttpPost post = new HttpPost(url);
//	    
//	    List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
//	    parameters.add(new BasicNameValuePair("phone", "18639619359"));
//	    
//	    parameters.add(new BasicNameValuePair("verifycode", "249382"));
//	    
//		post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
//		CloseableHttpResponse response = http.execute(post);
//		System.out.println(EntityUtils.toString(response.getEntity()));
//		
//		Header[] headers = response.getAllHeaders();
//		for(Header h : headers){
//			String headerName = h.getName().toUpperCase();
//			if(headerName.equals("SET-COOKIE")){
//				String headerValue = h.getValue();
//				String cookie1 = headerValue.substring(0, headerValue.indexOf(";")+1);
//				cookie = cookie+cookie1;
//			}
//		}
	
//		CloseableHttpClient http1 = HttpClients.createDefault();
		String url1 = "https://pai.renrenche.com/api/v3/intent/bid?os=android&sv=4.1.3";
//		HttpPost post1 = new HttpPost(url1);
		
//	    List<BasicNameValuePair> parameters1 = new ArrayList<BasicNameValuePair>();
//	    parameters1.add(new BasicNameValuePair("car_id", "c1acd535d6a2125c"));
//	    parameters1.add(new BasicNameValuePair("phone", "18639619359"));
//	    parameters1.add(new BasicNameValuePair("price", "1"));
//	    parameters1.add(new BasicNameValuePair("car_price", "1.2"));
//	    parameters1.add(new BasicNameValuePair("city", "武汉"));
//	    parameters1.add(new BasicNameValuePair("version", "4.1.3"));
		
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("car_id", "c1acd535d6a2125c");
		parameter.put("phone", "18639619359");
		parameter.put("price", "1");
		parameter.put("car_price", "1.2");
		parameter.put("city", "武汉");
		parameter.put("version", "4.1.3");
		
		String cookie = "acw_tc=AQAAAIHgNRQVjgkASWgSG38Grf/BeMW6;c2bSess=s%3AxDVxmKeHT9bG7BwFnsB1BJArAVbjL5hL.IlfnzC1Q%2FP2kgpdxDxNEEeD6pYSobs5Lv6PEi0W1PDM;";
		String cookie1 = "acw_tc=AQAAAIHgNRQVjgkASWgSG38Grf/BeMW6;c2bSess=s%3AxDVxmKeHT9bG7BwFnsB1BJArAVbjL5hL.IlfnzC1Q%2FP2kgpdxDxNEEeD6pYSobs5Lv6PEi0W1PD1;";
		
		System.err.println(cookie);
		
		String result2 = HTTPUtils.post(url1,parameter, null,cookie);
		System.out.println(result2);
	}

}