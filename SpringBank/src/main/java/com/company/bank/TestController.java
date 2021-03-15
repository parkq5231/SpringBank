package com.company.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.bank.service.TestVO;

@Controller
public class TestController {
	// get, post, 커맨드객체(VO)
	@RequestMapping("/getTest1")
	public String getTest1(TestVO vo) {
		System.out.println("값:" + vo);
		return "home";
	}

	// 파라미터
	@RequestMapping("/getTest2")
	public String getTest2(@RequestParam String firstName, @RequestParam int salary) {
		System.out.println(firstName + ":" + salary);
		return "home";
	}

	// 배열
	@RequestMapping("/getTest3")
	// 이름이 다르면 (value)적기
	public String getTest3(@RequestParam("hobby") String[] hobbies) {
		// 배열을 리스트로 변환
		System.out.println(Arrays.asList(hobbies));
		// loop
		// for (int i = 0; i < hobby.length; i++) {
		// System.out.println(hobby[i]);
		// }
		return "home";
	}

	// json
	@RequestMapping("/getTest4")
	public String getTest4(@RequestBody List<Map> vo) {
		System.out.println(vo);
		return "home";
	}

	//
	@RequestMapping("/getTest5/{firstName}/{salary}")
	public String getTest5(@PathVariable String firstName, @PathVariable String salary,
			@ModelAttribute("tvo") TestVO vo, Model model) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		System.out.println(firstName + " : " + salary);
		model.addAttribute("firstName", firstName);
		return "test";
	}

	// model+view
	@RequestMapping("/getTest6/{firstName}/{salary}")
	public ModelAndView getTest6(@PathVariable String firstName, @PathVariable String salary,
			@ModelAttribute("tvo") TestVO vo) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		ModelAndView mv = new ModelAndView();
		// addAttribute
		mv.addObject("firstName", firstName);
		// viewPage
		mv.setViewName("test");

		return new ModelAndView("test", "firstName", firstName);
	}

	// 응답결과 json
	@RequestMapping("/getTest7/{firstName}/{salary}")
	@ResponseBody
	public TestVO getTest7(@PathVariable String firstName, @PathVariable String salary, TestVO vo) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		return vo;
	}

	// 응답결과 json2
	@RequestMapping("/getTest8")
	@ResponseBody
	public List<Map> getTest8() {
		List list = new ArrayList<>();

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "park");
		map.put("sal", "1000");
		list.add(map);

		new HashMap<String, String>();
		map.put("name", "kim");
		map.put("sal", "2000");
		list.add(map);

		return list;
	}
}
