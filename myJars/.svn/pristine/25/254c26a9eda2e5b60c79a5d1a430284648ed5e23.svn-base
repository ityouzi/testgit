package cn.myjava.jars.hkgs.zh;

import java.util.LinkedHashMap;
import java.util.Map;

public class OWShoppingQuery2 {
		
	private static String login_url = "http://m.shenzhenair.com/weixin_front/member.do?method=airMemberLogin";
		
	public static void main(String[] args) throws Exception {
		String proxy_ip = "";
		int proxy_port = 0;

		Map<String, String> parameter = new LinkedHashMap<String, String>();
		parameter.put("air_name", "13157050449");
		parameter.put("air_password", "ls123456");
		parameter.put("checkboxBind", "0");

		String sessionId = "JSESSIONID=F52152B23732E307E7B2586555491EE7-n1; Path=/weixin_front/";
		String cookie ="CoreSessionId=583e9c78b5d010a6d18e038a0e24198397abcf3f68949bed; _g_sign=f0a2791dee225e28ce7cc46646265ca9; _gscu_1739384231=278226048dkmdu44; _gscu_1330024767=28294961fmlyzo84; _gscs_1330024767=28374614gx69db84|pv:4; _gscbrs_1330024767=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22163b952d89a15c-0b31c5985-5e42211d-2073600-163b952d89b2f5%22%2C%22%24device_id%22%3A%22163b952d89a15c-0b31c5985-5e42211d-2073600-163b952d89b2f5%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22nowStatus%22%3A%22%E9%9D%9E%E4%BC%9A%E5%91%98%E7%99%BB%E5%BD%95%22%2C%22platform%22%3A%22WAP%22%2C%22loginid%22%3A%22%22%7D%7D"
				+ ";"+sessionId;
		System.out.println(cookie);
		String result2 = HTTPForSzair.post(login_url, parameter, null, cookie,proxy_ip,proxy_port);
		System.out.println(result2);
	}
}