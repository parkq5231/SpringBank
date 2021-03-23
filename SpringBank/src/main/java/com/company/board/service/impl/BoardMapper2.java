package com.company.board.service.impl;

import java.util.List;

import com.company.board.service.BoardVO2;

public interface BoardMapper2 {
	// 등록
	public int insertBoard(BoardVO2 vo);

	// 단건조회
	public BoardVO2 getBoard(BoardVO2 vo);

	// 전체조회
	public List<BoardVO2> getSearchBoard(BoardVO2 vo);

	// 페이지 전체 수
	public int getCount();
}
