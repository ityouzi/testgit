package cn.myjava.jars.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class QNOrderTest {

	public static void main(String[] args) throws IOException {
		String url = "http://dzc.trade.qunar.com/tts/interface/new/orderexport";
		Connection con = Jsoup.connect(url);
		con.data("user", "qunar01");
		con.data("pass", "123456");
		con.data("type", "exact");
		con.data("status", "");
		con.data("orderNo","dzc150111022235287");
		
		Document doc = con.post();
		System.out.println(doc);
	}
	
}