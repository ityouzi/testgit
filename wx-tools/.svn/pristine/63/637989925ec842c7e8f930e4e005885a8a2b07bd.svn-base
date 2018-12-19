package cn.wx.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.exception.WxErrorException;

import cn.wx.service.WXService;
import cn.wx.utils.Msg;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx")
public class WXController {
	
	@Autowired
	private WXService wxService;
	
	static IService iService = new WxService();
	
	@RequestMapping("/getAT")
	@ResponseBody
	public Msg getAT(String code){
		System.err.println("进来了");
		return Msg.success().add("result", wxService.getAT(code));
	}
	
	//发送消息到客户微信
	@RequestMapping("/sendMsg")
	@ResponseBody
	public Msg sendMsg(String openid,String orderid,String contant){
		
		System.err.println(openid);
		System.err.println(orderid);
		
		TemplateSender sender = new TemplateSender();
		sender.setTouser(openid);
		sender.setTemplate_id("ba8fY0GNzQXHvesK95BdEAQVAhJDW7OVZb4ee7661yw");

		Map<String,Map<String,String>> data = new HashMap<String,Map<String,String>>();
		Map<String,String> first = new HashMap<String,String>();
		first.put("value","您好，您有一个授权需要处理");
		first.put("color","#FF0000");
		  
		Map<String,String> keyword1 = new HashMap<String,String>();
		keyword1.put("value","武汉闹闹科技");
		  
		Map<String,String> keyword2 = new HashMap<String,String>();
		keyword2.put("value",contant);
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		sender.setData(data);
		  
//		sender.setUrl("http://192.168.0.205:8020/wxqicheweixiu/html/jSignature.html?orderid="+orderid);
		sender.setUrl("http://foreover.vicp.cc/wx-tools/html/order-qianming.html?orderid="+orderid);
//		sender.setUrl("http://192.168.0.205:8085/wx-tools/jSignature.html?orderid="+orderid);
		try {
			TemplateSenderResult result = iService.templateSend(sender);
			System.out.println(result.toString());
			return Msg.success().add("result", "发布成功");
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//发送消息到复核人微信
	@RequestMapping("/sendFuhe")
	@ResponseBody
	public Msg sendFuhe(String openid,String contant){
		
		System.err.println(openid);
		
		TemplateSender sender = new TemplateSender();
		sender.setTouser(openid);
		sender.setTemplate_id("ba8fY0GNzQXHvesK95BdEAQVAhJDW7OVZb4ee7661yw");

		Map<String,Map<String,String>> data = new HashMap<String,Map<String,String>>();
		Map<String,String> first = new HashMap<String,String>();
		first.put("value","您好，您有一个授权需要处理");
		first.put("color","#FF0000");
		  
		Map<String,String> keyword1 = new HashMap<String,String>();
		keyword1.put("value","武汉闹闹科技");
		  
		Map<String,String> keyword2 = new HashMap<String,String>();
		keyword2.put("value",contant);
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		sender.setData(data);
		  
//		sender.setUrl("http://192.168.0.205:8020/wxqicheweixiu/html/jSignature.html?orderid="+orderid);
//		sender.setUrl("http://foreover.vicp.cc/wx-tools/html/order-qianming.html?orderid="+orderid);
//		sender.setUrl("http://192.168.0.205:8085/wx-tools/jSignature.html?orderid="+orderid);
		try {
			TemplateSenderResult result = iService.templateSend(sender);
			System.out.println(result.toString());
			return Msg.success().add("result", "发布成功");
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return null;
	}


}