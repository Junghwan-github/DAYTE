<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/adminHead.jsp" %>
<!-- 콘텐츠 -->
<div class="h-100  px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5 position-relative">
        <p class="h2 ps-2 fw-bold titleName border-bottom pb-2">회원 수정</p>
        <form class="w-100" style="margin-top:50px;">
            <table class="table border">
                <tr>
                    <th><label for="userEmail" class="ps-3 adminUserFS">아이디</label></th>
                    <td><input type="text" class="adminUserFS" name="userEmail" id="userEmail" value="${user.userEmail}" disabled></td>
                    <th><label for="password" class="ps-3 adminUserFS">비밀번호</label></th>
                    <td><input type="password" class="adminUserFS" name="password" id="password" value=""></td>
                </tr>
                <tr>
                    <th><label for="userName" class="ps-3 adminUserFS">이름</label></th>
                    <td><input type="text" class="adminUserFS" name="userName" id="userName" value="${user.userName}" disabled></td>
                    <th><label for="nickName" class="ps-3 adminUserFS">닉네임</label></th>
                    <td><input type="text" class="adminUserFS" name="nickName" id="nickName" value="${user.nickName}"></td>
                </tr>
                <tr>
                    <th><label for="phone" class="ps-3 adminUserFS">휴대전화번호</label></th>
                    <td><input type="text" class="adminUserFS" name="phone" id="phone" value="${user.phone}"></td>
                    <th><label for="joinDate" class="ps-3 adminUserFS">회원가입일</label></th>
                    <td><input type="text" class="adminUserFS" name="joinDate" id="joinDate" value="${user.joinDate}" disabled></td>
                </tr>
                <tr>
                    <th><label for="social" class="ps-3 adminUserFS">소셜 로그인</label></th>
                    <td><input type="text" class="adminUserFS" name="social" id="social" value="" disabled></td>
                    <th><label for="gender" class="ps-3 adminUserFS">성별</label></th>
                    <td><input type="text" class="adminUserFS" name="gender" id="gender" value="${user.gender}" disabled></td>
                </tr>
                <tr>
                    <th><label for="birthDate" class="ps-3 adminUserFS">생년월일</label></th>
                    <td><input type="text" class="adminUserFS" name="birthDate" id="birthDate" value="${user.birthDate}" disabled></td>
                    <th><label for="role" class="ps-3 adminUserFS">권한</label></th>
                    <td>
                        <select name="role" id="role" class="list-item adminUserFS" style="padding-right: 70px;">
                            <option value="USER" <c:if test="${user.role == 'USER'}">selected</c:if>> USER</option>
                            <option value="ADMIN" <c:if test="${user.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
                        </select>
                    </td>

                </tr>
                <tr>
                    <th><label for="latelyDate" class="ps-3 adminUserFS">최근접속일</label></th>
                    <td><input type="text" class="adminUserFS" name="latelyDate" id="latelyDate" value="${user.loginDate}" disabled></td>
                    <th><label for="del" class="ps-3 adminUserFS">탈퇴여부</label></th>
                    <td><input type="text" class="adminUserFS" name="del" id="del" value="${user.del}" disabled></td>
                </tr>
                <tr>
                    <th><label for="userImage" class="ps-3 adminUserFS">회원이미지</label></th>
                    <td><input type="file" class="adminUserFS" name="userImage" id="userImage" accept="image/gif, image/png, image/jpeg"></td>
                    <th><label for="black" class="ps-3 adminUserFS">제제</label></th>
                    <td>
                        <select name="black" id="black" class="list-item adminUserFS" style="padding-right: 70px;">
                            <option value="없음" selected>없음</option>
                            <option value="1일" >1일</option>
                            <option value="2일" >2일</option>
                            <option value="1주" >1주</option>
                            <option value="2주" >2주</option>
                            <option value="1달" >1달</option>
                            <option value="2달" >2달</option>
                            <option value="6개월" >6개월</option>
                            <option value="1년" >1년</option>
                            <option value="영구" >영구</option>
                        </select>
                    </td>
                </tr>
            </table>


        </form>
        <div class="position-absolute d-flex flex-row" style="top:40px; right:30px;">
            <a href="/admin/user" class="btn btn-dark px-3 ms-1 d-block adminUserFS">목록</a>
            <button type="button" id="editUserBtn" class="btn btn-dark px-3 ms-1 d-block adminUserFS">확인</button>
        </div>

    </div>
</div>

<script src="/js/admin/editUser.js"></script>
<%-- div.wrapper의 닫힘 태그는 푸터 안에 있음ㅇ --%>
<%@include file="../layout/adminFooter.jsp" %>