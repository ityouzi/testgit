package cn.tools.yyReg;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Tools {
	
	public static String getProperties(String filePath,String key){
		 Properties prop = new Properties();
       String value = null;
       try {
           // 通过输入缓冲流进行读取配置文件
           InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
           // 加载输入流
           prop.load(InputStream);
           // 根据关键字获取value值
           value = prop.getProperty(key);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
       return value;
	}

}
