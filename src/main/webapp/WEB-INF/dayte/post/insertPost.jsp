<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- head --%>
<%@include file="../layout/head.jsp"%>

<%-- css, jquery --%>
<link rel="stylesheet" href="/css/layout/subnav.css">



</head> <%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp"%> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>
<script src="/js/main/header.js"></script>
<main> <%-- main --%>

    <div class="container mt-3">
        <form>
            <div class="mb-3">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title">
            </div>
            <div class="mb-3">
                <label for="content">Content:</label>
                <textarea rows="5" class="form-control summernote" id="content" name="content"></textarea>
            </div>
        </form>
        <button id="save-post" class="btn btn-secondary">등록</button>
    </div>

    <script src="/js/post/post.js"></script>
    <script>
        $(document).ready(function() {
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
                fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
                fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
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

</main>
<%@include file="../layout/footer.jsp" %>