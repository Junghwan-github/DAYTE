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
let map;
let imageSrc = '/images/marker.png',
    imageSize = new kakao.maps.Size(25, 33),
    imageOption = {offset: new kakao.maps.Point(13, 33)};
let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

$(".nextDayBtn").on("click", function (e) {
    $(".tableUuid").text($(e.target).val());
    $(".daysValue").text($(e.target).data("now-days"));
    $(".daysListAddModal").slideDown(150);
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

    mouseDrag(slider);

    let container = document.getElementById("rightModalLayout");
    let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
    };
    map = new kakao.maps.Map(container, options);
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

/*listCartBtn.addEventListener("click", () => {
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
});*/

function detailedLinks(uuid) {

    $(".detailedScheduleAddModal").show();
    $(".schedule-tb-list").hide();
    $("." + uuid).show();
    $("body").css("overflow", "hidden");
    let detailList = $("." + uuid).height();
    if (detailList > 800) {
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
let nowDateValue;
$(".detail-daysPrint-button").on("click", function () {
    let detailedScheduleList = [];
    nowDateValue = $(this).closest('.detailedScheduleDiv').data('now-days');

    $(".detailedScheduleAddModal").hide();
    $(".daysListAddModal").show({

    });
    $(".contentListModalArea").addClass("show");
    $(".scheduleTotalSaveBtn").hide();
    $(".scheduleTotalModifyBtn").show();

    // 담아놓은 컨텐츠를 비교하여 같은 날짜 인것만 넘기는 forEach
    $(".detailedScheduleListUl li").each(function () {
        if ($(this).closest('.detailedScheduleDiv').data('now-days') === nowDateValue) {
            let contentId = $(this).find('.detailedScheduleListId').val(); // .detailedScheduleListId의 value를 가져옴
            let businessName = $(this).find('span').text();
            let matchingContentBtns = $(`.contentListItemAddBtn[value="${contentId}"]`);
            detailedScheduleList.push(contentId);
            itemArr.push(contentId);

            matchingContentBtns.each(function () {
                // Retrieve other details using the found button
                let contentListItem = $(this).closest('.contentListItems');
                let latitude = contentListItem.find('.contentListItemPoint-x').text();
                let longitude = contentListItem.find('.contentListItemPoint-y').text();
                let sliderItemImages = contentListItem.find('.contentListItemsImages img').attr('src');
                // Append selected items to the contentModalSlider
                $(".contentModalSlider").append(`<li class='contentsListItemSelected'><span class="contentListItemPoint-x">${latitude}</span><span class="contentListItemPoint-y">${longitude}</span><div><img src='${sliderItemImages}'/></div><span>${businessName}</span><button type='button' class='contentsListItemDelete' value='${detailedScheduleList[detailedScheduleList.length - 1]}'><i class="xi-close-min"></i></button></li>`);
            });
        }
    });

    // Add click event for contentsListItemDelete
    $(".contentsListItemDelete").on("click", function (e) {
        e.stopPropagation();
        $(this).parent().remove();
        itemArr.splice($(this).val(), 1);
    });

    let container = document.getElementById("rightModalLayout");
    let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5
    };
    let map = new kakao.maps.Map(container, options);

    let containerEl = document.querySelector('.contentModalSlider');
    sortableInstance = new Sortable(containerEl, {
        animation: 150,
        ghostClass: 'active',
        direction: "horizontal",
        filter: '.contentsListItemDelete'
    });
    sortableInstance.option("disabled", true);

    mouseDrag(slider);

});

$(".contentListItemdetailViewBtn").click(function () {
    let getUrl = $(this).val();
    window.open("/contents/detail/"+getUrl , "_blank");
})

let marker = '';
$('.contentListViewer > li').on('click', function (e) {

    // 상세보기와 추가하기를 제외한 li 요소를 누르면 마커 생성
    if ($(e.target)[0].localName !== 'button') {
        let latitude = $(e.target).closest('.contentListItems').find('.contentListItemPoint-x').text();
        let longitude = $(e.target).closest('.contentListItems').find('.contentListItemPoint-y').text();
        if (marker != '') {
            marker.setMap(null);
        }
        let moveLatLng = new kakao.maps.LatLng(latitude, longitude);
        marker = new kakao.maps.Marker({
            position: moveLatLng,
            image: markerImage
        });

        marker.setMap(map);
        map.panTo(moveLatLng);

    }
});