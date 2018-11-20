package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Activity;
import com.nn.bean.Reply;
import com.nn.bean.ReplyExample;
import com.nn.bean.User;
import com.nn.dao.ActivityMapper;
import com.nn.dao.ReplyMapper;
import com.nn.dao.UserMapper;
import com.nn.utils.Msg;
import com.nn.utils.MyTools;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ActivityMapper activityMapper;

	//新增应邀
	public boolean add2(Reply reply, User user) {
		try {
			ReplyExample example = new ReplyExample();
			ReplyExample.Criteria criteria = example.createCriteria();
			criteria.andActIdEqualTo(reply.getActId());
			criteria.andCreatorEqualTo(user.getUserid());
			List<Reply> list = replyMapper.selectByExample(example);
			if(list.size()>0){
				String replyId = list.get(0).getReplyId();
				reply.setReplyId(replyId);
				replyMapper.updateByPrimaryKeySelective(reply);
				return true;
			}else{
				reply.setCreator(user.getUserid());
				reply.setReplyId(MyTools.getDateR());					//主键ID
				reply.setCreatetime(MyTools.getTime());					//时间
				replyMapper.insertSelective(reply);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	

//	public boolean del(String replyId) {
//		try {
//			replyMapper.deleteByPrimaryKey(replyId);
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}	
//	}
//
//	public boolean update(Reply reply) {
//		try {
//			replyMapper.updateByPrimaryKeySelective(reply);
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}		
//	}
//
//	public Object get(String replyId) {		
//		return replyMapper.selectByPrimaryKey(replyId);
//	}
	
	//活动应邀人列表
	public List<Reply> list(Reply reply) {
		String actId = reply.getActId();
		String status = reply.getStatus();
		ReplyExample example = new ReplyExample();
		ReplyExample.Criteria criteria = example.createCriteria();
		criteria.andActIdEqualTo(actId);
//		criteria.andStatusEqualTo(status);
		example.setOrderByClause("createTime DESC");			//时间降序
		List<Reply> list = replyMapper.selectByExample(example);
		for(Reply r : list){
			User user = userMapper.selectByPrimaryKey(r.getCreator());
			r.setUser(user);
		}
		return list;
	}
	
	//我的活动应邀列表
	public List<Reply> myReplyList(Reply reply) {
		ReplyExample example = new ReplyExample();
		ReplyExample.Criteria criteria = example.createCriteria();
		criteria.andCreatorEqualTo(reply.getCreator());
		criteria.andStatusEqualTo(reply.getStatus());
		example.setOrderByClause("createTime DESC");			//时间降序
		List<Reply> list = replyMapper.selectByExample(example);
		return list;
	}

	public Reply getreply(String replyId) {
		return replyMapper.selectByPrimaryKey(replyId);
	}
	
	

	
}
