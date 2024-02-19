<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/head.jsp" %>

<title>DAYTE | 내 리뷰</title>


<link rel="stylesheet" href="/css/myReview/myRating.css">

</head>

<body>

<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>
<main id="past-schedule-container">
    <nav id="past-schedule-side-nav">
        <ul>
            <li>
                <a href="/schedule/pastSchedule">리얼리뷰 등록<i
                        class="xi-angle-right-min"></i></a>
            </li>
            <li>
                <a href="/myReview">내가 등록한 글<i class="xi-angle-right-min"></i></a>
            </li>
            <li>
                <a href="/myRating" class="past-schedule-side-nav-active">내가 등록한 리뷰<i
                        class="xi-angle-right-min"></i></a>
            </li>
        </ul>
    </nav>
    <div class="container">
        <c:forEach var="reply" items="${myReview.content}">
            <div class="reviewRatingBox">

                    <%--내 일정관리에서 완료된 컨텐츠의 리뷰를 가지고 올 것--%>

                <div class="contents-images">
                    <img src="${reply.contents.adminContentsImageList[0].imageURL}">
                </div>
                <c:if test="${reply.user.userEmail eq principal.userEmail}">
                    <div class="contents-text">
                        <ul>
                            <li><h1>${reply.contents.businessName}</h1></li>
                            <li><span class="star-point">${reply.rating}</span></li>
                            <li>
                                <div class="text-content-box">
                                    <p class="myreview-content">${reply.content}</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="modify-delete-button-wrapper">
                        <i class="xi-ellipsis-v"></i>
                    </div>
                    <ul class="modify-delete-button">
                        <li>
                            <button class="modify" value="${reply.contents.uuid}"><i class="xi-pen-o"></i>수정</button>
                        </li>
                        <li>
                            <button class="delete" value="${reply.num}"><i class="xi-trash-o"></i>삭제</button>
                        </li>
                    </ul>
                </c:if>

            </div>
        </c:forEach>
        <div class="pagination">
            <ul>
                <c:if test="${!myReviewPage.first}">
                    <li>
                        <a class="pageDoubleMoveBtn" href="?page=0">
                            <img src="/images/doublePrevious.png"/>
                            처음으로
                        </a>
                    </li>
                    <li>
                        <a class="pageMoveBtn" href="?page=${myReviewPage.number - 1}">
                            <img src="/images/previous.png"/>
                            이전
                        </a>
                    </li>
                </c:if>

                <c:if test="${endPage >= 0}">
                    <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <li class="paginationNum">
                            <a class="<c:if  test='${currentPage == i}'>active</c:if>"
                               href="?page=${i}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>

                <c:if test="${!myReviewPage.last}">
                    <li>
                        <a class="pageMoveBtn" href="?page=${myReviewPage.number + 1}">
                            <img src="/images/next.png"/>
                            다음
                        </a>
                    </li>
                    <li>
                        <a class="pageDoubleMoveBtn" href="?page=${myReviewPage.totalPages-1}">
                            <img src="/images/doubleNext.png"/>
                            마지막으로
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</main>

<script src="/js/myReview/myRating.js"></script>
<%--<script src="/js/contentReply/contentReply.js"></script>--%>
<%@include file="../layout/footer.jsp" %>