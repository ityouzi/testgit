package cn.myjava.jars.option.getInfo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GetInfoByIp {
	
	public String getAddr (String ip) throws IOException {
		Document doc = Jsoup.connect("https://ip.51240.com/"+ip+"__ip/").get();
		Element el = doc.getElementsByTag("tbody").get(3);
		Element e = el.getElementsByTag("td").get(3);
		return e.text();
	}
	
	public static void main(String[] args) throws IOException {
		String addr = new GetInfoByIp().getAddr("27.17.73.105");
		System.out.println(addr);
	}
	
}