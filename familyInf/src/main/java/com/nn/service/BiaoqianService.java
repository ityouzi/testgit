package com.nn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Biaoqian;
import com.nn.bean.BiaoqianExample;
import com.nn.bean.BiaoqianExample.Criteria;
import com.nn.bean.User;
import com.nn.dao.BiaoqianMapper;
import com.nn.utils.MyTools;

@Service
public class BiaoqianService {
	
	@Autowired
	private BiaoqianMapper biaoqianMapper;

	public boolean add(Biaoqian biaoqian,User currentUser) {
		String id = MyTools.getDateR();
		biaoqian.setId(id);
		String time = MyTools.getTime();
		biaoqian.setTime(time);
		String name = currentUser.getName();
		biaoqian.setCreator(name);
		try {
			biaoqianMapper.insertSelective(biaoqian);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Biaoqian biaoqian, User currentUser) {
		try {
			if(currentUser.getName().equals(biaoqian.getTarget())){
				biaoqianMapper.deleteByPrimaryKey(biaoqian.getId());
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Biaoqian> list(Biaoqian biaoqian) {
		String target = biaoqian.getTarget();
		BiaoqianExample example = new BiaoqianExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTargetEqualTo(target);
		return biaoqianMapper.selectByExample(example);
	}
	
	
}
