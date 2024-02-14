<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css --%>
<link rel="stylesheet" href="/css/post/mainPostList.css">
<title>DAYTE | 일정후기</title>
</head><%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp" %> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>

<main> <%-- main --%>
    <div class="page-title">
        <h1>일정후기</h1>
    </div>

    <div class="post-list-container">
        <c:forEach var="post" items="${postList.content}">
            <div class="post-list-items-wrapper" onclick="getPostDetail(${post.id})"> <!-- 게시글 상세페이지 클릭 -->
                <div class="user-part">
                    <ul>
                        <li>
                            <div><img src="${post.user.profileImagePath}"></div> <!-- 프로필 사진 -->
                        </li>
                        <li><span>${post.user.nickName}</span></li>
                        <li><span><fmt:formatDate value="${post.createDate}" pattern="yyyy.MM.dd"/></span></li>
                    </ul>
                </div>
                <div class="content-part">
                    <div class="post-items-title">
                        <h2>${post.title}</h2>
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
            </div>
        </c:forEach>

    </div>

    <%------------------------------------ bottom wrapper ------------------------------------%>
    <div class="post-bottom-wrapper">

        <%-- 검색폼--%>
        <div class="postSearchForm">
            <form action="/mainPostList" method="get">
                <label for="postBordSearchDropDown"></label>
                <select id="postBordSearchDropDown" name="postField">
                    <option value="postAll">전체</option>
                    <option value="postTitle">제목</option>
                    <option value="postContent">내용</option>
                </select>
                <input type="text" name="postWord"/>
                <button type="submit" class="postSearchSubmitBtn">검색</button>
                <button class="postSearchSubmitBtn" onclick="postPostList()">목록</button>
            </form>
        </div>
        <button type="button" onclick="writingPost()">글 작성</button>
    </div>

    <%------------------------------------ 검색 조건이 없을 경우의 페이지네이션  ------------------------------------%>
    <div class="pagination">
        <ul class="paginationList">
            <c:if test="${!postList.first}">
                <li>  <%-- 첫번째 페이지로 이동하는 버튼 --%>
                    <a class="firstPageOpenBtn" href="?page=0"><i class="fa-solid fa-angles-left"></i></a>
                </li>

                <li>  <%-- 이전 페이지로 이동하는 버튼 --%>
                    <a class="pastPageBtn" href="?page=${postList.number -1}"><i class="fa-solid fa-angle-left"></i></a>
                </li>
            </c:if>

            <%-- 페이지 생성  --%>

            <c:if test="${postEndPage >= 0}">
                <c:forEach var="i" begin="${postStartPage}" end="${postEndPage}">
                    <li class="paginationNum">
                        <a class="<c:if test='${postNowPage == i}'>active</c:if>" href="?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
            </c:if>

            <c:if test="${!postList.last}">
                <li>  <%-- 다음 페이지로 이동하는 버튼 --%>
                    <a class="nextPageBtn" href="?page=${postList.number +1}"><i class="fa-solid fa-angle-right"></i></a>
                </li>
                <li>  <%-- 마지막 페이지로 이동하는 버튼 --%>
                    <a class="lastPageBtn" href="?page=${postList.totalPages -1}"><i class="fa-solid fa-angles-right"></i></a>
                </li>
            </c:if>
        </ul>
    </div>
</main>
<script src="/js/post/mainPostList.js"></script>
<jsp:include page="../layout/footer.jsp"/>