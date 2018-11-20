package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Comment;
import com.nn.bean.CommentExample;
import com.nn.bean.Said;
import com.nn.bean.SaidExample;
import com.nn.bean.User;
import com.nn.dao.CommentMapper;
import com.nn.dao.SaidMapper;
import com.nn.utils.MyTools;
@Service
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	public boolean addComment(Comment comment,User user) {
		try {
			String createTime=MyTools.getTime();
			comment.setCreateTime(createTime);
			comment.setCreator(user.getName());
			commentMapper.insertSelective(comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteComment(String commentId) {
		try {
			commentMapper.deleteByPrimaryKey(commentId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateComment(Comment comment) {
		try {
			commentMapper.updateByPrimaryKeySelective(comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Comment getComment(String commentId) {
		return commentMapper.selectByPrimaryKey(commentId);
	}
	
	public List<Comment> list(Comment comment) {
		String creator = comment.getCreator();
		String psaidid = comment.getPsaidid();
		/*String createTime = comment.getCreateTime();*/
		CommentExample example = new CommentExample();
		CommentExample.Criteria criteria = example.createCriteria();
		/*if(createTime != null && !"".equals(createTime)){
			criteria.andCreateTimeIsNotNull();
		}
		if(start!=null&&!"".equals(start))
		{
			criteria.andCreateTimeGreaterThan(start);
		}
		if(end!=null&&!"".equals(end)){
			criteria.andCreateTimeLessThan(end);
		}*/
		if(creator != null && !"".equals(creator)){
			criteria.andCreatorLike(creator);
		}
		if(psaidid != null && !"".equals(psaidid)){
			criteria.andPsaididLike(psaidid);
		}
		return commentMapper.selectByExample(example);
	}
	
}
