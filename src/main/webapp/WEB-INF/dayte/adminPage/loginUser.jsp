<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="layout/adminHead.jsp" %>
<div class="h-100px-3" style="padding-top: 70px; min-width:375px;">
    <div class=" px-5">
        <p class="h2 ps-2 titleName border-bottom pb-2 mb-4">로그인한 회원</p>
        <div class="table-responsive" style="min-height: 80vh;">
            <table class="table table-striped border" >
                <tbody class="align-middle">
                <c:forEach var="user" items="${userList}">
                        <tr style="height: 50px;">
                            <td class="text-center align-middle h4 fw-normal">${user}</td>
                        </tr>
                </c:forEach>
                <h1>${uniqueUsers}</h1>
                </tbody>
                <caption class="adminUserFS">접속자 수 : ${fn:length(userList)}</caption>
            </table>
        </div>
    </div>
<%@include file="layout/adminFooter.jsp" %>