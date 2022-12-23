package com.spring.qlrm.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.qlrm.dto.res.EmployeeDto;
import com.spring.qlrm.dto.res.TitleJoinDeptManagerRes;
import com.spring.qlrm.repository.EmployRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
public class HomeApiController {
	
	
	//여기에서는 service만들지 않고 바로 repository test하기 
	
	private final EmployRepository employRepository;
	public HomeApiController(EmployRepository employRepository) {
		this.employRepository = employRepository;
	}
	
	
	@GetMapping({"","/index"})
	public ResponseEntity<?> home() {
		EmployeeDto resultDto = employRepository.getOldEmployeeAgeInfo();
		return new ResponseEntity<EmployeeDto>(resultDto, HttpStatus.OK);
	}
	
	@GetMapping("/manager")
	public ResponseEntity<?> manager() {
		List<TitleJoinDeptManagerRes> resultDto = employRepository.data();
		return new ResponseEntity<List<TitleJoinDeptManagerRes>>(resultDto, HttpStatus.OK);
	}
	
	
	
	
	
	
}
