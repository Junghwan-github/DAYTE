// 체크박스 체크 상태로 뷰단에서 보일건지 말건지 체크
window.addEventListener("beforeunload", function (e){
//e.preventDefault()
    saveCheckboxStates();
});

function saveCheckboxStates(){
    let checkboxId;
    let isChecked;
    let checkboxIdstate = {};

    document.querySelectorAll(".viewCheckbox")
        .forEach(function (t){
            checkboxId = t.id
            isChecked = t.checked;

            checkboxIdstate[checkboxId] = isChecked;
    })
   // console.log(checkboxIdstate);

    fetch("/notice/ViewCheck", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify(checkboxIdstate),

    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            console.log('All checkbox states saved successfully.');
        })
        .catch(error => {
            console.error('Error saving all checkbox states:', error);
        });

}