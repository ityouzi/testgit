package com.fh.controller.userinfo;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.userinfo.UserinfoManager;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.nnsci.PdPut;

/** 
 * 说明：用户信息表
 * 创建人：NN Q174493573
 * 创建时间：2018-05-14
 */
@Controller
@RequestMapping(value="/userinfo")
public class UserinfoController extends BaseController {
	
	String menuUrl = "userinfo/list.do"; //菜单地址(权限用)
	@Resource(name="userinfoService")
	private UserinfoManager userinfoService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Userinfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USERINFO_ID", this.get32UUID());	//主键
		userinfoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Userinfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Userinfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Userinfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(new PdPut().pdPut(pd));
		List<PageData>	varList = userinfoService.list(page);	//列出Userinfo列表
		mv.setViewName("nninfo/userinfo/userinfo_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goUserinfoAdd")
	public ModelAndView goUserinfoAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USER_NAME", pd.get("USERNAME"));
		mv.setViewName("nninfo/userinfo/userinfo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = userinfoService.findById(pd);	//根据ID读取
		mv.setViewName("nninfo/userinfo/userinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Userinfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			userinfoService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Userinfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("用户名");	//1
		titles.add("角色类型");	//2
		titles.add("状态");	//3
		titles.add("姓名");	//4
		titles.add("性别");	//5
		titles.add("身份证");	//6
		titles.add("电话");	//7
		titles.add("手机号码");	//8
		titles.add("地址");	//9
		titles.add("邮箱");	//10
		titles.add("QQ");	//11
		titles.add("微信");	//12
		titles.add("备注");	//13
		titles.add("预留1");	//14
		titles.add("预留2");	//15
		titles.add("预留3");	//16
		titles.add("预留4");	//17
		titles.add("预留5");	//18
		titles.add("预留6");	//19
		titles.add("预留7");	//20
		titles.add("预留8");	//21
		titles.add("预留9");	//22
		titles.add("预留10");	//23
		titles.add("支付宝");	//24
		dataMap.put("titles", titles);
		List<PageData> varOList = userinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("USER_NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("ROLE_TYPE"));	    //2
			vpd.put("var3", varOList.get(i).getString("STATUS"));	    //3
			vpd.put("var4", varOList.get(i).getString("NAME"));	    //4
			vpd.put("var5", varOList.get(i).getString("SEX"));	    //5
			vpd.put("var6", varOList.get(i).getString("PAPERS"));	    //6
			vpd.put("var7", varOList.get(i).getString("PHONE"));	    //7
			vpd.put("var8", varOList.get(i).getString("MOBIL"));	    //8
			vpd.put("var9", varOList.get(i).getString("ADRESS"));	    //9
			vpd.put("var10", varOList.get(i).getString("EMAIL"));	    //10
			vpd.put("var11", varOList.get(i).getString("QQ"));	    //11
			vpd.put("var12", varOList.get(i).getString("WECHAT"));	    //12
			vpd.put("var13", varOList.get(i).getString("REMARK"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL1"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL2"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL3"));	    //16
			vpd.put("var17", varOList.get(i).getString("YL4"));	    //17
			vpd.put("var18", varOList.get(i).getString("YL5"));	    //18
			vpd.put("var19", varOList.get(i).getString("YL6"));	    //19
			vpd.put("var20", varOList.get(i).getString("YL7"));	    //20
			vpd.put("var21", varOList.get(i).getString("YL8"));	    //21
			vpd.put("var22", varOList.get(i).getString("YL9"));	    //22
			vpd.put("var23", varOList.get(i).getString("YL10"));	    //23
			vpd.put("var24", varOList.get(i).getString("ZFB"));	    //24
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
