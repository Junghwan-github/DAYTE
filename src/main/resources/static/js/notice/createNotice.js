// 새 공지사항 등록

let noticeObject = {
    init: function () {
        $("#btn-create").on("click", () => {
            this.createNotice();
        });
    },

    createNotice: function () {

        let fileInput = document.getElementById("file-input");
        let checkFileSize = false;
        let newFiles = [];

        for (let i = 0; i < fileInput.files.length; i++) {
            if (fileInput.files[i].size > 1 * 1024 * 1024) {
                alert(fileInput.files[i].name + "의 파일 용량이 기준 용량(1MB)를 초과하였습니다.");
                checkFileSize = true;
            } else {
                // 용량이 초과하지 않는 파일만 새로운 배열에 담음
                newFiles.push(fileInput.files[i]);
            }
        }

        if(checkFileSize){
            checkFileSize = false;

            let dataTransfer = new DataTransfer();
            newFiles.forEach(file => {
                dataTransfer.items.add(file);
            });

            fileInput.files = dataTransfer.files;

            //기존의 preview 초기화
            const preview = document.getElementById("preview");
            preview.innerHTML = "";

            let filePreview = Array.from(fileInput.files)
            filePreview.forEach(file => {
                preview.innerHTML += `
                 <p id="${file.lastModified}">
                  ${file.name}
                 <button data-index='${file.lastModified}' class='file-remove' onclick="removeFile(this)">X</button>
                  <span>${(file.size / (1024)).toFixed(2)}KB</span>
   
                 </p>
                `
            });
            return;
        }

        const form = document.getElementById("saveForm");
        const formData = new FormData(form);
        let content = $(".summernote").summernote('code');
        formData.append("content", content);

        let confirmed = confirm("새 공지사항을 등록 하시겠습니까?")

        if (confirmed) {
            fetch("/notice/createNotice", {
                method: "POST",
                body: formData,
            })
                .then(response => {
                        return response.json();
            }).then(data => {
            location = "/notice/modAll";
            }).catch(error => {
                alert(`에러발생 : ${error.message}`);
            })
        }
    }
}

noticeObject.init();