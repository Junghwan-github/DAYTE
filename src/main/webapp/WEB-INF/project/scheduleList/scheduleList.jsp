<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/schedule.css">
<link rel="stylesheet" href="/css/scheduleSlider.css">
<title>일정 관리</title>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<script src="/js/header.js"></script>
<section id="searchContainer">
    <div class="searchArea">
        <form action="#" method="get">
            <input type="text" name="searchArea" placeholder="검색어를 입력하세요"/>
            <button type="submit"><i class="xi-search"></i></button>
        </form>
        <ul class="subNavIcon">
            <li><a href="#"><img src="/images/hotel-i.png"></a></li>
            <li><a href="#"><img src="/images/restaurant-i.png"></a></li>
            <li><a href="#"><img src="/images/cafe-i.png"></a></li>
            <li><a href="#"><img src="/images/events.png"></a></li>
        </ul>
    </div>
</section>
<main>
    <section id="modalArea">
        <div class="buttonArea">
            <button type="button" id="createSchedule" onclick="openModal()">일정 등록</button>
        </div>
        <form id="myForm">
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <input type="text" id="scheduleSubjectTitle" placeholder="일정 제목을 입력해주세요" maxlength="19"/>
                    <div class="modalCalContainer">
                        <div class="calendar-box">
                            <div class="ctr-box clearfix">
                            </div>
                            <div class="calendarMounthView">
                                <table class="cal-table"></table>
                            </div>
                        </div>
                    </div>
                    <ul>
                        <li>
                            <button type="button" class="nextBtn">확인</button>
                        </li>
                        <li>
                            <button type="button" id="closeModal">닫기</button>
                        </li>
                    </ul>
                </div>
            </div>
            </div>
        </form>
    </section>
    <c:if test="${!empty userScheduleList}">
        <c:forEach var="scheduleList" items="${userScheduleList}">
            <c:set var="startDate" value="${scheduleList.startDate.toEpochDay()}"/>
            <c:set var="endDate" value="${scheduleList.endDate.toEpochDay()}"/>
            <section class="schedulePrint">
                <div class="scheduleContentsItem">
                    <i class="xi-ellipsis-v">
                    </i>
                    <div class="menuList">
                        <ul>
                            <li><a href="#">편집</a></li>
                            <li><a href="#" class="deleteSchedule" onclick="deleteLinks('${scheduleList.startDate}')">삭제</a></li>
                        </ul>
                    </div>
                    <div>
                        <ul class="scheduleItemSlider">
                            <li><img src="/images/testimages1.jpg"></li>
                            <li><img src="/images/testimages2.jpg"></li>
                            <li><img src="/images/testimages3.jpg"></li>
                        </ul>
                    </div>
                    <div class="contentTextArea">
                        <ul class="contentsText">
                            <li><span>${scheduleList.title}</span></li>
                            <c:choose>
                                <c:when test="${startDate - dDay == 0}">
                                    <li><span class = "uSchedule">${scheduleList.startDate}</span>
                                        <span>| D - Day</span></li>
                                </c:when>
                                <c:otherwise>
                                    <li><span class="uSchedule">${scheduleList.startDate} </span>
                                        <span>| D - ${startDate - dDay }</span></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                    <div class="daysPrint">
                        <ul class="daysPrintList">
                            <c:set var="day" value="0"/>
                            <c:forEach var="i" begin="${startDate}" end="${endDate}">
                                <c:set var="nextDays" value="${day + 1 }"/>
                                <li>
                                    <button class="nextDayBtn" value="${nextDays}">${nextDays}일 차
                                    </button>
                                </li>
                                <c:set var="day" value="${nextDays}"/>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </section>
        </c:forEach>
    </c:if>
</main>
<script src="/js/schedule.js"></script>
<script defer src="/js/cal.js"></script>
<%@include file="../layout/footer.jsp" %>
