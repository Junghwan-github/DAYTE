<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>

<title>공지사항 | 작성</title>

<style>
    .wrapper {
        width: 1180px;
        margin: 0 auto;
        background: white;
    }

    /*==================================== 제목 */
    .wrapper > h1 {
        font-size: 2.8rem;
        color: #333;
        padding: 20px 0;
    }

    .wrapper > form > div:first-child {
        display: flex;
        flex-direction: column;
        width: 100%;
        box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 3px 0px, rgba(0, 0, 0, 0.06) 0px 1px 2px 0px;
    }

    .noticeTitle {
        width: 100%;
        height: 50px;

        display: flex;
        align-items: center;

        border-top: #333 solid 2px;
        border-bottom : 1px solid #ddd;

    }

    .noticeTitle > input {
        box-sizing: border-box;
        width: 100%;
        height: 100%;
        padding-left: 15px;
        border: transparent;
        background-color: transparent;

        font-size: 2rem;
        font-weight: 600;
    }
    .noticeTitle > input:focus {
        outline: none;
    }

    /* ================================ 파일 */
    .fileList {
        margin-top: 10px;
        border: 1px solid #eee;
        /*background-color: pink;*/

    }

    .fileInput {
        font-size: 1.4rem;
    }

    #newPreview > p {
        font-size: 1.4rem;
        padding-left: 10px;
    }

    #newPreview > p:hover {
        background-color: #eeeeee;
        transition: 0.2s;
    }

    #newPreview > p > button {
        width: 25px;
        height: 25px;
        padding: 0px;
        margin: 5px;
        border: 1px solid #777;
        border-radius: 5px;
        background-color: transparent;
        box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px -1px, rgba(0, 0, 0, 0.06) 0px 2px 4px -1px;
    }

    /* ======================================= 수정 취소 버튼 */
    .noticebtns {
        margin: 10px 0 5px 0;
        display: flex;
        justify-content: end;
    }

    .noticebtns > button {
        padding: 8px 11px;
        margin: 0 0 0 5px;
        border: transparent;
        border-radius: 5px;

        background-color: #333;
        color: #fff;
        font-size: 1.6rem;
        text-decoration: none;

        cursor: pointer;
    }

    .noticebtns > button:hover {
        background-color: #111;
        color: #fff;
        transition: 0.2s;
    }


</style>
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
                <textarea rows="5" id="content" class="summernote" name="content"></textarea>
            </div>
        </div>

        <div class="fileList">
            <div id="file-input-Div-parentNode">
                <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip "
                       class="fileInput"
                       name="files" id="file-input" multiple="multiple" onchange="selectFile(this);"/>
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
        $('.summernote').summernote({
            height: 500,
            focus: true,
            lang: "ko-KR",
            placeholder: '내용',
            disableResizeEditor: true,
            toolbar: [
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video']],
                ['view', ['codeview']]
            ],
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
            fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
            callbacks: {
                onImageUpload: function (files) {
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
            success: function (response) {
                const imageUrl = response.url;
                $('.summernote').summernote('insertImage', imageUrl);
            },
            error: function (error) {
                console.error('이미지 업로드 실패:', error);
            }
        });
    }
</script>


<%@include file="../layout/footer.jsp" %>