package com.nn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Biaoqian;
import com.nn.bean.User;
import com.nn.service.BiaoqianService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("biaoqian")
public class BiaoqianController	extends BaseController{

	@Autowired
	private BiaoqianService biaoqianService;
	
	//新增
	@ResponseBody
	@RequestMapping("add")
	public Msg add(Biaoqian biaoqian,HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		if(biaoqianService.add(biaoqian,currentUser)){
			return Msg.success().add("result", "添加成功");
		
		}else{
			return Msg.fail().add("result", "添加失败");
		}
	}
	
	//删除
	@ResponseBody
	@RequestMapping("delete")
	public Msg delete(Biaoqian biaoqian,HttpServletRequest request){
		User currentUser = getCurrentUser(request);
		if(biaoqianService.delete(biaoqian,currentUser)){
			return Msg.success().add("result", "删除成功");
		}else{
			return Msg.fail().add("result", "删除失败");
		}
	}
	
	//列表
	@ResponseBody
	@RequestMapping("list")
	public Msg list(Biaoqian biaoqian){
		return Msg.success().add("result", biaoqianService.list(biaoqian));
	}
}
