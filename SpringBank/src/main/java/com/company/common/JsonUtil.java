package com.company.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.sf.jasperreports.engine.data.JsonData;

public class JsonUtil {

	// Map -> json
	public String toJson(Map<String, Object> map) {
		StringBuilder result = new StringBuilder();
		result.append("{");//
		// iterator로 줄세움
		Iterator<String> keys = map.keySet().iterator();
		boolean start = true;
		while (keys.hasNext()) {// 읽을게 있는지 채크
			String key = keys.next();
			String value = (String) map.get(key);
			if (!start) {
				result.append(",");
			} else {
				start = false;
			}
			result.append("\"").append(key)// {"key
					.append("\"").append(":\"")// {"key":"
					.append(value).append("\"");// {"key":"value"
		} // end of while
		result.append("}");
		// to do
		return result.toString();
	}

	// List -> json
	public String toJson(List<Map<String, Object>> list) {
		StringBuilder result = new StringBuilder();
		result.append("{");//
		// to do
		JsonArray json = new JsonArray();
		for (Map<String, Object> map : list) {
			json.add(toJson(map));
		}
		// 두번째부터 구분기호 , 추가
		boolean start = true;
		if (!start) {
			result.append(",");
		} else {
			start = false;
		}
		return json.toString();
	}

	// object -> json
	public String toObjectJson(Object vo) throws Exception {// refliction 활용 class or name 값 가져오기
		StringBuilder result = new StringBuilder();
		result.append("{");
		// refliction활용하여 변수접근
		Field[] fields = vo.getClass().getDeclaredFields();
		// loop
		boolean start = true;
		for (Field field : fields) {
			System.out.println(field);
			// field이름만 가져옴
			String fieldName = field.getName();
			// 1번째 글자 잘라서 대문자로 변경 + 1번째 글자 이후의 글자를 문자열 덧셈처리
			String method = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			// 메소드를 리턴해주는 의미
			Method m = vo.getClass().getDeclaredMethod(method, null);
			// 메서드 실행
			String value = (String) m.invoke(vo, null);
			// System.out.println(fieldName + ":" + value);
			// ,추가용
			if (!start) {
				result.append(",");
			} else {
				start = false;
			}
			// 내용
			result.append("\"").append(fieldName).append("\":")// {"key":
					.append("\"").append(value).append("\"");// {"key":"value"
		}
		result.append("}");
		return result.toString();
	}

	// list<object> -> json
	public String toObjectJson(List<Object> vo) {
		StringBuilder result = new StringBuilder();
		// to do
		// 두번째부터 구분기호 , 추가
		boolean start = true;
		if (!start) {
			result.append(",");
		} else {
			start = false;
		}
		result.append("}");
		return result.toString();
	}
}// end of class
