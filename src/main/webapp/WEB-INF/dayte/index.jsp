<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/head.jsp" %>

<%--여기 각자 쓸 css--%>
<link rel="stylesheet" href="/css/main/index.css">
<link rel="stylesheet" href="/css/main/slider.css">
<title>DAYTE</title>
</head>

<body>
<div id="headerBack"></div>
<%@include file="layout/header.jsp" %>
<%--여기부터 본문 시작--%>
<!-- 메인 -->
<main>
    <!-- 슬라이드 -->
    <section id="visual">
        <ul class="mainSlider">
<c:forEach var="indexSliderItem" items="${sliderList}">
            <li class="items">
                <!-- db단으로 들어올 내용 -->
                <div class="indexMainSliderImages">
                    <img src="${indexSliderItem.images}">
                </div>
                <span class="sticker">${indexSliderItem.category}</span>
                <h2 class="i-1">${indexSliderItem.mainTitle}<span class="i-1">${indexSliderItem.subTitle}</span></h2>
                <ul class="i-1">
                    <li>${indexSliderItem.schedule}</li>
                    <li>${indexSliderItem.address}</li>
                </ul>
                <p class="i-1">${indexSliderItem.summary}</p>
                <a class="i-1" target="_blank" href="/event/${indexSliderItem.href}">자세히 보기</a>
            </li>
</c:forEach>
        </ul>
    </section>
    <!-- 검색 -->
    <section id="searchForm">
        <form action="/contents/indexSearch" method="get">
            <ul>
                <li>
                    <input
                            type="text"
                            name="indexSearch"
                            placeholder="검색어를 입력하세요" autocomplete="off"/>
                </li>
                <li class="searchContents">
                    <div class="cityList">
                        <h3>구/군</h3>
                        <ul>
                            <li>중구</li>
                            <li>수성구</li>
                            <li>북구</li>
                            <li>동구</li>
                            <li>남구</li>
                            <li>서구</li>
                            <li>달서구</li>
                            <li>달성군</li>
                            <li>군위군</li>
                        </ul>
                    </div>
                    <div class="previewImage">
                        <h3>지금 핫한 장소</h3>
                        <ul class="c-jg">
                            <c:forEach var="contentList" items="${contentList}" varStatus="loop">
                                <c:if test="${loop.index lt 5}">
                                    <li class="isl-items">
                                        <div class="isl-img-area">
                                            <img src="${contentList. adminContentsImageList[0].imageURL}">
                                        </div>
                                        <div class="isl-text-area">
                                            <h3>${contentList.businessName}</h3>
                                            <p>${contentList.detailedAddress}</p>
                                            <p class="description">${contentList.detailedDescription}</p>
                                            <a href="/contents/detail/${contentList.uuid}" target="_blank">자세히 보기</a>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
                <li>
                    <button type="submit"><i class="xi-search"></i></button>
                </li>
            </ul>
        </form>
    </section>
    <!-- 카드박스 -->
    <section id="categoryCard">
        <ul class="subNav">
            <li onclick="indexContentsListLink('hotels')">
                <div>
                    <h3>숙소</h3>
                    <p>아직도 숙소를 찾고있나요?<br>모든 숙소 정보를 한눈에 확인하세요!</p>
                    <img src="/images/bedicon.png">
                </div>
            </li>
            <li onclick="indexContentsListLink('restaurants')">
                <div>
                    <h3>맛집</h3>
                    <p>어디가 맛집일까?<br>고민하지 않도록 제가 다 알려드릴게요!</p>
                    <img src="/images/foodicon.png">
                </div>
            </li>
            <li onclick="indexContentsListLink('cafes')">
                <div>
                    <h3>카페</h3>
                    <p>오늘의 내 기분은?<br>기분따라 다른 분위기 카페 여기있어요!</p>
                    <img src="/images/coffeicon1.png">
                </div>
            </li>
            <li onclick="indexContentsListLink('events')">
                <div>
                    <h3>이벤트</h3>
                    <p>무엇을 보고싶으세요?<br>모든 공연/전시 일정 알려드릴께요!</p>
                    <img src="/images/eventicon2.png">
                </div>
            </li>
        </ul>
    </section>
    <div class="sectionSubject"><h2>날씨 | 예보 </h2><span> 대구광역시 날씨 정보만 제공됩니다.
            - 기상청 자료</span></div>
    <!-- <div id="weatherbg"></div> -->
    <section id="weather">
        <div class="present">
            <h3>현재 날씨</h3>
            <ul class="weatherVisual">
                <li class="weatherIcon"></li>
                <li class="temperature"></li>
            </ul>
            <ul class="weatherText">
                <li class="minMaxtemp"></li>
                <li class="presentRainPercent"></li>
            </ul>
        </div>
        <div class="nextWeather">
            <div class="today6Hours">
                <ul>

                </ul>
            </div>
            <div class="daysWeather">
                <ul>

                </ul>
            </div>
        </div>
    </section>
</main>
<script src="/js/main/index.js"></script>
<script src="/js/main/editForm.js"></script>
<%@include file="layout/footer.jsp" %>