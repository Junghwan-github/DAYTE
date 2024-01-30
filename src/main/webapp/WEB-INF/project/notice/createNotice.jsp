<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>

    <title>공지사항 메인페이지</title>

    <link href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">
    <script src="/webjars/summernote/0.8.10/summernote-bs4.min.js"></script>


</head>

<body>
<%@include file="../layout/header.jsp"%>

<h1> 새 글 쓰기 </h1>

<div class="container mt-3">

    <form id="saveForm" method="post" autocomplete="off" enctype="multipart/form-data">

    <div class="mb-3">
        <label for="title">Title:</label>
        <input type="text" class="form-control" id="title" name="title">
    </div>
    <div class="mb-3">
        <label for="content">Content:</label>
        <textarea rows="5" id="content" class="form-control" name="content"></textarea>
    </div>
        <div class="file-list">
            <div id="file-input-Div-parentNode">
                <div class="file-input-Div">
                    <input type="text" readonly />

                        <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip " name="files" id="file-input"  multiple="multiple" onchange="selectFile(this);" />
                </div>
                <label for="file-add">파일 추가</label>
                <input type="file" name="files" id="file-add" multiple="multiple" style="display: none" onchange="addFile();" />

            </div>
        </div>
    </form>

    <div>
        <div id="preview"></div>
    </div>

    <button id="btn-create"> 등록</button>
    <button id="btn-cancel"> 취소</button>

</div>




<script src="/js/notice/cancel.js"></script>
<script src="/js/notice/createNotice.js"></script>
<script src="/js/notice/fileFunction.js"></script>



<%@include file="../layout/footer.jsp"%>