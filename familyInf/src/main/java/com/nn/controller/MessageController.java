package com.nn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Message;
import com.nn.service.MessageService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("message")
public class MessageController extends BaseController{
	
	@Autowired
	private MessageService messageService;
	
	//新增
	@ResponseBody
	@RequestMapping("add")
	public Msg addMessage(Message message){
		if(messageService.add(message)){
			return Msg.success().add("result", "添加成功");
		}else{
			return Msg.fail().add("result", "添加失败");
		}
	}
	
	//更新
	@ResponseBody
	@RequestMapping("update")
	public Msg updateMessage(Message message){
		if(messageService.update(message)){
			return Msg.success().add("result", "修改成功");
		}else{
			return Msg.fail().add("result", "修改失败");
		}
	}
	
	//查询
	@ResponseBody
	@RequestMapping("get")
	public Msg getMessage(Message message){
		return Msg.success().add("result",messageService.get(message));
	}
	
	//列表
	@ResponseBody
	@RequestMapping("list")
	public Msg list(Message message){
		return Msg.success().add("result", messageService.list(message));
	}
}
