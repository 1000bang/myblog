package com.blog.abc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReqDto {

	private String username;
	private String count;
	private String amount;
	private String itemname;
	
}
