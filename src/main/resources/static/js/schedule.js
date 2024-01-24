$(document).ready(function () {
    $(".scheduleItemSlider").bxSlider({
        mode: "horizontal",
        slideWidth: 350,
        speed: 500,
        auto: true,
        slideMargin: 0,
        minSlides: 1,
        maxSlides: 1,
        pager: false,
    });
});


$(".nextDayBtn").on("click", function(e) {
    console.log($(e.target).val());
    $(".daysListAddModal").show();
    $(".contentModalSlider").bxSlider({
        mode: "horizontal",
        slideWidth: 250,
        slideMargin: 20,
        minSlides: 1,
        maxSlides: 10,
        pager: false,
    });
    $("body").css("overflow","hidden");
})



let listCartBtn = document.querySelector("#divUpDownButton");
let contentListCart = document.querySelector(".contentListModalArea");

listCartBtn.addEventListener("click", () => {
    contentListCart.classList.toggle("show");
});

// let container = document.getElementById("contentsMap"); //지도를 담을 영역의 DOM 레퍼런스
// let options = {
//   //지도를 생성할 때 필요한 기본 옵션
//   center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
//   level: 5, //지도의 레벨(확대, 축소 정도)
// };

// let map = new kakao.maps.Map(container, options);

let selectedClass = document.querySelector("#schedulePrint");
let contentsSubNavBtn = selectedClass.querySelectorAll(".xi-ellipsis-v");
let menuList = selectedClass.querySelector(".scheduleContentsItem > .menuList");

contentsSubNavBtn.forEach(function (button) {
    button.addEventListener("click", (e) => {
        let scheduleItem = button.closest(".scheduleContentsItem");

        let menuList = scheduleItem.querySelector(".menuList");
        menuList.classList.toggle("show");
        e.preventDefault;
    });
});
