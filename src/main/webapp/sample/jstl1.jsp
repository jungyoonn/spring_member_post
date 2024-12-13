<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<c:set var="str" value="abcd" />
		<h3>${pageScope.str}</h3>
		<h3>${str}</h3> <!-- 스코프 정의를 안 하면 page이다 -->
		
		<c:set var="str2" value="efg" scope="request"/>
		<h3>${pageScope.str2}</h3> <!-- 출력 안 나옴 -->
		<h3>${requestScope.str2}</h3>
		
		<c:if test="${empty pageScope.str2 }" >
			<h3>페이지 스코프에 str2이 없습니다</h3>
		</c:if>
		<c:if test="${not empty pageScope.str2 }">
			<h3>페이지 스코프에 str2이 있습니다</h3>
		</c:if>
	</body>
</html>