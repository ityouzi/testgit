package cn.tools.yy;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author L email:174493573@qq.com
 * @date 2018年4月15日 下午9:48:42
 * @version 1.0
 * 
 * 每11秒获取一次代理IP,放入数组
 * 检测代理IP连通性，去除telnet不通的代理IP
 * 连通的代理IP，每个创建一个线程执行注册
 * 校验getHtml()连续10次需要验证码，关闭线程;连续2次连接超时，关闭线程
 */
public class YYTest {
	
	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		while(1==1) {
			String[] ips = getIP();
			for(int i = 0;i<ips.length;i++) {
				String[] ipip = ips[i].split(":");
				String ip = ipip[0];
				int port = Integer.parseInt(ipip[1]);
				TelnetClient telnet = new TelnetClient();
				if(checkIP(telnet,ip,port)) {
					String password = getStringRandom(11);
					//开启线程
					YYRegRun run = new YYRegRun();
					run.setIp(ip);
					run.setPort(port);
					run.setPassword(password);
					run.start();
				}
			}
			System.out.println("等待10秒");
			Thread.sleep(11000);
		}
		
	}
	
	//获取代理IP
		public static String[] getIP(){
			String url = "http://s.zdaye.com/?api=201804181226151165&px=1";
			try {
				Document doc = Jsoup.connect(url).get();
				System.out.println(doc);
				String[] ips = doc.getElementsByTag("body").get(0).text().split(" ");
				return ips;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// 检测代理IP连通性
	    public static boolean checkIP(TelnetClient telnet,String ip, int port) {
	        try {
	        	telnet.setConnectTimeout(1000);
	            telnet.connect(ip, port);
	            telnet.disconnect(); 
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    //生成随机密码
	    public static String getStringRandom(int length) {  
	        String val = "";  
	        Random random = new Random();        
	        //length为几位密码 
	        for(int i = 0; i < length; i++) {          
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