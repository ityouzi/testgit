package com.nn.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Relation;
import com.nn.bean.RelationExample;
import com.nn.bean.User;
import com.nn.dao.RelationMapper;
import com.nn.dao.UserMapper;

@Service
public class RelationsService {
	
	@Autowired
	private RelationMapper relationMapper;
	@Autowired
	private UserMapper userMapper;

	public boolean delRelation(Relation r) {
		try{
			relationMapper.deleteByPrimaryKey(r.getRelationId());
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public Relation getRelation(String r) {
		return relationMapper.selectByPrimaryKey(r);
	}

public List<Relation> listRelation(Relation r) {	
		
		RelationExample example = new RelationExample();
		RelationExample.Criteria criteria = example.createCriteria();
		criteria.andUser1EqualTo(r.getUser1());
		List<Relation> list = relationMapper.selectByExample(example);
		for (Relation relation : list) {
			User user = userMapper.selectByPrimaryKey(relation.getUser2());
			relation.setUserMsg(user);
		}
		
		/*
		List<String> dsList = new ArrayList<String>();
		
		for(Relation relation : list){
			String dString = relation.getYl1();
			if(!dsList.contains(dString)){
				dsList.add(dString);
			}
		}
		
		
		
		for(String dsds : dsList){
			List<User> woList = new ArrayList<User>();
			for (Relation relation : list) {
				if(relation.getYl1().equals(dsds)){
					woList.add(userMapper.selectByPrimaryKey(relation.getUser2()));
				}
			}
			map.put(dsds, woList);
		}*/
		
		return list;
	}


	

	public boolean updateRelation(Relation r) {
		try{
			relationMapper.updateByPrimaryKeySelective(r);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public Map oneRelation(Relation r) {
		Map map = new HashMap();
		RelationExample example = new RelationExample();
		RelationExample.Criteria criteria = example.createCriteria();
		criteria.andUser1EqualTo(r.getUser1());
		criteria.andGuanxiEqualTo("1"); 					//饼状图显示关系为“1”的
		List<Relation> list = relationMapper.selectByExample(example);
		
		List<User> ggList = new ArrayList<User>();
		List<User> ddList = new ArrayList<User>();
		List<User> jjList = new ArrayList<User>();
		List<User> mmList = new ArrayList<User>();
		List<User> bbList = new ArrayList<User>();
		List<User> maList = new ArrayList<User>();
		List<User> ezList = new ArrayList<User>();
		List<User> nrList = new ArrayList<User>();
		List<User> lpList = new ArrayList<User>();
		
		for (Relation relation : list) {
			if(relation.getUser2cw().equals("哥哥")){
				User gg = userMapper.selectByPrimaryKey(relation.getUser2());
				String ggage = getage(gg.getBirthday());
				gg.setAge(ggage);
				ggList.add(gg);
			}
			if(relation.getUser2cw().equals("弟弟")){
				User dd = userMapper.selectByPrimaryKey(relation.getUser2());
				String ddage = getage(dd.getBirthday());
				dd.setAge(ddage);
				ddList.add(dd);
			}
			if(relation.getUser2cw().equals("姐姐")){
				User jj = userMapper.selectByPrimaryKey(relation.getUser2());
				String jjage = getage(jj.getBirthday());
				jj.setAge(jjage);
				jjList.add(jj);
			}
			if(relation.getUser2cw().equals("妹妹")){
				User mm = userMapper.selectByPrimaryKey(relation.getUser2());
				String mmage = getage(mm.getBirthday());
				mm.setAge(mmage);
				mmList.add(mm);
			}
			if(relation.getUser2cw().equals("爸爸")){
				User bb = userMapper.selectByPrimaryKey(relation.getUser2());
				String bbage = getage(bb.getBirthday());
				bb.setAge(bbage);
				bbList.add(bb);
			}
			if(relation.getUser2cw().equals("妈妈")){
				User ma = userMapper.selectByPrimaryKey(relation.getUser2());
				String maage = getage(ma.getBirthday());
				ma.setAge(maage);
				maList.add(ma);
			}
			if(relation.getUser2cw().equals("儿子")){
				User ez = userMapper.selectByPrimaryKey(relation.getUser2());
				String ezage = getage(ez.getBirthday());
				ez.setAge(ezage);
				ezList.add(ez);
			}
			if(relation.getUser2cw().equals("女儿")){
				User nr = userMapper.selectByPrimaryKey(relation.getUser2());
				String nrage = getage(nr.getBirthday());
				nr.setAge(nrage);
				nrList.add(nr);
			}
			if(relation.getUser2cw().equals("老婆") || relation.getUser2cw().equals("老公")){
				User lp = userMapper.selectByPrimaryKey(relation.getUser2());
				String lpage = getage(lp.getBirthday());
				lp.setAge(lpage);
				lpList.add(lp);
			}
		}
		
		map.put("gg", ggList);
		map.put("dd", ddList);
		map.put("jj", jjList);
		map.put("mm", mmList);
		map.put("ba", bbList);
		map.put("ma", maList);
		map.put("ez", ezList);
		map.put("nr", nrList);
		map.put("po", lpList);

		return map;
	}
	
	
	public String getage(String brithday) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			String currentTime = formatDate.format(calendar.getTime());
			Date today = formatDate.parse(currentTime);
			Date brithDay = formatDate.parse(brithday);
			Integer age = today.getYear() - brithDay.getYear();
			if(age<0){
				return null;
			}else {
				return age.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
}
