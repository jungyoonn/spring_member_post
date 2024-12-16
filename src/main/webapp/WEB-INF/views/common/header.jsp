<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="container-fluid bg-light">
    <div class="container clearfix p-2">
        <a href="/" class="float-start"><img src="/imgs/logo.png" alt="로고" class="img-fluid" width="250"></a>
        <h1 class="text-center fw-bold p-3">게시판 레이아웃</h1>
    </div>
</header>
<nav class="navbar bg-dark navbar-expand-sm">
    <ul class="navbar-nav container justify-content-start">
        <li class="nav-item mx-4"><a class="text-white nav-link" href="${cp}/index">메인 페이지</a></li>
        <li class="nav-item mx-4"><a class="text-white nav-link" href="${cp}mypage">마이 페이지</a></li>
        <li class="nav-item mx-4"><a class="text-white nav-link" href="${cp}/post/list?category=1">공지사항</a></li>
        <li class="nav-item dropdown mx-4">
            <a class="text-white nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">게시판</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="${cp}/post/list?category=2">자유게시판</a></li>
                <li><a class="dropdown-item" href="${cp}/post/list?category=3">자료실</a></li>
                <li><a class="dropdown-item" href="${cp}gallery">갤러리</a></li>
            </ul>
        </li>
    </ul>
</nav>