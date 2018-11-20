package com.nn.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputUtil {
	 // 读取本地图片获取输入流
	    public static FileInputStream readImage(String path) throws IOException {
	        return new FileInputStream(new File(path));
	    }
	
	    // 读取表中图片获取输出流
	    public static void readBin2Image(String targetPath) {
	    	InputStream in = null;
	        File file = new File(targetPath);
	        String path = targetPath;
	        if (!file.exists()) {
	            new File(path).mkdir();
	        }
	        FileOutputStream fos = null;
	        try {
	            fos = new FileOutputStream(file);
	            int len = 0;
	            byte[] buf = new byte[1024];
	            while ((len = in.read(buf)) != -1) {
	                fos.write(buf, 0, len);
	            }
	            fos.flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (null != fos) {
	                try {
	                    fos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    
	    //存入图片到数据库
	    public static InputStream getImageStream(String url) throws IOException {
	            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	            connection.setReadTimeout(5000);
	            connection.setConnectTimeout(5000);
	            connection.setRequestMethod("GET");
	            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                InputStream inputStream = connection.getInputStream();
	                return inputStream;
	            }else {
					return null;
				}
	    }
	    
	    //将图片存入本地
	    public static void byteToFile(byte[] buf, String filePath, String fileName)  
	    {  
	        BufferedOutputStream bufferOut = null;  
	        FileOutputStream fileOut = null;  
	        File file = null;  
	        try  
	        {  
	            File resFile = new File(filePath);  
	            if (!resFile.exists() && resFile.isDirectory())  
	            {  
	                resFile.mkdirs();  
	            }  
	            file = new File(filePath + File.separator + fileName);  
	            fileOut = new FileOutputStream(file);  
	            bufferOut = new BufferedOutputStream(fileOut);  
	            bufferOut.write(buf);  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	        finally  
	        {  
	            if (bufferOut != null)  
	            {  
	                try  
	                {  
	                    bufferOut.close();  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (fileOut != null)  
	            {  
	                try  
	                {  
	                    fileOut.close();  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }          
	        
	    }
	    
	    //生日转成long类型
	    public static Long timeToLong(String time) {
//	    	Date date = new Date();
//	    	date.getTime();
//	    	System.err.println(date.getTime());
	    	Date date = null;
	    	try {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		date = sdf.parse(time);
	    		return date.getTime();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return 0L;
	    	}
	    }
	    
	    public static void main(String[] args) {
			Long longtime = timeToLong("2018-08-21");
			System.out.println(longtime);
		}
	    
}
