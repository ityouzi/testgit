package com.fh.controller.nnfiv.question;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.fh.service.nnfiv.comment.CommentManager;
import com.fh.service.nnfiv.question.QuestionManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.MyTools;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.nnsci.PdPut;

/** 
 * 说明：问题表
 * 创建人：NN Q174493573
 * 创建时间：2018-07-28
 */
@Controller
@RequestMapping(value="/question")
public class QuestionController extends BaseController {
	
	String menuUrl = "question/list.do"; //菜单地址(权限用)
	@Resource(name="questionService")
	private QuestionManager questionService;
	@Resource(name="commentService")
	private CommentManager commentService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Question");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("QUESTION_ID", this.get32UUID());	//主键
		pd.put("CREATE_TIME", MyTools.getTime());
		pd.put("CREATOR",Jurisdiction.getUsername());
		questionService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Question");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		questionService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Question");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		pd = this.getPageData();
		pd2 = questionService.findById(pd);									//根据ID读取
		String CREATOR = pd2.getString("CREATOR");							//创建人		
		String CREATE_TIME = pd2.getString("CREATE_TIME");					//创建时间
		pd.put("CREATOR", CREATOR);
		pd.put("CREATE_TIME", CREATE_TIME);
		questionService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Question");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BIANMA", "wentileixing");
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma!=null){
			String string = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> list = dictionariesService.listSubDictByParentId(string);
			mv.addObject("varLi", list);
		}
		
		
		String datarole = Jurisdiction.getDatarole();
		if("个人用户".equals(datarole)){
			pd.put("CREATOR", Jurisdiction.getUsername());
		}
		page.setPd(pd);
		List<PageData>	varList = questionService.list(page);	//列出Question列表
		mv.setViewName("nnfiv/question/question/question_list");
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
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BIANMA", "wentileixing");
		pd = dictionariesService.findByBianma(pd);
		List<Dictionaries> list = dictionariesService.listSubDictByParentId(pd.getString("DICTIONARIES_ID"));
		mv.setViewName("nnfiv/question/question/question_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		mv.addObject("list",list);
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
		PageData pd2 = new PageData();
		pd = this.getPageData();
		pd = questionService.findById(pd);						//根据ID读取
		pd2.put("BIANMA", "wentileixing");						
		pd2 = dictionariesService.findByBianma(pd2);			//获取问题类型编码
		List<Dictionaries> list = dictionariesService.listSubDictByParentId(pd2.getString("DICTIONARIES_ID"));
		mv.setViewName("nnfiv/question/question/question_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("list",list);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Question");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			questionService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Question到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("创建人");	//1
		titles.add("创建时间");	//2
		titles.add("状态");	//3
		titles.add("问题");	//4
		titles.add("类型");	//5
		titles.add("预留1");	//6
		titles.add("预留2");	//7
		titles.add("预留3");	//8
		titles.add("预留4");	//9
		titles.add("预留5");	//10
		titles.add("预留6");	//11
		titles.add("预留7");	//12
		titles.add("预留8");	//13
		titles.add("预留9");	//14
		titles.add("预留10");	//15
		dataMap.put("titles", titles);
		List<PageData> varOList = questionService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("CREATOR"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_TIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("STATUS"));	    //3
			vpd.put("var4", varOList.get(i).getString("QUESTION"));	    //4
			vpd.put("var5", varOList.get(i).getString("TYPE"));	    //5
			vpd.put("var6", varOList.get(i).getString("YL1"));	    //6
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
	@RequestMapping(value="/goReply")
	public ModelAndView reply()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = questionService.findById(pd);
		mv.setViewName("nnfiv/question/question/askdec");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	@RequestMapping(value="/detail")
	public ModelAndView detail()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd1 = new PageData();
		pd = this.getPageData();
		String questionid = pd.getString("questionid");
		pd.put("QUESTION_ID", questionid);
		pd1.put("pid", questionid);
		Page page=new Page();
		page.setPd(pd1);
		pd = questionService.findById(pd);
		List<PageData> commentlist=commentService.list(page);
		mv.setViewName("nnfiv/question/question/detail");
		mv.addObject("msg", "add");
		mv.addObject("pd", pd);
		mv.addObject("commentlist", commentlist);
		return mv;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
