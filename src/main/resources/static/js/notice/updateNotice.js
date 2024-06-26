//공지사항 수정

let updateNoticeObject = {
    init: function () {
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    update: function () {
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

        if (checkFileSize) {
            checkFileSize = false;

            let dataTransfer = new DataTransfer();
            newFiles.forEach(file => {
                dataTransfer.items.add(file);
            });

            fileInput.files = dataTransfer.files;

            //기존의 preview는 놔두고 newPreview만 초기화
            const newPreview = document.getElementById("newPreview");
            newPreview.innerHTML = "";

            let filePreview = Array.from(fileInput.files)
            filePreview.forEach(file => {
                newPreview.innerHTML += `
                 <p id="${file.lastModified}">
                  ${file.name}
                 <button data-index='${file.lastModified}' class='file-remove' onclick="removeFile(this)">X</button>
                 <span>${(file.size / (1024)).toFixed(2)}KB</span>
                 </p>
                `
            });
            return;
        }

        let confirmed = confirm("수정 하시겠습니까?")

        if (confirmed) {

            const form = document.getElementById("saveForm");
            const formData = new FormData(form);

            const no = document.getElementById("id").value;
            const title = document.getElementById("title").value;
            let content = $(".summernote").summernote('code');

            const savedFileHTMLCollection = document.getElementsByName("savedFile");
            const savedFile = Array.from(savedFileHTMLCollection).map(fileInput => fileInput.value);

            formData.append("no", no);
            formData.append("title", title);
            formData.append("content", content);
            savedFile.forEach(saveFile => {
                formData.append("savedFile", saveFile);
            });

            fetch("/update/all", {
                method: "POST",
                body: formData
            })
                .then(response => {
                    return response.json();
                }).then(data => {
                alert("수정이 완료되었습니다.")
                location = "/viewNotice/" + no;
            })
                .catch(error => {
                    alert(`에러발생 : ${error.message}`);
                })
        }
    }

}

updateNoticeObject.init();