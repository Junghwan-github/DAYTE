<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/adminHead.jsp" %>
<!-- 콘텐츠 -->
<style>
    .upload {
        position: relative;
        padding-top: 70px;
        min-width:375px;
    }

    input, textarea {
        width: 100%;
        border: 1px solid #ddd;
        height: 50px;
        padding: 5px 10px;
        margin: 10px 0 0 0;

    }

    .saveBtn {
        font-size: 1.3rem;
        display: block;
        padding: 7px 50px;
        font-weight: bold;
        float: right;
    }

    span {
        font-size: 12px;
        color: #666666;
    }

    .latitudeText, .longitudeText {
        font-size: 12px;
        color: #ff2215;
    }

</style>

<div class="upload h-100 bg-body-tertiary px-3" >
    <form id="scheduleMainListUploadForm" class="py-3 px-5" enctype="multipart/form-data">
        <p class="h4 fw-bold" >컨텐츠 관리</p>
        <ul class="m-0 p-0 list-unstyled">
            <li class="mb-3 p-0">
                <input type="hidden" name="article"/>
            </li>
            <li class="mb-3 p-0">
                <label for="image" class="d-block">이미지 등록</label>
                <input type="file" multiple id="image" name="image" accept="image/*" />
            </li>
            <li class="mb-3 p-0">
                <!-- Main Title -->
                <label for="businessName" class="d-block">비즈니스</label>
                <input type="text" id="businessName" name="businessName" required/>
            </li>
            <li class="mb-3 p-0">
                <label for="category" class="d-block">카테고리</label>
                <input type="text" id="category" name="category" required/>
            </li>
            <li class="mb-3 p-0">
                <label for="gu" class="d-block">구/군</label>
                <input type="text" id="gu" name="gu"/>
            </li>
            <li class="mb-3 p-0">
                <label for="positionX" class="d-block">위도
                    <span>소수점 앞 2자리와 소수점 뒤 7자리로 입력</span>
                </label>
                <input type="text" id="positionX" name="positionX">
                <span class="latitudeText"></span>
            </li>
            <li class="mb-3 p-0">
                <label for="positionY" class="d-block">경도
                    <span>소수점 앞 3자리와 소수점 뒤 7자리로 입력</span>
                </label>
                <input type="text" id="positionY" name="positionY">
                <span class="longitudeText"></span>
            </li>
            <li class="mb-3 p-0">
                <label for="address" class="d-block">상세주소</label>
                <input type="text" id="address" name="address">
            </li>
            <li class="mb-3 p-0">
                <label for="keyword" class="d-block">키워드</label>
                <input type="text" id="keyword" name="keyword">
            </li>
            <li class="mb-3 p-0">
                <label for="detailedDescription" class="d-block">상세설명</label>
                <textarea id="detailedDescription" name="detailedDescription" required></textarea>
            </li>


            <li class="mb-3 p-0">
                <button type="button" class="btn btn-dark px-5 d-block saveBtn" id="scheduleContentsListSubmit" >저장</button>
            </li>

        </ul>
    </form>

    <script src="/js/admin/adminScheduleContentsList.js"></script>
<%@include file="../layout/adminFooter.jsp" %>