
let userObject = {
    init: function (){
        $("#editUserBtn").on("click", () => {
            this.editUser();
        })
    },

    editUser: function (){
        console.log("회원정보 수정 메서드 시작")
        let user = {
            userEmail: $("#userEmail").val(),
            userName : $("#userName").val(),
            nickName : $("#nickName").val(),
            birthDate : $("#birthDate").val(),
            password : $("#password").val(),
            gender : $("#gender").val(),
            phone : $("#phone").val(),
            role : $("#role").find(":selected").val(),
            black : $("#black").find(":selected").val(),
        }

        let formData = new FormData();
        formData.append("userEmail", $("#userEmail").val()); // 변경된 부분
        formData.append("userName", $("#userName").val());
        formData.append("nickName", $("#nickName").val());
        formData.append("birthDate", $("#birthDate").val());
        formData.append("password", $("#password").val());
        formData.append("gender", $("#gender").val());
        formData.append("phone", $("#phone").val());
        formData.append("role",$("#role").find(":selected").val());
        formData.append("black", $("#black").find(":selected").val());
        // 이미지 파일이 선택되었을 때만 추가
        let my_photo = $("#userImage")[0];
        if (my_photo.files.length > 0) {
            formData.append("image", my_photo.files[0]);
        }
        $.ajax({
            // 회원정보 수정 요청
            type : "PUT",
            url : "/admin/editUser",
            data : formData,
            processData: false,
            contentType: false,
        }).done(function (response) {
            if(response.status === 200) {
                alert("회원정보 수정이 완료되었습니다.");
                location = "/admin/home";
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