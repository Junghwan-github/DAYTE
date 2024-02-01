<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/adminHead.jsp"%>
<!-- 콘텐츠 -->
<div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
    <div class="px-5 position-relative">
        <p class="h4 fw-bold mt-1 mb-3" >회원 수정</p>
        <form class="w-100 ">
            <table class="table border">
                <tr>
                    <th><label for="userEmail" class="ps-3">아이디</label></th>
                    <td><input type="text" name="userEmail" id="userEmail" value="${user.userEmail}" disabled></td>
                    <th><label for="password" class="ps-3">비밀번호</label></th>
                    <td><input type="password" name="password" id="password" value="${user.password}"></td>
                </tr>
                <tr>

                    <th><label for="userName" class="ps-3">이름</label></th>
                    <td><input type="text" name="userName" id="userName"  value="${user.userName}"></td>
                    <th><label for="nickName" class="ps-3">닉네임</label></th>
                    <td><input type="text" name="nickName" id="nickName"  value="${user.nickName}"></td>
                </tr>
                <tr>
                    <th><label for="phone" class="ps-3" >휴대전화번호</label></th>
                    <td><input type="text" name="phone" id="phone"  value="${user.phone}"></td>
                    <th><label for="joinDate" class="ps-3">회원가입일</label></th>
                    <td><input type="text" name="joinDate" id="joinDate"  value="${user.joinDate}" disabled></td>
                </tr>
                <tr>
                    <th><label for="social" class="ps-3" >소셜 로그인</label></th>
                    <td><input type="text" name="social" id="social"  value="" disabled></td>
                    <th><label for="gender" class="ps-3">성별</label></th>
                    <td><input type="text" name="gender" id="gender"  value="${user.gender}" disabled></td>
                </tr>
                <tr>
                    <th><label for="birthDate" class="ps-3">생년월일</label></th>
                    <td><input type="text" name="birthDate" id="birthDate" value="${user.birthDate}" disabled></td>
                    <th><label for="role" class="ps-3">권한</label></th>
                    <td>
                        <select name="role" id="role" class="list-item" style="padding-right: 70px;">
                            <option value="USER" <c:if test="${user.role == 'USER'}">selected</c:if>> USER</option>
                            <option value="ADMIN" <c:if test="${user.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
                            <option value="DORMANCY"  <c:if test="${user.role == 'DORMANCY'}">selected</c:if>>DORMANCY</option>
                            <option value="BLOCK" <c:if test="${user.role == 'BLOCK'}">selected</c:if>>BLOCK</option>
                        </select>
                    </td>
                    <!-- <th><label for="userIP" class="ps-3">IP</label></th>
                    <td><input type="text" name="userIP" id="userIP" value=""></td> -->
                </tr>
                <tr>
                    <th><label for="latelyDate" class="ps-3">최근접속일</label></th>
                    <td><input type="text" name="latelyDate" id="latelyDate" value=""></td>
                    <th><label for="del" class="ps-3">탈퇴여부</label></th>
                    <td><input type="text" name="del" id="del" value="${user.del}"></td>
                </tr>
                <tr>
                    <th><label for="userImage" class="ps-3">회원이미지</label></th>
                    <td><input type="text" name="userImage" id="userImage"></td>
                </tr>
            </table>


        </form>
        <div class="position-absolute" style="top:0px; right:50px;">
            <a href="" class="btn btn-dark py-1" onclick="history.back()">목록</a>
            <button type="button" id="editUserBtn" class="btn btn-dark py-1">확인</button>
        </div>

    </div>
</div>





<script src="/js/admin/editUser.js"></script>
<%-- div.wrapper의 닫힘 태그는 푸터 안에 있음ㅇ --%>
<%@include file="../layout/adminFooter.jsp"%>