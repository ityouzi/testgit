package cn.myjava.jars.option.proxyTest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.myjava.jars.http.CloseableHttpClientUtils;

public class ProxyRun extends Thread {
	
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

	@Override
	public void run(){
		String url = "http://www.flycua.com/";
		CloseableHttpClient http = HttpClients.createDefault();
		HttpEntity entity = CloseableHttpClientUtils.getCloseablehttpGetProxy(http, url, ip, port, null);
		if(entity!=null){
			try {
				System.out.println(EntityUtils.toString(entity, "UTF-8"));
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.err.println("1");
		}
	}

}