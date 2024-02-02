<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%-- head --%>
<%@include file="../layout/head.jsp"%>

<%-- css, jquery --%>
<link rel="stylesheet" href="/css/layout/subnav.css">

<!-- 써머노트 -->
<link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">
<script src="/webjars/summernote/0.8.10/summernote-bs4.min.js"></script>

<%-- 부트스트랩 --%>
<link href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>

</head> <%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp"%> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>

<main> <%-- main --%>

    <div class="container mt-3">
        <form>
            <input type="hidden" id="id" value="${post.id}">
            <div class="mb-3">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" value="${post.title}">
            </div>
            <div class="mb-3">
                <label for="content">Content:</label>
                <textarea rows="5" class="form-control" id="content" name="content">${post.content}</textarea>
            </div>
        </form>

        <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
        <button id="btn-update" class="btn btn-secondary">포스트 수정</button>
    </div>

    <script>
        $(document).ready(function () {
            $('#content').summernote();
        });
    </script>

    <script src="/js/post/post.js"></script>
</main>

<jsp:include page="../layout/footer.jsp"/>