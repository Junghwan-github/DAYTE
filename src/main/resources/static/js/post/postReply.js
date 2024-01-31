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

// 댓글 햄버거창 onclick 이벤트
replyObject.init();

function postReplyBtnListOpen(button) {
    // console.log(button);
    console.log($(button).closest('div').find('.postReplyBtnList'));
    button.parentElement.nextElementSibling.classList.toggle('showPostReplyBtnList');
}




//     textarea: "",
//     textareaValue: "",
//     saveReply: "", // 수정 버튼
//     updateBtn: "", // 확인 버튼
//     updateReply: function (btn, num) {
//
//         let updateBtnBool;
//         let test;
//         if (this.textarea !== "") {
//             updateBtnBool = confirm("작성하던 글이 있습니다. 저장하지 않고 나가시겠습니까?");
//             if (updateBtnBool) {
//                 console.log(updateBtnBool);
//                 this.textarea.val(this.textareaValue);
//                 this.textarea.attr('readonly', true);
//                 this.saveReply.attr('class', 'replyBtnShow');
//                 this.updateBtn.attr('class', 'replyBtnHidden');
//                 updateBtnBool = false;
//                 console.log(updateBtnBool);
//                 test = true;
//             }
//         }
//         if (this.textarea === "" || !updateBtnBool ){
//             $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
//             this.textarea = $(btn).closest('tr').find('td:first > textarea');
//             this.textareaValue = $(btn).closest('tr').find('td:first > textarea').text();
//             console.log(this.textareaValue);
//
//             $(btn).attr('class', 'replyBtnHidden');
//             this.saveReply = $(btn);
//
//             $(btn).closest('td').find("#updateComplete").attr('class', 'replyBtnShow ');
//             this.updateBtn = $(btn).closest('td').find("#updateComplete");
//         }
//     },
//
//     // 댓글 수정 완료
//     completeUpdateReply: function (btn) {
//
//         this.textarea.attr('readonly', true);
//         let modifyBtn = $(btn).closest('td').find("#btn-update-reply");
//         $(modifyBtn).attr("class", "replyBtnShow");
//         btn.className = "replyBtnHidden";
//
//         // 수정된 댓글 내용을 가져옴
//         // let updatedPost = {
//         //     content : $("#reply-content").val()
//         // }
//         //
//         // // 서버에 댓글 수정 요청을 보냄
//         // fetch("/postReply/" + num, {
//         //     method: "PUT",
//         //     headers: {
//         //         "Content-Type": "application/json; charset=utf-8",
//         //     },
//         //     body: JSON.stringify(updatedPost)
//         //
//         // })
//         //
//         //     .then(res => res.json())
//         //     .then(data => {
//         //         console.log(data);
//         //         // location.reload(); // 페이지 새로고침
//         //     })
//         //     .catch(err => alert(`에러 발생 : ${err.message}`));
//     }
//
// }


