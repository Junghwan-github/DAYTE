
$(document).ready(function () {


    $(".content-banner-slider").bxSlider({
        mode: "horizontal",
        slideWidth: 320,
        speed: 500,
        auto: true,
        slideMargin: 0,
        minSlides: 1,
        maxSlides: 1,
        touchEnabled: navigator.maxTouchPoints > 0,
        pager: false,
        controls: false
    });
});


let pointerX = $(".pointer-x").text();
let pointerY = $(".pointer-y").text();

let container = document.getElementById("content-map");
let containerContent = document.querySelector(".map-xy");
let options = {
    center: new kakao.maps.LatLng(pointerX, pointerY),
    level: 4
};
let map = new kakao.maps.Map(container, options);
let mapContent = new kakao.maps.Map(containerContent, options);
let imageSrc = '/images/marker.png',
    imageSize = new kakao.maps.Size(25, 33),
    imageOption = {offset: new kakao.maps.Point(13, 33)};


let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(pointerX, pointerY);
let marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage // 마커이미지 설정
});

let markerContet = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage // 마커이미지 설정
});
marker.setMap(map);
markerContet.setMap(mapContent);


setDraggable();

function setDraggable(draggable) {
    // 마우스 드래그로 지도 이동 가능여부를 설정합니다
    map.setDraggable(draggable);
}


function openTab(tabId) {
    $(".tab-content").removeClass("active");

    $("#" + tabId).addClass("active");
}

$(".tab").click(function (e) {
    $(this).addClass("atv");

    $(".tab").not(this).removeClass("atv");
})

$(document).ready(function() {
    let navLinks = $('#content-sub-nav a');
    let contentSubNav = $('#content-sub-nav');

    // 이전 스크롤 위치를 저장하기 위한 변수
    let prevScrollPos = 0;

    // 스크롤 이벤트 감지
    $(window).scroll(function() {
        let scrollPos = $(document).scrollTop();

        // content-sub-nav의 top 속성을 조절
        if (scrollPos > prevScrollPos) {
            // 스크롤 다운일 때
            contentSubNav.addClass('scrolled-nav');
            contentSubNav.removeClass('fixed-nav');
        } else {
            // 스크롤 업일 때
            if (scrollPos === 0) {
                // 최상단에 도달하면 모든 클래스 제거
                contentSubNav.removeClass('fixed-nav scrolled-nav');
            } else {
                // 스크롤 업 중에는 fixed-nav 클래스 추가
                contentSubNav.removeClass('scrolled-nav');
                contentSubNav.addClass('fixed-nav');
            }
        }

        // 이전 스크롤 위치 업데이트
        prevScrollPos = scrollPos;

        // 각 섹션의 위치를 순회하면서 현재 보이는 섹션을 감지
        $('section').each(function() {
            let top = $(this).offset().top - 135;
            let bottom = top + $(this).outerHeight();

            if (scrollPos >= top && scrollPos <= bottom) {
                let targetId = $(this).attr('id');
                navLinks.removeClass('active');
                $('[href="#' + targetId + '"]').addClass('active');
            }
        });
    });
});
$(document).ready(function () {
    let star = $(".star-point");
    switch (star.text()) {
        case "1":
           star.text("★");
           break;
        case "2":
            star.text("★★");
            break;
        case "3":
            star.text("★★★");
            break;
        case "4":
            star.text("★★★★");
            break;
        case "5":
            star.text("★★★★★");
            break;
    }

    $("#mainContentsImages").on("click", function () {
        $(".images-detail-modal").show();
        $("body").css("overflow", "hidden");
        $(".images-detail-slider").bxSlider({
            mode: "horizontal",
            slideMargin: 10,
            speed: 500,
            minSlides: 1,
            maxSlides: 1,
            pager: false,
            auto: false,
            controls: true
        });

    })

    $(".images-detail-wrapper > h3 > i").on("click",function () {
        $("body").css("overflow", "");
        $(".images-detail-modal").hide();
    })
})


