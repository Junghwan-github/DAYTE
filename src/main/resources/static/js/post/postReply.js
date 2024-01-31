let replyObject = {
    init : function () {
        $("#btn-save-reply").on("click", () => {
            this.insertReply();
        });

        // 삭제버튼을 배열로 생성하여 forEach를 돌려 삭제버튼(btn)을 불러오고 삭제버튼(btn)을 눌렀을 때 순차적으로
        // click 이벤트 실행 할 수 있게 하는 기능 구현

        // 하지만 밑에 방법과 달리 버튼 태그에 onclick 옵션을 달아주는 방법도 있음
        // <button id="btn-delete-reply" onclick="replyObject.deleteReply(this.value)" value="${reply.id}">삭제</button> 이런 방법도 있음 (참고)

        // document.querySelectorAll("#btn-delete-reply").forEach(btn => {
        //     btn.addEventListener('click', function () {
        //         this.deleteReply(btn.value);
        //     })
        // });
    },

    insertReply: function () {
        console.log("댓글 등록 요청됨");

        let reply = {
            content : $("#reply-content").val(), // 등록한 댓글 내용
            id : $("#postId").val()
        }
        console.log(reply);

        fetch("/postReply", {
            method : "POST",
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
                    // 제이쿼리 문법 객체 속성 접근
                    alert(data["data"]);
                    // 자바스크립트 문법
                    // alert(data.data);
                    location.reload();
                })
                .catch(err => {
                    alert(`에러 발생 : ${err.message}`);
                });
        }
    },
    textarea: "",
    textareaValue: "",
    saveReply: "", // 수정 버튼
    updateBtn: "", // 확인 버튼
    updateReply: function (btn, num) {

        let updateBtnBool;
        let test;
        if (this.textarea !== "") {
            updateBtnBool = confirm("작성하던 글이 있습니다. 저장하지 않고 나가시겠습니까?");
            if (updateBtnBool) {
                console.log(updateBtnBool);
                this.textarea.val(this.textareaValue);
                this.textarea.attr('readonly', true);
                this.saveReply.attr('class', 'replyBtnShow');
                this.updateBtn.attr('class', 'replyBtnHidden');
                updateBtnBool = false;
                console.log(updateBtnBool);
                test = true;
            }
        }
        if (this.textarea === "" || !updateBtnBool ){
            $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
            this.textarea = $(btn).closest('tr').find('td:first > textarea');
            this.textareaValue = $(btn).closest('tr').find('td:first > textarea').text();
            console.log(this.textareaValue);

            $(btn).attr('class', 'replyBtnHidden');
            this.saveReply = $(btn);

            $(btn).closest('td').find("#updateComplete").attr('class', 'replyBtnShow ');
            this.updateBtn = $(btn).closest('td').find("#updateComplete");
        }

        // $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
        // this.textarea = $(btn).closest('tr').find('td:first > textarea');
        //
        // $(btn).attr('class', 'replyBtnHidden');
        // this.saveReply = $(btn);
        //
        // $(btn).closest('td').find("#updateComplete").attr('class', 'replyBtnShow ');
        // this.updateBtn = $(btn).closest('td').find("#updateComplete");

        // else if (this.textarea !== "") {

        // this.textarea.attr('readonly', true);
        // this.saveReply.attr('class', 'replyBtnShow');
        // this.updateBtn.attr('class', 'replyBtnHidden');

        // $(btn).attr('class', 'replyBtnHidden');
        // this.saveReply = $(btn);
        //
        // $(btn).closest('td').find("#updateComplete").attr('class', 'replyBtnShow ');
        // this.updateBtn = $(btn).closest('td').find("#updateComplete");
        //
        // $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
        // this.textarea = $(btn).closest('tr').find('td:first > textarea');
        // this.textarea.attr("readonly", true);
        // this.updateBtn.attr("class", "replyBtnHidden");
        // $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
        // this.textarea = $(btn).closest('tr').find('td:first > textarea');
        // this.updateBtn = $(btn).closest('td').find("#updateComplete");
        // }

        // if(this.updateBtn) {
        //     let modifyBtn = $(btn).closest('td').find("#updateComplete");
        //     $(modifyBtn).attr("class", "replyBtnShow");
        //     btn.className = "replyBtnHidden" ;
        // }
        //
        // let adslkjfas = $(btn).closest('tr').find('td:first > textarea').attr("readonly");
        // let updateBtn = false;
        //
        // if (this.textarea === "") {
        //     this.textarea = $(btn).closest('tr').find('td:first > textarea');
        //     $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
        //     this.updateBtn = false;
        // }
        // else if (this.textarea !== "") {
        //     this.textarea.attr("readonly", true);
        //     this.textarea = $(btn).closest('tr').find('td:first > textarea');
        //     $(btn).closest('tr').find('td:first > textarea').attr("readonly", false);
        //     this.updateBtn = true;
        // }
        //
        // if(this.updateBtn) {
        //     let modifyBtn = $(btn).closest('td').find("#updateComplete");
        //     $(modifyBtn).attr("class", "replyBtnShow");
        //     btn.className = "replyBtnHidden" ;
        // }


        // $(btn).closest('tr') : $(btn) 요소를 기준으로 가장 가까운 tr 태그를 찾아서 반환
        // $(btn) : 매개변수로 넘어온 수정 버튼 태그

        // 해당 댓글의 내용을 수정 폼의 textarea 에 채우기

        // $("#reply-content").val(content);

        // 모든 수정 상태를 초기화
        // $(".update-status").addClass("hidden");

        // 수정 버튼의 클릭 이벤트 변경
        // $("#btn-save-reply").off("click").on("click", () => {
        //     this.completeUpdateReply(num);
        // });
    },

    // 댓글 수정 완료
    completeUpdateReply: function (btn) {

        this.textarea.attr('readonly', true);
        let modifyBtn = $(btn).closest('td').find("#btn-update-reply");
        $(modifyBtn).attr("class", "replyBtnShow");
        btn.className = "replyBtnHidden";

        // 수정된 댓글 내용을 가져옴
        // let updatedPost = {
        //     content : $("#reply-content").val()
        // }
        //
        // // 서버에 댓글 수정 요청을 보냄
        // fetch("/postReply/" + num, {
        //     method: "PUT",
        //     headers: {
        //         "Content-Type": "application/json; charset=utf-8",
        //     },
        //     body: JSON.stringify(updatedPost)
        //
        // })
        //
        //     .then(res => res.json())
        //     .then(data => {
        //         console.log(data);
        //         // location.reload(); // 페이지 새로고침
        //     })
        //     .catch(err => alert(`에러 발생 : ${err.message}`));
    }

}

replyObject.init();

function postReplyBtnListOpen(button) {
    console.log(button);
    console.log($(button).closest('div').find('.postReplyBtnList'));
    button.parentElement.nextElementSibling.classList.toggle('showPostReplyBtnList');
}
