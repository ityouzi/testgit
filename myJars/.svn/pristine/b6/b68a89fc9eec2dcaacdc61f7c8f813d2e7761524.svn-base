package cn.myjava.jars.option.img.scan;

import java.io.File;
import java.net.URL;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImgScanUtils {
	
		public static void main(String[] args){  
			ITesseract instance = new Tesseract();
	        File imageFile = new File("E:/TIM图片20180510180212.jpg");
//	        File imageFile = new File("C:/Users/Administrator/Desktop/验证码/乐桃1.png");
	        
	        randCode(imageFile,instance);
	    }
		
		public static String randCode(File file,ITesseract instance){
	        URL url = ClassLoader.getSystemResource("tessdata");
	        String path = url.getPath().substring(1);
	        instance.setDatapath(path);
	        // 默认是英文（识别字母和数字），如果要识别中文(数字 + 中文），需要制定语言包  
//	        instance.setLanguage("chi_sim");  
	        try{  
	            String result = instance.doOCR(file);  
	            System.out.println(result);  
	            return result;
	        }catch(TesseractException e){  
	            System.out.println(e.getMessage()); 
	            return e.getMessage();
	        }  
		}

}