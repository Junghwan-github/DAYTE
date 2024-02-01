<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>
<title>DAYTE | 정보 수정</title>
<link rel="stylesheet" href="/css/main/joinForm.css"/>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<script src="/js/main/header.js"></script>
<!-- 메인 -->
<main>
    <div class="container">
        <form class="joinForm2">
            <div class="joinInfo">
                <h1>내 정보</h1>
            </div>
            <div class="formBorder ">
                <!-- 이메일 아이디 -->
                <div class="formItem formDivine" id="emailId">
                    <img src="/images/user.png" alt="이름이미지"/>
                    <span class="email">${userInfo.userEmail}</span>
                </div>
            </div>

            <!-- 비밀번호,비밀번호 입력 -->
            <div class="formBorder">
                <div class="formItem formDivine">
                    <img src="/images/lock.png" alt="비밀번호이미지"/>
                    <input
                            type="password"
                            id="password1"
                            name="password1"
                            placeholder="비밀번호"
                            class="joinInput"
                            value
                    />
                </div>
                <div class="formItem">
                    <img src="/images/lockCheck.png" alt="비밀번호이미지"/>
                    <input
                            type="password"
                            id="password2"
                            name="password2"
                            placeholder="비밀번호 확인"
                            class="joinInput"
                            value
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
                    <img src="/images/user.png" alt="이름이미지"/>
                    <input
                            type="text"
                            id="userName"
                            name="userName"
                            placeholder="이름"
                            class="joinInput"
                            value
                    />
                </div>
                <div class="formItem">
                    <img src="/images/nickName.png" alt="닉네임이미지"/>
                    <input
                            type="text"
                            id="nickName"
                            name="nickName"
                            placeholder="닉네임"
                            class="joinInput"
                            value
                    />
                    <button type="button" class="EmailBttn">중복확인</button>
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
                    <img src="/images/thickPhone.png" alt="휴대전화이미지"/>
                    <input
                            type="tel"
                            id="phone"
                            name="phone"
                            class="joinInput"
                            placeholder='휴대전화번호 ( "-"d 없이 입력 )'
                    />
                </div>
                <div class="formItem">
                    <img src="/images/birthDate.png" alt="달력이미지"/>
                    <input
                            type="text"
                            id="birthDate"
                            name="birthDate"
                            placeholder="생년월일 8자리"
                            class="joinInput"
                            value
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
<%@include file="../layout/footer.jsp" %>