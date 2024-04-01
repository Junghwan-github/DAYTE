function updateReply() {

    let uuid = document.getElementById("contentUuid").value;

    let newReply = document.getElementById('reply-content').value;

    let newRating = document.querySelector('input[type="radio"]:checked');

    if(newRating == null){
        alert("별점을 입력해주세요.");
        return;
    } else if(!!!newReply){
        alert("리뷰를 입력해주세요.")
        return;
    } else{
        let data = {
            uuid : uuid,
            newReply : newReply,
            newRating : newRating.value
        }

       fetch("/modReview", {
           method: "PUT",
           headers: {
               "Content-Type": "application/json; charset=utf-8",
           },
           body: JSON.stringify(data),
       }).then(response => {
           return response.json();
       }).then(data => {
           alert("리뷰 수정이 완료되었습니다.");
           location = "/schedule/pastSchedule";
       }).catch(error => {
           console.error(error);
       });
    }
}