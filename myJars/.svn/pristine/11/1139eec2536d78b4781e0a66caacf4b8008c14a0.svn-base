package cn.myjava.jars.intface.ruokuai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.myjava.jars.http.CloseableHttpClientUtils;

public class RuoKuaiUtils {
	
	static String username = "foreover";
	static String password = "Girliess2017";
//	static String typeid = "3060";
	static String timeout = "10";
	static String softId = "93217";
	static String softKey = "0f52deddc13d4a1e893a7444dbf34f78";
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String typeid = "3060";
		CloseableHttpClient http = HttpClients.createDefault();
		HttpEntity entity = CloseableHttpClientUtils.getCloseablehttpGetProxy(http,"http://www.travelsky.com/tsky/servlet/CallYanServlet?title=home", "", 0, null);
		byte[] data = EntityUtils.toByteArray(entity);
		String result = httpPostImage("http://api.ruokuai.com/create.json", typeid, data);
		System.out.println(result);
	}
	
	/**
	 * 上传题目图片返回结果	
	 * @param username		用户名
	 * @param password		密码
	 * @param typeid		题目类型
	 * @param timeout		任务超时时间
	 * @param softid		软件ID
	 * @param softkey		软件KEY
	 * @param filePath		题目截图或原始图二进制数据路径
	 * @return
	 * @throws IOException
	 */
	public static String createByPost(String typeid,byte[] data) {
		String result = "";
		String param = String.format(
				"username=%s&password=%s&typeid=%s&timeout=%s&softid=%s&softkey=%s",
				username, password, typeid, timeout, softId, softKey);
		try {
			result = RuoKuaiUtils.httpPostImage("http://api.ruokuai.com/create.json", param, data);
		} catch(Exception e) {
			result = "未知问题";
		}
		return result;
	}

	/**
	 * 报错
	 * @param username
	 * @param password
	 * @param softId
	 * @param softKey
	 * @param error
	 * @return
	 */
	public static String error(String error) {
		// TODO Auto-generated method stub
		String ret="";
		String param = String.format("username=%s&password=%s&softid=%s&softkey=%s&id=%s",username,password,softId,softKey, error);
		try {
			ret=httpRequestData("http://api.ruokuai.com/reporterror.xml",param);
		} catch (Exception e) {
			// TODO: handle exception
			return "未知错误";
		}
		return ret;
	}
	
	private static String httpRequestData(String url, String param) throws IOException{
		// TODO Auto-generated method stub
		URL u;
		HttpURLConnection con = null;
		OutputStreamWriter osw;
		StringBuffer buffer = new StringBuffer();

		u = new URL(url);
		con = (HttpURLConnection)u.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(param);
		osw.flush();
		osw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(con
				.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
	 		buffer.append("\n");
		}
		return buffer.toString();
	}
	
	/**
	 * 字符串MD5加密
	 * @param s 原始字符串
	 * @return  加密后字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 答题
	 * @param url 			请求URL，不带参数 如：http://api.ruokuai.com/create.xml
	 * @param param			请求参数，如：username=test&password=1
	 * @param data			图片二进制流
	 * @return				平台返回结果XML样式 
	 * @throws IOException
	 */
	public static String httpPostImage(String url, String typeid,
		byte[] data) throws IOException {
		String param = String.format(
				"username=%s&password=%s&typeid=%s&timeout=%s&softid=%s&softkey=%s",
				username, password, typeid, timeout, softId, softKey);
		
		long time = (new Date()).getTime();
		URL u = null;
		HttpURLConnection con = null;
		String boundary = "----------" + MD5(String.valueOf(time));
		String boundarybytesString = "\r\n--" + boundary + "\r\n";
		OutputStream out = null;
		
		u = new URL(url);
		
		con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		//con.setReadTimeout(95000);   
		con.setConnectTimeout(95000); //此值与timeout参数相关，如果timeout参数是90秒，这里就是95000，建议多5秒
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setUseCaches(true);
		con.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
		
		out = con.getOutputStream();
			
		for (String paramValue : param.split("[&]")) {
			out.write(boundarybytesString.getBytes("UTF-8"));
			String paramString = "Content-Disposition: form-data; name=\""
					+ paramValue.split("[=]")[0] + "\"\r\n\r\n" + paramValue.split("[=]")[1];
			out.write(paramString.getBytes("UTF-8"));
		}
		out.write(boundarybytesString.getBytes("UTF-8"));

		String paramString = "Content-Disposition: form-data; name=\"image\"; filename=\""
				+ "sample.gif" + "\"\r\nContent-Type: image/gif\r\n\r\n";
		out.write(paramString.getBytes("UTF-8"));
		
		out.write(data);
		
		String tailer = "\r\n--" + boundary + "--\r\n";
		out.write(tailer.getBytes("UTF-8"));

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con
					.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
			buffer.append("\n");
		}
		return buffer.toString();
	}

}