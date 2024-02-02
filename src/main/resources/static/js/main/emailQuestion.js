let sendQuestionToEmail = {
    init: function () {
        $("#sendEmail").on("click", () => {
            /* $("#verification").removeClass("hide");*/

            this.sendQuestion();

        });

    },
    sendQuestion: function () {


        let email = {
            title : $("#title").val(),
            content : $("#content").val()
        }


        $.ajax({
            type: "POST",
            url: "/question",
            data: JSON.stringify(email),
            contentType: "application/json; charset=utf-8"

        })
            .done(function (res) {
                console.log("^^^^^^^^^^^^^^^");
                console.log(res.data);
                if(res === 200) {
                    alert("해당 이메일로 인증번호를 전송했습니다.");
                }
            })
            .fail(function (err) {
                alert("에러발생 :" + err);
            });
    }


};
sendQuestionToEmail.init();
