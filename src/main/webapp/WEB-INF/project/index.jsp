<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="layout/head.jsp"%>
<%--여기 각자 쓸 css--%>
<link rel="stylesheet" href="/css/index.css">
<link rel="stylesheet" href="/css/slider.css" >
    <title>Project T</title>
  </head>

  <body>
  <div id="headerBack"></div>
  <%@include file="layout/header.jsp"%>
  <script src="/js/indexheader.js"></script>
  <%--여기부터 본문 시작--%>
    <!-- 메인 -->
    <main>
      <!-- 슬라이드 -->
      <section id="visual">
        <ul class="mainSlider">
          <li class="items">
            <!-- db단으로 들어올 내용 -->
            <img src="/images/testimages4.jpg">
            <span class="sticker">공연/행사</span>
            <h2 class="i-1">이월드<span class="i-1">ILLUMINATION</span></h2>
            <ul class="i-1">
              <li>일정 : 2023.11.18 ~ 2024.02.28</li>
              <li>주소 : 대구광역시 달서구 두류공원로 200 이월드</li>
            </ul>
            <p class="i-1">신비로운 마법의 빛과 달콤한 환상이 가득한 비비의 꿈속으로 여행을 떠나볼까요?</p>
            <a class="i-1" target="_blank" href="/contents/123123">자세히 보기</a>
          </li>
          <li class="items">
            <img src="/images/testimages5.jpg">
            <span class="sticker">테스트</span>
            <h2 class="i-1">테스트<span class="i-1">HOTEL</span></h2>
            <ul class="i-1">
              <li>일정 : 2023.11.18 ~ 2024.02.28</li>
              <li>주소 : 대구광역시 달서구 두류공원로 200 이월드</li>
            </ul>
            <p class="i-1">신비로운 마법의 빛과 달콤한 환상이 가득한 비비의 꿈속으로 여행을 떠나볼까요?</p>
            <a class="i-1" href="#">자세히 보기</a>
          </li>
          <li class="items">
            <img src="/images/testimages6.jpg">
            <span class="sticker">테스트</span>
            <h2 class="i-1">투어<span class="i-1">TESTCLUSE</span></h2>
            <ul class="i-1">
              <li>일정 : 2023.11.18 ~ 2024.02.28</li>
              <li>주소 : 대구광역시 달서구 두류공원로 200 이월드</li>
            </ul>
            <p class="i-1">신비로운 마법의 빛과 달콤한 환상이 가득한 비비의 꿈속으로 여행을 떠나볼까요?</p>
            <a class="i-1" href="#">자세히 보기</a>
          </li>
        </ul>
      </section>
      <!-- 검색 -->
      <section id="searchForm">
        <form action="#" method="get">
          <ul>
            <li>
              <input
                type="text"
                name="indexSearch"
                placeholder="검색어를 입력하세요" autocomplete="off" />
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
                <h3>지금 핫한 장소
                </h3>
                <ul class="c-jg">
                  <li class="isl-1">
                    <div class="isl-img-area"></div>
                    <div class="isl-text-area">
                      <h3>랑데자뷰</h3>
                      <p>대구광역시 북구 구암로 15길 38</p>
                      <p>카페 자리 좋고 물좋고 커피좋고 어디부터어디까지</p>
                      <a href="#">자세히 보기</a>
                    </div>
                  </li>
                  <li class="isl-2">
                    <div class="isl-img-area"></div>
                    <div class="isl-text-area">
                      <h3>스파크 랜드</h3>
                      <p>대구광역시 북구 구암로 15길 38</p>
                      <p>카페 자리 좋고 물좋고 커피좋고 그렇게 살아라</p>
                      <a href="#">자세히 보기</a>
                    </div>
                  </li>
                  <li class="isl-3">
                    <div class="isl-img-area"></div>
                    <div class="isl-text-area">
                      <h3>맛있는 스파게티</h3>
                      <p>대구광역시 북구 구암로 15길 38</p>
                      <p>카페 자리 좋고 물좋고 커피좋고 동해물과백두산이...</p>
                      <a href="#">자세히 보기</a>
                    </div>
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
            <li>
              <div>
                <h3>숙소</h3>
                <p>아직도 숙소를 찾고있나요?</p>
                <p>모든 숙소 정보를 한눈에 확인하세요!</p>
                <img src="/images/hotel.png">
              </div>
            </li>
            <li>
              <div>
                <h3>맛집</h3>
                <p>어디가 맛집일까?</p>
                <p>고민하지 않도록 제가 다 알려드릴게요!</p>
                <img src="/images/rest.png">
              </div>
            </li>
            <li>
              <div>
                <h3>카페</h3>
                <p>오늘의 내 기분은?</p>
                <p>기분따라 다른 분위기 카페 여기있어요!</p>
                <img src="/images/cafe.png">
              </div>
            </li>
            <li>
              <div>
                <h3>공연/전시</h3>
                <p>무엇을 보고싶으세요?</p>
                <p>모든 공연/전시 일정 알려드릴께요!</p>
                <img src="/images/event.png">
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
              <li class="rainPercent"></li>
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
  <script src="/js/index.js"></script>
  <%@include file="layout/footer.jsp"%>