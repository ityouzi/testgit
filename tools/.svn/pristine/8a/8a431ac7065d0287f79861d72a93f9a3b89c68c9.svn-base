package cn.tools.yyReg;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.net.telnet.TelnetClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class YYMain {
	public static void main(String[] args) throws InterruptedException {
		while(1==1) {
			String[] ips = getIP();
			for(int i =0;i<ips.length;i++) {
				String[] ipip = ips[i].split(":");
				String ip = ipip[0];
				int port = Integer.parseInt(ipip[1]);
				TelnetClient telnet = new TelnetClient();
				if(checkIP(telnet,ip,port)) {
					//开启线程
					YYRun1 run = new YYRun1();
					run.setIp(ip);
					run.setPort(port);
					run.start();
				}
			}
			System.out.println("等待150秒");
			Thread.sleep(150000);
		}
	}
	
	
	//获取代理IP
	public static String[] getIP(){
		String url = Tools.getProperties("config.properties","ips");
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc);
			String[] ips = doc.getElementsByTag("body").get(0).text().split(" ");
//			String result = doc.getElementsByTag("body").get(0).text();
//			JSONObject json = JSONObject.fromObject(result);
//			JSONArray ja = json.getJSONArray("result");
//			TelnetClient telnet = new TelnetClient();
//			List<String> ips = new ArrayList<String>();
//			for(int i = 0;i<ja.size();i++){
//				JSONObject jo = (JSONObject) ja.get(i);
////				System.out.println(jo);
//				String[] ipport = jo.getString("ip:port").split(":");
//				String ip = ipport[0];
//				int port = Integer.parseInt(ipport[1]);
//				if(checkIP(telnet,ip,port)){
//					System.err.println(ip+":"+port);
//					ips.add(ip+":"+port);
//				}
//			}
			return ips;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 检测代理IP连通性
    public static boolean checkIP(TelnetClient telnet,String ip, int port) {
        try {
        	telnet.setConnectTimeout(1000);
            telnet.connect(ip, port);
            telnet.disconnect(); 
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}