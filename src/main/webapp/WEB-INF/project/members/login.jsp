<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>
<%--여기 각자 쓸 css--%>

<link rel="stylesheet" href="/css/login.css">
<title>Project T | 로그인</title>
</head>

<body>
<%@include file="../layout/header.jsp"%>
<script src="/js/header.js"></script>
  <section class="loginForm">
    <form action="/members/securitylogin" method="post">
      <h1>Project T 로그인</h1>
      <ul>
        <li>
          <label for="userId"></label>
          <input type="text" name="username" id="userId" placeholder="아이디를 입력해주세요">
        </li>
        <li>
          <label for="userPwd"></label>
          <input type="password" name="password" id="userPwd" placeholder="비밀번호를 입력해주세요">
        </li>
        <li>
          <input type="submit" value="로그인">
        </li>
      </ul>
    </form>
  </section>
  <section class="joinForm">
    <div class="apiWrap">
      <h2>간편 회원가입</h2>
      <p>10초 만에 간편하게 가입하세요!</p>
      <ul class="apiJoin">
        <li>
          <img src="../images/go_logo.png" />
          <span>Google 계정으로 회원가입</span>
        </li>
        <li>
          <img src="../images/ap_logo.png" />
          <span>Apple 계정으로 회원가입</span>
        </li>
        <li>
          <img class="kaka" src="../images/ka_logo.png" />
          <span>KAKAO 계정으로 회원가입</span>
        </li>
      </ul>
      <ul class="subNavi">
        <li><a href="/members/join">회원가입</a></li>
        <li><a href="#">비밀번호를 잊으셨나요?</a></li>
      </ul>
    </div>
  </section>
<%@include file="../layout/footer.jsp"%>
</body>

</html>