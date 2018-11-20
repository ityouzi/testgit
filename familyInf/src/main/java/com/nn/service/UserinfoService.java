package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Userinfo;
import com.nn.bean.UserinfoExample;
import com.nn.bean.UserinfoExample.Criteria;
import com.nn.dao.UserinfoMapper;
import com.nn.utils.MyTools;

@Service
public class UserinfoService {
	
	@Autowired
	private UserinfoMapper userinfoMapper;
	
	//新增
	public boolean add(Userinfo userinfo) {
		try {
			userinfo.setId(MyTools.getDateR());
			userinfoMapper.insertSelective(userinfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//通过id修改用户信息
	public boolean update(Userinfo userinfo) {
		try {
			String userid = userinfo.getUserid();
			UserinfoExample example = new UserinfoExample();
			Criteria criteria = example.createCriteria();
			criteria.andUseridEqualTo(userid);
			userinfoMapper.updateByExampleSelective(userinfo, example);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//通过用户id查询用户信息
	public Userinfo get(Userinfo userinfo) {
		String userid = userinfo.getUserid();
		UserinfoExample example = new UserinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<Userinfo> list = userinfoMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

	
}
