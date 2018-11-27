package cn.tools.yy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import net.sf.json.JSONObject;

@SuppressWarnings("deprecation")
public class YYRegRun extends Thread {
	
	static String ch = "cbyy345";
	private String ip;
	private int port;
	private String password;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		CloseableHttpClient http=HttpClients.createDefault(); // 创建httpClient实例
		System.out.println(getHtml(http, "60.184.4.167", 57950));
		checkPhone(http, "18671436670", "60.184.4.167", 57950);
		checkPhone(http, "18671436670", "60.184.4.167", 57950);
		checkPhone(http, "18671436670", "60.184.4.167", 57950);
		checkPhone(http, "18671436670", "60.184.4.167", 57950);
		checkPhone(http, "18671436670", "60.184.4.167", 57950);
	}
	
	@Override
	public void run() {
		while(1==1){
			System.out.println("进来了");
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		int m = 0;	//连接超时计数
//		A:for(int i = 0;i<20;i++) {
//			System.err.println("第"+i+"次获取手机号码");
//			String phone = getPhone();
//			if(phone!=null){
//				System.out.println(phone);
//				CloseableHttpClient http=HttpClients.createDefault(); // 创建httpClient实例
//				B:for(int j = 0;j<10;j++) {
//					try {
//						if(getHtml(http, ip, port)){
//							if(checkPhone(http, phone, ip, port)){
//								if(check(http, phone, password, ip, port)){
//									if(send(http, phone, ip, port)){
//										String code = getCode(phone);
//										if(code != null) {
//											reg(http, phone, password, code, ip, port);
//											removePhone(phone);
//											break B;
//										}
//									}
//								}
//							}else {
//								j++;
//								removePhone(phone);
//								break B;
//							}
//						}
//						if(j==9) {
//							removePhone(phone);
//							break B;
//						}
//						j++;
//					}catch (Exception e) {
//						e.printStackTrace();
//						m++;
//						if(m == 2) {
//							removePhone(phone);
//							break A;
//						}
//						continue;
//					}
//				}
//			}
//		}
	}
	
	public void regService(String ip,int port) throws InterruptedException {
		
	}
	
	//获取手机号码
	public static String getPhone(){
		String url = "http://59.107.26.77:8888/getmobile.php?ch="+ch;
		try{
			Document doc = Jsoup.connect(url).timeout(10000).get();
//			System.out.println(doc);
			String body = doc.getElementsByTag("body").get(0).text();
			System.out.println(body);
			if("1".equals(body.subSequence(0, 1))){
				String phone = body.substring(2, 13);
				return phone;
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
	
	//移除号码
	public static boolean removePhone(String phone){
		String url = "http://59.107.26.77:8888/close.php?ch="+ch+"&mobile="+phone;
		while(1==1) {
			try{
				Document doc = Jsoup.connect(url).timeout(10000).get();
	//			System.out.println(doc);
				String body = doc.getElementsByTag("body").get(0).text();
				System.out.println(body);
				if("1".equals(body.subSequence(0, 1))){
					return true;
				}
				continue;
			}catch(Exception e){
				continue;
			}
		}
	}
	
	//获取验证码
	public static String getCode(String phone) throws InterruptedException{
		String url = "http://59.107.26.77:8888/getcode.php?ch="+ch+"&mobile="+phone;
		System.out.println(url);
		for(int i = 0;i<30;i++){
			try{
				Document doc = Jsoup.connect(url).timeout(10000).get();
				String body = doc.getElementsByTag("body").get(0).text();
				System.out.println(body);
				if("1".equals(body.subSequence(0, 1))){
					String code = body.substring(2, body.length());
					code = code.substring(code.indexOf("%81%EF%BC%9A")+12, code.indexOf("%EF%BC%8C%E7%94"));
					System.out.println(code);
					return code;
				}
				Thread.sleep(10000);
				continue;
			}catch(Exception e){
				e.printStackTrace();
				Thread.sleep(10000);
				continue;
			}
		}
		return null;
	}
	
	//获取页面，判断是否需要输入验证码
	public static boolean getHtml(CloseableHttpClient http,String ip,int port){
		String url = "https://zc.yy.com/reg/udb/reg4udb.do?mode=udb&type=Mobile&appid=1&foreignMb=1&action=3&busiurl=https%3A%2F%2Faq.yy.com&fromadv=lgn&reqDomainList=";
		HttpGet get = new HttpGet(url);
		HttpHost proxy=new HttpHost(ip, port);
	    RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
	    get.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = http.execute(get);
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity,"UTF-8");
//			System.out.println(html);
			Document doc = Jsoup.parse(html);
			Element e = doc.getElementById("caVCodeMain");
			if(e == null){
				return true;
			}
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//校验号码是否可用
	public static boolean checkPhone(CloseableHttpClient http,String phone,String ip,int port){
		String url = "https://zc.yy.com/reg/pc/chk.do";
		HttpPost post = new HttpPost(url);
		HttpHost proxy=new HttpHost(ip, port);
	    RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
	    post.setConfig(requestConfig);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("appid","1"));
        parameters.add(new BasicNameValuePair("mobilefix",""));
        parameters.add(new BasicNameValuePair("passport",phone));
        try {
			post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
			CloseableHttpResponse response = http.execute(post);
			HttpEntity entity = response.getEntity();
			System.out.println(response.getStatusLine().getStatusCode());
			String send = EntityUtils.toString(entity,"UTF-8");
			System.out.println(send);
			if(send.contains("可以注册")){
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//YY检查
	public static boolean check(CloseableHttpClient http,String phone,String password,String ip,int port){
		String url = "https://zc.yy.com/p/chkRegInfo4EM.do";
		HttpPost post = new HttpPost(url);
		HttpHost proxy=new HttpHost(ip, port);
	    RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
	    post.setConfig(requestConfig);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("appid","1"));
        parameters.add(new BasicNameValuePair("mobilefix",""));
        parameters.add(new BasicNameValuePair("passport",phone));
        parameters.add(new BasicNameValuePair("password",password));
        try {
			post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
			CloseableHttpResponse response = http.execute(post);
			HttpEntity entity = response.getEntity();
			System.out.println(response.getStatusLine().getStatusCode());
			String send = EntityUtils.toString(entity,"UTF-8");
			System.out.println(send);
			if(send.contains("检查成功")){
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//发送短信验证码
	public static boolean send(CloseableHttpClient http,String phone,String ip,int port){
		String url = "https://zc.yy.com/reg/aq/send.do";
		HttpPost post = new HttpPost(url);
		HttpHost proxy=new HttpHost(ip, port);
	    RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
	    post.setConfig(requestConfig);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("appid","1"));
        parameters.add(new BasicNameValuePair("mobilefix",""));
        parameters.add(new BasicNameValuePair("passport",phone));
        parameters.add(new BasicNameValuePair("token",""));
        
        try {
        	post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
        	CloseableHttpResponse response = http.execute(post);
    		HttpEntity entity = response.getEntity();
    		System.out.println(response.getStatusLine().getStatusCode());
    		String send = EntityUtils.toString(entity,"UTF-8");
    		System.out.println(send);
			if(send.contains("发送成功")){
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//注册
	public static void reg(CloseableHttpClient http,String phone,String password,String code,String ip,int port){
		String url = "https://zc.yy.com/regCore/aq4ME/f95ad1.do?__ts=1523766370341";
		HttpPost post = new HttpPost(url);
		HttpHost proxy=new HttpHost(ip, port);
	    RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
	    post.setConfig(requestConfig);
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        parameters.add(new BasicNameValuePair("appid","1"));
        parameters.add(new BasicNameValuePair("acct",phone));
        parameters.add(new BasicNameValuePair("password",password));
        parameters.add(new BasicNameValuePair("vtcode",code));
        parameters.add(new BasicNameValuePair("isNeedVcode","0"));
        parameters.add(new BasicNameValuePair("token",""));
        try {
        	post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
        	CloseableHttpResponse response = http.execute(post);
    		HttpEntity entity = response.getEntity();
    		System.out.println(response.getStatusLine().getStatusCode());
    		String send = EntityUtils.toString(entity,"UTF-8");
			System.out.println(send);
			JSONObject json = JSONObject.fromObject(send);
			if("0".equals(json.getString("code"))) {
				inputTxt(phone+":"+password);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//注册成功的保存到txt
	public static boolean inputTxt(String content) {
		FileOutputStream fos = null;  
        try {  
            //true不覆盖已有内容  
            fos = new FileOutputStream("C:/YY.txt", true);    
            //写入  
            fos.write(content.getBytes());  
            // 写入一个换行  
            fos.write("\r\n".getBytes());  
            return true;
        } catch (IOException e) {  
            e.printStackTrace();
            return false;
        }finally{  
            if(fos != null){  
                try {  
                    fos.flush();  
                    fos.close();   
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
	
}