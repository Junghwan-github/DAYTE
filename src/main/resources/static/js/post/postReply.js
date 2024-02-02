
let replyObject = {
    init: function () {
        $("#btn-save-reply").on("click", () => {
            if ($("#reply-content").val() == "" || $("#reply-content").val() == null) {
                alert("댓글을 내용을 입력해주세요.");
            } else {
                this.insertReply();
            }
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
    updateReply: function (num) {


    }
}

// 포스트 수정 버튼을 눌렀을때 기존 댓글 컨텐츠 태그를 textarea로 변경
function updateBtnClick () {
    let replyContentExistingValue = document.querySelector('.changeTextarea').innerText;

    let textarea = document.createElement('textarea');
    textarea.className = 'replyUpdateTextarea';
    textarea.value = replyContentExistingValue;

    let pTag = document.querySelector('.changeTextarea');
    pTag.parentNode.replaceChild(textarea, pTag);

    let checkButton = document.createElement("button")
    checkButton.className = "checkButton";
    checkButton.innerText = "확인";
    checkButton.addEventListener("click", checkButtonClick);

    let cancelButton = document.createElement("button")
    cancelButton.className = "cancelButton";
    cancelButton.innerText = "취소";
    cancelButton.addEventListener("click", cancelButtonClick);

    textarea.parentNode
}


// 댓글 햄버거창 onclick 이벤트
replyObject.init();


let replyItems = document.querySelectorAll(".post-reply-li");

replyItems.forEach(function (scheduleItem) {
    let replyModifyBtn = scheduleItem.querySelector(".xi-ellipsis-v");

    replyModifyBtn.addEventListener("click", function (e) {
        let postReplyBtnList = scheduleItem.querySelector(".postReplyBtnList");

        if (postReplyBtnList) {
            postReplyBtnList.classList.toggle("showPostReplyBtnList");
        }

        e.preventDefault();
    });
});

