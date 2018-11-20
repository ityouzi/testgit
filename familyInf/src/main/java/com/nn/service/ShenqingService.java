package com.nn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Friend;
import com.nn.bean.FriendExample;
import com.nn.bean.Shenqing;
import com.nn.bean.ShenqingExample;
import com.nn.bean.User;
import com.nn.dao.FriendMapper;
import com.nn.dao.ShenqingMapper;
import com.nn.dao.UserMapper;
import com.nn.utils.MyTools;

@Service
public class ShenqingService{
	
	@Autowired
	private ShenqingMapper shenqingMapper;
	@Autowired
	private FriendMapper friendMapper;
	@Autowired
	private UserMapper userMapper;

	public boolean add(User user1, String user2id) {
		try {
			String user1id = user1.getUserid();
			if(user1id.equals(user2id)){			//不能添加自己为好友
				return false;
			}else{
				//user1id申请人   user2id被申请人
				ShenqingExample example = new ShenqingExample();
				ShenqingExample.Criteria criteria1 = example.createCriteria();
				criteria1.andUseroneidEqualTo(user1id);
				criteria1.andUsertwoidEqualTo(user2id);
				
				ShenqingExample.Criteria criteria2 = example.createCriteria();
				criteria2.andUseroneidEqualTo(user2id);
				criteria2.andUsertwoidEqualTo(user1id);
				
				example.or(criteria2);
				List<Shenqing> list = shenqingMapper.selectByExample(example);
				
				if(list.size() == 0){
					Shenqing shenqing = new Shenqing();
					shenqing.setCreator(user1.getName());
					shenqing.setCreatetime(MyTools.getTime());
					shenqing.setId(MyTools.getDateR());
					shenqing.setStatus("待审核");
					shenqing.setType("好友申请");
					shenqing.setContent("对方想添加您为好友");
					shenqing.setUseroneid(user1id);		  			//申请人ID
					User user = userMapper.selectByPrimaryKey(user2id);
					shenqing.setReceived(user.getName()); 			//接收人
					shenqing.setUsertwoid(user2id);      	 		//被申请人的id
					shenqingMapper.insertSelective(shenqing);
					return true;
				}else{
					Shenqing shenqing = list.get(0);
					String status = shenqing.getStatus();
					String creator = shenqing.getCreator();			//申请人
					String received = shenqing.getReceived();		//被申请人呢
					String useroneid = shenqing.getUseroneid();		//申ID
					String usertwoid = shenqing.getUsertwoid();		//被申ID
					if(status.equals("已拒绝")&&user1id.equals(useroneid)){			
						shenqing.setStatus("待审核");
						shenqing.setYl1("");
						shenqing.setCreatetime(MyTools.getTime());
						shenqingMapper.updateByPrimaryKeySelective(shenqing);
						return true;
					}else if(status.equals("已拒绝")&& user1id.equals(usertwoid)){	//角色互换
						shenqing.setStatus("待审核");
						shenqing.setYl1("");
						shenqing.setCreatetime(MyTools.getTime());
						shenqing.setCreator(received);
						shenqing.setReceived(creator);
						shenqing.setUseroneid(usertwoid);
						shenqing.setUsertwoid(useroneid);
						shenqingMapper.updateByPrimaryKeySelective(shenqing);
						return true;
					}else{
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//同意加好友
	public boolean update(Shenqing shenqing) {
		try {			
			shenqing.setStatus("已通过");
			shenqingMapper.updateByPrimaryKeySelective(shenqing);	
			Shenqing sq = shenqingMapper.selectByPrimaryKey(shenqing.getId());
			Friend friend = new Friend();				//同意后加一条好友数据
			friend.setFid(MyTools.getDateR());
			friend.setUser1id(sq.getUseroneid());		//申请人
			friend.setUser2id(sq.getUsertwoid());		//被申请人
			friend.setCw("已通过");
			friendMapper.insertSelective(friend);		//添加朋友表
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	//拒接加好友
	public boolean refuse(Shenqing shenqing,String id) {
		try {			
			shenqing.setStatus("已拒绝");
			shenqingMapper.updateByPrimaryKeySelective(shenqing);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//查询详情
	public Shenqing get(Shenqing shenqing) {
		return shenqingMapper.selectByPrimaryKey(shenqing.getId());
	}
	
	//申请   
	public List<Shenqing> list(User user) {
		ShenqingExample example = new ShenqingExample();
		ShenqingExample.Criteria criteria = example.createCriteria();
		criteria.andUseroneidEqualTo(user.getUserid());
		List<Shenqing> list = shenqingMapper.selectByExample(example);
		return list;
	}
	
	
	//调用合并方法	
//	public boolean panduan(List<Shenqing> list, User user){
//		for(Shenqing s:list){
//			String status = s.getStatus();			
//			
//			if(status.equals("通过")){
//				userService.hebin(user);											
//				return true;
//			}else{
//				return false;
//			}
//		}
//		
//		return false;
//		
//	}

	
	//审核列表
	public List<Shenqing> list2(User user2) {
		ShenqingExample example2 = new ShenqingExample();
		ShenqingExample.Criteria criteria2 = example2.createCriteria();	
		criteria2.andUsertwoidEqualTo(user2.getUserid());
		criteria2.andStatusEqualTo("待审核");
		return shenqingMapper.selectByExample(example2);
	}

	


	
}
