package cn.myjava.jars.hkgs.tv;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OWShoppingQuery3 {

	private static String query_url = "https://tv.travelsky.com/tvb2b/OWShoppingQuery.do";
	
	private static String login_url = "https://tv.travelsky.com/tvb2b/Login.do";
	
	private static String code_url = "https://tv.travelsky.com/tvb2b/VerificationCode.do";

	
		
	public static void main(String[] args) throws Exception {
		
		for(int r = 1;r<400;r++){
			System.out.println(r+"次************************************************************");
		
		String cookie = "HTTPOnly; JSESSIONID=99EE0FD9A9D06EE6550EE36B9EF1943A.TVB2BServer2; Webtrends=27.18.189.9.1527839169330800; nullTGC=TGT-1-EnmJed6CD7JZuy2JubBAUk3nXdaMUoGh4nn; X-LB=1.32d.7cf.ac46447d";
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("tripType", "0");
		parameter.put("orgCity", "NKG");
		parameter.put("dstCity", "KWE");
		parameter.put("takeOffDate", "2018-06-04");
		parameter.put("groupType", "0");
		String result2 = HTTPUtils.post(query_url,parameter, null,cookie);
//		System.out.println(result2);
		
		Document doc = Jsoup.parse(result2);
		Element e = doc.getElementsByClass("confirm_p2").get(0);
//		System.out.println(e);
		Elements es = e.getElementsByClass("bk_list");
		for(Element ee : es){
//			System.out.println(ee);
			
			
			Element eleft = ee.getElementsByClass("bk_lis_lef").get(0).getElementsByClass("rig").get(0);
			
//			System.err.println(eleft);
			
			Elements els = eleft.getElementsByTag("p");
			
//			System.err.println(els);
			
			String hbh = els.get(0).text();
			String cfTime = els.get(1).text();
			String ddTime = els.get(2).text();
			
//			System.err.println(hbh);
//			System.err.println(cfTime);
//			System.err.println(ddTime);
			
			Elements ers = ee.getElementsByClass("bk_lis_rig");
			
//			System.err.println(ers);
			for(Element er : ers){
				Element edl = er.getElementsByTag("dl").get(0);
				String cabin = edl.getElementsByTag("dt").get(0).text();
				Elements edds = edl.getElementsByTag("dd");
				String seats = edds.get(0).text();
				String price = edds.get(1).text();
				String ret = edds.get(2).text();
				
				System.out.println(hbh+"--"+cfTime+"--"+ddTime+"--"+cabin+"--"+seats+"--"+price+"--"+ret);
			}
			
			
		}
	}
	}
}
