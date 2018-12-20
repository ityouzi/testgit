package cn.myjava.jars.test.ip;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class IPTest {
	
	public static void main(String[] args) throws IOException {
		String url = "http://39.108.59.38:7772/Tools/proxyIP.ashx?OrderNumber=aaa8668efa4b7d4d1bc931f57576ffc4&poolIndex=70021&cache=1&action=GetInf&qty=1";
		Document doc = Jsoup.connect(url).get();
		String result = doc.getElementsByTag("body").get(0).text();
		result = result.substring(result.indexOf("本次申请IP数量：1")+11, result.length());
		String[] r = result.split(":");
		System.out.println(r[0]);
	}

}