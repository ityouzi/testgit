package com.nn.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Qianwen;
import com.nn.bean.QianwenWithBLOBs;
import com.nn.bean.Zidian;
import com.nn.bean.ZidianWithBLOBs;
import com.nn.service.QianwenService;
import com.nn.service.ZidianService;
import com.nn.utils.InputUtil;
import com.nn.utils.JsoupTest;
import com.nn.utils.Msg;
import com.nn.utils.MyTools;

@Controller
@RequestMapping("qianwen")
public class QianwenController {
	
	@Autowired
	private QianwenService qianwenService;
	@Autowired
	private ZidianService zidianService;
	
	//添加签文信息
	@ResponseBody
	@RequestMapping("add")
	public void add() throws IOException{
		
		for(int i=1;i<=100;i++){
			String url ="https://www.zgjm.org/chouqian/guanyin/"+i+".html";
			Map<String, String> d = JsoupTest.getD(url);
			
			QianwenWithBLOBs qianwen = new QianwenWithBLOBs();
			qianwen.setContent(d.get("elements"));
			qianwen.setYl1(d.get("photo"));
			qianwen.setStatus(d.get("status"));
			qianwen.setTitle(d.get("title"));
			String a = i+"";
			qianwen.setQianid(a);
			qianwen.setYl2(1+"");
			qianwen.setId(MyTools.getDateR());
			qianwenService.add(qianwen);
		}
	}
	
	//添加签文图片信息
	@ResponseBody
	@RequestMapping("save")
	public void save(HttpServletRequest request,QianwenWithBLOBs qianwen) throws IOException{
		List<QianwenWithBLOBs> list = qianwenService.list(qianwen);
		for (QianwenWithBLOBs q : list) {
			String yl1 = q.getYl1();
			q.setId(q.getId());
			InputStream imageStream = InputUtil.getImageStream(yl1);
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
			byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据 
			int rc = 0; 
			while ((rc = imageStream.read(buff, 0, 100)) > 0) { 
				swapStream.write(buff, 0, rc); 
			} 
			byte[] in_b = swapStream.toByteArray();
			q.setPhoto(in_b);
			qianwenService.update(q);
		}
	}
	
	//抽签(查询)
	@ResponseBody
	@RequestMapping("get")
	public Msg get(Qianwen qianwen,String name,String date){
		int number = getNumber();
		int bihua = 0;
		int ran = 0;
		if(name!=null && !"".equals(name)){
			bihua = getBihua(name);
			number = (number+bihua)/2;
		}
		if(date!=null && !"".equals(date)){
			ran = getRan(date);
			number = (number+ran)/2;
		}
		String c = String.valueOf(number);
		System.err.println(c);
		qianwen.setQianid(c);
		return Msg.success().add("result", qianwenService.listone(qianwen));
	}
	
	public int getNumber(){
		int i=(int)(Math.random()*100+1);
		return i;
	}
	
	public int getRan(String date){
		String[] split = date.split("-");
		char a = split[0].charAt(3);
		char b = split[1].charAt(1);
		char c = split[2].charAt(1);
		char [] d = {a,b,c};
		String e = String.valueOf(d);
		int i = Integer.parseInt(e);
//		System.out.println(e);
		return i/10;
	}
	
	public int getBihua(String name){
		ZidianWithBLOBs zidian = new ZidianWithBLOBs();
		int count = 0;
		for (int i = 0; i < name.length(); i++) {
			char f = name.charAt(i);
			String a = String.valueOf(f);
			zidian.setWenzi(a);
			Zidian list = zidianService.add(zidian);
			Integer bihua = list.getBihua();
			count += bihua;
		}
		return count*2;
	}
	
	
}	
