package cn.myjava.jars.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TVTest {
	
	
	private static final Logger LOG = LogManager.getLogger(HttpClient.class);  
    public  static CloseableHttpClient httpClient = null;  
    public  static HttpClientContext context = null;  
    public  static CookieStore cookieStore = null;  
    public  static RequestConfig requestConfig = null;  
	
	
	
	
//	public static void main(String[] args) throws IOException {
////		HttpClientBuilder builder = HttpClients.custom()
////	            .disableAutomaticRetries() //关闭自动处理重定向
////	            .setRedirectStrategy(new LaxRedirectStrategy());//利用LaxRedirectStrategy处理POST重定向问题
//		
//		CookieStore cookieStore = new BasicCookieStore();
//	    CloseableHttpClient http = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		
//	    String url1 = "https://tv.travelsky.com/tvb2b/VerificationCode.do";
//		String url2 = "https://tv.travelsky.com/tvb2b/Login.do";
//		
//		HttpGet get1 = new HttpGet(url1);
//		CloseableHttpResponse response1 = http.execute(get1);
//		InputStream is = response1.getEntity().getContent();
//		
//		// 1K的数据缓冲    
//        byte[] bs = new byte[1024];    
//        // 读取到的数据长度    
//        int len;    
//       OutputStream os = new FileOutputStream("c:/1.png");    
//        // 开始读取    
//        while ((len = is.read(bs)) != -1) {    
//          os.write(bs, 0, len);    
//        }    
//        // 完毕，关闭所有链接    
//        os.close();    
//        is.close();    
//		
//        Scanner sc = new Scanner(System.in);  
//        System.out.println("请输入数据:");  
//        String verCode = sc.next();
//        HttpPost post2 = new HttpPost(url2);
//        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
//        parameters.add(new BasicNameValuePair("serviceURL", "https://tv.travelsky.com:443/tvb2b/ssoAction.do"));
//        parameters.add(new BasicNameValuePair("signMessage", ""));
//        parameters.add(new BasicNameValuePair("loginname", "njyysj"));
//        parameters.add(new BasicNameValuePair("loginpass", "njyy.sj123"));
//        parameters.add(new BasicNameValuePair("vrtifycode", verCode));
//        post2.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
//        CloseableHttpResponse response2 = http.execute(post2);
//        System.out.println(EntityUtils.toString(response2.getEntity()));
//        
//        List<Cookie> cookies = cookieStore.getCookies();
//        String c = "";
//        for (int i = 0; i < cookies.size(); i++) {
//        	String cname = cookies.get(i).getName();
//        	String cvalue= cookies.get(i).getValue();
//        	String cc = "";
//        	if(cvalue==null || "".equals(cvalue)){
//        		if(i==cookies.size()-1){
//        			cc = cname;
//        		}else{
//        			cc = cname+"; ";
//        		}
//        	}else{
//        		if(i==cookies.size()-1){
//        			cc = cname+"= "+cvalue;
//        		}else{
//        			cc = cname+"= "+cvalue+"; ";
//        		}
//        	}
//        	c = c + cc;
//        }
//        System.out.println(c);
//
//        
//        String url3 = "https://tv.travelsky.com/tvb2b/OWShoppingQuery.do";
//        
//        HttpPost post3 = new HttpPost(url3);
//        List<BasicNameValuePair> parameters1 = new ArrayList<BasicNameValuePair>();
//		parameters1.add(new BasicNameValuePair("tripType","0"));
//		parameters1.add(new BasicNameValuePair("groupType","0"));
//		parameters1.add(new BasicNameValuePair("isInter","0"));
//		parameters1.add(new BasicNameValuePair("orgCity","LXA"));
//		parameters1.add(new BasicNameValuePair("dstCity","HGH"));
//		parameters1.add(new BasicNameValuePair("takeOffDate","2018-06-15"));
//		parameters1.add(new BasicNameValuePair("takeOffDate","2018-06-15"));
//		parameters1.add(new BasicNameValuePair("grpName",""));
//		parameters1.add(new BasicNameValuePair("personNum",""));
//		parameters1.add(new BasicNameValuePair("contacTel","13182867936"));
//		parameters1.add(new BasicNameValuePair("contactName","苏军"));
//		parameters1.add(new BasicNameValuePair("contactPhone","025-86666666"));
//		parameters1.add(new BasicNameValuePair("contactEmail","2846174985@QQ.COM"));
//		parameters1.add(new BasicNameValuePair("contactAddress",""));
//		parameters1.add(new BasicNameValuePair("mulStep","-1"));
//		
//		post3.addHeader("Host", "tv.travelsky.com");
//		post3.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:61.0) Gecko/20100101 Firefox/61.0");
//		post3.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		post3.addHeader("Accept-Language", "en-US,en;q=0.5");
//		post3.addHeader("Accept-Encoding", "gzip, deflate, br");
//		post3.addHeader("Referer", "https://tv.travelsky.com/tvb2b/ReservationIndex.do");
//		post3.addHeader("Content-Type", "application/x-www-form-urlencoded");
////		post3.addHeader("Content-Length", "261");
//		post3.addHeader("Connection", "keep-alive");
//		post3.addHeader("Upgrade-Insecure-Requests", "1");
//		post3.addHeader("Pragma", "no-cache");
//		post3.addHeader("Cache-Control", "no-cache");
//		post3.addHeader("Cookie", c);
//		
//		
//		CloseableHttpResponse response3 = http.execute(post3);
//        System.out.println(EntityUtils.toString(response3.getEntity()));
//        
//	}
	
	

    static {  
        init();  
    }  

    private static void init() {  
        context = HttpClientContext.create();  
        cookieStore = new BasicCookieStore();  
        // 配置超时时间（连接服务端超时1秒，请求数据返回超时2秒）  
        requestConfig = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(60000)  
                       .setConnectionRequestTimeout(60000).build();  
        // 设置默认跳转以及存储cookie  
        httpClient = HttpClientBuilder.create().disableAutomaticRetries() //关闭自动处理重定向
                	 .setRedirectStrategy(new LaxRedirectStrategy()) 
                     .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())  
                     .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)  
                     .setDefaultCookieStore(cookieStore).build();  
    }  

    /**  
     * http get  
     * @param url  
     * @return response  
     * @throws ClientProtocolException  
     * @throws IOException  
     */  
    public static CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {  
        HttpGet httpget = new HttpGet(url);  
        CloseableHttpResponse response = httpClient.execute(httpget, context);  
        try {  
            cookieStore = context.getCookieStore();  
            List<Cookie> cookies = cookieStore.getCookies();  
            for (Cookie cookie : cookies) {  
                LOG.debug("key:" + cookie.getName() + "  value:" + cookie.getValue());  
            }  
        } finally {  
            response.close();  
        }  
        return response;  
    }  

    /**  
     * http post  
     * @param url  
     * @param parameters  
     * form表单  
     * @return response  
     * @throws ClientProtocolException  
     * @throws IOException  
     */  
    public static CloseableHttpResponse post(String url, String parameters)  
            throws ClientProtocolException, IOException {  
        HttpPost httpPost = new HttpPost(url);  
        List<NameValuePair> nvps = toNameValuePairList(parameters);  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));  
        CloseableHttpResponse response = httpClient.execute(httpPost, context);  
        try {  
            cookieStore = context.getCookieStore();  
            List<Cookie> cookies = cookieStore.getCookies();  
            for (Cookie cookie : cookies) {  
                LOG.debug("key:" + cookie.getName() + "  value:" + cookie.getValue());  
            }  
        } finally {  
            response.close();  
        }  
        return response;  
          
    }  
      
    @SuppressWarnings("unused")  
    private static List<NameValuePair> toNameValuePairList(String parameters) {  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        String[] paramList = parameters.split("&");  
        for (String parm : paramList) {  
            int index = -1;  
            for (int i = 0; i < parm.length(); i++) {  
                index = parm.indexOf("=");  
                break;  
            }  
            String key = parm.substring(0, index);  
            String value = parm.substring(++index, parm.length());  
            nvps.add(new BasicNameValuePair(key, value));  
        }  
        System.out.println(nvps.toString());  
        return nvps;  
    }  

    /**  
     * 手动增加cookie  
     * @param name  
     * @param value  
     * @param domain  
     * @param path  
     */  
    public static void addCookie(String name, String value, String domain, String path) {  
        BasicClientCookie cookie = new BasicClientCookie(name, value);  
        cookie.setDomain(domain);  
        cookie.setPath(path);  
        cookieStore.addCookie(cookie);  
    }  

    /**  
     * 把结果console出来  
     * @param httpResponse  
     * @throws ParseException  
     * @throws IOException  
     */  
    public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {  
        // 获取响应消息实体  
        HttpEntity entity = httpResponse.getEntity();  
        // 响应状态  
        System.out.println("status:" + httpResponse.getStatusLine());  
        System.out.println("headers:");  
        HeaderIterator iterator = httpResponse.headerIterator();  
        while (iterator.hasNext()) {  
            System.out.println("\t" + iterator.next());  
        }  
        // 判断响应实体是否为空  
        if (entity != null) {  
    //      String responseString = EntityUtils.toString(entity);  
    //      System.out.println("response length:" + responseString.length());  
    //      System.out.println("response content:" + responseString.replace("\r\n", ""));  
        }  
        System.out.println(  
                "------------------------------------------------------------------------------------------\r\n");  
    }  

    /**  
     * 把当前cookie从控制台输出出来  
     */  
    public static void printCookies() {  
        System.out.println("headers:");  
        cookieStore = context.getCookieStore();  
        List<Cookie> cookies = cookieStore.getCookies();  
        for (Cookie cookie : cookies) {  
            System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());  
        }  
    }  

    /**  
     * 检查cookie的键值是否包含传参  
     * @param key  
     * @return  
     */  
    public static boolean checkCookie(String key) {  
        cookieStore = context.getCookieStore();  
        List<Cookie> cookies = cookieStore.getCookies();  
        boolean res = false;  
        for (Cookie cookie : cookies) {  
            if (cookie.getName().equals(key)) {  
                res = true;  
                break;  
            }  
        }  
        return res;  
    }  

    /**  
     * 直接把Response内的Entity内容转换成String  
     * @param httpResponse  
     * @return  
     * @throws ParseException  
     * @throws IOException  
     */  
    public static String toString(CloseableHttpResponse httpResponse) throws ParseException, IOException {  
        // 获取响应消息实体  
        HttpEntity entity = httpResponse.getEntity();  
        if (entity != null)  
            return EntityUtils.toString(entity);  
        else  
            return null;  
    }  
      
    public static void main(String[] args) throws ClientProtocolException, IOException {  
    	
    	String url1 = "https://tv.travelsky.com/tvb2b/VerificationCode.do";
		String url2 = "https://tv.travelsky.com/tvb2b/Login.do";
		String url3 = "https://tv.travelsky.com/tvb2b/OWShoppingQuery.do";
		
		HttpGet get1 = new HttpGet(url1);
		CloseableHttpResponse response1 = httpClient.execute(get1);
		InputStream is = response1.getEntity().getContent();
		
		// 1K的数据缓冲    
        byte[] bs = new byte[1024];    
        // 读取到的数据长度    
        int len;    
       OutputStream os = new FileOutputStream("c:/1.png");    
        // 开始读取    
        while ((len = is.read(bs)) != -1) {    
          os.write(bs, 0, len);    
        }    
        // 完毕，关闭所有链接    
        os.close();    
        is.close();    
		
        Scanner sc = new Scanner(System.in);  
        System.out.println("请输入数据:");  
        String verCode = sc.next();
          
//      parameters.add(new BasicNameValuePair("serviceURL", "https://tv.travelsky.com:443/tvb2b/ssoAction.do"));
//      parameters.add(new BasicNameValuePair("signMessage", ""));
//      parameters.add(new BasicNameValuePair("loginname", "njyysj"));
//      parameters.add(new BasicNameValuePair("loginpass", "njyy.sj123"));
//      parameters.add(new BasicNameValuePair("vrtifycode", verCode));
        
        //用户登陆
        CloseableHttpResponse response = TVTest.post(url2, "serviceURL=https://tv.travelsky.com:443/tvb2b/ssoAction.do&signMessage="
        		+ "&loginname=njyysj&loginpass=njyy.sj123&vrtifycode="+verCode);  
        
        printResponse(response);
        printCookies();
//        addCookie("name","test","localhost","/BCP/");
        
        
        Header header=response.getFirstHeader("Location");
        //重定向地址
        String location =  header.getValue();
        System.out.println(location+"***************");

        //然后再对新的location发起请求即可

        HttpGet httpGet = new HttpGet(location);
        CloseableHttpResponse response2 = httpClient.execute(httpGet);
        System.out.println("返回报文"+EntityUtils.toString(response2.getEntity(), "UTF-8"));
        
        System.err.println(response2.toString());
        System.err.println("-----------------------------");
        
        
        
        
//        CloseableHttpResponse response3 = TVTest.post(url3, "tripType=0&groupType=0&isInter=0&orgCity=LXA&dstCity=HGH"
//        		+ "&takeOffDate=2018-06-15&takeOffDate=2018-06-15&grpName=&personNum=&contacTel=13182867936&contactName=苏军"
//        		+ "&contactPhone=025-86666666&contactEmail=2846174985@QQ.COM&contactAddress=&mulStep=-1");  
//        
//        printResponse(response3);
//        printCookies();
//        
//        
//        System.err.println(response3.toString());
//        System.err.println("-----------------------------");
//        System.err.println(EntityUtils.toString(response3.getEntity()));
        
        
    }  
	
	

}