<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/adminHead.jsp"%>
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5">
        <p class="h4 fw-bold titleName" >접속자 집계</p>
<div class="w-50 row">
    <div class="col">
        <canvas id="myChart"></canvas>
    </div>
</div>
<select class="select">
    <option value="1" selected>1주일</option>
    <option value="2">1달</option>
    <option value="3">6개월</option>
    <option value="4">1년</option>
</select>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/admin/visitors.js"></script>
<%@include file="layout/adminFooter.jsp"%>