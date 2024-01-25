<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/schedule.css">
<link rel="stylesheet" href="/css/scheduleSlider.css">
<link rel="stylesheet" href="/css/subnav.css">
<link rel="stylesheet" href="/css/scheduleModal.css">
<title>일정 관리</title>
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
                    <input type="text" id="scheduleSubjectTitle" placeholder="일정 제목을 입력해주세요" maxlength="19" />
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
    <c:if test="${!empty userScheduleList}">
        <c:forEach var="scheduleList" items="${userScheduleList}">
            <c:set var="startDate" value="${scheduleList.startDate.toEpochDay()}"/>
            <c:set var="endDate" value="${scheduleList.endDate.toEpochDay()}"/>

            <section id="schedulePrint">
                <div class="scheduleContentsItem">
                    <i class="xi-ellipsis-v">
                    </i>
                    <div class="menuList">
                        <ul>
                            <li><a href="#">편집</a></li>
                            <li><a href="#" class="deleteSchedule" onclick="deleteLinks('${scheduleList.startDate}')">삭제</a></li>
                        </ul>
                    </div>
                    <div class="scheduleItemSliderArea">
                        <ul class="scheduleItemSlider">
                            <li><img src="/images/testimages1.jpg"></li>
                            <li><img src="/images/testimages2.jpg"></li>
                            <li><img src="/images/testimages3.jpg"></li>
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
                            <c:forEach var="i" begin="${startDate}" end="${endDate}">
                                <c:set var="nextDays" value="${day + 1 }"/>
                                <li>
                                    <button class="nextDayBtn" value="${scheduleList.uuid}" data-next-days="${nextDays}">${nextDays}일 차
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
                    <ul class="contentModalSlider">
                    </ul>
                </div>
            </div>
            <button type="button" id="divUpDownButton">펼치기 / 접기</button>
            <div class="bottomModalWraper">
                <div class="leftModalLayout">
                    <div><input type="text" id="leftModalSearchBar" placeholder="검색어를 입력하세요"><button type="button" id="leftModalSearchBarBtn">검색</button></div>
                    <div>
                        <h2>구/군</h2>
                        <ul>
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
                        <ul>
                            <li>#숙박</li>
                            <li>#맛집</li>
                            <li>#카페</li>
                            <li>#공연</li>
                            <li>#전시</li>
                            <li>#행사</li>
                            <li>#이벤트</li>
                            <li>#관광</li>
                            <li>#명소</li>
                            <li>#가족</li>
                            <li>#연인</li>
                            <li>#봄</li>
                            <li>#여름</li>
                            <li>#가을</li>
                            <li>#겨울</li>
                        </ul>
                    </div>
                </div>
                <div class="centerModalLayout">
                    <ul class="contentListViewer">
                        <c:forEach var="content" items="${contentsList}">
                        <li>
                            <span class="contentListItemPoint-x">${content.positionX}</span><span class="contentListItemPoint-y">${content.positionY}</span>
                            <div class="contentListItems">
                                <div class="contentListItemsImages">
                                    <img src="../images/testimages1.jpg">
                                </div>
                                <ul class="contentListItemText">
                                    <li>
                                        <h2>${content.businessName}</h2>
                                        <h2>${content.category}</h2>
                                    </li>
                                    <li>
                                        <span>대구 ${content.gu} ${content.ro}</span>
                                    </li>
                                    <li>
                                        <p>영업시간 : 09:00</p>
                                        <p>기간 : 없음</p>
                                        <p>문의 : 0507-2221-1321</p>
                                    </li>
                                    <li>
                                        <span>★ 4.5</span>
                                    </li>
                                </ul>
                                <div class="contentListItemButton">
                                    <ul>
                                        <li>
                                            <button class="contentListItemdetailViewBtn">상세보기</button>
                                        </li>
                                        <li>
                                            <button class="contentListItemAddBtn" value="${content.id}">추가하기</button>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="rightModalLayout" id="rightModalLayout">
                <button class="saveBtn" onclick="asd()">저장</button>
                </div>
            </div>
        </div>
    </div>
</main>
<script>
    let contentsList = '<c:out value="${contentsList}"/>';
</script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script src="/js/schedule.js"></script>
<script src="/js/cal.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/contentsList.js"></script>
<%@include file="../layout/footer.jsp" %>
