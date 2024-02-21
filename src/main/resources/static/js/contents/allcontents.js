    $(".contentListViewer").on("click",".contentListItemdetailViewBtn", function () {
        let _this = $(this).val();
        window.open("/contents/detail/" + _this, "_blank");
    })

    $("#leftModalSearchBar").keyup(function(event) {
        if (event.which === 13) {
            $("#leftModalSearchBarBtn").click();
        }
    });

function searchContentsCategory(search) {
    // 현재 URL 가져오기
    let currentUrl = window.location.href;
    // URL 객체 생성
    let urlObject = new URL(currentUrl);
    // 경로 가져오기
    let path = urlObject.pathname;
    // 경로를 '/'로 분할하여 배열로 저장
    let pathSegments = path.split('/');
    // hotels 값을 가져오기 (경로에서 두 번째 요소)
    let value = pathSegments[3];

    let categoryAndSearch = {
        search : search,
        category: value
    }
    $.ajax({
        url        : "/contents/category/searchContentsCategory",
        method     : "POST",
        contentType: "application/json; charset=utf-8",
        data       : JSON.stringify(categoryAndSearch),
        success    : function (data) {
            // 검색 결과를 UI에 표시
            if (!!!data) {
                alert("값 없음");
            }
            console.log(data);
            displaySearchResults1(data);
        },
        error      : function (error) {
            console.error('검색 실패:', error);
        }
    });
}

$("#guList").on("click", "li", function () {
    searchContentsCategory($(this).text());
});

$("#keywordList").on("click", "li", function () {
    searchContentsCategory($(this).text());
});

// 검색 결과를 UI에 표시하는 함수
function displaySearchResults1(data) {
    const contentListViewer = $('.contentListViewer');
    contentListViewer.html('');
    if (data.searchByContents.length === 0) {
        const listItem = $('<li>').html('<p>검색 결과가 없습니다.</p>');
        contentListViewer.append(listItem);
    } else {
        $.each(data.searchByContents, function(index, content) {
            const listItem = $('<li>');

            listItem.html(`
                <div class="contentListItems">
                <span class="contentListItemPoint-x">${content.positionX}</span>
                <span class="contentListItemPoint-y">${content.positionY}</span>
                    <div class="contentListItemsImages">
                        <img src="${content.adminContentsImageList[0].imageURL}">
                    </div>
                    <ul class="contentListItemText">
                        <li>
                        <div class="contents-title-wrapper">
                            <h2>${content.businessName}</h2>
                            <div class="contents-category-keyword">
                                                    <span>${content.category}</span>
                                                    <span>${content.keyword}</span>
                                                </div>
                        </li>
                        <li>
                            <span>${content.detailedAddress}</span>
                        </li>
                        <li>
                            <p>영업시간 : ${content.opening} ~ ${content.closed}</p>
                            <p>기간 : 없음</p>
                            <p>문의 : ${content.contactInfo}</p>
                        </li>
                         <li>  
                         <span class="rating"></span>   
                         </li>
                        </ul>
                    <div class="contentListItemButton">
                        <ul>
                            <li>
                                <button class="contentListItemdetailViewBtn" value="${content.uuid}">상세보기</button>
                            </li>
                        </ul>
                    </div>
                </div>`);
            const matchingStar = data.avgStarViewDTOList.find(star => star.uuid === content.uuid);
            if (matchingStar) {
                listItem.find('.rating').text('★' + matchingStar.starAVG.toFixed(1));
            } else {
                listItem.find('.rating').text('★0.0');
            }
            contentListViewer.append(listItem);
        });
    }
}

    $(document).ready(function() {
        let sidebar = $(".leftModalLayout");
        let contentBox = $(".centerModalLayout");

        $(window).scroll(function() {
            if ($(window).scrollTop() >= sidebar.height()) {
                sidebar.addClass("fixed");
                contentBox.addClass("relative");
            } else {
                sidebar.removeClass("fixed");
                contentBox.removeClass("relative");
            }
        });
    });