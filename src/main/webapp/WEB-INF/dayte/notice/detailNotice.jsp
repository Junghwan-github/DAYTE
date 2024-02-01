<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>

<title>공지사항</title>

    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
    <link  rel="stylesheet" href="/css/notice/detailNotice.css">

</head>

<body>
<%@include file="../layout/header.jsp"%>
<script src="/js/main/header.js"></script>

<div class="wrapper">
    <h1> 상세보기 </h1>
        <form>
            <div>
                <div class="noticeTitle" >
                    <input type="text" id="title" value="${notice.title}" readonly >
                </div>
                <div class="noticeContent">
                  <textarea id="content" readonly>
                      ${notice.content}
                  </textarea>
                </div>
            </div>

            <c:forEach var="file" items="${notice.files}">
                <fieldset>
                    <legend>첨부파일</legend>

                    <a href="/notice/${notice.no}/files/${file.id}">
                        <img src="/images/clipIcon.png" alt="첨부파일">
                            ${file.originalName}
                    </a>
                </fieldset>
            </c:forEach>
        </form>

        <div class="btns">
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/update/${notice.no}" >수정</a>
            </sec:authorize>
            <a  id="cancelBtn">뒤로가기</a>
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
    $(document).ready(()=> {
        $("#cancelBtn").click(()=>{
            window.history.back();
        })
    })
</script>


<%--<script src="/js/notice/cancel.js"></script>--%>

<%@include file="../layout/footer.jsp"%>