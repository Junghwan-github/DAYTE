$(document).ready(function () {
    $(".btn-open-modal").on("click", function () {
        $(".daysPrint").show();

    })

    $(".material-symbols-outlined").on("click", function () {
        $(".daysPrint").hide();
    })
})



/*let tabMenuUl = document.getElementById('tabMenu');
let tabPageUl = document.getElementById('tabPage');

let tabMenuList = tabMenuUl.querySelectorAll('[class*="tab"]');
let tabPageList = tabPageUl.querySelectorAll('[class*="tab"]');*/


// function openModal(element) {
//
//     let thisTabMenuList;
//     let thisTabPageList;
//     let activeModal;
//
//     let findCard = element.closest('[class*=scheduleCard]');
//     let findClassName = findCard.className;
//     console.log(findClassName);
//
//     $(".daysPrint").show();


    // let thisModal = document.querySelector('.modal');
    // console.log(thisModal);
    // thisModal.style.display="flex";

//
//     let modalBodys = thisModal.querySelectorAll('.modal_body');
//     for (let modalBody of modalBodys) {
//         if(modalBody.classList.contains(findClassName)){
//             modalBody.style.display = "block";
//
//             activeModal = modalBody;
//
//             let tabMenuUl = modalBody.querySelector('#tabMenu');
//             let tabPageUl = modalBody.querySelector('#tabPage');
//
//             console.log(tabMenuUl);
//             console.log(tabPageUl);
//
//             let tabMenuList = tabMenuUl.querySelectorAll('[class*="tab"]');
//             let tabPageList = tabPageUl.querySelectorAll('[class*="tab"]');
//
//             thisTabMenuList = tabMenuList;
//             thisTabPageList = tabPageList;
//
//             for(let tabMenuLi of tabMenuList){
//                 if(tabMenuLi.classList.contains('tab1')){
//                     tabMenuLi.classList.remove("closeTab");
//                     tabMenuLi.classList.add("nowTab");
//                     continue;
//                 } else if(tabMenuLi.classList.contains('nowTab')){
//                     tabMenuLi.classList.remove('nowTab');
//                     tabMenuLi.classList.add('closeTab');
//                 }
//             }
//
//             for(let tabPageLi of tabPageList){
//                 if(tabPageLi.classList.contains('tab1')){
//                     tabPageLi.classList.remove('closeTabPage');
//                     tabPageLi.classList.add('nowTabPage');
//                 }else if(tabPageLi.classList.contains('nowTabPage')){
//                     tabPageLi.classList.remove('nowTabPage');
//                     tabPageLi.classList.add('closeTabPage');
//                 }
//
//             }
//
//
//         }
//     }
//
// //탭 선택
//
//     for (let i=0; i<thisTabMenuList.length; i++){
//         thisTabMenuList[i].addEventListener('click', function (e){
//             for(let j=0; j<thisTabMenuList.length; j++){
//                 thisTabMenuList[j].classList.remove('nowTab');
//                 thisTabMenuList[j].classList.add('closeTab');
//                 thisTabPageList[j].classList.remove('nowTabPage');
//                 thisTabPageList[j].classList.add('closeTabPage');
//
//             }
//             thisTabMenuList[i].classList.remove('closeTab');
//             thisTabMenuList[i].classList.add('nowTab');
//             thisTabPageList[i].classList.remove('closeTabPage');
//             thisTabPageList[i].classList.add('nowTabPage');
//         })
//     }
//
//

// }



// 모달 창 닫기
// function closeModal(element){
//
//     $(".daysPrint").hide();
    // let thisModal = element.closest(".modal");
    // thisModal.style.display = "none";
    //
    // let thisModalChildren = thisModal.children;
    // for (let thisModalChild of thisModalChildren) {
    //     if(thisModalChild.style.display == "block"){
    //         thisModalChild.style.display = "none";
    //     }
    //
    // }


// }








