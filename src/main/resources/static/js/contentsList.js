

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
            itemArr.push(id);
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
            nowDate: $(".daysValue").text(),
            uuid: $(".tableUuid").text(),
            contentsList: saveSchedule
        };

        fetch("/schedule/saveSchedule", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(userSchedule),
        }).then(response => {
            console.log(response);
            location.reload();
        }).catch(error => {
            alert(`에러 발생: ${error.message}`);
        });
    } catch (error) {
        alert(`에러 발생: ${error.message}`);
    }
}

function searchContents() {
    const searchInput = document.getElementById('leftModalSearchBar').value;
    console.log(searchInput);
    fetch('/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({searchInput: searchInput}),
    })
        .then(response => response.json())
        .then(data => {
            displaySearchResults(data);
            console.log(data);
        })
        .catch(error => {
            console.error('검색 실패:', error);
        });
}

function displaySearchResults(data) {
    const contentListViewer = document.querySelector('.contentListViewer');
    contentListViewer.innerHTML = '';

    data.forEach(content => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
                    <span class="contentListItemPoint-x">${content.positionX}</span>
                    <span class="contentListItemPoint-y">${content.positionY}</span>
                    <div class="contentListItems">
                        <div class="contentListItemsImages">
                        <img src="../images/testimages1.jpg">
                    </div>
                     <ul class="contentListItemText">
                                        <li>
                                            <h2>${content.businessName}</h2>
                                            <h2>${content.category}</h2>
                                        </li>
                                        <li>
                                            <span>대구 ${content.gu} ${content.ro}</span>
                                        </li>
                                        <li>
                                            <p>영업시간 : 09:00</p>
                                            <p>기간 : 없음</p>
                                            <p>문의 : 0507-2221-1321</p>
                                        </li>
                                        <li>
                                            <span>★ 4.5</span>
                                        </li>
                                    </ul>
                                    <div class="contentListItemButton">
                                        <ul>
                                            <li>
                                                <button class="contentListItemdetailViewBtn">상세보기</button>
                                            </li>
                                            <li>
                                                <button class="contentListItemAddBtn" value="${content.id}">추가하기
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                `;
        contentListViewer.appendChild(listItem);
    })
}
