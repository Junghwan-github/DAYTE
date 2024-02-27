<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>

<title>내 정보 | 회원 탈퇴</title>

<link rel="stylesheet" href="/css/main/delUserForm.css">
<link rel="stylesheet" href="/css/layout/myProfileSideBar.css">
</head>

<body>
<%@include file="../layout/header.jsp" %>
<div class="wrapper">
    <%@include file="./layout/profileSideBar.jsp" %>
    <main>
        <c:choose>
            <c:when test="${principal.social}">
                <p>${principal.socialName}</p>
                <div class="formBox">
                    <div>
                        <h1>
                            회원 탈퇴
                        </h1>
                    </div>
                    <div class="delInfo socialDelInfo">
                        <p>탈퇴시 안내 사항</p>
                        <div>
                            <ul>
                                <li>회원 탈퇴 후 7일 동안은 회원가입이 불가능합니다.</li>
                                <li>소셜계정을 연동하여 가입한 회원의 경우, 탈퇴시 소셜계정도 자동으로 연동해지 됩니다.</li>
                            </ul>
                        </div>
                        <p>탈퇴시 삭제/유지 되는 정보를 확인하세요.</p>
                        <div>
                            <ul>
                                <li>계정 및 프로필 정보 삭제</li>
                                <li>일정 및 리뷰, 사진 유지</li>
                            </ul>
                        </div>
                        <p>정말 탈퇴하시겠습니까?</p>
                    </div>
                    <div>
                        <button class="socialDelUserBtn">탈퇴하기</button>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="formBox">
                    <div>
                        <h1>
                            회원 탈퇴
                        </h1>
                    </div>
                    <div class="delInfo">
                        <p>탈퇴시 안내 사항</p>
                        <div>
                            <ul>
                                <li>회원 탈퇴 후 7일 동안은 회원가입이 불가능합니다.</li>
                                <li>소셜계정을 연동하여 가입한 회원의 경우, 탈퇴시 소셜계정도 자동으로 연동해지 됩니다.</li>
                            </ul>
                        </div>
                        <p>탈퇴시 삭제/유지 되는 정보를 확인하세요.</p>
                        <div>
                            <ul>
                                <li>계정 및 프로필 정보 삭제</li>
                                <li>일정 및 리뷰, 사진 유지</li>
                            </ul>
                        </div>
                    </div>
                    <form>
                        <div>
                            <input type="text" id="email" value="${principal.getUserEmail()}" hidden>
                            <label for="password">현재 비밀번호 | </label>
                            <input type="password" id="password" name="password" placeholder="현재 비밀번호를 입력해주세요.">
                        </div>
                    </form>
                    <div>
                        <button id="delUserBtn">탈퇴하기</button>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</div>

<script src="/js/main/delUserForm.js"></script>
<%@include file="../layout/footer.jsp" %>