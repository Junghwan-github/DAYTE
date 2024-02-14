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

                console.log(res.data);
                if(res === 200) {

                }
            })
            .fail(function (err) {
                alert("에러발생 :" + err);
            });
    }


};
sendQuestionToEmail.init();


