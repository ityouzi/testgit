package cn.tools.telnet;

import org.apache.commons.net.telnet.TelnetClient;  

//检测端口是否通
public class NetTelnet {
	
	public static void main(String[] args) {  
		TelnetClient telnet = new TelnetClient();
		String ip = "119.97.180.51";
		int port = 8511;
		System.out.println(check(telnet,ip,port));
    }  
  
    // 检测
    public static boolean check(TelnetClient telnet,String ip, int port) {
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
}  