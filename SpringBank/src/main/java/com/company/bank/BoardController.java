package com.company.bank;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.bank.service.BoardVO;

@Controller
public class BoardController {
	// insertBoard 페이지
	@RequestMapping("/insertBoardResult")
	public String insertBoard(BoardVO vo, Model model) {
		model.addAttribute("vo", vo);
		return "insertBoardResult";
	}
}
