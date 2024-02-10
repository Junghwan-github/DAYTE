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
        let postReplyContentText = $(_this).closest("#post-reply-items").find(".changeTextarea");
        let pToTextarea = $("<textarea class='changedTextarea'>").text(postReplyContentText.text());
        let pToHeight = Math.max(postReplyContentText.height(),150);
        $(_this).hide();
        $(_this).parent().find(".checkButton").show();
        $(_this).closest(".reply-sub-nav-plate > ul").find("#btn-delete-reply").hide();
        $(_this).closest(".reply-sub-nav-plate > ul").find(".cancelButton").show();

        $(postReplyContentText).replaceWith(pToTextarea.height(pToHeight));
    })

    $(".cancelButton").on("click", function () {
        let _this = $(this);
        let postReplyContentText = $(_this).closest("#post-reply-items").find(".changedTextarea");
        let textareaToP = $("<p class='changeTextarea'>").text(postReplyContentText.text());

        $(_this).hide();
        $(_this).parent().find("#btn-delete-reply").show();
        $(_this).closest(".reply-sub-nav-plate > ul").find(".checkButton").hide();
        $(_this).closest(".reply-sub-nav-plate > ul").find(".replyBtnShow").show();
        $(_this).closest(".reply-sub-nav-plate").hide();
        $(postReplyContentText).replaceWith(textareaToP);
    })

    $(".checkButton").on("click", function () {
        let _this = $(this).val();


    })
})