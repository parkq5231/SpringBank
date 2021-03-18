package com.company.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class Compress {
	// zip압축 메서드
	// 매개값 파일이름
	// 파일이름 split으로 자르기
	public void CompressZIP(String[] files, HttpServletResponse response) throws Exception {

		ZipOutputStream zout = null;

		String zipName = "";
		String tempPath = "";

		// 파일이름 유효성채크
		if (files.length > 0) {
			tempPath = "c:/temp/";// 저장경로
			// 파일압축
			zout = new ZipOutputStream(new FileOutputStream(tempPath + zipName));
			byte[] buffer = new byte[1024];
			FileInputStream in = null;

			for (int k = 0; k < files.length; k++) {
				in = new FileInputStream(tempPath + files[k]);// 압축할 대상파일
				zout.putNextEntry(new ZipEntry(files[k]));// 저장될 파일명

				int len;
				while ((len = in.read(buffer)) > 0) {
					zout.write(buffer, 0, len);// 읽은 파일 write
				} // end of while
				zout.closeEntry();
				in.close();
			} // end of for
			zout.close();// zip파일 압축 end

			response.setContentType("text/html; charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + zipName);

			FileInputStream fis = new FileInputStream(tempPath + zipName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ServletOutputStream so = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(so);

			int n = 0;

			while ((n = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, n);
				bos.flush();
			}
			if (bos != null)
				bos.close();
			if (bis != null)
				bis.close();
			if (so != null)
				so.close();
			if (fis != null)
				fis.close();

		} // end of if
	}// end of method
}// end of class
