package com.eeerrorcode.member_post.servlet.member;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/signup")
public class Signup extends HttpServlet{
	// private MemberService service = new MemberServiceImpl();

	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 	req.getRequestDispatcher("/WEB-INF/jsp/member/signup.jsp").forward(req, resp);
	// }

	// @Override
	// protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 	String id = req.getParameter("id");
	// 	String pwd = req.getParameter("pwd");
	// 	String name = req.getParameter("name");
	// 	String email = req.getParameter("email");
	// 	String roadAddr = req.getParameter("roadAddr");
	// 	String detailAddr = req.getParameter("detailAddr");
		
	// 	Member member = Member.builder()
	// 					.id(id)
	// 					.pw(pwd)
	// 					.name(name)
	// 					.email(email)
	// 					.roadAddr(roadAddr)
	// 					.detailAddr(detailAddr)
	// 					.build();
	// 	System.out.println(member);
		
	// 	service.register(member);
	// 	resp.sendRedirect("signup");
	// }
}
