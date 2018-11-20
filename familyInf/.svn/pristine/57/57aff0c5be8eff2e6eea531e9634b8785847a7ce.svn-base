package com.nn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nn.bean.Zidian;
import com.nn.bean.ZidianWithBLOBs;
import com.nn.service.ZidianService;
import com.nn.utils.JsoupTest;
import com.nn.utils.Msg;

@Controller
@RequestMapping("zidian")
public class ZidianController {
	@Autowired
	private ZidianService zidianService;
	
	
	//id查询
	@ResponseBody
	@RequestMapping("get")
	public Msg get(Zidian zidian){
		return Msg.success().add("result", zidianService.get(zidian));
	}
	
	@ResponseBody
	@RequestMapping("add")
	public void add() throws IOException{
		List<String> data = JsoupTest.getData4();
		for (String s : data) {
			ZidianWithBLOBs zidian = new ZidianWithBLOBs();
			zidian.setWenzi(s);
			zidian = zidianService.add(zidian);
			if(zidian!=null){
				zidian.setYl1("1");
				zidianService.update(zidian);
			}else{
				System.err.println(s);
			}
		}
	}
	
	@ResponseBody
	@RequestMapping("list")
	public Msg list(String xs,String sex,String num,String xsb) throws IOException{
		List<ZidianWithBLOBs> list = zidianService.list(sex);
		List<String> list2 = new ArrayList<String>();
		for(ZidianWithBLOBs zz : list){
			if("1".equals(num)){
				String xm = xs+zz.getWenzi();
				list2.add(xm);
			}else if("2".equals(num)){
				if(!"".equals(xsb)){
					String xm = xs+xsb+zz.getWenzi();
					list2.add(xm);
				}else{
					ZidianWithBLOBs z = list.get((int)(Math.random()*list.size()));
					String xm = xs+zz.getWenzi()+z.getWenzi();
					list2.add(xm);
				}
			}
		}
		return Msg.success().add("result", list2);
	}
}
