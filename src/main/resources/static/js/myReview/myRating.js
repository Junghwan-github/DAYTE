$(document).ready(function () {

    $(".star-point").each(function () {
        let star = parseInt($(this).text());
        switch (star) {
            case 1:
                $(this).text("★");
                break;
            case 2:
                $(this).text("★★");
                break;
            case 3:
                $(this).text("★★★");
                break;
            case 4:
                $(this).text("★★★★");
                break;
            case 5:
                $(this).text("★★★★★");
                break;
        }
    })

    $(".xi-ellipsis-v").on("click", function () {
        $(this).closest(".reviewRatingBox").find(".modify-delete-button").toggle();
    })

})

$(".modify").on("click", function (e){
    if (confirm('리뷰를 수정 하시겠습니까?')) {
        let uuid = $(e.target).val();
        location.href = "/modReview/" + uuid;
    }
})

$(".delete").on("click", function (e){
    if (confirm('리뷰를 삭제 하시겠습니까?')) {
        let num = $(e.target).val();

        $.ajax({
            url        : "/contentReply/" + num,
            type       : "DELETE",
            contentType: "application/json; charset=utf-8",
            success    : function (data) {
                alert(data.data);
                location.reload();
            },
            error      : function (error) {
                alert("error");
            }
        });
    }
});
