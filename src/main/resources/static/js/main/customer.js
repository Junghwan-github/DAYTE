let sendQuestionToEmail = {
    init: function () {
        $("#sendEmail").on("click", () => {
            /* $("#verification").removeClass("hide");*/

            this.sendQuestion();

        });

    },
    sendQuestion: function () {

        let emailAdress = $("#inquiry-email").val();
        if(emailAdress == ""){
            emailAdress = null;
        }

        let email = {
            emailAdress: emailAdress,
            title: $("#inquiry-title").val(),
            content: $("#inquiry-content").val()
        }
        console.log(email.emailAdress);


        $.ajax({
            type: "POST",
            url: "/question",
            data: JSON.stringify(email),
            contentType: "application/json; charset=utf-8"
        })
            /*.done(function (res) {
                alert("문의접수가 완료되었습니다.");
            })

            .fail(function (err) {
                alert("이메일 문의 접수가 완료되었습니다.");
                location.reload();
            });*/
    }


};
sendQuestionToEmail.init();


