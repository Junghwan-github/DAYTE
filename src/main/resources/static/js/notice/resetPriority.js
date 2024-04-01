function resetPriority(no, event) {
    event.preventDefault();

    let confirmed = confirm(no + "번 공지를 일반 공지로 변경하시겠습니까?");

    if (confirmed) {
        let data = {
            no: no
        }

        fetch("/notice/resetPriority" + no, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            }
        })
            .then(response => {
                return response.json();
            }).then(data => {
            location = "/notice/modAll";
        })
            .catch(error => {
                alert(`에러발생 : ${error.message}`);
            })
    }
}
