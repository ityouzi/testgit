package cn.tools.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//httpclient代理IP发送请求
public class ZZ {
	
	public static void main(String[] args) throws IOException {
		String url = "https://www.51240.com/";
		
		
		CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
		
		
        HttpGet httpGet=new HttpGet(url); // 创建httpget实例
        HttpHost proxy=new HttpHost("60.175.196.110", 23414);
        RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        String html = EntityUtils.toString(entity, "utf-8"); // 获取网页内容
        Document doc = Jsoup.parse(html);
		Elements es = doc.getElementsByTag("a");
		System.err.println(es.get(14).text());
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
	}

}