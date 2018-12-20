package cn.myjava.jars.hkgs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class KNAPP {
	
	//获取KN数据
  	public static void main(String[] args) throws Exception {
  		String orgCode = "HLH";
  		String dstCode = "NAY";
  		String takeoffdate1 = "2018-08-06";
  		String[] ipinfo = {"183.129.207.73","17570"};
  		
  		String req = orgCode+"***"+dstCode+"***"+takeoffdate1;
  		System.out.println(req);
  		String result1 = null;
  		CloseableHttpClient http = HttpClients.createDefault();
  		Timer timer = null;
  		String r = null;
  		
//  			System.out.println(orgCode+"***"+dstCode+"***"+takeoffdate1);
	  		
	  		String url = "https://m.flycua.com/h5/pip/book/flightSearch.json";
			
			HttpPost post = new HttpPost(url);
			
			Map<String,String> heads = new HashMap<String,String>();
			heads.put("Content-Type", "application/json;charset=UTF-8");
			
			JSONObject params = new JSONObject();
			params.put("tripType", "OW");
			params.put("orgCode", orgCode);
			params.put("dstCode", dstCode);
			params.put("takeoffdate1", takeoffdate1);
			params.put("takeoffdate2", "");
			
			StringEntity entity = new StringEntity(JSONObject.fromObject(params).toString(),"utf-8");//解决中文乱码问题    
	        entity.setContentEncoding("UTF-8");    
	        entity.setContentType("application/json");    
	        post.setEntity(entity); 
	        
	        HttpHost proxy=new HttpHost(ipinfo[0], Integer.parseInt(ipinfo[1]));
		    RequestConfig requestConfig=RequestConfig.custom()
		    		.setSocketTimeout(5000).setConnectTimeout(5000)
		    		.setConnectionRequestTimeout(5000).setProxy(proxy).build();
		    post.setConfig(requestConfig);
		    
		 // 计时器
	        timer = new Timer();
	        ReleaseIdleConnTask timeTask = new ReleaseIdleConnTask(http);
	        timer.schedule(timeTask, 5000 * 2);
	        
	        HttpResponse result = http.execute(post); 
	        
	        System.out.println(result);
	        
	        result1 = EntityUtils.toString(result.getEntity());
	        
		    System.out.println(result1);
	        
		    timer.cancel();
		    http.close();
		    
  	}
  	
  	static class ReleaseIdleConnTask extends TimerTask
    {
        public ReleaseIdleConnTask(CloseableHttpClient client)
        {
            httpClient = client;
        }

        @Override
        public void run() {
            try {
                System.err.println("********************HttpClinet Connect Cost Too Much Time.Killing It!");
                httpClient.close();
            } catch (IOException e) {
                System.out.println("*********************Kill Failed!");
            }
        }

        private CloseableHttpClient httpClient = null;
    }
    

}