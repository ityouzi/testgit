package com.nn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Activity;
import com.nn.bean.Reply;
import com.nn.bean.User;
import com.nn.service.ActivityService;
import com.nn.service.ReplyService;
import com.nn.utils.Msg;

@Controller
@RequestMapping("reply")
public class ReplyController extends BaseController{
	
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ActivityService activityService;
	
	//添加
	@ResponseBody
	@RequestMapping("add")
	public Msg add(Reply reply,HttpServletRequest request){
		User user = getCurrentUser(request);
		if(replyService.add2(reply, user)){
			return Msg.success().add("result", "成功");
		}else{
			return Msg.fail().add("result", "失败");
		}
	}
		

		
		
//	//删除
//	@ResponseBody
//	@RequestMapping("del")
//	public Msg del(Reply reply){
//		if(replyService.del(reply.getReplyId())){
//			return Msg.success().add("result", "删除成功");
//		}else {
//			return Msg.fail().add("result", "删除失败");
//		}		
//	}
	
//	//修改
//	@ResponseBody
//	@RequestMapping("update")
//	public Msg update(Reply reply){
//		if(replyService.update(reply)){
//			return Msg.success().add("result", "修改成功");
//		}else{
//			return Msg.fail().add("result", "修改失败");
//		}		
//	}
	
//	//查询
//	@ResponseBody
//	@RequestMapping("get")
//	public Msg get(Reply reply){
//		return Msg.success().add("result", replyService.get(reply.getReplyId())) ;
//	}	
	
//	//列表
//	@ResponseBody
//	@RequestMapping("alist")
//	public Msg alist(Reply reply){		
//		return Msg.success().add("result", replyService.list(reply));		
//	}
//	
	//查看我的应邀
	@ResponseBody
	@RequestMapping("list")
	public Msg list(Reply reply){	
		List<Activity> alist = new ArrayList<Activity>();
		String type = "应邀";
		reply.setStatus(type);
		List<Reply> list = replyService.myReplyList(reply);
		for (Reply r : list) {
			String actId = r.getActId();
			Activity activity = activityService.get(actId);
			alist.add(activity);
		}                                              
		return Msg.success().add("result", alist);		
	}

	//查看应邀人
	@ResponseBody
	@RequestMapping("replyToActivity")
	public Msg replyToActivity(Reply reply){	
//		reply.setStatus("是");
		List<Reply> list = replyService.list(reply);                                            
		return Msg.success().add("result", list);		
	}

}
