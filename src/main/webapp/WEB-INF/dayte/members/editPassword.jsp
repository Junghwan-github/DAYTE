<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>

<title>내 정보 | 비밀번호 변경</title>

<link rel="stylesheet" href="/css/main/editPassword.css">
</head>

<body>
<%@include file="../layout/header.jsp" %>
<div class="wrapper">
    <aside class="sideBar">
        <ul>
            <li> 내 정보</li>
            <li><a href="/members/editForm">나의 프로필<img src="/images/next.png"></a></li>
            <li><a href="/members/editPsForm">비밀번호 변경<img src="/images/next.png"></a></li>
            <li><a href="/members/delete">회원탈퇴<img src="/images/next.png"></a></li>
            <li></li>
        </ul>
    </aside>
    <main>

        <div class="formBox">
            <div >
                <h1>
                    비밀번호 변경
                </h1>
            </div>
            <form>
                <div>
                    <label for="password1">현재 비밀번호 | </label>
                    <input type="password" id="password1" placeholder="현재 비밀번호를 입력해주세요.">
                </div>
                <div>
                    <label for="password2">새 비밀번호 | </label>
                    <input type="password" id="password2" placeholder="새 비밀번호를 입력해주세요.">
                </div>
                <div>
                    <label for="password3">비밀번호 확인 | </label>
                    <input type="password" id="password3" placeholder="다시 비밀번호를 입력해주세요.">
                </div>
                <div id="errorMsg">
                    <p id="err1"></p>
                </div>

            </form>
            <div>
                <button>변경하기</button>
            </div>
        </div>
    </main>
</div>
<script>
    $(document).ready(function () {

        // 비밀번호 유효성 검사 메서드
        let passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*?_]).{8,20}$/;
        $("#password2").on("keyup", function () {
            $("#err1").text("");
        })
        $("#password2").on("blur", function () {

            if (!passwordRegex.test($("#password2").val())) {
                $("#err1").css("color", "red").text("비밀번호는 8자 이상 20글자 이하이며, 한글,숫자,영문,특수기호를 포함해야 합니다.");
                $("#password2").css("border-color", "red");
                // $("#password2").focus();
                return;
            } else {
                $("#password2").css("border-color", "#333");
                $("#err1").text("");
            }
        })
        $("#password3").on("keyup", function () {
            $("#err1").text("");

        })
        $("#password3").on("blur", function () {
            if (passwordRegex.test($("#password2").val())) {
                if ($("#password2").val() != $("#password3").val()) {
                    $("#err1").css("color", "red").text("비밀번호를 다시 확인해주세요");
                    $("#password3").css("border-color", "red");
                    return;
                }
                if ($("#password2").val() == $("#password3").val()) {
                    $("#password3").css("border-color", "#333");
                    $("#err1").text("");
                    return;
                }
            }else {
                $("#err1").css("color", "red").text("비밀번호는 8자 이상 20글자 이하이며, 한글,숫자,영문,특수기호를 포함해야 합니다.");
                $("#password2").css("border-color", "red");
                $("#password2").focus();
                return;
            }
        })

    })
</script>
<%@include file="../layout/footer.jsp"%>