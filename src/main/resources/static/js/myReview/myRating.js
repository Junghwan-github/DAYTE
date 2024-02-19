$(document).ready(function () {

    $(".star-point").each(function () {
        let star = parseInt($(this).text());
        switch (star) {
            case 1:
                $(this).text("★");
                break;
            case 2:
                $(this).text("★★");
                break;
            case 3:
                $(this).text("★★★");
                break;
            case 4:
                $(this).text("★★★★");
                break;
            case 5:
                $(this).text("★★★★★");
                break;
        }
    })
})


