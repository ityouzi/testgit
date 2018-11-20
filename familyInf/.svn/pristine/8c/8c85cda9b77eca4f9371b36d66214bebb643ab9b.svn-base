package com.nn.service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Friend;
import com.nn.bean.FriendExample;
import com.nn.bean.Shenqing;
import com.nn.bean.ShenqingExample;
import com.nn.bean.User;
import com.nn.bean.UserExample;
import com.nn.dao.FriendMapper;
import com.nn.dao.ShenqingMapper;
import com.nn.dao.UserMapper;
import com.nn.utils.MyTools;

@Service
public class FriendService {
	@Autowired
	private FriendMapper friendMapper;
	@Autowired
	private ShenqingMapper shenqingMapper;
	@Autowired
	private UserMapper userMapper;

	//添加方法
	public boolean add(Friend friend) {
		try {						
			friendMapper.insertSelective(friend);
			return true;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//我的朋友列表
	public List<User> list(User user1) {						
		String userid = user1.getUserid();				//登录人id
		FriendExample example = new FriendExample();
		FriendExample.Criteria criteria = example.createCriteria();
		criteria.andUser1idEqualTo(userid);				//登录人是申请人	
		criteria.andCwEqualTo("已通过");
		
		FriendExample.Criteria criteria2 = example.createCriteria();
		criteria2.andUser2idEqualTo(userid);			//登录人是审核人		
		criteria2.andCwEqualTo("已通过");
		
		example.or(criteria2);
		List<Friend> list = friendMapper.selectByExample(example);
		
		List<User> listusers = new ArrayList<User>();		
		for(Friend f:list){
			if(f.getUser1id().equals(userid)){
				User user = userMapper.selectByPrimaryKey(f.getUser2id());
				listusers.add(user);				
			}
			if (f.getUser2id().equals(userid)) {
				User user = userMapper.selectByPrimaryKey(f.getUser1id());
				listusers.add(user);
			}
		}
		return listusers;
	}

	//删除指定朋友关系
	public boolean del(User user1, String userid) {
		String userid1 = user1.getUserid();									//当前登录用户
		try {
			FriendExample example = new FriendExample();					//删除朋友数据表
			FriendExample.Criteria criteria = example.createCriteria();
			criteria.andUser1idEqualTo(userid1);
			criteria.andUser2idEqualTo(userid);
			FriendExample.Criteria criteria1 = example.createCriteria();
			criteria1.andUser1idEqualTo(userid);
			criteria1.andUser2idEqualTo(userid1);
			example.or(criteria1);
			friendMapper.deleteByExample(example);
			
			ShenqingExample sqExample = new ShenqingExample();				//删除申请记录关系表
			ShenqingExample.Criteria sqCriteria1 = sqExample.createCriteria();
			sqCriteria1.andUseroneidEqualTo(userid1);
			sqCriteria1.andUsertwoidEqualTo(userid);
			ShenqingExample.Criteria sqCriteria2 = sqExample.createCriteria();
			sqCriteria2.andUseroneidEqualTo(userid);
			sqCriteria2.andUsertwoidEqualTo(userid1);
			sqExample.or(sqCriteria2);
			shenqingMapper.deleteByExample(sqExample);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
