package com.nn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Friend;
import com.nn.bean.User;
import com.nn.service.FriendService;
import com.nn.utils.Msg;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

@RequestMapping("friend")
@Controller
public class FriendController extends BaseController{
	
	@Autowired
	private FriendService friendService;
	
	//新增
	@RequestMapping("add")
	@ResponseBody
	public Msg addFriend(Friend friend,HttpServletRequest request){
		if(friendService.add(friend)){
			return Msg.success().add("result", "成功");
		}else{
			return Msg.fail().add("result", "失败");
		}		
	}	
	
	//列表
	@RequestMapping("list")
	@ResponseBody
	public Msg list(HttpServletRequest request){
		User user1 = getCurrentUser(request);
		return Msg.success().add("result", friendService.list(user1));
		
	}

	//删除
	@RequestMapping("del")
	@ResponseBody
	public Msg del(HttpServletRequest request,String userid){
		User user1 = getCurrentUser(request);
		if(friendService.del(user1,userid)){
			return Msg.success().add("result", "删除成功");
		}else{
			return Msg.fail().add("result", "删除失败");
		}
		
		
	}
	

}
