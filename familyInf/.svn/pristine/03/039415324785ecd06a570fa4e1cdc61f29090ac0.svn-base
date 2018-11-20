package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Festival;
import com.nn.bean.FestivalExample;
import com.nn.dao.FestivalMapper;
@Service
public class FestivalService {
	@Autowired
	private FestivalMapper festivalMapper;
	public boolean add(Festival festival) {
		try {
			festivalMapper.insert(festival);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean delete(String festivalId) {
		try {
			int id = Integer.parseInt(festivalId);
			festivalMapper.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Festival festival) {
		try {
			festivalMapper.updateByPrimaryKeySelective(festival);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Festival get(String festivalId) {
		
			int id = Integer.parseInt(festivalId);
			
			return festivalMapper.selectByPrimaryKey(id);
		
	}

	public List<Festival> list(Festival festival) {
		FestivalExample example=new FestivalExample();
		FestivalExample.Criteria criteria=example.createCriteria();
		String date=festival.getDate();
		String festivalname=festival.getFestivalname();
		if (null != date && !"" .equals(date)) {
			criteria.andDateEqualTo(date);
		}
		if (null != festivalname && !"" .equals(festivalname)) {
			criteria.andFestivalnameEqualTo(festivalname);
		}
		return festivalMapper.selectByExample(example);
	}

	public List<Festival> getFesByDay(String day,String afterDay) {
		FestivalExample example=new FestivalExample();
		FestivalExample.Criteria criteria=example.createCriteria();
		criteria.andDateBetween(day, afterDay);
		List<Festival> list = festivalMapper.selectByExample(example);
		return list;
	}

	public List<Festival> listAll() {
		FestivalExample example=new FestivalExample();
		return festivalMapper.selectByExample(example);
	}

}
