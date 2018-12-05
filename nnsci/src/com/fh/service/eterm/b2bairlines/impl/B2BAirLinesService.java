package com.fh.service.eterm.b2bairlines.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.eterm.b2bairlines.B2BAirLinesManager;
import com.fh.util.HTTPUtils;
import com.fh.util.PageData;
import com.fh.util.RandemUtils;
import com.fh.util.TimeUtils;

/** 
 * 说明： 航线管理
 * 创建人：NN Q174493573
 * 创建时间：2018-06-01
 * @version
 */
@Service("b2bairlinesService")
public class B2BAirLinesService implements B2BAirLinesManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("B2BAirLinesMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("B2BAirLinesMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("B2BAirLinesMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("B2BAirLinesMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("B2BAirLinesMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("B2BAirLinesMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("B2BAirLinesMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public boolean toExc(final PageData pd_user,final String cookie) {
		String userName = pd_user.getString("USER_NAME");
		PageData pd = new PageData();
		pd.put("PID", userName);
		try{
			final List<PageData> list = (List<PageData>)dao.findForList("B2BAirLinesMapper.listAll", pd);
			new Thread(new Runnable() {
			    public void run() {
			    	try {
			    		getData(list,cookie,pd_user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}) {}.start();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void getData(List<PageData> list,String cookie1,PageData pd_user) throws Exception{
		String query_url = "https://tv.travelsky.com/tvb2b/OWShoppingQuery.do";
		int i = 0;
		System.err.println(list.size()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		for(PageData p : list){
			i++;
			System.out.println(i+"次************************************************************");
			String cfcode = p.getString("CFCODE");
			String ddcode = p.getString("DDCODE");
			String queryDate = p.getString("YL1");
			try{
				String cookie = cookie1;
				Map<String, String> parameter = new HashMap<String, String>();
				parameter.put("tripType", "0");
				parameter.put("orgCity", cfcode);
				parameter.put("dstCity", ddcode);
				parameter.put("takeOffDate", queryDate);
				parameter.put("groupType", "0");
				String result2 = HTTPUtils.post(query_url,parameter, null,cookie);
				
				Document doc = Jsoup.parse(result2);
				
//				System.err.println(doc);
				
				Element e = doc.getElementsByClass("confirm_p2").get(0);
	
				Elements es = e.getElementsByClass("bk_list");
				for(Element ee : es){
					
					Element eleft = ee.getElementsByClass("bk_lis_lef").get(0).getElementsByClass("rig").get(0);
					
					Elements els = eleft.getElementsByTag("p");
					
					String hbh = els.get(0).text();
					String cfTime = els.get(1).text();
					String ddTime = els.get(2).text();
					
					Elements ers = ee.getElementsByClass("bk_lis_rig");
					
					for(Element er : ers){
						Element edl = er.getElementsByTag("dl").get(0);
						String cabin = edl.getElementsByTag("dt").get(0).text();
						Elements edds = edl.getElementsByTag("dd");
						String seats = edds.get(0).text();
						String price = edds.get(1).text();
						String ret = edds.get(2).text();
						
						PageData pd = new PageData();
						pd.put("HBH", hbh);
						pd.put("CABIN", cabin);
						pd.put("PRICE", price);
						pd.put("RET", ret);
						Page pg = new Page();
						pg.setPd(pd);
						List<PageData> listpd = (List<PageData>) dao.findForList("B2BDataMapper.datalistPage1", pg);
						if(listpd.size()>0){
							pd = listpd.get(0);
							String d = (String) pd.get("YL3");
							String dd = d + queryDate+",";
							System.out.println(d+"***"+dd);
							pd.put("YL3", pd.get("YL3")+queryDate+",");
							dao.update("B2BDataMapper.edit", pd);
						}else{
							pd.put("PID", p.getString("B2BAIRLINES_ID"));
							pd.put("SEATS", seats);
							pd.put("CFTIME", cfTime);
							pd.put("DDTIME", ddTime);
							pd.put("B2BDATA_ID", TimeUtils.getTimeFormat("YYYYMMddHHmmssSSS")+RandemUtils.getRdStr(6));
							pd.put("EXCTIME", new Date());
							pd.put("YL1", cfcode);
							pd.put("YL2", ddcode);
							pd.put("YL3", queryDate+",");
							
							System.out.println(hbh+"--"+cfTime+"--"+ddTime+"--"+cabin+"--"+seats+"--"+price+"--"+ret);
							dao.save("B2BDataMapper.save", pd);
						}
					}
				}
				p.put("YL2", "1");
				dao.update("B2BAirLinesMapper.edit", p);
			}catch (Exception e2) {
				e2.printStackTrace();
				p.put("YL2", "2");
				dao.update("B2BAirLinesMapper.edit", p);
				continue;
			}
		}
		pd_user.put("YL1", "0");
		dao.update("UserinfoMapper.edit", pd_user);
	}

	@Override
	public void edits(PageData pd) throws Exception {
		dao.update("B2BAirLinesMapper.edits", pd);
	}
	
	@Override
	public void deletes() throws Exception {
		dao.delete("B2BAirLinesMapper.deletes",null);
	}
	
}

