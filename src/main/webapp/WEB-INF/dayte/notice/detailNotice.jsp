<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>

<title>공지사항 | 본문</title>

<link rel="stylesheet" href="/css/notice/detailNotice.css">

</head>

<body>
<%@include file="../layout/header.jsp" %>

<div class="wrapper">

    <div class="btns listBtn">
        <a href="/notice?page=${page}">목록</a>
    </div>
    <form>
        <div>
            <div class="noticeTitle">
                <input type="text" id="title" value="${notice.title}" readonly>
            </div>
            <div class="noticeContent">
                  <div class="content">
                      ${notice.content}
                  </div>
            </div>
        </div>

        <c:if test="${not empty notice.files}">
            <fieldset>
                <legend>첨부파일</legend>
                <c:forEach var="file" items="${notice.files}">
                    <a href="/notice/${notice.no}/files/${file.id}">
                        <img src="/images/clipIcon.png" alt="첨부파일">
                            ${file.originalName}
                    </a>
                    <br>
                </c:forEach>
            </fieldset>
        </c:if>

    </form>

    <div class="btns">
        <sec:authorize access="hasRole('ADMIN')">
            <a href="/update/${notice.no}">수정</a>
        </sec:authorize>
        <a id="cancelBtn">뒤로가기</a>
    </div>

    <div class="moveContents">
        <c:if test="${!empty prevId}">
            <div><a href="/viewNotice/${prevId}">이전 글로 가기 | <span>${prevTitle}</span> </a></div>
            <div class="divine"></div>
        </c:if>
        <c:if test="${!empty nextId}">
            <div><a href="/viewNotice/${nextId}">다음 글로 가기 | <span>${nextTitle}</span> </a></div>
        </c:if>
    </div>

</div>


<script>
    $(document).ready(() => {
        $("#cancelBtn").click(() => {
            window.history.back();
        })
    })

</script>


<%@include file="../layout/footer.jsp" %>