package cn.tools.xtycheck;

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

@SuppressWarnings({ "deprecation" })
public class XtyCheck {

	//信天游验真
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient http = new DefaultHttpClient();
		Entity et = new Entity();
		et.setTicketNo("731-2463068953");
		et.setName("邓晶晶");
		et = check(http,et);
		System.out.println(et.getState());
	}
	
	public static Entity check(HttpClient http,Entity et) throws ClientProtocolException, IOException{
		//获取验证码图片
		String urlcode = "http://www.travelsky.com/tsky/servlet/CallYanServlet?title=home";
		HttpGet get = new HttpGet(urlcode);
		HttpResponse response = http.execute(get);
		HttpEntity entity = response.getEntity();
		byte[] data = EntityUtils.toByteArray(entity); 
		System.out.println(data.length);
		
		//调若快解析验证码
		String username = "foreover";
		String password = "Girliess2017";
		String typeid = "3060";
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
		String url = "http://www.travelsky.com/tsky/validate";
		HttpPost post = new HttpPost(url);
		post.setHeader("Origin", "http://www.travelsky.com");
		post.setHeader("Referer", "http://www.travelsky.com/tsky/validate");
		//post参数
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("validateFlag","0"));
        parameters.add(new BasicNameValuePair("eticketNo",et.getTicketNo()));
        parameters.add(new BasicNameValuePair("invoiceNo",""));
        parameters.add(new BasicNameValuePair("imgSrc","/tsky/images/loading.gif"));
        parameters.add(new BasicNameValuePair("eticketNoORIn",et.getTicketNo()));
        parameters.add(new BasicNameValuePair("passengerName_src",et.getName()));
//		parameters.add(new BasicNameValuePair("passengerName","%E9%82%93%E6%99%B6%E6%99%B6"));
        parameters.add(new BasicNameValuePair("passengerName",et.getName()));
        parameters.add(new BasicNameValuePair("randCode",randCode));
        post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
		HttpResponse response1 = http.execute(post);
		HttpEntity entity1 = response1.getEntity();
		System.out.println(response1.getStatusLine().getStatusCode());
		String send = EntityUtils.toString(entity1,"UTF-8");
		Document doc = (Document) Jsoup.parse(send);
		String e = "";
		try{
			e = doc.getElementsByClass("trip-table-box").get(0).toString();
		}catch(Exception ee){
			e = doc.getElementsByClass("error-txt").get(0).toString();
			//验证码不正确，调若快报错接口报错
			if(e.indexOf("验证码不正确")>0){
				String result1 = RuoKuai.error(username, password, softid, softkey, id);
				System.out.println(result1);
			}else {
				ee.printStackTrace();
			}
		}
		et.setState(e);
		return et;
	}
	
}