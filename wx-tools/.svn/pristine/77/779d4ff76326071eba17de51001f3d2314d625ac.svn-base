package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.result.IndustryResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.exception.WxErrorException;

public class MyTest {
	
	static IService iService = new WxService();
	
	public static void main(String[] args) {
//		try {
//            IndustryResult result = iService.templateGetIndustry();
//            System.out.println(result.getPrimary_industry());
//            System.out.println(result.getSecondary_industry());
//        } catch (WxErrorException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
		
		TemplateSender sender = new TemplateSender();
        sender.setTouser("openid");
        sender.setTemplate_id("templateId");
        sender.setData("Object：与模板内容对应的对象");
        sender.setUrl("url");
        try {
            TemplateSenderResult result = iService.templateSend(sender);
            System.out.println(result.toString());
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

}