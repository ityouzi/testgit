package cn.myjava.jars.option.hlzh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.myjava.jars.entity.HlzhEntity;
import cn.myjava.jars.entity.HlzhEntityMx1;
import cn.myjava.jars.entity.HlzhEntityMx2;
import cn.myjava.jars.option.img.OcrUtils;

@SuppressWarnings("deprecation")
public class HlzhUtils {
	
	public static void main(String[] args) throws IOException, TesseractException {
		HlzhEntity ef = new HlzhEntity();
		ef.setHbh("KN2215");
		ef.setDate("2018-12-15");
		ef = getFull(ef);
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(ef);
		System.out.println(json);
	}
	
	public static HlzhEntity getFull(HlzhEntity ef) throws ClientProtocolException, IOException{
		ITesseract instance = new Tesseract();
		String url = "http://www.umetrip.com/mskyweb/fs/fc.do?flightNo="+ef.getHbh()+"&date="+ef.getDate()+"&channel=";
		HttpClient http = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = http.execute(get);
		HttpEntity entity = response.getEntity();
		Document doc = Jsoup.parse(EntityUtils.toString(entity,"UTF-8"));
		
		System.out.println(doc);
		
		Element et = doc.getElementsByClass("del_com").get(0);
		String hbinfo = et.getElementsByTag("b").get(0).text()+"***"+et.getElementsByTag("h1").get(0).text().replace(" ", "");
		ef.setHbinfo(hbinfo);
		List<HlzhEntityMx1> listMx1 = new ArrayList<HlzhEntityMx1>();
		List<HlzhEntityMx2> listMx2 = new ArrayList<HlzhEntityMx2>();
		
		Elements uls = et.getElementsByTag("ul");
		for(Element ul : uls){
			Elements lis = ul.getElementsByTag("li");
			HlzhEntityMx1 mx1 = new HlzhEntityMx1();
			
			Element elszdl = lis.get(4);
			String lszdlimg = getImgString(http,elszdl.getElementsByTag("img").get(0).attr("src"),instance).replace("\n", "").replace(" ", "");
			lszdlimg = lszdlimg.substring(0, 2)+"."+lszdlimg.substring(2, lszdlimg.length());
			
			mx1.setCity(lis.get(0).text());
			mx1.setMileage(lis.get(1).text());
			mx1.setTime(lis.get(2).text());
			mx1.setAge(lis.get(3).text());
			mx1.setPer(elszdl.text()+lszdlimg);
			
			listMx1.add(mx1);
		}
		
		Elements eflybox = doc.getElementsByClass("fly_box");
		for(Element e : eflybox){
			HlzhEntityMx2 mx2 = new HlzhEntityMx2();
			//航班头部信息
			Element etop = e.getElementsByClass("f_tit").get(0);
			mx2.setJc(etop.getElementsByTag("h2").get(0).text());
			if(etop.getElementsByTag("div").size()>1){
				mx2.setFinfo(etop.getElementsByTag("div").get(1).text());
			}
			
			//天气信息
			Element efly = e.getElementsByClass("f_r").get(0);
			Elements eflyinfo = efly.getElementsByTag("p");
			mx2.setWd(eflyinfo.get(0).text());
			mx2.setNjd(eflyinfo.get(1).text());
			mx2.setLl(eflyinfo.get(2).text());
			
			//航延信息
			Element ehy = e.getElementsByClass("time").get(0);
			String jhqf = ehy.getElementsByTag("dd").get(0).text();
			String sjqf = ehy.getElementsByTag("dd").get(1).text();
			String jhqfimg = ehy.getElementsByTag("img").get(0).attr("src");
			String sjqfimg = ehy.getElementsByTag("img").get(1).attr("src");
			jhqf += getImgString(http,jhqfimg,instance).replace("\n", "").replace(" ", "");
			sjqf += getImgString(http,sjqfimg,instance).replace("\n", "").replace(" ", "");
			mx2.setJhqf(jhqf);
			mx2.setSjqf(sjqf);
			
			//今日准点率
			Elements zdl = e.getElementsByClass("pun").get(0).getElementsByTag("p");
			String jrjg = zdl.get(0).text();
			String jrcg = zdl.get(3).text();
			String jrjgimg = zdl.get(1).getElementsByTag("img").get(0).attr("src");
			String jrcgimg = zdl.get(4).getElementsByTag("img").get(0).attr("src");
			jrjg += getImgString(http,jrjgimg,instance).trim();
			jrcg += getImgString(http,jrcgimg,instance).trim();
			mx2.setJrjg(jrjg);
			mx2.setJrcg(jrcg);
			
			listMx2.add(mx2);
		}
		ef.setPinfo(listMx1);
		ef.setFlybox(listMx2);
		return ef;
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