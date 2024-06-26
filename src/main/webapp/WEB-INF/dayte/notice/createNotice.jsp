<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>

<title>공지사항 | 작성</title>
<link rel="stylesheet" href="/css/notice/updateNotice.css">

</head>

<body>
<%@include file="../layout/header.jsp" %>
<script src="/js/main/header.js"></script>
<div class="wrapper">
    <h1> 공지사항 글쓰기 </h1>


    <form id="saveForm" method="post" autocomplete="off" enctype="multipart/form-data">
        <div>
            <div class="noticeTitle">
                <input type="text" id="title" name="title" placeholder="제목">
            </div>
            <div>
                <div id="content" class="summernote" name="content"></div>
            </div>
        </div>

        <div class="fileList">
            <div id="file-input-Div-parentNode">
                <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip "
                       class="fileInput"
                       name="inputFiles" id="file-input" multiple="multiple" onchange="selectFile(this);"/>
            </div>
            <div>
                <div id="preview"></div>
            </div>
        </div>

    </form>

    <div class="noticebtns">
        <button id="btn-create" class="btn"> 등록</button>
        <button id="cancelBtn" class="btn"> 취소</button>
    </div>
</div>


<script src="/js/notice/cancel.js"></script>
<script src="/js/notice/createNotice.js"></script>
<script src="/js/notice/fileFunction.js"></script>

<script>
    $(document).ready(function () {
        $('#content').summernote({
            tabSize: 2,
            height: 600,                 // 에디터 높이
            minHeight: 600,             // 최소 높이
            maxHeight: 600,             // 최대 높이
            focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
            lang: "ko-KR",					// 한글 설정
            placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
            disableResizeEditor: true,
            toolbar            : [
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
            fontSizes          : ['15', '16', '18', '20', '24', '36', '58', '72'],
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


<%@include file="../layout/footer.jsp" %>