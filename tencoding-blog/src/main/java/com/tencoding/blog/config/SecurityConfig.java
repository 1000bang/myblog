package com.tencoding.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.tencoding.blog.auth.PrincipalDetailService;

@SuppressWarnings("deprecation")
@Configuration // IoC 관리 
//@EnableJpaRepositories
@EnableWebSecurity // security filter로 등록 !  (필터커스텀) 
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증 처리를 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//becrypt 방식으로 인코딩해주는 녀석  
	// 왜 여기서? IoC관리하고 싶어서 어떻게 쓰나 di
	@Bean
	public  BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	// /auth/login_form, auth/join_form --> /auth/** : auth뒤에 오는 걸 모두 허용하겠다
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		//http.csrf().disable(); // 개발완료전 테스트시 사용 (실 서비스 시 풀어/  사용 안함 권장)
		
		http
			.csrf()
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //httponly는 읽기만하는 기능을 해제함 
		
		.and()
			.authorizeHttpRequests()
			.antMatchers("/auth/**", "/" , "/js/**", "/image/**", "/css/**")  //허용하고 싶은 주소를 커스텀 
			.permitAll()			// 모든 권한 허용  
			.anyRequest()
			.authenticated()
		.and()
			.formLogin() 				//허용된 사용자가 아니면 로그인페이지로 보내기  
			.loginPage("/auth/login_form")
			.loginProcessingUrl("/auth/loginProc")  //이 요청이 오면 catch해라 
			.defaultSuccessUrl("/") 	 //성공시 이동경로  
			//.failureUrl("/")  		//실패시 이동 경로 
			;
		
	}
	
	
	// >>>> AuthenticationManager 생성하고 여기서 관리
	/*
	 * 회원정보 수정시에 authentication 객체를 생성하기 위해 AuthenticationManager가 필요하다 
	 * 이 시점에 AuthenticationManager를 IoC 처리 
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailsService 들어갈 Object 만들어주어야 한다. 
		// 2. passwordEncoder 우리가 사용하는 해시 암호와 함수를 알려 주어야 한다. 
		
		// 1 우리가 커스텀한 녀석을 넣어야 한다.
		// 2 BCryptPasswordEncoder 사용해서 암호화하였다. 
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	
	}
	
	
}