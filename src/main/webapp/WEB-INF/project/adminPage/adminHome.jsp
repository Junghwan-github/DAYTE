<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        .wrap_container {
            width: 100vw;
            height: 100vh;
            display: block;
        }
        header {
            height: 20%;
            background-color: #dddddd;
        }
        aside {
            height: 80%;
            width: 20%;

            background-color: #eee;
            float: left;
        }
        main {
            float: right;
            height: 80%;
            width: 80%;
            background-color: #ccc;
        }
    </style>
</head>
<body>
<div class="wrap_container">
    <!-- 헤더 -->
    <header>
        <h1 id="logo"><a href="/">Project T</a></h1>
        상단
    </header>
    <!--  사이드바 -->
    <aside>
        <nav>
            <ul>
                <li>메뉴1</li>
                <li>메뉴2</li>
                <li>메뉴3</li>
                <li>메뉴4</li>
                <li>메뉴5</li>
            </ul>
        </nav>
    </aside>
    <!-- 메인 -->
    <main>메인</main>
</div>
</body>
</html>