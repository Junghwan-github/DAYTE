<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css, jquery --%>

<link rel="stylesheet" href="/css/post/insertPost.css">

<title>일정후기 | ${post.title}</title>
</head> <%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp" %> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>
<main> <%-- main --%>
    <h1 class="post-insert-title">일정후기 | 수정</h1>
    <section id="insert-post-wrap">
        <input type="hidden" id="id" value="${post.id}">
        <div class="post-title-wrapper">
            <input type="text" class="insert-post-title" id="title" name="title" value="${post.title}">
        </div>
        <%--    서머노트영역--%>
        <div id="summernote">${post.content}</div>
        <div class="save-btn-wrapper">
            <button id="btn-back" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            <button id="btn-update" class="btn btn-secondary">수정</button>
        </div>
    </section>
    <script src="/js/post/post.js"></script>
</main>
<%@include file="../layout/footer.jsp" %>