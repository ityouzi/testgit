package cn.myjava.jars.test;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.myjava.jars.http.HttpClientUtils;

public class JsoupTest {
	
	
	public static void main(String[] args) throws IOException {
		String url = "https://m.flycua.com/h5/pip/book/flightSearch.json";
		String parmes = "{\"tripType\": \"OW\",\"orgCode\": \"NAY\",\"dstCode\": \"SHA\",\"takeoffdate1\": \"2018-11-09\",\"takeoffdate2\": \"\"}";
		Map<String, String> hearders = new HashMap<String, String>();
		hearders.put("Content-Type", "application/json;charset=UTF-8");
		Connection con = Jsoup.connect(url);
		con.requestBody(parmes);
		con.headers(hearders);
		con.timeout(2000);
		con.proxy("47.100.46.155", 80);
		Document doc = con.validateTLSCertificates(false).ignoreContentType(true).post();
		System.out.println(doc.getElementsByTag("body").get(0).text());
	}
	
	
	public static void main4444(String[] args) throws IOException {
		
//			String url = "http://121.14.201.14/697429A8DF14775D34DFF3F75/03000A01005BA4697A283D9066248B7956EDEC-4BCB-4BFE-84A5-8536113C9F32.mp4?ccode=01010101&duration=123&expire=18000&psid=8290863a2aaa9f1a6c77a9c85b511b4b&ups_client_netip=1b12bc23&ups_ts=1538020992&ups_userid=&utid=WnQwxbULVPcDAC3kxDvK5ECD&vid=XMzgzMjA4NTQzNg%3D%3D&vkey=Ae02d730b2957eb02354fba427fdbf7a5";
			String url2 = "https://tcmobileapi.17usoft.com/flight/queryhandler.ashx";
			
			String parmes = "{\"request\":{\"body\":{\"SessionId\":\"25360661-1538117404052\",\"Departure\":\"WUH\",\"DepartureDate\":\"2018-10-30\",\"HasAirPort\":\"1,1\",\"linkTrackerId\":\"a2adf957-c2e5-11e8-85b1-d39fc57ce20d\",\"clientInfo\":{\"pushInfo\":\"de9813e4316bd3df4c9185bfb9f7a508146d4ab8\",\"networkType\":\"wifi\",\"systemCode\":\"tc\",\"extend\":\"4^4.4.2,5^SM-G955N,6^-1,os_v^19\",\"refId\":\"16019635\",\"mac\":\"B4:B5:2F:60:70:0A\",\"clientId\":\"91192cf44674b7dc3d63b8a953fae821fcb015a76601\",\"area\":\"1|11|165\",\"manufacturer\":\"samsung\",\"device\":\"b4b52f60700a4994|armeabi-v7a|720*1280*240|SM-G955N|134699cc\",\"clientIp\":\"172.17.100.15\",\"versionType\":\"android\",\"deviceId\":\"b4b52f60700a4994\",\"versionNumber\":\"9.0.4\"},\"ArrivalName\":\"北京\",\"getType\":1,\"IsBaby\":0,\"traceId\":\"a2adf957-c2e5-11e8-85b1-d39fc57ce20d\",\"Arrival\":\"PEK\",\"DepartureName\":\"上海\",\"deviceId\":\"b4b52f60700a4994\"},\"header\":{\"accountID\":\"c26b007f-c89e-431a-b8cc-493becbdd8a2\",\"digitalSign\":\"1544ee8f42ef9808edd7d339625aae0f\",\"reqTime\":\"1538117405027\",\"serviceName\":\"getflightlist\",\"version\":\"20111128102912\"}}}";
			JSONObject json = JSONObject.fromObject(parmes);
			@SuppressWarnings("unchecked")
			Map<String,Object> maps = JSONObject.fromObject(json);
			
			Map<String,String> maps1 = new HashMap<String, String>();
//			maps1.put("apmat", "b4b52f60700a4994|i|20180928145005017|00004");
//			maps1.put("alisign", "88afd77a62dfd4ed3a87ea5269287324c053f4cc");
//			maps1.put("reqdata", "c9d57fcec753ee27b148d887f5a40178");
			maps1.put("reqdata", md5(parmes+ "4957CA66-37C3-46CB-B26D-E3D9DCB51535"));
//			maps1.put("sxx", "6af49231c9638da0a61bba0da49ae767");
			
			String result = HttpClientUtils.getHttpclientJson(url2, maps, maps1);
			
//			System.out.println(result);
			
			JSONObject jsonresuslt = JSONObject.fromObject(result);
			
			JSONArray ja = jsonresuslt.getJSONObject("response").getJSONObject("body").getJSONObject("body").getJSONArray("FlightInfoSimpleList");
			
			for(int i = 0;i<ja.size();i++){
				JSONObject jo = (JSONObject) ja.get(i);
				String flightNo = jo.getString("flightNo");
				JSONObject joo = jo.getJSONObject("productPrices");
				@SuppressWarnings("unchecked")
				Map<String,Object> map = JSONObject.fromObject(joo);
				Set<String> ss = map.keySet();
				int price1 = 9999;
				int price = 0;
				for(String s :ss){
					price = Integer.valueOf(map.get(s).toString());
					if(price<price1){
						price1 = price;
					}
				}
				System.err.println(flightNo+"最低价"+price1);
			}
	}
	
	
	
	
	
	
	public static void main1(String[] args) {
		String body = "{\"request\":{\"body\":{\"SessionId\":\"25360661-1538117404052\",\"Departure\":\"SHA\",\"DepartureDate\":\"2018-09-30\",\"HasAirPort\":\"1,1\",\"linkTrackerId\":\"a2adf957-c2e5-11e8-85b1-d39fc57ce20d\",\"clientInfo\":{\"pushInfo\":\"de9813e4316bd3df4c9185bfb9f7a508146d4ab8\",\"networkType\":\"wifi\",\"systemCode\":\"tc\",\"extend\":\"4^4.4.2,5^SM-G955N,6^-1,os_v^19\",\"refId\":\"16019635\",\"mac\":\"B4:B5:2F:60:70:0A\",\"clientId\":\"91192cf44674b7dc3d63b8a953fae821fcb015a76601\",\"area\":\"1|11|165\",\"manufacturer\":\"samsung\",\"device\":\"b4b52f60700a4994|armeabi-v7a|720*1280*240|SM-G955N|134699cc\",\"clientIp\":\"172.17.100.15\",\"versionType\":\"android\",\"deviceId\":\"b4b52f60700a4994\",\"versionNumber\":\"9.0.4\"},\"ArrivalName\":\"北京\",\"getType\":1,\"IsBaby\":0,\"traceId\":\"a2adf957-c2e5-11e8-85b1-d39fc57ce20d\",\"Arrival\":\"PEK\",\"DepartureName\":\"上海\",\"deviceId\":\"b4b52f60700a4994\"},\"header\":{\"accountID\":\"c26b007f-c89e-431a-b8cc-493becbdd8a2\",\"digitalSign\":\"1544ee8f42ef9808edd7d339625aae0f\",\"reqTime\":\"1538117405027\",\"serviceName\":\"getflightlist\",\"version\":\"20111128102912\"}}}";
		String reqdata = md5(body+ "4957CA66-37C3-46CB-B26D-E3D9DCB51535");
		System.err.println(reqdata);
	}
	
	 
	public static String md5(String paramString) {
		try {
			byte[] bytes = MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"));
			StringBuffer localStringBuffer = new StringBuffer();
			int i = 0;
			while (i < bytes.length) {
				int j = bytes[i] & 0xFF;
				if (j < 16) {
					localStringBuffer.append("0");
				}
				localStringBuffer.append(Integer.toHexString(j));
				i += 1;
			}
			paramString = localStringBuffer.toString();
			System.err.println(paramString);
			return paramString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main3(String[] args) {
		System.out.println(DigestUtils.md5Hex("外部测试2#Ctrip@2")); 
	}
	

}