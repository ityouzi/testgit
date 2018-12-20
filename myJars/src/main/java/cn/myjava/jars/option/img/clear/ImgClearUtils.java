package cn.myjava.jars.option.img.clear;

import javax.imageio.ImageIO; 
import javax.imageio.ImageWriter; 
import javax.imageio.stream.ImageOutputStream; 

import cn.myjava.jars.option.img.clear.GifDecoder.GifImage;

import java.awt.*; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 
  
/** 
 * Created by Jzhung on 2017/2/27. 
 * 文档图片水印处理 
 */ 
public class ImgClearUtils { 
    private static List<File> fileList = new ArrayList<File>(); 
    
    //从一个文件夹到另一个文件夹批量去水印
    public static void main(String[] args) throws IOException { 
    	String dir = "E:\\epc-img\\part5+水印";
//    	String dir = "E:\\epc-img\\part2\\20180706103844870308570.gif";
    	String saveDir = "E:\\epc-img\\part5";
        convertAllImages(dir, saveDir); 
    } 
  
    private static void convertAllImages(String dir, String saveDir) throws IOException {
        loadImages(new File(dir)); 
        for (File file : fileList) {
        	try{
	            String fileName = file.getName();
	            String dstPath = saveDir +"\\"+ fileName; 
	            System.out.println("converting: " + dstPath); 
	            replaceColor(file.getAbsolutePath(), dstPath); 
        	}catch(Exception e){
        		e.printStackTrace();
        		continue;
        	}
        } 
    } 
  
    public static void loadImages(File f) { 
        if (f != null) { 
            if (f.isDirectory()) { 
                File[] fileArray = f.listFiles(); 
                if (fileArray != null) { 
                    for (int i = 0; i < fileArray.length; i++) { 
                        //递归调用 
                    	fileList.add(fileArray[i]); 
                    } 
                } 
            }else{
            	fileList.add(f); 
            }
        } 
    } 
  
    private static void replaceColor(String srcFile, String dstFile) { 
        try { 
            Color color = new Color(252, 254, 252); 
            replaceImageColor(srcFile, dstFile, color, Color.WHITE); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
  
    //读取img，java自带的方法只适用部分gif文件的读取，GIFImage是专业读取gif文件的方法
    public static void replaceImageColor(String file, String dstFile, Color srcColor, Color targetColor) throws IOException {
    	FileInputStream data = new FileInputStream(file);
    	GifImage gif = GifDecoder.read(data);
    	BufferedImage bi = gif.getFrame(0);
    	
//        BufferedImage bi = ImageIO.read(new FileInputStream(file));
        
        if(bi == null){ 
            return; 
        } 
        System.out.println("***********开始处理****");
        
        for (int i = 0; i < bi.getWidth(); i++) { 
            for (int j = 0; j < bi.getHeight(); j++) { 
                int color = bi.getRGB(i, j); 
                Color oriColor = new Color(color); 
                int red = oriColor.getRed(); 
                int greed = oriColor.getGreen(); 
                int blue = oriColor.getBlue(); 
                
                //水印颜色替换为srcColor
                if(red>210 && greed>210 && blue>210){
                	bi.setRGB(i, j, srcColor.getRGB());
                }
            } 
        } 
        String type = file.substring(file.lastIndexOf(".") + 1, file.length()); 
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName(type); 
        ImageWriter writer = it.next(); 
        File f = new File(dstFile); 
        f.getParentFile().mkdirs(); 
        ImageOutputStream ios = ImageIO.createImageOutputStream(f); 
        writer.setOutput(ios); 
        writer.write(bi); 
        bi.flush(); 
        ios.flush(); 
        ios.close(); 
    } 
    
} 