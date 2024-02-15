<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
    <input type="hidden" id="post-id" value="${post.id}"/>
    <div class="get-post-detail">
        <div class="postItems">
            <div class="menu-button">
                <div>
                    <a href="/mainPostList" id="post-main-list-btn">목록</a>
                </div>
                <c:if test="${post.user.userEmail eq principal.userEmail}">
                    <span class="material-symbols-outlined" id="post-modify-delete-btn">more_vert</span>
                    <div id="post-modify-delete-menu">
                        <ul>
                            <li><a href="/post/updatePost/${post.id}" id="post-modify"><i class="xi-pen-o"></i>수정</a>
                            </li>
                            <li><a href="#" id="post-delete"><i class="xi-trash-o"></i>삭제</a></li>
                        </ul>
                    </div>
                </c:if>
            </div>
            <div class="user-part">
                <ul>
                    <li>
                        <div><img src="${post.user.profileImagePath}"></div> <!-- 프로필 사진 -->
                    </li>
                    <li><span>${post.user.nickName}</span></li>
                    <li><span><fmt:formatDate value="${post.createDate}" pattern="yyyy.MM.dd"/></span></li>
                </ul>
                <p class="post-number">포스트 번호 : ${post.id}</p>
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
            <%-- 댓글 폼 감추기 버튼 --%>
            <div class="post-reply-write-btn">
                <button type="button" id="reply-show-hide-btn"><i class="xi-comment"></i>댓글 쓰기</button>
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
            <hr>
            <%-- 댓글 목록 불러오기 구현 --%>
            <c:if test="${!empty postReplyList}">
                <div class="reply-wrapper">
                    <c:forEach var="reply" items="${postReplyList}">
                        <div class="post-reply-items">
                            <div class="user-part-reply">
                                <ul>
                                    <li>
                                        <div><img src="${reply.user.profileImagePath}"></div>
                                    </li>
                                    <li><span>${reply.user.nickName}</span></li>
                                    <li><span><fmt:formatDate value="${reply.createDate}"
                                                              pattern="yyyy.MM.dd"/></span>
                                    </li>
                                </ul>
                            </div>
                            <div class="post-reply-content">
                                <p class="changeTextarea">${reply.content}</p>
                            </div>

                            <div class="reply-sub-nav">
                                <c:if test="${reply.user.userEmail eq principal.userEmail}">
                                    <span class="material-symbols-outlined post-reply-modify-delete-btn">more_vert</span>
                                    <div class="reply-sub-nav-plate">
                                        <ul>
                                            <li>
                                                <button type="button" class="replyBtnShow"><i class="xi-pen-o"></i>수정
                                                </button>
                                                <button type="button"  class="checkButton" value="${reply.num}"><i class="xi-check"></i>확인
                                                </button>
                                            </li>
                                            <li>
                                                <button type="button" class="btn-delete-reply" value="${reply.num}"><i class="xi-trash-o"></i>삭제
                                                </button>
                                                <button type="button" class="cancelButton"><i class="xi-close"></i>취소
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</main>
<script src="/js/post/getPost.js"></script>
<script src="/js/post/post.js"></script>
<script src="/js/post/postReply.js"></script>
<%@include file="../layout/footer.jsp" %>