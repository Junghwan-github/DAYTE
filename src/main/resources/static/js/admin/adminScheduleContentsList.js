let scheduleContentsListObject = {
    init: function () {
        let _this = this;
        $("#scheduleContentsListSubmit").on("click", () => {
            const regNumber =  /^[0.-9.]*$/;
            let latitude = $("#positionX").val();
            let longitude = $("#positionY").val();

            $('.latitudeText').text("");
            $('.longitudeText').text("");
            if (!regNumber.test(latitude) || latitude === "") {
                $('.latitudeText').text("숫자만 입력 가능합니다.");
                return;
            } else if (!regNumber.test(longitude) || longitude === "") {
                $('.longitudeText').text("숫자만 입력 가능합니다.");
                return;
            } else {
                let latitude_frontLength = latitude.substring(0, latitude.indexOf('.')).length;
                let longitude_frontLength = longitude.substring(0, longitude.indexOf('.')).length;
                let latitude_backLength = latitude.substring(latitude.indexOf('.') + 1).length;
                let longitude_backLength = longitude.substring(longitude.indexOf('.') + 1).length;
                if (latitude_frontLength != 2) {
                    $('.latitudeText').text("소수점 앞자리를 2자리로 입력해주세요.");
                    return;
                } else if (longitude_frontLength != 3) {
                    $('.longitudeText').text("소수점 앞자리를 3자리로 입력해주세요.");
                    return;
                }
                else if (latitude_backLength != 7) {
                    $('.latitudeText').text("위도의 값은 소수점 앞 2자리와 소수점 뒤 7자리로 입력해주세요.");
                    return;
                } else if (longitude_backLength != 7) {
                    $('.longitudeText').text("경도의 값은 소수점 앞 3자리와 소수점 뒤 7자리로 입력해주세요.");
                    return;
                }
            }
            _this.insertContentsList();
        });
    },
    insertContentsList: function () {

        let formData = new FormData();

        // const imgArr = [];

        for (let i = 0; i < $("#image")[0].files.length; i++) {
            // imgArr.push($("#image")[0].files[i]);
            formData.append("imageFiles", $("#image")[0].files[i]);
        }
        // let imageFile = $("#images")[0].files[0];
        // console.log("Image File:", imageFile);
        // formData.append("images", imageFile);
        // formData.append("ImageFiles", imgArr);

        formData.append("businessName", $("#businessName").val());
        formData.append("category", $("#category").val());
        formData.append("gu", $("#gu").val());
        formData.append("positionX", $("#positionX").val());
        formData.append("positionY", $("#positionY").val());
        formData.append("detailedAddress", $("#address").val());
        formData.append("keyword", $("#keyword").val());
        formData.append("detailedDescription", $("#detailedDescription").val());

        console.log(formData);


        $.ajax({
            type : "POST",
            url : "/admin/home/registration/contents",
            data : formData,
            processData: false,
            contentType: false
        }).done(function (response) {
            alert(response.data);
            location.reload();
        }).fail(function (error) {
            alert("에러 발생 : " + error);
        })
    },
}

scheduleContentsListObject.init();