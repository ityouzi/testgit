package com.nn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Activity;
import com.nn.bean.ActivityExample;
import com.nn.bean.Friend;
import com.nn.bean.FriendExample;
import com.nn.bean.User;
import com.nn.dao.ActivityMapper;
import com.nn.dao.FriendMapper;
import com.nn.dao.ReplyMapper;
import com.nn.utils.MyTools;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private FriendMapper friendMapper;

	public boolean add(Activity activity,User user) {
		try {
			activity.setActivityId(MyTools.getDateR());
			activity.setCreatetime(MyTools.getTime());
			activity.setCreator(user.getName());
			activity.setYl1(user.getYl1());
			activity.setYl2(user.getUserid());  	//创建人ID
			activityMapper.insertSelective(activity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Activity get(String activityId) {		
		return activityMapper.selectByPrimaryKey(activityId);
	}

	public List<Activity> list(User user) {
		
		//查询我的朋友发布的活动
		FriendExample friendExample = new FriendExample();
		friendExample.or().andUser1idEqualTo(user.getUserid());
		friendExample.or().andUser2idEqualTo(user.getUserid());
		List<Friend> list2 = friendMapper.selectByExample(friendExample);
		List<String> str = new ArrayList<String>();			//查询我的朋友
		for(Friend f:list2){
			String user1id = f.getUser1id();
			String user2id = f.getUser2id();
			if(user.getUserid().equals(user1id)){
				str.add(user2id);
			}
			if(user.getUserid().equals(user2id)){
				str.add(user1id);
			}
		}
		System.err.println(str.size());
		List<Activity> act_list = new ArrayList<Activity>();//存放朋友的活动List
		for(int i=0;i<str.size();i++){
			String actid = str.get(i);
			ActivityExample example1 = new ActivityExample();
			ActivityExample.Criteria criteria = example1.createCriteria();
			criteria.andYl2EqualTo(actid);
			example1.setOrderByClause("createTime DESC");
			List<Activity> selectByExample = activityMapper.selectByExample(example1);
			act_list.addAll(selectByExample);
		}
		System.err.println(act_list.size());
		//查看亲族发布的活动
		ActivityExample example = new ActivityExample();
		String yl1 = user.getYl1();
		String yl7 = user.getYl7();
		if(yl1!=null && !"".equals(yl1)){
			example.or().andYl1EqualTo(yl1);
		}
		if(yl7!=null && !"".equals(yl7)){
			example.or().andYl1EqualTo(yl7);
		}
		example.setOrderByClause("createTime DESC");
		List<Activity> list3 = activityMapper.selectByExampleWithBLOBs(example);
		list3.addAll(act_list);				
		return list3;
	}

	public List<Activity> getActByDay(String day,String afterDay) {
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		criteria.andTimeBetween(day, afterDay);
		List<Activity> list = activityMapper.selectByExampleWithBLOBs(example);
		return list;
	}
	
	//查看我发布的活动
		public List<Activity> list2(User user) {
			ActivityExample example = new ActivityExample();
			ActivityExample.Criteria criteria = example.createCriteria();
			criteria.andYl2EqualTo(user.getUserid());
			List<Activity> list = activityMapper.selectByExample(example);
			return list;
		}
	
}
