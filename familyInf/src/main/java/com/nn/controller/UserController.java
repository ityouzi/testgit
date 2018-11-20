package com.nn.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gexin.rp.sdk.template.LinkTemplate;
import com.nn.bean.Activity;
import com.nn.bean.Festival;
import com.nn.bean.Relation;
import com.nn.bean.SysUser;
import com.nn.bean.User;
import com.nn.service.ActivityService;
import com.nn.service.FestivalService;
import com.nn.service.ShenqingService;
import com.nn.service.SysUserService;
import com.nn.service.UserService;
import com.nn.utils.GetRandem;
import com.nn.utils.Msg;
import com.nn.utils.MyTools;
import com.nn.utils.PushUtils;
import com.nn.utils.PushtoList;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("usermsg")
@EnableScheduling
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private FestivalService festivalService;
	@Autowired
	private SysUserService sysUserService;

	

	List<String> cidList = new ArrayList<String>();

	// 添加
	@RequestMapping("add")
	@ResponseBody
	public Msg Add(HttpServletRequest request,User user) {
		User user1 = getCurrentUser(request);   				//当前用户
		String userid = request.getParameter("userid");		
		String cw2cw = request.getParameter("cw2cw");			//称谓
		
		Relation relation = new Relation();
		relation.setUser1(userid);
		relation.setUser2cw(cw2cw);
		
		if (userService.addUser(user, relation,user1)) {
			return Msg.success().add("result", "success");
		} else {
			return Msg.fail().add("result", "faulier");
		}

	}

	// 删除
	@RequestMapping("delete")
	@ResponseBody
	public Msg Delete(String userId) {
		if (userService.deleteUser(userId)) {
			return Msg.success().add("result", "success");
		} else {
			return Msg.fail().add("result", "faulier");
		}
	}

	// 修改
	@RequestMapping("update")
	@ResponseBody
	public Msg Upadet(User user) {

		if (userService.updateUser(user)) {
			return Msg.success().add("result", "success");
		} else {
			return Msg.fail().add("result", "faulier");
		}
	}

	// 根据ID查询
	@RequestMapping("get")
	@ResponseBody
	public Msg Get(String userid) {
		User user = userService.getUser(userid);
		return Msg.success().add("result", user);
	}

	// 查看列表
	@RequestMapping("list")
	@ResponseBody
	public Msg userList(User user) {
		List<User> users = userService.listUser(user);
		return Msg.success().add("result", users);
	}

	// 登录
	@RequestMapping("login")
	@ResponseBody
	public Msg login(SysUser sysUser) {
		/*if (user.getPhone() == null || user.getPassword() == null) {
			return Msg.fail().add("result", "用户名或密码不正确");
		}		
		user.setPassword(new SimpleHash("SHA-1", user.getPhone(), user.getPassword()).toString());
		User loguser = userService.getUser(user);
		System.err.println(loguser);
		if (loguser == null) {
			return Msg.fail().add("result", "用户名或密码不正确");
		} else {			
			String token = GetRandem.getDateR();
			Cache cache = cacheManager.getCache("data-cache");
			Element e = new Element(token, loguser);
			cache.put(e);
			return Msg.success().add("result", loguser).add("token", token);
		}*/
		
		if(sysUser.getUsername() == null || sysUser.getPassword() == null){
			return Msg.fail().add("result", "用户名或密码不对");
		}
		sysUser.setPassword(new SimpleHash("SHA-1", sysUser.getUsername(), sysUser.getPassword()).toString());	//密码加密
		sysUser = sysUserService.getSysUser(sysUser);
		
		if(sysUser == null){
			return Msg.fail().add("result", "用户名或密码不对");
		}
		String token = GetRandem.getDateR();
		Cache cache = cacheManager.getCache("data-cache");
		
		User loguser = userService.getUserBysys(sysUser);
		Element e = new Element(token, loguser);
		cache.put(e);
		
		return Msg.success().add("token", token).add("result", loguser);

	}	
	
	//注册
	@RequestMapping("register")
	@ResponseBody
	public Msg register(User user){		
		SysUser sysUser = sysUserService.getSysUserByUsername(user.getPhone()); //查询账户是否已存在
		if(sysUser == null){
			user.setPassword(new SimpleHash("SHA-1", user.getPhone(), user.getPassword()).toString());
			if(userService.register(user)){
				return Msg.success().add("result", "注册成功");			
			}else{
				return Msg.fail().add("result", "注册失败");
			}			  
		}else {
			return Msg.fail().add("result", "用户名已存在");
		}
	}

	// 缓存cid
	@RequestMapping("save")
	@ResponseBody
	public Msg save(HttpServletRequest request, String cid, String userid) {
		Cache cache = cacheManager.getCache("data-cache");
		Element e = new Element(userid, cid);
		cache.put(e);
		return Msg.success().add("result", userid);
	}
	
	
	//缓存提前提醒天数
	@RequestMapping("saveday")
	@ResponseBody
	public Msg SaveDay(HttpServletRequest request, String day) {
		Cache cache = cacheManager.getCache("data-cache");
		Element e = new Element("num", day);
		cache.put(e);
		return Msg.success().add("result", day);
	}
	

	// 定时任务
	@SuppressWarnings("deprecation")
//	@Scheduled(cron = "*/30 * * * * ?")
	@Scheduled(cron = "0 0 1 * * ?")
	public void dingshi() throws InterruptedException {
		Cache cache = cacheManager.getCache("data-cache");

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day = sdf.format(calendar.getTime());
		
		Element el = cache.get("num");
		String afterDay ="";
		if(el == null){
			afterDay = AfterDay(7);
		}else {
			afterDay = AfterDay(Integer.parseInt(el.getValue().toString()));
		}
		
		System.out.println(day);
		System.out.println(afterDay);
		
		String[] split = day.split("-");
		String[] split1 = afterDay.split("-");
		
		String today = split[1]+split[2];
		String after = split1[1]+split1[2];
		
		List<User> birthlist = userService.getUserByBirth(today, after);
		if (birthlist.size() > 0) {
			for (User user : birthlist) {
				List<String> strList = new ArrayList<String>();

				String yl1 = user.getYl1();
				List<User> list = userService.selectByYl(yl1);
				for (User u : list) {
					String userid = u.getUserid();
					Element element = cache.get(userid);
					if (element != null) {
						String string = element.getValue().toString();
						strList.add(string);
					}
				}
				String name = user.getName();
				String birthday = user.getBirthday();
				String content = name + "：" + name + "的生日";
				String pushMessage = PushtoList.pushMessage(birthday, content,"setTransmissionContent",strList);
				Thread.sleep(5000);
				System.out.println(pushMessage + "b");
			}

		} 

		List<Activity> actList = activityService.getActByDay(day, afterDay);
		if (actList.size() > 0) {
			for (Activity activity : actList) {
				List<String> strList = new ArrayList<String>();

				String yl1 = activity.getYl1();
				List<User> list = userService.selectByYl(yl1);
				for (User u : list) {
					String userid = u.getUserid();
					Element element = cache.get(userid);
					if (element != null) {
						String string = element.getValue().toString();
						strList.add(string);
					}

				}

				String co = activity.getContent();
				String creator = activity.getCreator();
				String content = creator + "：" + co;
				String time = activity.getTime();
				String pushMessage = PushtoList.pushMessage(time, content,"setTransmissionContent",strList);
				Thread.sleep(5000);
				System.out.println(pushMessage + "a");

			}
		} 

		List<Festival> fesList = festivalService.getFesByDay(day, afterDay);
		if (fesList.size() > 0) {
			for (Festival festival : fesList) {
				String name = festival.getFestivalname();
				String content = "系统：" + name;
				String date = festival.getDate();
				LinkTemplate linkTemplate = PushUtils.linkTemplateDemo(date,content);
				String sendMsg = PushUtils.sendMsg(linkTemplate);
				Thread.sleep(5000);
				System.out.println(sendMsg + "f");

			}
		} 
	}

	// 获取到指定时间
	public static String AfterDay(int sum) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DATE, sum);
		String sum_days = sdf.format(calendar.getTime());
		return sum_days;
	}
	
	//根据手机号查询要合并用户
		@RequestMapping("getuser")
		@ResponseBody
		public Msg gethebin(HttpServletRequest request){	
			return Msg.success().add("result", userService.hebin(getCurrentUser(request)));		
		}
	
		
	//合并关系(亲族合并 )
	@RequestMapping("addrelation1")
	@ResponseBody
	public Msg addrelation1(HttpServletRequest request,User user){
		User user2 = getCurrentUser(request);
		String userid = user2.getUserid();			//真实ID
		String yl1 = user2.getYl1();
		user2.setUserid(user.getUserid());			//虚拟ID
		user2.setYl1(user.getYl1());				//虚拟YL1
		user2.setYl7(user.getYl1());				
		
		if(userService.updateUser(user2)){
			user2.setUserid(userid);
			user2.setPhone(user2.getPhone()+"***"+MyTools.getDateR());
			user2.setYl1(yl1);
			user2.setStatus("0");				//状态
			if(!userService.updateUser(user2)){
				return Msg.fail().add("result", "失败");
			}
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");
	}
	
	//合并关系(喜结连理)
	/**
	 * 1.传参，被合并userid，token，老公老婆
	 * 2.获取被合并user1对象，获取合并user2对象
	 * 3.获取user1的1级关系relation1，获取user2的1级关系relation2
	 * 4.添加关系
	 * 5.更新user1，user2对象
	 */
	@RequestMapping("addrelation2")
	@ResponseBody
//	public Msg addrelation(HttpServletRequest request,User user1){		
//		User user2 = getCurrentUser(request);
////		String cw = request.getParameter("cw");				//获取称谓
//		if (userService.hebing2(user1,user2)) {
//			return Msg.success().add("result", "成功");
//		} else {
//			return Msg.fail().add("result", "失败");
//		}	
//	}
	public Msg addrelation(HttpServletRequest request,User user1){		
		User user2 = getCurrentUser(request);
//		String cw = request.getParameter("cw");				
		if (userService.hebing2(user1,user2)) {				//喜结连理
			if(!userService.updateUser1(user1)){            //喜结连理，注销掉虚拟账号
				return Msg.fail().add("result", "失败");
			}
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");
	}
	
	
	//添加朋友
	//通过手机号查询
	@RequestMapping("addfriend")
	@ResponseBody
	public Msg add(String phone){
		return Msg.success().add("result", userService.search(phone));
		
	}
	
	//修改密码
	@RequestMapping("change")
	@ResponseBody
	public Msg change(HttpServletRequest request, String oldPwd, String newPwd, String newPhone){
		User user = getCurrentUser(request);
		String oldPhone = user.getPhone();
		String old = new SimpleHash("SHA-1", oldPhone, oldPwd).toString();
		if (old.equals(user.getPassword())) {
			//修改用户密码
//			user.setPhone(newPhone);
			user.setPassword(new SimpleHash("SHA-1", oldPhone, newPwd).toString());
			userService.updatePwd(user);
			
			//修改系统密码
			SysUser sysUser = new SysUser();
//			sysUser.setUsername(oldPhone);
			sysUser.setPassword(new SimpleHash("SHA-1", oldPhone, newPwd).toString());
			sysUserService.updatePwd(oldPhone, sysUser);
			
			return Msg.success().add("result", "修改成功");
		}else{
			return Msg.fail().add("result", "输入原密码不正确");
		}
		
		
		
	}
}
