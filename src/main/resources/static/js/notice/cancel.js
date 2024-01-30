var cancelButton = document.getElementById("btn-cancel");

cancelButton.addEventListener("click", backToWeb);

function backToWeb() {
    window.history.back();
}