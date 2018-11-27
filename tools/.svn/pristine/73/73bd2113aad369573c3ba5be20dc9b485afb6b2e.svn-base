package cn.tools.yy.in;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class In {
	
	public static void main(String[] args) throws IOException {
		String url = "http://www.yy.com/22795823";
		Document doc = Jsoup.connect(url).timeout(100000).ignoreContentType(true).maxBodySize(0).get();
		System.out.println(doc);
	}

}