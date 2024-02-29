const firstCtg = {
    '계정': ['이메일 본인 인증', '비밀번호 찾기', '휴면계정'],
    '이용문의': ['숙소', '맛집', '카페', '행사'],
    '일정': ['일정관리', '일정공유', '일정삭제'],
    '뭐있지': ['이거', '나오면', '되는데', '드롭박스', '메뉴']
}

let mainCategory = document.querySelector("#mainCategory");
let subCategory = document.querySelector('#subCategory');

init();

function init() {

    let firstHtml = `<option value="" selected disabled hidden><span class="selectCategory" style="font-size: 14px">카테고리 선택</span></option>`;
    let secondHtml = `<option value="" selected disabled hidden><span class="selectCategory" style="font-size: 14px">카테고리 선택</span></option>`;

    for (const key in firstCtg) {
        firstHtml += `<option value="${key}"><span>${key}</span></option>`;

        firstCtg[key].forEach(second => {
            secondHtml += `<option value="${second}" class="${key}"><span>${second}</span></option>`
        });
    }

    mainCategory.innerHTML = firstHtml;
    subCategory.innerHTML = secondHtml;

    let subOption = subCategory.querySelectorAll('option');

    subOption.forEach(e => {
        e.style.display = 'none';
    })
}

mainCategory.addEventListener("change", function (element){

    subCategory.querySelectorAll('option').forEach(e => {

        if(element.target.value == e.className){
            e.style.display = 'block';
        } else{
            e.style.display = 'none';
        }
    })
})

$("#inquiry-email").on('change', function () {

    const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+$/;
    let email = $("#inquiry-email");

    if(!pattern.test(email.val())) {
        if(!$("#invalidEmail").length){
            email.after(`<span class="invalid" id="invalidEmail" style="color: red">유효하지 않은 이메일 형식입니다</span>`);
            email.focus();
        }
    } else{
        $("#invalidEmail").remove();
    }
})

$("#inquiry-title").on('input', function () {
    if($("#inquiry-title").val() !== ""){
        $("#invalidTitle").remove();
    }
})

$("#inquiry-content").on('input', function () {
    if($("#inquiry-content").val() !== ""){
        $("#invalidContent").remove();
    }
})

let mainSelectElement = document.getElementById('mainCategory');
let mainIcoArrow = document.getElementById('mainIcoArrow');

mainSelectElement.addEventListener('click', function (){
    mainIcoArrow.classList.toggle('openState');
})

mainSelectElement.addEventListener('blur', function () {
    mainIcoArrow.classList.remove('openState');
});

let subSelectElement = document.getElementById('subCategory');
let subIcoArrow = document.getElementById('subIcoArrow');

subSelectElement.addEventListener('click', function (){
    subIcoArrow.classList.toggle('openState');
})

subSelectElement.addEventListener('blur', function () {
    subIcoArrow.classList.remove('openState');
});
