//필독 공지사항 우선순위 저장

let fixPriorityObject = {
    init: function () {

        $("#fixPriority").on("click", () => {
            this.fixPriority();
        });

    },


    fixPriority: function () {

        var confirmed = confirm("필독 공지사항 우선순위를 저장하시겠습니까?")

        if(confirmed){



        let trueIdPriEle = document.querySelectorAll(".selected");
        let trueNoList = [];
        let trueNo;

        trueIdPriEle.forEach(function (t) {
            trueNo = t.querySelector("td > input[name='truetdNo']").value;
            trueNoList.push(trueNo);
        });

        fetch("/notice/fixPriority", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(trueNoList),
        })
            .then(response => {

                return response.json();

            }).then(data => {
            console.log(data);
          // location = "/notice/modAll";
        })
            .catch(error => {
                alert(`에러발생 : ${error.message}`);
            })
        }
    }
}


fixPriorityObject.init();





