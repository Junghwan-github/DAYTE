<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>

<title>비밀번호 변경</title>

<link rel="stylesheet" href="/css/main/changePassword.css">
</head>

<body>
<%@include file="../layout/header.jsp" %>
<div class="wrapper">
    <main>
        <div class="formBox">
            <div class="changePasswordBox">
                <h1>
                    비밀번호 변경
                </h1>
            </div>
            <form id="changePwdForm">
                <div>
                    <label for="newPwd">새 비밀번호 | </label>
                    <input type="password" id="newPwd" name="newPwd" placeholder="새 비밀번호를 입력해주세요.">
                </div>
                <div>
                    <label for="confirmPwd">비밀번호 확인 | </label>
                    <input type="password" id="confirmPwd" name="password" placeholder="다시 비밀번호를 입력해주세요.">
                </div>
                <div id="errorMsg">
                    <span id="err1"></span>
                </div>

            </form>
            <div>
                <button id="changePwdBtn" value="${userEmail}">변경하기</button>
            </div>
        </div>
    </main>
</div>
<script src="/js/main/changePassword.js"></script>
<%@include file="../layout/footer.jsp" %>