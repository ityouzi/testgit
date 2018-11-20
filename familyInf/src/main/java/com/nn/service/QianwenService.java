package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Qianwen;
import com.nn.bean.QianwenExample;
import com.nn.bean.QianwenWithBLOBs;
import com.nn.dao.QianwenMapper;

@Service
public class QianwenService {
	
	@Autowired
	private QianwenMapper qianwenMapper;
	
	//新增签文
	public boolean add(QianwenWithBLOBs qianwen) {
		try {
			qianwenMapper.insertSelective(qianwen);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//列表
	public List<QianwenWithBLOBs> list(QianwenWithBLOBs qianwen) {
		QianwenExample example = new QianwenExample();
		QianwenExample.Criteria criteria = example.createCriteria();
		List<QianwenWithBLOBs> list = qianwenMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	
	//更新
	public boolean update(QianwenWithBLOBs qianwen) {
		try {
			qianwenMapper.updateByPrimaryKeyWithBLOBs(qianwen);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//主键查询
	public QianwenWithBLOBs get(QianwenWithBLOBs qianwen) {
		return qianwenMapper.selectByPrimaryKey(qianwen.getId());
	}
	
	//抽签
	public Qianwen listone(Qianwen qianwen) {
		String qianid = qianwen.getQianid();
		QianwenExample example = new QianwenExample();
		QianwenExample.Criteria criteria = example.createCriteria();
		criteria.andQianidEqualTo(qianid);
		List<QianwenWithBLOBs> list = qianwenMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			String status = list.get(0).getStatus();		//去除标签		
			String str_status = status.substring(3, status.length()-4);
			String title = list.get(0).getTitle();
			String title1 = title.substring(3, title.length()-4);
			String str_title = title1.substring(10, title1.length()-9);
			
			QianwenWithBLOBs qianwenWithBLOBs = list.get(0);
			qianwenWithBLOBs.setStatus(str_status);
			qianwenWithBLOBs.setTitle(str_title);
			return qianwenWithBLOBs;
		}
		return null;
	}
	
	
}
