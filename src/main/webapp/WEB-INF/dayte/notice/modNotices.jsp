<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="principal" property="principal"/>
<%@include file="../layout/head.jsp" %>
    <title>공지사항</title>

    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
<%--    <link rel="stylesheet" href="/css/main/modNotice.css">--%>

<style>
    .wrapper{
        width: 1180px;
        margin: 0 auto;
        background: white;
    }
    .title{
        font-size: 2.8rem;
        color: #333;
        padding-top: 20px;
        padding-bottom: 10px;

    }
    .wrapper > form {
        padding: 0;
    }

    /* ============================================ 검색창 */
    .noticeSearchForm {
        margin: 10px 0;
        display: flex;
        flex-wrap: nowrap;
        flex-direction: row;

        width: 254px;
        border: 2px solid #ddd;
        border-radius: 5px;
    }
    .noticeSearchForm > div {
        padding: 0;

    }
    .noticeSearchForm > div > select {
        height: 100%;

        border: transparent;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;

        cursor: pointer;
    }

    .noticeSearchForm > input {
        border: transparent;
    }
    .noticeSearchForm > button {
        padding-top: 3px;

        background-color: transparent;
        border: transparent;

        cursor: pointer;
    }

    .noticeSearchForm > button> img {
        width: 20px;
        height: 20px;
    }
    .noticeSearchForm > button:hover {
        background-color:#eee;

        transition: 0.4s;

        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
    }

    .noticeSearchForm > div > select:focus,
    .noticeSearchForm > input:focus {
        border: transparent;
        outline: transparent;
    }
    /* ======================================== 테이블 */
    img {
        width: 28px;
        height: 28px;
    }

    table {
        width:100%;
        margin-bottom: 30px;
    }

    th {
        font-size: 1.8rem;
        font-weight: 600;
        color: #fff;
        padding: 10px 0;
        background-color: #3d828e;

    }
    td {
        font-size: 1.6rem;
        border-bottom: #ddd 1px solid;
    }

    td > button {

        width: 30px;
        height: 30px;
        padding: 0 2px 0 0;
        margin: 5px;
        border: 2px solid #333;

        background-color: transparent;
    }
    td > button:hover {
        background-color: #eee;

        transition: 0.4s;
    }

    th.NoticeNum, td.truetdNum {
        width : 10%;
    }
    th.NoticeTitle, td.truetdTitle {
        width: 20%;
    }
    td.truetdTitle > a {
        color: #333;
        font-size: 1.6rem;
    }
    td.truetdNum > input {
        /*background-color: pink;*/
        border: transparent;
        text-align: center;

        font-size: 1.6rem;
    }

    table, td, th {
        border-collapse : collapse;
        text-align: center;
        vertical-align: middle;
    }

    /* ============== 버튼*/

    .modBtns {
        border: transparent;
        border-radius: 5px;
        background-color: #333;
        color: #fff;
        padding: 5px 10px;
        cursor: pointer;
        margin-bottom: 10px;
        font-size: 1.6rem;
    }
    input.modBtns:hover {
        background-color: #111;
        transition: 0.4s;
    }

</style>


</head>

<body>
<%@include file="../layout/header.jsp" %>
<script src="/js/main/header.js"></script>

<c:set var="indexNumber" value="${noticeList.number + 2}"/>

<div class="wrapper">
<h1 class="title"> 전체수정 </h1>
<div>
    <a href="/notice/createNotice">
        <button id="new-notice">글쓰기</button>
    </a>
    <br>
    <button id="fixPriority">필독 공지사항 우선순위 저장</button>
</div>
<form action="/notice/searchNoticesAdmin" method="get">
    <div class="noticeSearchForm">
        <div>
            <label for="dropdown"></label>
            <select id="dropdown" name="searchOption">
                <option value="all">전체</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select>
        </div>
        <input type="text" id="searchText" name="searchWord">
        <button id="searchFunction"><img src="/images/searchIconB.png"> </button>
    </div>
</form>


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
                        <a href="/notice/${notice.no}">${notice.title}</a>
                    </td>
                    <td class="truetdDate">
                        <fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/>
                    </td>
                    <td>
                            <button class="upbtn" onclick="moveUp(this,event)"><img src="/images/upIcon2.png"></button>
                    </td>
                    <td>
                            <button class="downbtn" onclick="moveDown(this,event)"><img src="/images/downIcon2.png"></button>
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
    <input type="button" class="modBtns"id="mod-notice" value="수정">
    <input type="hidden" class="modBtns" id="del-notice" value="삭제">
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
                        <button class="upToSelected"  value="${notice.no}" onclick="upToSelected(this.value, event)"><img src="/images/upIcon2.png"></button>
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