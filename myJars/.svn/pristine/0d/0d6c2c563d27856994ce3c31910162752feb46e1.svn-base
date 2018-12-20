package cn.myjava.jars.option.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.myjava.jars.option.img.OcrUtils;

@SuppressWarnings("deprecation")
public class Hlzhtest {
	public static void main(String[] args) {
		String hbh = "KN5867";
		String date = "2018-12-28";
		for(int i = 0;i<10;i++){
			Map<String, String> map = getInfo(hbh,date);
			if(map!=null){
				break;
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static Map<String,String> getInfo(String hbh,String date) {
		ITesseract instance = new Tesseract();
		String url = "http://www.umetrip.com/mskyweb/fs/fc.do?flightNo="+hbh+"&date="+date+"&channel=";
		HttpClient http = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try{
			HttpResponse response = http.execute(get);
			HttpEntity entity = response.getEntity();
			Document doc = Jsoup.parse(EntityUtils.toString(entity,"UTF-8"));
		
			Elements ets = doc.getElementsByClass("del_com");
			if(ets.size()>0){
			
				Element et = ets.get(0);
//				System.out.println(et);
				//机型
				Element et1 = et.getElementsByTag("ul").get(0);
				String jx = et1.getElementsByClass("age").get(0).getElementsByTag("span").get(0).text();
				jx = jx.split("/")[0];
				
//				System.err.println(jx);
				
				Elements etss = et.getElementsByClass("fly_box");
				//经停次数
				int stopcount = etss.size()-2;
				
				Element eeq = etss.get(0);
				Element eel = etss.get(etss.size()-1);
				
				String imgq = eeq.getElementsByTag("img").get(0).attr("src");
				String imgl = eel.getElementsByTag("img").get(0).attr("src");
				//计划起飞、落地时间
				String qftime = getImgString(http,imgq,instance).replace("\n", "").replace(" ", "");
				String ldtime = getImgString(http,imgl,instance).replace("\n", "").replace(" ", "");
				
				Map<String,String> map = new HashMap<String, String>();
				map.put("jx", jx);
				map.put("stops", stopcount+"");
				map.put("qftime", qftime);
				map.put("ldtime", ldtime);
				System.out.println(jx+"***"+stopcount+"***"+qftime+"***"+ldtime);
				
				return map;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static String getImgString(HttpClient http,String url,ITesseract instance) throws ClientProtocolException, IOException{
		//请求图片
		HttpGet get = new HttpGet(url);
		HttpResponse response = http.execute(get);
		HttpEntity entity = response.getEntity();
		byte[] data = EntityUtils.toByteArray(entity);
		
        //识别图片
		String path = "c:/mpl.png";
        FileOutputStream fos = new FileOutputStream(path);  
        fos.write(data);  
        fos.close();
        File imageFile = new File(path);
        return OcrUtils.randCode(imageFile,instance);
	}

}

