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
                       name="inputFiles" id="file-input" multiple="multiple" onchange="selectFile(this);"/>
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
    $(document).ready(function () {
        $('#summernote').summernote({
            height: 300,                 // 에디터 높이
            minHeight: null,             // 최소 높이
            maxHeight: null,             // 최대 높이
            focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
            lang: "ko-KR",					// 한글 설정
            placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
            callbacks: {	//여기 부분이 이미지를 첨부하는 부분
                onImageUpload: function (files) {
                    uploadSummernoteImageFile(files[0], this);
                },
                onPaste: function (e) {
                    var clipboardData = e.originalEvent.clipboardData;
                    if (clipboardData && clipboardData.items && clipboardData.items.length) {
                        var item = clipboardData.items[0];
                        if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
                            e.preventDefault();
                        }
                    }
                },
                onMediaDelete: function ($target, editor, $editable) {
                    var deletedImageUrl = $target
                        .attr('src')
                        .split('/')
                        .pop()
                    deleteSummernoteImageFile(deletedImageUrl)

                },
            }
        });
    });
    /**
     * 이미지 파일 업로드
     */
    function uploadSummernoteImageFile(file, editor) {
        data = new FormData();
        data.append("file", file);
        $.ajax({
            data: data,
            type: "POST",
            url: "/uploadNoticeImageFile",
            contentType: false,
            processData: false,
            success: function (data) {
                //항상 업로드된 파일의 url이 있어야 한다.
                $(editor).summernote('insertImage', data.url);
                console.log(data.url);
            }
        });
    }

    function deleteSummernoteImageFile(imageName) {
        data = new FormData()
        data.append('file', imageName)
        $.ajax({
            data: data,
            type: 'POST',
            url: '/deleteSummernoteImageFile',
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
        })
    }


</script>
<%--</body>--%>

<%@include file="../layout/footer.jsp" %>