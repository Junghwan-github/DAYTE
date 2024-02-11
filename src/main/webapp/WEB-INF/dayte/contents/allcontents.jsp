<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css --%>
<link rel="stylesheet" href="/css/contents/allcontents.css">
<title>DAYTE | ${contentsListCategory[0].category}</title>
</head><%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp" %> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>


<main> <%-- main --%>
    <h1>대구 ${contentsListCategory[0].category} </h1>
</main>


<script src="/js/contents/allcontents.js"></script>
<jsp:include page="../layout/footer.jsp"/>