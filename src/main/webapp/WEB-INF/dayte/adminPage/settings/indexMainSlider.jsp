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

</style>
<div class="upload px-3" >
    <p class="h4 titleName  mx-5 border-bottom pb-2" >메인 설정</p>
    <form id="indexMainSliderUploadForm" class="pb-3 px-5">

        <ul class="h-100 m-0 p-0 list-unstyled">
            <li class="mb-3 p-0">
                <input type="hidden" name="article"/>
            </li>
            <li class="mb-2 p-0">
                <label for="images" class="d-block">이미지 등록</label>
                <input type="file" id="images" name="images" accept="image/*"/>
            </li>
            <li class="mb-2 p-0">
                <label for="category" class="d-block">카테고리</label>
                <input type="text" id="category" name="category" required/>
            </li>
            <li class="mb-2 p-0">
                <!-- Main Title -->
                <label for="mainTitle" class="d-block">타이틀</label>
                <input type="text" id="mainTitle" name="mainTitle" required/>
            </li>
            <li class="mb-2 p-0">
                <label for="subTitle" class="d-block">서브 타이틀</label>
                <input type="text" id="subTitle" name="subTitle" required/>
            </li>
            <li class="mb-2 p-0">
                <label for="schedule" class="d-block">일정</label>
                <input type="text" id="schedule" name="schedule"/>
            </li>
            <li class="mb-2 p-0">
                <label for="address" class="d-block">주소</label>
                <input type="text" id="address" name="address">
            </li>
            <li class="mb-2 p-0">
                <label for="summary" class="d-block">요약</label>
                <textarea id="summary" name="summary" required></textarea>
            </li>
            <li class="mb-2 p-0">
                <label for="summary">링크</label>
                <input type="text" id="href" name="href" required>
            </li>

            <li class=" p-0">
                <button type="button" class="btn btn-dark px-5 d-block saveBtn" id="indexMainSliderSubmit" >저장</button>
            </li>

        </ul>
    </form>
</div>
    <script src="/js/admin/adminIndexMainSlider.js"></script>
<%@include file="../layout/adminFooter.jsp" %>