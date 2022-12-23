package com.spring.qlrm.dto.res;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TitleJoinDeptManagerRes {

	
	private int emp_no;
	private String title;
	private String dept_no;
	private Date from_date;
	private Date to_date;
	
	
	public TitleJoinDeptManagerRes(int emp_no, String title, String dept_no, Date from_date, Date to_date) {
		super();
		this.emp_no = emp_no;
		this.title = title;
		this.dept_no = dept_no;
		this.from_date = from_date;
		this.to_date = to_date;
	}


    public TitleJoinDeptManagerRes(Object[] objs) {
    
    	this.emp_no = (Integer) objs[0];
		this.title = (String) objs[1];
		this.dept_no = (String) objs[2];
		this.from_date = (Date) objs[3];
		this.to_date = (Date) objs[4];
//		
    }
	
	
}
