$(document).ready(function () {

    $(".top-wrapper > span").on("click", function () {
    $(this).parent().next().toggle(200);
    })
})