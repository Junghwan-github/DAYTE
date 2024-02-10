
let replyObject = {
    init: function () {
        $("#btn-save-reply").on("click", () => {
            if ($("#reply-content").val() == "" || $("#reply-content").val() == null) {
                alert("댓글을 내용을 입력해주세요.");
            } else {
                this.insertReply();
            }
        });

        $(".btn-delete-reply").on("click", (e) => {
           this.deleteReply($(e.target).val());
           console.log($(e.target).val());

        });

        $(".checkButton").on("click", (e) => {
            this.checkButtonClick($(e.target).val());
            // console.log($(e.target).val());
        });
    },

    // ------------- 댓글 등록 -------------
    insertReply: function () {
        console.log("댓글 등록 요청됨");

        let reply = {
            content: $("#reply-content").val(), // 등록한 댓글 내용
            id: $("#postId").val() // 댓글이 등록될 포스트의 번호(id값)
        }
        console.log(reply);

        fetch("/postReply", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(reply),
        })
            .then(res => {
                return res.json();
            })
            .then(data => {
                console.log(data);
                location.reload();
            })
            .catch(err => {
                alert(`에러 발생 : ${err.message}`);
            });
    },

    // ------------- 댓글 삭제 -------------
    deleteReply: function (replyId) {
        let ck = confirm("댓글을 삭제하시겠습니까?");

        if (ck) {
            fetch("/postReply/" + replyId, {
                method: "DELETE",
            })
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    alert(data["data"]);
                    location.reload();
                })
                .catch(err => {
                    alert(`에러 발생 : ${err.message}`);
                });
        }
    },


    // ------------- 댓글 수정 -------------
    checkButtonClick: function (replyId) {
        let updateReply = {
            content: $(".changedTextarea").val(), // 등록한 댓글 내용
            id: $("#postId").val() // 댓글이 등록될 포스트의 번호(id값)
        }
        console.log(updateReply);

        fetch("/postReply/" + replyId, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(updateReply),
        })
            .then(res => {
                return res.json();
            })
            .then(data => {
                alert(data["data"]);
                location.reload();
            })
            .catch(err => {
                alert(`에러 발생 : ${err.message}`);
            });
    }
}

replyObject.init();

