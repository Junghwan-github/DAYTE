<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>
    <title>공지사항</title>

    <link href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>


</head>

<body>
<%@include file="../layout/header.jsp"%>
<c:if test="${!empty notice.files}">
    <c:forEach var="file" items="${notice.files}">

    </c:forEach>

</c:if>

<h1> 상세보기 </h1>

<div>
    <form id="saveForm" method="post" autocomplete="off" enctype="multipart/form-data">
        <input type="hidden" id="id" value="${notice.no}">
        <div>
            <input type="text" id="title" value="${notice.title}">
        </div>
        <div>
            <textarea rows="5" class="form-control" id="content" >${notice.content}</textarea>
        </div>
        <div class="file-list">
            <div id="file-input-Div-parentNode">
                <div class="file-input-Div">
                    <input type="text" readonly />

                    <input type="file" name="files" id="file-input"  multiple="multiple" onchange="selectFile(this);" />
                </div>
                <label for="file-add">파일 추가</label>
                <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip " name="files" id="file-add" multiple="multiple" style="display: none" onchange="addFile();" />

                <div>
                    <div id="preview">
                        <c:forEach var="file" items="${notice.files}">
                            <p class="${file.saveName}">
                                    ${file.originalName}
                                <button class='file-remove' value="${file.saveName}" onclick="removeFileOnView(this)">X</button>
                                <input class="savedFile" name="savedFiled" type="hidden" value="${file.saveName}">
                            </p>
                        </c:forEach>
                    </div>
                    <div id="newPreview">

                    </div>
                </div>


            </div>
        </div>
    </form>






    <button id="btn-update"> 수정 </button>
    <button id="btn-cancel"> 취소 </button>




</div>






<script src="/js/notice/updateNotice.js"></script>
<script src="/js/notice/cancel.js"></script>
<script src="/js/notice/modifyFile.js"></script>

</body>

</html>