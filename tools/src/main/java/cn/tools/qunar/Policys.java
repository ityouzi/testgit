package cn.tools.qunar;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

public class Policys {
	
	//上传政策程序样例
	public static void main(String[] args) throws Exception {
		long l=System.currentTimeMillis();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://tts.trade.qunar.com/tts/interface/policy.jsp");
		method.setRequestEntity(new ByteArrayRequestEntity(toByte(new BufferedInputStream(new FileInputStream("testPolicy.zip"))),"multipart/form-data"));
		client.executeMethod(method);
		System.out.println(method.getResponseBodyAsString());
		method.releaseConnection();
		System.out.println(System.currentTimeMillis()-l);
	}
	
	public static byte[] toByte(InputStream is) {
		byte[] a = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = is.read(buf, 0, buf.length)) != -1) {
				os.write(buf, 0, len);
			}
			a = os.toByteArray();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			is.close();
			}
			catch (IOException e) {
			e.printStackTrace();
			}
		}
		return a;
	}

}