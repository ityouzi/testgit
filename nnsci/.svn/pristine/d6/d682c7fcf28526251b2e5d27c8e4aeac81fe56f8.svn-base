package com.fh.controller.nnfiv.xiangmu;

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

import sun.security.action.PutAllAction;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.service.nnfiv.xiangmu.XiangMuManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.nnsci.PdPut;

/** 
 * 说明：项目进度表
 * 创建人：NN Q174493573
 * 创建时间：2018-07-28
 */
@Controller
@RequestMapping(value="/xiangmu")
public class XiangMuController extends BaseController {
	
	String menuUrl = "xiangmu/list.do"; //菜单地址(权限用)
	@Resource(name="xiangmuService")
	private XiangMuManager xiangmuService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService; 
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增XiangMu");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("XIANGMU_ID", this.get32UUID());	//主键
		pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//建表时间
		pd.put("DUTY", Jurisdiction.getUsername());
		pd.put("YL1", "");	//预留1
//		pd.put("YL2", "");	//预留2
		pd.put("YL3", "");	//预留3
		pd.put("YL4", "");	//预留4
		pd.put("YL5", "");	//预留5
		pd.put("YL6", "");	//预留6
		pd.put("YL7", "");	//预留7
		pd.put("YL8", "");	//预留8
		pd.put("YL9", "");	//预留9
		pd.put("YL10", "");	//预留10
		xiangmuService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除XiangMu");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		xiangmuService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改XiangMu");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		xiangmuService.edit(pd);
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
		logBefore(logger, username+"列表XiangMu");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BIANMA", "state");
		PageData findByBianma = dictionariesService.findByBianma(pd);		
		if(findByBianma != null){
			String ID = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> list = dictionariesService.listSubDictByParentId(ID);
			mv.addObject("list", list);
		}	
		if(Jurisdiction.getDatarole().equals("个人用户")){
			pd.put("duty",username);
		}
		
		page.setPd(new PdPut().pdPut(pd));
		List<PageData>	varList = xiangmuService.list(page);	//列出XiangMu列表
		mv.setViewName("nnfiv/xiangmu/xiangmu_list");
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
		pd.put("DUTY", Jurisdiction.getUsername());		
		pd.put("BIANMA", "state");
		PageData findByBianma = dictionariesService.findByBianma(pd);		
		if(findByBianma != null){
			String id = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> list = dictionariesService.listSubDictByParentId(id);
			mv.addObject("list", list);
		}
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> List1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));
		mv.addObject("list1", List1);
		mv.setViewName("nnfiv/xiangmu/xiangmu_edit");
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
		pd = xiangmuService.findById(pd);	//根据ID读取
		
		pd.put("BIANMA", "state");
		PageData findByBianma = dictionariesService.findByBianma(pd);		
		if(findByBianma != null){
			String ID = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> list = dictionariesService.listSubDictByParentId(ID);
			mv.addObject("list", list);
		}
		PageData pd1=new PageData();
		pd1.put("BIANMA", "quanxian");//获取数据字典的字节码
		PageData findByBianma1 = dictionariesService.findByBianma(pd1);
		List<Dictionaries> List1 = dictionariesService.listSubDictByParentId(findByBianma1.getString("DICTIONARIES_ID"));
		pd.put("DUTY", pd.get("DUTY"));
		System.err.println(pd.get("DUTY"));
		mv.setViewName("nnfiv/xiangmu/xiangmu_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("list1", List1);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除XiangMu");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			xiangmuService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出XiangMu到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("项目名称");	//1
		titles.add("项目成员");	//2
		titles.add("开始日期");	//3
		titles.add("状态");	//4
		titles.add("建表时间");	//5
		titles.add("负责人");	//6
		titles.add("预留1");	//7
		titles.add("预留2");	//8
		titles.add("预留3");	//9
		titles.add("预留4");	//10
		titles.add("预留5");	//11
		titles.add("预留6");	//12
		titles.add("预留7");	//13
		titles.add("预留8");	//14
		titles.add("预留9");	//15
		titles.add("预留10");	//16
		titles.add("项目信息");	//17
		dataMap.put("titles", titles);
		List<PageData> varOList = xiangmuService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PROJECTNAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("NUMBER"));	    //2
			vpd.put("var3", varOList.get(i).getString("BEGIN_DATE"));	    //3
			vpd.put("var4", varOList.get(i).getString("STATUS"));	    //4
			vpd.put("var5", varOList.get(i).getString("CREATE_TIME"));	    //5
			vpd.put("var6", varOList.get(i).getString("DUTY"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL1"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL2"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL3"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL4"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL5"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL6"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL7"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL8"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL9"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL10"));	    //16
			vpd.put("var17", varOList.get(i).getString("INFORMATION"));	    //17
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
