//체크박스에 선택된 공지사항 삭제

document.getElementById('del-notice').addEventListener('click', logSelectedCheckboxValues);

function logSelectedCheckboxValues() {
    // 체크박스들 중에서 체크된 것들을 가져오기
    var selectedCheckboxes = document.querySelectorAll('input[name="delete-checkbox"]:checked');

    if(selectedCheckboxes.length === 0){
        alert("선택된 항목이 없습니다")
        return;
    }
    var confirmed = confirm(selectedCheckboxes.length + "개의 게시글을 삭제하시겠습니까?");

    if(confirmed){
        var selectedValues = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);

        fetch("/notice/delete", {
            method : "DELETE",
            headers:{
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(selectedValues)
        })
            .then(res => {
                return res.json();
            })
            .then(data => {
                location = "/notice/modAll";
            })

    }
}
