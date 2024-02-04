$(function () {
    let naviScroll = 0;
    $(window).scroll(function () {
        if ($(document).scrollTop() == 0) {
            $("body").css("paddingTop", "0px");
            $("header").removeClass("naviHide");
        } else if ($(document).scrollTop() > naviScroll) {
            $("body").css("paddingTop", "0px");
            $("header").attr("class", "naviDown");
        } else {
            $("body").css("paddingTop", "80px");
            $("header").attr("class", "naviHide");

        }
        naviScroll = $(document).scrollTop();
    });
});

$(".userProfile").click(function (e) {
    e.preventDefault();
    $(".sub-nav-slide-bar").slideToggle(100);
})