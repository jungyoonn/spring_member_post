<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common/head.jsp" />
    </head>
    <body>
        <div class="wrap">
            <jsp:include page="../common/header.jsp" />
            <main class="container">
            <div class="clearfix py-4">
            	<h2 class="float-start">Post View</h2>
           	</div>
                <div class="col md-9 mx-auto">
                	<form method="post" action="modify?${cri.qs2}">
	                	<input type="hidden" name="pno" value="${post.pno}">
	                
	                    <label for="title" class="form-label mt-3"><i class="fa-solid fa-pen"></i> <b>제목:</b></label>
	                    <input type="text" class="form-control" id="title" placeholder="글 제목을 입력해 주세요" name="title" value="${post.title}">
	
	                    <label for="content" class="form-label mt-3"><i class="fa-regular fa-clipboard"></i> <b>내용:</b></label>
	                    <textarea class="form-control" id="content" placeholder="내용을 입력해 주세요" name="content" rows="15" >${post.content}</textarea>
	
	                    <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user-pen"></i> <b>작성자:</b></label>
	                    <input type="text" class="form-control" id="writer" name="writer" value="${post.writer}" disabled>
	                    
	                    <div class="text-center my-5">
	                    	<button class="btn btn-outline-dark">수정</button>
	                        <a href="view?pno=${post.pno}&${cri.qs2}" class="btn btn-dark">취소</a>
	                    </div>
                    </form>
                </div>
            </main>
            <jsp:include page="../common/footer.jsp" />
        </div>
    </body>
</html>