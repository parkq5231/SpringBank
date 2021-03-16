package com.company.board.service;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO2 {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private String fileName;
	private MultipartFile uploadFile;
}