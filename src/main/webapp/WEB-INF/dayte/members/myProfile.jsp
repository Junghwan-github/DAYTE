<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>
<title>내 정보 | 나의 프로필</title>
<link rel="stylesheet" href="/css/main/myProfile.css">
<link rel="stylesheet" href="/css/layout/myProfileSideBar.css">
<style>


</style>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<div class="wrapper">

    <%@include file="./layout/profileSideBar.jsp" %>
    <main>
        <c:set var="nowDate" value="${java.time.LocalDate.now()}"/>
        <div class="myScheduleList">
            <div>
                <span>다가올 일정 | ${mySchedule[0].title}</span>
                <a href="/schedule/scheduleList">일정 보기</a>
            </div>
        <c:if test="${!empty mySchedule[0].scheduleDates}">
            <c:forEach var="scheduleDates" items="${mySchedule[0].scheduleDates}">
                <c:choose>
                    <c:when test="${scheduleDates.scheduleDateId.nowDate eq nowDate}">
                        <div class="day-contents">
                                <ul>
                        <c:forEach var="content" items="${scheduleDates.detailedScheduleList}">
                                    <li>
                                            <img src="${content.adminContents.adminContentsImageList[0].imageURL}">
                                        <span>${content.adminContents.businessName}</span>
                                    </li>
                        </c:forEach>
                                </ul>
                            </div>
                    </c:when>
                    <c:when test="${scheduleDates.scheduleDateId.nowDate eq mySchedule[0].scheduleDates[0].scheduleDateId.nowDate}">
                            <div class="day-contents">
                                <ul>
                        <c:forEach var="content" items="${scheduleDates.detailedScheduleList}">
                                    <li>
                                        <span>${content.adminContents.businessName}</span>
                                        <img src="${content.adminContents.adminContentsImageList[0].imageURL}">
                                    </li>
                        </c:forEach>
                                </ul>
                            </div>
                    </c:when>
                </c:choose>
            </c:forEach>
        </c:if>
        <c:if test="${empty mySchedule[0].scheduleDates[0].detailedScheduleList}">
            <div class="noDayContent">
                <ul>
                    <li>
                        <span>일정이 없습니다.</span>
                    </li>
                </ul>
            </div>
        </c:if>
        </div>
        <div class="profileStyle">
            <form>
                <div class="profileImg">
                    <div class="ImgPreview">
                        <div>
                            <img src="${userInfo.profileImagePath}" class="profile-photo" alt="회원사진">
                        </div>
                        <div class="editIcon">
                            <label for="upload"><img src="/images/whitePen.png" alt="편집"></label>
                            <input type="file" id="upload" accept="image/gif, image/png, image/jpeg"
                                   style="margin-left : 57px;">
                        </div>
                    </div>
                </div>
                <div>
                    <div class="profileId">
                        <input type="text" id="email" value="${userInfo.userEmail}" disabled>
                    </div>
                    <div>
                        <img src="/images/nickName.png">
                        <input type="text" id="nickName" name="nickName" value="${userInfo.nickName}">
                        <button type="button" id="nickNameChk"> 중복체크</button>
                    </div>
                    <div id="nickNameErrs">
                        <span id="err1"></span>
                    </div>
                    <div>
                        <img src="/images/thickPhone.png" alt="휴대전화">
                        <input type="tel" id="phone" name="phone" value="${userInfo.phone}">
                    </div>
                </div>
            </form>
            <div>
                <button type="button" id="editBttn">정보 수정</button>
            </div>
        </div>
    </main>
</div>
    <script defer src="/js/main/editForm.js"></script>


<%@include file="../layout/footer.jsp" %>