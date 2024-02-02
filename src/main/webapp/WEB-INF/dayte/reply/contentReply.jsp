<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>--%>
<%@include file="../layout/head.jsp"%>
<link rel="stylesheet" href="/css/schedule/contentsReply.css">
</head>
<body>

<%-- 댓글 작성 부분 --%>
<div class="container mt-3">
    <input type="hidden" id="postId">
    <table class="table">


        <tbody class="commentBody">

        <div class="star-rating space-x-4 mx-auto">
            <input type="radio" id="5-stars" name="rating" value="5" v-model="ratings"/>
            <label for="5-stars" class="star pr-4">★</label>
            <input type="radio" id="4-stars" name="rating" value="4" v-model="ratings"/>
            <label for="4-stars" class="star">★</label>
            <input type="radio" id="3-stars" name="rating" value="3" v-model="ratings"/>
            <label for="3-stars" class="star">★</label>
            <input type="radio" id="2-stars" name="rating" value="2" v-model="ratings"/>
            <label for="2-stars" class="star">★</label>
            <input type="radio" id="1-star" name="rating" value="1" v-model="ratings" />
            <label for="1-star" class="star">★</label>
        </div>

        <tr align="right">
            <td class="form-container">
                <textarea id="reply-content" rows="1" class="form-control" ></textarea>
                <button id="btn-save-reply" class="btn btn-secondary" >댓글 등록</button>

            </td>
        </tr>
        </tbody>

    </table>


    <br><br>
</div>
<%--댓글 목록 불러오기 구현--%>
<c:if test="${!empty contentReplyList}">
    <div class="container mb-3 ">
        <table class="table">
            <tbody>
            <c:forEach var="reply" items="${contentReplyList}">
                <tr>
                <tr></tr><td></td><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
                <td class="ReplyUser">${reply.user.nickName} <span class="ContStar">★ <span style="color: black
">${reply.rating}점</span></span></td>
                <tr>

                    <td class="star-rating">
                        <c:forEach begin="1" end="${reply.rating}">
                            <span class="ReplyStar">★ </span>
                        </c:forEach>
                    </td>

                    <%--<td class="Times"><span>${reply.createDate()}</span></td>--%>
                </tr>

                <tr>

                    <td class="comment">${reply.content}</td>

                    <td class="modifyBtn">
                        <c:if test="${reply.user != null}">
                    <td><button onclick="replyObject.deleteReply(${reply.num})">삭제</button></td>
                    <td><button onclick="replyObject.updateReply(${reply.num}, '${reply.content}')">수정</button></td>
                        </c:if>
                    </td>

                </tr>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>


<script src="/js/contentReply/contentReply.js"></script>

<%@include file="../layout/footer.jsp" %>