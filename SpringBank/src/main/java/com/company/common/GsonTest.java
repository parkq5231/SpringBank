package com.company.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;

public class GsonTest {

	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "길동");
		map.put("age", 20);

		// 자바 객체를 String(json)형태로
		Gson gson = new Gson();
		// Map,vo,List는 parsing할 때 별 차이가 없음
		String str = gson.toJson(map);// 자바 객체 상관 x
		System.out.println("[gson]" + str);

		str = "{\"name\":\"길동\",\"age\":20, \"dept\":\"개발\"}";
		// String(json)을 자바 객체로
		map = gson.fromJson(str, Map.class);
		System.out.println(map.get("dept"));

		// jackson : spring json lib @ResponseBody(자동으로 변환해준 annotation)
		// 자바 객체를 String(json)형태로
		JsonMapper mapper = new JsonMapper();
		String str2 = mapper.writeValueAsString(map);
		System.out.println("[jackson]" + str2);
		// String -> 자바객체
		map = mapper.readValue(str2, Map.class);
		System.out.println("[jackson]" + map);

	}// end of main
}// end of class
