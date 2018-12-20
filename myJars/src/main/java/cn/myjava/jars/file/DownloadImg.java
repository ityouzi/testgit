package cn.myjava.jars.file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImg {
	
	public void down(String imgurl,String path){
		try{
		    // 构造URL
		    URL url = new URL(imgurl);
		    // 打开连接
		    URLConnection con = url.openConnection();
		    // 输入流
		    InputStream is = con.getInputStream();
		    // 1K的数据缓冲
		    byte[] bs = new byte[1024];
		    // 读取到的数据长度
		    int len;
		    // 输出的文件流
		    OutputStream os = new FileOutputStream(path);
		    // 开始读取
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
		    // 完毕，关闭所有链接
		    os.close();
		    is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}   

}
