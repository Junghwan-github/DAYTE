<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="principal" property="principal"/>


<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>

    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/modNotice.css">


</head>

<body>
<c:set var="indexNumber" value="${noticeList.number + 2}"/>

<h1 class="title"> 전체수정 </h1>
<br>
<a href="/notice/createNotice">
    <button id="new-notice">글쓰기</button>
</a>
<br>
<button id="fixPriority">필독 공지사항 우선순위 저장</button>

<form action="/notice/searchNoticesAdmin" method="get">
    <label for="dropdown"></label>
    <select id="dropdown" name="searchOption">
        <option value="all">전체</option>
        <option value="title">제목</option>
        <option value="content">내용</option>
    </select>
    <input type="text" id="searchText" name="searchWord">
    <button id="searchFunction"><img src="/images/searchIcon.png"> </button>
</form>



<div id="trueNotice">
    <c:if test="${!empty trueNotices}">
        <table id="trueNoticeTable">
            <thead>
            <tr>
                <th class="NoticeNum">번호</th>
                <th class="NoticeTitle">제목</th>
                <th class="NoticeDate">게시일</th>
                <th class="upPriority">필독 up</th>
                <th class="downPriority">필독 down</th>
                <th class="changeToDefaultNotice">일반 공지로 변경</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="notice" items="${trueNotices}" varStatus="loopStatus">
                <tr class="selected">
                    <td class="truetdNum">
                        <input type="text" name="truetdNo" value="${notice.no}">
                    </td>
                    <td class="truetdTitle">
                        <a href="/notice/${notice.no}">${notice.title}</a>
                    </td>
                    <td class="truetdDate">
                        <fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/>
                    </td>
                    <td>
                            <button class="upbtn" onclick="moveUp(this,event)"><img src="/images/upbtn.png"></button>
                    </td>
                    <td>
                            <button class="downbtn" onclick="moveDown(this,event)"><img src="/images/downbtn.png"></button>
                    </td>
                    <td>
                        <button class="goToDefault" value="${notice.no}" onclick="resetPriority(this.value, event)"><img src="/images/x-button.jpg"> </button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>



    </c:if>

</div>

<div id="modDel-button">
    <input type="button" id="mod-notice" value="수정">
    <input type="hidden" id="del-notice" value="삭제">

</div>

<div id="defaultNotice">
    <c:if test="${!empty defaultNotices}">
        <table id="defaultNoticeTable">
            <thead>
            <tr>
                <th class="NoticeNum">번호</th>
                <th class="NoticeTitle">제목</th>
                <th class="NoticeDate">게시일</th>
                <th class="viewCheck">뷰 단에서 보일건가</th>
                <th class="grantPriority">필독 공지사항으로 등록</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="notice" items="${defaultNotices.content}">
                <tr>
                    <td class="tdNum">${notice.no}</td>
                    <td class="truetdTitle"><a href="/notice/${notice.no}">${notice.title}</a></td>
                    <td class="tdDate"><fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/></td>
                    <td>
                        <input type="checkbox" <c:if test="${notice.viewCheck == true}"> checked</c:if> class="viewCheckbox" id="${notice.no}">
                    </td>
                    <td>
                        <button class="upToSelected"  value="${notice.no}" onclick="upToSelected(this.value, event)"><img src="/images/upbtn.png"></button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>




        <div class="pagination">
            <ul>
                <c:if test="${!defaultNotices.first}">
                <li>
                    <a class="pageDoubleMoveBtn" href="?page=0">
                        <img src="/images/doublePrevious.png"/>
                        처음으로
                    </a>
                </li>
                <li>
                    <a class="pageMoveBtn" href="?page=${defaultNotices.number - 1}">
                        <img src="/images/previous.png"/>
                        이전
                    </a>
                </li>
                </c:if>


                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <li>
                        <a class="page-link <c:if test="${defaultNotices.number == i}"> nowPage</c:if>" href="?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
                <c:if test="${!defaultNotices.last}">
                <li>
                    <a class="pageMoveBtn" href="?page=${defaultNotices.number + 1}">
                        <img src="/images/next.png"/>
                        다음
                    </a>
                </li>
                <li>
                    <a class="pageDoubleMoveBtn" href="?page=${defaultNotices.totalPages-1}">
                        <img src="/images/doubleNext.png"/>
                        마지막으로
                    </a>
                </li>
                </c:if>
            </ul>

        </div>


    </c:if>

</div>

<script src="/js/notice/modPriority.js"></script>
<script src="/js/notice/resetPriority.js"></script>
<script src="/js/notice/grantPriority.js"></script>
<script src="/js/notice/rangeNoticeList.js"></script>
<script src="/js/notice/checkNoticeList.js"></script>
<script src="/js/notice/delNotice.js"></script>
<script src="/js/notice/viewCheck.js"></script>
<%--
<script src="/js/notice/searchNotices.js"></script>
--%>


</body>

</html>