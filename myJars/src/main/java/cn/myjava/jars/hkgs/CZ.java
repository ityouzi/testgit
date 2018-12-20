package cn.myjava.jars.hkgs;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.myjava.jars.http.CloseableHttpClientUtils;

public class CZ {
	
	public static void main(String[] args) throws Exception {
		String url1 = "http://b2c.csair.com/B2C40/modules/bookingnew/main/flightSelectDirect.html?t=S&c1=CAN&c2=HGH&d1=2018-05-19&at=1&ct=0&it=0";
		String url2 = "http://b2c.csair.com/B2C40/query/jaxb/minprice/query.ao";
		String ip = "";
		int port= 0;
		CloseableHttpClient http = HttpClients.createDefault();
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36 Maxthon/5.1.5.2000");
		
		HttpEntity entity = CloseableHttpClientUtils.getCloseablehttpGetProxy(http, url1, ip, port, headers);
		String html = EntityUtils.toString(entity,"UTF-8");
		System.out.println(html);

		Map<String,String> params = new HashMap<String, String>();
		params.put("json", "{'depcity':'CAN', 'arrcity':'HGH', 'flightdate':'20180519','adultnum':'1', 'childnum':'0', 'infantnum':'0', 'cabinorder':'0', 'airline':'1', 'flytype':'3', 'international':'0', 'action':'0', 'segtype':'1', 'cache':'0','channel':'B2CPC1'}");
		HttpEntity entity2 = CloseableHttpClientUtils.getCloseablehttpPostProxy(http, url2, ip, port, headers, params);
		String html2 = EntityUtils.toString(entity2,"UTF-8");
		System.out.println(html2);
		
		//没有成功
	}

}