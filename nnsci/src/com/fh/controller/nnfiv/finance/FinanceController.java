package com.fh.controller.nnfiv.finance;

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
import com.fh.service.nnfiv.finance.FinanceManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.nnsci.PdPut;

/** 
 * 说明：财务收支
 * 创建人：NN Q174493573
 * 创建时间：2018-07-29
 */
@Controller
@RequestMapping(value="/finance")
public class FinanceController extends BaseController {
	
	String menuUrl = "finance/list.do"; //菜单地址(权限用)
	@Resource(name="financeService")
	private FinanceManager financeService;
	
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Finance");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("FINANCE_ID", this.get32UUID());	//主键
		pd.put("CREATE_NAME", Jurisdiction.getUsername());	//创建人
		pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//创建时间
			
		
//		pd.put("YL3", "");	//预留3
		pd.put("YL4", "");	//预留4
		pd.put("YL5", "");	//预留5
		pd.put("YL6", "");	//预留6
		pd.put("YL7", "");	//预留7
		pd.put("YL8", "");	//预留8
		pd.put("YL9", "");	//预留9
		pd.put("YL10", "");	//预留10
		
		System.err.println(pd.get("YL2"));
		
		pd.put("YL1", pd.get("YL1"));  //支付方式
		pd.put("YL2", pd.get("YL2"));	//备注
		
		financeService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Finance");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		financeService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Finance");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		
		financeService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Finance");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData pdd = new PageData();
		pdd.put("BIANMA", "zhjy");
		pdd = dictionariesService.findByBianma(pdd);
		List<Dictionaries> listd = dictionariesService.listSubDictByParentId(pdd.getString("DICTIONARIES_ID"));
		mv.addObject("listd", listd);
		
		String datarole = Jurisdiction.getDatarole();
		System.err.println(datarole+"@@@@@@@@@@@@@@@@@@@@@@");
		if("个人用户".equals(datarole) || datarole == null || "".equals(datarole)){
			pd.put("CREATE_NAME", Jurisdiction.getUsername());
		}
		page.setPd(new PdPut().pdPut(pd));
		List<PageData>	varList = financeService.list(page);	//列出Finance列表
		mv.setViewName("nnfiv/caiwu/finance/finance_list");
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
		
		pd.put("BIANMA", "zhifukemu");
		PageData findByBianma = dictionariesService.findByBianma(pd);
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> List1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));
		if(findByBianma != null){
			String str = (String) findByBianma.get("DICTIONARIES_ID");
			pd.put("DICTIONARIES_ID", str);
			page.setPd(new PdPut().pdPut(pd));
			List<PageData> list = dictionariesService.list(page);
			mv.addObject("list", list);
		}
		mv.addObject("list1", List1);
		mv.setViewName("nnfiv/caiwu/finance/finance_edit");
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
		pd = financeService.findById(pd);	//根据ID读取
		
		pd.put("BIANMA", "zhifukemu");
		PageData findByBianma = dictionariesService.findByBianma(pd);
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> List1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));
		if(findByBianma != null){
			String str = (String) findByBianma.get("DICTIONARIES_ID");
			pd.put("DICTIONARIES_ID", str);
			page.setPd(new PdPut().pdPut(pd));
			List<PageData> list = dictionariesService.list(page);
			mv.addObject("list", list);
		}
		mv.addObject("list1", List1);
		mv.setViewName("nnfiv/caiwu/finance/finance_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Finance");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			financeService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Finance到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("创建人");	//1
		titles.add("创建时间");	//2
		titles.add("发生人");	//3
		titles.add("金额");	//4
		titles.add("收支类型");	//5
		titles.add("支付时间");	//6
		titles.add("用途");	//7
		titles.add("预留1");	//8
		titles.add("预留2");	//9
		titles.add("预留3");	//10
		titles.add("预留4");	//11
		titles.add("预留5");	//12
		titles.add("预留6");	//13
		titles.add("预留7");	//14
		titles.add("预留8");	//15
		titles.add("预留9");	//16
		titles.add("预留10");	//17
		dataMap.put("titles", titles);
		List<PageData> varOList = financeService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("CREATE_NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_TIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("USER_NAME"));	    //3
			vpd.put("var4", varOList.get(i).get("PRICE").toString());	//4
			vpd.put("var5", varOList.get(i).getString("PRICE_TYPE"));	    //5
			vpd.put("var6", varOList.get(i).getString("PRICE_TIME"));	    //6
			vpd.put("var7", varOList.get(i).getString("REMAKE"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL1"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL2"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL3"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL4"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL5"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL6"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL7"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL8"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL9"));	    //16
			vpd.put("var17", varOList.get(i).getString("YL10"));	    //17
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
