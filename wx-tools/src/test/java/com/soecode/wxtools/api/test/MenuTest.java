package com.soecode.wxtools.api.test;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMenu.WxMenuButton;
import com.soecode.wxtools.bean.WxMenu.WxMenuRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;

@Ignore
public class MenuTest {

  static IService iService = new WxService();

  public static void should_create_normal_menu_successfully() throws Exception {
    WxMenu menu = getWxMenu();
    String result = iService.createMenu(menu, false);
    Assert.assertEquals("{\"errcode\":0,\"errmsg\":\"ok\"}", result);
  }

  public void should_create_condition_menu_successfully() throws Exception {
    WxMenu menu = getWxMenu();
    WxMenuRule matchrule = new WxMenuRule();
    matchrule.setTag_id("103");
    matchrule.setCountry("中国");
    matchrule.setProvince("广东");
    menu.setMatchrule(matchrule);
    String result = iService.createMenu(menu, true);
    System.out.println(result);
  }

  public void should_delete_condition_menu_successfully() throws Exception {
    String result = iService.deleteMenu("443508866");
    System.out.println(result);
  }


  private static WxMenu getWxMenu() {
    WxMenu menu = new WxMenu();
    List<WxMenuButton> btnList = new ArrayList<>();

    //设置VIEW类型的按钮2
    WxMenuButton btn1 = new WxMenuButton();
    btn1.setType(WxConsts.MENU_BUTTON_VIEW);
    btn1.setUrl("http://www.nowlv.com/NN/html/login.html");
    btn1.setName("公司内部");

  //设置含有子按钮的按钮2
    List<WxMenuButton> subList1 = new ArrayList<>();
    //子按钮
    WxMenuButton btn2_1 = new WxMenuButton();
    btn2_1.setType(WxConsts.MENU_BUTTON_VIEW);
    btn2_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2Fgztongzhi.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn2_1.setName("gz通知");
    WxMenuButton btn2_2 = new WxMenuButton();
    btn2_2.setType(WxConsts.MENU_BUTTON_VIEW);
    btn2_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2Fjiechugztz.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn2_2.setName("解除gztz");
    
    subList1.add(btn2_1);
    subList1.add(btn2_2);
    //把子按钮列表设置进按钮3
    WxMenuButton btn2 = new WxMenuButton();
    btn2.setName("其它");
    btn2.setSub_button(subList1);

    //设置含有子按钮的按钮3
    List<WxMenuButton> subList = new ArrayList<>();
    //子按钮
    WxMenuButton btn3_2 = new WxMenuButton();
    btn3_2.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2Fkehubangding.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn3_2.setName("账号绑定");
    WxMenuButton btn3_3 = new WxMenuButton();
    btn3_3.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2Forder.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn3_3.setName("查看订单");
    WxMenuButton btn3_4 = new WxMenuButton();
    btn3_4.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2FComplaint-list.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn3_4.setName("投诉建议");
    WxMenuButton btn3_5 = new WxMenuButton();
    btn3_5.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_5.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2Fyuangongbangding.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn3_5.setName("员工绑定");
    WxMenuButton btn3_6 = new WxMenuButton();
    btn3_6.setType(WxConsts.MENU_BUTTON_VIEW);
    btn3_6.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx11279bb04f32769a&redirect_uri=http%3A%2F%2Fforeover.vicp.cc%2Fwx-tools%2Fhtml%2Fjiechubangding.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    btn3_6.setName("解除绑定");
    
    subList.add(btn3_2);
    subList.add(btn3_3);
    subList.add(btn3_4);
    subList.add(btn3_5);
    subList.add(btn3_6);
    //把子按钮列表设置进按钮3
    WxMenuButton btn3 = new WxMenuButton();
    btn3.setName("汽修");
    btn3.setSub_button(subList);

    //将三个按钮设置进btnList
    btnList.add(btn1);
    btnList.add(btn2);
    btnList.add(btn3);
    //设置进菜单类
    menu.setButton(btnList);
    return menu;
  }
  
  public static void main(String[] args) throws Exception {
	  should_create_normal_menu_successfully();
  }
  
}