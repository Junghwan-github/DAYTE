const firstCtg = {
    '계정': ['이메일 본인 인증', '비밀번호 찾기', '휴면계정'],
    '이용문의': ['숙소', '맛집', '카페', '행사'],
    '일정': ['일정관리', '일정공유', '일정삭제'],
    '뭐있지': ['이거', '나오면', '되는데', '드롭박스', '메뉴']
}

let firstCategory = document.querySelector("#firstCategory");
let secondCategory = document.querySelector('#secondCategory');

init();

function init() {

    let firstHtml = `<option value="" selected disabled hidden>카테고리 선택</option>`;
    let secondHtml = `<option value="" selected disabled hidden>카테고리 선택</option>`;


    for (const key in firstCtg) {
        firstHtml += `<option value="${key}">${key}</option>`;


        firstCtg[key].forEach(second => {
            secondHtml += `<option value="${second}" class="${key}">${second}</option>`

        });
    }

    firstCategory.innerHTML = firstHtml;
    secondCategory.innerHTML = secondHtml;

    let secondOption = secondCategory.querySelectorAll('option');

    secondOption.forEach(e => {
        e.style.display = 'none';
    })

}

firstCategory.addEventListener("change", function (element){



    secondCategory.querySelectorAll('option').forEach(e => {

        if(element.target.value == e.className){
            e.style.display = 'block';
        } else{
            e.style.display = 'none';
        }
    })


})


