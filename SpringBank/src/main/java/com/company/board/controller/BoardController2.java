package com.company.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.board.service.BoardVO2;
import com.company.board.service.impl.BoardMapper2;
import com.company.common.FileRenamePolicy;
import com.company.common.Paging;

@Controller
public class BoardController2 {

	@Autowired
	BoardMapper2 dao;

	// board페이지
	@GetMapping("/insertBoard")
	public String insertBoardForm() {
		return "board/insertBoard";
	}

	// multipart를 사용하여 파일을 보낼 때 post + encType필수
	@PostMapping("/insertBoard")
	public String insertBoard(BoardVO2 vo) throws IllegalStateException, IOException {
		System.out.println(vo);
		// 첨부파일처리
		MultipartFile[] files = vo.getUploadFile();
		String filenames = "";
		boolean start = true;
		for (MultipartFile file : files) {
			if (file != null && !file.isEmpty() && file.getSize() > 0) {
				// 업로드 된 파일명
				String filename = file.getOriginalFilename();
				// 파일명 중복채크
				File rename = FileRenamePolicy.rename(new File("C:\\upload", filename));
				// vo에 업로드 된 rename된 파일명 담기
				if (!start) {
					filenames += ",";
				} else {
					start = false;
				}
				filenames += rename.getName();
				// 임시폴더에서 실제 업로드 폴더로 파일 이동
				file.transferTo(rename);
			} // end of if
		} // end of for
		vo.setFileName(filenames);
		// 등록서비스 호출
		int r = dao.insertBoard(vo);
		System.out.println(r + "건 등록.");
		return "redirect:/getBoard?seq=" + vo.getSeq();
	}

	// 단건조회
	@GetMapping("/getBoard")
	public String getBoard(BoardVO2 vo, Model model) {
		model.addAttribute("board", dao.getBoard(vo));
		return "board/getBoard";
	}

	// 전체조회
	@GetMapping("/getSearchBoard")
	public String getSearchBoard(BoardVO2 vo, Model model, Paging paging) {
		paging.setPageUnit(5);// 페이지 레코드 뷰
		paging.setPageSize(3);// 페이지 번호
		// paging
		if (vo.getPage() == null)
			vo.setPage(1);
		//
		vo.setStart(paging.getFirst());
		vo.setEnd(paging.getLast());
		//
		paging.setTotalRecord(dao.getCount());
		model.addAttribute("paging", paging);

		// jsp에서 넘겨오는 값
		model.addAttribute("list", dao.getSearchBoard(vo));
		return "board/getSearchBoard";
	}

	// 파일1개 다운
	@GetMapping("/fileDown")
	public void fileDown(BoardVO2 vo, HttpServletResponse response) throws IOException {
		// 단건조회
		vo = dao.getBoard(vo);
		File file = new File("c:/upload", vo.getFileName());

		if (file.exists()) {// file이 있을 때를 채크
			// 다운받을 때의 파일header설정 파일명,contentType,encoding
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(vo.getFileName(), "utf-8") + "\"");
			// 파일복사의 원리와 같음
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(response.getOutputStream());
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else { // 파일이 없을 때
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>")//
					.append("alert('file not found(파일 없음)');")//
					.append("history.go(-1);").append("</script>");
		}
	}

	// 파일 여러개 다운
	@GetMapping("/fileNameDown")
	public void fileNameDown(BoardVO2 vo, HttpServletResponse response) throws IOException {
		// 파일이름 받아옴
		File file = new File("c:/upload", vo.getFileName());

		if (file.exists()) {// file이 있을 때를 채크
			// 다운받을 때의 파일header설정 파일명,contentType,encoding
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(vo.getFileName(), "utf-8") + "\"");
			// 파일복사의 원리와 같음
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(response.getOutputStream());
				FileCopyUtils.copy(in, out);
				out.flush();

			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else { // 파일이 없을 때
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>")//
					.append("alert('file not found(파일 없음)');")//
					.append("history.go(-1);").append("</script>");
		}
	}

	private final int MAX_SIZE = 1024;

	// 파일 알집으로 다운받기
	@RequestMapping("/fileCompress")
	public void fileCompress(BoardVO2 vo, HttpServletResponse response) throws Exception {

		String[] files = { "test1.txt", "test2.txt", "test3.txt" };
		byte[] buf = new byte[MAX_SIZE];

		ZipOutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = new ZipOutputStream(new FileOutputStream("result.zip"));

			for (String file : files) {
				fileInputStream = new FileInputStream(file);
				outputStream.putNextEntry(new ZipEntry(file));

				int length = 0;
				while (((length = fileInputStream.read()) > 0)) {
					outputStream.write(buf, 0, length);
				}
				outputStream.closeEntry();
				fileInputStream.close();
			}
			outputStream.close();
		} catch (IOException e) {
			// Exception Handling
		} finally {
			try {
				outputStream.closeEntry();
				outputStream.close();
				fileInputStream.close();
			} catch (IOException e) {
				// Exception Handling
			}
		}

		// 3.파일명과 DB에 있는 이름이 일치하는 경우 조회
		// 4.일치한 이름들의 파일 압축
		// 5.압축한 zip파일 다운로드
		// 1.파일이름 받아옴
		// 2.파일명에서 ,제거하기
		// ,제거 작업 3번 반복
		//
		// 3.DB값과 일치하는지 채크
		// File rename = FileRenamePolicy.rename(new File("C:\\upload", filename));
		// 4.일치할 경우, 디렉토리 생성
		// 5.생성한 디렉토리에 파일들 집어넣기
		// 6.디렉토리 압축
		// 7.압축한 zip파일 다운로드

	}// end of fileCompress
}