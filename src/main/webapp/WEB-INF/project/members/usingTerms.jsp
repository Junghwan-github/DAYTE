<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>

    <link rel="stylesheet" href="/css/usingTerms.css"/>
<title>회원가입 | 개인정보 동의</title>
</head>
<body>
<%@include file="../layout/header.jsp"%>
<script src="/js/header.js"></script>
<main>

    <div class="container">
        <form action="/members/joinForm" method="get" class="joinForm">
            <div class="termInfo">
                <h1>이용 약관 안내</h1>
                <ul>
                    <li><img src="/images/selected1.png" alt="1"/></li>
                    <li><img src="/images/num2.png" alt="2"/></li>

                </ul>

                <div class="privacyInfo">
                    <div id="checkTerm1">
                        <iframe src="/txt/humaninfoverification.txt" frameborder="1"></iframe>
                        <br>
                        <div>
                            <input
                                    type="radio"
                                    id="disagreeCheckbox"
                                    value="N"
                                    name="checkTerm1"
                            />
                            <label for="disagreeCheckbox">동의하지 않습니다.</label>
                        </div>
                        <div>
                            <input type="radio" id="agreeCheckbox" value="Y" name="checkTerm1"/>
                            <label for="agreeCheckbox">동의합니다.</label>
                        </div>

                    </div>
                </div>
                <div class="termCheckBttn">
                    <button type="button" value="약관 동의">회원 정보 등록</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        /* 이용약관 동의 시 -> 회원정보 기입페이지 연결 */
        $(document).ready(function () {
            $("button[type='button']").click(function () {
                if ($("#agreeCheckbox").is(":checked")) {
                    $("button[type='button']").prop("type", "submit");
                } else {
                    alert("약관에 동의 하지 않으셨습니다.");
                }
            })
        });
    </script>
</main>
<%@include file="../layout/footer.jsp"%>