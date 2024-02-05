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
                    <h2></h2>
                </div>
                <div class="post-items-images">
                    <ul>
                        <li>
                            <div><img src=""></div>
                        </li>
                        <li>
                            <div><img src=""></div>
                        </li>
                        <li>
                            <div><img src=""></div>
                        </li>
                        <li>
                            <div><img src=""></div>
                        </li>
                    </ul>
                </div>
                <div class="post-items-content">
                    <p>
                        테스트오집니다.
                    </p>
                </div>
            </div>
        </div>
            </c:forEach>
    </div>

    <%--    <table border="1">--%>
    <%--        <tr>--%>
    <%--            <th></th>--%>
    <%--            <th>번호</th>--%>
    <%--            <th>제목</th>--%>
    <%--            <th>내용</th>--%>
    <%--            <th>작성일</th>--%>
    <%--            <th>작성자</th>--%>
    <%--        </tr>--%>
    <%--        <c:forEach var="post" items="${postList.content}">--%>
    <%--            <tr>--%>
    <%--                <td>--%>
    <%--                    <img src="/images/1.PNG">--%>
    <%--                </td>--%>
    <%--                <td>${post.id}</td>--%>
    <%--                <td><a href="/post/${post.id}"> ${post.title}</a></td>--%>
    <%--                <td>${post.content}</td>--%>
    <%--                <td><fmt:formatDate value="${post.createDate}" pattern="yyyy-MM-dd"/></td>--%>
    <%--                <td>${post.user.userEmail}</td>--%>
    <%--            </tr>--%>
    <%--        </c:forEach>--%>
    <%--    </table>--%>


    <%------------------------------------ 글 작성 버튼  ------------------------------------%>
    <br>
    <div class="postInsertButtonDiv">
        <form class="postInsertButton" method="get" action="/mainPostList/in">
            <button type="submit">글 작성</button>
        </form>
    </div>
    <br>

    <%------------------------------------ 검색 폼 ------------------------------------%>
    <div class="postSearchForm">
        <form:form action="/post/search" method="get" modelAttribute="searchForm">
            <input type="text" name="postKey"/>
            <input type="submit" value="검색"/>
        </form:form>
    </div>


    <%------------------------------------ 페이지네이션  ------------------------------------%>
    <div class="pagination">

        <ul>
            <li>  <%-- 첫번째 페이지로 이동하는 버튼 --%>
                <a class="firstPageOpenBtn" href="?page=0"><<</a>
            </li>

            <li>  <%-- 이전 페이지로 이동하는 버튼 --%>
                <a class="beforePageBtn" href="${postList.number -1}"><</a>
            </li>

            <%-- 페이지 버튼 생성  --%>
            <c:forEach var="i" begin="${postStartPage}" end="${postEndPage}">
                <li class="paginationNum">
                    <a class="<c:if  test='${postNowPage == i}'>active</c:if>" href="?page=${i}">${i + 1}</a>
                </li>
            </c:forEach>


            <li>  <%-- 다음 페이지로 이동하는 버튼 --%>
                <a class="nextPageBtn" href="${postList.number +1}">></a>
            </li>

            <li>  <%-- 마지막 페이지로 이동하는 버튼 --%>
                <a class="lastPageBtn" href="?page=${postList.totalPages -1}">>></a>
            </li>
        </ul>
    </div>


</main>

<jsp:include page="../layout/footer.jsp"/>