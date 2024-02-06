<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="../layout/head.jsp" %>
<title>내 정보 | 나의 프로필</title>
<link rel="stylesheet" href="/css/main/myProfile.css">
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
        <div> 일정
            <p>비워둠</p>
            <p>비워둠</p>
            <p>비워둠</p>
            <p>비워둠</p>
            <p>비워둠</p>
            <p>비워둠</p>
            <p>비워둠</p>
            <p>비워둠</p>
        </div>
        <div class="profileStyle">
            <form>
                <div class="profileImg">
                    <div class="ImgPreview">
                        <div>
                            <img src="/images/default_icon_profile.png" class="profile-photo" alt="회원사진">
                        </div>
                        <div class="editIcon">

                            <label for="upload"><img src="/images/whitePen.png" alt="편집"></label>
                            <input type="file" id="upload" accept="image/gif, image/png, image/jpeg"
                                   style="margin-left : 57px;">
                        </div>
                    </div>

                </div>
                <div>
                    <div class="profileId">
                        <input type="text" id="email" value="${userInfo.userEmail}" disabled>
                    </div>
                    <div>
                        <img src="/images/nickName.png">
                        <input type="text" id="nickName" name="nickName" value="${userInfo.nickName}">
                        <button type="button" id="nickNameChk"> 중복체크</button>

                    </div>
                    <div id="nickNameErrs">
                        <p id="err1"></p>
                    </div>
                    <div>
                        <img src="/images/thickPhone.png" alt="휴대전화">
                        <input type="tel" id="phone" name="phone" value="${userInfo.phone}">

                    </div>
                </div>
            </form>
            <div>
                <button type="button" id="editBttn">정보 수정</button>
            </div>
        </div>
    </main>
    <script defer src="/js/main/editForm.js"></script>

</div>
<%@include file="../layout/footer.jsp" %>