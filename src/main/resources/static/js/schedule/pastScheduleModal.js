$(document).ready(function () {

    //     모달오픈
    $(".btn-open-modal").on("click", function () {
        let _thisClass = $(this).attr("class").split(" ");
        console.log(_thisClass[1]);
        $(".modal_body").removeClass("active");
        $(".modal_body."+_thisClass[1]).addClass("active");
        $(".daysPrint").show();
        $(".modal_body."+_thisClass[1]+" #tabPage .tab1").addClass("active");
    })

    // 모달 닫기
    $(".material-symbols-outlined").on("click", function () {
        $(".modal_body").removeClass("active");
        $("#tabPage > .display-hide").removeClass("active");
        $(".daysPrint").hide();

    })

    // 상호명 클릭시 새창 컨텐츠 링크
    $(".businessName").on("click", function () {
        let uuidUrl = $(this).attr("data-uuid");
        window.open("/contents/detail/" + uuidUrl, "blank");
    })

    //탭메뉴 영역

    $("#tabMenu li").on("click", function () {
        let _thisClass = $(this).attr("class");
        $("#tabPage > .display-hide").removeClass("active");
        $("#tabPage > ."+_thisClass).addClass("active");
    })

})

//
// let tabMenuUl = document.getElementById('tabMenu');
// let tabPageUl = document.getElementById('tabPage');
//
// let tabMenuList = tabMenuUl.querySelectorAll('[class*="tab"]');
// let tabPageList = tabPageUl.querySelectorAll('[class*="tab"]');
//
//
// let thisTabMenuList;
// let thisTabPageList;
// let activeModal;
// let openModalBtn = document.querySelectorAll(".btn-open-modal");
//
// openModalBtn.forEach(function (e){
//     e.addEventListener("click", function (){
//
//         console.log(e);
//
//
//
//         let findCard = e.closest('[class*=scheduleCard]');
//         let findClassName = findCard.className;
//         console.log(findClassName);
//
//         // $(".daysPrint").show();
//
//
//         let thisModal = document.querySelector('.modal');
//         console.log(thisModal);
//         thisModal.style.display = "flex";
//
//
//         let modalBodys = document.querySelectorAll('.modal_body');
//         for (let modalBody of modalBodys) {
//             if (modalBody.classList.contains(findClassName)) {
//                 modalBody.style.display = "block";
//                 console.log(modalBody);
//
//                 activeModal = modalBody;
//
//                 let tabMenuUl = modalBody.querySelector('#tabMenu');
//                 let tabPageUl = modalBody.querySelector('#tabPage');
//
//                 console.log(tabMenuUl);
//                 console.log(tabPageUl);
//
//                 let tabMenuList = tabMenuUl.querySelectorAll('[class*="tab"]');
//                 let tabPageList = tabPageUl.querySelectorAll('[class*="tab"]');
//
//                 thisTabMenuList = tabMenuList;
//                 thisTabPageList = tabPageList;
//
//                 for (let tabMenuLi of tabMenuList) {
//                     if (tabMenuLi.classList.contains('tab1')) {
//                         tabMenuLi.classList.remove("closeTab");
//                         tabMenuLi.classList.add("nowTab");
//                         continue;
//                     } else if (tabMenuLi.classList.contains('nowTab')) {
//                         tabMenuLi.classList.remove('nowTab');
//                         tabMenuLi.classList.add('closeTab');
//                     }
//                 }
//
//                 for (let tabPageLi of tabPageList) {
//                     if (tabPageLi.classList.contains('tab1')) {
//                         tabPageLi.classList.remove('closeTabPage');
//                         tabPageLi.classList.add('nowTabPage');
//                     } else if (tabPageLi.classList.contains('nowTabPage')) {
//                         tabPageLi.classList.remove('nowTabPage');
//                         tabPageLi.classList.add('closeTabPage');
//                     }
//
//                 }
//
//
//             }
//         }
//
// //탭 선택
//
//         for (let i = 0; i < thisTabMenuList.length; i++) {
//             thisTabMenuList[i].addEventListener('click', function (e) {
//                 for (let j = 0; j < thisTabMenuList.length; j++) {
//                     thisTabMenuList[j].classList.remove('nowTab');
//                     thisTabMenuList[j].classList.add('closeTab');
//                     thisTabPageList[j].classList.remove('nowTabPage');
//                     thisTabPageList[j].classList.add('closeTabPage');
//
//                 }
//                 thisTabMenuList[i].classList.remove('closeTab');
//                 thisTabMenuList[i].classList.add('nowTab');
//                 thisTabPageList[i].classList.remove('closeTabPage');
//                 thisTabPageList[i].classList.add('nowTabPage');
//             })
//         }
//
//
//     })
// })
















