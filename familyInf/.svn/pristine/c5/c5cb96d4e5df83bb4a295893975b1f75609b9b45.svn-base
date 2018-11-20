package com.nn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Simiao;
import com.nn.service.SimiaoService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("simiao")
public class SimiaoController {
	
	@Autowired
	private SimiaoService simiaoService;
	
	//id查询
	@ResponseBody
	@RequestMapping("get")
	public Msg get(Simiao simiao){
		return Msg.success().add("result", simiaoService.get(simiao));
	}
	
	//列表
	@ResponseBody
	@RequestMapping("list")
	public Msg list(Simiao simiao){
		return Msg.success().add("result", simiaoService.list(simiao));
	}
}
