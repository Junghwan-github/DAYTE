function strongPassword(str) {
    return /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*?_]).{8,20}$/.test(str);
}
let userEmail
// 사용자의 이메일을 매개변수로 넘겨 User 테이블 조회
$("#updatePwdBtn").on('click', function () {

    userEmail = $("#userEmail").val()
    fetch("/members/findPwd/" + userEmail, {
        method : "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        }
    }).then(res => {
        return res.json();
    }).then(data => {
        if (data["status"] == 200) {
            alert("인증번호가 발송되었습니다.")
            sendEmail(userEmail);
        } else if (data["status"] == 400){
            alert(data["data"])
        }
    }).catch(err => {
        alert(err)
    })
});

// 사용자의 이메일 주소로 이메일 전송
function sendEmail(userEmail) {
    let verificationDTO = {
        address: userEmail
    }
    fetch("/members/sendEmail", {
        method : "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify(verificationDTO)
    }).then(res => {
        return res.json();
    }).then(data => {
    }).catch(err => {
        alert(err)
    })
}

// 뷰 단에서 사용자가 입력한 값과 비교
$("#checkNumberBtn").on('click', function () {
    fetch("/members/checkEmail", {
        method : "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify($("#checkNumber").val())
    }).then(res => {
        return res.json();
    }).then(data => {
        if (data == 200){
            alert("인증되었습니다.");
                let form = document.querySelector('#checkPwdForm');
                form.action = "/members/changePwd";
                form.submit();
        } else if (data == 400)
            alert("인증번호가 일치하지 않습니다.")
    }).catch(err => {
        alert(err)
    })
});

// 비밀번호 변경
$("#changePwdBtn").on('click', function () {
    let userDTO = {
        userEmail: $(this).val(),
        password: $("#confirmPwd").val()
    }
    let newPwd = $("#newPwd").val();
    let confirmPwd = $("#confirmPwd").val();
    if (newPwd.length < 8 || newPwd.length > 20) {
        $("#err1").text("비밀번호는 8~20글자 사이로 입력해야 합니다.")
        return;
    }
    if (!strongPassword(newPwd)) {
        $("#err1").text("비밀번호는 영문, 숫자, 특수문자(@, $, !, %, *, #, ?, &)를 사용하여야 합니다.")
        return;
    }
    if (!newPwd == confirmPwd) {
        $("#err1").text("입력한 비밀번호가 틀립니다.")
        return;
    }

    fetch("/members/changePwd", {
        method : "PUT",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify(userDTO)
    }).then(res => {
        return res.json();
    }).then(data => {
        if (data["status"] == 200){
            alert(data["data"]);
            location.href = "/members/login";
        } else if (data["status"] == 400)
            alert(data["data"])
    }).catch(err => {
        alert(err)
    })
});
