let userObject = {
    init: function () {
        $("#delUserBtn").click(() => {
            let check = confirm("회원 탈퇴를 하시겠습니까?");
            if (check) {
                this.deleteUser();
            }
            return;
        });
    },
    deleteUser: function () {
        let user = {
            userEmail: $("#email").val(),
            password: $("#password").val(),
        }
        $.ajax({
            type: "DELETE",
            url: "/members/delForm/" + user.userEmail,
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
        }).done(function (response) {
            alert("회원 탈퇴가 완료되었습니다.")
            location = "/members/logout";
        }).fail(function (error) {
            alert("에러 발생 :" + error);
        });
    },

}
userObject.init();