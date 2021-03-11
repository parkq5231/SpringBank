package com.company.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class StreamTest {

	public static void main(String[] args) throws Exception {
		/*
		 * FileReader fr = new FileReader("D:\\temp\\1234.txt"); int r; while ((r =
		 * fr.read()) != -1) { System.out.println((char) r); } fr.close();
		 */
		// 파일복사
		BufferedReader br = new BufferedReader(new FileReader("D:\\temp\\1234.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\temp\\김밥만두.txt"));
		String line;
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			bw.write(line + "\n");
		}
		br.close();
		bw.close();

		// 이미지 복사
		BufferedInputStream bs = new BufferedInputStream(new FileInputStream("D:/temp/라이언.png"));
		BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream("D:/temp/파라랑.png"));

		int cnt;
		byte[] b = new byte[100];

		while (true) {
			cnt = bs.read(b);
			if (cnt == -1) break;
			bo.write(b);
		}
		bs.close();
		bo.close();
		
	}// end of main
}// end of class
