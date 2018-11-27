package cn.tools.qunar;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetPolicy {
	
	public static void main(String[] args) throws IOException {
		
		String url = "http://fuwu.qunar.com/index.do?domain=dzc.trade.qunar.com#sid=nav_FN_POLICY&module=welcome9527|nav_FN_POLICY";
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc);
		
	}

}