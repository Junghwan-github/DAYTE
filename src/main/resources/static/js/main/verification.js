let varificationValue = false;
let verificationBtn = {
    init: function () {
        let _this = this;
        $("#sendEmail").on("click", () => {
            /* $("#verification").removeClass("hide");*/

            _this.verificationEmail();

        });
        $("#numCheck").on("click", () => {
            _this.checkEmail();
        });
    },
    verificationEmail: function () {
        let emailAndDomain = $("#id").val() + "@" + $("#emailDomain").val();
        console.log(emailAndDomain);
        let email = {
            address: emailAndDomain
        }

        $.ajax({
            type: "POST",
            url: "/members/sendEmail",
            data: JSON.stringify(email),
            contentType: "application/json; charset=utf-8"

        })
            .done(function (res) {

            })
            .fail(function (err) {

            });
    },
    checkEmail: function () {
        let emailNum = $("#emailNum").val();
        $.ajax({
            type: "POST",
            url: "/members/checkEmail",
            data: JSON.stringify(emailNum),
            contentType: "application/json; charset=utf-8"
        })
            .done(function (res) {
                if(res === null) {
                    alert("인증번호가 입력되지 않았습니다.")
                }
                else if(res === 200) {
                    alert("인증이 완료되었습니다.");
                    varificationValue = true;
                } else {
                    alert("인증번호가 틀립니다.");
                }
            })
            .fail(function (err) {
                alert("에러발생 :" + err);
            });
    },

};
verificationBtn.init();



let timer = null;
let isRunning = false;


$("#sendEmail").on("click", function() {
    $("#numCheck").attr("disabled", false);
    let display = $(".timer > span");
    // 유효시간 설정
    let leftSec = 300;

    // 버튼 클릭 시 시간 연장
    if (isRunning){
        clearInterval(timer);
        display.html("");
        startTimer(leftSec, display);
    }else{
        startTimer(leftSec, display);
    }
});

function startTimer(count, display) {
    var minutes, seconds;
    timer = setInterval(function () {
        minutes = parseInt(count / 60, 10);
        seconds = parseInt(count % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.html(minutes + ":" + seconds);

        // 타이머 끝
        if (--count < 0) {
            clearInterval(timer);
            alert("시간초과");
            display.html("시간초과");
            $("#numCheck").attr("disabled", true);
            isRunning = false;
        }
    }, 1000);
    isRunning = true;
}


