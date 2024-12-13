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
            	<h2 class="float-start">새로운 글 작성</h2>
           	</div>
                <div class="col md-9 mx-auto">
                	<form method="post" action="write?page=1&${cri.qs}">
	                    <label for="title" class="form-label mt-3"><i class="fa-solid fa-pen"></i> <b>제목:</b></label>
	                    <input type="text" class="form-control" id="title" placeholder="글 제목을 입력해 주세요" name="title">
	
	                    <label for="content" class="form-label mt-3"><i class="fa-regular fa-clipboard"></i> <b>내용:</b></label>
	                    <textarea class="form-control" id="content" placeholder="내용을 입력해 주세요" name="content" rows="15" ></textarea>
	
	                    <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user-pen"></i> <b>작성자:</b></label>
	                    <input type="text" class="form-control" id="writer" name="writer" value="${member.id}" readonly>
	                    
	                    <label class="form-label mt-3"><i class="fa-solid fa-paperclip"></i> <b>첨부파일:</b><br></label>
	                    <label for="attach"><span class="btn btn-dark">추가하기</span></label>
						<span class="mx-2 attach-count-txt"></span>	                    
	                    <input type="file" class="d-none mb-2" id="attach" name="files" multiple>
	                    <ul class="list-group attach-result mt-2">

						</ul>
	                    
	                    <div class="text-center my-5">
	                    	<button class="btn btn-outline-dark">등록</button>
	                        <a href="list" class="btn btn-dark">취소</a>
	                    </div>
	                    
	                    <div class="uploaded-input">
	                    
	                    </div>
                    </form>
                </div>
            </main>
            <jsp:include page="../common/footer.jsp" />
        </div>
        <script>
			$("#fileForm").submit(function() {
				event.preventDefault();
			});
			
			$("#attach").change(function() {
				const url = '${cp}' + 'upload';
				const formData = new FormData();
				const files = this.files;
				
				if(!files) {
					$(".attach-count-txt").text("");
					$(".attach-result").empty();
				}
				
				for(let i = 0; i < files.length; i++) {
					formData.append("file", files[i]);
				}

				$.post({
					url,
					contentType:false,
					processData:false,
					data:formData
				})
				.done(function(data) {
					$(".attach-count-txt").text(data.length + "개의 파일");
					let str = '';
					let strHidden = '';
					for(let i in data) {
						str += `<li class="list-group-item">\${data[i].origin}</li>`;
						strHidden += `<input type="hidden" name="uuid" value="\${data[i].uuid}" >`;
						strHidden += `<input type="hidden" name="origin" value="\${data[i].origin}" >`;
						strHidden += `<input type="hidden" name="image" value="\${data[i].image}" >`;
						strHidden += `<input type="hidden" name="path" value="\${data[i].path}" >`;
					}
					$(".attach-result").html(str);
					$(".uploaded-input").html(strHidden);
					console.log(data);
				});
			});
		</script>
		
    </body>
</html>