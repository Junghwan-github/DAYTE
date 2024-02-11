/*main 슬라이더 */
$(document).ready(function () {
    $(".mainSlider").bxSlider({
        mode: "horizontal",
        slideWidth: 1180,
        speed: 500,
        auto: true,
        slideMargin: 30,
        minSlides: 1,
        maxSlides: 2,
        touchEnabled: navigator.maxTouchPoints > 0,
    });
});

// 검색바 슬라이더
$(document).ready(function () {
    $(".previewImage > ul").bxSlider({
        mode: "horizontal",
        speed: 500,
        slideWidth: 660,
        auto: true,
        slideMargin: 0,
        autoHover: true,
    });
});

// 상세 검색 히든
let toggleSelected = document.querySelector(".searchContents");
let bottonSearch = document.querySelector("input[name='indexSearch']");
let cityList = document.querySelector(".cityList");

bottonSearch.addEventListener("click", function (e) {
    toggleSelected.classList.add("selected");
    e.stopPropagation();
});

document.addEventListener("click", () => {
    toggleSelected.classList.remove("selected");
});

let selectText = cityList.querySelectorAll("ul > li");
selectText.forEach(function (t) {
    t.addEventListener("click", () => {
        bottonSearch.value = t.innerText;
    });
});

selectText.forEach(function (t) {
    t.addEventListener("mouseenter", () => {
        if (t.innerText === "중구") {
            gugoon = "c-jg";
        } else if (t.innerText === "수성구") {
            gugoon = "c-ssg";
        } else if (t.innerText === "북구") {
            gugoon = "c-bg";
        } else if (t.innerText === "서구") {
            gugoon = "c-sg";
        } else if (t.innerText === "동구") {
            gugoon = "c-dg";
        } else if (t.innerText === "달서구") {
            gugoon = "c-dsg";
        } else if (t.innerText === "달성군") {
            gugoon = "c-dsgn";
        } else if (t.innerText === "군의군") {
            gugoon = "c-geg";
        }

        document.querySelector(".previewImage > div > div > ul").className = gugoon;
    });
});

// 날씨 api

// 시간 설정 함수 (초단기)

function getUpdatedDate(t) {
    const currentDate = new Date();

    const nextUpdateDate = new Date(currentDate);
    nextUpdateDate.setHours(t, 0, 0, 0);

    if (currentDate < nextUpdateDate) {
        const year = currentDate.getFullYear();
        const month = (currentDate.getMonth() + 1).toString().padStart(2, "0");
        const day = currentDate.getDate().toString().padStart(2, "0");
        const formattedDate = year + month + day;
        return formattedDate;
    }

    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, "0");
    const day = currentDate.getDate().toString().padStart(2, "0");
    const formattedDate = year + month + day;

    nextUpdateDate.setDate(currentDate.getDate() + 1);
    const timeDiff = nextUpdateDate - currentDate;
    setTimeout(function () {
        getUpdatedDate();
    }, timeDiff);
    return formattedDate;
}

function getUpdatedDatePlus(t) {
    const currentDate = new Date();

    currentDate.setDate(currentDate.getDate() + t);
    const formattedDate1 = currentDate.toISOString().slice(0, 10).replace(/-/g, "");
    return formattedDate1;
}

function thisDate(t) {
    const currentDate = new Date();

    if (
        t == "0000" ||
        t == "0100" ||
        t == "0200" ||
        t == "0300" ||
        t == "0400" ||
        t == "0500"
    ) {
        currentDate.setDate(currentDate.getDate() + 1);
        const formattedDate1 = currentDate.toISOString().slice(0, 10).replace(/-/g, "");
        return formattedDate1;
    }

    const formattedDate1 = currentDate.toISOString().slice(0, 10).replace(/-/g, "");

    return formattedDate1;
}

function halfTime(d) {
    if (d % 2 === 0) {
        return "pm";
    } else {
        return "am";
    }
}

// 단기 api 불러오기
let apiShort =
    "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=coQyCyc75MfuQqHbbxJydRKCCUcqGUPYrhfREOFKrPf6DaV%2FvpQrWaDPAP%2B7fOxTTae5KgaO4Et0Jy1pQb7Opg%3D%3D&pageNo=1&numOfRows=1000&DataType=JSON&base_date=" +
    getUpdatedDate(3) +
    "&base_time=0200&nx=89&ny=90";

//기온
let apiLong1 =
    "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey=coQyCyc75MfuQqHbbxJydRKCCUcqGUPYrhfREOFKrPf6DaV%2FvpQrWaDPAP%2B7fOxTTae5KgaO4Et0Jy1pQb7Opg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&regId=11H10701&tmFc=" +
    getUpdatedDate(7) +
    "0600";

//날씨
let apiLong2 =
    "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey=coQyCyc75MfuQqHbbxJydRKCCUcqGUPYrhfREOFKrPf6DaV%2FvpQrWaDPAP%2B7fOxTTae5KgaO4Et0Jy1pQb7Opg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&regId=11H10000&tmFc=" +
    getUpdatedDate(7) +
    "0600";

fetch(apiShort)
    .then((response) => response.json())
    .then((json) => {
        const apiData = {};
        apiData.pty = [];
        apiData.sky = [];
        apiData.tmp = [];
        apiData.pop = [];
        apiData.tmn = [];
        apiData.tmx = [];
        let data = json.response.body.items.item;


        for (let i = 0; i < data.length; i++) {
            const fcstDate = data[i].fcstDate;
            const fcstTime = data[i].fcstTime;
            const fcstValue = data[i].fcstValue;

            if (
                fcstDate == getUpdatedDate() ||
                fcstDate == getUpdatedDatePlus(1) ||
                fcstDate == getUpdatedDatePlus(2)
            ) {
                const category = data[i].category;
                const entry = {date: fcstDate, Time: fcstTime, value: fcstValue};

                if (["PTY", "SKY", "TMP", "POP", "TMN", "TMX"].includes(category)) {
                    apiData[category.toLowerCase()].push(entry);
                }
            }
        }


        let temperature = document.querySelector(".temperature");
        let ptyValue = "";
        let ptyv = "";
        let tmn = "";
        let tmx = "";
        let pop = "";

        function nowHours(int) {
            let currentDate = new Date();

            currentDate.setHours(currentDate.getHours() + int);

            return currentDate.getHours().toString().padStart(2, "0");
        }

        function nowDay(int) {
            let currentDate = new Date();

            currentDate.setDate(currentDate.getDate() + int);

            return currentDate.getDate().toString().padStart(2, "0");
        }

        // 현재 날씨 최저 최고기온
        for (let i = 0; i < 3; i++) {
            if (apiData.tmn[i].date == thisDate()) {
                tmn = apiData.tmn[i].value;
            }

            if (apiData.tmx[i].date == thisDate()) {
                tmx = apiData.tmx[i].value;
            }

        }
        document.querySelector(
            ".minMaxtemp"
        ).innerHTML = `${tmn}<span>º</span> / ${tmx}<span>º</span>`;

        for (let i = 0; i < apiData.pty.length; i++) {
            if (
                apiData.pty[i].date == thisDate() &&
                apiData.pty[i].Time == nowHours(0) + "00"
            ) {
                switch (apiData.pty[i].value) {
                    case "1":
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/rain.png)"
                        );
                        break;
                    case "2":
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/rainsnow.png)"
                        );
                        break;
                    case "3":
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/snow.png)"
                        );
                        break;
                    case "4":
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/rainy.png)"
                        );
                        break;
                }
            }
            if (apiData.pty[i].value == "0") {
                if (
                    apiData.sky[i].date == thisDate() &&
                    apiData.sky[i].Time == nowHours(0) + "00"
                ) {
                    if (apiData.sky[i].value == "1") {
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/sunny.png)"
                        );
                    } else if (apiData.sky[i].value == "3") {
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/cloud.png)"
                        );
                    } else if (apiData.sky[i].value == "4") {
                        $(".weatherIcon").css(
                            "background-image",
                            "url(../images/clouds.png)"
                        );
                    }
                }
            }
            if (
                apiData.tmp[i].date == thisDate() &&
                apiData.tmp[i].Time == nowHours(0) + "00"
            ) {
                temperature.innerHTML = apiData.tmp[i].value + "<span>º</span>";
            }
            if (
                apiData.pop[i].date == thisDate() &&
                apiData.pop[i].Time == nowHours(0) + "00"
            ) {
                pop = apiData.pop[i].value;
            }
            document.querySelector(
                ".presentRainPercent"
            ).innerHTML = `<span class="material-symbols-outlined">rainy</span> ${pop} %`;
        }

        // 시간예보  현재 시간 부터 6시간 뒤 까지
        for (let k = 1; k < 7; k++) {
            const hoursListId = `hoursList${k}`;

            $(".today6Hours > ul").append(
                `<li class='${hoursListId}'><h4>${nowHours(k)} 시</h4></li>`
            );

            $(`.${hoursListId}`).append(`<div class="hoursListIcon"></div>`);

            for (let m = 0; m < apiData.pty.length; m++) {
                if (
                    apiData.pty[m].date == thisDate(apiData.pty[m].Time) &&
                    apiData.pty[m].Time == nowHours(k) + "00"
                ) {
                    console.log(apiData.pty[m]);
                    switch (apiData.pty[m].value) {
                        case "1":
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/rain.png)"
                            );
                            break;
                        case "2":
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/rainsnow.png)"
                            );
                            break;
                        case "3":
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/snow.png)"
                            );
                            break;
                        case "4":
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/rainy.png)"
                            );
                            break;
                    }
                }
                if (apiData.pty[m].value == "0") {
                    if (
                        apiData.sky[m].date == thisDate(apiData.sky[m].Time) &&
                        apiData.sky[m].Time == nowHours(k) + "00"
                    ) {
                        if (apiData.sky[m].value == "1") {
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/sunny.png)"
                            );
                        } else if (apiData.sky[m].value == "3") {
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/cloud.png)"
                            );
                        } else if (apiData.sky[m].value == "4") {
                            $(`.${hoursListId} .hoursListIcon`).css(
                                "background-image",
                                "url(../images/clouds.png)"
                            );
                        }
                    }
                }

                if (
                    apiData.tmp[m].date == thisDate(apiData.tmp[m].Time) &&
                    apiData.tmp[m].Time == nowHours(k) + "00"
                ) {
                    $(`.${hoursListId}`).append(
                        `<ul><li class="hoursListTmp">${apiData.tmp[m].value} <span>º</span></li></ul>`
                    );
                }

                if (
                    apiData.pop[m].date == thisDate(apiData.pop[m].Time) &&
                    apiData.pop[m].Time == nowHours(k) + "00"
                ) {
                    $(`.${hoursListId} > ul`).append(
                        `<li class="hoursListPop"><span class="material-symbols-outlined">rainy</span> ${apiData.pop[m].value} %</li>`
                    );
                }
            }
        }


        function roundToNearestTen(num) {
            return Math.round(num / 10) * 10;
        }

        let formattedDate = apiData.tmn[0].date.replace(/(\d{4})(\d{2})(\d{2})/, "$1-$2-$3");

        let weatherInfo = {};
        weatherInfo.maxTem = [];
        weatherInfo.minTem = [];
        weatherInfo.weather = [];
        weatherInfo.pop = [];


//기온
        fetch(apiLong1)
            .then((response) => response.json())
            .then((json) => {
                let data2 = json.response.body.items.item;


                //날씨
                fetch(apiLong2)
                    .then((response) => response.json())
                    .then((json) => {

                        //내일부터 6일 뒤의 기온, 날씨, 강수량 화면단에 불러오기


                        for (let i = 0; i < 2; i++) {

                            let stringToDate = new Date(formattedDate);
                            stringToDate.setDate(stringToDate.getDate() + (i + 1));
                            let DateToString = stringToDate.toISOString().slice(0, 10).replace(/-/g, "");

                            /* 1일, 2일 최고 기온, 최저 기온 추출  */
                            let dayTemperatures = [];

                            apiData.tmp.forEach(k => {
                                if (k.date == DateToString) {
                                    dayTemperatures.push(parseInt(k.value));
                                }
                            })

                            let maxTemperatureOfDay = dayTemperatures[0];
                            let minTemperatureOfDay = dayTemperatures[0];

                            for (let i = 0; i < dayTemperatures.length; i++) {
                                if (dayTemperatures[i] > maxTemperatureOfDay) {
                                    maxTemperatureOfDay = dayTemperatures[i];
                                }
                            }
                            for (let i = 0; i < dayTemperatures.length; i++) {
                                if (dayTemperatures[i] < minTemperatureOfDay) {
                                    minTemperatureOfDay = dayTemperatures[i];
                                }
                            }

                            weatherInfo.maxTem.push(maxTemperatureOfDay);
                            weatherInfo.minTem.push(minTemperatureOfDay);

                            /* ============= 1일, 2일 최고 기온, 최저 기온 추출 여기까지 int형 ================ */


                            /*============== 1일, 2일 pty 추출 ==========================*/
                            let morningPtyList = [];
                            let afternoonPtyList = [];


                            apiData.pty.forEach(k => {

                                if (k.date == DateToString) {
                                    if (parseInt(k.Time) < 1200) {
                                        morningPtyList.push(parseInt(k.value));
                                    } else {
                                        afternoonPtyList.push(parseInt(k.value));
                                    }
                                }
                            })

                            let morningSkyList = [];
                            let afternoonSkyList = [];

                            apiData.sky.forEach(k => {
                                if (k.date == DateToString) {
                                    if (parseInt(k.Time) < 1200) {
                                        morningSkyList.push(parseInt(k.value));
                                    } else {
                                        afternoonSkyList.push(parseInt(k.value));
                                    }
                                }
                            })


                            // console.log(morningSkyList);
                            // console.log(afternoonSkyList);

                            //exampleArray1,2 는 나중에 morningPtyList, afternoonPtyListfh 바꾸기

                            //let exampleArray1 = [2, 1, 1, 1, 0, 3, 2, 3, 1, 3, 3, 4];
                            // let exampleArray2 = [3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3];
                            // let ampmPty = [exampleArray1, exampleArray2];
                            let morning = [morningPtyList, morningSkyList];
                            let afternoon = [afternoonPtyList, afternoonSkyList];
                            let ampmPty = [morning, afternoon];


                            weatherInfo.weather[i] = {};


                            ampmPty.forEach(array => {


                                let valuesToCheck = [1, 2, 3, 4];
                                let includesAny = valuesToCheck.some(value => array[0].includes(value));

                                if (includesAny) {
                                    // console.log("배열 안에 1, 2, 3, 4 중 어떤 값이라도 포함되어 있음");
                                    let frequency = valuesToCheck.reduce((result, valueToCheck) => {
                                        result[valueToCheck] = array[1].filter(value => value === valueToCheck).length;
                                        return result;
                                    }, {});

                                    let maxFrequencyValues = Math.max(...Object.values(frequency));
                                    let maxFrequency = Object.keys(frequency).filter(key => frequency[key] === maxFrequencyValues).map(value => parseInt(value));

                                    // pty의 값의 최대빈도수가 1과 3이라면 2로 변경
                                    if (maxFrequency.length >= 2 && (maxFrequency.includes(1) && maxFrequency.includes(3))) {
                                        maxFrequency.length = 1;
                                        maxFrequency[0] = 2;
                                    }

                                    switch (maxFrequency[0]) {
                                        case 1:
                                        case 4:
                                            maxFrequency[0] = "비";
                                            break;
                                        case 2:
                                            maxFrequency[0] = "비눈";
                                            break;
                                        case 3:
                                            maxFrequency[0] = "눈";

                                    }


                                    if (weatherInfo.weather[i].wea1 == null) {
                                        weatherInfo.weather[i].wea1 = maxFrequency[0];
                                    } else {
                                        weatherInfo.weather[i].wea2 = maxFrequency[0];
                                    }


                                    // 필요한 값은 최대 빈도수를 가지는 번호: maxFrequency
                                } else {
                                    // console.log("배열 안에 1, 2, 3, 4 값이 포함되어 있지 않음");


                                    let skyList = array[1];

                                    let valuesToCheck = [3, 4];
                                    let includesAny = valuesToCheck.some(value => skyList.includes(value));

                                    if (includesAny) {
                                        // console.log("배열 안에 3, 4 중 어떤 값이라도 포함되어 있음");
                                        let frequency = valuesToCheck.reduce((result, valueToCheck) => {
                                            result[valueToCheck] = skyList.filter(value => value === valueToCheck).length;
                                            return result;
                                        }, {});

                                        let maxFrequencyValues = Math.max(...Object.values(frequency));
                                        let maxFrequency = Object.keys(frequency).filter(key => frequency[key] === maxFrequencyValues).map(value => parseInt(value));


                                        switch (maxFrequency[0]) {
                                            case 1:
                                                maxFrequency[0] = "맑음";
                                                break;
                                            case 3:
                                                maxFrequency[0] = "구름많음";
                                                break;
                                            case 4:
                                                maxFrequency[0] = "흐림";
                                                break;
                                        }


                                        if (weatherInfo.weather[i].wea1 == null) {
                                            weatherInfo.weather[i].wea1 = maxFrequency[0];
                                        } else {
                                            weatherInfo.weather[i].wea2 = maxFrequency[0];
                                        }

                                    }
                                }


                            });

                            /*========= 1일, 2일 pty 추출 여기까지 ==================*/


                            /*========= 1일, 2일 pop 추출 ==================*/


                            let morningPopList = [];
                            let afternoonPopList = [];


                            apiData.pop.forEach(k => {

                                if (k.date == DateToString) {
                                    if (parseInt(k.Time) < 1200) {
                                        morningPopList.push(parseInt(k.value));
                                    } else {
                                        afternoonPopList.push(parseInt(k.value));
                                    }
                                }
                            })

                            let plusMorningPop = 0;

                            morningPopList.forEach(k => {
                                plusMorningPop += k;
                            })

                            let morningPop = roundToNearestTen(Math.floor(plusMorningPop / 12));

                            let plusAfternoonPop = 0;
                            afternoonPopList.forEach(k => {
                                plusAfternoonPop += k;
                            })

                            let afternoonPop = roundToNearestTen(Math.floor(plusAfternoonPop / 12));

                            weatherInfo.pop[i] = {};

                            weatherInfo.pop[i].pop1 = morningPop;
                            weatherInfo.pop[i].pop2 = afternoonPop;


                            /*========= 1일, 2일 pop 추출 여기까지 ==================*/


                        }


                        let data3 = json.response.body.items.item;

                        console.log(data2[0]);
                        console.log(data3[0]);

                        /* == 중기 기온 조회 + 단기와 중기 기온 한 배열에 담음 ==*/

                        const taMinpattern = /^taMin\d+$/i;
                        const taMinfilteredKeys = Object.keys(data2[0]).filter(key => taMinpattern.test(key));
                        const taMinValues = taMinfilteredKeys.map(key => data2[0][key]);

                        const taMaxpattern = /^taMax\d+$/i;
                        const taMaxfilteredKeys = Object.keys(data2[0]).filter(key => taMaxpattern.test(key));
                        const taMaxValues = taMaxfilteredKeys.map(key => data2[0][key]);


                        for (let i = 0; i < 4; i++) {
                            weatherInfo.maxTem.push(taMaxValues[i]);
                            weatherInfo.minTem.push(taMinValues[i]);
                        }
                        console.log(weatherInfo);

                        /* == 중기 기온 조회 + 단기와 중기 기온 한 배열에 담음  여기까지 ==*/


                        /* == 중기 날씨 정보 3,4,5,6일 후 날씨 배열에 담음 == */

                        for (let i = 2; i < 6; i++) {
                            let morningArray = Object.keys(data3[0])
                                .filter(key => key.match(new RegExp(`^wf${i + 1}(Am)$`)))
                                .map(key => data3[0][key]);

                            let afternoonArray = Object.keys(data3[0])
                                .filter(key => key.match(new RegExp(`^wf${i + 1}(Pm)$`)))
                                .map(key => data3[0][key]);


                            weatherInfo.weather[i] = {};

                            weatherInfo.weather[i].wea1 = morningArray[0];
                            weatherInfo.weather[i].wea2 = afternoonArray[0];

                        }

                        /* == 중기 날씨 정보 3,4,5,6일 후 날씨 배열에 담음  여기까지 == */

                        /* == 중기 강수량 정보 배열에 담음 */


                        for (let i = 2; i < 6; i++) {
                            let rnStMorningArray = Object.keys(data3[0])
                                .filter(key => key.match(new RegExp(`^rnSt${i + 1}(Am)$`)))
                                .map(key => data3[0][key]);

                            let rnStAfternoonArray = Object.keys(data3[0])
                                .filter(key => key.match(new RegExp(`^rnSt${i + 1}(Pm)$`)))
                                .map(key => data3[0][key]);


                            weatherInfo.pop[i] = {};

                            weatherInfo.pop[i].pop1 = rnStMorningArray[0];
                            weatherInfo.pop[i].pop2 = rnStAfternoonArray[0];

                        }


                        for (let i = 0; i < 6; i++) {

                            const daysListId = `daysList${i + 1}`;

                            $(".daysWeather > ul").append(
                                `<li class='${daysListId}'><h4>${nowDay(i + 1)} 일</h4></li>`
                            );

                            for (let d = 1; d < 3; d++) {
                                $(`.${daysListId}`).append(`<div class="${halfTime(d)}">`);
                            }
                            $(`.${daysListId} > .am`).append(`<h4>오전</h4>`);
                            $(`.${daysListId} > .pm`).append(`<h4>오후</h4>`);
                            $(`.${daysListId} > div`).append(`<div class="daysListIcon"></div>`);
                            $(`.${daysListId} > div`).append(`<div class="rainPercentDiv"></div>`);
                            $(`.${daysListId}`).append(`<ul class="temperatureOfDay"></ul>`);


                            //오전 날씨
                            switch (weatherInfo.weather[i].wea1) {
                                case "맑음":
                                    console.log(i + 2 + "번 째날 오전 맑음");
                                    $(`.${daysListId} .am .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/sunny.png')"
                                    );
                                    break;
                                case "흐림":
                                    console.log(i + 2 + "번 째날 오전 흐림");
                                    $(`.${daysListId} .am .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/cloud.png')"
                                    );
                                    break;
                                case "구름많음":
                                    console.log(i + 2 + "번 째날 오전 구름많음");
                                    $(`.${daysListId} .am .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/clouds.png')"
                                    );
                                    break;
                                case "비":
                                case "구름많고 비":
                                case "흐리고 비":
                                    console.log(i + 2 + "번 째날 오전 비");
                                    $(`.${daysListId} .am .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/rain.png')"
                                    );
                                    break;
                                case "비눈":
                                    console.log(i + 2 + "번 째날 오전 비눈");
                                    $(`.${daysListId} .am .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/rainsnow.png')"
                                    );
                                    break;
                                case "눈":
                                case "구름많고 눈":
                                case "흐리고 눈":
                                    console.log(i + 2 + "번 째날 오전 눈");
                                    $(`.${daysListId} .am .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/snow.png')"
                                    );
                                    break;
                            }

                            //오후 날씨
                            switch (weatherInfo.weather[i].wea2) {
                                case "맑음":
                                    console.log(i + 2 + "번 째날 오후 맑음");
                                    $(`.${daysListId} .pm .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/sunny.png')"
                                    );
                                    break;
                                case "흐림":
                                    console.log(i + 2 + "번 째날 오후 흐림");
                                    $(`.${daysListId} .pm .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/cloud.png')"
                                    );
                                    break;
                                case "구름많음":
                                    console.log(i + 2 + "번 째날 오후 구름많음");
                                    $(`.${daysListId} .pm .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/clouds.png')"
                                    );
                                    break;
                                case "비":
                                case "구름많고 비":
                                case "흐리고 비":
                                    console.log(i + 2 + "번 째날 오후 비");
                                    $(`.${daysListId} .pm .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/rain.png')"
                                    );
                                    break;
                                case "비눈":
                                    console.log(i + 2 + "번 째날 오후 비눈");
                                    $(`.${daysListId} .pm .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/rainsnow.png')"
                                    );
                                    break;
                                case "눈":
                                case "구름많고 눈":
                                case "흐리고 눈":
                                    console.log(i + 2 + "번 째날 오후 눈");
                                    $(`.${daysListId} .pm .daysListIcon`).css(
                                        "background-image",
                                        "url('../images/snow.png')"
                                    );
                                    break;
                            }

                            $(`.${daysListId} .am .rainPercentDiv`).append(`<span class="rainPercent">${weatherInfo.pop[i].pop1} %</span>`);
                            $(`.${daysListId} .pm .rainPercentDiv`).append(`<span class="rainPercent">${weatherInfo.pop[i].pop2} %</span>`);

                            $(`.${daysListId} .temperatureOfDay`).append(`<li class="maxTemperature">${weatherInfo.maxTem[i]}º</li><li class="slash">/</li><li class="minTemperature">${weatherInfo.minTem[i]}º</li>`);


                        }


                    })
                    .catch((error3) => console.log(error3))
            })
            .catch((error2) => console.log(error2));

    })
    .catch((error) => console.log(error));


function indexContentsListLink (categoryName) {

    location.href = "/contents/category/" + categoryName ;

}