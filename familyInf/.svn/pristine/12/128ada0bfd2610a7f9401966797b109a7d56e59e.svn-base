package com.nn.utils;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

public class PushUtils {

	// 定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
	private static String appId = "gAMCDtTfmf74MO8YQ9pt87";
	private static String appKey = "DXZ7r1z9ey6dyALu4Gf6B7";
	private static String masterSecret = "7MW7NhsYBX92GZFenH1ibA";
	private static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static String sendMsg(LinkTemplate template) {
		try {
			IGtPush push = new IGtPush(host, appKey, masterSecret);

	/*		LinkTemplate template = linkTemplateDemo();*/
			AppMessage message = new AppMessage();
			message.setData(template);

			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(24 * 1000 * 3600);
			// 推送给App的目标用户需要满足的条件
			AppConditions cdt = new AppConditions();
			List<String> appIdList = new ArrayList<String>();
			appIdList.add(appId);
			message.setAppIdList(appIdList);
			// 手机类型
			List<String> phoneTypeList = new ArrayList<String>();
			// 省份
			List<String> provinceList = new ArrayList<String>();
			// 自定义tag
			List<String> tagList = new ArrayList<String>();

			cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
			cdt.addCondition(AppConditions.REGION, provinceList);
			cdt.addCondition(AppConditions.TAG, tagList);
			message.setConditions(cdt);

			IPushResult ret = push.pushMessageToApp(message, "任务别名_toApp");
			return ret.getResponse().toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failure";
		}

	}

	@SuppressWarnings("deprecation")
	public static LinkTemplate linkTemplateDemo(String day,String content) {
		
		LinkTemplate template = new LinkTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTitle(day);
		template.setText(content);
		template.setLogo("icon.png");
		template.setLogoUrl("");
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		template.setUrl("http://www.baidu.com");

		return template;
	}

}
