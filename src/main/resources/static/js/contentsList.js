

const cleanedString = contentsList.replace(/\s/g, ''); // 공백과 줄바꿈 제거

const contentsListStrings = cleanedString.split('Contents(').slice(1); // 'Coordinate('로 문자열을 나누고 첫 번째 항목은 무시

const contents = contentsListStrings.map(contentString => {
    const match = contentString.match(/id=([^,]+),businessName=([^,]+),category=([^,]+),gu=([^)]+),ro=([^)]+),positionX=([^)]+),positionY=([^)]+)/);

    if (match) {
        const [, id, businessName, category, gu, ro, positionX, positionY] = match;
        return {id, businessName, category, gu, ro, positionX: parseFloat(positionX), positionY: parseFloat(positionY)}
    } else {
        return null;
    }
});

const contentBtn = document.querySelectorAll(".contentListItemAddBtn");
const itemArr = [];
contentBtn.forEach(function (content) {
    content.addEventListener("click", function (e) {

        let id;
        let businessName;
        let latitude;
        let longitude;



        contents.forEach(function (vo) {
            if (vo.id === e.target.value) {
                businessName = vo.businessName;
                latitude = vo.latitude;
                longitude = vo.longitude;
                id = vo.id
            }
        });

        if (itemArr.length === 0 || itemArr.indexOf(id) === -1) {
            let sliderItemImages = $(".contentListItemsImages > img").attr("src");
            $(".contentModalSlider").append(`<li class='contentsListItemSelected'><div><img src='${sliderItemImages}'/></div><span>${businessName}</span><button type='button' class='contentsListItemDelete' value='${id}'><i class="xi-close-min"></i></button></li>`);
        }
        $(".contentsListItemDelete").on("click", function (e) {
            e.stopPropagation();
            $(this).parent().remove();
        })
    });
});
function scheduleTotalSaveBtn() {
    try {
        let saveSchedule = [];
        $(".contentsListItemSelected").each(function () {
            let button = $(this).find("button");
            saveSchedule.push(button.val());
        });

        const userSchedule = {
            nowDate    : $(".daysValue").text(),
            uuid        : $(".tableUuid").text(),
            contentsList: saveSchedule
        };

        fetch("/schedule/saveSchedule", {
            method : "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body   : JSON.stringify(userSchedule),
        }).then(response => {
            console.log(response);
        }).catch(error => {
            alert(`에러 발생: ${error.message}`);
        });
    } catch (error) {
        alert(`에러 발생: ${error.message}`);
    }
}