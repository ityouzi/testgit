package cn.myjava.jars.option.downImage;

import java.io.File;    
import java.io.FileOutputStream;    
import java.io.InputStream;    
import java.io.OutputStream;    
import java.net.URL;    
import java.net.URLConnection;    
    
//下载图片到本地
public class DownloadImage {    
    
    /**   
     * @param args   
     * @throws Exception    
     */    
    public static void main(String[] args) throws Exception {    
        // TODO Auto-generated method stub    
    	for(int i = 0;i<1000;i++){
    		download("http://www.flycua.com/kaptcha.servlet", "联航登录"+i+".jpg","d:\\image\\");   
    	}
    }    
        
    public static void download(String urlString, String filename,String savePath) throws Exception {    
        // 构造URL    
        URL url = new URL(urlString);    
        // 打开连接    
        URLConnection con = url.openConnection();    
        //设置请求超时为5s    
        con.setConnectTimeout(5*1000);    
        // 输入流    
        InputStream is = con.getInputStream();    
        
        // 1K的数据缓冲    
        byte[] bs = new byte[1024];    
        // 读取到的数据长度    
        int len;    
        // 输出的文件流    
       File sf=new File(savePath);    
       if(!sf.exists()){    
           sf.mkdirs();    
       }    
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);    
        // 开始读取    
        while ((len = is.read(bs)) != -1) {    
          os.write(bs, 0, len);    
        }    
        // 完毕，关闭所有链接    
        os.close();    
        is.close();    
    }     
    
}    