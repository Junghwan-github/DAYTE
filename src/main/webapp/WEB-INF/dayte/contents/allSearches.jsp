<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<%--여기 각자 쓸 css--%>
<link rel="stylesheet" href="/css/main/index.css">
<link rel="stylesheet" href="/css/main/slider.css">
<title>DAYTE</title>
</head>

<body>
<div id="headerBack"></div>
<%@include file="../layout/header.jsp" %>
<main>
    <c:if test="${!empty contentsList}">
        <c:forEach var="contents" items="${contentsList}">
            <a href="/contents/detail/${contents.uuid}"><h1>${contents.businessName}</h1></a>
        </c:forEach>
    </c:if>
    <c:if test="${!empty postList}">
        <c:forEach var="post" items="${postList}">
           <a href="/post/${post.id}">
               <h1>${post.title}</h1>
           </a>
        </c:forEach>
    </c:if>
</main>

<script src="/js/main/index.js"></script>
<script src="/js/main/editForm.js"></script>
<%@include file="../layout/footer.jsp" %>