<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp"%>
<script defer src="/js/contentInfo.js"></script>
<link rel="stylesheet" href="/css/contentInfo.css" >
<link rel="stylesheet" href="/css/contentSlider.css">

<title>DAYTE | </title>

</head>
<body>
<!-- 해야할 일 : 자바스크립트로 이미지 테이블의 이미지 갯수 끌고와서 div만들어 넣기
      (contentInfo.css)에 마지막 줄에 bxslider bgimage 넣는 형식으로 만들어야 함
    -->
<%@include file="../layout/header.jsp"%>
<div class="contentsWrapper">

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
            <!-- 지도 -->
<%--            <div class="contentMap">--%>
<%--                <img src="/images/exMapImg.png" alt="지도예시"/>--%>
<%--            </div>--%>
            <!-- 콘텐츠 정보 -->
            <span>맛집</span> <span>달서구</span>
            <h1>문어물갈비 대구본점</h1>
            <div class="summaryDesc">
<%--                <div class="businessName">--%>
<%--                    <p>쭈니 닭갈비 & 우동 테스트</p>--%>
<%--                    <div class="schedule">--%>
<%--                        <button id="addSchedule"><a href="#">일정에 추가하기</a></button>--%>
<%--                    </div>--%>
<%--                    <div class="detail">--%>
<%--                        <p>닭갈비가 맛있는 집, 식후 식혜도 줌 닭갈비 비빔밥도 맛있음 근데 가격이 9천원이었는데 만원으로 오름 슬픔</p>--%>
<%--                    </div>--%>
<%--                </div>--%>


                <div class="address">
                    <img src="/images/icon-flags.png" alt="주소">
                    <p> 대구광역시 서구 어쩌구 저쩌구</p>
                </div>
                <div class="contact">
                    <img src="/images/icon-phone.png" alt="연락처">
                    <p>053-123-4567</p>
                </div>
                <div class="businessHours">
                    <img src="/images/icon-clock.png" alt="이용시간">
                    <p>이용 시간</p>
                </div>
                <div class="bookingLink">
                    <a href="#">예약페이지로 이동</a>
                </div>

            </div>

        </div>
        <!-- 이용후기, 댓글 탭메뉴 -->
        <div class="tabMenu ">
            <ul>
                <li id="tab1" class="conBtn">
                    <input type="radio" checked name="tabMenu" id="tabMenu1"/>
                    <label for="tabMenu1"> 이용 팁</label>
                    <div class="tabContent">
                        <!-- 이용 팁 -->
                        <div>
                            주차 어저구저쩌구
                            비용 어쩌구 저꺼주
                        </div>
                    </div>
                </li>

                <li id="tab2" class="conBtn">
                    <input type="radio" name="tabMenu" id="tabMenu2"/>
                    <label for="tabMenu2">간단 리뷰</label>
                    <div class="tabContent">
                        <!-- 간단 리뷰 -->
                    </div>
                </li>

                <li id="tab3" class="conBtn">
                    <input type="radio" name="tabMenu" id="tabMenu3"/>
                    <label for="tabMenu3">상세 후기</label>
                    <div class="tabContent">
                        <!-- 상세 후기 -->
                    </div>

                </li>
            </ul>
        </div>
    </main>

</div>
<footer></footer>
</body>
</html>