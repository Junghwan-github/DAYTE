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

        const selectContents = document.querySelector(".contentModalSlider");
        const uSelectButton = document.createElement("button");
        const uSelectImg = document.createElement("img");
        const uSelectImgDiv = document.createElement("div");
        const uSelectSpan = document.createElement("span");
        const uSelect = document.createElement("li");

        uSelect.className = "uSelect";

        uSelectImg.setAttribute('src', '');
        uSelectImg.setAttribute('alt', '이미지');
        uSelectButton.setAttribute('type', 'button');
        uSelectButton.setAttribute('value', id);

        uSelectSpan.textContent = businessName;
        uSelectImgDiv.append(uSelectImg);
        uSelect.append(uSelectImgDiv);
        uSelect.append(uSelectSpan);
        uSelect.append(uSelectButton);
        selectContents.append(uSelect);
    });
});

async function asd() {
    try {
        let saveSchedule = [];
        $(".uSelect").each(function () {
            let button = $(this).find("button");
            saveSchedule.push(button.val());
        });

        const userSchedule = {
            nextDays: $(".daysValue").text(),
            uuid: $(".tableUuid").text(),
            contents: saveSchedule
        };

        console.log(userSchedule);

        const response = await fetch("/schedule/saveSchedule", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(userSchedule),
        });

        if (!response.ok) {
            throw new Error(`에러 발생: ${response.status}`);
        }

        console.log(response);
    } catch (error) {
        alert(`에러 발생: ${error.message}`);
    }
}