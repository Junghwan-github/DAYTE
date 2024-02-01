//공지사항 수정 화면에서 파일을 동적으로 제어

let selectedFile = [];
function selectFile(element) {
    const fileInput = document.getElementById(element.id);
    if(fileInput.files.length != 0){
        selectedFile=[];

        const filesArray = Array.from(fileInput.files);
        filesArray.forEach(t => {
            selectedFile.push(t);

            let dataTransfer = new DataTransfer();

            selectedFile.forEach(file => {
                dataTransfer.items.add(file)
            });

            fileInput.files = dataTransfer.files;



        })
    } else if(fileInput.files.length == 0){
        console.log(selectedFile);

        let dataTransfer = new DataTransfer();

        selectedFile.forEach(file => {
            dataTransfer.items.add(file)
        });

        fileInput.files = dataTransfer.files;


    }



    const preview = document.getElementById("newPreview");

    preview.innerHTML = "";


    let filePreview = Array.from(fileInput.files)
    filePreview.forEach(file => {
        preview.innerHTML += `
        <p id="${file.lastModified}">
        ${file.name}
        <button data-index='${file.lastModified}' class='file-remove' onclick="removeFile(this)">X</button>
        </p>
        `
    })
}

function removeFile(element) {
    let removeTargetId = element.dataset.index;
    let removeTarget = document.getElementById(removeTargetId);
    let fileInput = document.getElementById("file-input");
    let dataTransfer = new DataTransfer();

    Array.from(fileInput.files)
        .filter(file => file.lastModified != removeTargetId)
        .forEach(file => {
            dataTransfer.items.add(file);
            console.log(file.name);
            console.log(file);


        })


    fileInput.files = dataTransfer.files;

    selectedFile = [];

    const filesArray = Array.from(fileInput.files);
    filesArray.forEach(t => {
        selectedFile.push(t);
    })

    removeTarget.remove();

}

/*function addFile() {
    const addFile = document.getElementById("file-add");
    let originalFile = document.getElementById("file-input");
    let dataTransfer = new DataTransfer();

    Array.from(originalFile.files)
        .forEach(file => {
            dataTransfer.items.add(file);
        });

    Array.from(addFile.files)
        .forEach(file => {
            dataTransfer.items.add(file);
        });

    console.log("새로 담길 파일 : " + dataTransfer.files);

    originalFile.files = dataTransfer.files;

    addFile.value = "";
    console.log("파일이 추가된 원본 파일리스트 : " + originalFile.files);
    console.log("추가파일리스트, 0이어야함 : " + addFile.files);

    updatePreview();

}*/

function updatePreview(){
    const fileInput = document.getElementById("file-input");
    const preview = document.getElementById("newPreview");

    preview.innerHTML = "";


    let updatePreview = Array.from(fileInput.files)
    console.log(updatePreview);
    updatePreview.forEach(file => {
        preview.innerHTML += `
        <p id="${file.lastModified}">
        ${file.name}
        <button data-index='${file.lastModified}' class='file-remove' onclick="removeFile(this)">X</button>
        </p>
        `
    })
}


function removeFileOnView(element){
    element.parentNode.remove();
}
