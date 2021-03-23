package com.company.bank.service.impl;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
	// result값이 List<Map> 형태라는 의미
	// Map은 String,Object 형태의 K,V를 가짐
	public List<Map<String, Object>> getEmpList();

	// 일별합계
	public List<Map<String, Object>> dailyTotal();

	// 월별합계
	public List<Map<String, Object>> monthlyTotal();

	// 년도별합계
	public List<Map<String, Object>> yearsTotal();

}
