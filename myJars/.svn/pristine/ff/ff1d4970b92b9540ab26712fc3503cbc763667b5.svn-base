package cn.myjava.jars.option.xty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.myjava.jars.entity.XtyEntity;
import cn.myjava.jars.http.CloseableHttpClientUtils;
import cn.myjava.jars.intface.ruokuai.RuoKuaiUtils;

public class XtyUtils {
	
	//信天游验真
		public static void main(String[] args) throws IOException, InterruptedException {
			CloseableHttpClient http = HttpClients.createDefault();
			XtyEntity et = new XtyEntity();
			et.setTicketNo("784-2365541532");
			et.setName("梁传龙");
			et = check(http,et);
			System.out.println(et.getState());
		}
		
		public static XtyEntity check(CloseableHttpClient http,XtyEntity et) throws ClientProtocolException, IOException{
			String typeid = "3060";
			
			HttpEntity entity = CloseableHttpClientUtils.getCloseablehttpGetProxy(http,"http://www.travelsky.com/tsky/servlet/CallYanServlet?title=home", "", 0, null);
			byte[] data = EntityUtils.toByteArray(entity);
			String result = RuoKuaiUtils.httpPostImage("http://api.ruokuai.com/create.json", typeid, data);
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
			parameters.add(new BasicNameValuePair("passengerName","%E6%A2%81%E4%BC%A0%E9%BE%99"));
	        parameters.add(new BasicNameValuePair("isFromIndex","false"));
	        parameters.add(new BasicNameValuePair("randCode",randCode));
	        post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
			HttpResponse response1 = http.execute(post);
			HttpEntity entity1 = response1.getEntity();
			int code = response1.getStatusLine().getStatusCode();
			
			String send = EntityUtils.toString(entity1,"UTF-8");
			
			System.out.println(send);
			
			if (code == 302) {
	            Header header = response1.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
	            String newuri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
	            System.out.println(newuri);
	        }else{
			
				Document doc = (Document) Jsoup.parse(send);
				String e = "";
				try{
					e = doc.getElementsByClass("trip-table-box").get(0).toString();
				}catch(Exception ee){
					e = doc.getElementsByClass("error-txt").get(0).toString();
					//验证码不正确，调若快报错接口报错
					if(e.indexOf("验证码不正确")>0){
						String result1 = RuoKuaiUtils.error(id);
						System.out.println(result1);
					}else {
						ee.printStackTrace();
					}
				}
				et.setState(e);
				return et;
	        }
			return et;
		}

}