<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>
<title>공지사항 | 업데이트</title>


<link rel="stylesheet" href="/css/notice/updateNotice.css">

</head>

<body>
<%@include file="../layout/header.jsp" %>

<c:if test="${!empty notice.files}">
<c:forEach var="file" items="${notice.files}">

</c:forEach>

</c:if>
<div class="wrapper">
    <h1>공지사항 수정</h1>
    <form id="saveForm" method="post" autocomplete="off" enctype="multipart/form-data">
        <div>
            <input type="hidden" id="id" value="${notice.no}">
            <div class="noticeTitle">
                <input type="text" id="title" value="${notice.title}">
            </div>
            <div class>
                <textarea id="summernote" name="content">${notice.content}</textarea>
            </div>
        </div>
        <div class="fileList">
            <div id="file-input-Div-parentNode">
                <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip "
                       class="fileInput"
                       name="files" id="file-input" multiple="multiple" onchange="selectFile(this);"/>
                <%-- <label for="file-add">파일 추가</label>
                 <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip " name="files" id="file-add" multiple="multiple" style="display: none" onchange="addFile();" />
 --%>
            </div>
            <div>
                <div id="preview">
                    <c:forEach var="file" items="${notice.files}">
                        <p>
                                ${file.originalName}
                            <button class='file-remove' value="${file.saveName}" onclick="removeFileOnView(this)">
                                X
                            </button>
                            <input class="savedFile" name="savedFile" type="hidden" value="${file.saveName}">
                        </p>
                    </c:forEach>
                </div>
                <div id="newPreview">

                </div>
            </div>
        </div>
    </form>

    <div class="noticebtns">
        <a id="btn-update" class="btn"> 수정</a>
        <a id="cancelBtn" class="btn"> 취소</a>
    </div>


</div>


<script src="/js/notice/updateNotice.js"></script>
<script src="/js/notice/cancel.js"></script>
<script src="/js/notice/modifyFile.js"></script>
<script>
    $(document).ready(function() {
        $('#summernote').summernote({
            tabSize: 2,
            height: 600,
            minHeight: 600,             // 최소 높이
            focus: true,
            lang: "ko-KR",
            placeholder: '내용',
            disableResizeEditor: true,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'italic', 'underline', 'clear']],
                ['fontsize', ['fontsize']],
                ['fontname', ['fontname']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video']],
                ['view', ['fullscreen', 'codeview']],
            ],
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
            fontSizeUnit : "pt",
            fontSizes: ['15', '16', '18', '20', '24', '36', '58', '72'],
            callbacks: {
                onImageUpload: function(files){
                    const [imageFile] = files;
                    sendFile(imageFile);
                }
            },
        })
    })

    // 이미지를 임시저장합니다.
    function sendFile(file) {
        const formData = new FormData();
        formData.append('file', file);

        $.ajax({
            type: 'POST',
            url: '/uploadSummernoteImageFile',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function(response) {
                const imageUrl = response.url;
                $('.summernote').summernote('insertImage', imageUrl);
            },
            error: function(error) {
                console.error('이미지 업로드 실패:', error);
            }
        });
    }
</script>
<%--</body>--%>

<%@include file="../layout/footer.jsp" %>