<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/head.jsp"%>

 <title>공지사항</title>


    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/css/main/notice.css">


</head>

<body>

<%@include file="../layout/header.jsp"%>
<script src="/js/main/header.js"></script>
<c:set var="indexNumber" value="${noticeList.number + 2}"/>

<h1 class="title"> 공지사항 </h1>
<br>
<sec:authorize access="hasRole('ADMIN')">

    <div>
        <a href="/notice/modAll">
        <input type="button" id="modAll" value="전체수정">
        </a>
    </div>


</sec:authorize>

<form action="/notice/searchNotices" method="get">
    <label for="dropdown"></label>
    <select id="dropdown" name="searchOption">
        <option value="all">전체</option>
        <option value="title">제목</option>
        <option value="content">내용</option>
    </select>
    <input type="text" id="searchText" name="searchWord">
    <button id="searchFunction"><img src="/images/searchIcon.png"> </button>
</form>


<div id="divform">
    <c:if test="${!empty noticeList}">
        <table id="noticeTable">
            <thead>
            <tr>

                <th class="thNum">번호</th>
                <th class="thTitle">제목</th>
                <th class="thWriter">작성자</th>
                <th class="thDate">게시일</th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="notice" items="${noticeList.content}">

                <tr <c:if test="${notice.priority > 0}"> class="priorityNotZero" </c:if> >
                    <td class="tdNum">${notice.no}</td>
                    <td class="tdTitle"><a href="/notice/${notice.no}">${notice.title}</a></td>
                    <td class="tdWriter">관리자</td>
                    <td class="tdDate"><fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/></td>
                </tr>


            </c:forEach>

            <br>


            </tbody>
        </table>


        <div class="pagination">
            <ul>
                <c:if test="${!noticeList.first}">
                <li>
                    <a class="pageDoubleMoveBtn" href="?page=0">
                        <img src="/images/doublePrevious.png"/>
                        처음으로
                    </a>
                </li>
                <li>
                    <a class="pageMoveBtn" href="?page=${noticeList.number - 1}">
                        <img src="/images/previous.png"/>
                        이전
                    </a>
                </li>
                </c:if>


                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <li>
                        <a class="page-link" href="?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>

                <c:if test="${!noticeList.last}">
                <li>
                    <a class="pageMoveBtn" href="?page=${noticeList.number + 1}">
                        <img src="/images/next.png"/>
                        다음
                    </a>
                </li>
                <li>
                    <a class="pageDoubleMoveBtn" href="?page=${noticeList.totalPages-1}">
                        <img src="/images/doubleNext.png"/>
                        마지막으로
                    </a>
                </li>
                </c:if>
            </ul>

        </div>

    </c:if>

</div>



<%@include file="../layout/footer.jsp"%>