// $(function(){
//   $('.bxslider').bxSlider({
//     mode: 'fade',
//     captions: true,
//     slideWidth: 1180,
//     pager : false,
//     controls: true,
//     infiniteLoop: false
//   });
// });





let pointerX = $(".pointer-x").text();
let pointerY = $(".pointer-y").text();

let container = document.getElementById("content-map");
let options = {
  center: new kakao.maps.LatLng(pointerX, pointerY),
  level: 4
};
let map = new kakao.maps.Map(container, options);

let imageSrc = '/images/marker.png',
    imageSize = new kakao.maps.Size(25, 33),
    imageOption = {offset: new kakao.maps.Point(13, 33)};


let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(pointerX, pointerY);
let marker = new kakao.maps.Marker({
  position: markerPosition,
  image: markerImage // 마커이미지 설정
});

marker.setMap(map);


// setDraggable();
// function setDraggable(draggable) {
//   // 마우스 드래그로 지도 이동 가능여부를 설정합니다
//   map.setDraggable(draggable);
// }


$(document).ready(function() {
  var divCount = $('div[name="bxslider"] > div').length;
  $('#allImg').text(divCount);
  
});



// 이미지 슬라이더 인덱스 
  $(document).ready(function() {
    var currentImageIndex = 1;

    // 이미지 슬라이더 초기화
    var slider = $('.bxslider').bxSlider();

    // 다음 이미지로 이동
    $('.bx-next').on('click', function() {
      if(currentImageIndex < $('div[name="bxslider"] > div').length) {
        currentImageIndex++;
      } else {
        currentImageIndex = $('div[name="bxslider"] > div').length;
      }
  
      $('#nowImg').text(currentImageIndex);
    });

    // 이전 이미지로 이동
    $('.bx-prev').on('click', function() {
      if(currentImageIndex > 1) {
        currentImageIndex--;
      } else {
        currentImageIndex;
      }

      $('#nowImg').text(currentImageIndex);
    });
  });