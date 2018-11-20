package com.nn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Memorialday;
import com.nn.bean.MemorialdayExample;
import com.nn.bean.MemorialdayExample.Criteria;
import com.nn.bean.User;
import com.nn.dao.MemorialdayMapper;
import com.nn.utils.Msg;
import com.nn.utils.MyTools;

@Service
public class MemorialdayService {
	
	@Autowired
	private MemorialdayMapper memorialdayMapper;

	public boolean add(Memorialday memorialday, User user) {
		try {
			memorialday.setFid(MyTools.getDateR());
			memorialday.setCreatetime(MyTools.getTime());
			memorialday.setCreater(user.getName());					//获取创建人名字
			memorialdayMapper.insertSelective(memorialday);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}

	public boolean del(Memorialday memorialday) {
		try {
			memorialdayMapper.deleteByPrimaryKey(memorialday.getFid());
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean update(Memorialday memorialday) {
		try {
			memorialdayMapper.updateByPrimaryKeySelective(memorialday);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}

	public Memorialday get(Memorialday memorialday) {
	
		return memorialdayMapper.selectByPrimaryKey(memorialday.getFid());
				
	}

	public List<Memorialday> list(Memorialday memorialday, User user) {
		
		String name = user.getName();
		String festival = memorialday.getFestival();
		MemorialdayExample example = new MemorialdayExample();
		MemorialdayExample.Criteria criteria = example.createCriteria();
		criteria.andCreaterEqualTo(name);
		if(festival!=null && !"".equals(festival)){
			criteria.andFestivalEqualTo(festival);
		}
		return memorialdayMapper.selectByExample(example);
	}
	//纪念日接口
	public List<String> list2(User user, String mouth) {
		String name = user.getName();
		MemorialdayExample example = new MemorialdayExample();
		MemorialdayExample.Criteria criteria = example.createCriteria();
		criteria.andCreaterEqualTo(name);	
		
		List<Memorialday> list = memorialdayMapper.selectByExample(example);
		int mm = Integer.parseInt(mouth)+1;
		String month = "";
		if(mm<10){
			month = "0"+mm;
		}else{
			month = mm+"";
		}
		ArrayList<String> list2 = new ArrayList<String>(); 
		Map<String, String> map = new HashMap<String, String>();
		
		for(Memorialday m: list){
			String f = m.getFestival();
			String [] ff = f.split("-");
			System.out.println(ff[1]);
			if(ff[1].equals(month)){
				String key = ff[1]+ff[2];
				String b = map.get(key);
				if(b==null || "".equals(b)){					
					String content = m.getContent();
					map.put(key, content);	
					String str = key+" "+content;
					list2.add(str);
				}else{
					String content = m.getContent() + "和" + b;
					map.put(key, content);
					String str = key+" "+content;
					list2.add(str);
				}				
			}
		}		
		return list2;
	}

	

}
