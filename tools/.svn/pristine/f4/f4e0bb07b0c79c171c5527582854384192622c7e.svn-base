package cn.tools.fanyi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class FreeFanyi {
	
	public static void main(String[] args) throws Exception {
		getResult("are you a dog?");
	}
	
	public static String getResult (String q) throws Exception{
		
		if(q.equals("")||q == null || q.equals("-") || q.equals("null")){
			return q;
		}
		
		 //创建连接 
        URL url = new URL("http://translate.hotcn.top/translate/api"); 
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
        connection.setDoOutput(true); 
        connection.setDoInput(true); 
        connection.setRequestMethod("POST"); 
        connection.setUseCaches(false); 
        connection.setInstanceFollowRedirects(true); 
        connection.setRequestProperty("Content-Type","application/json"); 
        connection.connect(); 
        //POST请求 
        DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
        
        
        JSONObject param = new JSONObject(); 
        param.put("text", q); 
        
        out.write(param.toString().getBytes("UTF-8"));
        out.flush(); 
        out.close(); 
        //读取响应 
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
        String lines; 
        StringBuffer sb = new StringBuffer(""); 
        while ((lines = reader.readLine()) != null) { 
            lines = new String(lines.getBytes(), "utf-8"); 
            sb.append(lines); 
        } 
        reader.close(); 
        // 断开连接 
        connection.disconnect(); 
        
        JSONObject result = JSONObject.fromObject(sb.toString());
        
        String ret = result.getString("text");
        
        System.out.println(ret);
        
		return ret;
	}
	
}