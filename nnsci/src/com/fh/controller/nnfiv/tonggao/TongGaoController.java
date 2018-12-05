package com.fh.controller.nnfiv.tonggao;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
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
import com.fh.entity.system.User;
import com.fh.service.nnfiv.tonggao.TongGaoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.nnsci.PdPut;

/** 
 * 说明：通告文章
 * 创建人：NN Q174493573
 * 创建时间：2018-07-28
 */
@Controller
@RequestMapping(value="/tonggao")
public class TongGaoController extends BaseController {
	
	String menuUrl = "tonggao/list.do"; //菜单地址(权限用)
	@Resource(name="tonggaoService")
	private TongGaoManager tonggaoService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增TongGao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TONGGAO_ID", this.get32UUID());	//主键
		pd.put("DATE", Tools.date2Str(new Date()));	//创建时间
		pd.put("NAME",Jurisdiction.getUsername());
		tonggaoService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除TongGao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		tonggaoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改TongGao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		tonggaoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表TongGao");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData pd1 = new PageData();
		pd1 = this.getPageData();
		
		pd1.put("BIANMA", "tonggao");//通知类型检索
		PageData findByBianma = dictionariesService.findByBianma(pd1);
		if(findByBianma != null){
			String str = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(str);
			mv.addObject("varList1", varList1);
		}
		
		String datarole = Jurisdiction.getDatarole();
		if("个人用户".equals(datarole)){
			pd.put("NAME", Jurisdiction.getUsername());
		}
		
		page.setPd(new PdPut().pdPut(pd));
		List<PageData>	varList = tonggaoService.list(page);	//列出TongGao列表
		mv.setViewName("nnfiv/tonggao/tonggao/tonggao_list");
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
		
		
		pd.put("BIANMA", "tonggao");
		PageData findByBianma = dictionariesService.findByBianma(pd);	
		if(findByBianma != null){
			String str = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> list = dictionariesService.listSubDictByParentId(str);
			mv.addObject("list", list);
		}
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> list1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));
		mv.addObject("list1", list1);
		mv.setViewName("nnfiv/tonggao/tonggao/tonggao_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(Page  page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		pd.put("BIANMA", "tonggao");
		PageData findByBianma = dictionariesService.findByBianma(pd);		
		if(findByBianma != null){
			String str = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> list = dictionariesService.listSubDictByParentId(str);
			mv.addObject("list", list);
		}
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> list1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));
		
		pd = tonggaoService.findById(pd);	//根据ID读取
		mv.setViewName("nnfiv/tonggao/tonggao/tonggao_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("list1", list1);
		return mv;
	}	
	
	
	 /**去详情页面
		 * @param
		 * @throws Exception
		 */
		@RequestMapping(value="/goDetail")
		public ModelAndView goDetail(Page  page)throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			pd = tonggaoService.findById(pd);	//根据ID读取
			mv.setViewName("nnfiv/tonggao/tonggao/tonggao_detail");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除TongGao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			tonggaoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出TongGao到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("创建人");	//1
		titles.add("创建时间");	//2
		titles.add("标题");	//3
		titles.add("通告内容");	//4
		titles.add("状态");	//5
		titles.add("通告类型");	//6
		titles.add("预留字段");	//7
		titles.add("预留字段");	//8
		titles.add("预留字段");	//9
		titles.add("预留字段");	//10
		titles.add("预留字段");	//11
		titles.add("预留字段");	//12
		titles.add("预留字段");	//13
		titles.add("预留字段");	//14
		titles.add("预留字段");	//15
		dataMap.put("titles", titles);
		List<PageData> varOList = tonggaoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("DATE"));	    //2
			vpd.put("var3", varOList.get(i).getString("TITLE"));	    //3
			vpd.put("var4", varOList.get(i).getString("CONTENT"));	    //4
			vpd.put("var5", varOList.get(i).getString("STATE"));	    //5
			vpd.put("var6", varOList.get(i).getString("TYPE"));	    //6
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
