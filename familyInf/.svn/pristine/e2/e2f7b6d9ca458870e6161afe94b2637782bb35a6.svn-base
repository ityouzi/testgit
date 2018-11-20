package com.nn.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Comment;
import com.nn.bean.CommentExample;
import com.nn.bean.Relation;
import com.nn.bean.RelationExample;
import com.nn.bean.Said;
import com.nn.bean.SaidExample;
import com.nn.bean.User;
import com.nn.bean.UserExample;
import com.nn.dao.CommentMapper;
import com.nn.dao.RelationMapper;
import com.nn.dao.SaidMapper;
import com.nn.dao.UserMapper;
import com.nn.utils.MyTools;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;
@Service
public class SaidService {
	
//	final String URL = "http://192.168.0.112:8088/file-server/zupu/";
	@Autowired
	private SaidMapper saidMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private RelationMapper relationMapper;
	@Autowired
	private UserMapper userMapper;
	//添加说说
	public boolean addSaid(Said said,User user, String longitudeLatitude, String address) {
		try {
			
			said.setCreateTime(MyTools.getTime());
			said.setCreator(user.getName());
			said.setYl1(user.getYl1());
			said.setYl2(longitudeLatitude);			//地址
			said.setYl3(address);
			saidMapper.insertSelective(said);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//删除说说
//	public boolean deleteSaid(String saidId, User user) {
//		String yl1 = user.getYl1();
//		SaidExample example = new SaidExample();
//		SaidExample.Criteria criteria = example.createCriteria();
//		criteria.andYl1EqualTo(yl1);			//删除的时候只能删除自己的说说
//		try {
//			saidMapper.deleteByPrimaryKey(saidId);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	//更新说说
	public boolean updateSaid(Said said) {
		try {
			saidMapper.updateByPrimaryKeySelective(said);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//查询说说
	public Said getSaid(String saidId) {
		return saidMapper.selectByPrimaryKey(saidId);
	}
	
	//list查询
	public List<Said> list(Said said,User user,String URL) {
		SaidExample example = new SaidExample();			//or多条件查询
		String yl1 = user.getYl1();
		String yl7 = user.getYl7();
		if(yl1!=null && !"".equals(yl1)){
			example.or().andYl1EqualTo(yl1);
		}
		if(yl7!=null && !"".equals(yl7)){
			example.or().andYl1EqualTo(yl7);
		}
		example.setOrderByClause("CREATE_TIME desc");       //说说时间排序		
		List<Said> list=saidMapper.selectByExample(example);//查询出好友的说说
		
		//遍历出说说的每条说说评论
		for(Said s : list){						
			List<String> li = new ArrayList<String>();
			if(s.getPictureUrl() != null && !"".equals(s.getPictureUrl())){
				String[] split = s.getPictureUrl().split("[+]");
				for(int i=0; i<split.length; i++){
					String urlPath = URL +split[i];
//					System.err.println(urlPath);
					li.add(urlPath);
				}
			}
			s.setPictureUrls(li);
			String saidId=s.getSaidId();
			CommentExample exampleComment = new CommentExample();
			CommentExample.Criteria criteriaComment = exampleComment.createCriteria();
			criteriaComment.andPsaididEqualTo(saidId);
			exampleComment.setOrderByClause("CREATE_TIME ASC");
			List<Comment> listComment=commentMapper.selectByExample(exampleComment);
			
			for (Comment comment : listComment) {
				String commentid = comment.getPcommentid();
				if( commentid != null  && !"".equals(commentid) && !"null".equals(commentid)){
					String creator = commentMapper.selectByPrimaryKey(commentid).getCreator();
					comment.setRepeople(creator);
				}
			}
			s.setComments(listComment);
		}
		System.err.println(list.size());
		return list;
	}
	
}
