package cn.myjava.jars.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.net.telnet.TelnetClient;

public class CmdUtils {
	
	public static void main(String[] args) {
		System.out.println(checkTelnet("119.97.180.51",8512));
	}
	
	/**
	 * 传入IP，端口检测连通性
	 * @param ip	检测的IP
	 * @param port	检测的端口
	 * @return boolean
	 */
    public static boolean checkTelnet(String ip, int port) {
    	TelnetClient telnet = new TelnetClient();
        try {
        	telnet.setConnectTimeout(1000);
            telnet.connect(ip, port);
            telnet.disconnect(); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * ADSL自动重拔
     */
    public static boolean resetADSL(){
    	try{ 
	        cutAdsl();  
	        Thread.sleep(1000);  
	        connAdsl();  
	        return true;
    	}catch(Exception e){
    		return false;
    	}
    }
    
    /**
     * 传入cmd命令执行并获取执行结果
     */
    public static String excuCmd(String strCmd){
    	try{
	    	Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);  
	        StringBuilder sbCmd = new StringBuilder();  
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
	        String line;  
	        while ((line = br.readLine()) != null) {  
	            sbCmd.append(line + "\n");  
	        }  
	        return sbCmd.toString(); 
    	}catch(Exception e){
    		return null;
    	}
    }
  
    /** 
     * 连接ADSL 
     */  
    public static boolean connAdsl() throws Exception {  
        System.out.println("正在建立连接.");  
        String adslCmd = "rasdial adsl 02701009464 031640";
        String tempCmd = excuCmd(adslCmd);
        // 判断是否连接成功  
        if (tempCmd.indexOf("已连接") > 0) {  
            System.out.println("已成功建立连接.");  
            return true;  
        } else {  
            System.err.println(tempCmd);  
            System.err.println("建立连接失败");  
            return false;  
        }  
    }  
  
    /** 
     * 断开ADSL 
     */
    public static boolean cutAdsl() throws Exception {  
        String cutAdsl = "rasdial adsl /disconnect";  
        String result = excuCmd(cutAdsl);  
         
        if (result.indexOf("没有连接")!=-1){  
            System.err.println("adsl连接不存在!");  
            return false;  
        } else {  
            System.out.println("连接已断开");  
            return true;  
        }  
    }  

}