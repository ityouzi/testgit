package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Lingqian;
import com.nn.bean.LingqianExample;
import com.nn.bean.LingqianExample.Criteria;
import com.nn.dao.LingqianMapper;

@Service
public class LingqianService {
	
	@Autowired
	private LingqianMapper lingqianMapper;
	
	//id查询
	public Lingqian get(Lingqian lingqian) {
		return lingqianMapper.selectByPrimaryKey(lingqian.getId());
	}

	public List<Lingqian> list(Lingqian lingqian) {
		LingqianExample example = new LingqianExample();
		Criteria criteria = example.createCriteria();
		List<Lingqian> list = lingqianMapper.selectByExample(example);
		return list;
	}
	
	
}
