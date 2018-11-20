package com.nn.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.util.StringUtil;
import com.google.common.io.Resources;
import com.nn.bean.Activity;
import com.nn.bean.User;
import com.nn.service.ActivityService;
import com.nn.service.ReplyService;
import com.nn.utils.Msg;
import com.nn.utils.MyTools;
import com.nn.utils.RandemUtils;

@Controller
@RequestMapping("activity")
public class ActivityController extends BaseController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ReplyService replyService;
	
//	static String filepath = "http://192.168.0.103:8080/file-server/zupu/";
//	static String saveFilePath = "E:/file-server/zupu/";
	
//	final String basePath = "C:/file-server/zupu/active/";
//	final String URL = "http://192.168.0.208:8088/file-server/zupu/active/";

	// 新增
	@ResponseBody
	@RequestMapping("add")
	public Msg add(Activity activity,MultipartHttpServletRequest req,HttpServletRequest request){	
		User user = getCurrentUser(request);
		StringBuilder str = new StringBuilder();
		MultiValueMap<String,MultipartFile> map = req.getMultiFileMap();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "plain",  Charset.forName("UTF-8")));
		List<MultipartFile> list = map.get("mFile");
		
		if(list != null){
			 for (MultipartFile mFile : list) {  
				 String rdInt = MyTools.getDateR() + ".jpg";
				 try {
					 byte[] bytes = mFile.getBytes();//获取字节数组            
					 String filePath= getProperties("my.properties", "imagepath") +  rdInt; 
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
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			 }
		}
		activity.setPhoto(str.toString());
		if(activityService.add(activity,user)){
			return Msg.success().add("result", "添加成功");
		}else{
			return Msg.fail().add("result", "添加失败");
		}
	}

	// 活动详情
	@ResponseBody
	@RequestMapping("get")
	public Msg get(HttpServletRequest request) {
		String activityId = request.getParameter("activityId"); // 获取id
		Activity activity = activityService.get(activityId); 	// 查询活动
		String photo = activity.getPhoto();
		if(photo!=null&&!"".equals(photo)){
			String[] split = activity.getPhoto().split("[+]");
			List<String> list = new ArrayList<String>();
			for (String filname : split) {
				String urlPath = getProperties("my.properties", "urlpath")+filname;
				list.add(urlPath);
			}
			activity.setPhotos(list);
			return Msg.success().add("result", activity);
		}else{
			return Msg.success().add("result", activity);
		}
//		String[] split = activity.getPhoto().split("[+]");
//		if(!split[0].equals("")){ 								//下标判断
//			List<String> list = new ArrayList<String>();
//			for (String filname : split) {
//				String urlPath = URL+filname;
//				list.add(urlPath);
//			}
//			activity.setPhotos(list);
//			return Msg.success().add("result", activity);
//		}else{
//			return Msg.success().add("result", activity);
//		}
	}

	// 列表
	@ResponseBody
	@RequestMapping("list")
	public Msg list(HttpServletRequest request) {
		User user = getCurrentUser(request);
		if(user != null){
			return Msg.success().add("result", activityService.list(user));
		}else{
			return Msg.fail().add("result", "token过期");
		}
	}
	
	//查看我的活动
		@ResponseBody
		@RequestMapping("list2")
		public Msg list2(HttpServletRequest request){
			User user = getCurrentUser(request);
			if(user!=null){
				return Msg.success().add("result", activityService.list2(user));
			}else{
				return Msg.fail().add("result", "token过期");
			}
		}
		
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
	             // 加载输入流
 	             prop.load(asStream(filePath));
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
		
		public static void main(String[] args) {
			System.err.println(getProperties("my.properties", "filepath"));
			System.err.println(getProperties("my.properties", "urlpath"));
		}
}
