package cn.myjava.jars.hkgs.tv;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class TV {
	
	public static void main(String[] args) throws Exception {
		
	  CloseableHttpClient client = HttpClients.createDefault();
		
      String url1 = "https://tv.travelsky.com/tvb2b/VerificationCode.do";
	  String url2 = "https://tv.travelsky.com/tvb2b/Login.do";
        
      HttpGet get1 = new HttpGet(url1);
      CloseableHttpResponse response1 = client.execute(get1);
      InputStream is = response1.getEntity().getContent();
		
      byte[] bs = new byte[1024];
      int len;
      OutputStream os = new FileOutputStream("c:/1.png");
      while ((len = is.read(bs)) != -1) {
        os.write(bs, 0, len);
      }
      os.close();
      is.close();
		
      Scanner sc = new Scanner(System.in);
      System.out.println("请输入数据:");  
      String verCode = sc.next();
      
      HttpPost post = new HttpPost(url2);
      List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
      parameters.add(new BasicNameValuePair("serviceURL", "https://tv.travelsky.com:443/tvb2b/ssoAction.do"));
      parameters.add(new BasicNameValuePair("signMessage", ""));
      parameters.add(new BasicNameValuePair("loginname", "njyysj"));
      parameters.add(new BasicNameValuePair("loginpass", "njyy.sj123"));
      parameters.add(new BasicNameValuePair("vrtifycode", verCode));
      post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
      CloseableHttpResponse response2 = client.execute(post);
      
      String setCookie = response2.getFirstHeader("Set-Cookie")
    	        .getValue();
    	    String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
    	        setCookie.indexOf(";"));
    	    System.out.println("JSESSIONID:" + JSESSIONID);
      
      Header[] hs = response2.getAllHeaders();
      for(Header h : hs){
    	  System.out.println(h);
      }
      System.out.println(EntityUtils.toString(response2.getEntity()));
  }

}