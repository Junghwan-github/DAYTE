<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/head.jsp" %>
<%--여기 각자 쓸 css--%>
<link rel="stylesheet" href="/css/contents/allSearches.css">
<title>DAYTE</title>
</head>

<body>
<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>
<main>
    <div class="all-search-container">
        <div class="all-search-contents">
            <div class="top-wrapper">
                <h1>상세보기 바로가기</h1>
                <span>목록 접기<i class="xi-angle-down-min"></i></span>
            </div>
            <div class="all-search-contents-display">
                <c:if test="${!empty contentsList}">
                    <c:forEach var="contents" items="${contentsList}">
                        <div class="all-search-contents-wrapper">
                            <div class="contentListItemsImages">
                                <img src="${contents.adminContentsImageList[0].imageURL}">
                            </div>
                            <ul class="contentListItemText">
                                <li>
                                    <div class="contents-title-wrapper">
                                        <h2><a href="/contents/detail/${contents.uuid}"
                                               target="_blank">${contents.businessName}</a></h2>
                                        <span>${contents.category}</span>
                                        <span>${contents.keyword}</span>
                                    </div>
                                </li>
                                <li>
                                    <span>${contents.detailedAddress}</span>
                                </li>
                                <li>
                                    <p>영업시간 : ${contents.opening} ~ ${contents.closed}</p>
                                    <p>문의 : ${contents.contactInfo}</p>
                                </li>
                                <li>
                                    <span>★ 4.5</span>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="all-search-post">
            <div class="top-wrapper">
                <h1>이용후기</h1>
                <span>목록 접기<i class="xi-angle-down-min"></i></span>
            </div>
            <div class="all-search-post-display">
                <c:if test="${!empty postList}">
                    <c:forEach var="post" items="${postList}">
                        <div class="content-part">
                            <div class="post-items-title">
                                <h2><a href="/post/${post.id}">${post.title}</a></h2>
                            </div>
                            <div class="post-items-images">
                                <ul>
                                    <c:forEach var="images" items="${post.postImages}">
                                        <li>
                                            <div><img src="${images.imageUrl}"/></div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="post-items-content">
                                <c:set var="postId" value="${post.id}"></c:set>
                                <!-- postId 변수 생성하여 postId 대입 -->
                                <p>
                                    <c:forEach var="text" items="${postListText}">
                                        <!-- postListText 를 순회하여 text 변수에 대입 -->
                                        <c:if test="${text.getLeft() eq postId}">
                                            ${text.getRight()}
                                        </c:if>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</main>

<script src="/js/contents/allSearches.js"></script>
<%@include file="../layout/footer.jsp" %>