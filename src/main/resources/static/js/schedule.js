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
        infiniteLoop: false
    });
});

$(".nextDayBtn").on("click", function (e) {
    $(".tableUuid").text($(e.target).val());
    $(".daysValue").text($(e.target).data("next-days"));
    $(".daysListAddModal").show();


    let containerEl = document.querySelector('.contentModalSlider');
    sortableInstance = new Sortable(containerEl, {
        animation: 150,
        ghostClass: 'active',
        direction: "horizontal"
    });
    sortableInstance.option("disabled", true);
    $("body").css("overflow", "hidden");

    mouseDrag();

    let container = document.getElementById("rightModalLayout");
    let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
    };
    let map = new kakao.maps.Map(container, options);
});

let slider = document.querySelector('.contentModalSlider');
let isDown = false;
let startX;
let scrollLeft;
let x = "";
let walk = "";

$(".scheduleTotalListModifyBtn").on("click", function (e) {
    $(".scheduleTotalListModifyBtn").hide();
    $(".scheduleTotalListCancelBtn").show();
    $(".contentModalSlider > li > button").show();
    sortableInstance.option("disabled", false);
    $(slider).off('mousedown');
    $(slider).off('mouseleave');
    $(slider).off('mouseup');
    $(slider).off('mousemove');

});


$(".scheduleTotalListCancelBtn").on("click", function () {
    $(".scheduleTotalListCancelBtn").hide();
    $(".scheduleTotalListModifyBtn").show();
    $(".contentModalSlider > li > button").hide();
    sortableInstance.option("disabled", true);
    mouseDrag();
});



function mouseDrag() {

        $(slider).on('mousedown', handleMouseDown);
        $(slider).on('mouseleave', handleMouseLeave);
        $(slider).on('mouseup', handleMouseUp);
        $(slider).on('mousemove', handleMouseMove);

    function handleMouseDown(e) {
        isDown = true;
        startX = e.pageX - slider.offsetLeft;
        scrollLeft = slider.scrollLeft;
    }

    function handleMouseLeave() {
        isDown = false;
    }

    function handleMouseUp() {
        isDown = false;
    }

    function handleMouseMove(e) {
        if (!isDown) return;
        e.preventDefault();
        e.stopPropagation();
        x = e.pageX - slider.offsetLeft;
        walk = x - startX;
        slider.scrollLeft = scrollLeft - walk;
    }
}




    let listCartBtn = document.querySelector("#divUpDownButton");
    let contentListCart = document.querySelector(".contentListModalArea");

    listCartBtn.addEventListener("click", () => {
        contentListCart.classList.toggle("show");
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


