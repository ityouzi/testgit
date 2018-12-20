package com.fh.util.nnsci;

import com.fh.util.PageData;

public class PdPut {
	
	public PageData pdPut(PageData pd){
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastStart = pd.getString("lastStart");	//开始时间
		String lastEnd = pd.getString("lastEnd");		//结束时间
		
		if(lastStart != null && !"".equals(lastStart)){
			pd.put("lastStart", lastStart+" 00:00:00");	
		}
		if(lastEnd != null && !"".equals(lastEnd)){
			pd.put("lastEnd", lastEnd+" 59:59:59");
		}
		String name = pd.getString("name");
		if(name != null && !"".equals(name)){
			pd.put("name", name);
		}
		return pd;
	}

}