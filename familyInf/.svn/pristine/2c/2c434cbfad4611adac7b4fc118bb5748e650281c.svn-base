package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Message;
import com.nn.bean.MessageExample;
import com.nn.bean.MessageExample.Criteria;
import com.nn.dao.MessageMapper;
import com.nn.utils.MyTools;

@Service
public class MessageService {
	
	@Autowired
	private MessageMapper messageMapper;

	public boolean add(Message message) {
		try {
			message.setCreatetime(MyTools.getTime());
			messageMapper.insertSelective(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Message message) {
		try {
			messageMapper.updateByPrimaryKeySelective(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Message get(Message message) {
		return messageMapper.selectByPrimaryKey(message.getId());
	}

	public List<Message> list(Message message) {
		String content = message.getContent();
		String status = message.getStatus();
		String title = message.getTitle();
		String type = message.getType();
		MessageExample example = new  MessageExample();
		Criteria createCriteria = example.createCriteria();
		if (content!=null&&!"".equals(content)) {
			createCriteria.andContentLike("%"+content+"%");
		}
		if (status!=null&&!"".equals(status)) {
			createCriteria.andStatusEqualTo(status);
		}
		if (title!=null&&!"".equals(title)) {
			createCriteria.andContentLike("%"+title+"%");
		}
		if (type!=null&&!"".equals(type)) {
			createCriteria.andTypeEqualTo(type);
		}
		return messageMapper.selectByExample(example);
	}



	
}
