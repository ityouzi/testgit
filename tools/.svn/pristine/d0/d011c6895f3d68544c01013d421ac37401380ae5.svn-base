package cn.tools.imgclear;

import javax.imageio.ImageIO; 
import javax.imageio.ImageWriter; 
import javax.imageio.stream.ImageOutputStream; 
import javax.net.ssl.HttpsURLConnection; 

import java.awt.*; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 
  
/** 
 * Created by Jzhung on 2017/2/27. 
 * 文档图片水印处理 
 */ 
public class ImageConverter { 
    private static List<File> fileList = new ArrayList<File>(); 
    
    //从一个文件夹到另一个文件夹批量去水印
    public static void main(String[] args) throws IOException { 
    	String dir = "E:\\img\\imgdatas\\partimg\\noclear";
    	String saveDir = "E:\\img\\imgdatas\\partimg\\clear";
        convertAllImages(dir, saveDir); 
    } 
  
    private static void convertAllImages(String dir, String saveDir) throws IOException { 
        File dirFile = new File(dir); 
        File saveDirFile = new File(saveDir); 
        dir = dirFile.getAbsolutePath(); 
        saveDir = saveDirFile.getAbsolutePath(); 
        loadImages(new File(dir)); 
        for (File file : fileList) { 
        	String filePath = "";
        	String dstPath = "";
        	try{
            filePath = file.getAbsolutePath(); 
            dstPath = saveDir + filePath.substring(filePath.indexOf(dir) + dir.length(), filePath.length()); 
            System.out.println("converting: " + filePath); 
            replaceColor(file.getAbsolutePath(), dstPath); 
        	}catch(Exception e){
        		FileInputStream ins = new FileInputStream(new File(filePath));
                FileOutputStream out = new FileOutputStream(new File(dstPath));
                byte[] b = new byte[1024];
                int n=0;
                while((n=ins.read(b))!=-1){
                    out.write(b, 0, n);
                }
                
                ins.close();
                out.close();
        		
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
                        loadImages(fileArray[i]); 
                    } 
                } 
            } else { 
                String name = f.getName(); 
                if (name.endsWith("png") || name.endsWith("jpg") || name.endsWith("gif")) { 
                    fileList.add(f); 
                } 
            } 
        } 
    } 
  
    private static void replaceColor(String srcFile, String dstFile) { 
        try { 
            Color color = new Color(252, 222, 220); 
            replaceImageColor(srcFile, dstFile, color, Color.WHITE); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
  
    public static void replaceImageColor(String file, String dstFile, Color srcColor, Color targetColor) throws IOException { 
        URL http; 
        if (file.trim().startsWith("https")) { 
            http = new URL(file); 
            HttpsURLConnection conn = (HttpsURLConnection) http.openConnection(); 
            conn.setRequestMethod("GET"); 
        } else if (file.trim().startsWith("http")) { 
            http = new URL(file); 
            HttpURLConnection conn = (HttpURLConnection) http.openConnection(); 
            conn.setRequestMethod("GET"); 
        } else { 
            http = new File(file).toURI().toURL(); 
        } 
        BufferedImage bi = ImageIO.read(http.openStream()); 
        if(bi == null){ 
            return; 
        } 
        System.out.println("***********开始处理****");
        Color wColor = new Color(255, 255, 255); 
        for (int i = 0; i < bi.getWidth(); i++) { 
            for (int j = 0; j < bi.getHeight(); j++) { 
                //System.out.println(bi.getRGB(i, j)); 
                int color = bi.getRGB(i, j); 
                Color oriColor = new Color(color); 
                int red = oriColor.getRed(); 
                int greed = oriColor.getGreen(); 
                int blue = oriColor.getBlue(); 
                //粉色 
                if (greed < 190 || blue < 190) { 
  
                } else { 
//                    if ((red >= 216 || red <= 219) && (greed >= 208 || greed <= 212) && (blue >= 208 || blue <= 212)) { 
                        bi.setRGB(i, j, wColor.getRGB()); 
//                    } 
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