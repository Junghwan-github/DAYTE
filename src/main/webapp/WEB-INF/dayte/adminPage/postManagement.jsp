<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="layout/adminHead.jsp" %>
<!-- 콘텐츠 -->
<div class="h-100 px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5">
        <p class="h2 ps-2 fw-bold titleName border-bottom pb-2 mb-4" >게시물 관리</p>

        <!-- 게시물 요약 통계 -->
        <div class="d-flex flex-row justify-content-start">
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4">총 게시글</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold h4">${postList.content.size()}</li>
            </ul>
        </div>

        <!-- 게시물 검색 -->
        <div class="mb-2 d-flex flex-row align-items-center justify-content-between">

            <form id="uSearch" name="uSearch" method="get" action="/admin/post">
                <div class="list-group d-flex flex-row">
                    <a href="/admin/post" class="btn btn-dark px-3 py-2 m-0 me-2 adminUserFS">전체 목록</a>
                    <select name="postField" id="field" class="list-item me-1 adminUserFS">
                        <option value="postAll">전체</option>
                        <option value="postTitle">제목</option>
                        <option value="postContent">내용</option>
                    </select>
                    <input type="text" name="postWord" id="word" class="list-item me-1 adminUserFS">
                    <input type="submit" class="btn-submit btn btn-dark px-3 py-2 m-0 me-2 adminUserFS" value="검색">
                </div>
            </form>
            <div>
                <input type="button" id="delChkPostBtn" class="btn btn-dark px-3 py-2 m-0 me-2 adminUserFS" value="선택 삭제">
            </div>
        </div>

        <!-- 게시글 조회 -->
        <div class="table-responsive">
            <table class="table table-striped border" >
                <thead class="align-middle">
                <tr style="height: 50px;">
                    <th  scope="col" class="text-center align-middle bg-secondary-subtle">
                        <input class="form-check-input" type="checkbox" id="chkAll" >
                    </th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">No</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">제목</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">닉네임</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">등록일</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">관리</th>
                </tr>
                </thead>
                <c:if test="${!empty postList.content}">
                        <tbody class="align-middle">
                        <form id="postList">
                    <c:forEach var="post" items="${postList.content}">
                            <tr style="height: 50px;">
                                <td class="text-center align-middle">
                                <input class="form-check-input chkGrp" name="chkPost" type="checkbox" value="<c:out value='${post.id}'/>">
                                </td>
                                <td class="text-center align-middle h4 fw-normal">
                                    <span>${post.id}</span>
                                </td>
                                <td class="text-center align-middle h4 fw-normal">
                                    <a class="d-block  h-100 w-100 mb-0 text-decoration-none text-dark h4 fw-normal" href="/post/${post.id}">${post.title} [${post.replyList.size()}]</a>
                                </td>
                                <td class="text-center align-middle h4 fw-normal">${post.user.nickName}</td>
                                <td class="text-center align-middle h4 fw-normal"><fmt:formatDate value="${post.createDate}"
                                                                        pattern="yyyy.MM.dd HH:mm:ss"/></td>
                                <td class="text-center align-middle">
                                        <a href="/post/${post.id}" class="btn btn-dark px-0 ms-1 d-block adminUserFS">삭제</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </form>
                        </tbody>
                </c:if>
            </table>
        </div>

        <!-- 페이지네이션 -->
        <c:if test="${!empty postList.content}">
            <div class="mt-3" >
                <nav aria-label="Page navigation ">
                    <ul class="pagination justify-content-center">
                        <!-- 이전 -->
                        <c:choose>
                            <c:when test="${postList.first}"></c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link text-dark adminUserFS" href="?page=0&field=${param.field}&word=${param.word}">처음</a></li>
                                <li class="page-item"><a class="page-link text-dark adminUserFS" href="?page=${postList.number-1}&field=${param.field}&word=${param.word}">&larr;</a></li>
                            </c:otherwise>
                        </c:choose>

                        <!-- 페이지 그룹 -->
                        <c:forEach begin="${postStartPage}" end="${postEndPage}" var="i">
                            <c:choose>
                                <c:when test="${postList.pageable.pageNumber+1 == i}">
                                    <li class="page-item disabled"><a class="page-link px-3 text-dark adminUserFS" href="?page=${i-1}&field=${param.field}&word=${param.word}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link px-3 text-dark adminUserFS" href="?page=${i-1}&field=${param.field}&word=${param.word}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- 다음 -->
                        <c:choose>
                            <c:when test="${postList.last}"></c:when>
                            <c:otherwise>
                                <li class="page-item "><a class="page-link text-dark adminUserFS" href="?page=${postList.number+1}&field=${param.field}&word=${param.word}">&rarr;</a></li>
                                <li class="page-item "><a class="page-link text-dark adminUserFS" href="?page=${postList.totalPages-1}&field=${param.field}&word=${param.word}">마지막</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </c:if>
        </div>
    </div>
</div>

<script>
    $(function() {
        $("#chkAll").click(function(){
            $(".chkGrp").prop("checked", this.checked);
        });
    });
    $("#delChkPostBtn").click(function(){
        let formData = new FormData();
        let lists = [];

        $(".chkGrp:checked").each(function() {
            lists.push($(this).val());
            console.log(lists);
        });
        for(let i = 0; i < lists.length; i++){
            formData.append(i,lists[i]);
        }
        $.ajax({
            type: "DELETE",
            url: "/admin/delPosts",
            data: formData,
            contentType: false,
            processData: false,
        }).done(function(response){
            let message = response["data"];
            alert(message);
            location = "/admin/post";

        }).fail(function(error){
            alert(error);
        })
    })
</script>
<%@include file="layout/adminFooter.jsp" %>