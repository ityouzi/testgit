package com.fh.controller.eterm.b2bairlines;

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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.FileDownload;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelRead;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.PathUtil;
import com.fh.util.nnsci.PdPut;
import com.fh.service.eterm.b2bairlines.B2BAirLinesManager;
import com.fh.service.system.fhlog.FHlogManager;
import com.fh.service.userinfo.impl.UserinfoService;

/** 
 * 说明：航线管理
 * 创建人：NN Q174493573
 * 创建时间：2018-06-01
 */
@Controller
@RequestMapping(value="/b2bairlines")
public class B2BAirLinesController extends BaseController {
	
	String menuUrl = "b2bairlines/list.do"; //菜单地址(权限用)
	@Resource(name="b2bairlinesService")
	private B2BAirLinesManager b2bairlinesService;
	@Resource(name="fhlogService")
	private FHlogManager FHLOG;
	@Autowired
	private UserinfoService userinfoService;
	
	/**
	 * 启动任务
	 */
	@RequestMapping(value="/toExc")
	public String toExc(HttpServletRequest request) throws Exception{
		final String userName = Jurisdiction.getUsername();
		final String cookie = request.getParameter("cookie");
		logBefore(logger, userName+"新增B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd_user = new PageData();
		pd_user.put("USER_NAME", Jurisdiction.getUsername());
		Page pageUser = new Page();
		pageUser.setPd(pd_user);
		pd_user = userinfoService.list(pageUser).get(0);
		pd_user.put("YL1", "1");
		userinfoService.edit(pd_user);
		b2bairlinesService.toExc(pd_user,cookie);
		return "0";
	}
	
	/**
	 * 修改日期
	 */
	@RequestMapping(value="/setDate")
	public String setDate(HttpServletRequest request) throws Exception{
		String date = request.getParameter("date");
		System.out.println(date+"***********************************");
		String userName = Jurisdiction.getUsername();
		logBefore(logger, userName+"新增B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd.put("YL1", date);
		b2bairlinesService.edits(pd);
		return "0";
	}
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("B2BAIRLINES_ID", this.get32UUID());	//主键
		pd.put("YL2", "0");
		b2bairlinesService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		b2bairlinesService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**删除全部
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/deletes")
	@ResponseBody
	public Object deletes() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"全部删除B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		b2bairlinesService.deletes();
		return "ok";
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		b2bairlinesService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表B2BAirLines");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(new PdPut().pdPut(pd));
		List<PageData> varList = b2bairlinesService.list(page);	//列出B2BAirLines列表
		
		PageData pd_user = new PageData();
		pd_user.put("USER_NAME", Jurisdiction.getUsername());
		Page pageUser = new Page();
		pageUser.setPd(pd_user);
		pd_user = userinfoService.list(pageUser).get(0);
		
		mv.setViewName("eterm/b2bairlines/b2bairlines_list");
		mv.addObject("varList", varList);
		mv.addObject("pd_user", pd_user);
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
		mv.setViewName("eterm/b2bairlines/b2bairlines_edit");
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
		pd = b2bairlinesService.findById(pd);	//根据ID读取
		mv.setViewName("eterm/b2bairlines/b2bairlines_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除B2BAirLines");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			b2bairlinesService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出B2BAirLines到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("PID");	//1
		titles.add("出发城市");	//2
		titles.add("到达城市");	//3
		titles.add("出发城市三字码");	//4
		titles.add("到达城市三字码");	//5
		titles.add("备注");	//6
		titles.add("日期");	//7
		titles.add("状态");	//8
	/*	titles.add("备注10");	//9
		titles.add("备注11");	//10
		titles.add("备注12");	//11
		titles.add("备注13");	//12
		titles.add("备注14");	//13
		titles.add("备注15");	//14
		titles.add("备注16");	//15
*/		dataMap.put("titles", titles);
		List<PageData> varOList = b2bairlinesService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PID"));	    //1
			vpd.put("var2", varOList.get(i).getString("CFCITY"));	    //2
			vpd.put("var3", varOList.get(i).getString("DDCITY"));	    //3
			vpd.put("var4", varOList.get(i).getString("CFCODE"));	    //4
			vpd.put("var5", varOList.get(i).getString("DDCODE"));	    //5
			vpd.put("var6", varOList.get(i).getString("REMARK"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL1"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL2"));	    //8
			/*vpd.put("var9", varOList.get(i).getString("YL3"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL4"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL5"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL6"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL7"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL8"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL9"));	    //15
*/			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("eterm/b2bairlines/uploadexcel");
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcel")
	public void downExcel(HttpServletResponse response)throws Exception{
		FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "Users.xls", "Users.xls");
	}
	
	/**从EXCEL导入到数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		FHLOG.save(Jurisdiction.getUsername(), "从EXCEL导入到数据库");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
			String fileName =  FileUpload.fileUp(file, filePath, "b2bairlinesexcel");							//执行上传
			List<PageData> listPd = (List)ObjectExcelRead.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 1:从第2行开始；0:从第A列开始；0:第0个sheet
			/*存入数据库操作======================================*/
			pd.put("PID", "");					//pid
			pd.put("CFCITY", "");				//出发城市
			pd.put("DDCITY", "");				//到达城市
			pd.put("CFCODE", "");				//出发城市三字码
			pd.put("DDCODE", "");				//到达城市三字码
			pd.put("REMARK", "");				//备注
			pd.put("YL1", "");					//日期
			pd.put("YL2", "default");			//状态
			/**
			 */
			for(int i=0;i<listPd.size();i++){		
				pd.put("B2BAIRLINES_ID", this.get32UUID());								//ID
				pd.put("PID", listPd.get(i).getString("var0"));							//pid
				pd.put("CFCITY", listPd.get(i).getString("var1"));						//出发城市
				pd.put("DDCITY",listPd.get(i).getString("var2"));						//到达城市
				pd.put("CFCODE", listPd.get(i).getString("var3"));						//出发城市三字码
				pd.put("DDCODE", listPd.get(i).getString("var4"));						//到达城市三字码
				pd.put("REMARK", listPd.get(i).getString("var5"));						//备注
				pd.put("YL1", listPd.get(i).getString("var6"));							//日期
				pd.put("YL2", listPd.get(i).getString("var7"));						//状态
				
				b2bairlinesService.save(pd);
			}
			/*存入数据库操作======================================*/
			mv.addObject("msg","success");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
