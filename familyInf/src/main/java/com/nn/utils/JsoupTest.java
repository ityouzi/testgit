package com.nn.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	
	/**
	 * 通过地址爬取数据
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getD(String url) throws IOException{
		
			Document doc;
			doc = Jsoup.connect(url).ignoreContentType(true).get();
			
			Map<String, String> map = new HashMap<String, String>();
			
 			Element e = doc.getElementById("entrybody");
			
			//标题
			String text = e.getElementsByTag("p").get(0).text();
			
			System.err.println(text);
			
			//签的类型
			Element status = e.getElementsByTag("p").get(2);
			
//			System.err.println(status);
			
			String photo = "http:"+e.getElementsByTag("p").get(1).getElementsByTag("img").get(0).attr("src");
			
//			System.err.println(photo);
			
			e.getElementsByTag("p").get(0).remove();
			e.getElementsByTag("p").get(0).remove();
			e.getElementsByTag("p").get(0).remove();
			Elements elements = e.getElementsByTag("p");
//			System.out.println(elements);
			
			map.put("title", text);
			map.put("status", status.toString());
			map.put("photo", photo.toString());
			map.put("elements", elements.toString());
			
			return map ;
		
	}
	
	
	public static List<String> getData() throws IOException{
		List<String> list = new ArrayList<String>();
		File fis = new File("C:/C64E.tmp.txt");
		Document doc = Jsoup.parse(fis, "UTF-8");
		Elements es = doc.getElementsByClass("bjh-p");
		for(Element element : es){
			String text = element.text();
			System.err.println(text);
			text = (String) text.subSequence(text.indexOf("、")+1, text.indexOf("："));
			System.err.println(text);
			list.add(text);
		}
		return list;
	}
	
	public static List<String> getData2() throws IOException{
		List<String> list = new ArrayList<String>();
		File fis = new File("C:/2.txt");
		Document doc = Jsoup.parse(fis, "GBK");
		Elements es = doc.getElementsByClass("article-content").get(0).getElementsByTag("p");
		for(int i = 2;i<es.size()-1;i++){
			String text = es.get(i).text();
			String[] z = text.split("、");
			text = z[1];
			z = text.split(" ");
			for(String zzString : z){
				for(int j = 0;j<zzString.length();j++){
					String rrrr = zzString.charAt(j)+"";
					System.out.println(rrrr);
					list.add(rrrr);
				}
			}
		}
		return list;
	}
	
	public static List<String> getData3() throws IOException{
		List<String> list = new ArrayList<String>();
		File fis = new File("C:/4.txt");
		Document doc = Jsoup.parse(fis, "GBK");
		String esString = doc.getElementsByTag("p").get(0).text();
		String[] es = esString.split("、");
		for(String e : es){
			list.add(e);
		}
		return list;
	}
	
	public static List<String> getData4() throws IOException{
		List<String> list = new ArrayList<String>();
		File fis = new File("C:/5.txt");
		Document doc = Jsoup.parse(fis, "GBK");
		String esString = doc.getElementsByTag("p").get(0).text();
		String[] es = esString.split(" ");
		for(String e : es){
			e = e.replace("	", "");
			for(int i = 0;i<e.length();i++){
				e = e.charAt(i)+"";
				list.add(e);
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		getData2();
		
	}
}
