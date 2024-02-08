<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header>
    <!-- 네비게이션 -->
    <nav>
        <h1 id="logo"><a href="/">Project T</a></h1>
        <ul>
            <li><a href="/notice">공지사항</a></li>
            <li><a href="/mainPostList">일정후기</a></li>
            <li><a href="#">고객센터</a></li>
            <c:choose>
                <c:when test="${principal == null}">
                    <li><a href="/members/login">로그인</a></li>
                </c:when>
                <c:otherwise>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/admin/home">관리자</a></li>
                    </sec:authorize>
                    <li class="userProfile">
                        <a href="#">
                            <div class="user-profile-images">
                                <img src="${principal.getProfileImagePath()}"/>
                            </div>
                                ${principal.getNickName()} 님
                             <i class="xi-heart"></i>
                        </a>
                    </li>
                    <ul class="sub-nav-slide-bar">
                        <li><a href="/members/editForm">내 정보</a></li>
                        <li><a href="/schedule/scheduleList">내 일정관리</a></li>
                        <li><a href="/myReview">내 리얼리뷰</a></li>
                        <li><a href="/members/logout">로그아웃</a></li>
                    </ul>

                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>