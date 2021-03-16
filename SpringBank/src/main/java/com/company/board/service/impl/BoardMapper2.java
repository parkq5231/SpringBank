package com.company.board.service.impl;

import com.company.board.service.BoardVO2;

public interface BoardMapper2 {
	// 등록
	public int insertBoard(BoardVO2 vo);

	// 단건조회
	public BoardVO2 getBoard(BoardVO2 vo);

}
