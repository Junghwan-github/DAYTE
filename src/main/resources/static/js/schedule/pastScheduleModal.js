$(document).ready(function () {

    //     모달오픈
    $(".btn-open-modal").on("click", function () {
        let _thisClass = $(this).attr("class").split(" ");
        $(".modal_body").removeClass("active");
        $(".modal_body."+_thisClass[1]).addClass("active");
        $(".daysPrint").show();
        $(".modal_body."+_thisClass[1]+" #tabPage .tab1").addClass("active");
    })

    // 모달 닫기
    $(".material-symbols-outlined").on("click", function () {
        $(".modal_body").removeClass("active");
        $("#tabPage > .display-hide").removeClass("active");
        $(".daysPrint").hide();

    })

    // 상호명 클릭시 새창 컨텐츠 링크
    $(".businessName").on("click", function () {
        let uuidUrl = $(this).attr("data-uuid");
        window.open("/contents/detail/" + uuidUrl, "blank");
    })

    //탭메뉴 영역

    $("#tabMenu li").on("click", function () {
        let _thisClass = $(this).attr("class");
        $("#tabPage > .display-hide").removeClass("active");
        $("#tabPage > ."+_thisClass).addClass("active");
    })

})
