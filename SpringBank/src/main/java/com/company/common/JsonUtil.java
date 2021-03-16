package com.company.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	//
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
				result.append(", ");
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

	//
	public String toJson(List<Map<String, Object>> map) {
		StringBuilder result = new StringBuilder();
		result.append("[");//
		// to do
		// {},{}
		map.size();

		result.append("]");//
		return result.toString();
	}

	//
	public String toObjectJson(Object vo) throws Exception {// refliction 활용 class or name 값 가져오기
		StringBuilder result = new StringBuilder();
		result.append("{");
		// refliction활용하여 변수접근
		Field[] fields = vo.getClass().getDeclaredFields();
		// loop
		for (Field field : fields) {
			// field이름만 가져옴
			String fieldName = field.getName();
			// 1번째 글자 대문자로
			String method = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method m = vo.getClass().getDeclaredMethod(method, null);
			// 메서드 실행
			String value = (String) m.invoke(vo, null);
			System.out.println(fieldName + ":" + value);
			// to do
		}
		result.append("}");
		return result.toString();
	}

	//
	public String toObjectJson(List<Object> vo) {
		String result = "";
		// to do
		return result;
	}
}// end of class
