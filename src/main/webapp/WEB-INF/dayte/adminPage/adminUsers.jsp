<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="layout/adminHead.jsp"%>
<!-- 콘텐츠 -->
<div class="h-100  px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5">
        <p class="h2 ps-2 titleName border-bottom pb-2 mb-4" >회원 관리</p>

        <!-- 회원 요약 통계 -->
        <div class="d-flex flex-row justify-content-start">
            <ul class="list-unstyled border rounded d-flex flex-row me-2" >
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4">총회원수</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${allList.totalElements}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold h4">탈퇴</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold h4">${delList.totalElements}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold h4">정지</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold h4">${bList.totalElements}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold h4">휴면</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold h4">${dList.totalElements}</li>
            </ul>
        </div>

        <!-- 회원 검색 -->
        <div class="mb-2 d-flex flex-row align-items-center justify-content-between">

            <form id="uSearch" name="uSearch" method="get" action="/admin/user">
                <div class="list-group d-flex flex-row">
                    <a href="/admin/user" class="btn btn-dark px-3 py-2 m-0 me-2 adminUserFS">전체 목록</a>
                    <select name="field" id="field" class="list-item me-1 adminUserFS">
                        <option value="userEmail">아이디</option>
                        <option value="nickName">닉네임</option>
                        <option value="userName">이름</option>
                        <option value="role">권한</option>
                        <option value="phone">휴대전화번호</option>
                    </select>
                    <input type="text" name="word" id="word" class="list-item me-1 adminUserFS">
                    <input type="submit" class="btn-submit btn btn-dark px-3 py-2 m-0 me-2 adminUserFS" value="검색">
                </div>
            </form>
            <div>
                <input type="button" id="delChkUserBtn" class="btn btn-dark px-3 py-2 m-0 me-2 adminUserFS" value="선택 삭제">
            </div>
        </div>

        <!-- 회원 조회 -->
        <div class="table-responsive">
            <table class="table table-striped border" >
                <thead class="align-middle">
                <tr style="height: 50px;">
                    <th scope="col" class="text-center align-middle bg-secondary-subtle">
                        <input class="form-check-input" type="checkbox" id="chkAll" >
                    </th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">아이디</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">이름</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">닉네임</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">휴대전화번호</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">성별</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">가입일</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">권한</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">탈퇴</th>
                    <th scope="col" class="text-center align-middle h4 bg-secondary-subtle">관리</th>
                </tr>
                </thead>
                <c:if test="${!empty ulist}">
                    <tbody class="align-middle">
                    <form id="userList">
                    <c:forEach var="user" items="${ulist.content}">
                        <tr style="height: 50px;">
                            <td class="text-center align-middle">
                                <input class="form-check-input chkGrp" name="chkUsers" type="checkbox" value="<c:out value='${user.userEmail}'/>" >
                            </td>
                            <td class="text-center align-middle h4 fw-normal">${user.userEmail}</td>
                            <td class="text-center align-middle h4 fw-normal">${user.userName}</td>
                            <td class="text-center align-middle h4 fw-normal">${user.nickName}</td>
                            <td class="text-center align-middle h4 fw-normal">${user.phone}</td>
                            <td class="text-center align-middle h4 fw-normal">${user.gender}</td>
                            <td class="text-center align-middle h4 fw-normal"><fmt:formatDate value="${user.joinDate}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                            <td class="text-center align-middle h4 fw-normal">${user.role}</td>
                            <td class="text-center align-middle h4 fw-normal">${user.del}</td>
                            <td class="text-center align-middle ">
                                <a href="/admin/editUser/${user.userEmail}" class="btn btn-dark px-1 ms-1 d-block adminUserFS">수정</a>

                            </td>
                        </tr>
                    </c:forEach>
                    </form>
                    </tbody>
                    <caption class="adminUserFS">회원 수 : ${ulist.totalElements}</caption>
                </c:if>
            </table>
        </div>

        <!-- 페이지네이션 -->
        <c:if test="${!empty ulist}">
            <div class="mt-3" >
                <nav aria-label="Page navigation ">
                    <ul class="pagination justify-content-center">
                        <!-- 이전 -->
                        <c:choose>
                            <c:when test="${ulist.first}"></c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link text-dark adminUserFS" href="?page=0&field=${param.field}&word=${param.word}">처음</a></li>
                                <li class="page-item"><a class="page-link text-dark adminUserFS" href="?page=${ulist.number-1}&field=${param.field}&word=${param.word}">&larr;</a></li>
                            </c:otherwise>
                        </c:choose>

                        <!-- 페이지 그룹 -->
                        <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                            <c:choose>
                                <c:when test="${ulist.pageable.pageNumber+1 == i}">
                                    <li class="page-item disabled"><a class="page-link px-3 text-dark adminUserFS" href="?page=${i-1}&field=${param.field}&word=${param.word}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link px-3 text-dark adminUserFS" href="?page=${i-1}&field=${param.field}&word=${param.word}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- 다음 -->
                        <c:choose>
                            <c:when test="${ulist.last}"></c:when>
                            <c:otherwise>
                                <li class="page-item "><a class="page-link text-dark adminUserFS" href="?page=${ulist.number+1}&field=${param.field}&word=${param.word}">&rarr;</a></li>
                                <li class="page-item "><a class="page-link text-dark adminUserFS" href="?page=${ulist.totalPages-1}&field=${param.field}&word=${param.word}">마지막</a></li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </nav>
            </div>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("#chkAll").click(function(){
            $(".chkGrp").prop("checked", this.checked);
        });
    });
    $("#delChkUserBtn").click(function(){
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
            type: "PUT",
            url: "/members/delUsers",
            data: formData,
            contentType: false,
            processData: false,
        }).done(function(response){
                let message = response["data"];
                alert(message);
                location = "/admin/home";

        }).fail(function(error){
            alert(error);
        })
    })



</script>

<%-- div.wrapper의 닫힘 태그는 푸터 안에 있음ㅇ --%>
<%@include file="layout/adminFooter.jsp"%>