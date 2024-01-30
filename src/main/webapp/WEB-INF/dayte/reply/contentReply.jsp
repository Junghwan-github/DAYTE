<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ContentReply</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <style>
        .number {
            display: none;
        }
        td {
            text-align: center;
        }
        .comment{
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
        .star-rating {
            display: flex;
            flex-direction: row-reverse;
            justify-content: left;
            text-align: center;
            width: 10px;
        }

        .star-rating input {
            display: none;
        }

        .star-rating label {
            -webkit-text-fill-color: transparent;
            -webkit-text-stroke-width: 1px;
            -webkit-text-stroke-color: #000000;
            cursor: pointer;
        }

        .star-rating :checked ~ label {
            -webkit-text-fill-color: gold;
        }

        .star-rating label:hover,
        .star-rating label:hover ~ label {
            -webkit-text-fill-color: #FFFF00;
        }

        .star{
            font-size: 20px;
        }
        .star-rating{
            width: 110px;
        }
        .modifyBtn{
            text-align: center;
            width: 200px;
        }
        .modifyBtn > *{
            font-size: 14px;
            border: 0;
            background-color: transparent;
            color: #999;
        }
        .Times{
            font-size: 15px;
            color: #666;
        }
        .ReplyStar{
            -webkit-text-fill-color: gold;
            font-weight: bold;
        }

        .star-rating label:hover,
        .star-rating label:hover ~ label {
            -webkit-text-fill-color: #FFFF00;
        }

        .ContStar{

            font-size: 15px;
            font-weight: bold;
            color: gold;
        }

    </style>
</head>
<body>

<h1>ContentReply</h1>

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

                    <td class="Times"><span>${reply.formatDate()}</span></td>
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


<script src="/js/ContentReply.js"></script>

<%@include file="../layout/footer.jsp" %>