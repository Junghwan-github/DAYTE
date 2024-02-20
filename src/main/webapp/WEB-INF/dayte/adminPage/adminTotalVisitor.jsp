<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/adminHead.jsp"%>
<div class="h-100 px-3" style="padding-top: 70px; min-width:375px;">
    <div class=" px-5">
        <p class="h2 ps-2 titleName border-bottom pb-2 mb-4">방문자 집계</p>
        <div class="d-flex flex-row justify-content-end align-items-center">
            <label for="selectVisit" class="d-block m-0 pe-2 adminUserFS"> <i class="bi bi-calendar4-week adminUserFS"></i> 기간 선택 | </label>
        <select id="selectVisit" class="p-1 select col-1 text-center rounded-1 select-style adminUserFS">
            <option value="1" selected>1주일</option>
            <option value="2">1달</option>
            <option value="3">6개월</option>
            <option value="4">1년</option>
        </select>
        </div>
        <div class="d-flex justify-content-center" >
            <div class="w-75">
                <canvas id="myChart"></canvas>
            </div>
        </div>

    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/admin/visitors.js"></script>
<%@include file="layout/adminFooter.jsp"%>