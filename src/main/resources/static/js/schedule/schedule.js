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
let slider = document.querySelector('.contentModalSlider');

$(".nextDayBtn").on("click", function (e) {
    $(".tableUuid").text($(e.target).val());
    $(".daysValue").text($(e.target).data("now-days"));
    $(".daysListAddModal").show();
    $(".scheduleTotalModifyBtn").hide();


    let containerEl = document.querySelector('.contentModalSlider');
    sortableInstance = new Sortable(containerEl, {
        animation: 150,
        ghostClass: 'active',
        direction: "horizontal",
        filter: '.contentsListItemDelete'
    });
    sortableInstance.option("disabled", true);


    $("body").css("overflow", "hidden");
    let slider = document.querySelector('.contentModalSlider');

    mouseDrag(slider);

    let container = document.getElementById("rightModalLayout");
    let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
    };
    let map = new kakao.maps.Map(container, options);
});

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
    mouseDrag(slider);
});



function mouseDrag(select) {

        $(select).on('mousedown', handleMouseDown);
        $(select).on('mouseleave', handleMouseLeave);
        $(select).on('mouseup', handleMouseUp);
        $(select).on('mousemove', handleMouseMove);

    function handleMouseDown(e) {
        isDown = true;
        startX = e.pageX - select.offsetLeft;
        scrollLeft = select.scrollLeft;
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
        x = e.pageX - select.offsetLeft;
        walk = x - startX;
        select.scrollLeft = scrollLeft - walk;
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

function detailedLinks(uuid) {

    $(".detailedScheduleAddModal").show();
    $(".schedule-tb-list").hide();
    $("."+uuid).show();
    $("body").css("overflow","hidden");
    let detailList = $("."+uuid).height();
    if(detailList > 800) {
        $(".schedule-tb-list").height(800);
    }
        $(".detailedScheduleListUl").each(function () {
            $(this).on('mousedown', handleMouseDown);
            $(this).on('mouseleave', handleMouseLeave);
            $(this).on('mouseup', handleMouseUp);
            $(this).on('mousemove', handleMouseMove);

            function handleMouseDown(e) {
                isDown = true;
                startX = e.pageX - this.offsetLeft;
                scrollLeft = this.scrollLeft;
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
                x = e.pageX - this.offsetLeft;
                walk = x - startX;
                this.scrollLeft = scrollLeft - walk;
            }
        })

}

$(".detail-daysPrint-button").on("click", function () {
    $(".detailedScheduleAddModal").hide();
    $(".daysListAddModal").show();
    $(".contentListModalArea").addClass("show");
    $(".scheduleTotalSaveBtn").hide();
    $(".scheduleTotalModifyBtn").show();

    let container = document.getElementById("rightModalLayout");
    let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
    };
    let map = new kakao.maps.Map(container, options);

})
