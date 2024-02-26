<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.dayte.schedule.domain.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/schedule/schedule.css">
<link rel="stylesheet" href="/css/schedule/scheduleSlider.css">
<link rel="stylesheet" href="/css/schedule/scheduleModal.css">
<title>DAYTE | 일정 관리</title>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>
<main>
    <h1 id="pageTitleName">일정 관리</h1>
    <section id="modalArea">
        <div class="buttonArea">
            <button type="button" id="createSchedule" onclick="openModal(this,'cal')">일정 등록</button>
        </div>
        <form id="myForm">
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <input hidden="hidden"/>
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
                            <button type="button" id="closeModalBtn" onclick="closeModal(this,'cal')">닫기</button>
                        </li>
                    </ul>
                </div>
            </div>
            </div>
        </form>
    </section>
    <section id="schedulePrint">
        <c:if test="${!empty userScheduleList}">
            <c:forEach var="scheduleList" items="${userScheduleList}">
                <c:set var="startDate" value="${scheduleList.startDate.toEpochDay()}"/>
                <c:set var="endDate" value="${scheduleList.endDate.toEpochDay()}"/>
                <c:if test="${endDate - dDay >= 0}">
                    <div class="scheduleContentsItem">
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
                                        src="${scheduleList.scheduleDates[0].detailedScheduleList[1].adminContents.adminContentsImageList[0].imageURL}">
                                </li>
                                <li><img
                                        src="${scheduleList.scheduleDates[0].detailedScheduleList[2].adminContents.adminContentsImageList[0].imageURL}">
                                </li>
                            </ul>
                        </div>
                        <div class="contentTextArea">
                            <ul class="contentsText">
                                <li><span class="contentsTextTitle">${scheduleList.title}</span></li>
                                <c:choose>
                                    <c:when test="${startDate - dDay == 0}">
                                        <li><span class="contentsTextDate">${scheduleList.startDate}</span>
                                            <span>| D - Day</span></li>
                                    </c:when>
                                    <c:when test="${startDate - dDay < 0}">
                                        <li><span class="contentsTextDate">${scheduleList.startDate}</span>
                                            <span>| 진행중인 일정</span></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><span class="contentsTextDate">${scheduleList.startDate} </span>
                                            <span>| D - ${startDate - dDay }</span></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                        <div class="daysPrint">
                            <ul class="daysPrintList">
                                <c:set var="day" value="0"/>
                                <c:forEach begin="${startDate}" end="${endDate}">
                                    <c:set var="nextDays" value="${day + 1 }"/>
                                    <li>
                                        <button class="nextDayBtn"
                                                <c:if test="${!empty scheduleList.scheduleDates[day].detailedScheduleList}">disabled</c:if>
                                                value="${scheduleList.uuid}"
                                                data-now-days="${scheduleList.scheduleDates[day].scheduleDateId.nowDate}">${nextDays}일차
                                        </button>
                                    </li>
                                    <c:set var="day" value="${nextDays}"/>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </section>

    <div class="daysListAddModal">
        <span class="tableUuid"></span>
        <span class="daysValue"></span>
        <div class="daysListModalViewArea">
            <div class="daysContentsModalCloseBtn">
                <span class="material-symbols-outlined modalclosebutton" onclick="closeModal(this,'close')">
            close
                </span>
            </div>
            <div class="contentListModalArea">
                <div class="contentListModalItem">
                    <h2>일정 리스트</h2>
                    <ul class="contentListModalBtnWrap">
                        <li>
                            <button class="scheduleTotalListModifyBtn">수정</button>
                        </li>
                        <li>
                            <button class="scheduleTotalListCancelBtn">확인</button>
                        </li>
                        <li>
                            <button class="scheduleTotalListMapBtn">전체 지도 보기</button>
                        </li>
                    </ul>
                    <ul class="contentModalSlider">
                    </ul>

                </div>
            </div>
            <button type="button" id="divUpDownButton">펼치기 / 접기</button>
            <div class="bottomModalWraper">
                <div class="leftModalLayout">
                    <div><input type="text" id="leftModalSearchBar" placeholder="검색어를 입력하세요">
                        <button type="button" id="leftModalSearchBarBtn"
                                onclick="searchContents(leftModalSearchBar.value)">검색
                        </button>
                    </div>
                    <div>
                        <h2>구/군</h2>
                        <ul id="guList">
                            <li>중구</li>
                            <li>수성구</li>
                            <li>북구</li>
                            <li>동구</li>
                            <li>남구</li>
                            <li>서구</li>
                            <li>달서구</li>
                            <li>달성군</li>
                            <li>군위군</li>
                        </ul>
                    </div>
                    <div>
                        <h2>키워드</h2>
                        <ul id="keywordList">
                            <c:forEach var="contentKeyword" items="${contentsListKeyword}">
                                <c:if test="${!empty contentKeyword}">
                                    <li>${contentKeyword}</li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="centerModalLayout">
                    <div id="main-max-top"></div>
                    <ul class="contentListViewer">
                        <c:forEach var="content" items="${contentsList}">
                            <li>
                                <div class="contentListItems">
                                    <span class="contentListItemPoint-x">${content.positionX}</span>
                                    <span class="contentListItemPoint-y">${content.positionY}</span>
                                    <div class="contentListItemsImages">
                                        <img src="${content.adminContentsImageList[0].imageURL}">
                                    </div>
                                    <ul class="contentListItemText">
                                        <li>
                                            <div class="contents-title-wrapper">
                                                <h2>${content.businessName}</h2>
                                                <div class="contents-category-keyword">
                                                    <span>${content.category}</span>
                                                    <span>${content.keyword}</span>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <span>${content.detailedAddress}</span>
                                        </li>
                                        <li>
                                            <p>영업시간 : ${content.opening} ~ ${content.closed}</p>
                                            <p>문의 : ${content.contactInfo}</p>
                                        </li>
                                        <li class="star-point-find">
                                            <c:set var="hasMatch" value="false"/>
                                            <c:forEach var="star" items="${starList}">
                                                <c:if test="${star.uuid eq contents.uuid}">
                                                    <span class="star">★${star.starAVG}</span>
                                                    <c:set var="hasMatch" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not hasMatch}">
                                                <span class="star">★0.0</span>
                                            </c:if>
                                        </li>
                                    </ul>
                                    <div class="contentListItemButton">
                                        <ul>
                                            <li>
                                                <button class="contentListItemdetailViewBtn" value="${content.uuid}">
                                                    상세보기
                                                </button>
                                            </li>
                                            <li>
                                                <button class="contentListItemAddBtn" value="${content.uuid}">추가하기
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <a href="#main-max-top" id="max-top-bottom"><i class="xi-arrow-up"></i></a>
                </div>
                <div class="rightModalLayout" id="rightModalLayout">
                    <button class="scheduleTotalSaveBtn" onclick="scheduleTotalSaveBtn()">저장</button>
                    <button class="scheduleTotalModifyBtn" onclick="scheduleTotalModifyBtn()">저장</button>
                </div>
            </div>
        </div>
    </div>

    <div class="detailedScheduleAddModal">
        <div class="detailedScheduleViewArea">
            <div class="detailedScheduleCloseBtn">
                <span class="material-symbols-outlined detail-schedule-modal" onclick="closeModal(this,'closes')">
            close
                </span>
            </div>
            <c:forEach var="scheduleList" items="${userScheduleList}">
                <div class="schedule-tb-list ${scheduleList.uuid}">
                    <div class="detail-contentListModalArea">
                        <div class="detail-contentListModalItem">
                            <h2>${scheduleList.title}</h2>
                        </div>
                    </div>
                    <div class="detail-daysPrint">

                        <c:set var="day" value="0"/>
                        <c:forEach begin="${scheduleList.startDate.toEpochDay()}"
                                   end="${scheduleList.endDate.toEpochDay()}">
                            <c:set var="nextDays" value="${day + 1 }"/>

                            <div class="detailedScheduleDiv"
                                 data-now-days="${scheduleList.scheduleDates[day].scheduleDateId.nowDate}">
                                <div>
                                    <button class="detail-daysPrint-button">수정</button>
                                </div>
                                <h2>${nextDays}일
                                    차</h2>
                                <ul class="detailedScheduleListUl">
                                    <c:forEach var="detailedSchedule"
                                               items="${scheduleList.scheduleDates[day].detailedScheduleList}">
                                        <li>
                                            <div class="detail-schedule-li-images">
                                                <img src="${detailedSchedule.adminContents.adminContentsImageList[0].imageURL}">
                                            </div>
                                            <span>${detailedSchedule.adminContents.businessName}</span>
                                            <input class="detailedScheduleListId" hidden
                                                   value="${detailedSchedule.adminContents.uuid}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <c:set var="day" value="${nextDays}"/>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script src="/js/schedule/cal.js"></script>
<script src="/js/schedule/schedule.js"></script>
<script src="/js/schedule/calendar.js"></script>
<script src="/js/schedule/contentsList.js"></script>
<%@include file="../layout/footer.jsp" %>
