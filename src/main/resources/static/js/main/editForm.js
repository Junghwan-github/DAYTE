$("#editBttn").on("click", function (){
    let nickName = $("#nickName").val();
    if(!!$("#nickName").val()) {
        nickName = $("#nickName").val();
    } else {
        nickName = $("#nickName").attr('placeholder');
    }
    let user = {
        email: $("#email").text(),
        nickName: nickName,
    }
    let formData = new FormData();
    // formData.append("user", JSON.stringify(user));
    formData.append("userEmail", user.email); // 변경된 부분
    formData.append("nickName", user.nickName); // 변경된 부분
    // 이미지 파일이 선택되었을 때만 추가
    let my_photo = $("#upload")[0];
    if (my_photo.files.length > 0){
        formData.append("image", my_photo.files[0]);
    }
    $.ajax({
        type : "PUT",
        url : "/members/editForm",
        data : formData,
        processData : false,
        contentType: false,
    }).done(function (response) {
            alert("정보 수정이 완료되었습니다.");
            location = "/";
    }).fail(function (error) {
        alert("에러 발생 : " + error);
    })
});

// 닉네임 중복 체크
// $("#editBttn").on("click", function (){
//     let nickName = $("#email").val();
//     $.ajax({
//         type : "POST",
//         url : "/members/editForm" + nickName ,
//         contentType: "application/json; charset=utf-8",
//     }).done(function (response) {
//         if (response.status === 200) {
//             alert("일치하는 닉네임이 없습니다.");
//         } else if (response.status === 409) {
//             alert("이미 있는 닉네임 입니다.");
//         }
//     }).fail(function (error) {
//         alert("에러 발생 : " + error);
//     })
// });

    // 처음 이미지 가져오기
$(function(){
    let photo_path = $('.profile-photo').attr('src');
    let my_photo; // 회원이 업로드할 이미지를 담을 변수

    $('#upload').change(function(){
        my_photo = this.files[0];
        if(!my_photo){
            // 업로드된 이미지가 없을 경우 기존 이미지로 복원
            $('.profile-photo').attr('src', photo_path);
            return;
        }

        if(my_photo.size > 1024*1024){
            // 이미지 크기가 1MB를 초과하는 경우 알림 후 복원
            alert(Math.round(my_photo.size/1024/1024) + 'MB(1MB까지만 업로드 가능)');
            $('.profile-photo').attr('src', photo_path);
            $(this).val(''); // 파일 입력 필드 초기화
            return;
        }

        // 이미지 미리보기 처리
        let reader = new FileReader();
        reader.readAsDataURL(my_photo);

        reader.onload = function(){

            // 이미지 미리보기 업데이트
            $('.profile-photo').attr('src', reader.result);
        };
    });
});