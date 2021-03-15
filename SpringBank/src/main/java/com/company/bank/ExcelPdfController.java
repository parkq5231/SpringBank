package com.company.bank;

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
		model.addAttribute("filename", "empList");
		model.addAttribute("datas", empMapper.getEmpList());
		return "commonExcelView";
	}
}
