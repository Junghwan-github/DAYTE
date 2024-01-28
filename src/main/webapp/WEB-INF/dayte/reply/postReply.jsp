<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>PostReply</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <style>
        td {
            text-align: center;
        }
        .content{
            text-align: start;
        }
        h4{
            text-align: start;
        }
        .form-container {
            display: flex;
            align-items: center;
        }
        .form-control{
            width: 380px;
            height: 30px;
            margin-right: 10px;
            resize: none; /* 크기 변경 비활성화 */
        }
        .btn-secondary{
            height: 35px;
            width: 100px;
        }
        .commentBody{
            display: flex;
            justify-content: center;
            align-items: center;
        }

    </style>
</head>
<body>

<h1>PostReply</h1>

<%-- 댓글 작성 부분 --%>
<form>
<div class="container mt-3">
<%--    <c:set property="post" value="${postReplyList.post}" />--%>
    <input type="hidden" id="post" value="${post.id}" />

    <table class="table">
        <thead>
        <tr>
            <th><h4>댓글 목록</h4></th>
        </tr>
        </thead>
        <tbody class="commentBody">
        <tr align="right">
            <td class="form-container">
                <%-- 댓글 입력 --%>
                <textarea id="reply-postReply" rows="1" class="form-control"></textarea>

            </td>
        </tr>
        </tbody>
    </table>
    <br><br>
</div>
</form>
<button id="btn-save-postReply" class="btn-secondary">댓글 등록</button>
<%--댓글 목록 불러오기 구현--%>
<c:if test="${!empty postReplyList}">
    <div class="container mb-3 ">
        <table class="table">
            <thead>
            <tr>
                <th width="100">작성자</th>
                <th width="100">작성날짜</th>
                <th></th>
                <th></th>
                <th width="200">내용</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="postReply" items="${postReplyList}">
                <tr>
                    <td>${postReply.user.nickName}</td>
                    <td>${postReply.formatDate}</td>
                    <td></td>
                    <td></td>
                    <td class="content">${postReply.content}</td>

                    <c:if test="${postReply.user.userName != null}">
                        <td><button onclick="replyObject.deleteReply(${postReply.num})">삭제</button></td>
                        <td><button onclick="replyObject.updateReply(${postReply.num}, '${postReply.content}')">수정</button></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>


<script src="/js/PostReply.js"></script>

<%@include file="../layout/footer.jsp" %>