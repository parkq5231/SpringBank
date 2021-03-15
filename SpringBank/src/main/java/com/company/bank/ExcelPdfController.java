package com.company.bank;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.bank.service.impl.EmpMapper;

@Controller
public class ExcelPdfController {
	@Autowired
	EmpMapper empMapper;

	// excel
	@RequestMapping("/getEmpExcel")
	public String getEmpExcel(Model model) {
		List<Map<String, Object>> list = empMapper.getEmpList();
		System.out.println(list.get(0));
		model.addAttribute("filename", "empList");
		model.addAttribute("headers", new String[] { "firstName", "lastName", "salary" });
		model.addAttribute("datas", list);
		return "commonExcelView";
	}
}
