package com.tencoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tencoding.blog.dto.User;

/*
 * 시큐리티가 로그인 요청을 가로채서 
 * 로그인을 처리하고 (DB접근해서 확인) 
 * 완료되면 UserDetail 타입에 오브젝트를 시큐리티에 공유한 세션 저장소에 장을 해준다. 
 * (우리가 새롭게 정의한 Object로 처리할 예정)
 */
public class PrincipalDetail implements UserDetails{

	
	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	// 계정의 권한을 반환 처리 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			
			@Override
			// "ROLE_" 는 스프링 시큐리티 사용시 프리픽스로 무조건 넣어야한다. 
			public String getAuthority() {
				return "ROLE_" +user.getRole();
			}
		});
		return collection;
	}

	//람다표현식으로 만들 수 있음 
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> collection = new ArrayList<>();
//		collection.add(()->{
//			return "ROLE_" + user.getRole();
//		});
//		return collection;
//	}
	
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴 false = 만료 
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	//계정이 잠김여부 확인   false = 잠금 비번 5번 틀렸을 
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	// 비밀번호 만료여부를 알려준다.  
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	// 계정 활성화 여부 확인  
	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
