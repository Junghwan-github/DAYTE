let userObject = {
    //init() 함수를 선언
    init: function() {


        //#btn-save 버튼에 click 이벤트가 발생하면 insertUser() 함수를 호출
        $("#submitBttn").on("click", () => {
            this.insertUser();
            console.log("hi");
        });
    },


    insertUser: function (){
        // alert("회원가입 요청됨");

        let user ={
            email : $("#email").val(),
            password : $("#password").val(),
            name : $("#username").val(),
            nickName : $("#nickName").val(),
            tel : $("#tel").val(),
            birthDay: $("#birthDay").val()
        }
        console.log(user);
        $.ajax({
            type:"post",  //요청 방식
            url: "/auth/sign", //요청 경로
            data : JSON.stringify(user),  //user 객체를 json 형식으로 변환
            // HTTP의 body에 설정되는 데이터 마임타입
            contentType: "application/json; charset=utf-8",
            //응답으로 들어온 Json데이터를 response로 받겠다.  보내는 객체 타입이 json이라는 것을 알려주는 것
        }).done(function (response){
            // 요청 성공 후 응답 결과를 인자 값으로 받을 수 있다.
            console.log(response);
            location = "/";
        }).fail(function(error){
            // 요청 실패하면 예외가 발생하고 해당 결과를 인자 값으로 받을 수 있다.
            alert("에러발생 : " + error);
        });

        /*$.ajax({
            type:"post",  //요청 방식
            url: "/auth/sign", //요청 경로
            data : JSON.stringify(user),  //user 객체를 json 형식으로 변환
            // HTTP의 body에 설정되는 데이터 마임타입
            contentType: "application/json; charset=utf-8",
            //응답으로 들어온 Json데이터를 response로 받겠다.  보내는 객체 타입이 json이라는 것을 알려주는 것
        }).done(function (response){
            let status = response["status"]
            if(status == 200){
                let message = response["data"];
                alert(message);
                location = "/";
            } else{
                let warn = "";
                let errors = response["data"];
                if(typeof errors == "object"){
                    if(errors.username != null) warn = warn + errors.username + "\n"
                    if(errors.password != null) warn = warn + errors.password + "\n"
                    if(errors.email != null) warn = warn + errors.email + "\n"
                } else {
                    warn = response["data"];
                }
                alert(warn);
            }

        }).fail(function(error){
            // 요청 실패하면 예외가 발생하고 해당 결과를 인자 값으로 받을 수 있다.
            alert("에러발생 : " + error);
        })*/



    },  // 객체에는 또 무언가를 추가할 수 있기에 쉼표 작성, 쉼표 있어도 에러 안 뜸

}

//userObject 객체의 init() 함수 호출
userObject.init();    //이거만 js고 위에는 jquery