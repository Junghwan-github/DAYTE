<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/schedule/pastSchedule.css">
<link rel="stylesheet" href="/css/schedule/pastScheduleModal.css">
<link rel="stylesheet" href="/css/schedule/scheduleSlider.css">

<title>DAYTE | 내 리얼리뷰</title>
</head>

<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>


<main>
    <div id="past-schedule-container">
        <nav id="past-schedule-side-nav">
            <ul>
                <li>
                    <a href="/schedule/pastSchedule" class="past-schedule-side-nav-active">리얼리뷰 등록<i
                            class="xi-angle-right-min"></i></a>
                </li>
                <li>
                    <a href="/myReview">내가 등록한 글<i class="xi-angle-right-min"></i></a>
                </li>
                <li>
                    <a href="/myRating">내가 등록한 리뷰<i class="xi-angle-right-min"></i></a>
                </li>
            </ul>
        </nav>
        <div id="past-schedulePrint-container">
            <h1>종료된 일정</h1>
            <c:if test="${!empty pastScheduleList}">
                <c:forEach var="scheduleList" items="${pastScheduleList}" varStatus="loop">
                    <c:set var="startDate" value="${scheduleList.startDate.toEpochDay()}"/>
                    <c:set var="endDate" value="${scheduleList.endDate.toEpochDay()}"/>
                    <c:if test="${startDate - dDay >= 0}">


                        <div class="scheduleContentsItem">
                            <div class="scheduleCard${loop.index+1} my-review-wrapper">
                                <div class="past-scheduleItemSliderArea">
                                    <ul class="scheduleItemSlider">
                                        <li><img
                                                src="${scheduleList.scheduleDates[0].detailedScheduleList[0].adminContents.adminContentsImageList[0].imageURL}">
                                        </li>
                                        <li><img
                                                src="${scheduleList.scheduleDates[0].detailedScheduleList[0].adminContents.adminContentsImageList[1].imageURL}">
                                        </li>
                                        <li><img
                                                src="${scheduleList.scheduleDates[0].detailedScheduleList[0].adminContents.adminContentsImageList[2].imageURL}">
                                        </li>
                                    </ul>
                                </div>

                                <div class="contentTextArea">
                                    <ul class="contentsText">
                                        <li><span class="contentsTextTitle">${scheduleList.title}</span></li>
                                        <li><span class="contentsTextDate">${scheduleList.startDate}&nbsp;</span>
                                            <span>- ${scheduleList.endDate}</span></li>
                                    </ul>
                                </div>
                                <div class="open-list-button">
                                    <button class="btn-open-modal scheduleCard${loop.index+1}">자세히</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
    </div>
</main>
<div class="daysPrint">
    <div class="modal">
        <c:forEach var="scheduleModal" items="${pastScheduleList}" varStatus="loop">
            <c:set var="startDate" value="${scheduleModal.startDate.toEpochDay()}"/>
            <c:set var="endDate" value="${scheduleModal.endDate.toEpochDay()}"/>
            <div class="modal_body scheduleCard${loop.index+1}">
                <div class="close-modal-btn">
                    <span class="material-symbols-outlined detail-schedule-modal">close</span>
                </div>
                <div class="modal-layout-container">
                    <div class="left-modal-wrapper">
                        <ul id="tabMenu">
                            <c:forEach var="i" begin="0" end="${endDate - startDate}">
                                <c:set var="tab" value="tab${i+1}"/>
                                <li class="${tab}">${i+1}일 차<i class="xi-angle-right-min"></i></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="right-modal-wrapper">
                        <div id="tabPage">
                            <c:forEach var="scheduleDates" items="${scheduleModal.scheduleDates}" varStatus="loop">
                                <div class="tab${loop.index + 1} display-hide">
                                    <ul>
                                        <c:forEach var="detailedSchedule" items="${scheduleDates.detailedScheduleList}">
                                            <li class="contents">
                                                <div class="contentsImg">
                                                    <img src="${detailedSchedule.adminContents.adminContentsImageList[0].imageURL}">
                                                </div>
                                                <ul class="contentsInfo">
                                                    <li class="category-keyword"><span>${detailedSchedule.adminContents.category}</span><span>${detailedSchedule.adminContents.keyword}</span></li>
                                                    <li class="businessName"
                                                        data-uuid="${detailedSchedule.adminContents.uuid}">${detailedSchedule.adminContents.businessName}</li>
                                                    <li class="address">${detailedSchedule.adminContents.detailedAddress}</li>

                                                    <li class="write-review">
                                                        <button type="button" value="${detailedSchedule.adminContents.uuid}"
                                                                onclick="goToReview(this.value)">리뷰 쓰기
                                                        </button>
                                                    </li>
                                                </ul>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="/js/schedule/pastSchedule.js"></script>
<script src="/js/schedule/pastScheduleModal.js"></script>
<script src="/js/contentReply/goToReview.js"></script>


<%@include file="../layout/footer.jsp" %>
