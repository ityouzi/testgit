package cn.myjava.jars.hkgs.zh;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;

public class OWShoppingQuery3 {
	
	private static String query_url = "http://m.shenzhenair.com/weixin_front/books.do?method=queryFlightsView";
		
	public static void main(String[] args) throws Exception {
		
		String sessionId = "JSESSIONID=F52152B23732E307E7B2586555491EE7-n1; Path=/weixin_front/";
		final String cookie ="CoreSessionId=583e9c78b5d010a6d18e038a0e24198397abcf3f68949bed; _g_sign=f0a2791dee225e28ce7cc46646265ca9; _gscu_1739384231=278226048dkmdu44; _gscu_1330024767=28294961fmlyzo84; _gscs_1330024767=28374614gx69db84|pv:4; _gscbrs_1330024767=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22163b952d89a15c-0b31c5985-5e42211d-2073600-163b952d89b2f5%22%2C%22%24device_id%22%3A%22163b952d89a15c-0b31c5985-5e42211d-2073600-163b952d89b2f5%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22nowStatus%22%3A%22%E9%9D%9E%E4%BC%9A%E5%91%98%E7%99%BB%E5%BD%95%22%2C%22platform%22%3A%22WAP%22%2C%22loginid%22%3A%22%22%7D%7D"
				+ ";"+sessionId;
		Map<String, String> map = new HashMap<String, String>();
		map.put("depdate", "2018-06-15");
		map.put("hctype", "DC");
		map.put("orgcity", "SZX");
		map.put("dstcity", "PEK");
		map.put("depdate2", "");
		
		String request = JSON.toJSONString(map);

		final Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("request", request);
		
//		String result2 = HTTPForSzair.post(query_url,parameter, null,cookie);
//		System.out.println(result2);
		
		Document doc = Jsoup.connect("https://proxyapi.mimvp.com/api/fetchopen.php?orderid=864226070910603589&num=50&country=中国&http_type=1,2&ping_time=0.3&transfer_time=1&result_fields=1,2&result_format=json").ignoreContentType(true).get();
		JSONObject json = JSONObject.fromObject(doc.getElementsByTag("body").text());
		JSONArray ja = json.getJSONArray("result");
		for(int i = 0;i<ja.size();i++){
			JSONObject jo = ja.getJSONObject(i);
			String[] ips = jo.getString("ip:port").split(":");
			final String ip = ips[0];
			final int port = Integer.parseInt(ips[1]);
			System.out.println(ip+"***************"+port);
			
			new Thread(){
				@Override
	            public void run() {
					String result2 = null;
					while(1==1){
						try {
							result2 = HTTPForSzair.post(query_url,parameter, null,cookie,ip,port);
							System.out.println(result2);
							System.out.println("线程10秒后继续");
							Thread.sleep(10000);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("线程10秒后继续");
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							continue;
						}
					}
	            }
			}.start();
			
		}
	}
}