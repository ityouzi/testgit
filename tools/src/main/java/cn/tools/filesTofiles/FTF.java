package cn.tools.filesTofiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FTF {
	
	public static void main(String[] args) {
		
		String spath = "E:/FTF/SPATH/";
		String dpath = "E:/FTF/DPATH/";
		
		//按行读取TXT内容
		try{
	        FileInputStream inputStream = new FileInputStream("E:/FTF/ftf.txt");  
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  
	        String str = null;  
	        while((str = bufferedReader.readLine()) != null){  
	            System.out.println(str);
	            getFile(spath,dpath,1,str);
	        }  
	        //close  
	        inputStream.close();  
	        bufferedReader.close();  
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//遍历文件夹下所有文件
    private static void getFile(String spath,String dpath,int deep,String fileName){   
        File file = new File(spath);   
        File[] array = file.listFiles();   
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){
				for (int j = 0; j < deep; j++){
	                String name = array[i].getName();
	                System.out.println(name);   
	                if(name.equals(fileName)){
	                	copyFile(array[i],dpath);
	                }
				}
            }  
        }   
    }   
    
    //复制文件
    public static void copyFile(File file, String dpath) { 
	    try { 
		    int bytesum = 0; 
		    int byteread = 0; 
		    if (file.exists()) { //文件存在时 
			    InputStream inStream = new FileInputStream(file); //读入原文件 
			    FileOutputStream fs = new FileOutputStream(dpath+file.getName()); 
			    byte[] buffer = new byte[1444]; 
			    while ( (byteread = inStream.read(buffer)) != -1) { 
				    bytesum += byteread; //字节数 文件大小 
				    fs.write(buffer, 0, byteread); 
			    } 
			    inStream.close(); 
		    } 
		} catch (Exception e) { 
		    System.out.println("复制单个文件操作出错"); 
		    e.printStackTrace(); 
	    } 
    }

}