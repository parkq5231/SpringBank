package com.company.board.service;

import org.springframework.web.multipart.MultipartFile;

import com.company.common.Paging;

import lombok.Data;

@Data
public class BoardVO2 {
	private Integer seq;
	private String title;
	private String writer;
	private String content;
	private String fileName;
	// 배열로 고치면 여러건 가능
	private MultipartFile[] uploadFile;
	// paging
	Integer page = 1;
	int start = 1;
	int end = 10;
}