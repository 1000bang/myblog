package com.spring.qlrm.repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.spring.qlrm.dto.res.EmployeeDto;
import com.spring.qlrm.dto.res.TitleJoinDeptManagerRes;

//jpaRepository <T, ID> 상속 받으면 무조건 entity(model)로 받아야 하기 때문에
// dto 맵핑하기가 힘들다 그래서 직접 Repository 를 구성하자 


@Repository
public class EmployRepository {

	
	//준비물 
	// 1.entity manager 
	private final EntityManager entityManager;
	
	public EmployRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	//jpaRepo를 상속받지 않아서 우리가 직접 기능 설계해야 함
	//2. 기능설계
	
	public EmployeeDto getOldEmployeeAgeInfo() {
		// native query -> annotation 말고 직접 구현 
		// jdbc에서는 Connection Statement Resultset 세가지가 필요했었
		
		String strQuery = " SELECT "
						+ "    *"
						+ " FROM "
						+ "    employees"
						+ " WHERE "
						+ "    emp_no = 10001;";
		
		//Query(import javax.persistence.Query;) 객체 사용
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
		JpaResultMapper mapper = new JpaResultMapper();
		EmployeeDto dto = mapper.uniqueResult(nativeQuery, EmployeeDto.class);
		
		//		Object data = nativeQuery.getSingleResult();
		//형을 변환 처리 후 
		// 사이즈가 얼마일 까 
//		List<Object[]> resultList = nativeQuery.getResultList();
//		Object[] objs = resultList.get(0);
//		System.out.println(resultList.size());
//		System.out.println(objs.toString());
//		System.out.println(objs[0]);
//		System.out.println(objs[1]);
//		System.out.println(objs[2]);
//		System.out.println(objs[3]);
//		System.out.println(objs[4]);
//		System.out.println(objs[5]);
//		System.out.println(objs[6]);
//		EmployeeDto dto = new EmployeeDto(objs);
		
		
		return dto;
	}
	
	public Object countSalary10002() {
		
		String strQuery = " SELECT "
				 + "   count(*) AS count"
				 + " FROM"
				 + "    employees AS e,"
				 + "    salaries AS s"
				 + " WHERE"
				 + "    s.emp_no = e.emp_no"
				 + " GROUP BY e.emp_no"
				 + " HAVING e.emp_no = 10002;";
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
		
		Object data = nativeQuery.getSingleResult();
		
//		JpaResultMapper mapper = new JpaResultMapper();
//		BigInteger dto = mapper.uniqueResult(nativeQuery, BigInteger.class);
		
		return data;
	}
	
	public Object countSalary10002AllData() {
		
		String strQuery = " SELECT "
				 + "   * "
				 + " FROM"
				 + "    employees AS e,"
				 + "    salaries AS s"
				 + " WHERE"
				 + "    s.emp_no = e.emp_no"
				 + " GROUP BY e.emp_no"
				 + " HAVING e.emp_no = 10002;";
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
		
		Object data = nativeQuery.getSingleResult();
		
//		JpaResultMapper mapper = new JpaResultMapper();
//		BigInteger dto = mapper.uniqueResult(nativeQuery, BigInteger.class);
		
		return data;
	}
	
	public List<TitleJoinDeptManagerRes> data() {
		//ArrayList<TitleJoinDeptManagerRes> temp1 = new ArrayList<>();
		String strQuery = " SELECT "
						+ "    t.emp_no, t.title, m.dept_no, m.from_date, m.to_date"
						+ " FROM\n"
						+ "    titles AS t,"
						+ "    dept_manager AS m"
						+ " WHERE\n"
						+ "    t.emp_no = m.emp_no"
						+ "        AND YEAR(m.to_date) = 9999"
						+ " AND t.title = \"Manager\";";
	
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
		List<Object[]> resultList = nativeQuery.getResultList();
//		for (int i = 0; i < resultList.size(); i++) {
//			
//			System.out.println(resultList.get(i));
//			Object[] temp = resultList.get(i);	
//			TitleJoinDeptManagerRes dto = new TitleJoinDeptManagerRes(temp);
//			temp1.add(dto);
//		}
		
		JpaResultMapper mapper = new JpaResultMapper();
        List<TitleJoinDeptManagerRes> dto = mapper.list(nativeQuery, TitleJoinDeptManagerRes.class);

		
		return dto;
		}
		
	       
		/*
		 * String dept_no, Date from_date, Date to_date
		*/
//		Integer emp_no;
//		String title;
//		String dept_no;
//		String from_date;
//		String to_date;
//	
//		for(Object[] row : resultList){
//			 emp_no = (Integer)row[0];
//		     title = (String)row[1];
//		     dept_no = (String)row[2];
//		     from_date = (String)row[3];
//		     to_date = (String)row[4];
//		}
//		
//		ResDto dto = new ResDto(emp_no[0], title[0], dept_no[0], from_date[0], to_date[0]);
//		
//		
	
	
	
	
	
	
}
