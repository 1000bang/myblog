package com.tencoding.blog.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.dto.KakaoAccount;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {
	
	//yml 초기 파라미터를 가져올 수 있다. 
	@Value("${tenco.key}")
	private String tencoKey;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@GetMapping("/auth/login_form")
	public String loginForm() {
		
		
//		/WEB-INF/views/user/login_form.jsp
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {

		return "user/join_form";
	}
	
	@GetMapping("/user/update_form")
	public String updateForm(){
		
		return "user/update_form";
	}
	
	
	/*
	 * POST /oauth/token HTTP/1.1
		Host: kauth.kakao.com
		Content-type: application/x-www-form-urlencoded;charset=utf-8
	 * 
	 */
	// 페이지에서 데이터를 리턴하는 방법
		@GetMapping("/auth/kakao/callback")
		//@ResponseBody // data 를 리턴 함
		public String kakaoCallback(@RequestParam String code) {
			// 여기서 카카오 서버에서 보내 준 code 값을 받을 수 있다.
			// 다음 단계은 토큰 발급 받기

			RestTemplate rt = new RestTemplate();
			// 헤더 만들기
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// 바디 만들기
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "026e230aaa7d30f98a0e8cad9f217377");
			params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
			params.add("code", code);

			HttpEntity<MultiValueMap<String, String>> requestKakaoToken = new HttpEntity<>(params, headers);

			// 헤더 변조 해서 실행 시키는 메서드 RestTemplate exchange() 이다.
			ResponseEntity<OAuthToken> response = 
					rt.exchange("https://kauth.kakao.com/oauth/token",
							HttpMethod.POST,
							requestKakaoToken,
							OAuthToken.class);

			////////////////////여기까지 토큰 받기 완료  //////////////////////
			
			//다시 한번 더 kapi.kakaocom 로 토큰을 가지고 요청하여 사용자 정보를 응답받아야 한다. 
			OAuthToken authtoken = response.getBody(); 
			RestTemplate rt2 = new RestTemplate();
			// header 
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer "+authtoken.accessToken);
			headers2.add("Content-type", "application/x-www-form-urlencoded;");
			//JWT 주의 bearer 다음에 무조건 한칸 띄우기 
			
			HttpEntity<MultiValueMap<String, String>> kakaoDataRequest =
					new HttpEntity<>(headers2);
			//파싱 받을 dto 만들어야 함
			ResponseEntity<KakaoProfile> kakaoDataResponse  = rt2.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoDataRequest,
					KakaoProfile.class
					);
			
			//System.out.println("kakaoDataRespsone : " + kakaoDataResponse.getBody());
			
//			 User object 만들어서 판별해야함 
//			 소셜로그인 사용자가 우리 사이트에 
//			 1. 처음 가입한 사용자라면 회원가입 -- 가져온 데이터를 자동으로 우리 db에 넣을거임 
//			 2. 이미 회원가입된 사용자라면 db에 저장 x
			KakaoAccount account = kakaoDataResponse.getBody().kakaoAccount;
			User kakaoUser = User.builder()
					.username(account.profile.nickname + "_" + kakaoDataResponse.getBody().id)
					.email(account.email)
					.password(tencoKey)
					.oauth("kakao")
					.build();
			
			System.out.println("kakao" + kakaoUser);
			//여기서 1,2 구별 
			// user id를 사용하지 못함 그럼 username으로 검색하는 기능을 만들어 주어야 한다. 
			// 내 생각에는 email로 하는게 더 좋을 듯 
			// email은 한 계정에 하나만 사용가능하니까 
		
			// 데이터가 있나 없나 확인 
			User originUser = userService.searchUserNam(kakaoUser.getUsername());
			
			if(originUser.getUsername() == null) {
				System.out.println("신규회원이기 때문에 회원가입 진행 ");
				userService.saveUser(kakaoUser);
				
			}
			// 신규 회원가입이던 한번 가입했던 유저이던 무조건 소셜 로그인이면 세션을 생성해줘야함
			// 자동 로그인 처리 --> 시큐리티 세션에다 강제 저장
			// authentication 할 때 principal 이 캐치 
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), tencoKey));
			
			//컨텍스트 홀더에 밀어넣기  
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			//prefix "" _subfix
			return "redirect:/";
		}
	
}
