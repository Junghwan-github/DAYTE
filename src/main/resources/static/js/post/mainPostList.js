function getPostDetail(postId) {
    location.href = `/post/${postId}`;
}

function writingPost() {
    location.href = "/mainPostList/in"
}

function postPostList() {
    location.href = "/mainPostList"
}

// 현재 시간을 가져오는 함수
function getCurrentDateTime() {
    return new Date();
}

// 게시물의 작성 날짜를 가져오는 함수
let postCreateDates = document.querySelectorAll('.user-part > ul > li:nth-child(3) > span');
postCreateDates.forEach(function(dateElement) {
    let postDateTime = new Date(dateElement.textContent); // 게시물의 작성 날짜와 시간
    let twentyFourHoursLaterDateTime = new Date(postDateTime.getTime() + 24 * 60 * 60 * 1000); // 게시글의 작성 날짜 + 24시간
    if (new Date() < twentyFourHoursLaterDateTime) { // 24시간 이내인 경우
        dateElement.parentElement.parentElement.parentElement.parentElement.querySelector('.content-part > .post-items-title > h2 > .xi-new').style.display = 'inline-block'; // "new-icon"을 보이게 함
    }
});