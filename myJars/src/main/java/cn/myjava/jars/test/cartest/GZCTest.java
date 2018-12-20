package cn.myjava.jars.test.cartest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GZCTest {
	@SuppressWarnings("deprecation")
	public static List<Map<String, String>> getCar() {
		String url = "http://m.guazi.com/wh/buy/i7/";
		String cookie = "uuid=fb1af3c4-b6d4-4eca-e86c-8005c7fead11; __utmganji_v20110909=5b00a026-a47e-4a33-e82a-4666ff7fc135; ganji_uuid=1858718637954288037091; cityDomain=wh; lg=1; chg_city=1; antipas=V13787S9141t9770389uH0b; cainfo=%7B%22ca_s%22%3A%22pz_baidu%22%2C%22ca_n%22%3A%22tbmkbturl%22%2C%22ca_i%22%3A%22-%22%2C%22ca_medium%22%3A%22-%22%2C%22ca_term%22%3A%22-%22%2C%22ca_content%22%3A%22-%22%2C%22ca_campaign%22%3A%22-%22%2C%22ca_kw%22%3A%22-%22%2C%22keyword%22%3A%22-%22%2C%22ca_keywordid%22%3A%22-%22%2C%22scode%22%3A%2210103000312%22%2C%22ca_transid%22%3Anull%2C%22platform%22%3A%222%22%2C%22version%22%3A1%2C%22ca_b%22%3A%22-%22%2C%22ca_a%22%3A%22-%22%2C%22display_finance_flag%22%3A%22-%22%2C%22client_ab%22%3A%22-%22%2C%22guid%22%3A%22fb1af3c4-b6d4-4eca-e86c-8005c7fead11%22%2C%22sessionid%22%3A%22548431a7-ead2-4746-fcc7-2d58f85cbc58%22%7D; clueSourceCode=%2A%2300; preTime=%7B%22last%22%3A1543036839%2C%22this%22%3A1542362039%2C%22pre%22%3A1542362039%7D; __tins__19717059=%7B%22sid%22%3A%201543036846903%2C%20%22vd%22%3A%201%2C%20%22expires%22%3A%201543038646903%7D; __51cke__=; __51laig__=1; zg_did=%7B%22did%22%3A%20%22167208647498-0939c51aa-7d58193a-1fa400-1672086474a7e%22%7D; zg_7e763bc025fc4122af64893d9f28969f=%7B%22sid%22%3A%201543036847144%2C%22updated%22%3A%201543036850220%2C%22info%22%3A%201542438930258%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22m.guazi.com%22%7D; sessionid=548431a7-ead2-4746-fcc7-2d58f85cbc58";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7");
		headers.put("Cookie",cookie);
		headers.put("Host", "m.guazi.com");
		headers.put("Referer", "https://m.guazi.com/wh/buy/i7/");

		Connection con = Jsoup.connect(url);
		con.headers(headers);
		Document doc = null;
		try {
			doc = con.validateTLSCertificates(false).followRedirects(true).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Elements allcars = doc.getElementsByClass("list");
		Elements cars = allcars.get(0).getElementsByClass("list-item");
		ArrayList<Map<String, String>> carlist = new ArrayList<Map<String, String>>();
		// 获取每辆车的信息
		for (int i = 0; i < 10; i++) {
			Map<String, String> car = new HashMap<String, String>();
			Element element = cars.get(i);
			String name = element.getElementsByClass("car-name").get(0).text();
			String licheng = element.getElementsByClass("fl").get(0).text();
			String jiage = element.getElementsByClass("finance-price").get(0).text();
			String didian = element.getElementsByClass("fr city-hide").get(0).text();
			car.put("name", name);
			car.put("licheng", licheng);
			car.put("jiage", jiage);
			car.put("didian", didian);
			carlist.add(car);
		}
		return carlist;
	}

	public static void main(String[] args) {
		List<Map<String, String>> list = getCar();
		if(list!=null){
			Map<String, String> map = getCar().get(0);			//获取最初的车辆
			String lastName = map.get("name");
			System.out.println(lastName);
			while (true) {
				try {
					lastName = map.get("name");
					List<Map<String, String>> car = getCar();	//拿到最新的十辆车
					for (int j = 0; j < car.size(); j++) {
						if (car.get(j).get("name").equals(lastName)) {	//十条车辆信息匹配上次最新的	
							for (int k = 0; k < j; k++) {
								System.out.println(car.get(k));			//输出最新的车辆信息
							}
							map = car.get(0);					//重新赋值最新的车辆
						}
					}
					System.out.println("10自动刷新");
					Thread.sleep(10000);							//线程休眠5秒钟
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}
}