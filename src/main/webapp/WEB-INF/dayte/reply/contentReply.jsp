<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>--%>
<%@include file="../layout/head.jsp" %>
<c:set var="contentUuid" value="${uuid}"/>
<link rel="stylesheet" href="/css/schedule/contentReply.css">
</head>
<body>
<%@include file="../layout/header.jsp" %>
<%@include file="../layout/subnav.jsp" %>
<main>
    <%-- 댓글 작성 부분 --%>
    <h1 class="reply-title">리얼 리뷰 등록</h1>
    <div class="content-star-reply-container">
        <input type="hidden" id="contentUuid" value="${contentUuid}">

        <div class="past-schedule-reply-contents">
            <ul>
                <li class="contents">
                    <div class="contentsImg">
                        <img src="${showContentsDetail.adminContentsImageList[0].imageURL}">
                    </div>
                    <ul class="contentsInfo">
                        <li class="category-keyword">
                            <span>${showContentsDetail.category}</span><span>${showContentsDetail.keyword}</span></li>
                        <li class="businessName">${showContentsDetail.businessName}</li>
                        <li class="address">${showContentsDetail.detailedAddress}</li>
                        <div class="contents-content">
                            <p>${showContentsDetail.detailedDescription}</p>
                        </div>
                    </ul>

                </li>
            </ul>
        </div>


        <div class="star-rating space-x-4 mx-auto">

            <input type="radio" id="5-stars" name="rating" value="5"/>
            <label for="5-stars" class="star pr-4">★</label>
            <input type="radio" id="4-stars" name="rating" value="4"/>
            <label for="4-stars" class="star">★</label>
            <input type="radio" id="3-stars" name="rating" value="3"/>
            <label for="3-stars" class="star">★</label>
            <input type="radio" id="2-stars" name="rating" value="2"/>
            <label for="2-stars" class="star">★</label>
            <input type="radio" id="1-star" name="rating" value="1"/>
            <label for="1-star" class="star">★</label>
            <h2>별점</h2>
        </div>

        <c:choose>
            <c:when test="${msg == 'updateReply'}">
                            <textarea id="reply-content" rows="1"
                                      class="form-control">${contentReply.content}</textarea>
                <button type="button" id="btn-update" class="btn btn-secondary" onclick="updateReply()">댓글 수정</button>
            </c:when>
            <c:otherwise>
                <textarea id="reply-content" rows="1" class="form-control"></textarea>
                <button id="btn-save-reply" class="btn btn-secondary">리뷰 등록</button>
            </c:otherwise>
        </c:choose>

    </div>
</main>

<%--댓글 목록 불러오기 구현--%>
<%--<c:if test="${!empty contentReplyList}">
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

                    &lt;%&ndash;<td class="Times"><span>${reply.createDate()}</span></td>&ndash;%&gt;
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
</c:if>--%>


<script src="/js/contentReply/contentReply.js"></script>
<script src="/js/contentReply/updateReply.js"></script>

<%@include file="../layout/footer.jsp" %>