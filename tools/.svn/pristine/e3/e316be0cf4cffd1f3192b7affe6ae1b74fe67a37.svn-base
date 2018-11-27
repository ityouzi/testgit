package cn.tools.yyReg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

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

public class YYRun2 extends Thread {
	
	
	
	
	static String ch = Tools.getProperties("config.properties","ch");
	static String ipport = Tools.getProperties("config.properties","ip");
	
	private static String ip;
	private static int port;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	
	
	
	
public static void main(String[] args) {
		
		A:while(1==1){
			int m = 0;
			String phone = getPhone();
			if(phone!=null){
				System.out.println(phone);
				CloseableHttpClient http=HttpClients.createDefault(); // 创建httpClient实例
				B:for(int j = 0;j<10;j++) {
					try {
						if(getHtml(http, ip, port)){
							if(checkPhone(http, phone, ip, port)){
								if(check(http, phone, getStringRandom(), ip, port)){
									if(send(http, phone, ip, port)){
										String code = getCode(phone);
										if(code != null) {
											reg(http, phone, getStringRandom(), code, ip, port);
											removePhone(phone);
											break A;
										}else{
											removePhone(phone);
											continue A;
										}
									}
								}
								j++;
							}else {
								removePhone(phone);
								continue A;
							}
						}
						if(j==9) {
							removePhone(phone);
							break B;
						}
						j++;
					}catch (Exception e) {
						e.printStackTrace();
						m++;
						if(m == 2) {
							removePhone(phone);
							break A;
						}
						continue;
					}
				}
			}
			//如果null进入循环
		}
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void run(){
		A:while(1==1){
			int m = 0;
			String phone = getPhone();
			if(phone!=null){
				System.out.println(phone);
				CloseableHttpClient http=HttpClients.createDefault(); // 创建httpClient实例
				B:for(int j = 0;j<10;j++) {
					try {
						if(getHtml(http, ip, port)){
							if(checkPhone(http, phone, ip, port)){
								if(check(http, phone, getStringRandom(), ip, port)){
									if(send(http, phone, ip, port)){
										String code = getCode(phone);
										if(code != null) {
											reg(http, phone, getStringRandom(), code, ip, port);
											removePhone(phone);
											break A;
										}else{
											removePhone(phone);
											continue A;
										}
									}
								}
								j++;
							}else {
								removePhone(phone);
								continue A;
							}
						}
						if(j==9) {
							removePhone(phone);
							break B;
						}
						j++;
					}catch (Exception e) {
						e.printStackTrace();
						m++;
						if(m == 2) {
							removePhone(phone);
							break A;
						}
						continue;
					}
				}
			}
			//如果null进入循环
		}
	}
	
	//获取手机号码
	public static String getPhone(){
		String url = "http://"+ipport+"/getmobile.php?ch="+ch;
		try{
			Document doc = Jsoup.connect(url).timeout(10000).get();
			System.out.println(doc);
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
		String url = "http://"+ipport+"/close.php?ch="+ch+"&mobile="+phone;
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
		String url = "http://"+ipport+"/getcode.php?ch="+ch+"&mobile="+phone;
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
//					System.out.println(html);
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
	
	
	
	
	
	//生成随机密码
    public static String getStringRandom() {  
        String val = "";  
        Random random = new Random();        
        //length为几位密码 
        for(int i = 0; i < Integer.parseInt(Tools.getProperties("config.properties","b")); i++) {          
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
    
}