
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
    checkButtonClick: function (replyId) {
        let updateReply = {
            content: $("#reply-content").val(), // 등록한 댓글 내용
            id: $("#postId").val() // 댓글이 등록될 포스트의 번호(id값)
        }
        console.log(updateReply);

        fetch("/postReply" + replyId, {
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
                console.log(data);
                location.reload();
            })
            .catch(err => {
                alert(`에러 발생 : ${err.message}`);
            });
    }
}

// // ------------- 댓글 햄버거창 onclick 이벤트 -------------
replyObject.init();
// // replyObject 초기화
//
// let replyItems = document.querySelectorAll(".post-reply-li");
//
// replyItems.forEach(function (scheduleItem) {
//     let replyModifyBtn = scheduleItem.querySelector(".xi-ellipsis-v");
//
//     replyModifyBtn.addEventListener("click", function (e) {
//         let postReplyBtnList = scheduleItem.querySelector(".postReplyBtnList");
//
//         if (postReplyBtnList) {
//             postReplyBtnList.classList.toggle("showPostReplyBtnList");
//         }
//
//         e.preventDefault();
//     });
// });
//
// // ------------ 댓글 수정 버튼을 눌렀을때 a태그를 textarea로 바꿔주는 이벤트 실행 -------------
// let textarea;
// let textP;
//
// // 포스트 수정 버튼을 눌렀을때 기존 댓글 컨텐츠 태그를 textarea 로 변경
//
// // html5(data-set) 기능 사용법 익히기
// // <c:forech 사용해서 key 와 vel 을 이용하여 this요소 사용>
//
// function updateBtnClick (btn) {
//     let existingValue = $(btn).closest('.post-reply-li').find('.changeTextarea').text();
//     textP = existingValue;
//     // changeTextarea 클래스에 있는 기존 댓글 정보를 담음
//
//     textarea = document.createElement('textarea');
//     textarea.className = 'replyUpdateTextarea';
//     textarea.innerText = existingValue;
//     // textarea 를 생성해서 위에 담아둔 기존 댓글 정보를 담음
//
//     $(btn).closest('.post-reply-li').find('.checkButton').css('display', 'inline-block');
//     $(btn).closest('.post-reply-li').find('.cancelButton').css('display', 'inline-block');
//
//     let pTag = document.querySelector('.changeTextarea');
//     pTag.parentNode.replaceChild(textarea, pTag);
//     // pTag 에 changeTextarea 클래스 요소를 담고 기존 댓글 정보를 담고 있는 textarea 와 pTag 의 요소를 바꿔줌
//     // (pTag 를 textarea 로 바꿔줌)
// }
//
// // ------------ 취소 버튼을 눌렀을때 수정 하기 전 데이터로 초기화 -------------
// function cancelButtonClick (cancelBtn) {
//     console.log("취소 버튼 클릭");
//
//     let replyContentExistingValue = $(cancelBtn).closest('.post-reply-li').find('.replyUpdateTextarea').text();
//     console.log(replyContentExistingValue);
//     // changeTextarea 클래스에 있는 기존 댓글 정보를 담음
//
//     textarea = document.createElement('p');
//     textarea.className = 'changeTextarea';
//     textarea.innerText = textP;
//     // textarea 를 생성해서 위에 담아둔 기존 댓글 정보를 담음
//
//     let textareaTag = $(cancelBtn).closest('.post-reply-li').find('.replyUpdateTextarea');
//
//     textareaTag.replaceWith($(`<p class='changeTextarea'>${replyContentExistingValue}</p>`));
//     // textareaTag.parentNode.replaceChild(textarea, textareaTag);
//     // pTag 에 changeTextarea 클래스 요소를 담고 기존 댓글 정보를 담고 있는 textarea 와 pTag 의 요소를 바꿔줌
//     // (pTag 를 textarea 로 바꿔줌)
//     $(cancelBtn).closest('.post-reply-li').find('.checkButton').css('display', 'none');
//     $(cancelBtn).closest('.post-reply-li').find('.cancelButton').css('display', 'none');
//
// }



