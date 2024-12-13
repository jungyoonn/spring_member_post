package com.eeerrorcode.member_post.utils;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

public class Commons {
	public static final String UPLOAD_PATH = "c:/upload";
	
	public static void printMsg(String msg, String url, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<script>");
		pw.printf("alert('%s'); %n", msg);
		if(url == null) {
			pw.printf("hitory.back();");
		} else {
			pw.printf("location.href = '%s', %n", url);
		}
		pw.printf("location.href = '%s'; %n", url);
		pw.println("</script>");
	}
}
