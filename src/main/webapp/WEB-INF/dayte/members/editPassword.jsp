<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>

<title>내 정보 | 비밀번호 변경</title>

<style>
    .wrapper {
        width: 1180px;
        margin: 0 auto;
    }

    h1 {
        font-size: 2.2rem;
    }

    /* ================================== 사이드메뉴 */
    .sideBar {
        float: left;
        height: 100%;
        width: 20%;
    }

    .sideBar > ul {
        width: 100%;
        background-color: #fff;
        box-shadow: 0 1px 2px 0 rgba(51, 51, 51, 0.5);

        border-radius: 10px;
        list-style: none;
        text-align: center;


    }

    .sideBar > ul > li {
        box-sizing: border-box;
        width: 100%;
        height: 50px;

        font-size: 1.8rem;
        color: #111;
        font-weight: 500;

        border-bottom: 1px solid rgb(199, 207, 203);
    }

    .sideBar > ul > li:first-child {
        padding-top: 15px;
        text-align: center;
        font-weight: 600;;
    }

    .sideBar > ul > li:last-child {
        border: 1px transparent;
        margin: 0;
    }

    .sideBar > ul > li > a {
        box-sizing: border-box;
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 100%;
        width: 100%;
        padding: 0 15px;
        /*padding: 10px 15px;*/

        font-size: 1.7rem;
        text-decoration: none;
        color: #111;
        font-weight: 500;

    }

    .sideBar > ul > li > a:hover {
        color: #333;
        font-weight: 600;
        background-color: #f1f0f0;
        transition: 0.2s;
    }

    .sideBar > ul > li > a > img {
        width: 16px;
        height: 16px;
    }

    /* ============================================ 메인페이지 */
    main {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

    }
    main > div:nth-child(2){

    }

    .formBox {
        width: 90%;
        margin-top:10px;
        padding: 20px;
        background-color: pink;

        box-shadow: 0 1px 2px 0 rgba(51, 51, 51, 0.5);
        background-color: #fff;
        border-radius: 10px;

        display: flex;
        flex-direction: column;
    }

    /* ================================ 버튼 */
    .formBox button {
        border: transparent 1px solid;
        border-radius: 5px;
        padding: 5px;
        background-color: #333;
        color: #fff;
    }

    .formBox button:hover {
        background-color: #555;
        transition: 0.2s;
    }

    .formBox > div > button {
        padding: 10px 40px;
        font-size: 1.6rem;
    }

    .formBox > div {
        margin: 0 20px 20px 20px;
        font-size: 1.8rem;
        display: flex;
        justify-content: center;

    }
    .formBox > div:first-child {
        margin:0;
    }

    /* ============================= 폼 */
    .formBox > form {
        margin-top: 30px;
        display: flex;
        flex-direction: column;
        flex-wrap: nowrap;
        align-items: center;
        justify-content: center;
    }

    .formBox > form > div {
        width:50%;
        margin-top: 10px;
        display: flex;
        flex-direction: row;
        justify-content: flex-end;

        height: 40px;
    }

    .formBox > form > div > label {
        display: flex;
        align-items: center;
        height: 100%;
        font-size: 1.4rem;
        margin-right: 10px;

    }
    .formBox > form > div > input {
        box-sizing: border-box;
        width:50%;
        padding-left: 10px;
        border: 1px solid #333;
    }

</style>
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