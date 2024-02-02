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

        $("#btn-delete").on("click", () => {
            let check = confirm("해당 게시글을 삭제하시겠습니까?");
            if (check) {
                this.deletePost();
            }
        });
    },

    insertPost: function () {
        console.log("포스트 등록 요청됨");

        let post = {
            title: $("#title").val(),
            content: $("#summernote").summernote('code')
        }
        console.log(post);

        fetch("/mainPostList/reg", {
            method: "POST", //  요청 방식
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                // HTTP의 body에 설정되는 마임타입
            },
            body: JSON.stringify(post), // 요청 데이터
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
            id: $("#id").val(),
            title: $("#title").val(),
            content: $("#content").val()
        }

        fetch("/post", {
            method: "PUT", // 요청 방식
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(post), // 요청 데이터
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
        let id = $("#id").text();

        fetch("/post/" + id, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
        })
            .then(res => {
                return res.json();
            })
            .then(data => {
                alert(data.data);
                location = "/";
            })
            .catch(err => {
                alert(`에러 발생 : ${err.message}`);
            });

    },
}

postObject.init();


$(document).ready(function() {
    $('#summernote').summernote({
        height: 450,
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