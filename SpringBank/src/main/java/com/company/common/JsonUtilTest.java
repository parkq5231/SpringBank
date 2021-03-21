package com.company.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.board.service.BoardVO2;

public class JsonUtilTest {
	public static void main(String[] args) throws Exception {
		// JsonUtil
		JsonUtil json = new JsonUtil();
		// Map sample
		Map<String, Object> map = new HashMap<>();
		map.put("username", "park");
		map.put("age", "20");
		map.put("dept", "개발");

		String result = json.toJson(map);
		System.out.println("Map형태: " + result);
		// {"username" : "park", "age" : 20, "dept" : "개발"}

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map);

		map.put("username", "lee");
		map.put("age", "22");
		map.put("dept", "디자인");
		list.add(map);

		result = json.toJson(list);
		System.out.println("List형태: " + result);

		BoardVO2 vo = new BoardVO2();

		vo.setContent("김밥");
		vo.setFileName("김밥");
		vo.setTitle("title");
		// String result2 = json.toObjectJson(vo);
		// System.out.println("obj형태: " + result2);

	}// end of main
}// end of class
