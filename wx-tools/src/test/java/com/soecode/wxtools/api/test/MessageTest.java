package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.SenderContent.Media;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MessageTest {

  static IService iService = new WxService();
  
  public static void main(String[] args) {
	  
	  TemplateSender sender = new TemplateSender();
	  sender.setTouser("om0lM1dvlVSJ4EuwFqbWMcTMIQpM");
	  sender.setTemplate_id("ba8fY0GNzQXHvesK95BdEAQVAhJDW7OVZb4ee7661yw");
	  
	  Map<String,Map<String,String>> data = new HashMap<String,Map<String,String>>();
	  Map<String,String> first = new HashMap<String,String>();
	  first.put("value","您好，您有一个维修路测授权需要处理");
	  first.put("color","#FF0000");
	  
	  Map<String,String> keyword1 = new HashMap<String,String>();
	  keyword1.put("value","汽修集团武汉部 部长:张维修");
	  
	  Map<String,String> keyword2 = new HashMap<String,String>();
	  keyword2.put("value","电子签字");
	  
	  Map<String,String> keyword3 = new HashMap<String,String>();
	  keyword3.put("value","闹闹科技");
	  
	  Map<String,String> keyword4 = new HashMap<String,String>();
	  keyword4.put("value","服务顾问");
	  
	  Map<String,String> keyword5 = new HashMap<String,String>();
	  keyword5.put("value","欧阳维修");
	  
	  Map<String,String> remark = new HashMap<String,String>();
	  remark.put("value","请核对无误后及时处理!");
	  
	  data.put("first", first);
	  data.put("keyword1", keyword1);
	  data.put("keyword2", keyword2);
//	  data.put("keyword3", keyword3);
//	  data.put("keyword4", keyword4);
//	  data.put("keyword5", keyword5);
	  data.put("remark", remark);
	  
	  sender.setData(data);
	  
	  try {
		  TemplateSenderResult result = iService.templateSend(sender);
		  System.out.println(result.toString());
	  } catch (WxErrorException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
  }

  @Test
  public void should_preview_send_news_to_user() throws Exception {
    PreviewSender sender = new PreviewSender();
    sender.setTouser("om0lM1S9WF5ywIeT2425CHY4I0BI");
    sender.setMsgtype(WxConsts.MASS_MSG_MPNEWS);
    sender.setMpnews(new Media("QR3FgphTwoIpP1FZ-4c__cQTEeIHxMl7e_rWAfFYyfo"));
    SenderResult result = iService.sendAllPreview(sender);
    System.out.println(result.toString());
  }

  @Test
  public void should_send_news_to_user() throws Exception {
    WxOpenidSender sender = new WxOpenidSender();
    List<String> openidList = new ArrayList<>();
    openidList.add("oROCnuNihJnO9bnKOAORDFFriPgQ");
    openidList.add("oROCnuAQMnkPpEhsAYFzU-1xhKcQ");
    sender.setTouser(openidList);
    sender.setMsgtype(WxConsts.MASS_MSG_MPNEWS);
    sender.setMpnews(new Media("QR3FgphTwoIpP1FZ-4c__cQTEeIHxMl7e_rWAfFYyfo"));
    SenderResult result = iService.sendAllByOpenid(sender);
    System.out.println(result.toString());
  }
}
