package cn.tools.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.tools.ruokuai.RuoKuai;

@SuppressWarnings("deprecation")
public class HttpPostTest {
		
		public static void main(String[] args) throws IOException {
			HttpClient http = new DefaultHttpClient();
	        System.out.println(check(http));
		}
		
		public static String check(HttpClient http) throws ClientProtocolException, IOException{
			//获取验证码图片
			String urlcode = "https://my.maxthon.cn/verifycode.php?size=85x30x4x18&type=reg";
			HttpGet get = new HttpGet(urlcode);
			HttpResponse response = http.execute(get);
			HttpEntity entity = response.getEntity();
			byte[] data = EntityUtils.toByteArray(entity); 
			System.out.println(data.length);
			
			//调若快解析验证码
			String username = "foreover";
			String password = "Girliess2017";
			String typeid = "3040";
			String timeout = "10";
			String softid = "93217";
			String softkey = "0f52deddc13d4a1e893a7444dbf34f78";
			String param = String.format(
					"username=%s&password=%s&typeid=%s&timeout=%s&softid=%s&softkey=%s",
					username, password, typeid, timeout, softid, softkey);
			String result = RuoKuai.httpPostImage("http://api.ruokuai.com/create.json", param, data);
			System.out.println(result);
			JSONObject j = JSONObject.fromObject(result);
			String randCode = j.getString("Result");
			String id = j.getString("Id");
			
			//信天游验真
			String url = "https://my.maxthon.cn/api/register";
			HttpPost post = new HttpPost(url);
//			post.setHeader("Origin", "http://www.travelsky.com");
//			post.setHeader("Referer", "http://www.travelsky.com/tsky/validate");
			//post参数
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
	        parameters.add(new BasicNameValuePair("account_type","email"));
	        parameters.add(new BasicNameValuePair("account","174493572@nnsci.cn"));
	        parameters.add(new BasicNameValuePair("pwd","Girliess2018"));
	        parameters.add(new BasicNameValuePair("verifycode",randCode));
	        parameters.add(new BasicNameValuePair("formhash","3a54dba2"));
	        parameters.add(new BasicNameValuePair("open_reg_verifycode","1"));
	        parameters.add(new BasicNameValuePair("register_submit","确定并同意以下协议"));
	        post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
			HttpResponse response1 = http.execute(post);
			HttpEntity entity1 = response1.getEntity();
			System.out.println(response1.getStatusLine().getStatusCode());
			String send = EntityUtils.toString(entity1,"UTF-8");
			Document doc = (Document) Jsoup.parse(send);
			return doc.toString();
		}

}