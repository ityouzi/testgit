package cn.myjava.jars.hkgs.yqf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;

public class ShowTicketFare2 {
 
	static String processUrl = "http://yiqifei.com/AjaxData/Handler/ProcessUrl.ashx?t=1_CAN_SYD_2018-09-04_2018-09-09&airline=&cabin=Y&dccode=ICBCCA&showType=1&CompanyCode=";
    
	static String searchFSInfo = "http://yiqifei.com/AjaxData/Handler/SearchFSInfo.ashx";
    
	static String flightListUrl = "http://yiqifei.com/TemplatePage/YqfUIV1/AjaxData/Pages/GetShowSelectFlights.aspx?id=";
    
	static String selectFlightsUrl = "http://yiqifei.com/TemplatePage/YqfUIV1/AjaxData/Pages/GetShowSelectFlights.aspx?id=";
    
	static int searchCount = 1;
	
    public static void main(String[] args) {
    	
        HttpClient httpClient = new HttpClient();
//      httpClient.getHostConfiguration().setProxy("localhost", 8888);  
        try {
        	String cookies = getCookie(httpClient, processUrl);
            
        	String responseText1 = getResponText1(httpClient, searchFSInfo+"?_="+new Date().getTime(), cookies);
        	Map result1 = (HashMap)JSON.parseObject(responseText1,HashMap.class);
        	System.out.println(result1);
        	
        	Object RspMsg = result1.get("RspMsg");
        	if(null != RspMsg){
        		String Jobid = RspMsg.toString();
        		
        		getFlightJobInfo(httpClient,cookies, Jobid);
        		
            	if(searchCount > 1){
            		String resp3 = getResponText3(httpClient, flightListUrl+"&airline=CA&_="+new Date().getTime(), cookies);
            		System.out.println(resp3);
            		
            		List<String> list = match(resp3, "a", "data-id");

            		for(String id:list){
            			if(id.length()>0){
                			String resp4 = getResponText3(httpClient, selectFlightsUrl+id+"&_="+new Date().getTime(), cookies);
//                    		System.out.println(resp4);
//                    		System.err.println("***************************************");
                		}
            		}
            	}
        	}
        	
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void getFlightJobInfo(HttpClient httpClient,String tmpcookies, String Jobid) throws HttpException,IOException, InterruptedException {
		String responseText2 = getResponText1(httpClient, searchFSInfo+"?Jobid="+Jobid+"&_="+new Date().getTime()+"&searchCount="+searchCount, tmpcookies.toString());
		Map result2 = (HashMap)JSON.parseObject(responseText2,HashMap.class);
//		System.out.println(result2);
		
		if(result2.get("Result").toString().equals("false")){
			Thread.sleep(2000);
			searchCount++;
			getFlightJobInfo(httpClient, tmpcookies, Jobid);
		}
	}
    
    public static String getCookie(HttpClient httpClient,String url) throws HttpException, IOException{
    	PostMethod postMethod = new PostMethod(url);

        postMethod.setRequestHeader("Accept", "*/*");
        postMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
		postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		postMethod.setRequestHeader("Cache-Control", "no-cache");
		postMethod.setRequestHeader("Connection", "keep-alive");
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		postMethod.setRequestHeader("Cookie", "lastinvite="+new Date().getTime());
		postMethod.setRequestHeader("Host", "yiqifei.com");
		postMethod.setRequestHeader("Origin", "http://yiqifei.com");
		postMethod.setRequestHeader("Pragma", "no-cache");
		postMethod.setRequestHeader("Proxy-Connection", "keep-alive");
		postMethod.setRequestHeader("Referer", "http://yiqifei.com/ShowTicketFare.aspx");
		postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
		postMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

        httpClient.executeMethod(postMethod);
        
        Cookie[] cookies = httpClient.getState().getCookies();

//        System.out.println("================================");
//        System.out.println("url = "+url);
        postMethod.releaseConnection();
        
        StringBuffer tmpcookies = new StringBuffer();
        for (Cookie c : cookies) {
    		tmpcookies.append(c.toString() + "; ");
        }
        return tmpcookies.toString();
    }
    
    public static String getResponText(HttpClient httpClient,String url,String reqCookie) throws HttpException, IOException{
//    	System.out.println("================================");
//        System.out.println("url = "+url);
    	GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("cookie", reqCookie);
		
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        httpClient.executeMethod(getMethod);
        
        String text = getMethod.getResponseBodyAsString();
//        System.out.println(text);
        getMethod.releaseConnection();
        return text;
    }
    
    public static String getResponText1(HttpClient httpClient,String url,String reqCookie) throws HttpException, IOException{
//    	System.out.println("================================");
//        System.out.println("url = "+url);
    	GetMethod getMethod = new GetMethod(url);
    	
    	getMethod.setRequestHeader("Accept", "*/*");
    	getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
    	getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
    	getMethod.setRequestHeader("Cache-Control", "no-cache");
    	getMethod.setRequestHeader("Cookie", reqCookie);
    	getMethod.setRequestHeader("Host", "yiqifei.com");
    	getMethod.setRequestHeader("Pragma", "no-cache");
    	getMethod.setRequestHeader("Proxy-Connection", "keep-alive");
    	getMethod.setRequestHeader("Referer", "http://yiqifei.com/ShowTicketFare.aspx");
    	getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
    	getMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    	
    	httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        httpClient.executeMethod(getMethod);
        
        String text = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return text;
    }
    
    public static String getResponText3(HttpClient httpClient,String url,String reqCookie) throws HttpException, IOException{
//    	System.out.println("================================");
//        System.out.println("url = "+url);
    	GetMethod checkInviteMethod = new GetMethod(url);
    	
    	checkInviteMethod.setRequestHeader("Accept", "*/*");
    	checkInviteMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
    	checkInviteMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
    	checkInviteMethod.setRequestHeader("Cache-Control", "no-cache");
    	checkInviteMethod.setRequestHeader("Cookie", reqCookie);
    	checkInviteMethod.setRequestHeader("Host", "yiqifei.com");
    	checkInviteMethod.setRequestHeader("Pragma", "no-cache");
    	checkInviteMethod.setRequestHeader("Proxy-Connection", "keep-alive");
    	checkInviteMethod.setRequestHeader("Referer", "http://yiqifei.com/ShowTicketFare.aspx");
    	checkInviteMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
    	checkInviteMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    	
    	httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        httpClient.executeMethod(checkInviteMethod);
        
        byte[] text = checkInviteMethod.getResponseBody();
        checkInviteMethod.releaseConnection();
        if(null != text){
        	byte[] body = uncompress(text);
        	return new String(body);
        }
        return null;
    }
    

    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
    
    /**
     * 获取指定HTML标签的指定属性的值
     * @param source 要匹配的源文本
     * @param element 标签名称
     * @param attr 标签的属性名称
     * @return 属性值列表
     */
    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }
}