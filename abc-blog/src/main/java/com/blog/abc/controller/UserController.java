package com.blog.abc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.blog.abc.dto.GoogleProfile;
import com.blog.abc.dto.KakaoProfile;
import com.blog.abc.dto.KakaoProfile.KakaoAccount;
import com.blog.abc.dto.NaverProfile;
import com.blog.abc.dto.OAuthToken;
import com.blog.abc.dto.User;
import com.blog.abc.service.UserService;

@Controller
public class UserController {

	// yml 초기 파라미터를 가져올 수 있다.
	@Value("${tenco.key}")
	private String tencoKey;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

//	@GetMapping("/auth/login_form")
//	public String loginForm() {
//
//		return "user/login_form";
//	}
	
	@GetMapping("/auth/login_form")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "user/login_form";
	}

	@GetMapping("/auth/join_form")
	public String joinForm() {

		return "user/join_form";
	}

	
	
	@GetMapping("/user/update_form")
	public String updateForm() {

		return "user/update_form";
	}

	// 신규 가입자와 기존 가입자를 구분하고 로그인처리
	// authentication 할 때 principal이 캐치 하고 컨텍스트 홀더에 넣기
	public void findAndLoginUser(User user) {

		User originUser = userService.searchUserName(user.getUsername());

		if (originUser.getUsername() == null) {
			System.out.println("신규회원이기 때문에 회원가입 진행 ");
			userService.saveUser(user);

		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	///////////////////////////////// api
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(@RequestParam String code) {

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
		ResponseEntity<OAuthToken> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				requestKakaoToken, OAuthToken.class);

		//////////////////// 여기까지 토큰 받기 완료 //////////////////////

		// 다시 한번 더 kapi.kakaocom 로 토큰을 가지고 요청하여 사용자 정보를 응답받아야 한다.
		OAuthToken authtoken = response.getBody();
		RestTemplate rt2 = new RestTemplate();
		// header
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + authtoken.accessToken);
		headers2.add("Content-type", "application/x-www-form-urlencoded;");
		// JWT 주의 bearer 다음에 무조건 한칸 띄우기

		HttpEntity<MultiValueMap<String, String>> kakaoDataRequest = new HttpEntity<>(headers2);
		// 파싱 받을 dto 만들어야 함
		ResponseEntity<KakaoProfile> kakaoDataResponse = rt2.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, kakaoDataRequest, KakaoProfile.class);

		// System.out.println("kakaoDataRespsone : " + kakaoDataResponse.getBody());

//			 User object 만들어서 판별해야함 
//			 소셜로그인 사용자가 우리 사이트에 
//			 1. 처음 가입한 사용자라면 회원가입 -- 가져온 데이터를 자동으로 우리 db에 넣을거임 
//			 2. 이미 회원가입된 사용자라면 db에 저장 x
		KakaoAccount account = kakaoDataResponse.getBody().kakaoAccount;
		User kakaoUser = User.builder().username(account.profile.nickname + "_" + kakaoDataResponse.getBody().id)
				.email(account.email).password(tencoKey).oauth("kakao").build();

		System.out.println("kakao" + kakaoUser);

		findAndLoginUser(kakaoUser);

		return "redirect:/";
	}

	@GetMapping("/auth/google/callback")
	// @ResponseBody // data 를 리턴 함
	public String googleCallback(@RequestParam String code, @RequestParam String scope) {

		RestTemplate rt = new RestTemplate();
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// 바디 만들기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("client_id", "581731462324-vd9i5jd12s0j4d97jcv6n57l1nf2suik.apps.googleusercontent.com");
		params.add("client_secret", "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");
		params.add("redirect_uri", "http://localhost:9090/auth/google/callback");
		params.add("grant_type", "authorization_code");

		HttpEntity<MultiValueMap<String, String>> requestGoogleToken = new HttpEntity<>(params, headers);

		// 헤더 변조 해서 실행 시키는 메서드 RestTemplate exchange() 이다.
		ResponseEntity<OAuthToken> response = rt.exchange("https://oauth2.googleapis.com/token", HttpMethod.POST,
				requestGoogleToken, OAuthToken.class);
		System.out.println(code);
		System.out.println(scope);

		///

		String accessToken = response.getBody().accessToken;
		String tokentype = response.getBody().tokenType;
		String idtoken = response.getBody().idToken;
		RestTemplate rt2 = new RestTemplate();
		// header
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", tokentype + accessToken);
		headers2.add("Content-type", "application/x-www-form-urlencoded;");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers2);
		ResponseEntity<GoogleProfile> response2 = rt2.exchange("https://www.googleapis.com/oauth2/v1/userinfo",
				HttpMethod.GET, request, GoogleProfile.class);

		GoogleProfile account = response2.getBody();
		User googleUser = User.builder().username(account.name + "_" + account.id).email(account.email)
				.password(tencoKey).oauth("google").build();

		findAndLoginUser(googleUser);

		return "redirect:/";
	}

	@GetMapping("/auth/naver/callback")
	// @ResponseBody // data 를 리턴 함
	public String naverCallback(@RequestParam String code, @RequestParam String state)
			throws UnsupportedEncodingException {
		String clientId = "IdFsFqY3HF0S1iEY73kF";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "ApO2UiJob6";// 애플리케이션 클라이언트 시크릿값";
		String code2 = code;
		String state2 = state;
		String redirectURI = URLEncoder.encode("http://localhost:9090/auth/naver/callback", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token";

		RestTemplate rt = new RestTemplate();
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// 바디 만들기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("redirect_uri", redirectURI);
		params.add("code", code);
		params.add("state", state);

		HttpEntity<MultiValueMap<String, String>> requestNaverToken = new HttpEntity<>(params, headers);

		// 헤더 변조 해서 실행 시키는 메서드 RestTemplate exchange() 이다.
		ResponseEntity<OAuthToken> response = rt.exchange(apiURL, HttpMethod.POST, requestNaverToken, OAuthToken.class);

		OAuthToken authtoken = response.getBody();
		RestTemplate rt2 = new RestTemplate();
		// header
		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "Bearer " + authtoken.accessToken);
		headers2.add("Content-type", "application/x-www-form-urlencoded;");

		// JWT 주의 bearer 다음에 무조건 한칸 띄우기

		HttpEntity<MultiValueMap<String, String>> naverRequest = new HttpEntity<>(headers2);
		// 파싱 받을 dto 만들어야 함
		ResponseEntity<NaverProfile> naverDataResponse = rt2.exchange("https://openapi.naver.com/v1/nid/me",
				HttpMethod.POST, naverRequest, NaverProfile.class);

		System.out.println("naverDataRespsone : " + naverDataResponse.getBody());

		// User object 만들어서 판별해야함

		NaverProfile account = naverDataResponse.getBody();
		User naverUser = User.builder().username(account.response.nickname + "_" + account.response.id)
				.email(account.response.email).password(tencoKey).oauth("naver").build();

		findAndLoginUser(naverUser);

		return "redirect:/";
	}

}
