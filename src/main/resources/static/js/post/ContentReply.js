// 댓글 객체(replyObject)는 댓글 작성, 삭제, 수정 기능을 처리하는 메서드를 가지고 있습니다.

let replyObject = {
    init: function () {
        // 페이지 로드 후 초기화
        $(document).ready(() => {
            // "댓글 등록" 버튼 클릭 시 insertReply 메서드 호출
            $("#btn-save-reply").on("click", () => {
                if($("#reply-content").val() == null || $("#reply-content").val() == "") {
                    alert('댓글을 입력해 주세요.');

                } else if ($('input[name=rating]:checked').val() == null
                    || $('input[name=rating]:checked').val() == "") {

                    alert('별점을 선택해 주세요.');

                } else
                    this.insertReply();
                this.toggleButtons();

            });

            $("#btn-mod-reply").on("click", () => {

                this.toggleButtons();
            })

            $(".btn-mod-reply2").on("click", () =>{
                if($("#reply-content").val() == null || $("#reply-content").val() == "") {
                    alert('댓글을 입력해 주세요.');

                } else if ($('input[name=rating]:checked').val() == null
                    || $('input[name=rating]:checked').val() == "") {

                    alert('별점을 선택해 주세요.');

                } else

                this.completeUpdateReply();
                this.toggleButtons();

            });
            $(".btn-mod-reply2").hide();

        });



    },

    // 댓글 등록(insert) 부분
    insertReply: function () {
        let a = $("#reply-content").val();
        console.log(a);
        console.log("댓글 등록 요청됨");


        // 사용자가 입력한 내용과 임의 작성자 정보를 가져와서 객체 생성
        let contentReply = {
            content: $("#reply-content").val(),

            rating: $('input[name=rating]:checked').val()
        }

        // 서버에 댓글 등록 요청을 보냄
        fetch("/contentReply", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(contentReply),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                location.reload(); // 페이지 새로고침
            })
            .catch(err => console.log(`에러 발생 : ${err.message}`));
    },

    // 댓글 삭제(delete) 부분
    deleteReply: function (num) {
        // 사용자에게 댓글 삭제 여부를 확인하는 창을 띄우고 확인 시 삭제 진행
        let del = confirm("댓글을 삭제하시겠습니까?");
        if (del) {
            // 서버에 댓글 삭제 요청을 보냄
            fetch("/contentReply/" + num, {
                method: "DELETE",
            })
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                    location.reload(); // 현재 페이지 새로고침
                })
                .catch(err => alert(`에러 발생 : ${err.message}`));
        }
    },

    // 댓글 수정(update) 부분
    // 수정 폼 보이기
    updateReply: function (num, content, rating) {
        // 별점을 갖고옴
        $('input[name=rating]:checked').val();

        // 해당 댓글의 내용을 수정 폼의 textarea 에 채우기
        $("#reply-content").val(content);

        // 모든 수정 상태를 초기화
        $(".update-status").addClass("hidden");

        // 수정 버튼의 클릭 이벤트 변경
        $("#btn-save-reply").off("click").on("click", () => {
            this.completeUpdateReply(num);
        });
    },

    // 댓글 수정 완료
    completeUpdateReply: function () {
        // 수정된 댓글 내용을 가져옴
        let data ={
            content : $("#reply-content").val(),
            rating : $('input[name=rating]:checked').val(),
            num : $("#reply-num").val()
        }
        console.log(data);

        // 서버에 댓글 수정 요청을 보냄
        fetch("/contentReply/update", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(
                data
            )
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
               location.reload(); // 페이지 새로고침
            })
            .catch(err => alert(`에러 발생 : ${err.message}`));
    },
    toggleButtons: function () {
        $("#btn-save-reply").toggle();
        $(".btn-mod-reply2").toggle();
    }
}

// 댓글 객체 초기화
replyObject.init();
