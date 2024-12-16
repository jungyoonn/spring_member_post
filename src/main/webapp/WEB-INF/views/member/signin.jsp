<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common/head.jsp" />
        <style>
            form {
                box-shadow: -3px -3px 4px #aaa;
            }
        </style>
        <script>
            $(function() {
            	
            });
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <body>
        <div class="wrap">
            <jsp:include page="../common/header.jsp" />
            <main class="container">
                <form name="form" method="post" class="mx-auto col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4 col xxl-3 card p-3 my-5 login">
                    <h4 class="text-center mt-2">로그인</h4>
                    <input type="text" class="form-control my-2" id="id" placeholder="아이디" name="id" value="${cookie['remember-id'].value }">
                    <input type="password" class="form-control my-2" id="pw" placeholder="비밀번호" name="pw">
                    <div class="form-check form-switch my-3">
                        <input class="form-check-input" type="checkbox" id="mySwitch" name="remember-id" value="${cookie['remember-id']}" ${empty cookie['remember-id'] ? '' : 'checked' }>
                        <label class="form-check-label" for="mySwitch">로그인 정보 저장하기</label>
                    </div>
                    <button class="btn btn-dark">로그인</button>
                    <c:if test="${not empty msg}">
                        <p class="mt-3 text-danger text-center small">로그인 실패 - 아이디와 비밀번호를 확인하세요</p>
                    </c:if>
                </form>
            </main>
            <jsp:include page="../common/footer.jsp" />
        </div>
        <script>/* 
            if($.cookie("id")) {
                $("#mySwitch").prop("checked", "true");
                $("#id").val($.cookie("id"));
                $("#pwd").val($.cookie("pw"));
            }

            $(".login").click(function(){
                if($("#mySwitch").prop("checked")){
                    $.cookie('id', $("#id").val(), {expires:1});
                    $.cookie('pw', $("#pwd").val(), {expires:1});
                }
            }) */
        </script>
    </body>
</html>