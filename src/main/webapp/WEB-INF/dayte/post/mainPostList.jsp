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
        <c:set var="postListSize" value="${postListText.size()}"></c:set>
        <c:forEach var="post" items="${postList.content}">
            <c:set var="index" value="${postListSize-1}"></c:set>
            <div class="post-list-items-wrapper">
                <div class="user-part">
                    <ul>
                        <li>
                            <div></div>
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
                        <p>
                            ${postListText[index]}
                        </p>
                    </div>
                </div>
            </div>
            <c:set var="postListSize" value="${index}"></c:set>
        </c:forEach>
    </div>

    <%------------------------------------ 글 작성 버튼  ------------------------------------%>
    <div class="postInsertButtonDiv">
        <form class="postInsertButton" method="get" action="/mainPostList/in">
            <button type="submit">글 작성</button>
        </form>
    </div>

    <%------------------------------------ 검색 폼 ------------------------------------%>
    <div class="postSearchForm">
        <form action="/post/postSearch" method="get">
            <label for="postBordSearchDropDown"></label>
            <select id="postBordSearchDropDown" name="postBordSearchDropDownMenu" >
                <option value="postAll">전체</option>
                <option value="postTitle">제목</option>
                <option value="postContent">내용</option>
            </select>
            <input type="text" name="postSearchInputBox"/>
            <button class="postSearchSubmitBtn">검색</button>
        </form>
    </div>

    <%------------------------------------ 페이지네이션  ------------------------------------%>
    <div class="pagination">
        <ul class="paginationList">
            <c:if test="${!postList.first}">
                <li>  <%-- 첫번째 페이지로 이동하는 버튼 --%>
                    <a class="firstPageOpenBtn" href="?page=0">처음</a>
                </li>

                <li>  <%-- 이전 페이지로 이동하는 버튼 --%>
                    <a class="pastPageBtn" href="?page=${postList.number -1}">이전</a>
                </li>
            </c:if>

            <%-- 페이지 생성  --%>
            <c:if test="${postEndPage >= 0}">
                <c:forEach var="i" begin="${postStartPage}" end="${postEndPage}">
                    <li class="paginationNum">
                        <a class="<c:if  test='${postNowPage == i}'>active</c:if>" href="?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
            </c:if>


            <c:if test="${!postList.last}">
                <li>  <%-- 다음 페이지로 이동하는 버튼 --%>
                    <a class="nextPageBtn" href="?page=${postList.number +1}">다음</a>
                </li>

                <li>  <%-- 마지막 페이지로 이동하는 버튼 --%>
                     <a class="lastPageBtn" href="?page=${postList.totalPages -1}">마지막</a>
                  </li>
             </c:if>
        </ul>
    </div>

</main>

<jsp:include page="../layout/footer.jsp"/>