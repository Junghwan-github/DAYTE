$(document).ready(function () {
    $("img").each(function() {
        let imageUrl = $(this).attr("src");

        if(!imageUrl || imageUrl.trim() === "") {
            $(this).attr("src", "/images/error-images.png");
        }
    })
})


