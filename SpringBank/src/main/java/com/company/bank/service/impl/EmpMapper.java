package com.company.bank.service.impl;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
	// result값이 List<Map> 형태라는 의미
	// Map은 String,Object 형태의 K,V를 가짐
	public List<Map<String, Object>> getEmpList();
}
