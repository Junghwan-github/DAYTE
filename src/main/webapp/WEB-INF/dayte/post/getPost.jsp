<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%-- head --%>
<%@include file="../layout/head.jsp"%>

<%-- css --%>
<link rel="stylesheet" href="/css/post/post.css">
<link rel="stylesheet" href="/css/post/postReply.css">
<link rel="stylesheet" href="/css/layout/subnav.css">

</head> <%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp"%> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>


<main class="contentArea"> <%-- main --%>

    <div>
        <div class="PostItems">
            <div class="informationBox">
                포스트 번호 : <span id="id"><i>${post.id}</i></span> <br>
                작성자 : <span><i>${post.user.userEmail}</i></span>
            </div>
            <div>
                <c:if test="${post.user.userEmail eq principal.userEmail}">
                    <a href="/post/updatePost/${post.id}" class="btn btn-warning">수정하기</a>
                    <button id="btn-delete" class="btn btn-danger">삭제하기</button>
                </c:if>
            </div>
        </div>

        <div class="title">
            <h1>
                <%--글 제목--%>
                ${post.title}
            </h1>
        </div>
        <div class="content">
        <span><%--글 내용--%>
             ${post.content}
        </span>
        </div>
    </div>
    <%-- 포스트 내용 끝 --%>

    <%-- 댓글 등록 폼 --%>
    <div class="container mt-3">
        <input type="hidden" id="postId" value="${post.id}">
        <table class="table">
            <thead>
            <tr>
                <th>
                    <h3>댓글 목록</h3>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr align="right">
                <td class="ContainerForm">
                    <textarea id="reply-content" rows="1" class="form-control" placeholder="댓글을 입력하세요."></textarea>
                </td>
                <td class="ContainerForm2">
                    <button id="btn-save-reply" class="btn btn-secondary">댓글 등록</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <%-- 댓글 목록 불러오기 구현 --%>
    <c:if test="${!empty postReplyList}">
        <ul id="postReplyView">
            <c:forEach var="reply" items="${postReplyList}">
                <li>
                    <ul>
                        <li>
                            <span>○ 프로필사진</span>
                            <span>${reply.user.nickName}</span>
                            <span>${reply.formatDate}</span>
                        </li>
                        <li> <%-- 햄버거바 --%>
                            <div id="mobile_rnb" >
                                <button type="button" onclick="postReplyBtnListOpen(this)" id="postReplyHamburger" class=""></button>
                                <label for="postReplyHamburger">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </label>
                            </div>

                            <div class="postReplyBtnList">
                                <ul>
                                    <li>
                                        <button id="btn-update-reply" class="replyBtnShow"
                                                onclick="replyObject.updateReply(this.value)" value="${reply.num}">수정
                                        </button>
                                    </li>
                                    <li>
                                        <button id="btn-delete-reply" onclick="replyObject.deleteReply(this.value)"
                                                value="${reply.num}">삭제
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <ul>
                        <li><p class="replyContent">${reply.content}</p></li>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <script src="/js/post/post.js"></script>
    <script src="/js/post/postReply.js"></script>
</main>

<%@include file="../layout/footer.jsp" %>