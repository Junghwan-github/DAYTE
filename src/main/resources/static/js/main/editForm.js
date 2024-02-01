function editForm(email)  {
    let user = {
        userEmail: email
    }
    $.ajax({
        type : "POST",
        url : "/members/editForm",
        data : JSON.stringify(user),
        contentType : "application/json; charset=utf-8",
    }).done(function (response) {
        location = "/members/editForm";
    }).fail(function (error) {
        alert("에러 발생 : " + error);
    });
}