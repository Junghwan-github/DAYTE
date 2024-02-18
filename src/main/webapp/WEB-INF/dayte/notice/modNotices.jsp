<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="principal" property="principal"/>
<%@include file="../layout/head.jsp" %>
<title>공지사항 | 수정</title>

<link rel="stylesheet" href="/css/notice/modNotice.css">

</head>

<body>
<%@include file="../layout/header.jsp" %>

<c:set var="indexNumber" value="${noticeList.number + 2}"/>

<div class="wrapper">
    <h1 class="title"> 전체수정 </h1>
    <div class="formBtnArray">
        <form action="/notice/modAll" method="get">
            <div class="noticeSearchForm">
                <div>
                    <label for="searchOption"></label>
                    <select id="searchOption" name="searchOption">
                        <option value="all">전체</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <input type="text" id="searchWord" name="searchWord">
                <button id="searchFunction"><img src="/images/searchIconB.png"></button>
            </div>
        </form>
        <div>
            <a href="/notice/createNotice">
                <button>글쓰기</button>
            </a>
            <button id="fixPriority">필독 공지사항 우선순위 저장</button>
        </div>
    </div>


    <%-- 테이블 --%>
    <div id="trueNotice">
        <c:if test="${!empty trueNotices}">
            <table id="trueNoticeTable">
                <tbody>
                <tr>
                    <th class="NoticeNum">번호</th>
                    <th class="NoticeTitle">제목</th>
                    <th class="NoticeDate">게시일</th>
                    <th class="upPriority">필독 up</th>
                    <th class="downPriority">필독 down</th>
                    <th class="changeToDefaultNotice">일반 공지로 변경</th>
                </tr>

                <c:forEach var="notice" items="${trueNotices}" varStatus="loopStatus">
                    <tr class="selected">
                        <td class="truetdNum">
                            <input type="text" name="truetdNo" value="${notice.no}">
                        </td>
                        <td class="truetdTitle">
                            <a href="/viewNotice/${notice.no}">${notice.title}</a>
                        </td>
                        <td class="truetdDate">
                            <fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/>
                        </td>
                        <td>
                            <button class="upbtn" onclick="moveUp(this,event)"><img src="/images/topIcon.png"></button>
                        </td>
                        <td>
                            <button class="downbtn" onclick="moveDown(this,event)"><img src="/images/downIcon.png">
                            </button>
                        </td>
                        <td>
                            <button class="goToDefault" value="${notice.no}" onclick="resetPriority(this.value, event)">
                                <img src="/images/XIcon.png"></button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


        </c:if>

    </div>


    <div id="modDel-button" class="btnArray">
        <input type="hidden" class="modBtns" id="del-notice" value="삭제">
        <input type="button" class="modBtns" id="mod-notice" value="수정">

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
                        <td class="truetdTitle"><a href="/viewNotice/${notice.no}">${notice.title}</a></td>
                        <td class="tdDate"><fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/></td>
                        <td>
                            <input type="checkbox"
                            <c:if test="${notice.viewCheck == true}"> checked</c:if> class="viewCheckbox"
                                   id="${notice.no}">
                        </td>
                        <td>
                            <button class="upToSelected" value="${notice.no}" onclick="upToSelected(this.value, event)">
                                <img src="/images/topIcon.png"></button>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>


            <div class="pagination">
                <c:if test="${msg == 'default'}">
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

                    <c:if test="${endPage >= 0}">
                        <c:forEach var="i" begin="${startPage}" end="${endPage}">
                            <li class="paginationNum">
                                <a class="<c:if test="${defaultNotices.number == i}">active</c:if>"
                                   href="?page=${i}">${i + 1}</a>
                            </li>
                        </c:forEach>
                    </c:if>
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
                </c:if>
                <c:if test="${msg == 'searched'}">
                    <ul>
                        <c:if test="${!defaultNotices.first}">
                            <li>
                                <a class="pageDoubleMoveBtn" href="?page=0&amp;searchOption=${param.searchOption}&amp;searchWord=${param.searchWord}">
                                    <img src="/images/doublePrevious.png"/>
                                    처음으로
                                </a>
                            </li>
                            <li>
                                <a class="pageMoveBtn" href="?page=${defaultNotices.number - 1}&amp;searchOption=${param.searchOption}&amp;searchWord=${param.searchWord}">
                                    <img src="/images/previous.png"/>
                                    이전
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${endPage >= 0}">
                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                <li class="paginationNum">
                                    <a class="<c:if test="${defaultNotices.number == i}">active</c:if>"
                                       href="?page=${i}&amp;searchOption=${param.searchOption}&amp;searchWord=${param.searchWord}">${i + 1}</a>
                                </li>
                            </c:forEach>
                        </c:if>

                        <c:if test="${!defaultNotices.last}">
                            <li>
                                <a class="pageMoveBtn" href="?page=${defaultNotices.number + 1}&amp;searchOption=${param.searchOption}&amp;searchWord=${param.searchWord}">
                                    <img src="/images/next.png"/>
                                    다음
                                </a>
                            </li>
                            <li>
                                <a class="pageDoubleMoveBtn" href="?page=${defaultNotices.totalPages-1}&amp;searchOption=${param.searchOption}&amp;searchWord=${param.searchWord}">
                                    <img src="/images/doubleNext.png"/>
                                    마지막으로
                                </a>
                            </li>
                        </c:if>
                    </ul>

                </c:if>
            </div>


        </c:if>

    </div>
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

<%@include file="../layout/footer.jsp" %>