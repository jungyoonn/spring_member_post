package com.eeerrorcode.member_post.servlet.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.eeerrorcode.member_post.utils.Commons;

@WebServlet("/download")
public class Download extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터 수집
		String uuid = req.getParameter("uuid");
		String origin = req.getParameter("origin");
		String path = req.getParameter("path");
		
		if(uuid == null || origin == null || path == null) {
			Commons.printMsg("잘못된 접근입니다.", null, resp);
			return;
		}
		
		File file = new File(Commons.UPLOAD_PATH, path);
		file = new File(file, uuid);
		
		if(!file.exists()) {
			Commons.printMsg("잘못된 접근입니다.", null, resp);
			return;
		}
		
		// 2. 파일 실제 이름 처리
		String fileName = new String(origin.getBytes("utf-8"), "8859_1");
		
		// 3. 응답 처리
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", String.format("attachment; filename=%s", fileName));
		
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = fis.readAllBytes();
		resp.getOutputStream().write(bytes);
		fis.close();
	}

}
