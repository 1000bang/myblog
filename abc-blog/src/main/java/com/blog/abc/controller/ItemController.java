package com.blog.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import com.blog.abc.dto.ResponseDto;
import com.blog.abc.service.PaymentService;

@Controller
@SessionAttributes("tid")
@RequestMapping("/item")
public class ItemController {

	@GetMapping("/home")
	public String save() {
		return "/item/home";
	}
	@GetMapping("/map")
	public String map() {
		return "/item/map";
	}
	
	
	@GetMapping("/pay")
	public String pay() {
		
		return "/item/pay";
	}

	private String tid;
	private PaymentReqDto dtoData;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/ready")
	@ResponseBody
	public ResponseDto<PaymentRes>  ready(@RequestBody PaymentReqDto data, Model model) {
		RestTemplate rt = new RestTemplate();
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		
		int a = (Integer.parseInt(data.getCount()) * Integer.parseInt(data.getAmount()));
		String total = a+"" ;
		
		
		headers.add("Authorization", "KakaoAK 9d0a458b987ea7c9d2180dd705393b9d");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "123123123");
		params.add("partner_user_id", data.getUsername());
		params.add("item_name", data.getItemname());
		params.add("quantity", data.getCount());
		params.add("total_amount", total);
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:9090/item/pay/success");
		params.add("cancel_url", "http://localhost:9090/item/pay/cancel");
		params.add("fail_url", "http://localhost:9090/item/pay/fail");
		
		HttpEntity<MultiValueMap<String, String>> requestPayment = new HttpEntity<>(params, headers);
		ResponseEntity<PaymentRes> response = rt.exchange("https://kapi.kakao.com/v1/payment/ready", HttpMethod.POST,
				requestPayment, PaymentRes.class);
		tid = response.getBody().tid;
		dtoData = data;
		return new ResponseDto<PaymentRes>(HttpStatus.OK, response.getBody());
	}
	
	@GetMapping("/pay/success")
//	@ResponseBody
	public String payCompleted(@RequestParam("pg_token") String pgToken, Model model) {
		System.out.println("성공???????");
		
		// 카카오 결재 요청하기
		ApproveRes approve = paymentService.payApprove(tid, pgToken, dtoData);	
		
		ApproveRes payment = ApproveRes.builder()
				.partnerUserId(approve.getPartnerUserId())
				.quantity(approve.getQuantity())
				.amount(approve.getAmount())
				.itemName(approve.getItemName())
				.approvedAt(approve.getApprovedAt())
				.amount(approve.getAmount())
				.paymentMethodType(approve.getPaymentMethodType())
				.build();
				
	

		
		
		model.addAttribute("payment", payment);
		model.addAttribute("postcode", dtoData.getPostcode());
		model.addAttribute("roadAddress", dtoData.getRoadAddress());
		model.addAttribute("detailAddress", dtoData.getDetailAddress());
		
		return "item/success";
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
