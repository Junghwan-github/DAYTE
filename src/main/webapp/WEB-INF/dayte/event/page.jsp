<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css --%>
<link rel="stylesheet" href="/css/main/eventPage.css">
<title>DAYTE | ${sliderList[0].mainTitle} ${sliderList[0].subTitle} </title>
</head><%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>

<main> <%-- main --%>
    <div class="event-images-area">
    <h1>이벤트</h1>
        <div class="event-content-area">
            <h2>${sliderList[0].mainTitle} ${sliderList[0].subTitle} </h2>
            ${sliderList[0].content}
        </div>
    </div>
</main>

<jsp:include page="../layout/footer.jsp"/>