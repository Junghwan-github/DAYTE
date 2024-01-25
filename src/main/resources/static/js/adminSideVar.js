$(document).ready(function(){
  $('#sideVarList li button').click(function(){
    var $btn = $(this);
    var $collapseDiv = $btn.next();

    $collapseDiv.off('hide.bs.collapse show.bs.collapse');

    $collapseDiv.on('hide.bs.collapse', function(){
      $btn.find('img').attr("src", "/resources/static/images/chevron-down.svg");
    });
    $collapseDiv.on('show.bs.collapse', function(){
      $btn.find('img').attr("src", "/resources/static/images/chevron-up.svg");
    });
    
  });
})