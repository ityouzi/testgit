package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Pingfen;
import com.nn.bean.PingfenExample;
import com.nn.bean.PingfenExample.Criteria;
import com.nn.dao.PingfenMapper;

@Service
public class PingfenService {
	
	@Autowired
	private PingfenMapper pingfenMapper;
	
	//查询项目的所有评价
	public List<Pingfen> list(Pingfen pingfen) {
		String projectid = pingfen.getProjectid();
		PingfenExample example = new PingfenExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(projectid);
		List<Pingfen> list = pingfenMapper.selectByExample(example);
		return list;
	}

	//项目平均分
	public Pingfen listByGroup(Pingfen pingfen) {
		String projectid = pingfen.getProjectid();
		PingfenExample example = new PingfenExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectidEqualTo(projectid);
		List<Pingfen> list = pingfenMapper.selectByExample(example);
		 double count = 0;
		for(Pingfen p:list){
			double i = Double.parseDouble(p.getPingfen());
			count+=i;
		}
		double avg = count/list.size();
		String s = String.valueOf(avg);
		pingfen.setPingfen(s);
		return pingfen;
	}
	
	
}
