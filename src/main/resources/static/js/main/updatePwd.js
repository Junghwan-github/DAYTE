// 비밀번호 유효성 검사 메서드
let passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*?_]).{8,20}$/;
$("#newPwd").on("keyup", function () {
    $("#err1").text("");
})
$("#newPwd").on("blur", function () {

    if ($("#confirmPwd").val() == "") {
        if (!passwordRegex.test($("#newPwd").val())) {
            $("#err1").css("color", "red").text("비밀번호는 8자 이상 20글자 이하이며, 한글,숫자,영문,특수기호를 포함해야 합니다.");
            $("#newPwd").css("border-color", "red");
            // $("#password2").focus();
            return;
        }
            $("#newPwd").css("border-color", "#333");
            $("#err1").text("");
    } else {
        if($("#newPwd").val() != $("#confirmPwd").val()){
            $("#err1").css("color", "red").text("비밀번호가 일치하지 않습니다.");
            $("#confirmPwd").css("border-color", "red");
            return;
        }
        $("#confirmPwd").css("border-color", "#333");
        $("#err1").text("");
    }
})
$("#confirmPwd").on("keyup", function () {
    $("#err1").text("");

})
$("#confirmPwd").on("blur", function () {
    if (passwordRegex.test($("#newPwd").val())) {
        if ($("#newPwd").val() != $("#confirmPwd").val()) {
            $("#err1").css("color", "red").text("비밀번호가 일치하지 않습니다.");
            $("#confirmPwd").css("border-color", "red");
            return;
        }
            $("#confirmPwd").css("border-color", "#333");
            $("#err1").text("");

    } else {
        $("#err1").css("color", "red").text("비밀번호는 8자 이상 20글자 이하이며, 한글,숫자,영문,특수기호를 포함해야 합니다.");
        $("#newPwd").css("border-color", "red");
        $("#newPwd").focus();
        return;
    }
});

let userObject = {
    //init
    init: function () {
        let $this = this;

        $("#updatePwdBtn").click(function () {
            if ($('#newPwd').val() != $('#confirmPwd').val()) {
                alert('새로운 비밀번호가 일치하지 않습니다.');
                $('#newPwd').val('').focus();
                $('#confirmPwd').val('');
                return false;
            }
            $this.updatePwd();
        });
    },

    updatePwd: function () {
        let user = {
            checkPwd: $("#checkPwd").val(),
            newPwd: $("#newPwd").val(),
        }
        $.ajax({
            type: "PUT",
            url: "/members/pwdForm",
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8"
        }).done(function (response) {
            let status = response["status"];
            if (status == 200) {
                let message = response["data"];
                alert(message);
                location = "/members/logout";
            } else {
                let errors = response["data"];
                alert(errors);

            }
        }).fail(function (error) {
            alert("에러 발생 :" + error);
        });
    },
}
userObject.init();