<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@include file="../layout/head.jsp"%>
<style>
    .wrapper {
        width: 1180px;
        /*height: 100vh;*/
        margin: 0 auto;
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
    .sideBar > ul > li> a {
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
    .sideBar > ul > li> a:hover {
        color: #333;
        font-weight: 600;
        background-color: #f1f0f0;
        transition: 0.2s;
    }
    .sideBar > ul > li> a > img {
        width: 16px;
        height: 16px;
    }


    /* ============================================ 프로필 페이지 */
    main {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    main > div:first-child {
        margin-top: 30px;
        width: 95%;
        background-color: yellow;
    }

    /* ============================================ 정보 변경  */
    .profileStyle{
        /*display: inline-block;*/
        width: 90%;
        margin-top: 30px;
        padding: 20px;

        box-shadow: 0 1px 2px 0 rgba(51, 51, 51, 0.5);
        background-color: #fff;
        border-radius: 10px;

        display: flex;
        flex-direction: column;
    }
    /* ================================ 버튼 전역 설정 */
    .profileStyle button {
        border: transparent 1px solid;
        border-radius: 5px;
        padding: 5px;
        background-color: #333;
        color: #fff;
    }

    .profileStyle button:hover {
        background-color: #555;
        transition: 0.2s;
    }
    /* ================================== 내정보 리스트 */
    .profileStyle > div {
        margin: 20px;
        font-size: 1.8rem;
        display: flex;
        justify-content: center;

    }
    .profileStyle > div >button {
        /* 정보 수정 버튼 */
         padding: 10px 40px;
        font-size: 1.6rem;
    }

    .profileStyle > form > div {

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    .profileStyle > form > div:first-child {
        align-items: center;
        flex-direction: column;
        height: 22rem;

    }

    .ImgPreview {
        position: relative;

    }
    .ImgPreview > div > img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .ImgPreview > div:first-child{
        width: 18rem;
        height: 18rem;
        border-radius: 70%;
        box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
        overflow: hidden;

    }
    .editIcon {
        border-radius: 70%;
        overflow: hidden;
        padding: 7px;
        width: 32px;
        height: 32px;
        background-color: #28536a;
        box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;

        position: absolute;
        right: 10px;
        top:100%;
        transform: translate(0,-100%);
    }
    .editIcon  > label > img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        cursor: pointer;
    }


    .editIcon > input {
        display: none;
    }

    .editIcon:hover {
        background-color: #333;
        transition: 0.2s;
        cursor: pointer;
    }
    .profileId {
        margin: 0 0 10px 0 ;
    }
    /* ========================================= 닉네임 및 정보수정 필드*/
    .profileId > input{
        text-align: center;
        width: 100%;
        border: 1px transparent solid;
        padding: 3px 10px;
        background-color: #fff;
        font-size: 2rem;
        font-weight: 600;
    }
    .profileStyle > form > div:last-child > div >img {
        width: 20px;
        height: 20px;
        margin: 10px 0 0 7px;
        position: absolute;
        z-index: 2;

    }

    .profileStyle > form > div:last-child > div:nth-child(2),
    .profileStyle > form > div:last-child > div:nth-child(4)  {
        width: 30%;
        height: 40px;
        display: flex;
        justify-content: start;
    }

    .profileStyle > form > div:last-child > div:nth-child(2) > input {
        width: 65%;
        font-size: 1.5rem;
        padding-left: 40px;
        font-weight: 600;
        border: 1px #777 solid;
        outline: none;
        position:relative;
    }
    .profileStyle > form > div:last-child > div:nth-child(4) > input {
        width: 100%;
        font-size: 1.5rem;
        padding-left: 40px;
        font-weight: 600;
        border: 1px #777 solid;
        outline: none;
        position:relative;
    }

    .profileStyle > form > div:last-child > div > button {
        /* 중복체크 버튼 */
        width: 35%;
        font-size: 1.4rem;
        border-radius: 0;
        border: 1px #333 solid;
    }
    /* ==================================== 닉네임 에러  */
    #nickNameErrs {
        width: 30%;
        height:20px;
        padding-left: 10px;
    }

</style>
<title>내 정보 | 나의 프로필</title>
</head>
<body>
<%@include file="../layout/header.jsp"%>
<div class="wrapper">
    <aside class="sideBar">
        <ul>
            <li> 내 정보 </li>
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
                        <img src="/images/cat.jpg" class= "profile-photo" alt="회원사진">
                        </div>
                        <div class="editIcon">

                            <label for="upload"><img src="/images/whitePen.png" alt="편집"></label>
                            <input type= "file" id="upload" accept= "image/gif, image/png, image/jpeg" style= "margin-left : 57px;">
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
                        <button type="button" id="nickNameChk"> 중복체크 </button>

                    </div>
                    <div id="nickNameErrs" >
                        <p id="err1"></p>
                    </div>
                    <div>
                        <img src="/images/thickPhone.png" alt="휴대전화">
                        <input type="tel" id="phone" name="phone" value="${userInfo.phone}" >

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
<%@include file="../layout/footer.jsp"%>