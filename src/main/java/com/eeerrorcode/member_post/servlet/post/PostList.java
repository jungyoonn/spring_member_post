package com.eeerrorcode.member_post.servlet.post;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.eeerrorcode.member_post.dto.Criteria;
import com.eeerrorcode.member_post.dto.PageDto;
import com.eeerrorcode.member_post.service.PostService;
import com.eeerrorcode.member_post.service.PostServiceImpl;

@WebServlet("/post/list")
public class PostList extends HttpServlet{
	private PostService service = new PostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 수집
//		int cate = setDefaultValue(req.getParameter("category"), 2);
//		int page = setDefaultValue(req.getParameter("page"), 1);
//		int amount = setDefaultValue(req.getParameter("amount"), 10);
//		Criteria cri = Criteria.builder().page(page).amount(amount).category(cate).build();
		Criteria cri = new Criteria(req);
		System.out.println(cri);
		req.setAttribute("posts", service.list(cri));
		req.setAttribute("pageDto", new PageDto(cri, service.count(cri)));
		req.getRequestDispatcher("/WEB-INF/jsp/post/list.jsp").forward(req, resp);
	}
	
	// private int setDefaultValue(String value, int def) {
	// 	return value == null || value.equals("") ? def : Integer.parseInt(value);
	// }
}
