let indexMainSliderObject = {
    init: function () {
        let _this = this;
        $("#indexMainSliderSubmit").on("click", () => {

                _this.insertIndexMainSlider();
                console.log("눌림");
        });
    },
    insertIndexMainSlider: function () {
        console.log("1번");
        let content = {
            images : $("#images").val(),
            category : $("#category").val(),
           mainTitle : $("#mainTitle").val(),
            subTitle : $("#subTitle").val(),
            schedule : $("#schedule").val(),
            address : $("#address").val(),
            summary : $("#summary").val()
        }
        $.ajax({
            type : "POST",
            url : "/admin/home/registration/indexmainslider",
            data : JSON.stringify(content),
            contentType : "application/json; charset=utf-8",
        }).done(function (response) {
                location.reload();

        }).fail(function (error) {
            alert("에러 발생 : " + error);
        });
    },
}

indexMainSliderObject.init();