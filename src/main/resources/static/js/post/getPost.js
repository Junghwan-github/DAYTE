$(document).ready(function () {

    $("#post-modify-delete-btn").click(function (e) {
        e.preventDefault();
        $("#post-modify-delete-menu").toggle();
    })

    $("#reply-show-hide-btn").click(function(e) {
        e.preventDefault();
        $(".post-content-reply-container").toggle();
    })

    $(".post-reply-modify-delete-btn").on("click", function () {
      let _this = $(this).parent();
      $(_this).find(".reply-sub-nav-plate").toggle();
    })

    $(".replyBtnShow").on("click", function () {
        let _this = $(this);
        $(this).hide();
        $(this).parent().find(".checkButton").show();
        $(this).closest(".reply-sub-nav-plate > ul").find("#btn-delete-reply").hide();
        $(this).closest(".reply-sub-nav-plate > ul").find(".cancelButton").show();
    })

    $(".cancelButton").on("click", function () {
        let _this = $(this);
        $(this).hide();
        $(this).parent().find("#btn-delete-reply").show();
        $(this).closest(".reply-sub-nav-plate > ul").find(".checkButton").hide();
        $(this).closest(".reply-sub-nav-plate > ul").find(".replyBtnShow").show();
    })
})