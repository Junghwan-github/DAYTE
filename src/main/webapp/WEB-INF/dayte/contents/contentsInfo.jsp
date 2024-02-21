<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>

<link rel="stylesheet" href="/css/schedule/contentInfo.css">

<title>DAYTE | ${showContentsDetail.businessName} </title>

</head>
<body>
<!-- 해야할 일 : 자바스크립트로 이미지 테이블의 이미지 갯수 끌고와서 div만들어 넣기
      (contentInfo.css)에 마지막 줄에 bxslider bgimage 넣는 형식으로 만들어야 함
    -->
<%@include file="../layout/header.jsp" %>
<!-- 메인 -->
<section id="content-sub-nav">
    <div class="content-sub-nav-container">
        <ul>
            <li><a href="#mainContentsImages">처음으로</a></li>
            <li><a href="#contentInfo">기본정보</a></li>
            <li><a href="#amenities">편의시설</a></li>
            <li><a href="#map-pointer">위치</a></li>
            <li><a href="#tab-review">리얼리뷰</a></li>
        </ul>
    </div>
</section>

<main>
    <section id="mainContentsImages">
        <div class="mainContentsImagesWrapper">
            <div><img src="${showContentsDetail.adminContentsImageList[0].imageURL}"></div>
            <div><img src="${showContentsDetail.adminContentsImageList[1].imageURL}"></div>
            <div><img src="${showContentsDetail.adminContentsImageList[2].imageURL}"></div>
        </div>
    </section>
    <section id="contentInfo">

        <!-- 콘텐츠 정보 -->
        <span>${showContentsDetail.gu}</span> <span>${showContentsDetail.category}</span>
        <span>${showContentsDetail.keyword}</span>
        <h1>${showContentsDetail.businessName}<span class="star">★${star.starAVG}</span></h1>
        <div class="content-wrapper">
            <div class="left-content">
                <h2>${showContentsDetail.businessName} 소개</h2>
                <p>
                    ${showContentsDetail.detailedDescription}
                </p>
            </div>
            <div class="summaryDesc">
                <span class="pointer-x">${showContentsDetail.positionX}</span> <span
                    class="pointer-y">${showContentsDetail.positionY}</span>
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
        </div>
    </section>
    <section id="amenities">
        <div class="amenities-container">
            <h2>편의시설</h2>
            <div class="amenities-content">
                <span>${showContentsDetail.facilities}</span>
            </div>
        </div>
    </section>

    <section id="map-pointer">
        <div class="map-pointer-container">
            <h2>위치</h2>
            <div class="map-xy">

            </div>
        </div>
    </section>
    <!-- 이용후기, 댓글 탭메뉴 -->
    <section id="tab-review">
        <div class="tab-container">
            <div class="tab-wrapper">
                <div class="tab atv" onclick="openTab('tab1')">리얼리뷰</div>
                <div class="tab" onclick="openTab('tab2')">관련후기</div>
            </div>
            <div id="tab1" class="tab-content active">
                <div class="reply-content-review-list">
                    <c:forEach var="reply" items="${contentReply}">
                        <div class="reply-content-review-item">
                            <div class="user-info">
                                <ul>
                                    <li>
                                        <div class="user-profile-image">
                                            <img src="${reply.user.profileImagePath}">
                                        </div>
                                    </li>
                                    <li>
                                        <span class="nick-name">${reply.user.nickName}</span><span class="create-date"><fmt:formatDate value="${reply.createDate}" pattern="yyyy.MM.dd"/></span>
                                    </li>
                                </ul>
                                <div>
                                    <span class="star-point">${reply.rating}</span>
                                </div>
                            </div>
                            <div class="reply-content">
                                <p>${reply.content}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div id="tab2" class="tab-content">
                <div class="reply-content-review-list">
                    <c:forEach var="post" items="${postList}">
                        <div class="reply-content-review-item" onclick="location.href='/post/${post.id}'" >
                            <div class="user-info-post">
                                <ul>
                                    <li>
                                        <div class="user-profile-image">
                                            <img src="${post.user.profileImagePath}">
                                        </div>
                                    </li>
                                    <li>
                                        <span class="nick-name">${post.user.nickName}</span><span class="create-date"><fmt:formatDate value="${post.createDate}" pattern="yyyy.MM.dd"/>
                                    </li>
                                </ul>
                            </div>

                            <div class="reply-content-post">
                                <h3>${post.title}</h3>
                                <div class="images-wrapper">
                                    <c:forEach var="images" items="${post.postImages}" varStatus="loop">
                                        <c:if test="${loop.index lt 4}">
                                        <div>
                                            <img src="${images.imageUrl}"/>
                                        </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="post-items-content">
                                    <c:set var="postId" value="${post.id}"></c:set>
                                    <p><c:forEach var="text" items="${postListText}">
                                            <!-- postListText 를 순회하여 text 변수에 대입 -->
                                            <c:if test="${text.getLeft() eq postId}">
                                                ${text.getRight()}
                                            </c:if>
                                        </c:forEach>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
</main>
<div class="images-detail-modal">
    <div class="images-detail-wrapper">
        <h3><i class="xi-close"></i></h3>
        <ul class="images-detail-slider">
            <c:forEach var="images" items="${showContentsDetail.adminContentsImageList}">
                <li><img src="${images.imageURL}"/></li>
            </c:forEach>
        </ul>
    </div>
</div>
<script defer src="/js/schedule/contentInfo.js"></script>
<%@include file="../layout/footer.jsp" %>
