<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>

<title>공지사항</title>

<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
<link rel="stylesheet" href="/css/notice/detailNotice.css">

</head>

<body>
<%@include file="../layout/header.jsp" %>
<script src="/js/main/header.js"></script>

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
                  <div id="content">
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
            <div><a href="/notice/${prevId}">이전 글로 가기 | <span>${prevTitle}</span> </a></div>
            <div class="divine"></div>
        </c:if>
        <c:if test="${!empty nextId}">
            <div><a href="/notice/${nextId}">다음 글로 가기 | <span>${nextTitle}</span> </a></div>
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


<%--<script src="/js/notice/cancel.js"></script>--%>

<%@include file="../layout/footer.jsp" %>