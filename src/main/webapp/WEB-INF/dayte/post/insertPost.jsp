<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css, jquery --%>

<link rel="stylesheet" href="/css/post/insertPost.css">

<title>일정후기 | 작성</title>
</head> <%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp" %> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>
<main> <%-- main --%>
    <h1 class="post-insert-title">일정후기 | 글쓰기</h1>
    <section id="insert-post-wrap">
        <div class="post-title-wrapper">
            <input type="text" placeholder="제목을 입력하세요" class="insert-post-title" id="title" name="title">
        </div>
        <%--    서머노트영역--%>
        <div id="summernote"></div>
        <div class="save-btn-wrapper">
            <button id="save-post" class="btn btn-secondary">등록</button>
        </div>
    </section>
    <script src="/js/post/post.js"></script>
</main>
<%@include file="../layout/footer.jsp" %>