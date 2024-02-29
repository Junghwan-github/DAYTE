let indexMainSliderObject = {
    init: function () {
        let _this = this;
        $("#indexMainSliderSubmit").on("click", () => {
                _this.insertIndexMainSlider();
        });
    },
    insertIndexMainSlider: function () {
        let formData = new FormData();

        let imageFile = $("#images")[0].files[0];
        formData.append("images", imageFile);

        formData.append("category", $("#category").val());
        formData.append("mainTitle", $("#mainTitle").val());
        formData.append("subTitle", $("#subTitle").val());
        formData.append("schedule", $("#schedule").val());
        formData.append("address", $("#address").val());
        formData.append("summary", $("#summary").val());
        formData.append("href", $("#href").val());

        $.ajax({
            type : "POST",
            url : "/admin/home/registration/index",
            data : formData,
            processData: false,
            contentType: false
        }).done(function (response) {
                location.reload();
        }).fail(function (error) {
            alert("에러 발생 : " + error);
        });
    },
}
indexMainSliderObject.init();