package com.nn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Memorialday;
import com.nn.bean.User;
import com.nn.service.MemorialdayService;
import com.nn.utils.Msg;

//纪念日
@Controller
@RequestMapping("memorialday")
public class MemorialdayController extends BaseController {
	
	@Autowired
	private MemorialdayService memorialdayService;
	
	
	//添加
	@RequestMapping("add")
	@ResponseBody
	public Msg add(Memorialday memorialday, HttpServletRequest request){
		User user = getCurrentUser(request);
		if(memorialdayService.add(memorialday,user)){
			return Msg.success().add("result", "添加成功");
		}
		return Msg.fail().add("result", "添加失败");
		
	}
	
	//删除
	@RequestMapping("del")
	@ResponseBody
	public Msg del(Memorialday memorialday){
		if(memorialdayService.del(memorialday)){
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");		
	}
	
	//修改
	@RequestMapping("update")
	@ResponseBody
	public Msg update(Memorialday memorialday){
		if(memorialdayService.update(memorialday)){
			return Msg.success().add("result", "修改成功");
		}
		return Msg.fail().add("result", "失败");
		
	}
	
	//查询
	@RequestMapping("get")
	@ResponseBody
	public Msg get(Memorialday memorialday){
		return Msg.success().add("result", memorialdayService.get(memorialday));
		
	}
	
	//列表
	@RequestMapping("list")
	@ResponseBody
	public Msg list(Memorialday memorialday,HttpServletRequest request){
		User user = getCurrentUser(request);
		return Msg.success().add("result", memorialdayService.list(memorialday,user));		
	}
	//日历结婚纪念日接口
	@RequestMapping("list2")
	@ResponseBody
	public Msg list2(HttpServletRequest request){
		String mouth = request.getParameter("mouth");
		User user = getCurrentUser(request);
		return Msg.success().add("result", memorialdayService.list2(user,mouth));
		
	}

}
