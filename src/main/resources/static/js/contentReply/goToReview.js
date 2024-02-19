function goToReview(value){
    let uuid = value;

    fetch("/checkcontentsReview/"+uuid, {
        method: "GET",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
    })
        .then(data => {
            if (data.status == 200) {
               window.open("/contentsReview/"+uuid , "black");
            } else if (data.status == 400) {
                alert("이미 해당 컨텐츠에 댓글을 등록하셨습니다.");
            }
        })
        .catch(error => {
            console.error(error);
        });
}

/*function modReview(value){
    let uuid = value;
    console.log(uuid);

    fetch("/modReview/"+uuid, {
        method: "GET",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
    })
        .then(data => {
            if (data.status == 200) {
                location = "/contentsReview/"+uuid;
            } else if (data.status == 400) {
                alert("이미 해당 컨텐츠에 댓글을 등록하셨습니다.");
            }
        })
        .catch(error => {
            console.error(error);
        });

}*/


