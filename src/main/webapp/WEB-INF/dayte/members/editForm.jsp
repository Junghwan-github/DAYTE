<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>
<title>내 정보</title>
<link rel="stylesheet" href="/css/main/joinForm.css"/>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<script defer src="/js/main/editForm.js"></script>
<!-- 메인 -->
<main>
    <div class="container">
        <form class="joinForm2" enctype="multipart/form-data">
            <div class="joinInfo">
                <h1>내 정보</h1>
            </div>
            <div class="formBorder ">
                <!-- 이메일 아이디 -->
                <div class="formItem formDivine" id="emailId">
                    <img src="/images/user.png" alt="이름이미지"/>
                    <span id="email">${userInfo.userEmail}</span>
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
                    <span id="userName">${userInfo.userName}</span>
                </div>
                <div class="formItem">
                    <img src="/images/nickName.png" alt="닉네임이미지"/>
                    <input
                            type="text"
                            id="nickName"
                            name="nickName"
                            placeholder="${userInfo.nickName}"
                            class="joinInput"
                            value=""
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
                    <span id="phone">${userInfo.phone}</span>
                </div>
                <div class="formItem">
                    <img src="/images/birthDate.png" alt="달력이미지"/>
                    <span id="birthDate">${userInfo.birthDate}</span>
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
            <c:if test="${!empty userInfo.gender}">
            <div class="formBorder formItem">
                <span id="gender">${userInfo.gender}</span>
            </div>
            </c:if>

            <div class="profileImage">
                <p>
                    <img src= "/images/default_icon_profile.png" class= "profile-photo" width= "150" height= "150">
                    <br>
                    <br>
                    <input type= "file" id="upload" accept= "image/gif, image/png, image/jpeg" style= "margin-left : 57px;">
                </p>
            </div>

            <div class="submitBttn">
                <button type="button" id="editBttn">정보 수정</button>
            </div>

        </form>


    </div>
</main>
<%@include file="../layout/footer.jsp" %>