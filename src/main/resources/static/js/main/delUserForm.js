let userObject = {
    init: function () {
        $("#delUserBtn").click(() => {
            let check = confirm("회원 탈퇴를 하시겠습니까?");
            if (check) {
                this.deleteUser();
            }
            return;
        }),
            $(".socialDelUserBtn").on('click', () => {
                this.deleteSocialUser();
            });
    },
    deleteUser: function () {
        let user = {
            userEmail: $("#email").val(),
            password: $("#password").val(),
        }
        $.ajax({
            type: "PUT",
            url: "/members/delForm/" + user.userEmail,
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
        }).done(function (response) {
            let status = response["status"];
            if(status == 200 ){
                let message = response["data"];
                alert(message);
                location = "/members/logout";
            } else {
                let warn = response["data"];
                alert(warn);
            }
        }).fail(function (error) {
            alert("에러 발생 :" + error);
        });
    },
    // 소셜 회원탈퇴
    deleteSocialUser: function (socialName) {
        $.ajax({
            type: "PUT",
            url: "/members/delForm/",
            contentType: "application/json; charset=utf-8",
        }).done(response => {
            if(response["status"] == 200){
                if (response["socialName"] === "kakao") {
                    kakaoDelete(response["accessToken"]);
                } else if (response["socialName"] === "Naver") {
                    naverDelete(response["client"], response["accessToken"]);
                } else if (response["socialName"] === "Google") {
                    googleDelete(response["accessToken"]);
                }
                alert(response["data"]);
                location = "/members/logout";
            }
        }).fail(error => {
            alert("에러 발생 :" + error);
        });
    },
}

function kakaoDelete(accessToken) {
    console.log(accessToken);

    $.ajax({
        type: "POST",
        url: "https://kapi.kakao.com/v1/user/unlink",
        // contentType: "application/x-www-form-urlencoded",
        headers: {
            Authorization: `Bearer ${accessToken["tokenValue"]}`,
            "content-type": "application/x-www-form-urlencoded"
        },
    }).done(response => {}
    ).fail(error => {});
}
function naverDelete(client, accessToken) {
    console.log(accessToken["tokenValue"]);
    console.log(client["clientId"]);
    console.log(client["clientSecret"]);

    const naverUrl =
        `client_id=${client["clientId"]}&
        client_secret=${client["clientSecret"]}&
        access_token=${accessToken["tokenValue"]}&
        grant_type=delete&
        service_provider=NAVER`;

    $.ajax({
        type: "POST",
        url: "https://nid.naver.com/oauth2.0/token?" + naverUrl
    }).done(response => {}
    ).fail(error => {}
    );
}

function googleDelete(accessToken) {
    console.log(accessToken["tokenValue"]);

    $.ajax({
        type: "POST",
        url: "https://oauth2.googleapis.com/revoke?token=" + accessToken["tokenValue"]
    }).done(response => {}
    ).fail(error => {});

}
userObject.init();