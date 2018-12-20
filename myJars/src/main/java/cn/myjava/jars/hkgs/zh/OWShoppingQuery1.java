package cn.myjava.jars.hkgs.zh;

public class OWShoppingQuery1 {
	
	private static String cookie_url = "http://m.shenzhenair.com/weixin_front/wxcommon.do?method=getAirFanInfo";
	
	public static void main(String[] args) throws Exception {
		String proxy_ip = "";
		int proxy_port = 0;
		
		String cookie = HTTPForSzair.connect(cookie_url, proxy_ip, proxy_port);
		System.out.println(cookie);
	}
}