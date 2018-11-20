package com.nn.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


















import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Resources;
import com.nn.bean.Said;
import com.nn.bean.User;
import com.nn.service.CommentService;
import com.nn.service.SaidService;
import com.nn.utils.Msg;
import com.nn.utils.MyTools;
import com.nn.utils.RandemUtils;

@Controller
@RequestMapping("said")
public class SaidController extends BaseController {
	
//	static String filepath = "http://192.168.0.103:8080/file-server/zupu/";
//	static String saveFilePath = "";
	
//	final String basePath = "C:/file-server/zupu/";
//	final String URL = "http://192.168.0.208:8088/file-server/zupu/";
	
	@Autowired
	private SaidService saidService;
	@Autowired
	private CommentService commentService;
		
		//通过经纬度获取地址，对象(第一次定位)
		public  String getlongitudeLatitude(String longitudeLatitude){
			System.err.println(longitudeLatitude);
			JSONObject j= JSONObject.fromObject(longitudeLatitude);
			System.out.println(j);
			String longitude = j.getString("lat");
			String Latitude  = j.getString("lng");
			System.err.println(longitude);
			System.err.println(Latitude);
			
			String url="http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location="+longitude+","+Latitude+"&output=json&pois=1&ak=f9jNGIKTZxMFhpjykse3wsGmpx6p5HHY&qq-pf-to=pcqq.group";
//			System.out.println(url);
			try {
				Document doc = Jsoup.connect(url).ignoreContentType(true).get();	
				String data = doc.getElementsByTag("body").get(0).text();
				data = data.substring(29, data.length()-1);//截取返回结果(不需要的去掉)
				JSONObject json = JSONObject.fromObject(data);//将返回结果转化为json对象
				String result = json.getString("result");//获取json结果中的result数据，并转化为字符串
				JSONObject jsonResult = JSONObject.fromObject(result);
				System.err.println(jsonResult);
				return jsonResult.getString("formatted_address");
			} catch (Exception e) {
				// TODO: handle exception
			}		
			return null;
			
		}
		//通过经纬度获取地址，列表
		public  List<String> getLongitudeLatitude(String longitudeLatitude) throws IOException {
			System.err.println(longitudeLatitude);
			JSONObject j= JSONObject.fromObject(longitudeLatitude);
			System.err.println(j);
			String longitude = j.getString("lat");
			String Latitude  = j.getString("lng");
			System.err.println(longitude);
			System.err.println(Latitude);
			
			String url="http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location="+longitude+","+Latitude+"&output=json&pois=1&ak=f9jNGIKTZxMFhpjykse3wsGmpx6p5HHY&qq-pf-to=pcqq.group";
			System.err.println(url);
			Document doc = Jsoup.connect(url).ignoreContentType(true).get();
			String data = doc.getElementsByTag("body").get(0).text();
			data = data.substring(29, data.length()-1);//截取返回结果
			JSONObject json = JSONObject.fromObject(data);//将返回结果转化为json对象	
			String result = json.getString("result");//获取json结果中的result数据，并转化为字符串
			JSONObject jsonResult = JSONObject.fromObject(result);
			String pois = jsonResult.getString("pois");
			JSONArray poisArray = JSONArray.fromObject(pois);
			List<String> listAddress = new ArrayList<String>();
			for(int i=0;i<poisArray.size();i++){
				System.out.println(poisArray.getJSONObject(i).getString("addr"));//打印出地址
				listAddress.add(poisArray.getJSONObject(i).getString("addr"));
			}
			return listAddress;
		}
		
		//获取地址1
		@RequestMapping("addr1")
		@ResponseBody
		public Msg addr1(String longitudeLatitude) throws IOException{
			return Msg.success().add("result", getlongitudeLatitude(longitudeLatitude));			
		}		
		
		//获取地址2
		@RequestMapping("addr2")
		@ResponseBody
		public Msg addr2(String longitudeLatitude) throws IOException{
			return Msg.success().add("result", getLongitudeLatitude(longitudeLatitude));			
		}		
		
		//添加
		@RequestMapping("add")
		@ResponseBody
		public Msg Add(Said said,HttpServletRequest request,MultipartHttpServletRequest req,String longitudeLatitude,String addr) throws IOException{
			said.setSaidId(MyTools.getDateR());
			User user = getCurrentUser(request);			
			StringBuilder str = new StringBuilder();
			String saidContent = request.getParameter("saidContent");

			MultiValueMap<String,MultipartFile> map = req.getMultiFileMap();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("text", "plain",  Charset.forName("UTF-8")));
			List<MultipartFile> list = map.get("mFile");
		   		
			if(list != null){
				 for (MultipartFile mFile : list) {  
					 String rdInt = MyTools.getDateR() + ".jpg";
					 try {
						 byte[] bytes = mFile.getBytes();//获取字节数组            
						 String filePath= getProperties("/my.properties", "imagepath") +  rdInt ; 
						 File dest = new File(filePath);
						 // 检测是否存在目录
						 if (!dest.getParentFile().exists()) {
							 dest.getParentFile().mkdirs();// 新建文件夹
						 }
						 FileOutputStream fos= new FileOutputStream(dest); //写出到文件            
						 fos.write(bytes);           
						 fos.flush();           
						 fos.close();           
						 str.append("image/"+rdInt).append("+");  
						 System.out.println(str);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				 }
				 said.setPictureUrl(str.toString());
			}
			said.setSaidContent(saidContent);		
			if(saidService.addSaid(said,user,longitudeLatitude,addr)){
				return Msg.success().add("result", "success");
			}else{
				return Msg.fail().add("result", "faulier");
			}
		}
		
		//修改
		@RequestMapping("update")
		@ResponseBody
		public Msg Update(Said said){
			if(saidService.updateSaid(said)){
				return Msg.success().add("result", "success");
			}else{
				return Msg.fail().add("result", "faulier");
			}
		}
		//列表
		@RequestMapping("list")
		@ResponseBody
		public Msg listSaid(Said said,HttpServletRequest request){
			User user = getCurrentUser(request);	
			return Msg.success().add("result", saidService.list(said,user, getProperties("my.properties", "urlpath")));
		}
		//查询
		@RequestMapping("get")
		@ResponseBody
		public Msg getSaid(String saidId){
			return Msg.success().add("result", saidService.getSaid(saidId));
		}	
		
		/**
		 * 获取工程目录下Properties文件key的值
		 * @param filePath
		 * @param key
		 * @return String
		 */
		public String getProperties(String filePath,String key){
			 Properties prop = new Properties();
	         String value = null;
	         try {
	             // 通过输入缓冲流进行读取配置文件
//	             InputStream InputStream = new BufferedInputStream(new FileInputStream(new File("classpath://"+filePath)));
	             // 加载输入流
 	             prop.load(asStream(filePath));			//配置文件
	             // 根据关键字获取value值
	             value = prop.getProperty(key);
	         } catch (Exception e) {
	             e.printStackTrace();
	             return null;
	         }
	         return value;
		}
		
		public static InputStream asStream(String resourceName) throws IOException {
			return Resources.getResource(resourceName).openStream();
		}
		
//		public static void main(String[] args) {
//			String r = getProperties("my.properties", "imagepath");
//			System.out.println(r);
//		}
		
}