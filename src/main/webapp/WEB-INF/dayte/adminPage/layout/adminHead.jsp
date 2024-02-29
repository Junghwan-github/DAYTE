<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <link href="/css/layout/reset.css" rel="stylesheet">
    <!-- 부트스트랩 css cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <%-- 제이쿼리 --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <style>
        .shadow-light {
            box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
        }

        .bg-title {
            background-image: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
        }
        .select-style{
            border: 1px solid #ccc;

        }
        select:hover {
            background-color: #efefef;
            transition: 0.2s;
            cursor: pointer;
        }
        .form-check-input{
            width: 1.6rem;
            height: 1.6rem;
            border: var(--bs-border-width) solid #777;
        }
        .adminUserFS{
            font-size: 1.4rem;
        }


    </style>

</head>
<body>


    <!-- 네비 -->

    <nav class="navbar fixed-top navbar-expand-lg bg-success-subtle d-flex justify-content-between">

        <div class="container-fluid">
            <div class=" d-flex align-items-center">
                <button class="btn ms-3" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">
                    <img src="/images/icon-hamburger.png" style="width: 32px;height:32px;" alt="">
                </button>
                <p class="mb-0 ms-3">
                    <a href="/" class="navbar-brand text-decoration-none fw-bold" style="font-size:2.3rem;">
                        DAYTE
                    </a>
                </p>
            </div>
            <div class="me-2">
                <ul class="d-flex flex-row mb-0 me-3" >
                    <li ><a href="/admin/home" class="text-decoration-none text-reset fw-semibold" style="font-size:1.8rem;">관리자 메인</a></li>
                    <li class="text-reset fw-semibold mx-2" style="font-size:1.8rem;"> / </li>
                    <li ><span class="fw-bold text-body-tertiary" style="font-size:1.8rem;" id="nowPageTitle"></span></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- 사이드바  -->
    <div class="offcanvas offcanvas-start  bg-success-subtle" data-bs-scroll="true" data-bs-backdrop="true" tabindex="-1" id="offcanvasScrolling"
         aria-labelledby="offcanvasScrollingLabel" style="width:301px">
        <div class="flex-shrink-0 p-0 " style="width: 300px;">
            <div class="d-flex justify-content-end ">
                <button type="button" class="btn-close mt-2 me-2" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class= "offcanvas-header d-flex justify-content-center" >
                <a href="/admin/home" class="d-flex flex-column align-items-center pb-3 mb-3 link-body-emphasis text-decoration-none border-bottom">
                    <img src="/images/logo_dayte.png"  style="width:100px; border:transparent; border-radius:10px;">
                    <p class="offcanvas-title h1" id="offcanvasScrollingLabel">DAYTE</p>
                </a>
            </div>

            <div class="offcanvas-body p-0">
                <div class="text-center pb-3">
                    <span><a href="/members/logout" class="text-reset text-decoration-none h3 pe-2">로그아웃</a></span>
                    <div class="vr "></div>
                    <span><a href="/" class="text-reset text-decoration-none h3 ps-2">사이트</a></span>
                </div>
                <ul class="list-unstyled ps-0 " id="sideVarList">
                    <li class="border-top border-secondary-subtle my-3"></li>
                    <li class="mb-2">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 fw-bold ps-3"  >
                            <i class="bi bi-kanban-fill ms-4 h3"></i>
                            <a href="/admin/home" class="link-body-emphasis d-inline-flex text-decoration-none rounded ms-4 h3">대시 보드</a>
                        </button>
                    </li>
                    <li class="mb-2">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed ps-3" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                            <i class="bi bi-gear-fill ms-4 h3"></i>
                            <span class="fw-bold d-inline-block ms-4 h3">환경 설정</span>
                        </button>
                        <div class="collapse" id="orders-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small bg-light ps-5">
                                <li><a href="/admin/home/settings/index" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">메인 설정</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="mb-2">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed fw-bold ps-3" data-bs-toggle="collapse" data-bs-target="#user-collapse" aria-expanded="true">
                            <i class="bi bi-person-fill ms-4 h3"></i>
                            <span class="fw-bold d-inline-block ms-4 h3">회원 관리</span>
                        </button>
                        <div class="collapse " id="user-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small bg-light ps-5">
                                <li><a href="/admin/user" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">회원 관리</a></li>
                                <li><a href="/admin/totalVisitor" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">방문자 집계</a></li>
                                <li><a href="/admin/loginUser" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">접속자 현황</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="mb-2">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed fw-bold ps-3" data-bs-toggle="collapse" data-bs-target="#board-collapse" aria-expanded="false">
                            <i class="bi bi-clipboard2-data-fill ms-4 h3"></i>
                            <span class="fw-bold d-inline-block ms-4 h3">게시판 관리</span>
                        </button>
                        <div class="collapse " id="board-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small bg-light ps-5 ">
                                <li><a href="/admin/post" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">게시물 관리</a></li>
                                <li><a href="/admin/home/settings/contents" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">콘텐츠 관리</a></li>
                                <li><a href="/notice/modAll" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">공지사항 관리</a></li>
                                <li><a href="/customerService" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1">1:1 문의설정</a></li>

                                <li><a href="/customerService" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1 d-none">FAQ관리</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1 d-none">인기검색어관리</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1 d-none">인기검색어순위</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded h4 py-1 d-none">글,댓글 현황</a></li>
                            </ul>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    <!-- 사이드바 닫는 태그-->
    </div>
    <div class="container-fluid bg-body-tertiary wrapper" style="height: 100vh; overflow-y:scroll;" >
    <!-- wrapper 닫는 태그는 footer에 있음-->