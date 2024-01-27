<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../layout/head.jsp"%>
<div class="titleDiv">
<h3>게시물 목록</h3>
</div>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>제목</th>
            <th>작성일</th>
            <th>작성자</th>
        </tr>
        <c:forEach var="post" items="${postList.content}">
            <tr>
                <td>${post.id}</td>
                <td><a href="/post/${post.id}"> ${post.title}</a></td>
                <td><fmt:formatDate value="${post.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${post.user.userEmail}</td>
            </tr>
        </c:forEach>
    </table>
            <%------------------------------------ 글 작성 버튼  ------------------------------------%>
            <br>
                    <form class="button" method="get" action="/mainPostList/in">
                        <button type="submit">글 작성</button>
                    </form>
            <br>

            <%------------------------------------ 페이지네이션  ------------------------------------%>
            <ul class="pagination justify-content-center">
                <li class="page-item <c:if test="${postList.first}">disabled</c:if>">
                    <a class="page-link" href="?page=${postList.number -1}">이전</a>
                </li>
                <li class="page-item <c:if test="${postList.last}">disabled</c:if>">
                    <a class="page-link" href="?page=${postList.number +1}">다음</a>
                </li>
            </ul>
        </div>
</div>
<br>
<jsp:include page="../layout/footer.jsp"/>

</div>