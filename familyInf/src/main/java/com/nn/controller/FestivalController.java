package com.nn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Festival;
import com.nn.bean.Memorialday;
import com.nn.bean.User;
import com.nn.dao.MemorialdayMapper;
import com.nn.service.FestivalService;
import com.nn.service.MemorialdayService;
import com.nn.service.UserService;
import com.nn.utils.CalendarUtil;
import com.nn.utils.Msg;

@Controller
@RequestMapping("festival")
public class FestivalController extends BaseController{
	
	@Autowired
	private FestivalService festivalService;
	@Autowired
	private UserService userService;
	@Autowired
	private MemorialdayService memorialdayService;
	
	//添加
		@RequestMapping("add")
		@ResponseBody
		public Msg Add(Festival festival){
			if(festivalService.add(festival)){
				return Msg.success().add("result", "success");
			}else{
				return Msg.fail().add("result", "faulier");
			}
			
		}
		
		//删除
		@RequestMapping("delete")
		@ResponseBody
		public Msg Delete(String festivalId){
			if(festivalService.delete(festivalId)){
				return Msg.success().add("result", "success");
			}else{
				return Msg.fail().add("result", "faulier");
			}
		}
		
		//修改
		@RequestMapping("update")
		@ResponseBody
		public Msg Upadet(Festival festival){
			if(festivalService.update(festival)){
				return Msg.success().add("result", "success");
			}else{
				return Msg.fail().add("result", "faulier");
			}
		}
		
		//根据ID查询
		@RequestMapping("get")
		@ResponseBody
		public Msg Get(String festivalId){
			Festival festival = festivalService.get(festivalId);
			return Msg.success().add("result", festival);
		}
		
		//查看列表
		@RequestMapping("list")
		@ResponseBody
		public Msg userList(Festival festival){
			List<Festival> festivals = festivalService.list(festival);
			return Msg.success().add("result", festivals);
		}
		
		
		//获取生日,节日,忌日
		@RequestMapping("getBirth")
		@ResponseBody
		public Msg getBirth(HttpServletRequest request,String mouth,String year) throws Exception {
			User us = getCurrentUser(request);
			Map<String, String> map = new HashMap<String, String>();
			ArrayList<String> list = new ArrayList<String>(); 
			User user = userService.getUser(us.getUserid());
			int m = Integer.parseInt(mouth)+1;
			String month = "";
			if(m<10){
				month = "0"+m;
			}else{
				month = m+"";
			}
			
			String yl1 = user.getYl1();
			List<User> uList = userService.selectByYl(yl1);
			for (User u : uList) {
				String birth = u.getYl2();
				String[] split = birth.split("-");
				System.out.println(split[0]);
				if(split[0].equals(month) ){
					String key = split[0]+split[1];
					String b = map.get(key);
					if(b == null || "".equals(b)){
						String content = u.getName()+"阳历生日";
						map.put(key, content);
					}else{
						String content = u.getName() + "阳历生日和" + b;
						map.put(key, content);
					}
				}
			}
			
			for (User u : uList) {
				String birth = u.getYl4();
				if (birth!=null&!"".equals(birth)) {
					String[] split = birth.split("-");
					System.out.println(split[0]);
					if(split[0].equals(month) ){
						String key = split[0]+split[1];
						String b = map.get(key);
						if(b == null || "".equals(b)){
							String content = u.getName()+"忌日";
							map.put(key, content);
						}else{
							String content = u.getName() + "忌日和" + b;
							map.put(key, content);
						}
					}
				}
			}
			
			List<Festival> fList = festivalService.listAll();
			for (Festival f : fList) {
				String[] split = f.getDate().split("-");
				if(split[1].equals(month)){
					String key = split[1]+split[2];
					String birth = map.get(key);
					if(birth == null || "".equals(birth)){
						String content = f.getFestivalname();
						map.put(key, content);
					}else{
						String content = f.getFestivalname() + "和" + birth;
						map.put(key, content);
					}
				}
			}
			
			//阴历生日
			for (User u : uList) {
				//阴历转阳历
				String yl8 = u.getYl8();
				String[] split = yl8.split("-");
				int leapMonth = CalendarUtil.getLeapMonth(Integer.parseInt(year));
				boolean leapMonthFlag;
				if(leapMonth == 0){
					leapMonthFlag = false;
				}else{
					leapMonthFlag = true;
				}
				String lunarDate = year+split[0]+split[1];
				String yangli = CalendarUtil.lunarToSolar(lunarDate, leapMonthFlag);
				String[] split2 = yangli.split("-");
				if(split2[1].equals(month) ){
					String key = split2[1]+split2[2];
					String b = map.get(key);
					if(b == null || "".equals(b)){
						String content = u.getName()+"阴历生日";
						map.put(key, content);
					}else{
						String content = u.getName() + "阴历生日和" + b;
						map.put(key, content);
					}
				}
			}
			for (Map.Entry<String, String> entry : map.entrySet()) {
				  String key = entry.getKey().toString();
				  String value = entry.getValue().toString();
				  String str = key+" "+value;
				  list.add(str);
			}
			
			//纪念日
			List<String> memday = memorialdayService.list2(user, mouth);
			list.addAll(memday);
			
			return Msg.success().add("result", list);
		}
		
		
		//修改用户名和密码
		@RequestMapping("changePwd")
		@ResponseBody
		public Msg changePwd(User user) {
			
			return null;
		}
		
	}
