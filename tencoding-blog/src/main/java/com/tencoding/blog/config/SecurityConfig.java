package com.tencoding.blog.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration // IoC 관리 
@EnableWebSecurity // security filter로 등록 !  (필터커스텀) 
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증 처리를 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// /auth/login_form, auth/join_form --> /auth/** : auth뒤에 오는 걸 모두 허용하겠다
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable(); // 개발완료전 테스트시 사용 (실 서비스 시 풀어/  사용 안함 권장)
		
		http
		.authorizeHttpRequests()
			.antMatchers("/auth/**", "/" , "/js/**", "/image/**", "/css/**")  //허용하고 싶은 주소를 커스텀 
			.permitAll()			// 모든 권한 허용  
			.anyRequest()
			.authenticated()
		.and()
			.formLogin() 			//허용된 사용자가 아니면 로그인페이지로 보내기  
			.loginPage("/auth/login_form");
	}
	
	
}
