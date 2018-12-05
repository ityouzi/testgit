package com.fh.controller.nnfiv.comment;

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
import com.fh.util.AppUtil;
import com.fh.util.MyTools;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.nnsci.PdPut;
import com.fh.service.nnfiv.comment.CommentManager;
import com.fh.service.nnfiv.question.QuestionManager;
import com.fh.service.nnfiv.question.impl.QuestionService;

/** 
 * 说明：评论表
 * 创建人：NN Q174493573
 * 创建时间：2018-07-28
 */
@Controller
@RequestMapping(value="/comment")
public class CommentController extends BaseController {
	
	String menuUrl = "comment/list.do"; //菜单地址(权限用)
	@Resource(name="questionService")
	private QuestionManager questionService;
	@Resource(name="commentService")
	private CommentManager commentService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Comment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("COMMENT_ID", this.get32UUID());	//主键
		pd.put("CREATE_TIME", MyTools.getTime());
		commentService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Comment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		commentService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Comment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		commentService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Comment");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		System.out.println(pd);
		pd.put("PID", pd.get("pid"));
		page.setPd(new PdPut().pdPut(pd));
		List<PageData>	varList = commentService.list(page);	//列出Comment列表
		mv.setViewName("nnfiv/comment/comment/comment_list");
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
		mv.setViewName("nnfiv/comment/comment/comment_edit");
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
		System.out.println(pd);
		pd = commentService.findById(pd);	//根据ID读取
		mv.setViewName("nnfiv/comment/comment/comment_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Comment");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			commentService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Comment到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("父ID");	//1
		titles.add("创建人");	//2
		titles.add("创建时间");	//3
		titles.add("内容");	//4
		titles.add("状态");	//5
		titles.add("预留1");	//6
		titles.add("预留2");	//7
		titles.add("预留3");	//8
		dataMap.put("titles", titles);
		List<PageData> varOList = commentService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PID"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATOR"));	    //2
			vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("CONTENT"));	    //4
			vpd.put("var5", varOList.get(i).getString("STATUS"));	    //5
			vpd.put("var6", varOList.get(i).getString("YL1"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL2"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL3"));	    //8
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@RequestMapping(value="/reply")
	public ModelAndView reply()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd1=new PageData();
		pd = this.getPageData();
		pd.put("QUESTION_ID", pd.get("QUESTION_ID"));
		pd1.put("pid", pd.get("QUESTION_ID"));
		Page page=new Page();
		page.setPd(pd1);
		PageData pd2 = new PageData();
		pd2.put("COMMENT_ID", this.get32UUID());	//主键
		pd2.put("CREATE_TIME", MyTools.getTime());
		pd2.put("CREATOR",Jurisdiction.getUsername());
		System.out.println(Jurisdiction.getUsername());
		pd2.put("PID",pd.get("QUESTION_ID"));
		pd2.put("CONTENT", pd.get("CONTENT"));
		commentService.save(pd2);
		pd = questionService.findById(pd);
	
		List<PageData> commentlist=commentService.list(page);
		mv.setViewName("nnfiv/question/question/detail");
		mv.addObject("msg", "add");
		mv.addObject("pd", pd);
		mv.addObject("commentlist", commentlist);
		return mv;
		
		/*ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		System.out.println(pd+" do there");
		
		pd.put("COMMENT_ID", this.get32UUID());	//主键
		pd.put("CREATE_TIME", MyTools.getTime());
		pd.put("PID",pd.get("QUESTION_ID"));
		pd.put("CREATOR", pd.get("CREATOR"));
		commentService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
		System.err.println(pd.get("CONTENT"));*/
	}	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
