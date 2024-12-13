<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		 <%
		 	Member member = Member.builder()
						 	.id("java").name("자바")
						 	.build();
		 	System.out.println(page == this);
		 	pageContext.setAttribute("member", member);
		 	
		 	// vo에 
		 	// public int getNum() { return 10; }
		 	// 을 추가하고 EL로 member.num을 출력해 보면 10이 잘 출력된다
		 	
		 	// 이름이 겹치면 page부터 탐색 시작 (좁은 범위부터 탐색한다)
		 	// application은 전역 값, 리디렉션을 하면 세션 값을 주고받기 때문에 40이 나온다
		 	pageContext.setAttribute("number", 10);
		 	request.setAttribute("number", 20);
		 	session.setAttribute("number", 30);
		 	application.setAttribute("number", 40);
		 %>
		 <h2>${member}</h2>
		 <h2>${member.id}</h2>
		 <h2>${member.getId()}</h2>
		 <h2>${member.name}</h2>
		 <h2>${member.email}</h2>
		 
		 <jsp:forward page="el2.jsp" />
		 <%
		 	/* response.sendRedirect("el2.jsp"); */
		 %>
	</body>
</html>