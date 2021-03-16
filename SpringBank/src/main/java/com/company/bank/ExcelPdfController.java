package com.company.bank;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.bank.service.impl.EmpMapper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ExcelPdfController {
	@Autowired
	EmpMapper empMapper;

	@Autowired
	DataSource dataSource;

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

	// PDF변환3
	@RequestMapping("/pdf/empList3")
	public ModelAndView getPdfEmpList3(Model model) {
		return new ModelAndView("pdfView", "filename", "/reports/empList3.jasper");
	}

	@RequestMapping("/pdf/empList4")
	public void getPdfEmpList4(Model model, HttpServletResponse response) throws Exception {
		// datasource활용
		Connection conn = dataSource.getConnection();
		// jrxml소스파일을 읽음
		String jrxmlFile = getClass().getResource("/reports/empList3.jrxml").getFile();
		// 소스파일 컴파일하여 저장 : compileReportToFile
		String jasperFile = JasperCompileManager.compileReportToFile(jrxmlFile);
		// 저장
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, null, conn);
		// 출력
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}

	@RequestMapping("/getChartData")
	@ResponseBody
	public List<Map<String, String>> getChartData() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "인사");
		map.put("cnt", "5");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("name", "총무");
		map.put("cnt", "10");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("name", "기획");
		map.put("cnt", "20");
		list.add(map);
		return list;
	}

}
