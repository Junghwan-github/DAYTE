// "확인" 버튼에 대한 이벤트 핸들러 추가
const $nextBtn = $(".nextBtn");

$nextBtn.on("click", function(event) {
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
        title: title,
        startDate: startDate,
        endDate: endDate,
    };

    // POST 요청을 위한 jQuery AJAX 사용
    $.ajax({
        url: "/schedule/scheduleList",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(scheduleDTO),
        success: function(data) {
            // 서버로부터의 응답 처리
            console.log(data);
            if (data.status === 200) {
                location.href = "/schedule/scheduleList";
            } else if (data.status === 409) {
                let deleteSchedule = confirm("일정이 이미 있습니다 삭제 하고 다시 생성 하시겠습니까?");
                if (deleteSchedule) {
                    // 다른 API 엔드포인트로의 DELETE 요청
                    $.ajax({
                        url: "/schedule/deleteAndInsertSchedule",
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(scheduleDTO),
                        success: function(deleteData) {
                            if (deleteData.status === 200) {
                                console.log(deleteData);
                                location.href = "/schedule/scheduleList";
                            } else {
                                console.error("일정 삭제 실패:", deleteData.message);
                            }
                        },
                        error: function(deleteError) {
                            console.error("일정 삭제 오류:", deleteError);
                        }
                    });
                }
            } else {
                console.error("서버 오류:", data.message);
            }
        },
        error: function(error) {
            console.error("오류:", error);
        }
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

// 일정 삭제 onclick 메서드
function deleteLinks(startDate) {
    if (confirm('일정을 삭제 하시겠습니까?')) {
        // 다른 API 엔드포인트로의 DELETE 요청
        $.ajax({
            url: "/schedule/scheduleList/" + startDate,
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            success: function(response) {
                location = "/schedule/scheduleList";
            },
            error: function(error) {
                alert(`에러 발생 : ${error.message}`);
            }
        });
    }
};