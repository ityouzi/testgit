package cn.wx.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class WXService {
	static String appid = "wx11279bb04f32769a";
	static String secret = "cd1e5d654fe7d7f9ee1d3bbf22e21ead";
			
	public Object getAT(String code) {
		try{
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+
					"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
			Document doc = Jsoup.connect(url).ignoreContentType(true).get();
			String at = doc.getElementsByTag("body").get(0).text();
			System.out.println(at);
			return at;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	

}
