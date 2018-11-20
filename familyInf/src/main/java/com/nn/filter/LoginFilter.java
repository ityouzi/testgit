package com.nn.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nn.bean.User;
import com.nn.controller.BaseController;
import com.nn.utils.Msg;

public class LoginFilter implements HandlerInterceptor {
	
	//获取EhCacheCacheManager类  
    EhCacheCacheManager cacheCacheManager=ApplicationContextUtils.applicationContext.getBean(EhCacheCacheManager.class);  
    //获取CacheManager类  
    CacheManager cacheManager=cacheCacheManager.getCacheManager();  
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		String uri = request.getRequestURI();
        if(uri.equals("/familyInf/usermsg/login")){
        	return true;
        }
        
//        TimeIntercept.longCount ++ ;
//    	if(TimeIntercept.datetime==null){
//    		TimeIntercept.datetime = new Date();
//    	}else{
//    		Date currDate = new Date();
//    		long ddLong = currDate.getTime();
//    		long dLong = TimeIntercept.datetime.getTime();
//    		if(ddLong - dLong < 1000){
//    			return false;
//    		}else{
//    			TimeIntercept.datetime = currDate;
//    		}
//    	}
        
		String token = request.getParameter("token");
		System.out.println(token);
		Cache cache = cacheManager.getCache("data-cache");
		
		Element e = cache.get(token);
		if(e==null){
			BaseController.outJson(JSONObject.fromObject(Msg.fail().add("result", "当前没有登录，请登录!")).toString(), response);
			return false;
		}else{
			if(e.getObjectValue()!=null){
				User sysUser=(User) e.getObjectValue();
				if(sysUser==null){
					BaseController.outJson(JSONObject.fromObject(Msg.fail().add("result", "session过期，请重新登录!")).toString(), response);
					return false;
				}
				return true;
			}
			return false;
		}
    }

   /**
     * 该方法将在Controller执行之后，返回视图之前执行，modelAndView表示请求Controller处理之后返回的Model和View对象，所以可以在
     * 这个方法中修改modelAndView的属性，从而达到改变返回的模型和视图的效果。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {

    }

    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}