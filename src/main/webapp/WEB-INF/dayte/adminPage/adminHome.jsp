<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="layout/adminHead.jsp" %>
<!-- 콘텐츠 -->
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="h-100 px-5">
        <p class="h2 ps-2 titleName border-bottom pb-2">대시 보드</p>
        <div class="row mt-3 p-0" style="height: 45%;">
            <div class="w-100 col bg-body mx-3 mb-2 p-0 border rounded border-2 border-light-subtle shadow-light">
                <a href="/admin/view" class="d-block text-decoration-none w-100 border-bottom px-4 py-3 h3 bg-success-subtle" >
                    주간 방문자 현황
                </a>
                <div class="d-flex justify-content-center">
                    <div style="width:75%; height: 70%;">
                        <canvas id="myChart"></canvas>
                    </div>
                    <select class="select" hidden>
                        <option value="1" selected>1주일</option>
                    </select>
                </div>

            </div>
            <div class="col bg-body h-100 mx-3 mb-2 p-0 border rounded border-2 border-light-subtle  shadow-light">
                <a href="/admin/user"class="d-block text-decoration-none w-100 mb-0 border-bottom px-4 py-3 h3 bg-success-subtle" >
                    신규 회원
                </a>
                <div class="py-0" style="height:85%">
                    <div class="table-responsive h-100 overflow-y-scroll" >
                        <table class="mb-0 table table-striped">
                            <tbody>
                            <c:forEach var="user" items="${recentUsers}">
                                <tr>
                                    <td class="text-center col-4 h4 py-4">${user.userEmail}</td>
                                    <td class="text-center col-4 h4 py-4">${user.nickName}</td>
                                    <td class="text-center col-4 h4 py-4"><fmt:formatDate value="${user.joinDate}"
                                                        pattern="yyyy.MM.dd HH:mm:ss"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-3 p-0" style="height: 45%;">
            <div class="w-100 h-100 col bg-body mx-3 mb-2 p-0 border rounded border-2 border-light-subtle shadow-light">
                <a href="/mainPostList"class="d-block text-decoration-none w-100 mb-0 border-bottom px-4 py-3 h3 bg-success-subtle" >
                    신규 게시글
                </a>
                <div class="py-0" style="height:85%">
                    <div class="table-responsive h-100 overflow-y-scroll">
                        <table class="mb-0 table table-striped">
                                <tbody>
                                <c:forEach var="post" items="${recentPosts}">
                                    <tr>
                                        <td class="text-center col-4 h4 py-4">${post.title}</td>
                                        <td class="text-center col-4 h4 py-4">${post.user.nickName}</td>
                                        <td class="text-center col-4 h4 py-4"><fmt:formatDate value="${post.createDate}"
                                                            pattern="yyyy.MM.dd HH:mm:ss"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                        </table>
                    </div>
                </div>
            </div>
<%--            <div class="w-100 col bg-body mx-3 mb-2 p-0 border rounded border-2 border-light-subtle shadow-light">--%>
<%--                뭠--%>
<%--            </div>--%>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#chkAll").click(function () {
            $(".chkGrp").attr("checked", this.checked);
        });
    });
    $("#delChkUserBtn").click(function () {
        let formData = new FormData();
        let lists = [];

        $(".chkGrp:checked").each(function () {
            lists.push($(this).val());
            console.log(lists);
        });
        for (let i = 0; i < lists.length; i++) {
            formData.append(i, lists[i]);
        }
        $.ajax({
            type: "PUT",
            url: "/members/delUsers",
            data: formData,
            contentType: false,
            processData: false,
        }).done(function (response) {
            let message = response["data"];
            alert(message);
            location = "/admin/home";

        }).fail(function (error) {
            alert(error);
        })
    })


</script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/admin/visitors.js"></script>
<%-- div.wrapper의 닫힘 태그는 푸터 안에 있음ㅇ --%>
<%@include file="layout/adminFooter.jsp" %>