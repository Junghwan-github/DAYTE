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