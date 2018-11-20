package com.nn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Pingfen;
import com.nn.service.PingfenService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("pingfen")
public class PingfenController {
	
	@Autowired
	private PingfenService pingfenService;
	
	//每个项目的平均分
	@ResponseBody
	@RequestMapping("listByGroup")
	public Msg listByGroup(Pingfen pingfen){
		return Msg.success().add("result", pingfenService.listByGroup(pingfen));
	}
	
	//查询所有评价
	@ResponseBody
	@RequestMapping("list")
	public Msg list(Pingfen pingfen){
		return Msg.success().add("result", pingfenService.list(pingfen));
	}
}
