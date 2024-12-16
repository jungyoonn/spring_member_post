<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                $("#search").click(function() {
                    const keyword = $(this).prev().val();
                    if(!keyword) return;

                    const data = {
                        // keyword : $(this).prev().val(),
                        // keyword : keyword,
                        // keyword, 셋이 다 같은 뜻
                        keyword,
                        confmKey : 'U01TX0FVVEgyMDI0MTIxNjEyNTEzMjExNTMzMTk=',
                        currentPage : 1,
                        countPerPage : 100,
                        resultType : 'json'
                    };
                    console.log(data);
                    
                    $.ajax({
                        url : "https://business.juso.go.kr/addrlink/addrLinkApiJsonp.do",
                        type : 'get',
                        data,
                        dataType : 'jsonp',
                        crossDomain : true,
                        success : function(data) {
                            console.log(data);
                            console.log("결과는");
                            console.log(data.results.juso.length);

                            str = '';
                            for(let i = 0; i < data.results.juso.length; i++) {
                                str += `<li class="list-group-item"><a href="#" class="small">\${data.results.juso[i].roadAddr}</a></li>`;
                            }
                            $(".search-result-wrap").html(str);
                            $("ul.search-result-wrap").on("click", "a", function() {
                                console.log($("#roadAddr").text());
                                $("#roadAddr").val($(this).text());
                                $(this).closest("ul").empty();
                            });
                        },
                        error : function(xhr, msg) {
                            console.log(msg);
                        }
                    })
                    
                    // 쌤 코드
                    // $("ul").on("click", "a", function() {
                    //     $("#roadAddr").val($(this).text());
                    // });
                });
            });
        </script>
    </head>
    <body>
        <div class="wrap">
           <jsp:include page="../common/header.jsp" />
            <main class="container">
                <form name="form" method="post" class="mx-auto col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4 col xxl-3 card p-3 my-5">
                    <h4 class="text-center mt-2">회원가입</h4>
                    <input type="text" class="form-control my-2" id="id" placeholder="아이디" name="id">
                    <input type="password" class="form-control my-2" id="pw" placeholder="비밀번호" name="pw">
                    <input type="text" class="form-control my-2" id="name" placeholder="이름" name="name">
                    <input type="email" class="form-control my-2" id="email" placeholder="이메일" name="email">
                    <input type="text" class="form-control my-2" id="roadAddr" placeholder="주소" name="roadAddr" readonly>
                    <input type="text" class="form-control my-2" id="detailAddr" placeholder="상세 주소" name="detailAddr">
                    <div class="input-group mb-3 my-2">
                        <input type="text" class="form-control " placeholder="도로명검색">
                        <button class="btn btn-outline-dark" type="button" id="search">검색</button>
                    </div>
                    <ul class="list-group search-result-wrap">
                        <!-- <li class="list-group-item"><a href="#" class="small">서울특별시 동작구 상도로 59</a></li>
                        <li class="list-group-item"><a href="#" class="small">서울특별시 동작구 상도로 59</a></li>
                        <li class="list-group-item"><a href="#" class="small">서울특별시 동작구 상도로 59</a></li> -->
                    </ul>
                    <button class="btn btn-dark">가입하기</button>
                </form>
            </main>
            <jsp:include page="../common/footer.jsp" />
        </div>
    </body>
</html>