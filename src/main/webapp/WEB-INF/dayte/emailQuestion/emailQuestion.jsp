<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>



<title>1:1 문의하기</title>

</head>

<body>

<div>

</div>

<%@include file="../layout/header.jsp"%>

<form action="/question" method="POST">
    <div class="title">
        <span>제목</span>
        <input type="text" id="title">
    </div>
    <div class="content">
        <span>내용</span>
        <textarea id="content"></textarea>
    </div>



</form>

<button type="button" id="sendEmail">문의하기</button>

<script src="/js/emailQuestion.js"></script>


<%@include file="../layout/footer.jsp"%>