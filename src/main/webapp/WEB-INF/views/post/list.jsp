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
	            	<h2 class="float-start">Post List</h2>
	            	<a href="write?${pageDto.cri.qs2}" class="btn btn-dark float-end">글쓰기</a>
	           	</div>
				<form>
					<input type="hidden" name="page" value="${pageDto.cri.page}">
					<input type="hidden" name="category" value="${pageDto.cri.category}">
					<div class="row my-3">
						<div class="col-2">
							<select class="form-select" name="type">
							<option value="TCW" ${pageDto.cri.type == 'TCW' ? 'selected' : ''}>전체</option>
							<option value="T" ${pageDto.cri.type == 'T' ? 'selected' : ''}>제목</option>
							<option value="C" ${pageDto.cri.type == 'C' ? 'selected' : ''}>내용</option>
							<option value="W" ${pageDto.cri.type == 'W' ? 'selected' : ''}>작성자</option>
							<option value="TC" ${pageDto.cri.type == 'TC' ? 'selected' : ''}>제목+내용</option>
							<option value="TW" ${pageDto.cri.type == 'TW' ? 'selected' : ''}>제목+작성자</option>
							<option value="CW" ${pageDto.cri.type == 'CW' ? 'selected' : ''}>내용+작성자</option>
							</select>
						</div>
						<div class="col-4">
							<div class="input-group mb-3">
							<input type="text" class="form-control" placeholder="검색어 입력" name="keyword" value="${pageDto.cri.keyword}">
							<button class="btn btn-dark" type="submit">검색</button>
							</div>
						</div>
						<div class="col-4"></div>
						<div class="col-2 float-end">
							<select class="form-select" name="amount">
							<option value="10" ${pageDto.cri.amount == 10 ? 'selected' : ''}>10개씩 보기</option>
							<option value="20" ${pageDto.cri.amount == 20 ? 'selected' : ''}>20개씩 보기</option>
							<option value="30" ${pageDto.cri.amount == 30 ? 'selected' : ''}>30개씩 보기</option>
							<option value="50" ${pageDto.cri.amount == 50 ? 'selected' : ''}>50개씩 보기</option>
							</select>
						</div>
					</div>
				</form>
                <table class="table table-hover text-center" style="table-layout: fixed;">
                    <thead>
                        <tr>
                            <th style="width: 6%;">글번호</th>
                            <th>제목</th>
                            <th style="width: 15%;">작성자</th>
                            <th style="width: 15%;">작성일</th>
                            <th style="width: 6%;">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${posts}" var="p">
                        <tr>
                            <td>${p.pno}</td>
                            <td class="text-truncate text-start"><a href="view?pno=${p.pno}&${pageDto.cri.qs2}" class="text-decoration-none text-dark">${p.title} </a>
                            	<c:if test="${p.attachFlag}">
                            		<i class="fa-solid fa-paperclip"></i>
                            	</c:if>
                            </td>
                            <td>${p.writer}</td>
                            <td>${p.regdate}</td>
                            <td>${p.viewCount}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <ul class="pagination justify-content-center my-5">
                	<c:if test="${pageDto.doublePrev}">
	                    <li class="page-item"><a class="page-link text-dark" href="list?page=${pageDto.startPage-1}&${pageDto.cri.qs}"><i class="fa-solid fa-angles-left"></i></a></li>
                	</c:if>
                    <c:if test="${pageDto.prev}">
                    	<li class="page-item"><a class="page-link text-dark" href="list?page=${pageDto.cri.page-1}&${pageDto.cri.qs}"><i class="fa-solid fa-angle-left"></i></a></li>
                    </c:if>
                    
                    <c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" var="page">
	                    <li class="page-item ${page == pageDto.cri.page ? 'active' : ''}"><a class="page-link text-dark" href="list?page=${page}&${pageDto.cri.qs}">${page}</a></li>
                    </c:forEach>
                    
                    <c:if test="${pageDto.next}">
                    	<li class="page-item"><a class="page-link text-dark" href="list?page=${pageDto.cri.page+1}&${pageDto.cri.qs}"><i class="fa-solid fa-angle-right"></i></a></li>
                    </c:if>
                    <c:if test="${pageDto.doubleNext}">
                    	<li class="page-item"><a class="page-link text-dark" href="list?page=${pageDto.endPage+1}&amount=${pageDto.cri.amount}&category=${pageDto.cri.category}"><i class="fa-solid fa-angles-right"></i></a></li>
                	</c:if>
                </ul> 
            </main>
            <jsp:include page="../common/footer.jsp" />
        </div>
    </body>
</html>