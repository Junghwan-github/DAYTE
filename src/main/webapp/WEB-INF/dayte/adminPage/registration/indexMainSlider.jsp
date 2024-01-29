<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/adminHead.jsp" %>
<!-- 콘텐츠 -->
<style>
    .upload {
        position: relative;
        width: 1800px;
        top: 90px;
        left: 50%;
        transform: translate(-50%, 0);
    }

    body {
        margin: 0;
        padding: 0;
    }

    input, textarea {
        width: 100%;
        border: 1px solid #ddd;
        height: 50px;
        padding: 5px 10px;
        margin: 10px 0 0 0;

    }

label {
    display: block;
}

    ul {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    li {
        padding: 0;
        margin: 0 0 20px 0;
    }

    button {
        border: none;
        background: #1785f2;
        color: #fff;
        font-size: 1.3rem;
        display: block;
        padding: 7px 50px;
        font-weight: bold;
        float: right;
    }
</style>
<div class="upload">
    <form id="indexMainSliderUploadForm">
        <ul>
            <li>
                <input type="hidden" name="article"/>
            </li>
            <li>
                <label for="images">이미지 등록</label>
                <input type="file" id="images" name="images" accept="image/*"/>
            </li>
            <li>
                <label for="category">카테고리</label>
                <input type="text" id="category" name="category" required/>
            </li>
            <li>
                <!-- Main Title -->
                <label for="mainTitle">타이틀</label>
                <input type="text" id="mainTitle" name="mainTitle" required/>
            </li>
            <li>
                <label for="subTitle">서브 타이틀</label>
                <input type="text" id="subTitle" name="subTitle" required/>
            </li>
            <li>
                <label for="schedule">일정</label>
                <input type="text" id="schedule" name="schedule"/>
            </li>
            <li>
            <label for="address">주소</label>
            <input type="text" id="address" name="address">
            </li>
            <li>
                <label for="summary">요약</label>
                <textarea id="summary" name="summary" required></textarea>
            </li>

            <li>
                <button type="button" id="indexMainSliderSubmit">저장</button>
            </li>
        </ul>
    </form>

    <script src="/js/adminIndexMainSlider.js"></script>
<%@include file="../layout/adminFooter.jsp" %>