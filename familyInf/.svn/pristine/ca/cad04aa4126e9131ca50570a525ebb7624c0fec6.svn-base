package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Simiao;
import com.nn.bean.SimiaoExample;
import com.nn.bean.SimiaoExample.Criteria;
import com.nn.dao.SimiaoMapper;

@Service
public class SimiaoService {
	
	@Autowired
	private SimiaoMapper simiaoMapper;
	
	//id查询
	public Simiao get(Simiao simiao) {
		return simiaoMapper.selectByPrimaryKey(simiao.getId());
	}

	
	//寺庙列表
	public List<Simiao> list(Simiao simiao) {
		String provice = simiao.getProvice();
		String name = simiao.getName();
		String city = simiao.getCity();
		SimiaoExample example = new SimiaoExample();
		Criteria criteria = example.createCriteria();
		if(provice!=null && !"".equals(provice)){
			criteria.andProviceEqualTo(provice);
		}
		if(name!=null && !"".equals(name)){
			criteria.andNameLike("%"+name+"%");
		}
		if(city!=null && !"".equals(city)){
			criteria.andCityEqualTo(city);
		}
		List<Simiao> list = simiaoMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	
	
}	
