function upToSelected(no, event)  {
    event.preventDefault();

    console.log(no);
    // var confirmed = confirm("일반 공지를 필수 공지로 변경하시겠습니까?");

    let data = {
        no : no
    }
    console.log(no);

    fetch("/notice/grantPriority"+no, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        }

    })
        .then(response => {

            return response.json();

        }).then(data =>{
        console.log(data);
        location = "/notice/modAll";
    })
        .catch(error => {
            alert(`에러발생 : ${error.message}`);
        })

}