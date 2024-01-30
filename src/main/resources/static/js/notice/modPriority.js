//화면상에서 위 아래로 움직일 시 화면상으로만 순서가 바뀌는 것 처럼 보이게 하는 자바스크립트

function moveUp(button, event) {

        event.preventDefault();


    let currentRow = button.parentNode.parentNode;
    console.log(currentRow);

    // 이전 행을 찾아서 현재 행을 위로 이동
    let previousRow = currentRow.previousElementSibling;
    console.log(previousRow);

    if (previousRow) {
        currentRow.parentNode.insertBefore(currentRow, previousRow);

    }
}


function moveDown(button, event){

        event.preventDefault();

    let currentRow = button.parentNode.parentNode;

    let nextRow = currentRow.nextElementSibling;


    if(nextRow){
        currentRow.parentNode.insertBefore(nextRow, currentRow);

    }


}






