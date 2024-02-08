// 첫 Schedule 저장하는 함수
$(".nextBtn").on("click", function () {
    // 선택된 날짜 배열 가져오기
    const selectedDates = init.selectedDates;
    const title = $("#scheduleSubjectTitle").val();
    let startDate = formatDate(selectedDates[0]);
    let endDate = formatDate(selectedDates[1]);
    if (isNaN(endDate)) {
        endDate = startDate;
    }

    // 요청을 보내기 전에 scheduleDTO를 유효성 검사
    if (!validateScheduleDTO(title, startDate, endDate)) {
        // 유효성 검사 실패, 오류를 처리하거나 메시지를 표시
        console.error("유효하지 않은 scheduleDTO");
        return;
    }

    const scheduleDTO = {
        title    : title,
        startDate: startDate,
        endDate  : endDate,
    };
    $.ajax({
        url        : "/schedule/scheduleList",
        type       : "POST",
        contentType: "application/json; charset=utf-8",
        data       : JSON.stringify(scheduleDTO)
    })
        .done(function (data) {
            if (data.status === 200) {
                location.href = "/schedule/scheduleList";
            } else if (data.status === 409) {
                let deleteSchedule = confirm("일정이 이미 있습니다 삭제 하고 다시 생성 하시겠습니까?");
                if (deleteSchedule) {
                    // 다른 API 엔드포인트로의 DELETE 요청
                    $.ajax({
                        url        : "/schedule/deleteAndInsertSchedule",
                        type       : "DELETE",
                        contentType: "application/json",
                        data       : JSON.stringify(deleteUuid)
                    })
                        .done(function (deleteData) {
                            if (deleteData.status === 200) {
                                location.reload();
                            } else {
                                console.error("일정 삭제 실패:", deleteData.message);
                            }
                        })
                        .fail(function (deleteError) {
                            console.error("일정 삭제 오류:", deleteError);
                        });
                }
            } else {
                console.error("서버 오류:", data.message);
            }
        })
        .fail(function (error) {
            console.error("오류:", error);
        });
});

// scheduleDTO를 유효성 검사하는 함수
function validateScheduleDTO(title, startDate, endDate) {
    // 타이틀이 비어있지 않은지 확인
    if (!title.trim()) {
        alert("일정 제목을 입력 해 주세요");
        return false;
    }

    // 시작일이나 종료일이 비어있지 않은지 확인
    if (isNaN(startDate) || isNaN(endDate)) {
        alert("날짜를 선택하세요");
        return false;
    }
    return true;
}

// 일정 삭제 함수
function deleteLinks(startDate) {
    if (confirm('일정을 삭제 하시겠습니까?')) {
        $.ajax({
            url        : "/schedule/deleteSchedule/" + startDate,
            type       : "DELETE",
            contentType: "application/json; charset=utf-8",
            success    : function (data) {
                alert("일정이 삭제 되었습니다.")
                location.reload();
            },
            error      : function (error) {
                alert("error");
            }
        });
    }
};

// 날짜의 자세한 일정을 저장하는 함수
function scheduleTotalSaveBtn() {
    try {
        let saveSchedule = [];
        $(".contentsListItemSelected").each(function () {
            let button = $(this).find("button");
            saveSchedule.push(button.val());
        });

        const userSchedule = {
            nowDate     : $(".daysValue").text(),
            uuid        : $(".tableUuid").text(),
            contentsList: saveSchedule
        };

        $.ajax({
            url        : "/schedule/saveSchedule",
            method     : "POST",
            contentType: "application/json; charset=utf-8",
            data       : JSON.stringify(userSchedule),
            success    : function (response) {
                console.log(response);
                location.reload();
            },
            error      : function (error) {
                alert(`에러 발생: ${error.message}`);
            }
        });
    } catch (error) {
        alert(`에러 발생: ${error.message}`);
    }
}

let nowDate;  // 밖에서 변수를 선언
let uuid;
let saveSchedule = [];

// 일정을 수정하는 함수
function scheduleTotalModifyBtn() {
    $(".nextDayBtn").each(function () {
        if (nowDateValue === $(this).data("now-days")) {
            nowDate = $(this).data("now-days");
            uuid = $(this).val();
            $(".contentsListItemSelected").each(function (index) {
                console.log(index)
                if (saveSchedule[index] != $(this).find("button").val()) {
                    console.log("true")
                    console.log(saveSchedule[index])
                    let button = $(this).find("button");
                    saveSchedule.push(button.val());
                }
            });
        }
    });
    const detailedSchedule = {
        nowDate     : nowDate,
        uuid        : uuid,
        contentsList: saveSchedule
    }
    $.ajax({
        url        : "/schedule/detailedScheduleModify",
        type       : "PUT",
        contentType: "application/json; charset=utf-8",
        data       : JSON.stringify(detailedSchedule),
        success    : function (response) {
            location.reload();
        },
        error      : function (error) {
            alert(`에러 발생 : ${error.message}`);
        }
    })
}