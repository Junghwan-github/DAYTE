// 수정 버튼 눌렀을 때 체크박스 만들고 버튼은 "취소"+"삭제" 버튼으로 바꿈

const modMode = document.querySelector('#mod-notice');
function modToDel() {

    if (modMode.value === "수정") {

        modMode.value = "취소";

        let makeDeleteButton = document.getElementById("del-notice");
        makeDeleteButton.type = "button";

        var table = document.getElementById("defaultNoticeTable");

        // 테이블의 헤더에 th 엘리먼트 추가
        var headerRow = table.rows[0];
        var newHeaderCell = document.createElement("th");
        newHeaderCell.innerHTML = `선택`;
        headerRow.insertBefore(newHeaderCell, headerRow.firstElementChild);

        // 테이블의 각 행에 td 엘리먼트 추가
        var rows = table.getElementsByTagName("tr");
        var checkboxes = []; // 체크박스 배열 추가

        for (var i = 1; i < rows.length; i++) {
            var newCell = rows[i].insertCell(0);

            var checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.name = "delete-checkbox";

            newCell.appendChild(checkbox);

            checkboxes.push(checkbox); // 배열에 체크박스 추가
        }

        for (var i = 1; i < rows.length; i++) {
            var noticeId = table.rows[i].cells[1].innerText;

            checkboxes[i - 1].value = noticeId; // 각 체크박스의 값 설정
            console.log(checkboxes[i - 1].value);
        }

    } else if (modMode.value === "취소") {
        modMode.value = "수정";

        let makeDeleteButton = document.getElementById("del-notice");
        makeDeleteButton.type = "hidden";

        var table = document.getElementById("defaultNoticeTable");

        // 모든 행에 대해서 첫 번째 열 제거
        var rows = table.getElementsByTagName("tr");
        for (var i = 0; i < rows.length; i++) {
            var firstCell = rows[i].getElementsByTagName("td")[0];
            if (firstCell) {
                rows[i].deleteCell(0);
            }
        }

        // 테이블 헤더의 첫 번째 열 제거
        var headerRow = table.rows[0];
        headerRow.deleteCell(0);

    }

}

modMode.addEventListener("click", modToDel);