let postObject = {

    init: function () {
        $("#save-post").on("click", () => {
            //
            // if($("#title").val() == null || $("#title").val() == "") {
            //     alert('제목을 입력해주세요.');
            //
            // } else if ($("#content").val() == null || $("#content").val() == "") {
            //     alert('내용을 작성해주세요.');
            //
            // } else
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
            content: $("#content").val()

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
                location = "/post";
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