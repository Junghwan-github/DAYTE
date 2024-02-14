let postObject = {

    init: function () {
        $("#save-post").on("click", () => {
            this.insertPost();
        });

        $("#btn-update").on("click", () => {
            let check = confirm("해당 게시글을 수정하시겠습니까?");
            if (check) {
                this.updatePost();
            }
        });

        $("#post-delete").on("click", () => {
            let check = confirm("해당 게시글을 삭제하시겠습니까?");
            if (check) {
                this.deletePost();
            }
        });
    },

    insertPost: function () {
        console.log("포스트 등록 요청됨");

        let post = {
            title  : $("#title").val(),
            content: $("#summernote").summernote('code')
        }
        console.log(post);

        fetch("/mainPostList/reg", {
            method : "POST", //  요청 방식
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                // HTTP의 body에 설정되는 마임타입
            },
            body   : JSON.stringify(post), // 요청 데이터
        })
            .then(response => {
                // 응답으로 들어온 promise 객체의 응답 결과 json을 js에서 사용할 수 있는 객체로 변환 후 반환
                return response.json();
            })
            .then(data => {
                let status = data["status"];
                if (status == 200) {
                    let message = data["data"];
                    alert(message);
                    location = "/mainPostList";
                } else {
                    let warn = "";
                    let errors = data["data"];
                    if (errors.title != null) warn = warn + errors.title + "\n";
                    if (errors.content != null) warn = warn + errors.content;

                    alert(warn);
                }
            })
            .catch(error => {
                // 에러 메시지 알림창에 출력
                alert(`에러 발생 : ${error.message}`);
            });
    },

    updatePost: function () {
        // console.log('포스트 수정 요청됨');
        let post = {
            id     : $("#id").val(),
            title  : $("#title").val(),
            content: $("#summernote").summernote('code')
        }

        fetch("/post", {
            method : "PUT", // 요청 방식
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body   : JSON.stringify(post), // 요청 데이터
        })
            .then(res => {
                // Promise 객체 안의 응답 결과(json)를 담은 데이터만 뽑아내어 객체로 만든 후 반환
                return res.json();
            })
            .then(data => {
                console.log(data);
                location = "/mainPostList";
            })
            .catch(err => {
                alert(`에러 발생 : ${err.message}`);
            });
    },

    deletePost: function () {
        // console.log('삭제 요청');
        let id = $("#post-id").val();

        fetch("/post/" + id, {
            method : "DELETE",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
        })
            .then(res => {
                return res.json();
            })
            .then(data => {
                alert(data.data);
                location = "/mainPostList";
            })
            .catch(err => {
                alert(`에러 발생 : ${err.message}`);
            });
    },
}

postObject.init();


$(document).ready(function () {
    $('#summernote').summernote({
        tabSize            : 2,
        height             : 750,
        focus              : true,
        lang               : "ko-KR",
        placeholder        : '여기 내용을 입력하세요',
        disableResizeEditor: false,
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
        fontSizes          : ['15', '16', '18', '20', '24', '36', '58', '72'],
        callbacks          : {
            onImageUpload: function (files) {
                /** upload start */
                    // 이미지 용량 제한
                let maxSize = 10 * 1024 * 1024; // limit = 10MB
                let isMaxSize = false;
                let maxFiles = [];

                for (let i = 0; i < files.length; i++) {
                    if (files[i].size > maxSize) {
                        isMaxSize = true;
                        maxFiles.push(files[i].name);
                    } else {
                        sendFile(files[i], this);
                    }
                }
                if (isMaxSize) { // 사이즈 제한에 걸렸을 때
                    alert('이미지 파일이 업로드 용량(10MB)을 초과하였습니다.');
                } else {
                    /** upload end */
                }
            },
        },
    });
});

// 이미지를 임시저장합니다.
function sendFile(files) {
    const formData = new FormData();
    formData.append('files', files); // 'files'는 서버에서 받을 때의 파라미터 이름입니다.
    $.ajax({
        type       : 'POST',
        url        : '/uploadSummernoteImageFile',
        data       : formData,
        cache      : false,
        contentType: false,
        processData: false,
        success    : function (response) {
            $('#summernote').summernote('insertImage', response.url);
        },
        error      : function (error) {
            console.error('이미지 업로드 실패:', error);
        }
    });
}