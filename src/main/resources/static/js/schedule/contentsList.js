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
const itemArr = [];
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
