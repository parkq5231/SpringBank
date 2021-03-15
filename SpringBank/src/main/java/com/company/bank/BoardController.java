package com.company.bank;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.bank.service.BoardVO;

@Controller
public class BoardController {
	// 1.insertBoard 페이지
	@RequestMapping("/insertBoard")
	public String insertBoard(BoardVO vo) {
		return "insertBoardResult";
	}

	// 2.객체값만 확인
	@RequestMapping("/ajaxInsertBoard")
	@ResponseBody
	public BoardVO ajaxInsertBoard(BoardVO vo) {
		return vo;
	}
}
