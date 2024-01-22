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

let selectedClass = document.querySelector("#schedulePrint");
let contentsSubNavBtn = selectedClass.querySelectorAll(".xi-ellipsis-v");
let menuList = selectedClass.querySelector(".scheduleContentsItem > .menuList");

contentsSubNavBtn.forEach(function (button) {
    button.addEventListener("click", (e) => {
        let scheduleItem = button.closest(".scheduleContentsItem");

        // 해당 scheduleItem에서 .menuList를 찾아 클래스를 토글
        let menuList = scheduleItem.querySelector(".menuList");
        menuList.classList.toggle("show");
        e.preventDefault;
    });
});
