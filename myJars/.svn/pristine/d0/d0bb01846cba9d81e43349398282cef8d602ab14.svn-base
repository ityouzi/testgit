package cn.myjava.jars.option.qunar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.myjava.jars.http.CloseableHttpClientUtils;

public class GetPolicy {
	
	public static void main(String[] args) throws IOException {
		//验证码
		String url0 = "https://user.qunar.com/captcha/api/image?k=%7Ben7mni(z&p=ucenter_login&c=ef7d278eca6d25aa6aec7272d57f0a9a";
		
		//登录
		String url = "https://user.qunar.com/passport/loginx.jsp";
		
		String ip = "";
		int port = 0;
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36 Maxthon/5.2.1.6000");
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("loginType", "0");
		params.put("ret", "http://fuwu.qunar.com/userpass/clean");
		params.put("username", "ovkpexg4892");
		params.put("password", "dongbin123789");
		params.put("remember", "1");
		
		
		CloseableHttpClient http = HttpClients.createDefault();
		
		HttpEntity entity = CloseableHttpClientUtils.getCloseablehttpGetProxy(http, url0, ip, port, headers);
		byte[] bytes = EntityUtils.toByteArray(entity);
		OutputStream os = new FileOutputStream("D:\\code.jpg");   
        os.write(bytes, 0, bytes.length);    
        os.close();  
		
		Scanner sc=new Scanner(System.in);
        System.out.println("请输入验证码："); 
        String code = sc.next();
        System.out.println(code);
        params.put("vcode", code);
        HttpEntity entity1 = CloseableHttpClientUtils.getCloseablehttpPostProxy(http, url, ip, port, headers, params);
        System.out.println(EntityUtils.toString(entity1));
		
	}

}
