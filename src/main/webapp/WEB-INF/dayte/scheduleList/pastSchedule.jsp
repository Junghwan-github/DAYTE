<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/schedule/schedule.css">
<link rel="stylesheet" href="/css/schedule/scheduleSlider.css">
<link rel="stylesheet" href="/css/schedule/scheduleModal.css">
<link rel="stylesheet" href="/css/schedule/pastScheduleModal.css">
<title>DAYTE | 지난 일정 관리</title>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>

<%--<c:forEach var="schedule" items="${pastScheduleList}">
<h1>${schedule.title}</h1>
</c:forEach>--%>
<main>
    <section id="schedulePrint">
        <c:if test="${!empty pastScheduleList}">
            <c:forEach var="scheduleList" items="${pastScheduleList}" varStatus="loop">
                <c:set var="startDate" value="${scheduleList.startDate.toEpochDay()}"/>
                <c:set var="endDate" value="${scheduleList.endDate.toEpochDay()}"/>
                <c:if test="${startDate - dDay >= 0}">



                    <div class="scheduleContentsItem">
                        <div class="scheduleCard${loop.index+1}">
                            <div>
                                <button class="btn-open-modal" onclick="openModal(this)">Modal 열기</button>
                            </div>
                            <i class="xi-ellipsis-v">
                            </i>
                            <div class="menuList">
                                <ul>
                                    <li><a href="#" class="detailedSchedule"
                                           onclick="detailedLinks(`${scheduleList.uuid}`)">상세보기</a></li>
                                    <li><a href="#" class="deleteSchedule"
                                           onclick="deleteLinks(`${scheduleList.startDate}`)">삭제</a></li>
                                </ul>
                            </div>
                            <div class="scheduleItemSliderArea">
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
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </section>
</main>


<div class="daysPrint">
    <div class="modal">
        <c:forEach var="scheduleModal" items="${pastScheduleList}" varStatus="loop">
            <c:set var="startDate" value="${scheduleModal.startDate.toEpochDay()}"/>
            <c:set var="endDate" value="${scheduleModal.endDate.toEpochDay()}"/>
            <div class="modal_body scheduleCard${loop.index+1}" style="display: none">
                <button id="close-modal" onclick="closeModal(this)">모달 창 닫기</button>
                <ul id="tabMenu">
                    <c:forEach var="i" begin="0" end="${endDate - startDate}">
                        <c:set var="tab" value="tab${i+1}"/>
                        <li class="${tab} closeTab">${i+1}일차</li>
                    </c:forEach>
                </ul>
                <div id="tabPage">
                    <c:forEach var="scheduleDates" items="${scheduleModal.scheduleDates}" varStatus="loop" >
                        <div class="tab${loop.index + 1} closeTabPage">
                            <ul>
                                <c:forEach var="detailedSchedule" items="${scheduleDates.detailedScheduleList}">
                                    <li>
                                        <div class="contentsImg">
                                            <img src="${detailedSchedule.adminContents.adminContentsImageList[0].imageURL}" >
                                        </div>
                                        <ul class="contentsInfo">
                                            <li>${detailedSchedule.adminContents.businessName}</li>
                                            <li>${detailedSchedule.adminContents.category}</li>
                                            <li>${detailedSchedule.adminContents.detailedAddress}</li>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:forEach>

                    <%--<c:forEach var="scheduleDate" items="${scheduleModal.scheduleDates}">
                            <li class="tab${loop.index+1} closeTabPage">
                                <c:forEach var="detailedSchedule" items="${scheduleDate.detailedScheduleList}">
                                <h1>${detailedSchedule.adminContents.businessName}</h1>
                                <h1>${detailedSchedule.adminContents.category}</h1>
                                </c:forEach>
                            </li>
                    </c:forEach>--%>
                    <%--<c:forEach var="i" begin="0" end="${endDate - startDate}">
                        <c:set var="tab" value="tab${i+1}"/>
                        <li class="${tab} closeTabPage">
                            <c:forEach var="scheduleDates" items="${scheduleModal.scheduleDates}" >
                                <c:forEach var="detailedSchedule" items="${scheduleDates}">


                                </c:forEach>
                            </c:forEach>
                            지금 일차는 ${i+1}일차
                            <h1>${scheduleModal.uuid}</h1>
                            <h1>${scheduleModal.scheduleDates}</h1>

                        </li>

                    </c:forEach>--%>
                </div>
            </div>
        </c:forEach>


        <%--<c:forEach var="scheduleTab" items="${pastScheduleList}" varStatus="loop">
            <c:set var="startDate" value="${scheduleTab.startDate.toEpochDay()}"/>
            <c:set var="endDate" value="${scheduleTab.endDate.toEpochDay()}"/>

            <div class="modal_body scheduleCard${loop.index+1}">
                <button id="close-modal" onclick="closeModal(this)">모달 창 닫기</button>
                <ul id="tabMenu">
                    <c:forEach var="i" begin="0" end="${endDate - startDate}">
                        <c:set var="tab" value="tab${i+1}"/>
                        <li class="${tab} closeTab">${i+1}일차</li>
                    </c:forEach>
                </ul>
                <ul id="tabPage">
                    <c:forEach var="i" begin="0" end="${endDate - startDate}">
                        <c:set var="tab" value="tab${i+1}"/>
                        <li class="${tab} closeTabPage">지금 일차는 ${i+1}일차</li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>--%>
    </div>
</div>


<%--<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>--%>
<%--<script src="/js/schedule/cal.js"></script>
<script src="/js/schedule/calendar.js"></script>
<script src="/js/schedule/contentsList.js"></script>--%>
<script src="/js/schedule/pastSchedule.js"></script>
<script src="/js/schedule/pastScheduleModal.js"></script>


<%@include file="../layout/footer.jsp" %>
