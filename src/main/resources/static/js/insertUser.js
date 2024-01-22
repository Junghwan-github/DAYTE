let userObject = {
    init: function () {
        let _this = this;
        $("#signBttn").on("click", () => {
            if(varificationValue) {
                _this.insertUser();
            } else {
                alert("이메일 인증이 완료되지 않았습니다.");
            }
        });
    },
    insertUser: function () {
        console.log("inserUser 메서드 수행");
        let user = {
            userEmail: $("#id").val() + "@" + $("#emailDomain").val(),
            password: $("#password1").val(),
            userName: $("#userName").val(),
            nickName: $("#nickName").val(),
            phone: $("#phone").val(),
            birthDate: $("#birthDate").val(),
            gender: $("#gender").val()

        }
        $.ajax({
            type : "POST",
            url : "/members/joinForm",
            data : JSON.stringify(user),
            contentType : "application/json; charset=utf-8",
        }).done(function (response) {
            if(response == 200) {
                console.log("ddddd")
                alert("회원가입이 완료되었습니다.");
                location = "/";
            } else {
                let warn = "";
                let errors = response["data"];
                console.log(errors + 1);
                if(typeof errors == "object") {
                    console.log("SECOND IF");
                    if(errors.userEmail != null) warn = warn + errors.userEmail + "\n"
                    if(errors.userName != null) warn = warn + errors.userName + "\n"
                    if(errors.password != null) warn = warn + errors.password + "\n"
                    if(errors.nickName != null) warn = warn + errors.nickName + "\n"
                    if(errors.phone != null) warn = warn + errors.phone + "\n"
                    if(errors.birthDate != null) warn = warn + errors.birthDate + "\n"
                    if(errors.gender != null) warn = warn + errors.gender;
                } else {
                    console.log("SECOND ELSE");
                    warn = response["data"];
                }
                alert(warn);
            }
        }).fail(function (error) {
            alert("에러 발생 : " + error);
        });
    },
}

userObject.init();