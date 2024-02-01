$(function () {
    let naviScroll = 0;
    console.log($(document).scrollTop());
    $(window).scroll(function () {
        if ($(document).scrollTop() == 0) {
            $("header").removeClass("naviHide");
            $("#searchForm").css("margin-top", "430px");
        } else if ($(document).scrollTop() > naviScroll) {
            $("header").attr("class", "naviDown");
            $("#searchForm").css("margin-top", "430px");
        } else {
            $("header").attr("class", "naviHide");
            $("#searchForm").css("margin-top", "510px");
        }
        naviScroll = $(document).scrollTop();
    });
});


$(".userProfile > a").click(function (e) {
    e.preventDefault();
    $(".userProfile > a").toggleClass("sub-nav-active");
    $(".sub-nav-slide-bar").slideToggle(100);
})