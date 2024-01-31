<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>

    <title>공지사항</title>

    <link href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>


</head>

<body>
<%@include file="../layout/header.jsp"%>
<script src="/js/main/header.js"></script>
<h1> 상세보기 </h1>

<div>
    <form>
        <c:forEach var="file" items="${notice.files}">
            <a href="/notice/${notice.no}/files/${file.id}"><p>${file.originalName}</p></a>
        </c:forEach>
        <div>
           <input type="text" id="title" value="${notice.title}" readonly>
        </div>
        <div>
            <textarea rows="5" class="form-control" id="content" readonly>${notice.content}</textarea>
        </div>



    </form>
    <sec:authorize access="hasRole('ADMIN')">
    <a href="/update/${notice.no}"><button id="btn-update"> 수정 </button></a>
    </sec:authorize>
    <button id="btn-cancel"> 뒤로가기 </button>

    <div>
        <c:if test="${!empty prevId}">
        <a href="/notice/${prevId}">이전글로 가기</a>
            <p>${prevTitle}</p>
        </c:if>
    </div>


</div>
    <c:if test="${!empty nextId}">
    <a href="/notice/${nextId}">다음글로 가기</a>
        <p>${nextTitle}</p>
    </c:if>





<script src="/js/notice/cancel.js"></script>

<%@include file="../layout/footer.jsp"%>