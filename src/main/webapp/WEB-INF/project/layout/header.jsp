<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header>
    <!-- 네비게이션 -->
    <nav>
        <h1 id="logo"><a href="/">Project T</a></h1>
        <ul>

            <li><a href="notice">공지사항</a></li>

            <c:choose>
                <c:when test="${principal == null}">
                    <li><a href="/members/login">로그인</a></li>
                </c:when>
                <c:otherwise>
                    <li class="userProfile"><a href="#">${principal.getNickName()}님 <i class="xi-heart"></i></a></li>

                    <sec:authorize access="hasRole('ADMIN')" >

                        <li><a href="/admin/home">관리자 페이지</a></li>
                    </sec:authorize>


                    <li class="loginHamburger">
                        <div id="mobile_rnb" >
                            <input type="checkbox" id="hamburger" />
                            <label for="hamburger">
                                <span></span>
                                <span></span>
                                <span></span>
                            </label>
                            <div class="sidebar">
                                <ul>
                                    <li><a href="/schedule/scheduleList">일정관리</a></li>
                                    <li><a href="#">메뉴2</a></li>
                                    <li><a href="#">메뉴3</a></li>
                                    <li><a href="#">메뉴4</a></li>
                                    <li><a href="/members/logout">로그아웃</a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>