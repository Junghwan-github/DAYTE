<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/head.jsp" %>

<title>DAYTE | 내 리뷰</title>


<link rel="stylesheet" href="/css/myReview/myReview.css">

</head>

<body>

<%@include file="../layout/header.jsp" %>
<div class=wrapper>
<h1 class="title"> 내 리뷰 | 내 별점 </h1>

<div id="divform" class="tableContainer">
    <c:if test="${!empty myReviewPage}">
        <table id="myReviewTable">
            <tbody>
            <tr class="myReviewTable">
                <th class="thStyle">번호</th>
                <th class="thStyle thTitle">제목</th>
                <th class="thStyle thDate">게시일</th>
            </tr>
            <c:forEach var="myReview" items="${myReviewPage.content}">
                <tr class="myReviewTrStyle listHover">
                    <td class="tdStyle">${myReview.id}</td>
                    <td class="tdStyle"><a href="/post/${myReview.id}" class="tdTitle">${myReview.title}</a></td>
                    <td class="tdStyle"><fmt:formatDate value="${myReview.createDate}" pattern="yy-MM-dd"/></td>
                </tr>
            </c:forEach>
            <br>
            </tbody>
        </table>

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

        </c:if>

    </div>
</div>
</body>
















<%@include file="../layout/footer.jsp" %>