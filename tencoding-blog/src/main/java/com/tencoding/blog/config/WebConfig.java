package com.tencoding.blog.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// upload/** 낚아 채감 url 패턴이 /upload/파일명 --> 가져감  
	    registry.addResourceHandler("/upload/**")
	    // 실제 파일 서버 물리적인 경로  
	      .addResourceLocations("file:///"+uploadFolder)
	      .setCachePeriod(3600) //한번 열었던 녀석이면 캐시기능을 써서 보
	      .resourceChain(true) // 리소스 찾는 것을 최적화 하기 위해 설정  
	      .addResolver(new PathResourceResolver());

//	    List<String> imageFolders = Arrays.asList("email", "etc");
//	    for(String imageFolder : imageFolders) {
//	      registry.addResourceHandler("/static/img/" +imageFolder +"/**")
//	        .addResourceLocations("file:///" + uploadFolder + imageFolder +"/")
//	        .setCachePeriod(3600)
//	        .resourceChain(true)
//	        .addResolver(new PathResourceResolver());
//	    }
	}
}
