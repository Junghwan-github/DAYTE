<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>

<title>비밀번호 찾기</title>

<link rel="stylesheet" href="/css/main/findPassword.css">
</head>

<body>
<%@include file="../layout/header.jsp" %>
<main>

    <div class="formBox">
        <div class="checkedUserEmailBox">
            <h1>비밀번호 찾기</h1>
        </div>
        <form id="checkPwdForm" method="post">
            <ul>
                <li>
                    <label for="userEmail">아이디(이메일) | </label>
                </li>
                <li>
                    <input type="email" id="userEmail" name="userEmail" placeholder="현재 사용중인 아이디(이메일)을 입력해주세요."
                           required>
                </li>
                <li>
                    <button id="updatePwdBtn" type="button">인증번호 발송</button>
                </li>
            </ul>
        </form>
            <form id="checkNumberForm">
                <ul>
                    <li>
                        <label for="userEmail">인증번호 확인 | </label>
                    </li>
                    <li>
                        <input type="text" id="checkNumber" name="checkNumber" placeholder="인증번호" required>
                    </li>
                    <li>
                        <button id="checkNumberBtn">인증 확인</button>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</main>
<script src="/js/main/changePassword.js"></script>
<%@include file="../layout/footer.jsp" %>