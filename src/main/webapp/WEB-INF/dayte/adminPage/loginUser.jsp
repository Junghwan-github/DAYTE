<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/adminHead.jsp" %>
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5">
        <p class="h4 fw-bold titleName">접속자 현황</p>
        <div>
            <ul>
                <c:forEach var="user" items="${userList}">
                    <li>${user}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
<%@include file="layout/adminFooter.jsp" %>