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
<script>

    // // 사용자의 이메일을 매개변수로 넘겨 User 테이블 조회
    // $("#updatePwdBtn").on('click', function () {
    //     console.log(11111111111111);
    //     let userEmail = $("#userEmail").val()
    //     fetch("/members/findPwd/" + userEmail, {
    //         method : "POST",
    //         headers: {
    //             "Content-Type": "application/json; charset=utf-8",
    //         }
    //     }).then(res => {
    //         return res.json();
    //     }).then(data => {
    //         if (data["status"] == 200) {
    //             alert("인증번호가 발송되었습니다.")
    //             sendEmail(userEmail);
    //         } else if (data["status"] == 400){
    //             alert(data["data"])
    //         }
    //     }).catch(err => {
    //         alert(err)
    //     })
    // });
    //
    // // 사용자의 이메일 주소로 이메일 전송
    // function sendEmail(userEmail) {
    //     let verificationDTO = {
    //         address: userEmail
    //     }
    //     fetch("/members/sendEmail", {
    //         method : "POST",
    //         headers: {
    //             "Content-Type": "application/json; charset=utf-8",
    //         },
    //         body: JSON.stringify(verificationDTO)
    //     }).then(res => {
    //         return res.json();
    //     }).then(data => {
    //     }).catch(err => {
    //         alert(err)
    //     })
    // }
    //
    // // 뷰 단에서 사용자가 입력한 값과 비교
    // $("#checkNumberBtn").on('click', function () {
    //     fetch("/members/checkEmail", {
    //         method : "POST",
    //         headers: {
    //             "Content-Type": "application/json; charset=utf-8",
    //         },
    //         body: JSON.stringify($("#checkNumber").val())
    //     }).then(res => {
    //         return res.json();
    //     }).then(data => {
    //         if (data == 200){
    //             alert("인증되었습니다.")
    //             location = "/members/changePwd";
    //         } else if (data == 400)
    //             alert("인증번호가 일치하지 않습니다.")
    //     }).catch(err => {
    //         alert(err)
    //     })
    // });

</script>
<%@include file="../layout/footer.jsp" %>