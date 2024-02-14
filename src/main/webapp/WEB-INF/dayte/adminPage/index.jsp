<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/adminHead.jsp"%>
<div class="row">
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