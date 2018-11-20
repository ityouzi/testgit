package com.nn.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Relation;
import com.nn.service.RelationsService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("relation")
public class RelationController {
	
	@Autowired
	RelationsService relationService;
	
	@RequestMapping("del")
	@ResponseBody
	public Msg delRelation(Relation r){
		if(relationService.delRelation(r)){
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");	
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Msg updateRelation(Relation r){
		if(relationService.updateRelation(r)){
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");
	}
	
	@RequestMapping("get")
	@ResponseBody
	public Msg getRelation(String user1){
		return Msg.success().add("result", relationService.getRelation(user1));
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Msg listRelation(Relation r){
		return Msg.success().add("result", relationService.listRelation(r));
	}
	
	@RequestMapping("listone")				//饼状图关系
	@ResponseBody
	public Msg oneRelation(Relation r){
		 Map map = relationService.oneRelation(r);
		return Msg.success().add("result", map);
	}
}
