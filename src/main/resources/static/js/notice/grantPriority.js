//일반 공지사항에 priority 값을 부여해서 필수 공지로 변경
function upToSelected(no, event)  {
    event.preventDefault();

    var confirmed = confirm(no + "번 공지를 필수 공지로 변경하시겠습니까?");

    if(confirmed) {

        let data = {
            no : no
        }

        fetch("/notice/grantPriority"+no, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            }
        }).then(response => {
            return response.json();
        }).then(data =>{
            location = "/notice/modAll";
        }).catch(error => {
            alert(`에러발생 : ${error.message}`);
        })
    }
}