/*
let noticeObject = {
    init: function (){

        $("#searchFunction").on("click", () => {
            this.searchNotices();
        });

    },


    searchNotices: function() {

            let searchWord =
                 $("#searchText").val()


            console.log(searchWord);
            fetch("/notice/searchNotices"+searchWord, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                }
            })
               .then(response => {

                    return response.json();


                }).then(data =>{
                console.log(data);
              //location = "/notice/searchedNotices";
            })
                .catch(error => {
                    alert(`에러발생 : ${error.message}`);
                })

    },

    /!* cancelCreateNotice: function (){
         window.history.back();
     }*!/




}

noticeObject.init();*/
