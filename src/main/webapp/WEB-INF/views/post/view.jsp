<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common/head.jsp" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment-with-locales.min.js" integrity="sha512-4F1cxYdMiAW98oomSLaygEwmCnIP38pb4Kx70yQYqRwLVCs3DbRumfBq82T08g/4LJ/smbFGFpmeFlQgoDccgg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <body>
        <div class="wrap">
            <jsp:include page="../common/header.jsp" />
            <main class="container">
            <div class="clearfix py-4">
            	<h2 class="float-start">Post View</h2>
           	</div>
                <div class="col md-9 mx-auto">
                    <label for="title" class="form-label mt-3"><i class="fa-solid fa-pen"></i> <b>제목:</b></label>
                    <input type="text" class="form-control" id="title" placeholder="글 제목을 입력해 주세요" name="title" value="${post.title}" disabled>

                    <label for="content" class="form-label mt-3"><i class="fa-regular fa-clipboard"></i> <b>내용:</b></label>
                    <textarea class="form-control" id="content" placeholder="내용을 입력해 주세요" name="content" rows="15" disabled>${post.content}</textarea>

                    <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user-pen"></i> <b>작성자:</b></label>
                    <input type="text" class="form-control" id="writer" name="writer" value="${post.writer}" disabled>
                    
                    <label for="regdate" class="form-label mt-3"><i class="fa-regular fa-calendar"></i> <b>작성일:</b></label>
                    <input type="text" class="form-control" id="regdate" name="regdate" value="${post.regdate}" disabled>

                    <label for="updatedate" class="form-label mt-3"><i class="fa-regular fa-calendar-plus"></i> <b>수정일:</b></label>
                    <input type="text" class="form-control float-end" id="updatedate" name="updatedate" value="${post.updatedate}" disabled><br>

					<label class="form-label mt-3"><i class="fa-solid fa-paperclip"></i> <b>첨부파일:</b><br></label>
                    <ul class="list-group attach-result mt-2">
	                    <c:if test="${empty post.attachs}">첨부파일이 없습니다.</c:if>
	                    <c:forEach items="${post.attachs}" var="a">
							<li class="list-group-item"><a href="${cp}download?uuid=${a.uuid}&origin=${a.origin}&path=${a.path}">${a.origin}</a></li>
	                    </c:forEach>
					</ul>
                    
                    <div class="clearfix mt-3 mb-2">
                    	<label class="form-label float-start"><i class="fa-regular fa-comment-dots text-dark"></i> <b>내 댓글</b><br></label>
					</div>
                    
                    <ul class="list-group small my-2 my-replies">
                   		
                   	</ul>
                    
                    <div class="clearfix mt-5 mb-2">
                    	<label class="form-label float-start"><i class="fa-regular fa-comment-dots text-dark"></i> <b>댓글</b><br></label>
						<button type="button" class="btn btn-outline-dark btn-sm float-end" id="btnWriteReply">댓글작성</button>
					</div>

                   	<ul class="list-group small replies"></ul>

					<div class="d-grid my-3">
						<button class="btn btn-outline-secondary btn-block btn-more-reply">댓글 더보기</button>
					</div>

                    <div class="text-center my-5">
	                    <c:if test="${post.writer == member.id}">
	                        <a href="modify?pno=${post.pno}&${cri.qs2}" class="btn btn-outline-dark">수정</a>
	                        <a href="remove?pno=${post.pno}&${cri.qs2}" class="btn btn-secondary" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
	                    </c:if>
                        <a href="list?${cri.qs2}" class="btn btn-dark">목록</a>
                    </div>
                </div>
            </main>
            <script src="${cp}js/reply.js"></script>
            <script>
            	// 해결되지 않은 이슈 : 비 로그인 시 내 댓글 화면 처리, 내 댓글이 없을 시 처리
            	moment.locale('ko');
                const pno = '${post.pno}';
                
                // 목록 조회
                function list(cri, myOnly) {
	                replyService.list(pno, cri, function(data) {
	                	console.log(data);
	                	if(!data.list.length) {
	                		$(".btn-more-reply")
	                		.prop("disabled", true)
	                		.text("마지막 댓글입니다.")
	                		.removeClass("btn-outline-secondary")
	                		.addClass("btn-secondary");
	                		return;
	                	}
	                	
	                    let myStr = "";
	                    for(let i in data.myList) {
	                    	myStr += makeLi(data.myList[i]);
	                    }
	                    $(".my-replies").html(myStr);    
	                	
	                    if(myOnly) return;
	                    
	                    let str = "";
	                    for(let i in data.list) {
	                        str += makeLi(data.list[i]);
	                    }     
	                    $(".replies").append(str);               
	                });
                }
                list();

                // 댓글 더보기 버튼 클릭 시
                $(".btn-more-reply").click(function() {
                	const lastRno = $(".replies li:last").data("rno");
					list({lastRno});
                });
                
                function makeLi(reply) {
                    return `<li class="list-group-item" data-rno="\${reply.rno}">
                        <a class="text-decoration-none">
                            <p class="text-black fw-bold mt-3 text-truncate">\${reply.content}</p>
                            <div class="clearfix text-secondary">
                                <span class="float-start text-muted">\${reply.writer}</span>
                                <span class="float-end small text-muted mx-2">\${moment(reply.regdate, 'yyyy/MM/DD HH:mm:ss').fromNow()}</span>
                                <a class="btn-reply-remove float-end small text-danger" href="#">삭제</button>
                            </div>
                        </a>
                    </li>`;
                }
                
                //댓글 클릭 시
                $(".replies, .my-replies").on("click", "li", function() {
                	const rno = $(this).data("rno");
                	replyService.view(rno, function(data) {
                		$("#replyModal").find(".modal-footer div button").hide()
                			.filter(":gt(0)").show();
                		
	                	$("#replyModal").data("rno", rno).modal("show");
	                	$("#replyContent").val(data.content);
	                	$("#replyWriter").val(data.writer);
                		console.log(data);
                	})
                });
                
                $(".replies, .my-replies").on("click", "li .btn-reply-remove", function() {
                	event.preventDefault();                	
                	if(!confirm("삭제하시겠습니까?")) {
                		return false;
                	}
                	
                	const $li = $(this).closest("li"); 
                	const rno = $li.data("rno");
                	replyService.remove(rno, function(data) {
                		alert("삭제되었습니다.")
                		$li.remove();
                		list(undefined, true);
                	});
                	return false;
                });
                
                // 댓글 쓰기 버튼 클릭 시
                $("#btnWriteReply").click(function() {
                	$("#replyModal").find(".modal-footer div button").hide()
        				.filter(":eq(0)").show();
                	$("#replyModal").modal("show");
                	$("#replyContent").val("");
                	$("#replyWriter").val("${member.id}");
                });
                
                $(function() {
                	// 댓글 작성
                	$("#btnReplyWriteSubmit").click(function() {
                		const writer = $("#replyWriter").val();
                		const content = $("#replyContent").val();
                		const reply = {pno, writer, content};
                		
                		replyService.write(reply, function(data) {
                			$("#replyModal").modal("hide");
                			list(undefined, true);
                		});
                	});
                	
                	// 댓글 수정
                	$("#btnReplyModifySubmit").click(function() {
                		if($("#replyWriter").val() != "${member.id}") {
                			alert("본인의 댓글만 수정 가능합니다.");
                			return;
                		}
                		
                		const rno = $("#replyModal").data("rno");
                		const content = $("#replyContent").val();
                		const reply = {rno, content};
                		
                		replyService.modify(reply, function(data) {
                			$("#replyModal").modal("hide");
                			$(`.replies li[data-rno='\${rno}'] p`).text(content);
                			list(undefined, true);
                		});
                	});
                	
                	// 댓글 삭제
                	$("#btnReplyRemoveSubmit").click(function() {
                		if($("#replyWriter").val() != "${member.id}") {
                			alert("본인의 댓글만 삭제 가능합니다.");
                			return;
                		}
                		
                		const rno = $("#replyModal").data("rno");
                		const $li = $(`.replies li[data-rno='\${rno}']`);
                		replyService.remove(rno, function(data) {
                			$("#replyModal").modal("hide");
                			$li.remove();
                			list(undefined, true);
                		});
                	});
                });
            </script>
            <jsp:include page="../common/footer.jsp" />
        </div>
        
        <!-- The Modal -->
		<div class="modal fade" id="replyModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">댓글 작성</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
			      <label for="replyContent" class="mb-2">내용</label>
		          <input type="text" class="form-control mb-3" id="replyContent"> 
		          
		          <label for="replyWriter" class="mb-2">작성자</label>
		          <input type="text" class="form-control mb-3" id="replyWriter" readonly value="${member.id}"> 
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
			      <div>
			        <button type="button" class="btn btn-secondary" id="btnReplyWriteSubmit" data-bs-dismiss="modal">작성</button>
			        <button type="button" class="btn btn-outline-secondary" id="btnReplyModifySubmit" data-bs-dismiss="modal">수정</button>
			        <button type="button" class="btn btn-outline-dark" id="btnReplyRemoveSubmit" data-bs-dismiss="modal">삭제</button>
			      </div>
		           <button type="button" class="btn btn-dark" data-bs-dismiss="modal">취소</button>
		      </div>
		    </div>
		  </div>
		</div>
    </body>
</html>