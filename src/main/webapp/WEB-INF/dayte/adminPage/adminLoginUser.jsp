<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/adminHead.jsp"%>
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="h-100 px-5">
        <p class="h4 titleName border-bottom pb-2">접속자 현황</p>
        <div class="table-responsive">
            <table class="table table-striped border" >
                <tbody class="align-middle">
                        <tr>
                            <td class="text-center">어쩌구</td>
                            <td class="text-center">아이디</td>
                            <td class="text-center">저쩌구</td>
                        </tr>
                        <tr>
                            <td class="text-center">어쩌구</td>
                            <td class="text-center">아이디</td>
                            <td class="text-center">저쩌구</td>
                        </tr>
                </tbody>
                <caption>접속자 수 : </caption>
            </table>
        </div>
    </div>
</div>
<%@include file="layout/adminFooter.jsp"%>