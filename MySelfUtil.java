package com.qppi.crud.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.log4j.lf5.util.Resource;
import org.apache.taglibs.standard.extra.spath.ASCII_CharStream;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.corba.se.spi.orbutil.fsm.State;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MySelfUtil {

	//访问接口
	public static String doHttpGet(String url) {
		// 需要访问的接口路径
		//String url = "http://127.0.0.1:8085/sercar-app/wx/getOrderId?code=" + code;
		// 配置请求信息（请求时间）
		RequestConfig rc = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
		// 获取使用DefaultHttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 返回结果
		String result = null;
		try {
			if (url != null) {
				// 创建HttpGet对象，将URL通过构造方法传入HttpGet对象
				HttpGet httpget = new HttpGet(url);
				// 将配置好请求信息附加到http请求中
				httpget.setConfig(rc);
				// 执行DefaultHttpClient对象的execute方法发送GET请求，通过CloseableHttpResponse接口的实例，可以获取服务器返回的信息
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					// 得到返回对象
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// 获取返回结果
						result = EntityUtils.toString(entity);
					}
				} finally {
					// 关闭到客户端的连接
					response.close();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭http请求
				httpclient.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	// 图片转化成base64字符串
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
	  
	
	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr, String imgFilePath) throws Exception { 
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
 
		// Base64解码,对字节数组字符串进行Base64解码并生成图片
		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		// 生成jpeg图片
		// String imgFilePath = "c://temp_kjbl_001_ab_010.jpg";//新生成的图片
		 File dest = new File(imgFilePath);
		 if (!dest.getParentFile().exists()) {
			 dest.getParentFile().mkdirs();// 新建文件夹
		 }
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(b);
		out.flush();
		out.close();
		return true;
	}
	
	//读取文件中的内容
	/**
	 * 获取工程目录下Properties文件key的值
	 * @param filePath
	 * @param key
	 * @return String
	 */
	public static String getProperties(String filePath,String key){
		 Properties prop = new Properties();
         String value = null;
         try {
             // 通过输入缓冲流进行读取配置文件
             InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
             // 加载输入流
        	 
             prop.load(InputStream);
             // 根据关键字获取value值
             value = prop.getProperty(key);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
         return value;
	}
	
	//比较两集合的相同
	public static boolean bijiao(List<String> a, List<String> b){
		if (a.size() != b.size()){
			return false;
		}

		for (Object object : a) {
			if (!b.contains(object)){
				return false;
			}
		}
		return true;
	}
	
	//集合去重
	public static List<String> removeDuplicate(List<String> list)  {       
		for(int i = 0; i < list.size()-1; i++){       
			for(int j = list.size()-1; j > i; j--){       
				if(list.get(j).equals(list.get(i))){       
					list.remove(j);       
	            }        
			}        
		}        
	  	return list;       
	}
	
	//添加日志信息
	public static void addRiZhi(String createBy,String Xiangmuming,String requestcontent, String responsecontent, String status, String type, String url){
		Map<String, String> parems = new HashMap<String, String>();
		parems.put("createBy", createBy);
		parems.put("Xiangmuming", Xiangmuming);
		parems.put("requestcontent", requestcontent);
		parems.put("responsecontent", responsecontent);
		parems.put("status", status);
		parems.put("type", type);
		//新增日志
		Connection con = Jsoup.connect(url);
		con.data(parems);
		try{
			Document doc = con.ignoreContentType(true).post();
			JSONObject jsons = JSONObject.fromObject(doc.getElementsByTag("body").get(0).text());
			int code = (int) jsons.get("code");
			if(code==100){
				System.out.println("添加成功");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("添加失败");
		}
		
	}
}
