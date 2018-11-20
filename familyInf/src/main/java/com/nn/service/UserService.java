package com.nn.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.bean.Cwdy;
import com.nn.bean.CwdyExample;
import com.nn.bean.Cwgs;
import com.nn.bean.CwgsExample;
import com.nn.bean.Relation;
import com.nn.bean.RelationExample;
import com.nn.bean.SysUser;
import com.nn.bean.SysUserExample;
import com.nn.bean.User;
import com.nn.bean.UserExample;
import com.nn.dao.CwdyMapper;
import com.nn.dao.CwgsMapper;
import com.nn.dao.RelationMapper;
import com.nn.dao.ShenqingMapper;
import com.nn.dao.SysUserMapper;
import com.nn.dao.UserMapper;
import com.nn.utils.CalendarUtil;
import com.nn.utils.MyTools;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CwgsMapper cwgsMapper;
	@Autowired
	private RelationMapper relationMapper;
	@Autowired
	private CwdyMapper cwdyMapper;
	@Autowired
	private ShenqingMapper shenqingMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	private int num = 0;
	
	public boolean addUser(User user, Relation relation, User user22) {
		try {
			String id = MyTools.getDateR();
			user.setUserid(id);
			String yl1 = MyTools.getDateR();
			user.setYl5(user22.getUserid());		//创建人
			user.setYl6(MyTools.getTime());			//创建时间
			user.setStatus("1");
			
			String birthday = user.getBirthday();
			if(birthday !=null && !"".equals(birthday) ){
				String[] split = birthday.split("-");
				String yl2 = split[1]+"-"+split[2];
				String yl8 = CalendarUtil.solarToLunar(birthday);
				user.setYl8(yl8);
				user.setYl2(yl2);
			}
			
			String user1 = relation.getUser1();
			if(user1 != null && !"".equals(user1)){
				User selectByPrimaryKey = userMapper.selectByPrimaryKey(user1);
				user.setYl1(selectByPrimaryKey.getYl1());
				user.setYl7(selectByPrimaryKey.getYl1());		//亲友圈标识
				
				userMapper.insertSelective(user);
				relation.setUser2(id);

				addRelation(relation);
			}else{
				user.setYl1(yl1);
			
				userMapper.insertSelective(user);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	public boolean deleteUser(String userId) {
		try {
			userMapper.deleteByPrimaryKey(userId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//更新
	public boolean updateUser(User user) {
		try {
			String birthday = user.getBirthday();
			if(birthday!=null && !"".equals(birthday)){
				String[] split = birthday.split("-");
				String yl2 = split[1]+"-"+split[2];
				String yl8 = CalendarUtil.solarToLunar(birthday);	//阳历日期转为阴历日期
				user.setYl8(yl8);
				user.setYl2(yl2);
				userMapper.updateByPrimaryKeySelective(user);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getUser(String userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}
	
	public boolean addRelation111(Relation r){
		try{
			relationMapper.insertSelective(r);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean addRelation(Relation r) {		//创建人ID，被创建人ID，创建的称谓
		try {
			
			RelationExample example = new RelationExample();
			RelationExample.Criteria criteria = example.createCriteria();
			criteria.andUser1EqualTo(r.getUser1());						   //创建人的ID
			List<Relation> list = relationMapper.selectByExample(example); // 参照人的所有关系
			User user = userMapper.selectByPrimaryKey(r.getUser1());	   //创建人信息
			String u2 = r.getUser2(); 		// 被创建的人ID

			String u1 = r.getUser1(); 		// 被创建的人的参照人（创建人ID）

			String cw2 = r.getUser2cw(); 	// 参照人对被创建人的称呼（被创建人的称呼）
			String cw1 = ""; 				// 被创建人对参照人的称呼（创建人的称呼）

			String sex = user.getSex();
			System.err.println(sex);

			if (sex.equals("男")) {
				switch (cw2) {
				case "爸爸":
					cw1 = "儿子";
					break;
				case "妈妈":
					cw1 = "儿子";
					break;
				case "姐姐":
					cw1 = "弟弟";
					break;
				case "妹妹":
					cw1 = "哥哥";
					break;
				case "哥哥":
					cw1 = "弟弟";
					break;
				case "弟弟":
					cw1 = "哥哥";
					break;
				case "儿子":
					cw1 = "爸爸";
					break;
				case "女儿":
					cw1 = "爸爸";
					break;
				case "老婆":
					cw1 = "老公";
					break;
				}
			} 
			else {
				switch (cw2) {
				case "爸爸":
					cw1 = "女儿";
					break;
				case "妈妈":
					cw1 = "女儿";
					break;
				case "姐姐":
					cw1 = "妹妹";
					break;
				case "妹妹":
					cw1 = "姐姐";
					break;
				case "哥哥":
					cw1 = "妹妹";
					break;
				case "弟弟":
					cw1 = "姐姐";
					break;
				case "儿子":
					cw1 = "妈妈";
					break;
				case "女儿":
					cw1 = "妈妈";
					break;
				case "老公":
					cw1 = "老婆";
					break;
				}
			}

			Relation relation3 = new Relation(); 		// 参照人对被创建人的关系
			relation3.setRelationId(MyTools.getDateR());
			relation3.setUser1(u2);
			relation3.setUser2(u1);
			relation3.setGuanxi("1");
			if(cw1.equals("爸爸") || cw1.equals("妈妈")){
				relation3.setYl1("1");
			}else if(cw1.equals("儿子") || cw1.equals("女儿")){
				relation3.setYl1("-1");
			}else {
				relation3.setYl1("0");
			}
			relation3.setUser1cw(cw2);
			relation3.setUser2cw(cw1);
			relation3.setYl2(MyTools.getTime());
			relationMapper.insertSelective(relation3);

			Relation relation4 = new Relation(); 		// 被创建人对参照人的关系
			relation4.setRelationId(MyTools.getDateR());
			relation4.setUser1(u1);
			relation4.setUser2(u2);
			relation4.setGuanxi("1");
			if(cw2.equals("爸爸") || cw2.equals("妈妈")){
				relation4.setYl1("1");
			}else if(cw2.equals("儿子") || cw2.equals("女儿")){
				relation4.setYl1("-1");
			}else {
				relation4.setYl1("0");
			}
			relation4.setUser1cw(cw1);
			relation4.setUser2cw(cw2);
			relation4.setYl2(MyTools.getTime());
			relationMapper.insertSelective(relation4); 		//添加关系

			if (list.size() > 0) {
				for (Relation relation : list) {
					String uu2 = relation.getUser2(); 	  // 参照人关系表的关系人ID
					String uu2cw = relation.getUser2cw(); // 参照人对关系表的关系人的称呼
					String uu1cw = relation.getUser1cw(); // 关系表的关系人对参照人的称呼

					Cwgs gxr = gxr(uu1cw, cw2); 		  // 要添加的关系表称呼1（称谓公式）

					if(!"".equals(gxr) && gxr != null) {
						String cwcw2 = gxr.getGuanxi3();			   //依据称谓公式获取称谓
						User ur1 = userMapper.selectByPrimaryKey(u2);  // 参照人的信息
						User ur2 = userMapper.selectByPrimaryKey(uu2); // 被创建人的信息
						String birthday1 = ur1.getBirthday();
						String birthday2 = ur2.getBirthday();
						
						//这一部分判断没有用上， 因为cwcw2获取的值就是个称谓了 
						//姐妹
						if (cwcw2.equals("姐妹")) {
							int result = birthday1.compareTo(birthday2);//比较
							if (result > 0) {
								cwcw2 = "妹妹";
							} else {
								cwcw2 = "姐姐";
							}
						}
						
						//姨表姐妹
						if (cwcw2.equals("姨表姐妹")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "姨表妹";
							} else {
								cwcw2 = "姨表姐";
							}
						} 
						
						//姑表姐妹
						if (cwcw2.equals("姑表姐妹")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "姑表妹";
							} else {
								cwcw2 = "姑表姐";
							}
						}
						//舅表姐妹
						if (cwcw2.equals("舅表姐妹")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "舅表妹";
							} else {
								cwcw2 = "舅表姐";
							}
						}
						
						//堂姐妹
						if (cwcw2.equals("堂姐妹")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "堂妹";
							} else {
								cwcw2 = "堂姐";
							}
						}
						
						// 兄弟
						if (cwcw2.equals("兄弟")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "弟弟";
							} else {
								cwcw2 = "哥哥";
							}
						}
						
						//姨表兄弟
						if (cwcw2.equals("姨表兄弟")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "姨表弟";
							} else {
								cwcw2 = "姨表哥";
							}
						}

						//姑表兄弟
						if (cwcw2.equals("姑表兄弟")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "姑表弟";
							} else {
								cwcw2 = "姑表哥";
							}
						}

						//舅表兄弟
						if (cwcw2.equals("舅表兄弟")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "舅表弟";
							} else {
								cwcw2 = "舅表哥";
							}
						}

						//堂兄弟
						if (cwcw2.equals("堂兄弟")) {
							int result = birthday1.compareTo(birthday2);
							if (result > 0) {
								cwcw2 = "堂弟";
							} else {
								cwcw2 = "堂哥";
							}
						}

						// 叔伯
						if (cwcw2.equals("叔伯")) {
							String birthday = getBirthday(uu2, "爸爸");
							int result = birthday1.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "叔叔";
							} else {
								cwcw2 = "伯伯";
							}
						}
						
						// 舅子（111111111111111111）
						if (cwcw2.equals("舅子")) {
							String birthday = getBirthday(uu2, "老婆");
							int result = birthday1.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "小舅子";
							} else {
								cwcw2 = "大舅子";
							}
						}

						// 姨子
						if (cwcw2.equals("姨子")) {
							String birthday = getBirthday(uu2, "老婆");
							int result = birthday1.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "小姨子";
							} else {
								cwcw2 = "大姨子";
							}
						}

						// 嫂子弟妹
						if (cwcw2.equals("嫂子弟妹")) {
							String birthday = getBirthday(u2, "老公");
							int result = birthday2.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "嫂子";
							} else {
								cwcw2 = "弟妹";
							}
						}
						
						//姐夫妹夫
						if (cwcw2.equals("姐夫妹夫")) {
							String birthday = getBirthday(u2, "老婆");
							int result = birthday2.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "姐夫";
							} else {
								cwcw2 = "妹夫";
							}
						}
						
						//大小姑子
						if (cwcw2.equals("大小姑子")) {
							String birthday = getBirthday(uu2, "老公");
							int result = birthday1.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "小姑子";
							} else {
								cwcw2 = "大姑子";
							}
						}
						
						//叔伯子
						if (cwcw2.equals("叔伯子")) {
							String birthday = getBirthday(uu2, "老公");
							int result = birthday1.compareTo(birthday);
							if (result > 0) {
								cwcw2 = "小叔子";
							} else {
								cwcw2 = "大伯子";
							}
						}
						
						String sex2 = ur2.getSex();
						Cwdy fgx = fgx(cwcw2,sex2);    			//要添加的关系表称呼2
						String cwcw1 = null;
						String dengji = null;
						if(fgx==null){
							System.err.println("称谓对应表里没有！");
						}else{
							cwcw1 = fgx.getCwdy2(); 			//获取称呼（被添加人叫对应人的称呼）
							dengji = fgx.getYl2();  			//获取的是null
						}
						int i = Integer.parseInt(gxr.getYl1());
						//添加关系
						Relation relation2 = new Relation();
						relation2.setRelationId(MyTools.getDateR());
						relation2.setUser1(u2);					//被创建人ID
						relation2.setUser2(uu2);				//添加关系人的ID
						if("1".equals(dengji)){
							relation2.setGuanxi(dengji);
						}
						if(i<0){
							String abs = Math.abs(i)+"";
							relation2.setYl1(abs);
						}else if(i == 0){
							relation2.setYl1(i+"");
						}else {
							int q=-i;
							relation2.setYl1(q+"");
						}
						relation2.setUser1cw(cwcw2);
						relation2.setUser2cw(cwcw1);
						relation2.setYl2(MyTools.getTime());
						relationMapper.insertSelective(relation2);
															//添加关系
						Relation relation5 = new Relation();
						relation5.setRelationId(MyTools.getDateR());
						relation5.setUser1(uu2);
						relation5.setUser2(u2);
						if("1".equals(dengji)){
							relation5.setGuanxi(dengji);
						}
						relation5.setYl1(i+"");
						relation5.setUser1cw(cwcw1);
						relation5.setUser2cw(cwcw2);
						relation5.setYl2(MyTools.getTime());
						relationMapper.insertSelective(relation5);
					}else {
						
						if(cw2.equals("爸爸") || cw2.equals("妈妈")){
							try {
								int i = 0;
								daishu(uu2,u2,i);
							} catch (StopMsgException e) {
								// TODO: handle exception
							}
							
						}else if(cw2.equals("儿子") || cw2.equals("女儿")){
							RelationExample example22 = new RelationExample();
							RelationExample.Criteria criteria22 = example22.createCriteria();
							criteria22.andUser1EqualTo(uu2);
							criteria22.andUser2EqualTo(u1);
							List<Relation> selectByExample = relationMapper.selectByExample(example22);
							if(selectByExample.size()>0){
								String yl1 = selectByExample.get(0).getYl1();
								num = stringToInt(yl1)-1;
							}
						}else{
							RelationExample example22 = new RelationExample();
							RelationExample.Criteria criteria22 = example22.createCriteria();
							criteria22.andUser1EqualTo(uu2);
							criteria22.andUser2EqualTo(u1);
							List<Relation> selectByExample = relationMapper.selectByExample(example22);
							if(selectByExample.size()>0){
								String yl1 = selectByExample.get(0).getYl1();
								num = stringToInt(yl1);
							}
						}
						Relation r1 = new Relation();
						r1.setRelationId(MyTools.getDateR());
						r1.setUser1(uu2);
						r1.setUser2(u2);
						r1.setUser1cw("后辈");
						r1.setUser2cw("先祖");
						r1.setYl1(num+"");
						r1.setYl2(MyTools.getTime());
						relationMapper.insertSelective(r1);
						
						Relation r2 = new Relation();
						r2.setRelationId(MyTools.getDateR());
						r2.setUser2(uu2);
						r2.setUser1(u2);
						r2.setUser2cw("后辈");
						r2.setUser1cw("先祖");
						r2.setYl1((num*-1)+"");
						r2.setYl2(MyTools.getTime());
						relationMapper.insertSelective(r2);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void daishu(String uu2, String u2, int i) {
		i = i+1;
		RelationExample example = new RelationExample();
		RelationExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo("1");
		criteria.andUser1EqualTo(uu2);
		List<Relation> list = relationMapper.selectByExample(example);
		if(list.size()>0){
			String user2Id = list.get(0).getUser2();
			if(user2Id.equals(u2)){
				num = i;
				throw new StopMsgException();
			}else {
				daishu(user2Id, u2, i);
			}
		}
	}
	static class StopMsgException extends RuntimeException {
	}
	
	// 字符串转数字
		static Integer stringToInt(String s) {

			char first = s.charAt(0);
			int sum = 0;
			long sum1 = 0;

			if (first >= '0' && first <= '9') {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						sum1 = sum1 * 10 + (s.charAt(i) - '0');
						if (sum1 <= Integer.MAX_VALUE) {
							sum = (int) sum1;
							if (i == s.length() - 1)
								return sum;
						} 
					}
				}
			}else if (first == '+' || first == '-') {
				sum = 0;
				for (int i = 1; i < s.length(); i++) {
					if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						sum1 = sum1 * 10 + (s.charAt(i) - '0');
						if (sum1 <= Integer.MAX_VALUE) {
							sum = (int) sum1;
							if (i == s.length() - 1){
								return first == '-' ? -sum : sum;
							}	
						} 
					} 
				}
			}
			return null;
		}
	
	public Cwgs gxr(String gx1,String gx2) {
		CwgsExample example = new CwgsExample();
		CwgsExample.Criteria criteria = example.createCriteria();
		criteria.andGuanxi1EqualTo(gx1);
		criteria.andGuanxi2EqualTo(gx2);
		List<Cwgs> list = cwgsMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
		
	}
	
	//查询称谓对应关系
	public Cwdy fgx(String cw,String sex) {
		CwdyExample example = new CwdyExample();
		CwdyExample.Criteria criteria = example.createCriteria();
		criteria.andCwdy1EqualTo(cw);
		criteria.andYl1EqualTo(sex);
		List<Cwdy> list = cwdyMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

	public List<User> listUser(User user) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		String name = user.getName();
		if(name != null && !"".equals(name)){
			criteria.andNameLike("%"+name+"%");
		}
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	public String getBirthday(String id,String chengw){
		RelationExample example = new RelationExample();
		RelationExample.Criteria criteria = example.createCriteria();
		criteria.andUser1EqualTo(id);
		criteria.andUser2cwEqualTo(chengw);
		List<Relation> selectByExample = relationMapper.selectByExample(example);
		String user2 = selectByExample.get(0).getUser2();
		User user = userMapper.selectByPrimaryKey(user2);
		return user.getBirthday();
	}

	public boolean getUsername(User user){
		UserExample example = new UserExample();
		UserExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andPhoneEqualTo(user.getPhone());
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>=1){
			return false;
		}else{
			return true;
		}
	}
	
	
	//登录
	public User getUser(User user) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andPasswordEqualTo(user.getPassword());
		criteria.andPhoneEqualTo(user.getPhone());
		criteria.andStatusEqualTo("1");
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	

	public List<User> getUserByBirth(String day,String afterDay) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andYl2Between(day, afterDay);
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	public List<User> selectByYl(String yl1) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(yl1);
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	
	//根据手机号查询用户信息
	public List<User> hebin(User user) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();		
		String phone = user.getPhone();			
		if(phone!=null && !"".equals(phone)){
			criteria.andPhoneEqualTo(phone);
		}else{
			return null;
		}
		
		List<User> list = userMapper.selectByExample(example);	//查询
		List<User> list2 = new ArrayList<User>();
		for(User u:list){
			if(!u.getUserid().equals(user.getUserid())){
				u.setUser(userMapper.selectByPrimaryKey(u.getYl5()));
				list2.add(u);
			}
		}
		return list2;
	}
	
	//喜结连理
	/**
	 * 1.传参，被合并userid，token，老公老婆
	 * 2.获取被合并user1对象，获取合并user2对象
	 * 3.获取user1的1级关系relation1，获取user2的1级关系relation2
	 * 4.添加关系
	 * 5.更新user1，user2对象
	 */
	public boolean hebing2(User user1, User user2) {
		
		//1.获取被合并人ID，然后根据ID查到被合并人的创建人，2根据创建人和被合并人ID ，确认被合并人的称谓
		String idd = user1.getUserid();							//被合并人DI(假的)
		System.err.println(idd);
		User uu = userMapper.selectByPrimaryKey(idd);			//创建人ID	
		String yl5 = uu.getYl5();
		System.err.println(yl5);
		
//		String uidd = user2.getUserid();
//		System.err.println(uidd);
		
		RelationExample example = new RelationExample();
		RelationExample.Criteria criteria = example.createCriteria();
		if(idd!=null && !"".equals(idd)){
			criteria.andUser1EqualTo(idd);
		}else{
			return false;
		}
		if(yl5!=null && !"".equals(yl5)){
			criteria.andUser2EqualTo(yl5);
		}else{
			return false;
		}							
		List<Relation> list = relationMapper.selectByExample(example);		//一条关系(老公老婆关系)
//		List<Relation> list2 = new ArrayList<Relation>();
		Relation rr = new Relation();
		for(Relation r:list){
			 rr = relationMapper.selectByPrimaryKey(r.getRelationId());							 
		}
		String cw = rr.getUser1cw();		//获取user1称谓
		System.err.println(cw);

		user1 = userMapper.selectByPrimaryKey(user1.getUserid());
		String userid = user1.getYl5();				//获取创建人ID
		RelationExample example1 = new RelationExample();
		RelationExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andUser1EqualTo(userid);			//查询创建人的关系
		criteria1.andGuanxiEqualTo("1");			//关系为“1”的
		List<Relation> listr1 = relationMapper.selectByExample(example1);
		
		RelationExample example2 = new RelationExample();
		RelationExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andUser1EqualTo(user2.getUserid());		//查询真实ID的关系
		criteria2.andGuanxiEqualTo("1");
		List<Relation> listr2 = relationMapper.selectByExample(example2);
		
		if(!addRe12(listr1, user2, cw,user1)){
			return false;
		}
		
		String cw2 = "";
		if(cw.equals("老公")){
			cw2 = "老婆";
		}else{
			cw2 = "老公";
		}	
	
		User user11 =  userMapper.selectByPrimaryKey(userid);		//给创建人这个对象添加关系
		if(!addRe12(listr2, user11, cw2, user2)){
			return false;
		}
			
		
//		//关系
//		String u1id = user1.getUserid();			//虚拟ID
//		String u2id = user2.getUserid();			//真事iD
//		updateid(u1id,u2id);						//修改ID
		
		//更新老公老婆
		String user1id = user1.getUserid();			//虚拟花花ID
		RelationExample examplelglp = new RelationExample();
		RelationExample.Criteria criterialglp = examplelglp.createCriteria();
//		criterialglp.andUser1cwEqualTo(cw);			//老婆
		criterialglp.andUser1EqualTo(user1id);
		Relation rrrr = new Relation();
		rrrr.setUser1(user2.getUserid());			//真实花花ID
		relationMapper.updateByExampleSelective(rrrr, examplelglp);
		
		RelationExample examplelglp2 = new RelationExample();
		RelationExample.Criteria criterialglp2 = examplelglp2.createCriteria();
//		criterialglp2.andUser1cwEqualTo(cw2);		//老公
		criterialglp2.andUser2EqualTo(user1id);
		Relation rrrrr = new Relation();
		rrrrr.setUser2(user2.getUserid());
		relationMapper.updateByExampleSelective(rrrrr, examplelglp2);
		
		//去重
		RelationExample examplelglp4 = new RelationExample();
		examplelglp4.or().andUser1EqualTo(user2.getUserid());
		examplelglp4.or().andUser2EqualTo(user2.getUserid());
		List<Relation> list4 = relationMapper.selectByExample(examplelglp4);
		for(Relation r4:list4){
			String user1cw = r4.getUser1cw();				//称谓1
			String user2cw = r4.getUser2cw();				//称谓2
			String yl1 = r4.getYl1(); 						//关系代
			RelationExample examplelglp3 = new RelationExample();
			RelationExample.Criteria criteria3 = examplelglp3.createCriteria();
			criteria3.andUser1EqualTo(user2.getUserid());				//合并人真实ID
			criteria3.andUser1cwEqualTo(user1cw);						//称谓1
			criteria3.andUser2cwEqualTo(user2cw);						//称谓2
			criteria3.andYl1EqualTo(yl1);								//关系代
			List<Relation> list3 = relationMapper.selectByExample(examplelglp3);
			if(list3.size()>1){
				String relationId = list3.get(0).getRelationId();
				relationMapper.deleteByPrimaryKey(relationId);
			}
			RelationExample examplelglp5 = new RelationExample();
			RelationExample.Criteria criteria5 = examplelglp5.createCriteria();
			criteria5.andUser2EqualTo(user2.getUserid());			
			criteria5.andUser1cwEqualTo(user1cw);						//称谓1
			criteria5.andUser2cwEqualTo(user2cw);						//称谓2
			criteria5.andYl1EqualTo(yl1);								//关系代
			List<Relation> list5 = relationMapper.selectByExample(examplelglp5);
			if(list5.size()>1){
				String relationId = list5.get(0).getRelationId();
				relationMapper.deleteByPrimaryKey(relationId);
			}
			
		}
		
		//朋友圈标识，和活动			
		String uid11= user1.getUserid();
		User user22 = userMapper.selectByPrimaryKey(uid11); //虚拟的
		String u22id = user2.getUserid();					//真实的 
		System.out.println(u22id);
		String id = user22.getYl1();						//虚拟的
		System.err.println(user22.getYl1());
		updateid2(id,u22id,idd);							//虚拟的YL1 真实的ID
		return true;
	}
		
		public boolean addRe12(List<Relation> listr1,User user2,String cw,User user1){
			try{
				for(Relation r1: listr1){
					Relation r11 = new Relation();
					
					String user2id = r1.getUser2();
					String user1id = user2.getUserid();
					
					String cw11 = r1.getUser1cw();
					String cw22 = r1.getUser2cw();
					
					String cwcw = "";
					if(cw.equals("老公")){
						cwcw = "老婆";
					}else{
						cwcw = "老公";
					}
					
					Cwgs gx1 = gxr(cw11,cw);
					if(gx1!=null){
						String guanxi = "";  		//创建人的关系数  
						switch(gx1.getGuanxi3()){
						case "老公":
							guanxi="1";
							break;
						case "妈妈":
							guanxi="1";
							break;
						case "儿子":
							guanxi="1";
							break;
						case "女儿":
							guanxi="1";
							break;
						}
						Cwgs gx2 = gxr(cwcw,cw22);
						
						String cw1 = gx1.getGuanxi3();
						String cw2 = gx2.getGuanxi3();
						
						String yl1 = gx1.getYl1();
						String yl2 = gx2.getYl1();
						
						r11.setRelationId(MyTools.getDateR());
						r11.setUser1(user1id);
						r11.setUser2(user2id);
						r11.setUser1cw(cw1);
						r11.setUser2cw(cw2);
						r11.setGuanxi(guanxi);				//关系
						r11.setYl1(yl2);
						r11.setYl2(MyTools.getTime());		//更新时间
						addRelation111(r11);
						
						r11.setRelationId(MyTools.getDateR());
						r11.setUser2(user1id);
						r11.setUser1(user2id);
						r11.setUser2cw(cw1);
						r11.setUser1cw(cw2);
						r11.setGuanxi(guanxi);				//关系
						r11.setYl1(yl1);	
						r11.setYl2(MyTools.getTime());		//更新时间
						addRelation111(r11);						
					}
				}
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		//更改yl7
		private void updateid2(String id, String u22id, String idd) {		//idd虚拟花花的id
			//更新user的yl7	
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andUseridEqualTo(u22id);		
			User uu = new User();
			uu.setYl7(id);													//虚拟花花的预留1
			userMapper.updateByExampleSelective(uu, example);
			User selectByPrimaryKey = userMapper.selectByPrimaryKey(u22id); //真实花花id
			String yl1 = selectByPrimaryKey.getYl1();
			System.out.println(yl1);										//获取真实花花的YL1，做朋友圈标识
			
			UserExample example2 = new UserExample();
			UserExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andYl1EqualTo(id);
			criteria2.andUseridNotEqualTo(idd);								//去重
			List<User> selectByExample = userMapper.selectByExample(example2);
			String userid = selectByExample.get(0).getUserid();				//获取李华
			User user2 = userMapper.selectByPrimaryKey(userid);			
			user2.setYl7(yl1);												//把把花花的yl1，set到李华的yl7，朋友圈标识
			userMapper.updateByPrimaryKeySelective(user2);
		}
		
		//更新关系数据库
//		private void updateid(String u1id, String u2id) {
//			//更新user1
//			RelationExample example = new RelationExample();
//			RelationExample.Criteria criteria1 = example.createCriteria();
//			criteria1.andUser1EqualTo(u1id);			
//			Relation r = new Relation();
//			r.setUser1(u2id);
//			relationMapper.updateByExampleSelective(r, example);
//			//更新user2
//			RelationExample example1 = new RelationExample();
//			RelationExample.Criteria criteria2 = example1.createCriteria();
//			criteria2.andUser2EqualTo(u1id);			
//			Relation re = new Relation();
//			re.setUser2(u2id);
//			relationMapper.updateByExampleSelective(re, example1);
//		}
	
		//查询
		public List<User> search(String phone) {
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(phone);
			criteria.andYl3EqualTo("是");
			return userMapper.selectByExample(example);
		}
		
		
		//注册
		public boolean register(User user) {
			try {
				String userid = MyTools.getDateR();
				user.setUserid(userid);
//				user.setYl1(MyTools.getDateR());
				String yl1 = MyTools.getDateR();		//种族标识
				user.setStatus("1");                    //
				user.setYl1(yl1);
				user.setYl3("是");
				user.setYl4(user.getYl4());
				user.setYl5(userid);					//创建人				
				user.setYl6(MyTools.getTime());
				user.setYl7(yl1);						//朋友圈查看标识
				String birthday = user.getBirthday();
				if(birthday !=null && !"".equals(birthday) ){
					String[] split = birthday.split("-");
					String yl2 = split[1]+"-"+split[2];
					String yl8 = CalendarUtil.solarToLunar(birthday);
					user.setYl8(yl8);
					user.setYl2(yl2);
				}
				
				String phone = user.getPhone();
				String password = user.getPassword();
				SysUser sys = new SysUser();
				sys.setUserId(MyTools.getDateR());
				sys.setUsername(phone);
				sys.setPassword(password);
				sysUserMapper.insertSelective(sys);
				userMapper.insertSelective(user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		public User getUserBysys(SysUser sysUser) {
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(sysUser.getUsername());
			criteria.andPasswordEqualTo(sysUser.getPassword());
			List<User> list = userMapper.selectByExample(example);
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		}

		//修改user表中密码
		public boolean updatePwd(User user) {
			userMapper.updateByPrimaryKeySelective(user);
			return false;
		}
		
		
		
		//喜结连理，注销掉虚拟账号
		public boolean updateUser1(User user1) {
			try {
				User user_xn = userMapper.selectByPrimaryKey(user1.getUserid());
				user_xn.setPhone(user_xn.getPhone()+"***"+MyTools.getDateR());
				user_xn.setStatus("0");
				userMapper.updateByPrimaryKeySelective(user_xn);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}



		//查询
		public User getuser2(String userid) {
			return userMapper.selectByPrimaryKey(userid);
		}
		
	}