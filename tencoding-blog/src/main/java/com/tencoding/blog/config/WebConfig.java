package com.tencoding.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	//등록 + 객체 : configuration
//	@Bean
//	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean(){
//		 FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//		 filterRegistrationBean.setFilter(new XssEscapeServletFilter());
//		 filterRegistrationBean.setOrder(1);
//		 filterRegistrationBean.addUrlPatterns("/*");
//	        return filterRegistrationBean;
//		
//	}
}
