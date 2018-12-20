package cn.myjava.jars.test;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

import cn.myjava.jars.http.JsoupUtils;

public class EasyEcpTest {
	
	public static void main(String[] args) {
		String host = "http://www.easyepc123.com";
        String path = "/api/";
        String apiCode = "111001";
		
		Map<String, String> head = new HashMap<String, String>();
        head.put("username", "18671436670");
        head.put("password", "5047272a405e3fc96a632d39465d6748");
        //请求参数
        Map<String, String> body = new HashMap<String, String>();
        body.put("vinCode", "LVVDB12A6DD201624");
//        
//        Document doc = JsoupUtils.getDocPost("http://www.easyepc123.com/api/111001", head, body);
//        System.out.println(doc);
		
		
        try {
            /**
            * 重要提示如下:
            * HttpUtil请从
            * https://github.com/topzy/demo/blob/master/HttpUtil.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/topzy/demo/blob/master/pom.xml
            */
            String returnData = HttpUtil.getData(host + path + apiCode, head, body);
            System.out.println(returnData);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
		
		
		
		
		
	}

}