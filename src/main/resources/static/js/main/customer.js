let sendQuestionToEmail = {
    init: function () {
        $("#sendEmail").on("click", () => {
            this.sendQuestion();
        });
    },
    sendQuestion: function () {

        if(!$(".invalid").length){

            let validCheck = {
                emailAdress: $("#inquiry-email"),
                title: $("#inquiry-title"),
                content: $("#inquiry-content"),
                mainCategory : $("#mainCategory"),
                subCategory : $("#subCategory")
            }

            let formData = new FormData();

            for (let arg in validCheck) {
                if(validCheck[arg].val() == null || validCheck[arg].val() == ""){
                    if(arg == "emailAdress"){
                        validCheck[arg].after(`<span class="invalid" id="invalidEmail" style="color: red">유효하지 않은 이메일 형식입니다</span>`)
                        validCheck[arg].focus();
                        return;
                    } else if(arg == "mainCategory"){
                        alert("문의 분류를 선택해주세요.");
                        validCheck[arg].focus();
                        return;
                    } else if(arg == "subCategory"){
                        alert("문의 분류를 선택해주세요.");
                        validCheck[arg].focus();
                        return;
                    } else if(arg == "title"){
                        validCheck[arg].after(`<span class="invalid" id = "invalidTitle" style="color: red">제목을 입력해주세요(20자 이내)</span>`)
                        validCheck[arg].focus();
                        return;
                    } else if(arg == "content"){
                        validCheck[arg].after(`<span class="invalid" id = "invalidContent" style="color: red">문의하실 내용을 입력해주세요</span>`)
                        validCheck[arg].focus();
                        return;
                    }

                }else{
                    formData.append(arg, validCheck[arg].val());
                }
            }

            let filesInput = $("#file-input")[0].files; // jQuery 객체를 통해 DOM 요소에 접근

            if (filesInput.length > 0) {
                for (let i = 0; i < filesInput.length; i++) {
                    formData.append('files', filesInput[i]);
                }
            }

            $("#sendEmail").prop("disabled", true);
            $("#mainCategory").prop("disabled", true);
            $("#subCategory").prop("disabled", true);
            $("#inquiry-email").prop("disabled", true);
            $("#inquiry-title").prop("disabled", true);
            $("#inquiry-content").prop("disabled", true);

            $.ajax({
                 type: "POST",
                 url: "/question",
                data: formData,
                contentType: false,
                processData: false
             })
             .done(function (res) {
                 alert("문의접수가 완료되었습니다.");
                 location = "/customerService";
             })
             .fail(function (err) {
                 alert("이메일 접수를 실패하셨습니다.");
                 location.reload();
             });
        } else{
            $(".invalid").focus();
        }
    }
};

sendQuestionToEmail.init();


