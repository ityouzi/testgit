package cn.myjava.jars.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.util.EncodingUtils;

/**
 * 文件处理类，包括对properties文件和txt文件内容操作，以及任意文件读取流写入流
 * @author Administrator
 *
 */
public class FileUtils {
	
//	public static void main(String[] args) throws IOException {
		//保存文件
//		File file = new File("E:/soft/eclipse-jee-kepler-SR2-win32-x86_64.zip");
//		InputStream inStream = new FileInputStream(file);
//		saveFile(inStream,"E:/eclipse-jee-kepler-SR2-win32-x86_64.zip");
		
		//获取工程目录下Properties文件key的值
//		System.out.println(getProperties("file.properties","asdfasdf"));
		
		//按行读取txt内容到list
//		getListForTxt("e:/a.txt");
		
		//内容按行写入txt
//		outputToTxt("e:/a.txt","测试进去");
//	}
	
	/**
	 * 保存文件
	 * @param inputStream	要保存的文件流
	 * @param path	要保存为文件的完整路径
	 * @return boolean
	 */
	public static boolean saveFile(InputStream inputStream,String path){
		try { 
		    int byteread = 0; 
		    @SuppressWarnings("resource")
			FileOutputStream fs = new FileOutputStream(path); 
		    byte[] buffer = new byte[1024]; 
		    while ( (byteread = inputStream.read(buffer)) != -1) { 
			    fs.write(buffer, 0, byteread); 
		    } 
		    inputStream.close(); 
		    return true;
		} catch (Exception e) { 
		    return false;
	    } 
	}
	
	/**
	 * 获取工程目录下Properties文件key的值
	 * @param filePath
	 * @param key
	 * @return String
	 */
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
	
	/**
	 * 按行读取txt内容到list
	 * @param path
	 * @return List
	 */
	public static List<String> getListForTxt(String path){
		List<String> list = new ArrayList<String>();
		try{
			//BufferedReader是可以按行读取文件  
	        FileInputStream inputStream = new FileInputStream(path);  
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  
	        String str = null;  
	        while((str = bufferedReader.readLine()) != null) {  
	            System.out.println(str);  
	            list.add(str);
	        }  
	        //close  
	        inputStream.close();  
	        bufferedReader.close();  
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 内容按行写入txt
	 * @param path
	 * @param content
	 * @return boolean
	 */
	public static boolean outputToTxt(String path,String content){
        try {  
            //true不覆盖已有内容  
        	FileOutputStream fos = new FileOutputStream(path, true);    
            //写入  
            fos.write(content.getBytes());  
            // 写入一个换行  
            fos.write("\r\n".getBytes());  
            fos.flush();  
            fos.close();   
            return true;
        } catch (IOException e) {  
            e.printStackTrace();
            return false;
        }  
	}
	
	
	
	public static void main222(String[] args) throws Exception {
        String code = resolveCode("E:\\xz\\t_user_p.txt");  
        File file = new File("E:\\xz\\t_user_p.txt");  
        InputStream is = new FileInputStream(file);  
        InputStreamReader isr = new InputStreamReader(is, code);  
        BufferedReader bufferedReader = new BufferedReader(isr);  
        
        String str = null;  
        int i = 1;
        while((str = bufferedReader.readLine()) != null) {  
        	str = "INSERT INTO customer VALUES ("+str+");";
            System.out.println(i+"******"+str);  
            FileOutputStream fos = new FileOutputStream("E:\\xz\\d.sql", true);    
            //写入  
            fos.write(str.getBytes());  
            // 写入一个换行  
            fos.write("\r\n".getBytes());  
            fos.flush();  
            fos.close();   
            i++;
        }  
	}
	
	public static void main(String[] args) {
		System.out.println(getProperties("file.properties","fasdfgasfsaasF"));
	}
	
	//处理读取txt中文乱码问题
	public static String resolveCode(String path) throws Exception {  
//      String filePath = “D:/article.txt"; //[-76, -85, -71]  ANSI  
//      String filePath = “D:/article111.txt";  //[-2, -1, 79] unicode big endian  
//      String filePath = “D:/article222.txt";  //[-1, -2, 32]  unicode  
//      String filePath = “D:/article333.txt";  //[-17, -69, -65] UTF-8  
        InputStream inputStream = new FileInputStream(path);    
        byte[] head = new byte[3];    
        inputStream.read(head);      
        String code = "gb2312";  //或GBK  
        if (head[0] == -1 && head[1] == -2 )    
            code = "UTF-16";    
        else if (head[0] == -2 && head[1] == -1 )    
            code = "Unicode";    
        else if(head[0]==-17 && head[1]==-69 && head[2] ==-65)    
            code = "UTF-8";    
            
        inputStream.close();  
          
        System.out.println(code);   
        return code;  
    } 

}