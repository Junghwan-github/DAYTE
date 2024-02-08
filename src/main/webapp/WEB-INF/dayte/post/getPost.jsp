<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css --%>
<link rel="stylesheet" href="/css/post/getPost.css">
<title>일정후기 | ${post.title} </title>
</head> <%-- /head --%>

<body> <%-- body --%>
<%-- header --%>
<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>

<main> <%-- main --%>
    <div class="get-post-detail">
        <div class="PostItems">
            <div class="menu-button">
                <div><span class="material-symbols-outlined">
arrow_back_ios_new
</span>뒤로가기
                </div>
                <span class="material-symbols-outlined">
more_vert
</span>
            </div>
            <%--            <div class="information-wrapper">--%>
            <%--                <p>포스트 번호 : ${post.id}</p>--%>
            <%--                <p>작성자 : ${post.user.nickName}</p>--%>
            <%--            </div>--%>
            <%--            <div>--%>
            <%--                <c:if test="${post.user.userEmail eq principal.userEmail}">--%>
            <%--                                        <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>--%>
            <a href="/post/updatePost/${post.id}" class="btn btn-warning">
                <button class="btn btn-warning">수정하기</button>
            </a>
            <%--                                        <button id="btn-delete" class="btn btn-danger">삭제하기</button>--%>
            <%--                </c:if>--%>
            <%--            </div>--%>
        </div>
    </div>
    <div class="post-content-container">
        <div class="title">
            <h1>
                <%--글 제목--%>
                ${post.title}
            </h1>
        </div>
        <div class="content">
            <%--글 내용--%>
            ${post.content}
        </div>
        <%-- 포스트 내용 끝 --%>
        <div class="post-reply-write-btn">
            <button type="button" class="reply-show-hide-btn"><i class="xi-comment"></i>댓글 쓰기</button>
        </div>
        <%-- 댓글 등록 폼 --%>
        <div class="post-content-reply-container">
            <input type="hidden" id="postId" value="${post.id}">
            <div class="post-content-reply-wrapper">
                <div class="ContainerForm">
                    <textarea id="reply-content" rows="1" class="form-control" placeholder="댓글을 입력하세요."></textarea>
                </div>
                <div class="ContainerForm2">
                    <button id="btn-save-reply" class="btn-secondary">댓글 등록</button>
                </div>
            </div>
        </div>

        <%-- 댓글 목록 불러오기 구현 --%>
        <c:if test="${!empty postReplyList}">
        <div class="reply-wrapper">
            <div id="postReplyView">
                <c:forEach var="reply" items="${postReplyList}">
                    <ul class="post-reply-user-info">
                        <li>
                            <div class="post-user-profile-image"></div>
                        </li>
                        <li>
                            <span>${reply.user.nickName}</span>
                        </li>
                        <li>
                            <span>${reply.formatDate}</span>
                        </li>
                    </ul>
                    <div class="reply-sub-nav">
                            <%-- 햄버거바 --%>

                    </div>
                    <ul class="postReplyBtnList">
                        <li>
                            <button id="btn-update-reply" class="replyBtnShow"
                                <%--onclick="replyObject.updateReply(this.value)" value="${reply.num}">수정--%>
                                    onclick="updateBtnClick(this)">수정
                            </button>
                        </li>
                        <li>
                            <button id="btn-delete-reply" onclick="replyObject.deleteReply(this.value)"
                                    value="${reply.num}">삭제
                            </button>
                        </li>
                    </ul>
                    <p class="changeTextarea">${reply.content}</p>
                    <%--                    <button type="button" style="display: none" class="checkButton" onclick="checkButtonClick(this)" >확인</button>--%>
                    <button type="button" style="display: none" class="checkButton"
                            onclick="replyObject.checkButtonClick(this.value)" value="${reply.num}">확인
                    </button>
                    <button type="button" style="display: none" class="cancelButton"
                            onclick="cancelButtonClick(this)">
                        취소
                    </button>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>
    </c:if>

    <script src="/js/post/post.js"></script>
    <script src="/js/post/postReply.js"></script>
</main>

<%@include file="../layout/footer.jsp" %>