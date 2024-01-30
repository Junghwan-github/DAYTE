<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>

<link rel="stylesheet" href="/css/contentInfo.css">
<link rel="stylesheet" href="/css/contentSlider.css">

<title>DAYTE | </title>

</head>
<body>
<!-- 해야할 일 : 자바스크립트로 이미지 테이블의 이미지 갯수 끌고와서 div만들어 넣기
      (contentInfo.css)에 마지막 줄에 bxslider bgimage 넣는 형식으로 만들어야 함
    -->
<%@include file="../layout/header.jsp" %>

<!-- 메인 -->
<main>
    <section id="mainContentsImages">
        <div class="mainContentsImagesWrapper">
            <div><img src="/images/contentstest1.jpg"></div>
            <div><img src="/images/contentstest2.jpg"></div>
            <div><img src="/images/contentstest3.jpg"></div>
        </div>
    </section>
    <!-- 이미지 슬라이드 -->
    <%--        <div class="slider">--%>
    <%--            <div class="bxslider" name="bxslider">--%>
    <%--                <div></div>--%>
    <%--                <div></div>--%>

    <%--            </div>--%>

    <%--            <div class="contentFigCaption">--%>
    <%--                <figcaption>--%>
    <%--                    <span id="nowImg">1</span>--%>
    <%--                    <span> / </span>--%>
    <%--                    <span id="allImg"></span>--%>
    <%--                </figcaption>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <section id="contentInfo">

        <!-- 콘텐츠 정보 -->
        <span>달서구</span> <span>맛집</span> <span>#한식</span>
        <h1>문어물갈비 대구본점<span>★ 4.8</span></h1>
        <div class="content-wrapper">
            <div class="left-content">
                <h2>문어물갈비 대구본점 소개</h2>
                <p>
                    대구 달서구에만 있는 특별한 맛집 문어물갈비
                    한번도 안 먹어본 사람은 있어도 한번만 먹은 사람은 없다!!
                    직접 공수해온 쫄깃한 돌문어와 갈비의 만남
                    각종야채로 우러나온 맑고 시원한국물
                    특별한 보양식이 먹고싶을때 꼭 한번 먹어야 할 맛집
                    문어는 남해 거제에서 직접 공수합니다.
                    10맛집 선정
                    생생정보통에서도 소개된 맛집!!
                </p>

            </div>
            <!-- 지도 -->
            <%--            <div class="contentMap">--%>
            <%--                <img src="/images/exMapImg.png" alt="지도예시"/>--%>
            <%--            </div>--%>

            <div class="summaryDesc">
                <span class="pointer-x">35.822948</span> <span class="pointer-y">128.520754</span>
                <div id="content-map">
                    <ul class="content-info-wrapper">
                        <li class="address">
                            <i class="fa-solid fa-location-dot"></i>
                            <span> 대구광역시 달서구 월성1동 조암로 90</span>
                        </li>
                        <li class="contact">
                            <i class="fa-solid fa-phone-volume"></i>
                            <span>053.634.5253</span>
                        </li>
                        <li class="businessHours">
                            <i class="fa-regular fa-clock"></i>
                            <span>11:30 ~ 22:00</span>
                        </li>
                    </ul>
                </div>
            </div>
            <%--            <div class="bookingLink">--%>
            <%--                <a href="#">예약페이지로 이동</a>--%>
            <%--            </div>--%>
        </div>
    </section>

    <!-- 이용후기, 댓글 탭메뉴 -->
    <div class="tabMenu ">
        <ul>

            <li id="tab1" class="conBtn">
                <input type="radio" name="tabMenu" id="tabMenu1"/>
                <label for="tabMenu1">간단 리뷰</label>
                <div class="tabContent">
                    <!-- 간단 리뷰 -->
                </div>
            </li>

            <li id="tab2" class="conBtn">
                <input type="radio" name="tabMenu" id="tabMenu2"/>
                <label for="tabMenu2">관련 후기</label>
                <div class="tabContent">
                    <!-- 상세 후기 -->
                </div>

            </li>
        </ul>
    </div>
</main>
<script defer src="/js/contentInfo.js"></script>
<%@include file="../layout/footer.jsp" %>