package cn.myjava.jars.option.yyreg;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.net.telnet.TelnetClient;
import org.jsoup.nodes.Document;

import cn.myjava.jars.http.JsoupUtils;

public class Test {
	
	public static void main(String[] args) {
		
		String url = "https://proxyapi.mimvp.com/api/fetchopen.php?orderid=862122871114504187&num=100&anonymous=3,5&ping_time=0.3&result_sort_field=2&result_format=json";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36");
		Document doc = JsoupUtils.getDocGet(url, headers);
//		System.out.println(doc);
		String result = doc.getElementsByTag("body").text();
		JSONObject json = JSONObject.fromObject(result);
		JSONArray ja = json.getJSONArray("result");
		TelnetClient telnet = new TelnetClient();
		for(int i = 0;i<ja.size();i++){
			JSONObject jo = (JSONObject) ja.get(i);
//			System.out.println(jo);
			String[] ipport = jo.getString("ip:port").split(":");
			String ip = ipport[0];
			int port = Integer.parseInt(ipport[1]);
			if(checkIP(telnet,ip,port)){
				System.err.println(ip+":"+port);
			}
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
