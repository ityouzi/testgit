package com.fh.controller.nnfiv.report;

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
import com.fh.entity.system.Dictionaries;
import com.fh.service.nnfiv.report.ReportManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.nnsci.PdPut;

/** 
 * 说明：汇报表
 * 创建人：NN Q174493573
 * 创建时间：2018-07-28
 */
@Controller
@RequestMapping(value="/report")
public class ReportController extends BaseController {
	
	String menuUrl = "report/list.do"; //菜单地址(权限用)
	@Resource(name="reportService")
	private ReportManager reportService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Report");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("REPORT_ID", this.get32UUID());	//主键
		pd.put("CRETETIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("MEMBER", Jurisdiction.getUsername());//获取当前用户名
//		pd.put("YL2", "");	//预留
		pd.put("YL3", "");	//预留
		pd.put("YL4", "");	//预留
		pd.put("YL5", "");	//预留
		pd.put("YL6", "");	//预留
		pd.put("YL7", "");	//预留
		pd.put("YL8", "");	//预留
		pd.put("YL9", "");	//预留
		pd.put("YL10", "");	//预留
		reportService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Report");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		reportService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Report");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.get("STATE");
		System.err.println(pd.get("MEMBER"));
		reportService.edit(pd);
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
		String username = Jurisdiction.getUsername();
		logBefore(logger, username+"列表${objectName}");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();		
		//判断当前用户的数据权限
		if(Jurisdiction.getDatarole().equals("个人用户")){
			pd.put("member", username);
		}	
		
		pd.put("BIANMA", "gongzuohuibao");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			//类型
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list2", varList2);
		}
		
		page.setPd(new PdPut().pdPut(pd));//时间检索
		List<PageData>	varList = reportService.list(page);	//列出Report列表
		
		System.err.println(varList);
		
		mv.setViewName("nnfiv/report/report/report_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEMBER", Jurisdiction.getUsername());//获取当前用户名	
		
		pd.put("BIANMA", "gongzuohuibao");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			//类型
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list2", varList2);
		}
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> List1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));	
		mv.addObject("list1", List1);
		mv.setViewName("nnfiv/report/report/report_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = reportService.findById(pd);//根据ID读取
		pd.put("BIANMA", "gongzuohuibao");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);		
		if(findByBianma2 != null){
			//类型					
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list2", varList2);
		}
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> List1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));		
		mv.addObject("list1", List1);
		mv.setViewName("nnfiv/report/report/report_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Report");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			reportService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Report到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("汇报人");	//1
		titles.add("创建时间");	//2
		titles.add("提交日期");	//3
		titles.add("汇报内容");	//4
		titles.add("是否查阅");	//5
		titles.add("标题");	//6
		titles.add("预留");	//7
		titles.add("预留");	//8
		titles.add("预留");	//9
		titles.add("预留");	//10
		titles.add("预留");	//11
		titles.add("预留");	//12
		titles.add("预留");	//13
		titles.add("预留");	//14
		titles.add("预留");	//15
		dataMap.put("titles", titles);
		List<PageData> varOList = reportService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("MEMBER"));	    //1
			vpd.put("var2", varOList.get(i).getString("CRETETIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("DATE"));	    //3
			vpd.put("var4", varOList.get(i).getString("CONTENT"));	    //4
			vpd.put("var5", varOList.get(i).getString("STATE"));	    //5
			vpd.put("var6", varOList.get(i).getString("TITLE"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL2"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL3"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL4"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL5"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL6"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL7"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL8"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL9"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL10"));	    //15
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
