package com.nn.filter;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter{

    /**
     * 注册 拦截器
     */
//   @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginFilter());
//    }
 
    @Bean()
    public Filter AuthFilter() {
      return new AjaxFilter();
    }
    /**
     * 图片上传大小控制
     * */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
       MultipartConfigFactory factory = new MultipartConfigFactory();
       //单个文件最大
       factory.setMaxFileSize("50MB");
       // 设置总上传数据总大小
       factory.setMaxRequestSize("100MB");
       //文件上传临时路径
//       factory.setLocation("/data/dubbo/cache");
       return factory.createMultipartConfig();
    }

}