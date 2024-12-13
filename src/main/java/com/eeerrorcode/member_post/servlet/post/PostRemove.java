package com.eeerrorcode.member_post.servlet.post;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.eeerrorcode.member_post.dto.Criteria;
import com.eeerrorcode.member_post.service.PostService;
import com.eeerrorcode.member_post.service.PostServiceImpl;
import com.eeerrorcode.member_post.utils.Commons;
import com.eeerrorcode.member_post.vo.Member;

@WebServlet("/post/remove")
public class PostRemove extends HttpServlet {
	private PostService service = new PostServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pnoStr = req.getParameter("pno");
		Object memberObj = req.getSession().getAttribute("member");
		Criteria cri = new Criteria(req);
		String redirectUrl = "list?" + cri.getQs2();
		
		if(pnoStr == null || memberObj == null) {
			Commons.printMsg("비정상적인 접근입니다", redirectUrl, resp);
			return;
		}
		
		Long pno = Long.valueOf(pnoStr);
		Member m = (Member) memberObj; // 세션에 저장된 아이디와 글 작성자가 다르면 삭제 불가
		if(!m.getId().equals(service.findBy(pno).getWriter())) {
			Commons.printMsg("본인이 작성한 글만 삭제할 수 있습니다", redirectUrl, resp);
			return;
		}
		
		service.remove(pno);
		
		resp.sendRedirect("list" + "?" + cri.getQs2());
	}
}
