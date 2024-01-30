$(function () {
    let naviScroll = 0;
    console.log($(document).scrollTop());
    $(window).scroll(function () {
        if ($(document).scrollTop() == 0) {
            $("header").removeClass("naviHide");
        } else if ($(document).scrollTop() > naviScroll) {
            $("header").attr("class", "naviDown");
        } else {
            $("header").attr("class", "naviHide");
        }
        naviScroll = $(document).scrollTop();
    });
});
