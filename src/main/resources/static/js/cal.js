const $nextBtn = document.querySelector(".nextBtn");
const $uSchedule = document.querySelector(".uSchedule");
const $nextDayBtn = document.querySelectorAll(".nextDayBtn");

// "확인" 버튼에 대한 이벤트 핸들러 추가
$nextBtn.addEventListener("click", function (event) {
    // 선택된 날짜 배열 가져오기
    const selectedDates = init.selectedDates;
    const title = document.querySelector("#scheduleSubjectTitle").value;
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
    // POST 요청을 위한 fetch 사용
    fetch("/schedule/scheduleList", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(scheduleDTO),
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            // 서버로부터의 응답 처리
            console.log(data);
            if (data.status === 200) {
                location.href = "/schedule/scheduleList";
            }
        })
        .catch((error) => {
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

// nextDayBtn 버튼 클릭시 이벤트
$nextDayBtn.forEach(nextDay => {
    nextDay.addEventListener("click", function (event) {

        const userSchedule = {
            nextDayValue: nextDay.value,
            nextDayText : $uSchedule.innerText
        }
        fetch('/schedule/map', {
            method : 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body   : JSON.stringify(userSchedule),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                location = "/schedule/map";
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});

function deleteLinks(del) {
    if (confirm('일정을 삭제 하시겠습니까?')) {
        fetch("/schedule/scheduleList/" + del, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
        }).then(response => {
            location = "/schedule/scheduleList";
        }).catch(error => {
            alert(`에러 발생 : ${error.message}`);
        });
    }
}