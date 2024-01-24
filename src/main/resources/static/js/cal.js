const init = {
    selectedDates: [],
    today        : new Date(),
    monForChange : new Date().getMonth(),
    activeDate   : new Date(),
    getFirstDay  : (yy, mm) => new Date(yy, mm, 1),
    getLastDay   : (yy, mm) => new Date(yy, mm + 1, 0),

    selectDate         : function (date) {
        const index = this.selectedDates.indexOf(date);
        const $todayCell = document.querySelectorAll(".day");
        let isToday;

        if (index === -1) {
            console.log(index);
            this.selectedDates.push(date);
        }

        $todayCell.forEach((day) => {
            isToday = day.classList.contains("today");
            if (isToday)
                day.classList.remove("today");
            });
        this.applySelectedStyles();
    },

    applySelectedStyles: function () {
        const $days = document.querySelectorAll(".day");
        $days.forEach(($day) => {
            const date = $day.dataset.fdate;
            $day.classList.toggle(
                "day-range",
                this.selectedDates.length === 2 && this.isInRange(date)
            );
        });
    },

    isInRange: function (date) {
        let [start, end] = this.selectedDates;

        if (new Date(start) > new Date(end)) {
            [start, end] = [end, start];
            init.selectedDates = [start, end];
        }
        return date >= start && date <= end;
    },

    prevMonth: function () {
        const today = new Date();
        const prevMonth = this.activeDate.getMonth() - 1;
        const prevYear = this.activeDate.getFullYear();

        if (
            prevYear < today.getFullYear() ||
            (prevYear === today.getFullYear() && prevMonth < today.getMonth())
        ) {
            alert("현재 월 보다 뒤로는 갈 수 없습니다.");
        } else {
            // 그 외의 경우에는 이전 월로 이동
            this.activeDate = this.getFirstDay(prevYear, prevMonth);
        }

        return this.activeDate;
    },

    addZero   : (num) => (num < 10 ? "0" + num : num),
    activeDTag: null,
};

const $calTable = document.querySelector(".cal-table");

function check() {
    const $days = document.querySelectorAll(".day");
    $days.forEach(($day) => {
        const currentDate = $day.dataset.fdate;
        let [start, end] = init.selectedDates;

        if (new Date(start) > new Date(end)) {
            [start, end] = [end, start];
        }

        if (
            new Date(currentDate) >= new Date(start) &&
            new Date(currentDate) <= new Date(end)
        ) {
            $day.classList.add("day-range");
            document
                .querySelector(`td[data-fdate="${init.selectedDates[0]}"]`)
                .classList.add("day-start");
            document
                .querySelector(`td[data-fdate="${init.selectedDates[0]}"]`)
                .classList.add("day-active");
        }
    });
}

$(".ctr-box").append(
    `<a id='prevLink' href="calMonth-1"><i class="xi-angle-left"></i></a><h2>${
        init.monForChange + 1
    } 월</h2><a id='nextLink' href="calMonth-1"><i class="xi-angle-right"></i></a>`
);

let values = 1;
$("#nextLink").on("click", function () {
    values++;
    let ctd = new Date();
    let getMonthd = ctd.getMonth() + values;
    if (values < 13) {
        $(this).attr("href", `#calMonth-${values}`);
        $(".ctr-box > h2").text(`${getMonthd} 월`);
    } else if (values == 13) {
        values = 12;
    }
});

$("#prevLink").on("click", function () {
    --values;
    let ctd = new Date();
    let getMonthd = ctd.getMonth() + values;
    if (values > 0) {
        $(this).attr("href", `#calMonth-${values}`);
        $(".ctr-box > h2").text(`${getMonthd} 월`);
    } else if (values == 0) {
        values = 1;
    }
});

function loadYYMM(fullDate) {
    const today = init.today;

    const yy = fullDate.getFullYear();
    const mm = fullDate.getMonth();

    init.activeDate = init.activeDate || new Date(fullDate);
    let firstDay = init.getFirstDay(yy, mm);
    let lastDay = init.getLastDay(yy, mm);

    const markToday =
        mm === today.getMonth() && yy === today.getFullYear()
            ? today.getDate()
            : undefined;

    let trtd = "";
    let startCount;
    let countDay = 0;

    if (
        yy < today.getFullYear() ||
        (yy === today.getFullYear() && mm < today.getMonth())
    ) {
        init.activeDate = new Date(fullDate);
    }
    for (let k = 0; k < 12; k++) {
        trtd = `<thead id = "calMonth-${k + 1}">
    <tr>
        <th class="calsun">일</th>
        <th>월</th>
        <th>화</th>
        <th>수</th>
        <th>목</th>
        <th>금</th>
        <th class="calsat">토</th>
    </tr>
    </thead>
    <tbody class="cal-body">`;
        if (k !== 0) {
            firstDay = init.getFirstDay(yy, mm + k);
            lastDay = init.getLastDay(yy, mm + k);
        }
        for (let i = 0; i < 6; i++) {
            trtd += "<tr>";
            for (let j = 0; j < 7; j++) {
                if (i === 0 && !startCount && j === firstDay.getDay()) {
                    startCount = 1;
                }
                if (!startCount) {
                    trtd += "<td>";
                } else {
                    const fullDate = `${yy}.${init.addZero(mm + (k + 1))}.${init.addZero(
                        countDay + 1
                    )}`;

                    const currentDate = new Date(yy, mm + k, countDay + 1);
                    const isToday = currentDate.toDateString() === today.toDateString();
                    const isDisabled = currentDate < today && !isToday;
                    trtd += `<td class="day${isDisabled ? " day-disabled" : ""}${
                        isToday ? " today" : ""
                    }" data-date="${
                        countDay + 1
                    }" data-fdate="${fullDate}" onclick ="choiceDate(this)">`;
                }

                trtd += startCount ? ++countDay : "";
                if (countDay === lastDay.getDate()) {
                    startCount = 0;
                    countDay = 0;
                }
                trtd += "</td>";
            }

            trtd += "</tr>";
        }
        trtd += "</tbody>";
        $calTable.innerHTML += trtd;
    }
}

loadYYMM(init.today);

const $nextBtn = document.querySelector(".nextBtn");
const $uSchedule = document.querySelector(".uSchedule");
const $nextDayBtn = document.querySelectorAll(".nextDayBtn");

/**
 * @param {string} val
 */

function choiceDate(t) {
    // 두 번째 클릭 이후에 배열 초기화
    if (init.selectedDates.length === 2) {
        document.querySelector(".day-start").classList.remove("day-start");
        document.querySelector(".day-active").classList.remove("day-active");
        init.selectedDates = [];
    }

    if (t.classList.contains("day")) {
        // day-disabled 클래스가 없는 경우에만 처리
        if (!t.classList.contains("day-disabled")) {
            // 현재 활성화된 날짜 스타일 초기화
            if (new Date(init.selectedDates[0]) < new Date(init.selectedDates[1])) {
                init.activeDTag.classList.replace("day-active", "day-start");
            }

            let day = Number(t.textContent);
            // loadDate(day, t.cellIndex);

            // 날짜를 선택하면 selectDate 함수 호출
            init.selectDate(t.dataset.fdate);
            if (new Date(init.selectedDates[0]) < new Date(init.selectedDates[1])) {
                document
                    .querySelector(`td[data-fdate="${init.selectedDates[0]}"]`)
                    .classList.add("day-start");
                if (
                    document
                        .querySelector(`td[data-fdate="${init.selectedDates[0]}"]`)
                        .classList.contains("day-active")
                ) {
                    document
                        .querySelector(`td[data-fdate="${init.selectedDates[0]}"]`)
                        .classList.remove("day-active");
                }
                document
                    .querySelector(`td[data-fdate="${init.selectedDates[1]}"]`)
                    .classList.add("day-active");
            } else {
                t.classList.add("day-active");
            }

            init.activeDTag = t;
            init.activeDate.setDate(day);

            // 선택한 날짜의 범위에 스타일 적용
            init.applySelectedStyles();
            console.log(init.selectedDates);
        }
    }
}

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

// 초기 스타일 저장
const modalInitialStyles = {
    display        : "none",
    position       : "fixed",
    top            : "0",
    left           : "0",
    width          : "100%",
    height         : "100%",
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    zIndex         : "9999",
};

// 모달 열기
function openModal(btn, data) {
    if (data === "cal") {
        const modal = document.querySelector("#myModal");
        applyModalStyles(modal, modalInitialStyles);
        modal.style.display = "block";
        document.body.style.overflow = "hidden";
    }
}

// 모달 닫기
function closeModal(btn, data) {
    if (data === "cal") {
        const modal = document.querySelector("#myModal");
        applyModalStyles(modal, modalInitialStyles);
        modal.style.display = "none";
        init.selectedDates = [];
        console.log(init.selectedDates);
        const $days = document.querySelectorAll(".day");
        $days.forEach(($day) => {
            $day.classList.remove("day-range", "day-active", "day-start");
        });
    } else if (data === "close") {
        let closeDaysModalArea = document.querySelector(".daysListAddModal");
        applyModalStyles(closeDaysModalArea, modalInitialStyles);
        closeDaysModalArea.style.display = "none";
    }
}

// 모달 스타일을 적용하는 함수
function applyModalStyles(element, styles) {
    if (element) {
        for (const [key, value] of Object.entries(styles)) {
            element.style[key] = value;
        }
    }
}

// 모달 열기 버튼과 닫기 버튼 이벤트 처리
let closeModalButton = document.querySelector("#closeModal");
closeModalButton.addEventListener("click", closeModal);

// 날짜 포맷 지정
function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");
    return `${year}${month}${day}`;
}