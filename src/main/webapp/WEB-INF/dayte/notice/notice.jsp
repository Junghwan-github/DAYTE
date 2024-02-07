<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/head.jsp" %>

<title>DAYTE | 공지사항</title>


<link rel="stylesheet" href="/css/main/notice.css">

</head>

<body>

<%@include file="../layout/header.jsp" %>
<div class=wrapper>
    <c:set var="indexNumber" value="${noticeList.number + 2}"/>

    <h1 class="title"> 공지사항 </h1>
    <div class="formBtnArray">
    <form action="/notice" method="get">
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
    <sec:authorize access="hasRole('ADMIN')">
        <div>
            <a href="/notice/modAll">
                <input type="button" id="modAll" value="전체수정">
            </a>
            <a href="/auth/logout">
                <button>로그아웃</button>
            </a>
        </div>
    </sec:authorize>
    </div>


    <div id="divform" class="tableContainer">
        <c:if test="${!empty noticeList}">
            <table id="noticeTable">
                <tbody>
                <tr class="noticeTrStyle ">
                    <th class="thStyle">번호</th>
                    <th class="thStyle thTitle">제목</th>
                    <th class="thStyle thDate">게시일</th>
                </tr>
                <c:forEach var="notice" items="${noticeList.content}">
                    <tr class="noticeTrStyle listHover <c:if test='${notice.priority > 0}'>priorityNotZero</c:if>">
                        <td class="tdStyle">${notice.no}</td>
                        <td class="tdStyle"><a href="/notice/${notice.no}?page=${currentPage}" class="tdTitle">${notice.title}</a></td>
                        <td class="tdStyle"><fmt:formatDate value="${notice.createDate}" pattern="yy-MM-dd"/></td>
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

            <c:if test="${endPage >= 0}">
                    <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <li class="paginationNum">
                            <a class="<c:if  test='${currentPage == i}'>active</c:if>"
                               href="?page=${i}">${i + 1}</a>
                        </li>
                    </c:forEach>
            </c:if>

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
</div>

<%@include file="../layout/footer.jsp" %>