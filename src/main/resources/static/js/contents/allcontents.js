$(document).ready(function () {

    $(".contentListItemdetailViewBtn").on("click", function () {
        let _this =  $(this).val();
        window.open("/contents/detail/" + _this, "_blank");
    })

})