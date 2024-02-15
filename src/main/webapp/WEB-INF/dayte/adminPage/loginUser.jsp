<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="layout/adminHead.jsp" %>
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="h-100 px-5">
        <p class="h4 titleName border-bottom pb-2">로그인한 회원</p>
        <div class="table-responsive">
            <table class="table table-striped border" >
                <tbody class="align-middle">
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <caption>접속자 수 : ${fn:length(userList)}</caption>
            </table>
        </div>
    </div>
<%@include file="layout/adminFooter.jsp" %>