<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@include file="../layout/head.jsp"%>
      <title>회원가입 | 정보 입력</title>
      <link rel="stylesheet" href="/css/main/joinForm.css" />
    </head>
<body>
<%@include file="../layout/header.jsp"%>
    <!-- 메인 -->
    <main>
      <div class="container">
        <form class="joinForm2" autocomplete="off">
          <div class="joinInfo">
            <h1>회원 가입</h1>
            <ul>
              <li><img src="${contextPath}/images/num1.png" alt="1" /></li>
              <li><img src="${contextPath}/images/selected2.png" alt="2" /></li>
            </ul>
          </div>
          <div class="formBorder ">
            <!-- 이메일 아이디 -->
            <div class="formItem formDivine" id="emailId">

              <img src="/images/user.png" alt="이름이미지" />
              <input
                      type="text"
                      id="id"
                      name="id"
                      placeholder="이메일"
                      class="email"
                      autocapitalize="off"
                      autocomplete="off"
              />@
              <input
                      type="text"
                      id="emailDomain"
                      name="emailDomain"
                      class="email"
                      title="이메일 도메인"
                      placeholder="이메일 도메인"
                      maxlength="18"
                      autocomplete="off"
              />
              <select
                      class="select"
                      id="domainSelect"
                      title="도메인 선택"
                      onchange="updateDomain()"
              >
                <option value="">-선택-</option>
                <option value="naver.com">naver.com</option>
                <option value="gmail.com">gmail.com</option>
                <option value="hanmail.net">hanmail.net</option>
                <option value="daum.net">daum.net</option>
                <option value="hotmail.com">hotmail.com</option>
                <option value="korea.com">korea.com</option>
                <option value="nate.com">nate.com</option>
                <option value="yahoo.com">yahoo.com</option>
              </select>
              <button type="button" id="sendEmail" class="EmailBttn">
                인증 발송
              </button>
            </div>
            <div  class="verificationBox" id="verification">
              <div class="validateCk">
                <img src="/images/lockCheck.png" alt="비밀번호이미지" />
                <input
                        type="text"
                        id="emailNum"
                        name="emailNum"
                        placeholder="인증번호"
                        class="email"
                        autocomplete="off"
                />
              </div>
              <div class="timer">
                <span></span>
                <button type="button" class="EmailBttn" id="numCheck">인증 확인</button>
              </div>
            </div>

          </div>


          <div class="errorMsg">
            <div class="idFailMsg hide">
              아이디는 영어 또는 숫자만 입력 가능합니다.
            </div>
            <div class="idFailMsg2 hide">아이디는 6~18글자이어야 합니다.</div>
            <div class="idFailMsg3 hide">도메인을 올바르게 입력해주세요.</div>
          </div>

          <!-- 비밀번호,비밀번호 입력 -->
          <div class="formBorder">
            <div class="formItem formDivine">
              <img src="/images/lock.png" alt="비밀번호이미지" />
              <input
                      type="password"
                      id="password1"
                      name="password1"
                      placeholder="비밀번호"
                      class="joinInput"
                      autocomplete="new-password"
              />
            </div>
            <div class="formItem">
              <img src="/images/lockCheck.png" alt="비밀번호이미지" />
              <input
                      type="password"
                      id="password2"
                      name="password2"
                      placeholder="비밀번호 확인"
                      class="joinInput"
                      autocomplete="new-password"
              />
            </div>
          </div>
          <div class="errorMsg">
            <div class="pwdFailMsg hide">
              비밀번호는 8~20글자, 영문, 숫자, 특수문자(@, $, !, %, *, #, ?,
              &)를 사용하여야 합니다.
            </div>
            <div class="pwdFailMsg2 hide">비밀번호가 일치하지 않습니다.</div>
          </div>

          <!-- 이름,닉네임-->
          <div class="formBorder">
            <div class="formItem formDivine">
              <img src="/images/user.png" alt="이름이미지" />
              <input
                      type="text"
                      id="userName"
                      name="userName"
                      placeholder="이름"
                      class="joinInput"
                      autocomplete="off"
              />
            </div>
            <div class="formItem">
              <img src="/images/nickName.png" alt="닉네임이미지" />
              <input
                      type="text"
                      id="nickName"
                      name="nickName"
                      placeholder="닉네임"
                      class="joinInput"
                      autocomplete="off"
              />
            </div>
          </div>
          <div class="errorMsg">
            <div class="uNameFailMsg hide">
              이름은 한글, 영문 대/소문자를 사용해 주세요. ( 특수기호, 공백
              사용 불가 )
            </div>
            <div class="nickNameFailMsg hide">
              닉네임은 2 ~ 10글자, 한글, 숫자, 영문 대/소문자를 사용해
              주세요.(특수기호,공백 사용 불가)
            </div>
          </div>

          <!-- 휴대전화번호, 생년월일 -->
          <div class="formBorder">
            <div class="formItem formDivine">
              <img src="/images/thickPhone.png" alt="휴대전화이미지" />
              <input
                      type="tel"
                      id="phone"
                      name="phone"
                      class="joinInput"
                      placeholder='휴대전화번호 ( "-"d 없이 입력 )'
                      autocomplete="off"
              />
            </div>
            <div class="formItem">
              <img src="/images/birthDate.png" alt="달력이미지" />
              <input
                      type="text"
                      id="birthDate"
                      name="birthDate"
                      placeholder="생년월일 8자리"
                      class="joinInput"
                      autocomplete="off"
              />
            </div>
          </div>
          <div class="errorMsg">
            <div class="phoneFailMsg hide">
              휴대전화번호는 10~11자의 숫자만 사용하여야 합니다. (예 :
              01012345678)
            </div>
            <div class="birthFailMsg hide">
              생년월일은 8자의 숫자만 사용하여야 합니다. (예 : 19990101)
            </div>
          </div>
          <!-- 성별 -->
          <div class="formBorder formItem">
            <select id="gender" name="gender">
              <%-- value가 gender로 뜨면 선택하게 끔--%>
              <option value="gender">성별</option>
              <option value="male">남성</option>
              <option value="female">여성</option>
              <option value="other">그 외</option>
            </select>
          </div>

          <div class="submitBttn">
            <button type="button" id="signBttn">회원 가입</button>
          </div>
        </form>


      </div>
    </main>
    <script src="/js/main/verification.js"></script>
    <script src="/js/main/joinForm.js"></script>
    <script src="/js/main/insertUser.js"></script>

<%@include file="../layout/footer.jsp"%>