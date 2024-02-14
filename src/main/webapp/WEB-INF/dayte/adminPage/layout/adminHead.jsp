<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <!-- 부트스트랩 css cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <%-- 제이쿼리 --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>


</head>
<body>

<div class="container-fluid vh-100 wrapper">
    <!-- 네비 -->

    <nav class="navbar fixed-top navbar-expand-lg bg-success-subtle d-flex justify-content-between">

        <div class="container-fluid">
            <div class=" d-flex align-items-center">
                <button class="btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">
                    <img src="/images/icon-hamburger.png" style="width: 32px;height:32px;" alt="">
                </button>
                <span class="navbar-brand mb-0 h1 fw-bold">DAYTE</span>
            </div>
            <div class="me-2"aria-label="breadcrumb">
                <ul class="breadcrumb mb-0 " >
                    <li class="breadcrumb-item "><a href="/admin/home" class="text-decoration-none text-reset fw-semibold">관리자 메인</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><span class="h6 fw-bold" id="nowPageTitle"></span></li>
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
                <a href="/admin/home" class="d-flex align-items-center pb-3 mb-3 link-body-emphasis text-decoration-none border-bottom">
                    <h4 class="offcanvas-title" id="offcanvasScrollingLabel">DAYTE</h4>
                </a>
            </div>

            <div class="offcanvas-body p-0">
                <div class="text-center pb-3">
                    <span><a href="/members/logout" class="text-reset text-decoration-none">로그아웃</a></span>
                    <div class="vr"></div>
                    <span><a href="/" class="text-reset text-decoration-none">사이트</a></span>
                </div>
                <ul class="list-unstyled ps-0 " id="sideVarList">
                    <li class="border-top border-secondary-subtle my-3"></li>
                    <li class="mb-1">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed fw-bold ps-3" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                            <img src="/images/chevron-down.svg" style="width: 16px; height:16px; " class="me-1" alt="">
                            환경 설정
                        </button>
                        <div class="collapse" id="orders-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small bg-light ps-4">
                                <li><a href="/admin/home/settings/index" class="link-body-emphasis d-inline-flex text-decoration-none rounded">메인 설정</a></li>
                                <li><a href="/admin/home/settings/contents" class="link-body-emphasis d-inline-flex text-decoration-none rounded">컨텐츠 관리</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">설정</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">설정</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="mb-1">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed fw-bold ps-3" data-bs-toggle="collapse" data-bs-target="#user-collapse" aria-expanded="true">
                            <img src="/images/chevron-down.svg" style="width: 16px; height:16px; " class="me-1" alt="회원 관리 메뉴 보기">
                            회원 관리
                        </button>
                        <div class="collapse " id="user-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small bg-light ps-4">
                                <li><a href="/admin/home" class="link-body-emphasis d-inline-flex text-decoration-none rounded">회원 관리</a></li>
                                <li><a href="/admin/view" class="link-body-emphasis d-inline-flex text-decoration-none rounded">접속자 집계</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">접속자 검색</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="mb-1">
                        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed fw-bold ps-3" data-bs-toggle="collapse" data-bs-target="#board-collapse" aria-expanded="false">
                            <img src="/images/chevron-down.svg" style="width: 16px; height:16px; " class="me-1" alt="">
                            게시판 관리
                        </button>
                        <div class="collapse " id="board-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small bg-light ps-4 ">
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">게시판 관리</a></li>
                                <li><a href="/notice/modAll" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">공지사항 관리</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">인기검색어관리</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">인기검색어순위</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">1:1 문의설정</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">FAQ관리</a></li>
                                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded ">글,댓글 현황</a></li>
                            </ul>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    </div>