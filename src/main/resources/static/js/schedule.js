$(document).ready(function () {
    $(".scheduleItemSlider").bxSlider({
        mode: "horizontal",
        slideWidth: 350,
        speed: 500,
        auto: true,
        slideMargin: 10,
        minSlides: 1,
        maxSlides: 1,
        pager: false,
    });
});

let scheduleItems = document.querySelectorAll(".scheduleContentsItem");

scheduleItems.forEach(function (scheduleItem) {
    let contentsSubNavBtn = scheduleItem.querySelector(".xi-ellipsis-v");

    contentsSubNavBtn.addEventListener("click", function (e) {
        let menuList = scheduleItem.querySelector(".menuList");

        if (menuList) {
            menuList.classList.toggle("show");
        }

        e.preventDefault();
    });
});

