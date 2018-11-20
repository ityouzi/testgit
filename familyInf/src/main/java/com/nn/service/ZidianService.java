package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Zidian;
import com.nn.bean.ZidianExample;
import com.nn.bean.ZidianWithBLOBs;
import com.nn.dao.ZidianMapper;

@Service
public class ZidianService {
	
	@Autowired
	private ZidianMapper zidianMapper;
	
	//id查询
	public Zidian get(Zidian zidian) {
		return zidianMapper.selectByPrimaryKey(zidian.getId());
	}
	
	//汉字查询
	public ZidianWithBLOBs add(ZidianWithBLOBs zidian) {
		String wenzi = zidian.getWenzi();
		ZidianExample example = new ZidianExample();
		ZidianExample.Criteria criteria = example.createCriteria();
		criteria.andWenziEqualTo(wenzi);
		List<ZidianWithBLOBs> list = zidianMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	//获取汉字
	public List<ZidianWithBLOBs> list(String xb){
		ZidianExample example = new ZidianExample();
		ZidianExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(xb);
		return zidianMapper.selectByExampleWithBLOBs(example);
	}

	public void update(ZidianWithBLOBs list) {
		zidianMapper.updateByPrimaryKeySelective(list);
	}
}
