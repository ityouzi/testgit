package cn.tools.getinfobymac;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetInfoByMac {
	
	public Map<String, Object> getInfo(String mac) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Document doc = Jsoup.connect("https://mac.51240.com/"+mac+"__mac/").get();
		Element el = doc.getElementsByTag("tbody").get(1);
		Elements els = el.getElementsByTag("td");
		map.put(els.get(2).text(), els.get(3).text());
		map.put(els.get(4).text(), els.get(5).text());
		return map;
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, Object> map = new GetInfoByMac().getInfo("8C-BE-BE-2A-D5-70");
		System.out.println(JSONObject.fromObject(map));
	}

}