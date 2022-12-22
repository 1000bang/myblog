package com.blog.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.blog.abc.dto.ApproveRes;
import com.blog.abc.dto.OAuthToken;
import com.blog.abc.dto.PaymentReqDto;
import com.blog.abc.dto.PaymentRes;
import com.blog.abc.service.PaymentService;

@Controller
@SessionAttributes("tid")
@RequestMapping("/item")
public class ItemController {

	@GetMapping("/home")
	public String save() {
		return "/item/home";
	}
	
	
	@GetMapping("/pay")
	public String pay() {
		
		return "/item/pay";
	}

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/ready")
	@ResponseBody
	public PaymentRes ready(@RequestBody PaymentReqDto data, Model model) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
	
		PaymentRes response = paymentService.payReady(data);
		model.addAttribute("tid", response.getTid());
		System.out.println(response.nextRedirectPcUrl);
		return response;
	}
	
	@GetMapping("/pay/success")
	public String payCompleted(@RequestParam("pg_token") String pgToken, @ModelAttribute("tid") String tid,  Model model) {
		System.out.println("성공???????");
		
		// 카카오 결재 요청하기
		ApproveRes approveResponse = paymentService.payApprove(tid, pgToken);	
		
		// 결제정보  저장
		
		return "redirect:/";
	}
	// 결제 취소시 실행 url
	@GetMapping("/pay/cancel")
	public String payCancel() {
		System.out.println(" 취소  ");
		return "redirect:/";
	}
    
	// 결제 실패시 실행 url    	
	@GetMapping("/pay/fail")
	public String payFail() {
		System.out.println("실패  ");
		return "redirect:/";
	}

}
