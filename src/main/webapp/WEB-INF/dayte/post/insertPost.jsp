<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- head --%>
<%@include file="../layout/head.jsp"%>

<%-- css, jquery --%>

<link rel="stylesheet" href="/css/post/insertPost.css">


</head> <%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp"%> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>
<main> <%-- main --%>

    <section id="insert-post-wrap">
        <h2>일정후기 | 글쓰기</h2>
        <div class="m-3">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <%--    서머노트영역--%>
    <div id="summernote">

    </div>
        <button id="save-post" class="btn btn-secondary">등록</button>
    </section>
    <script src="/js/post/post.js"></script>
</main>
<%@include file="../layout/footer.jsp" %>