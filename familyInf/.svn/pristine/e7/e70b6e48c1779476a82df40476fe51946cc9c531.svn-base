package com.nn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Shenqing;
import com.nn.bean.User;
import com.nn.service.ShenqingService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("shenqing")
public class ShenqingController extends BaseController{
	
	@Autowired
	private ShenqingService shenqingService;
	
	//新增
	@ResponseBody
	@RequestMapping("add")
	public Msg add(Shenqing shenqing,HttpServletRequest request,String user2id){
		User user1 = getCurrentUser(request);
		if(shenqingService.add(user1,user2id)){
			return Msg.success().add("result", "已提交请求");
		}else {
			return Msg.fail().add("result", "提交请求失败");
		}
	}
	
	
	
	
	
	//修改
	//只有接收人才有权限修改申请状态
	@ResponseBody
	@RequestMapping("update")
	public Msg update(HttpServletRequest request,Shenqing shenqing){	
		if(shenqingService.update(shenqing)){
			return Msg.success().add("result", "成功");
		}else {
			return Msg.fail().add("result", "失败");
		}
	}
	
	
	
	
	//修改
	//只有接收人才有权限修改申请状态
	@ResponseBody
	@RequestMapping("refuse")
	public Msg refuse(Shenqing shenqing,String id){		
		if(shenqingService.refuse(shenqing,id)){
			return Msg.success().add("result", "成功");
		}else {
			return Msg.fail().add("result", "失败");
		}
	}
	
	
	//查询详情
	@ResponseBody
	@RequestMapping("get")
	public Msg get(Shenqing shenqing){
		return Msg.success().add("result", shenqingService.get(shenqing));
	}
	
	//申请列表
	@ResponseBody
	@RequestMapping("list")
	public Msg list(HttpServletRequest request){
			User user = getCurrentUser(request);           //获取当前用户
		return Msg.success().add("result", shenqingService.list(user));
	}	
	
	//审核列表
	@RequestMapping("list2")
	@ResponseBody
	public Msg list2(HttpServletRequest request){
		User user2 = getCurrentUser(request);
		return Msg.success().add("result", shenqingService.list2(user2));
		
	}
}
