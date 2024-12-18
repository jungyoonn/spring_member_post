// package com.eeerrorcode.member_post.servlet.reply;

// import java.io.IOException;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// // import com.google.gson.Gson;
// // import com.google.gson.GsonBuilder;

// import com.eeerrorcode.member_post.service.ReplyService;
// import com.eeerrorcode.member_post.service.ReplyServiceImpl;

// @WebServlet("/reply/*")
// public class ReplyController extends HttpServlet{
// 	private ReplyService service = ReplyServiceImpl.getInstance();
// 	// private Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
	
// 	@Override
// 	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		String uri = req.getRequestURI();
// 		uri = uri.replace(req.getContextPath() + "/reply/", "");
		
// 		if(uri.startsWith("list")) { // 목록 조회
// 			int tmpIdx = uri.lastIndexOf("/");
// 			Long pno = 0L;
// 			if(tmpIdx != -1) {
// 				pno = Long.valueOf(uri.substring(tmpIdx + 1));
// 			}
// 			service.removeAll(pno);
// 		} else { // 단일 조회
// 			Long rno = Long.valueOf(uri);
// 			service.remove(rno);
// 		}
// 	}

// 	@Override
// 	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		// String uri = req.getRequestURI();
// 		// uri = uri.replace(req.getContextPath() + "/reply/", "");
		
// 		// Object ret = null;
// 		// if(uri.startsWith("list")) { // 목록 조회
// 		// 	// /reply/list/#{pno}
// 		// 	// /reply/list/#{pno}/#{lastPno}
// 		// 	// /reply/list/#{pno}/#{lastPno}/#{amount}
// 		// 	ReplyCri cri = new ReplyCri();
// 		// 	int tmpIdx = uri.indexOf("/");
// 		// 	Long pno = 0L;
// 		// 	if(tmpIdx != -1) {
// 		// 		String tmp = uri.substring(tmpIdx+1);
// 		// 		String[] tmpArr = tmp.split("/");
// 		// 		switch(tmpArr.length) {
// 		// 		case 3:
// 		// 			cri.setAmount(Integer.parseInt(tmpArr[2]));
// 		// 		case 2:
// 		// 			cri.setLastRno(Long.parseLong(tmpArr[1]));
// 		// 		case 1:
// 		// 			pno = Long.valueOf(tmpArr[0]);
// 		// 		}
// 		// 	}
// 		// 	ret = service.list(pno, cri, req.getSession().getAttribute("member"));
// 		// } else { // 단일 조회
// 		// 	Long rno = Long.valueOf(uri);
// 		// 	ret = service.findBy(rno);
// 		// }
// 		// resp.setContentType("application/json; charset=utf-8");
// 		// resp.getWriter().print(gson.toJson(ret));
// 	}

// 	// @Override
// 	// protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 	// 	Reply reply = gson.fromJson(req.getReader(), Reply.class);
// 	// 	System.out.println(reply);
// 	// 	service.write(reply);
// 	// }

// 	// @Override
//   //   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 	// 	Reply reply = gson.fromJson(req.getReader(), Reply.class);
// 	// 	service.modify(reply);

//   //   }

// }
