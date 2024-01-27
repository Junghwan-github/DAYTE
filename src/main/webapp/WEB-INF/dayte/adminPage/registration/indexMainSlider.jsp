<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/adminHead.jsp"%>
<!-- 콘텐츠 -->
<div class="upload">
<form id="indexMainSliderUploadForm">
    <!-- Article ID (It's not editable) -->
    <input type="hidden" name="article" />

    <!-- Images File Upload -->
    <label for="images">Images:</label>
    <input type="text" id="images" name="images" />
    <br>

    <!-- Category -->
    <label for="category">Category:</label>
    <input type="text" id="category" name="category"  required/>
    <br>

    <!-- Main Title -->
    <label for="mainTitle">Main Title:</label>
    <input type="text" id="mainTitle" name="mainTitle"  required/>
    <br>

    <!-- Sub Title -->
    <label for="subTitle">Sub Title:</label>
    <input type="text" id="subTitle" name="subTitle" required/>
    <br>

    <!-- Schedule -->
    <label for="schedule">Schedule:</label>
    <input type="text" id="schedule" name="schedule"/>
    <br>

    <!-- Address -->
    <label for="address">Address:</label>
    <input type="text" id="address" name="address">
    <br>

    <!-- Summary -->
    <label for="summary">Summary:</label>
    <textarea id="summary" name="summary" required></textarea>
    <br>

    <button type="button" id="indexMainSliderSubmit">저장</button>
</form>

<script src="/js/adminIndexMainSlider.js"></script>
<%@include file="../layout/adminFooter.jsp"%>