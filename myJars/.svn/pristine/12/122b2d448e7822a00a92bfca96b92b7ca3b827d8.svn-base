package cn.myjava.jars.hkgs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class KN {
	
	public static void main(String[] args) throws IOException {
		
		String url = "https://m.flycua.com/h5/pip/book/flightSearch.json";
		
		Document doc = Jsoup.connect(url)
				.ignoreContentType(true)
				.header("Content-Type","application/json;charset=UTF-8")
				.requestBody("{\"tripType\":\"OW\",\"orgCode\":\"NAY\",\"dstCode\":\"SHA\",\"takeoffdate1\":\"2018-06-20\",\"takeoffdate2\":\"\"}")
				.post();
		JSONObject json = JSONObject.fromObject(doc.getElementsByTag("body").get(0).text());
		JSONObject json1 = json.getJSONObject("goFlightInfo");
		JSONArray ja = json1.getJSONArray("flightInfo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list1 = new ArrayList<Object>();
		
		String arrivalTime = "";				//到达时间
		String departTime = "";					//起飞时间
		
		for(int i =0;i<ja.size();i++){
			Map<String, Object> map1 = new HashMap<String, Object>();
			List<Object> list2 = new ArrayList<Object>();
			
			JSONObject json2 = (JSONObject) ja.get(i);
			String lowPrice = json2.getString("totalAmount");					//最低价格
			arrivalTime = json2.getString("arrivalTime");						//到达时间
			departTime = json2.getString("departTime");							//起飞时间
			
			JSONArray ja1 = json2.getJSONArray("flightSegs");
			for(int j =0;j<ja1.size();j++){
				Map<String, Object> map2 = new HashMap<String, Object>();
				List<Object> list3 = new ArrayList<Object>();
				
				JSONObject json3 = (JSONObject) ja1.get(j);
				String flightNo = json3.getString("flightNo");					//航班号
				String departTerminal = json3.getString("departTerminal");		//航站楼
				String brandSegSize = json3.getString("brandSegSize");			//产品数量
				JSONArray ja2 = json3.getJSONArray("brandSeg");
				for(int m =0;m<ja2.size();m++){
					Map<String, Object> map3 = new HashMap<String, Object>();
					List<Object> list4 = new ArrayList<Object>();
					
					JSONObject json4 = (JSONObject) ja2.get(m);
					JSONObject json51 = json4.getJSONObject("brandInfo");		//产品信息
					JSONObject json52 = json4.getJSONObject("canRefundChange");	//退改规则
					JSONObject json53 = json4.getJSONObject("handBaggage");		//行李信息
					JSONObject json54 = json4.getJSONObject("redPaperAmount");	//红包信息
					JSONArray ja3 = json4.getJSONArray("price");				//价格信息
					
					String brandCode = json4.getString("brandCode");			//产品代号
					String cabinCode = json4.getString("cabinCode");			//舱位
					String remaindNum = json4.getString("remaindNum");			//剩余数量
					String redTxt = json54.getString("text");					//红包描述
					String xl = json53.getString("description");				//行李描述
					String rcRole = json52.getString("text");					//退改描述
					String brandTxt = json51.getString("text");					//产品名称
					
					for(int n = 0;n<ja3.size();n++){
						Map<String, String> map4 = new HashMap<String, String>();
						JSONObject json6 = (JSONObject) ja3.get(n);
						String psgType = json6.getString("psgType");
						String price = json6.getString("price");
						map4.put("psgType", psgType);
						map4.put("price", price);
						list4.add(map4);
					}
					
					map3.put("brandCode", brandCode);
					map3.put("cabinCode", cabinCode);
					map3.put("remaindNum", remaindNum);
					map3.put("redTxt", redTxt);
					map3.put("xl", xl);
					map3.put("rcRole", rcRole);
					map3.put("brandTxt", brandTxt);
					map3.put("priceInfo", list4);
					list3.add(map3);
				}
				
				map2.put("flightNo", flightNo);
				map2.put("departTerminal", departTerminal);
				map2.put("brandSegSize", brandSegSize);
				map2.put("flightInfo", list3);
				list2.add(map2);
			}
			
			map1.put("lowPrice", lowPrice);
			map1.put("airInfo", list2);
			list1.add(map1);
		}
		
		map.put("arrivalTime", arrivalTime);
		map.put("departTime", departTime);
		map.put("data", list1);
		JSONObject show = JSONObject.fromObject(map);
		System.out.println(show);
	}

}