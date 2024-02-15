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

    .upload span {
        font-size: 12px;
        color: #666666;
    }

    .latitudeText, .longitudeText {
        font-size: 12px;
        color: #ff2215;
    }

    .sales {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .sales > input {
        width: 49%;
    }

    .sales > p {
        line-height: 100%;
        margin-bottom: 0;
    }
</style>

<div class="upload h-100 bg-body-tertiary px-3">
    <p class="h4 titleName mx-5 border-bottom pb-2" >컨텐츠 관리</p>

    <form id="scheduleMainListUploadForm" class="pb-3 px-5" enctype="multipart/form-data">

        <ul class="m-0 p-0 list-unstyled">
            <li class="mb-3 p-0">
                <input type="hidden" name="article"/>
            </li>
            <li class="mb-2 p-0">
                <label for="image" class="d-block">이미지 등록</label>
                <input type="file" multiple id="image" name="image" accept="image/*" />
            </li>
            <li class="mb-2 p-0">
                <!-- Main Title -->
                <label for="businessName" class="d-block">컨텐츠명</label>
                <input type="text" id="businessName" name="businessName" required/>
            </li>
            <li class="mb-2 p-0">
                <label for="category" class="d-block">카테고리</label>
                <input type="text" id="category" name="category" required/>
            </li>
            <li class="mb-2 p-0">
                <label for="gu" class="d-block">구/군</label>
                <input type="text" id="gu" name="gu"/>
            </li>
            <li class="mb-2 p-0">
                <label for="positionX" class="d-block">위도
                    <span>소수점 앞 2자리와 소수점 뒤 6자리로 입력</span>
                </label>
                <input type="text" id="positionX" name="positionX">
                <span class="latitudeText"></span>
            </li>
            <li class="mb-2 p-0">
                <label for="positionY" class="d-block">경도
                    <span>소수점 앞 3자리와 소수점 뒤 6자리로 입력</span>
                </label>
                <input type="text" id="positionY" name="positionY">
                <span class="longitudeText"></span>
            </li>
            <li class="mb-2 p-0">
                <label for="address" class="d-block">상세주소</label>
                <input type="text" id="address" name="address">
            </li>
            <li class="mb-2 p-0">
                <label for="contactInfo" class="d-block">연락처</label>
                <input type="text" id="contactInfo" name="contactInfo" required/>
            </li>
            <li class="mb-2 p-0">
                <label for="opening" class="d-block">영업시간(행사기간)</label>
                <div class="sales">
                    <input type="text" id="opening" name="opening" placeholder="시작시간(일자)를 작성해주세요." required/> <%--여는 시간--%>
                    <p>~</p>
                    <input type="text" id="closed" name="closed" placeholder="종료시간(일자)를 작성해주세요." required/> <%--닫는 시간--%>
                </div>
            </li>
            <li class="mb-2 p-0">
                <label for="keyword" class="d-block">키워드</label>
                <input type="text" id="keyword" name="keyword">
            </li>
            <li class="mb-2 p-0">
                <label for="detailedDescription" class="d-block">상세설명</label>
                <textarea id="detailedDescription" name="detailedDescription" required></textarea>
            </li>
            <li class="mb-2 p-0">
                <label for="facilities" class="d-block">편의시설</label>
                <textarea id="facilities" name="facilities" required></textarea>
            </li>


            <li class="p-0">
                <button type="button" class="btn btn-dark px-5 d-block saveBtn" id="scheduleContentsListSubmit" >저장</button>
            </li>

        </ul>
    </form>
</div>
    <script src="/js/admin/adminScheduleContentsList.js"></script>
<%@include file="../layout/adminFooter.jsp" %>