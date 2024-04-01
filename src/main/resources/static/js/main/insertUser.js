let checkedNickName = false;
let userObject = {
    init: function () {
        let _this = this;
        $("#nickCheck").on("click", () => {
            _this.checkNickName();
        }),
            $("#signBttn").on("click", () => {
                if (!pwd2Chk) {
                    alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.")
                    return;
                }
                if (!uNameChk) {
                    alert("이름을 다시 확인해 주세요.")
                    return;
                }
                if (!nickNameChk) {
                    alert("닉네임 양식을 해주세요.")
                    return;
                }
                if (!checkedNickName) {
                    alert("닉네임 중복 확인을 해주세요.")
                    return;
                }
                if (!phoneChk) {
                    alert("휴대전화번호를 다시 확인해 주세요.")
                    return;
                }
                if (!birthChk) {
                    alert("생년월일을 다시 확인해 주세요.")
                    return;
                }
                if (varificationValue) {
                    _this.insertUser();
                } else {
                    alert("이메일 인증이 완료되지 않았습니다.");
                }
            });
    },
    insertUser: function () {
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
            type: "POST",
            url: "/members/joinForm",
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
        }).done(function (response) {
            let status = response["status"];
            if (status == 200) {
                let message = response["data"];
                alert(message);
                location = "/";
            } else {
                let warn = "";
                let errors = response["data"];
                if (typeof errors == "object") {
                    if (errors.userEmail != null) warn = warn + errors.userEmail + "\n"
                    if (errors.userName != null) warn = warn + errors.userName + "\n"
                    if (errors.password != null) warn = warn + errors.password + "\n"
                    if (errors.nickName != null) warn = warn + errors.nickName + "\n"
                    if (errors.phone != null) warn = warn + errors.phone + "\n"
                    if (errors.birthDate != null) warn = warn + errors.birthDate + "\n"
                    if (errors.gender != null) warn = warn + errors.gender;
                } else {
                    warn = response["data"];
                }
                alert(warn);
            }
        }).fail(function (error) {
            alert("에러 발생 : " + error);
        });
    },
    checkNickName: function () {
        let user = {
            nickName: $("#nickName").val(),
        }

        $.ajax({
            type: "POST",
            url: "/members/nickNameCheck/" + user.nickName,
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
        }).done(function (response) {

            let status = response["status"]
            if (status == 200) {
                let message = response["data"];
                alert(message);
                checkedNickName = true;
            } else {
                let error = response["data"];
                alert(error);
            }
        }).fail(function (error) {
            alert("에러 발생" + error);
        })
    },
}

userObject.init();