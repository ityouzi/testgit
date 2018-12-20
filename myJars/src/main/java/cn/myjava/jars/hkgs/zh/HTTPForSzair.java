package cn.myjava.jars.hkgs.zh;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Http请求
 * @author mszhou
 */
public class HTTPForSzair {
    private static final int TIMEOUT = 10000;
    public static final String ENCODING = "UTF-8";

//    public static String proxy_ip="166.111.80.162";
//    public static int proxy_port= 3128;
    
    /**
     * 创建HTTP连接
     * 
     * @param url
     *            地址
     * @param method
     *            方法
     * @param headerParameters
     *            头信息
     * @param body
     *            请求内容
     * @return
     * @throws Exception
     */
    public static HttpURLConnection createConnection(String url,
            String method, Map<String, String> headerParameters, String body, String cookie,String proxy_ip,int proxy_port)
            throws Exception {
    	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy_ip, proxy_port));  
        URL Url = new URL(url);
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection(proxy);
        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);
        // 设置 header
        if (headerParameters != null) {
            Iterator<String> iteratorHeader = headerParameters.keySet()
                    .iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                httpConnection.setRequestProperty(key,
                        headerParameters.get(key));
            }
        }	
        
        httpConnection.setRequestProperty("User-Agent", 
        		"Mozilla/5.0 (Linux; Android 4.4.2; SM-G955N Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");
        httpConnection.setRequestProperty("Accept", 
        		"application/json, text/javascript, */*; q=0.01");
        httpConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        httpConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=" + ENCODING);
        if(null != cookie){
            httpConnection.setRequestProperty("Cookie", cookie);
        }
        // 设置请求方法
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        // 写query数据流
        if (!(body == null || body.trim().equals(""))) {
            OutputStream writer = httpConnection.getOutputStream();
            try {
                writer.write(body.getBytes(ENCODING));
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }
        // 请求结果
        int responseCode = httpConnection.getResponseCode();
        if(responseCode == 302){
        	System.out.println(httpConnection.getHeaderField( "location" ));;
        }else if (responseCode != 200) {
            throw new Exception(responseCode
                    + ":"
                    + inputStream2String(httpConnection.getErrorStream(),
                            ENCODING));
        }

        return httpConnection;
    }

    /**
     * POST请求
     * @param address 请求地址
     * @param headerParameters 参数
     * @param body
     * @return
     * @throws Exception
     */
    
    public static String connect(String address,String proxy_ip,int proxy_port) throws Exception {
        String result = "";
        HttpURLConnection httpConnection = null;
        try {
        	URL Url = new URL(address);
        	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy_ip, proxy_port));  
            httpConnection = (HttpURLConnection) Url
                    .openConnection(proxy);
            // 设置请求时间
            httpConnection.setConnectTimeout(TIMEOUT);
            
            httpConnection.setRequestProperty("User-Agent", 
            		"Mozilla/5.0 (Linux; Android 4.4.2; SM-G955N Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");
            httpConnection.setRequestProperty("Accept", 
            		"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            // 设置请求方法
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
          
            // 请求结果
            int responseCode = httpConnection.getResponseCode();
            if (responseCode != 200) {
            	result =  null;
            }
            List<String> cookieList = httpConnection.getHeaderFields().get("Set-Cookie");
            if(null != cookieList && cookieList.size()>0){
            	for(String oneCookie:cookieList){
            		result = result+oneCookie;
            	}
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }
    
    /**
     * POST请求
     * @param address 请求地址
     * @param headerParameters 参数
     * @param body
     * @return
     * @throws Exception
     */
    public static String post(String address,
            Map<String, String> headerParameters, String body, String cookie,String proxy_ip,int proxy_port) throws Exception {

        return proxyHttpRequest(address, "POST", null,
                getRequestBody(headerParameters),cookie, proxy_ip, proxy_port);
    }

    /**
     * GET请求
     * @param address
     * @param headerParameters
     * @param body
     * @return
     * @throws Exception
     */
    public static String get(String address,
            Map<String, String> headerParameters, String body, String cookie,String proxy_ip,int proxy_port) throws Exception {

        return proxyHttpRequest(address + "?"
                + getRequestBody(headerParameters), "GET", null, null ,cookie, proxy_ip, proxy_port);
    }

    /**
     * 读取网络文件
     * @param address
     * @param headerParameters
     * @param body
     * @param file
     * @return
     * @throws Exception
     */
    public static String getFile(String address,
            Map<String, String> headerParameters, File file, String cookie,String proxy_ip,int proxy_port) throws Exception {
        String result = "fail";

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, "POST", null,
                    getRequestBody(headerParameters), cookie, proxy_ip, proxy_port);
            result = readInputStream(httpConnection.getInputStream(), file);

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    public static byte[] getFileByte(String address,
            Map<String, String> headerParameters, String cookie,String proxy_ip,int proxy_port) throws Exception {
        byte[] result = null;

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, "POST", null,
                    getRequestBody(headerParameters),cookie, proxy_ip, proxy_port);
            result = readInputStreamToByte(httpConnection.getInputStream());

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    /**
     * 读取文件流
     * @param in
     * @return
     * @throws Exception
     */
    public static String readInputStream(InputStream in, File file)
            throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }

            out = new FileOutputStream(file);
            out.write(output.toByteArray());

        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return "success";
    }

    public static byte[] readInputStreamToByte(InputStream in) throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;
        byte[] byteFile = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            byteFile = output.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return byteFile;
    }

    /**
     * HTTP请求
     * 
     * @param address
     *            地址
     * @param method
     *            方法
     * @param headerParameters
     *            头信息
     * @param body
     *            请求内容
     * @return
     * @throws Exception
     */
    public static String proxyHttpRequest(String address, String method,
            Map<String, String> headerParameters, String body, String cookie,String proxy_ip,int proxy_port) throws Exception {
        String result = null;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method,
                    headerParameters, body ,cookie, proxy_ip, proxy_port);

            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null
                    && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType()
                        .substring(
                                httpConnection.getContentType().indexOf(
                                        "charset=") + 8);
            }
            result = inputStream2String(httpConnection.getInputStream(),
                    encoding);
            // logger.info("HTTPproxy response: {},{}", address,
            // result.toString());

        } catch (Exception e) {
            // logger.info("HTTPproxy error: {}", e.getMessage());
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }

    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    public static String getRequestBody(Map<String, String> params) {
        return getRequestBody(params, true);
    }

    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    public static String getRequestBody(Map<String, String> params,
            boolean urlEncode) {
        StringBuilder body = new StringBuilder();

        Iterator<String> iteratorHeader = params.keySet().iterator();
        while (iteratorHeader.hasNext()) {
            String key = iteratorHeader.next();
            String value = params.get(key);

            if (urlEncode) {
                try {
                    body.append(key + "=" + URLEncoder.encode(value, ENCODING)
                            + "&");
                } catch (UnsupportedEncodingException e) {
                    // e.printStackTrace();
                }
            } else {
                body.append(key + "=" + value + "&");
            }
        }

        if (body.length() == 0) {
            return "";
        }
        return body.substring(0, body.length() - 1);
    }

    /**
     * 读取inputStream 到 string
     * @param input
     * @param encoding
     * @return
     * @throws IOException
     */
    private static String inputStream2String(InputStream input, String encoding)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }

        return result.toString();

    }


    /**
     * 设置 https 请求
     * @throws Exception
     */
    private static void trustAllHttpsCertificates() throws Exception {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String str, SSLSession session) {
                return true;
            }
        });
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }


    //设置 https 请求证书
    static class miTM implements javax.net.ssl.TrustManager,javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }


    }
/*
    //====================================================================
    //============================= 测试调用   ============================
    //====================================================================
    public static void main(String[] args) {

            try {

                //请求地址(我这里测试使用淘宝提供的手机号码信息查询的接口)
                String address = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";

                //请求参数
                Map<String, String> params = new HashMap<String, String>();
                params.put("tel", "13777777777");//这是该接口需要的参数

                // 调用 get 请求
                String res = get(address, params, null);
                System.out.println(res);//打印返回参数

                res = res.substring(res.indexOf("{"));//截取

                System.out.println(res.toString());//打印

            } catch (Exception e) {
                // TODO 异常
                e.printStackTrace();
            }

    }*/

}