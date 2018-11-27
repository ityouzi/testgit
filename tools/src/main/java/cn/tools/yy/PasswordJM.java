package cn.tools.yy;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.Reader;  
  
import javax.script.Invocable;  
import javax.script.ScriptEngine;  
import javax.script.ScriptEngineManager; 

//调js文件里的方法
public class PasswordJM {
	
		public static void main(String[]arg)throws IOException  
	    {  
	        // 得到一个ScriptEngine对象  
	        ScriptEngineManager maneger = new ScriptEngineManager();  
	        ScriptEngine engine = maneger.getEngineByName("JavaScript");  
	          
	        // 读js文件  
	        String jsFile = "C:/yy.js";  
	        FileInputStream fileInputStream = new FileInputStream(new File(jsFile));  
	        Reader scriptReader = new InputStreamReader(fileInputStream, "utf-8");  
	          
	        try  
	        {  
	            engine.eval(scriptReader);  
	            if (engine instanceof Invocable)  
	            {  
	                // 调用JS方法  
	                Invocable invocable = (Invocable)engine;  
	                String result = (String)invocable.invokeFunction("test", new Object[]{"IVWyy1111"});  
	                System.out.println(result);  
	            }
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	        finally  
	        {  
	            scriptReader.close();  
	        }  
	    }  
}