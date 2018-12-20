package cn.myjava.jars.option.browse;

import java.io.IOException;  
  
/** 
* @ClassName：OpenBrowse 
* @Description：使用java代码打开关闭浏览器（指定的浏览器或者计算机默认的浏览器） 
*               获取网页字节流内容信息， 
* @date：2017年7月28日 
* 修改备注： 
*/  
public class OpenCloseBrowse {  
      
      
    private static String urls = "http://www.yy.com/22795823";  
      
    /**  
    * @throws InterruptedException 
     * @Description: 打开计算机默认的浏览器访问指定的url页面 
    * @date: 2017年7月28日 下午2:30:24 
    * @修改备注:  
    */  
    public static void openBrowse() throws InterruptedException{  
        for (int i = 0;i<20;i++) {  
            if(java.awt.Desktop.isDesktopSupported()){  
                try{  
                    //创建一个URI实例,注意不是URL  
                    java.net.URI uri=java.net.URI.create(urls);  
                    //获取当前系统桌面扩展  
                    java.awt.Desktop dp=java.awt.Desktop.getDesktop();  
                    //判断系统桌面是否支持要执行的功能  
                    if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){  
                        //获取系统默认浏览器打开链接  
                        dp.browse(uri);  
                    }  
                }catch(java.lang.NullPointerException e){  
                    //此为uri为空时抛出异常  
                }catch(java.io.IOException e){  
                    //此为无法获取系统默认浏览器  
                }  
            } 
            Thread.sleep(500);
        }  
           
    }  
      
    /**  
    * @Description: 关闭浏览器（关闭指定的浏览器，在此处是强行关闭浏览器，强行杀死进程） 
    * @date: 2017年7月27日 下午8:31:34 
    * @修改备注:  
    */  
    public static void closeBrowse(){  
        try {  
            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");    
        } catch (IOException e) {  
            e.printStackTrace();  
        }    
          
    }  
      
  
    public static void main(String[] args) throws InterruptedException {  
        openBrowse();  
//        openIEBrowser(); 
        Thread.sleep(20000);
        closeBrowse();  
    }  
  
}  