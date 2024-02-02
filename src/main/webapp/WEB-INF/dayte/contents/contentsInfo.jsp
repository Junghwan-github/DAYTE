<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>

<link rel="stylesheet" href="/css/schedule/contentInfo.css">
<link rel="stylesheet" href="/css/schedule/contentSlider.css">

<title>DAYTE | ${showContentsDetail.businessName} </title>

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
            <div><img src="${showContentsDetail.adminContentsImageList[0].imageURL}"></div>
            <div><img src="${showContentsDetail.adminContentsImageList[1].imageURL}"></div>
            <div><img src="${showContentsDetail.adminContentsImageList[2].imageURL}"></div>
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
        <span>${showContentsDetail.gu}</span> <span>${showContentsDetail.category}</span> <span>${showContentsDetail.keyword}</span>
        <h1>${showContentsDetail.businessName}<span>★ 4.8</span></h1>
        <div class="content-wrapper">
            <div class="left-content">
                <h2>${showContentsDetail.businessName} 소개</h2>
                <p>
                    ${showContentsDetail.detailedDescription}
                </p>

            </div>
            <!-- 지도 -->
            <%--            <div class="contentMap">--%>
            <%--                <img src="/images/exMapImg.png" alt="지도예시"/>--%>
            <%--            </div>--%>

            <div class="summaryDesc">
                <span class="pointer-x">${showContentsDetail.positionX}</span> <span class="pointer-y">${showContentsDetail.positionY}</span>
                <div id="content-map">
                    <ul class="content-info-wrapper">
                        <li class="address">
                            <i class="fa-solid fa-location-dot"></i>
                            <span>${showContentsDetail.detailedAddress}</span>
                        </li>
                        <li class="contact">
                            <i class="fa-solid fa-phone-volume"></i>
                            <span>${showContentsDetail.contactInfo}</span>
                        </li>
                        <li class="businessHours">
                            <i class="fa-regular fa-clock"></i>
                            <span>${showContentsDetail.opening} ~ ${showContentsDetail.closed}</span>
                        </li>
                    </ul>
                </div>
                <div class="content-event-banner">
                    <h2>진행중인 이벤트</h2>
                    <div>
                        <ul class="content-banner-slider">
                            <li>
                                <div class="item-images"><img src="/images/content-banner1.png"></div>
                            </li>
                            <li>
                                <div class="item-images"><img src="/images/content-banner2.png"></div>
                            </li>
                            <li>
                                <div class="item-images"><img src="/images/content-banner3.png"></div>
                            </li>
                        </ul>
                    </div>
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

                </div>
            </li>

            <li id="tab2" class="conBtn">
                <input type="radio" name="tabMenu" id="tabMenu2"/>
                <label for="tabMenu2">관련 후기</label>
                <div class="tabContent">

                </div>

            </li>
        </ul>
    </div>
</main>
<script defer src="/js/schedule/contentInfo.js"></script>
<%@include file="../layout/footer.jsp" %>
