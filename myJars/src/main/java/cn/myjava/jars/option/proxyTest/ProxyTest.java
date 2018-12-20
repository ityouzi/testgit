package cn.myjava.jars.option.proxyTest;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Document;

import cn.myjava.jars.http.CloseableHttpClientUtils;
import cn.myjava.jars.http.JsoupUtils;

public class ProxyTest {
	
	private String ip;
	private int port;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		
		String url = "https://proxyapi.mimvp.com/api/fetchopen.php?orderid=862122871114504187&num=1000&country=中国&ping_time=1&result_fields=1,2&result_format=json";
		Document doc = JsoupUtils.getDocGet(url, null);
		JSONObject json = JSONObject.fromObject(doc.getElementsByTag("body").get(0).text());
		JSONArray ja = json.getJSONArray("result");
		List<ProxyTest> list = new ArrayList<ProxyTest>();
		for(int i=0;i<ja.size();i++){
			JSONObject jo = ja.getJSONObject(i);
			ProxyTest pt = new ProxyTest();
			String ipport = jo.getString("ip:port");
			String[] ips = ipport.split(":");
			pt.setIp(ips[0]);
			pt.setPort(Integer.parseInt(ips[1]));
			list.add(pt);
		}
		
		for(ProxyTest proxy : list){
			ProxyRun pr = new ProxyRun();
			pr.setIp(proxy.getIp());
			pr.setPort(proxy.getPort());
			pr.start();
		}
		
	}

}