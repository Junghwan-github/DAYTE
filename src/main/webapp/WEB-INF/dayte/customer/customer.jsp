<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>
<link rel="stylesheet" href="/css/main/customer.css">


<title>DAYTE | 고객센터</title>

</head>

<body>
<%@include file="../layout/header.jsp"%>
<main>
    <div id="customer-container">
        <h1>고객센터</h1>
        <span>궁금한점이 있으신가요?</span>
        <div id="customer-nav">
            <ul>
                <li><i class="xi-help-o"></i>자주 묻는 질문</li>
                <li><i class="xi-mail-o"></i>1:1 이메일 문의</li>
                <li><i class="xi-kakaotalk"></i>카카오 문의</li>
            </ul>
        </div>
        <div id="customer-best-question" class="customer-best-question">
            <ul>
                <li>
                    <label class="question" for="question1">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트1
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question1">
                    <div>
                        <p>테스트1
                            테스트1
                            테스트1
                            테스트1
                            테스트1
                            테스트1
                            테스트1
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question2">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트2
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question2">
                    <div>
                        <p>테스트2
                            테스트2
                            테스트2
                            테스트2
                            테스트2
                            테스트2
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question3">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트3
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question3">
                    <div>
                        <p>테스트3
                            테스트3
                            테스트3
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question4">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트4
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question4">
                    <div>
                        <p>테스트4
                            테스트4
                            테스트4
                            테스트4
                            테스트4
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question5">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트5
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question5">
                    <div>
                        <p>테스트5
                            테스트5
                            테스트5
                            테스트5
                            테스트5
                            테스트5
                            테스트5
                            테스트5
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question6">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트6
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question6">
                    <div>
                        <p>테스트6
                            테스트6
                            테스트6
                            테스트6
                            테스트6
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question7">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트7
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question7">
                    <div>
                        <p>테스트7
                            테스트7
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question8">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트8
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question8">
                    <div>
                        <p>테스트8
                            테스트8
                            테스트8
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question9">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트9
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question9">
                    <div>
                        <p>테스트9
                            테스트9
                            테스트9
                            테스트9
                            테스트9
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question10">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            테스트10
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question10">
                    <div>
                        <p>테스트10
                            테스트10
                            테스트10
                        </p>
                    </div>
                </li>
            </ul>
        </div>
<%--        <div id="customer-n-inquiry" class="customer-n-inquiry">--%>
<%--            <form action="/question" method="POST">--%>
<%--                <div class="title">--%>
<%--                    <span>제목</span>--%>
<%--                    <input type="text" id="title">--%>
<%--                </div>--%>
<%--                <div class="content">--%>
<%--                    <span>내용</span>--%>
<%--                    <textarea id="content"></textarea>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--            <button type="button" id="sendEmail">문의하기</button>--%>
<%--        </div>--%>
<%--        <div id="customer-kakao-chanel" class="customer-kakao-chanel">--%>

<%--        </div>--%>
<%--    </div>--%>


<%--    <a href="http://pf.kakao.com/_MXjSG/chat">카카오톡 문의</a>--%>
</main>



<script src="/js/main/customer.js"></script>


<%@include file="../layout/footer.jsp"%>