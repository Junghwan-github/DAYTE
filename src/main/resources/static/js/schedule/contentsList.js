// // contentsList 문자열에서 공백과 줄바꿈 제거
// const cleanedString = contentsList.replace(/\s/g, '');
//
// // 'Contents('로 문자열을 나누고 첫 번째 항목은 무시
// const contentsListStrings = cleanedString.split('Contents(').slice(1);
//
// // contents 배열 생성
// const contents = $.map(contentsListStrings, function(contentString) {
//     const match = contentString.match(/id=([^,]+),businessName=([^,]+),category=([^,]+),gu=([^)]+),ro=([^)]+),positionX=([^)]+),positionY=([^)]+)/);
//
//     if (match) {
//         // match에서 추출한 정보를 사용하여 객체 생성
//         const [, id, businessName, category, gu, ro, positionX, positionY] = match;
//         return {id, businessName, category, gu, ro, positionX: parseFloat(positionX), positionY: parseFloat(positionY)};
//     } else {
//         return null;
//     }
// });
// console.log(contents);
const itemArr = [];

// contentBtn.on("click", function (e) {
//     let id;
//     let businessName;
//     let latitude;
//     let longitude;
//
//     contents.forEach(function (vo) {
//         if (vo.id === e.target.value) {
//             businessName = vo.businessName;
//             latitude = vo.latitude;
//             longitude = vo.longitude;
//             id = vo.id;
//         }
//     });
//
//     // 추가를 누르면 컨테이너에 담음
//     if (itemArr.length === 0 || itemArr.indexOf(id) === -1) {
//         let sliderItemImages = $(".contentListItemsImages > img").attr("src");
//         $(".contentModalSlider").append(`<li class='contentsListItemSelected'><div><img src='${sliderItemImages}'/></div><span>${businessName}</span><button type='button' class='contentsListItemDelete' value='${id}'><i class="xi-close-min"></i></button></li>`);
//         itemArr.push(id);
//     }
//
//     // ".contentsListItemDelete" 클래스를 가진 버튼에 대한 이벤트 핸들러 등록
//     $(".contentsListItemDelete").on("click", function (e) {
//         e.stopPropagation();
//         $(this).parent().remove();
//         itemArr.splice($(this).val(), 1);
//     })
// });

// 일정을 저장하는 함수
function scheduleTotalSaveBtn() {
    try {
        let saveSchedule = [];
        $(".contentsListItemSelected").each(function () {
            let button = $(this).find("button");
            saveSchedule.push(button.val());
        });

        const userSchedule = {
            nowDate     : $(".daysValue").text(),
            uuid        : $(".tableUuid").text(),
            contentsList: saveSchedule
        };

        $.ajax({
            url: "/schedule/saveSchedule",
            method : "POST",
            contentType: "application/json; charset=utf-8",
            data   : JSON.stringify(userSchedule),
            success: function(response) {
                console.log(response);
                location.reload();
            },
            error: function(error) {
                alert(`에러 발생: ${error.message}`);
            }
        });
    } catch (error) {
        alert(`에러 발생: ${error.message}`);
    }
}

// 콘텐츠 검색 함수
function searchContents(search) {
    $.ajax({
        url: '/search',
        method : 'POST',
        contentType: 'application/json',
        data   : JSON.stringify({search: search}),
        success: function(data) {
            // 검색 결과를 UI에 표시
            displaySearchResults(data);
            console.log(data);
        },
        error: function(error) {
            console.error('검색 실패:', error);
        }
    });
}

// 검색 결과를 UI에 표시하는 함수
function displaySearchResults(data) {
    const contentListViewer = $('.contentListViewer');
    contentListViewer.html('');

    if (data.length === 0) {
        const listItem = $('<li>').html('<p>검색 결과가 없습니다.</p>');
        contentListViewer.append(listItem);
    } else {
        $.each(data, function(index, content) {
            const listItem = $('<li>');

            listItem.html(`
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
            `);

            contentListViewer.append(listItem);
        });
    }
}

// "#guList" 요소 안의 <li> 클릭 이벤트 핸들러 등록
$("#guList").on("click", "li", function () {
    searchContents($(this).text());
});

// ".contentListItemAddBtn" 클래스를 가진 모든 버튼에 대한 이벤트 핸들러 등록
$('.contentListViewer').on('click', '.contentListItemAddBtn', function (e) {
    let id = $(e.target).val();
    let businessName = $(e.target).closest('.contentListItems').find('.contentListItemText h2:first').text();
    let latitude = $(e.target).closest('.contentListItems').find('.contentListItemPoint-x').text();
    let longitude = $(e.target).closest('.contentListItems').find('.contentListItemPoint-y').text();

    // 추가를 누르면 컨테이너에 담음
    if (itemArr.length === 0 || itemArr.indexOf(id) === -1) {
        let sliderItemImages = $(e.target).closest('.contentListItems').find('.contentListItemsImages > img').attr('src');
        $(".contentModalSlider").append(`<li class='contentsListItemSelected'><span class="contentListItemPoint-x">${latitude}</span><span class="contentListItemPoint-y">${longitude}</span><div><img src='${sliderItemImages}'/></div><span>${businessName}</span><button type='button' class='contentsListItemDelete' value='${id}'><i class="xi-close-min"></i></button></li>`);
        itemArr.push(id);
    }
    $(".contentsListItemDelete").on("click", function (e) {
        e.stopPropagation();
        $(this).parent().remove();
        itemArr.splice($(this).val(), 1);
    });
});

