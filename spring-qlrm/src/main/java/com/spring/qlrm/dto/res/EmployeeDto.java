package com.spring.qlrm.dto.res;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDto {

	
	private int emp_no;
	private Date birth_date;
	private String first_name;
	private String last_name;
	private String gender;
	private Date hire_date;
	private BigInteger age;
	
	public EmployeeDto(Integer emp_no, Date birth_date, String first_name,
			String last_name, Character gender, Date hire_date, BigInteger age) {
		
		this.emp_no = emp_no;
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender.toString();
		this.hire_date = hire_date;
		this.age = age;
	}



	//전체 생성자를 활용.. 알아서 jpaMapper 녀석을 호출해서 맵핑해준다. 
	// sql 타입으로 바꿔주자 
	public EmployeeDto(Integer emp_no, Date birth_date, String first_name,
			String last_name, Character gender, Date hire_date) {
		
		this(emp_no, birth_date, first_name, last_name, gender, hire_date, null);
	}
	
}
