package com.nn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Lingqian;
import com.nn.service.LingqianService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("lingqian")
public class LingqianController {
	
	@Autowired
	private LingqianService lingqianService;
	
	//id查询
	@ResponseBody
	@RequestMapping("get")
	public Msg get(Lingqian lingqian){
		return Msg.success().add("result", lingqianService.get(lingqian));
	}
	
	//列表
	@ResponseBody
	@RequestMapping("list")
	public Msg list(Lingqian lingqian){
		return Msg.success().add("result", lingqianService.list(lingqian));
	}
}
