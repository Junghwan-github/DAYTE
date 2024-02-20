<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/adminHead.jsp"%>
<!-- 콘텐츠 -->

<main>
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5">
        <p class="h2 ps-2 titleName border-bottom pb-2 mb-4" >콘텐츠 목록</p>

        <!-- 컨텐츠 요약 통계 -->
        <div class="d-flex flex-row justify-content-start">
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4 ">총콘텐츠수</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${arrangedList.get("all")}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4 ">숙소</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${arrangedList.get("accommodations")}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4">맛집</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${arrangedList.get("restaurant")}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4">카페</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${arrangedList.get("cafe")}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4">이벤트</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${arrangedList.get("event")}</li>
            </ul>
            <ul class="list-unstyled border rounded d-flex flex-row me-2">
                <li class="border-end bg-secondary-subtle m-0 p-2 rounded-start fw-semibold  h4">삭제됨</li>
                <li  class="m-0 py-2 px-3 bg-light rounded-end fw-semibold  h4">${arrangedList.get("deleted")}</li>
            </ul>
        </div>

        <!-- 컨텐츠 검색 -->
        <div class="mb-2 d-flex flex-row align-items-center justify-content-between">

            <form id="uSearch" name="uSearch" method="get" action="/admin/home/settings/contents">
                <div class="list-group d-flex flex-row">
                    <a href="/admin/home/settings/contents" class="btn btn-dark px-3 py-2 m-0 me-2 adminUserFS">전체 목록</a>
                    <select name="field" id="field" class="list-item me-1 adminUserFS">
                        <option value="all" <c:if test="${contentsDTO.field eq 'all'}">selected</c:if> >전체</option>
                        <option value="businessName" <c:if test="${contentsDTO.field eq 'businessName'}">selected</c:if> >이름</option>
                        <option value="category" <c:if test="${contentsDTO.field eq 'category'}">selected</c:if> >카테고리</option>
                        <option value="gu" <c:if test="${contentsDTO.field eq 'gu'}">selected</c:if> >구/군</option>
                        <option value="keyword" <c:if test="${contentsDTO.field eq 'keyword'}">selected</c:if> >키워드</option>
                    </select>
                    <input type="text" name="word" id="word" class="list-item me-1 adminUserFS">
                    <input type="submit" class="btn-submit btn btn-dark px-3 py-2 m-0 me-2 adminUserFS" value="검색">
                    <button type="button" class="btn-submit btn btn-dark deletedShow px-3 py-2 m-0 me-2 adminUserFS" value="deleted">삭제된 콘텐츠 보기</button>
                </div>
            </form>
            <div>
                <a href="/admin/home/settings/contents/add" class="btn btn-dark px-3 py-2 m-0 me-2 adminUserFS">콘텐츠 등록</a>
            </div>
        </div>

        <!-- 회원 조회 -->
        <div class="table-responsive">
            <table class="table table-striped border" >
                <thead class="align-middle">
                <tr style="height: 50px;">
                    <th scope="col" class="text-center align-middle bg-secondary-subtle h4">No</th>
                    <th scope="col" class="text-center align-middle bg-secondary-subtle h4">아이디</th>
                    <th scope="col" class="text-center align-middle bg-secondary-subtle h4">이름</th>
                    <th scope="col" class="text-center align-middle bg-secondary-subtle h4">카테고리</th>
                    <th scope="col" class="text-center align-middle bg-secondary-subtle h4">키워드</th>
                    <th scope="col" class="text-center align-middle bg-secondary-subtle h4">관리</th>
                </tr>
                </thead>
                <c:if test="${!empty contentsList}">
                    <tbody class="align-middle">
                    <form id="contentsList">
                        <c:forEach var="content" items="${contentsList.content}" varStatus="loop">
                            <tr style="height: 50px;">
                                <td class="text-center align-middle h4 fw-normal">${(loop.index+1)+(contentsList.number*10)}</td>
                                <td class="text-center align-middle h4 fw-normal">${content.uuid}</td>
                                <td class="text-center align-middle h4 fw-normal">${content.businessName}</td>
                                <td class="text-center align-middle h4 fw-normal">${content.category}</td>
                                <td class="text-center align-middle h4 fw-normal">${content.keyword}</td>
                                <td class="text-center align-middle h4 fw-normal">
                                    <div class="d-flex justify-content-center">
                                        <button value="${content.uuid}" class="btn btn-dark ms-1 d-block deleteBtn adminUserFS">
                                            삭제
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </c:if>
            </table>
        </div>

        <!-- 페이지네이션 -->
        <c:if test="${!empty contentsList}">
            <div class="mt-3" >
                <nav aria-label="Page navigation ">
                    <ul class="pagination justify-content-center">
                        <!-- 이전 -->
                        <c:if test="${!contentsList.first}">
                            <li class="page-item">
                                <a class="page-link text-dark adminUserFS" href="?page=0&field=${contentsDTO.field}&word=${contentsDTO.word}">
                                    처음<%--<i class="fa-solid fa-angle-right"></i>--%>
                                </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link text-dark adminUserFS" href="?page=${contentsList.number-1}&field=${contentsDTO.field}&word=${contentsDTO.word}">
                                    &larr;<%--<i class="fa-solid fa-angles-right"></i>--%>
                                </a>
                            </li>
                        </c:if>
                        <!-- 페이지 그룹 -->
                        <c:forEach begin="${contentsDTO.contentsStartPage}" end="${contentsDTO.contentsEndPage}" var="i">
                            <c:choose>
                                <c:when test="${contentsList.pageable.pageNumber+1 == i}">
                                    <li class="page-item disabled">
                                        <a class="page-link px-3 text-dark adminUserFS" href="?page=${i-1}&field${contentsDTO.field}&word=${contentsDTO.word}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link px-3 text-dark adminUserFS" href="?page=${i-1}&field=${contentsDTO.field}&word=${contentsDTO.word}">
                                            ${i}
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <!-- 다음 -->
                        <c:if test="${!contentsList.last}">
                                <li class="page-item ">
                                    <a class="page-link text-dark adminUserFS" href="?page=${contentsList.number+1}&field=${contentsDTO.field}&word=${contentsDTO.word}">
                                        &rarr;<%--<i class="fa-solid fa-angle-left"></i>--%>
                                    </a>
                                </li>
                                <li class="page-item ">
                                    <a class="page-link text-dark adminUserFS" href="?page=${contentsList.totalPages-1}&field=${contentsDTO.field}&word=${contentsDTO.word}">
                                        마지막<%--<i class="fa-solid fa-angles-left"></i>--%>
                                    </a>
                                </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </c:if>
    </div>
</div>
</main>
<script>

    $(".deletedShow").on('click', function (e) {
        e.preventDefault();
        location.href = "/admin/home/settings/contents?field=deleted&word=deleted"
    })

    $('.deleteBtn').on('click', function (e) {
        e.preventDefault();
        console.log($(e.target).val());

        if (!confirm("해당 컨텐츠를 삭제하시겠습니까?")) {
            return;
        }
        fetch("/admin/home/registration/contents/"+$(e.target).val(), {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            }
        }).then(res => {
            return res.json();
        }).then(data => {
            alert(data['data']);
            location.reload();
        }).catch(err => {
            alert(`에러 발생 : ${err.message}`);
        });
    });

</script>

<%-- div.wrapper의 닫힘 태그는 푸터 안에 있음 --%>
<%@include file="../layout/adminFooter.jsp"%>