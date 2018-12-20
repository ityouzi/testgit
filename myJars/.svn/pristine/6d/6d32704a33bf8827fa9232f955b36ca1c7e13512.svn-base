package cn.myjava.jars.hkgs;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

import cn.myjava.jars.http.JsoupUtils;

public class MU {
	
	public static void main(String[] args) {
		String url = "http://www.ceair.com/booking/new-low-price-calendar!getDynDayLowPriceEc.shtml";
		
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36 Maxthon/5.1.5.2000");
		headers.put("Host", "www.ceair.com");
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cond.monthOffSet","0");
		params.put("cond.depCode","KMG");
		params.put("cond.arrCode","PEK");
		params.put("cond.depCityCode","KMG");
		params.put("cond.arrCityCode","BJS");
		params.put("cond.deptAirport","");
		params.put("cond.arrAirport","");
		params.put("cond.trip","OW");
		params.put("cond.goDt","");
		params.put("cond.depDate","2018-05-17");
		params.put("cond.currency","CNY");
		
		Document doc = JsoupUtils.getDocPost(url, headers, params);
		System.out.println(doc);
	}

}