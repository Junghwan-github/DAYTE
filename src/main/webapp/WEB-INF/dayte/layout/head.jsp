<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


    <!--    제이쿼리-->
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>

    <!-- Popper -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js"></script>

    <!-- 아이콘 -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script src="https://kit.fontawesome.com/8ccf456cfd.js" crossorigin="anonymous"></script>

    <!-- 부트스트랩 -->
<%--    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>--%>
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">--%>


    <!--    디폴트 css-->
    <link rel="stylesheet" href="/css/layout/header.css">
    <link rel="stylesheet" href="/css/layout/reset.css">
    <link rel="stylesheet" href="/css/layout/footer.css">
    <link rel="stylesheet" href="/css/layout/subnav.css">




    <!-- 슬라이더 -->
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

    <!-- 카카오맵 -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d6e1eeae5c7853c6bd1f34a4ef04e664"></script>

    <!-- 써머노트 -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.js"></script>

    <script defer src="/js/main/header.js"></script>