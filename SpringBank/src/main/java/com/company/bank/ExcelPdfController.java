package com.company.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.bank.service.impl.EmpMapper;

@Controller
public class ExcelPdfController {
	@Autowired
	EmpMapper empMapper;

	// excel
	@RequestMapping("/getEmpExcel")
	public String getEmpExcel(Model model) {// vo로 받아서 응용하는 법:command객체 사용?
		List<Map<String, Object>> list = empMapper.getEmpList();
		System.out.println(list.get(0));
		model.addAttribute("filename", "empList");
		model.addAttribute("headers", new String[] { "firstName", "lastName", "salary" });
		model.addAttribute("datas", list);
		return "commonExcelView";
	}

	// PDF변환
	@RequestMapping("/pdf/empList")
	public String getPdfEmpList(Model model) {
		model.addAttribute("filename", "/reports/empList.jasper");
		return "pdfView";
	}

	// PDF변환2
	@RequestMapping("/pdf/empList2")
	public String getPdfEmpList2(Model model, @RequestParam String dept) {
		// servlet-context파일 설정하여 order순서 변경시만 가능
		// Component 클래스 이름이 PdfView이기 때문에 pdfView로 부름
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("P_DEPARTMENT_ID", dept);

		model.addAttribute("param", map);
		model.addAttribute("filename", "/reports/empList2.jasper");
		return "pdfView";
	}

}
