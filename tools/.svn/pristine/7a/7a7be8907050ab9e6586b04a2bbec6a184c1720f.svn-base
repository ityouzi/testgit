package cn.tools.jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.tools.time.TimeFormat;

public class JsoupTest {
	
	public static void main(String[] args) throws IOException {
//		String url = "https://www.51240.com/";
//		String url = "https://m.flycua.com/h5/pip/book/flightSearch.json";
//		CloseableHttpClient http = HttpClients.createDefault();
//		
//		Map<String,String> heads = new HashMap<String,String>();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		
//		Map<String,String> params = new HashMap<String,String>();
//		params.put("tripType", "OW");
//		params.put("orgCode", "NAY");
//		params.put("dstCode", "PVG");
//		params.put("takeoffdate1", "2018-06-20");
//		params.put("takeoffdate2", "");
		
//		HttpPost post = new HttpPost(url);
//		
//		StringEntity entity = new StringEntity(JSONObject.fromObject(params).toString(),"utf-8");//解决中文乱码问题    
//        entity.setContentEncoding("UTF-8");    
//        entity.setContentType("application/json");    
//        post.setEntity(entity);    
//        HttpResponse result = http.execute(post);  
//        
//        // 请求结束，返回结果  
//        String resData = EntityUtils.toString(result.getEntity());  
		
//		Document doc = Jsoup.parse(EntityUtils.toString(CloseableHttpClientUtils.getCloseablehttpPostProxy(http, url, "117.127.0.205", 80, heads, params)));
//		System.out.println(doc);
//		Elements es = doc.getElementsByTag("a");
//		for(Element e : es){
//			System.out.println(e);
//		}
//		System.err.println(es.get(14).text());
		
		String flights="CIF-NAY&LHW-IQN&NAY-UCB&XMN-NAY&NAY-HLD&NAY-DSN&NAY-CIH&JIC-NAY&NZH-NAY&NAY-CIF&NAY-RLK&XIL-NAY&IQN-NAY&TGO-NAY&BAV-NAY&RLK-NAY&NAY-LDS&LDS-NAY&NAY-SHA&NAY-JUH&ENY-NAY&SJW-PVG&NAY-HLH&HZG-KMG&HAK-NAY&NAY-XMN&NAY-RIZ&SQD-NAY&SHA-SJW&HUZ-SQD&UYN-NAY&NAY-UYN&NAY-WDS&WDS-CAN&SJW-DSN&NAY-IQN&RIZ-SZX&NAY-BAV&NAY-LHW&NAY-PVG&ACX-NAY&DSN-NAY&NNY-NAY&HJJ-NAY&NAY-XIL&PVG-SJW&NAY-NNY&HAK-TEN&CIH-NAY&PVG-NAY&SJW-SZX&UCB-NAY&LCX-NAY&SZX-FUG&NAY-HZG&BFJ-NAY&SZX-SJW&RIZ-NAY&LYG-NAY&HET-NAY&NAY-WUA&NAY-NGB&NAY-LYI&FUG-NAY&LHW-NAY&DOY-NAY&HLH-HLD&FUG-SZX&DSN-CTU&NAY-LYG&HLD-HLH&SZX-RIZ&TSN-SHA&WEH-NAY&NAY-HNY&WUA-NAY&IQN-LHW&DSN-SJW&NAY-HAK&LLF-NAY&NAY-DLC&DLC-SJW&SJW-DLC&NAY-LCX&INC-NAY&SQD-HUZ&NAY-HJJ&NAY-ENY&DLC-NAY&NAY-LLF&YSQ-HLD&NAY-WEH&NAY-AVA&HRB-NAY&DAT-INC&XFN-NAY&JUH-NAY&HLH-NAY&HNY-NAY&SHA-NAY&ZUH-SJW&SHA-TSN&LYI-NAY&PVG-TSN&CTU-SJW&YNZ-NAY&KMG-SJW&NAY-HUZ&TEN-NAY&NAY-INC&NAY-SZX&NAY-CTU&SJW-CTU&WDS-NAY&NAY-YNZ&HUZ-NAY&SJW-ZUH&NAY-BFJ&NAY-YNT&NAY-TEN&HLD-NAY&NAY-HET&SJW-SHA&NAY-TGO&NAY-SQD&NAY-DOY&CAN-NAY&URC-NAY&NAY-NZH&NAY-HRB&AVA-NAY&YNT-NAY&NAY-JIC&NAY-ACX&CAN-WDS&NAY-FUG&KMG-HZG&HZG-NAY&NAY-LNL&NAY-XFN&TEN-HAK&NAY-URC&TSN-PVG&SJW-KMG&NGB-NAY&NAY-JUZ&NAY-CAN&SZX-NAY&NAY-KMG&KMG-NAY&JUZ-NAY&CTU-NAY&LNL-NAY&HLD-YSQ&NAY-HSN&SWA-CSX&YSQ-NAY&YIE-NAY&CTU-DSN&BAV-NGB&DSN-CSX&CSX-SWA&HSN-NAY&CSX-DSN&SWA-DSN&NAY-YIE&INC-DAT";
		String[] ffff = flights.split("&");
		int i = 1;
		
		for(String fff : ffff){
			String[] ff = fff.split("-");
			String cf = ff[0];
			String dd = ff[1];
			
			Date today = new Date();  
	   
	        Calendar c = Calendar.getInstance(); 
	        c.setTime(today);
			
			for(int j = 1;j<30;j++){
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date r = c.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				
				String content = "INSERT INTO tb_zlpolicy VALUE ('"+i+"0"+j+"','KN','"+cf+"','"+dd+"','未获取','"+sdf.format(r)+"','','','','','','','','');";
				System.out.println(content);
				
				FileWriter fw = null;
				try {
					//如果文件存在，则追加内容；如果文件不存在，则创建文件
					File f=new File("D:\\1.txt");
					fw = new FileWriter(f, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				try {
					fw.flush();
					pw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}     
			}
			i++;
		}
		
	}

}