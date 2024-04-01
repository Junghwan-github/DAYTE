let isNicknameChecked = false;
let oldNickName = $("#nickName").val();
let nickName = null;
let changeNickName = $("#nickName").val();
function validateNickName(value) {
    return /^[가-힣a-zA-Z0-9]{2,10}$/.test(value);
}

$("#nickNameChk").on("click", function () {
    nickName = $("#nickName").val()

    if (!!nickName) {
        if (validateNickName(nickName)) {
            $("#err1").text("");
        } else {
            $("#err1").text("닉네임은 2 ~ 10글자, 특수문자는 사용할 수 없습니다.")
            return;
        }
    } else {
        $("#err1").text("닉네임을 입력해주세요.");
        return;
    }

    $.ajax({
        url        : "/members/nickNameCheck/" + nickName,
        type       : "POST",
        contentType: "application/json; charset=utf-8",
    }).done(function (response) {
        if (response.status == 200) {
            $("#err1").text("사용할 수 있는 닉네임 입니다.");
            isNicknameChecked = true;
            changeNickName = $("#nickName").val();
        } else if (response.status == 409) {
            isNicknameChecked = false;
            $("#err1").text("이미 사용 중인 닉네임입니다.");
        } else {
            $("#err1").text("");
        }
    }).fail(function (error) {
        alert("에러 발생 : " + error);
    })
})
;

$("#editBttn").on("click", function () {
    nickName = $("#nickName").val()
    if (nickName == oldNickName) {
        isNicknameChecked = true;
    }

    if (!isNicknameChecked || nickName != changeNickName) {
        alert("닉네임 중복을 먼저 확인하세요.");
        return;
    }

    let user = {
        nickName: nickName,
        phone   : $("#phone").val(),
    }

    let formData = new FormData();
    formData.append("nickName", user.nickName); // 변경된 부분
    formData.append("phone", user.phone); // 변경된 부분

    // 이미지 파일이 선택되었을 때만 추가
    let my_photo = $("#upload")[0];
    if (my_photo.files.length > 0) {
        formData.append("image", my_photo.files[0]);
    }
    $.ajax({
        type       : "PUT",
        url        : "/members/editForm",
        data       : formData,
        processData: false,
        contentType: false,
    }).done(function (response) {
        alert("정보 수정이 완료되었습니다.");
        location.reload()
    }).fail(function (error) {
        alert("에러 발생 : " + error);
    })
});

$(function () {
    // 처음 이미지 가져오기
    let photo_path = $('.profile-photo').attr('src');
    let my_photo; // 회원이 업로드할 이미지를 담을 변수

    $('#upload').change(function () {
        my_photo = this.files[0];
        if (!my_photo) {
            // 업로드된 이미지가 없을 경우 기존 이미지로 복원
            $('.profile-photo').attr('src', photo_path);
            return;
        }

        if (my_photo.size > 10240 * 1024) {
            // 이미지 크기가 1MB를 초과하는 경우 알림 후 복원
            alert(Math.round(my_photo.size / 1024 / 1024) + 'MB(10MB까지만 업로드 가능)');
            $('.profile-photo').attr('src', photo_path);
            $(this).val(''); // 파일 입력 필드 초기화
            return;
        }

        // 이미지 미리보기 처리
        let reader = new FileReader();
        reader.readAsDataURL(my_photo);

        reader.onload = function () {

            // 이미지 미리보기 업데이트
            $('.profile-photo').attr('src', reader.result);
        };
    });
});
