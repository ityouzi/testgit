package cn.myjava.jars.option.fanyi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class BaiDuFanyi {
	
	//md5加密32位小写
	public static String getMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {  
		MessageDigest md5 = MessageDigest.getInstance("MD5");  
		md5.update((str).getBytes("UTF-8"));  
		byte b[] = md5.digest();  
		  
		int i;  
		StringBuffer buf = new StringBuffer("");  
		  
		for(int offset=0; offset<b.length; offset++){  
		    i = b[offset];  
		    if(i<0){  
		        i+=256;  
		    }  
		    if(i<16){  
		        buf.append("0");  
		    }  
		    buf.append(Integer.toHexString(i));  
		}  
		  
		String result = buf.toString();  
		System.out.println("result = " + result);  
		return result;
	}
	
	//ascii转中文
	public static String decodeUnicode(String dataStr) {     
        int start = 0;     
        int end = 0;     
        StringBuffer buffer = new StringBuffer();     
        while (start > -1) {     
            end = dataStr.indexOf("\\u", start + 2);     
            String charStr = "";     
            if (end == -1) {     
                charStr = dataStr.substring(start + 2, dataStr.length());     
            } else {     
                charStr = dataStr.substring(start + 2, end);     
            }     
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。     
            buffer.append(new Character(letter).toString());     
            start = end;     
        }     
        return buffer.toString();     
     }  
	
	
	public String getResult (String q ,HttpClient http) throws NoSuchAlgorithmException, ClientProtocolException, IOException{
		
		String appid = "20171013000087965";
		String secret = "IsKeTvSXL7hBz570iCnM";
		String salt = "201710131210005966842";
		String from = "auto";
		String to = "zh";
		String sign = getMD5(appid+q+salt+secret);
		String url = "https://fanyi-api.baidu.com/api/trans/vip/translate";
		
		HttpPost httppost = new HttpPost(url);
		//post参数
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("q",q));
        parameters.add(new BasicNameValuePair("from",from));
        parameters.add(new BasicNameValuePair("to",to));
        parameters.add(new BasicNameValuePair("appid",appid));
        parameters.add(new BasicNameValuePair("salt",salt));
        parameters.add(new BasicNameValuePair("sign",sign));
        httppost.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
        // 执行请求
        HttpResponse response = http.execute(httppost);
        HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine());
		//通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理。
		String send = EntityUtils.toString(entity,"UTF-8");
		System.out.println(send);
		JSONObject jo = JSONObject.fromObject(send);
		JSONArray ja = (JSONArray) jo.get("trans_result");
		JSONObject jo1 = (JSONObject) ja.get(0);
		String tmp = jo1.get("dst").toString();
		System.out.println(tmp);
		return tmp;
		
	}
	
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
		String appid = "20171013000087965";
		String secret = "IsKeTvSXL7hBz570iCnM";
		String salt = "201710131210005966842";
		String q = "Air Conditioning";
		String from = "auto";
		String to = "zh";
		String sign = getMD5(appid+q+salt+secret);
		String url = "https://fanyi-api.baidu.com/api/trans/vip/translate";
		
		@SuppressWarnings("resource")
		HttpClient http = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		//post参数
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("q",q));
        parameters.add(new BasicNameValuePair("from",from));
        parameters.add(new BasicNameValuePair("to",to));
        parameters.add(new BasicNameValuePair("appid",appid));
        parameters.add(new BasicNameValuePair("salt",salt));
        parameters.add(new BasicNameValuePair("sign",sign));
        httppost.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
        // 执行请求
        HttpResponse response = http.execute(httppost);
        HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine());
		//通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理。
		String send = EntityUtils.toString(entity,"UTF-8");
		System.out.println(send);
		JSONObject jo = JSONObject.fromObject(send);
		JSONArray ja = (JSONArray) jo.get("trans_result");
		JSONObject jo1 = (JSONObject) ja.get(0);
		String tmp = jo1.get("dst").toString();
		System.out.println(tmp);
		
	}

}