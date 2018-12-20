package cn.myjava.jars.hkgs.yqf;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class YQFHK {
	static String url1 = "http://www.yiqifei.com/AjaxData/Handler/ProcessUrl.ashx?t=1_SYD_CAN_2018-09-02_2018-09-07&airline=&cabin=Y&dccode=ICBCCA&showType=1&CompanyCode=";
	static String url2 = "http://www.yiqifei.com/ShowTicketFare.aspx";
	static String url3 = "http://www.yiqifei.com/TemplatePage/YqfUIV1/AjaxData/Pages/GetShowMultiFlightList.aspx?SegRef=1&sort=TotalPrice%7CDESC&DepartureDate=&ArrivalDate=&Airline=&TurnaroundCount=&pageindex=0&isShowTejia=false&_=1534130121524";
	
	public static void main(String[] args) throws IOException {
		CloseableHttpClient http = HttpClients.createDefault();
        HttpGet get1 = new HttpGet(url1);
        HttpResponse result = http.execute(get1);
        System.out.println(result);
        
        String result1 = EntityUtils.toString(result.getEntity());
        
	    System.out.println(result1);
	    
	    
	    
	    
	    HttpGet get2 = new HttpGet(url2);
        HttpResponse result2 = http.execute(get2);
        System.out.println(result2);
        
        Header[] hs = result2.getAllHeaders();
	      for(Header h : hs){
	    	  System.out.println(h);
	      }
        
        String result22 = EntityUtils.toString(result2.getEntity());
        
//	    System.err.println(result22);
	    
	    
	    
	    
	    HttpGet get3 = new HttpGet(url3);
	    
        HttpResponse result3 = http.execute(get3);
        System.out.println(result3);
        
        Header[] hs3 = result3.getAllHeaders();
	      for(Header h : hs3){
	    	  System.out.println(h);
	      }
        
        String result33 = EntityUtils.toString(result3.getEntity());
        
	    System.out.println(result33+"%%%%%%%%%%%%");
	    
	    
	    Document doc = Jsoup.parse("<p>票价：<span class=\"price\">¥<em>20031</em></span></p>");
	    String result1111 = doc.getElementsByClass("price").get(0).getElementsByTag("em").get(0).text();
	    System.err.println(result1111);
	    
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    public static  final String returnCookies() {

        try {
            Connection conn = Jsoup.connect(url2);
            conn.method(Method.GET);
            conn.followRedirects(false);
            Response response;
            response = conn.execute();
            Map<String, String> getCookies = response.cookies();
            String Cookie = getCookies.toString();
            System.err.println(Cookie);
            Cookie = Cookie.substring(Cookie.indexOf("{")+1, Cookie.lastIndexOf("}"));
            Cookie = Cookie.replaceAll(",", ";");
            System.out.println(Cookie);
            return Cookie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static String goHttp() {

        try {
            Connection conn = Jsoup.connect(url1);
            conn.header("Host","xueqiu.com");
            conn.header("Connection","keep-alive");
            conn.header("Cache-Control","max-age=0");
            conn.header("Upgrade-Insecure-Requests","1");
            conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            conn.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//          conn.header("Accept-Encoding","gzip, deflate, sdch, br");
            conn.header("Accept-Language","zh-CN,zh;q=0.8");
            conn.header("Cookie",returnCookies());
            conn.method(Method.GET);
            conn.followRedirects(false);
            conn.ignoreContentType(true);
            Response response = conn.execute();
            String body = response.body(); 
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
	
	
	
	

}