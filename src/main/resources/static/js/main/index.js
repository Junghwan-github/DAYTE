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

  currentDate.setDate(currentDate.getDate() +t);
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
    currentDate.setDate(currentDate.getDate() +1);
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
          const entry = { date: fcstDate, Time: fcstTime, value: fcstValue };

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
            apiData.pty[i].Time == nowHours() + "00"
        ) {
          ptyValue = apiData.pty[i].value;
          switch (ptyValue) {
            case 1:
              $(".weatherIcon").css(
                  "background-image",
                  "url(../images/rain.png)"
              );
              break;
            case 2:
              $(".weatherIcon").css(
                  "background-image",
                  "url(../images/rainsnow.png)"
              );
              break;
            case 3:
              $(".weatherIcon").css(
                  "background-image",
                  "url(../images/snow.png)"
              );
              break;
            case 4:
              $(".weatherIcon").css(
                  "background-image",
                  "url(../images/rainy.png)"
              );
              break;
          }
        }
        if (ptyValue == 0) {
          if (
              apiData.sky[i].date == thisDate() &&
              apiData.sky[i].Time == nowHours(0) + "00"
          ) {
            if (apiData.sky[i].value == 1) {
              $(".weatherIcon").css(
                  "background-image",
                  "url(../images/sunny.png)"
              );
            } else if (apiData.sky[i].value == 3) {
              $(".weatherIcon").css(
                  "background-image",
                  "url(../images/cloud.png)"
              );
            } else if (apiData.sky[i].value == 4) {
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
            ".rainPercent"
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
            switch (ptyv) {
              case 1:
                $(`.${hoursListId} .hoursListIcon`).css(
                    "background-image",
                    "url(../images/rain.png)"
                );
                break;
              case 2:
                $(`.${hoursListId} .hoursListIcon`).css(
                    "background-image",
                    "url(../images/rainsnow.png)"
                );
                break;
              case 3:
                $(`.${hoursListId} .hoursListIcon`).css(
                    "background-image",
                    "url(../images/snow.png)"
                );
                break;
              case 4:
                $(`.${hoursListId} .hoursListIcon`).css(
                    "background-image",
                    "url(../images/rainy.png)"
                );
                break;
            }
          }
          if (ptyv == 0) {
            if (
                apiData.sky[m].date == thisDate(apiData.sky[m].Time) &&
                apiData.sky[m].Time == nowHours(k) + "00"
            ) {
              if (apiData.sky[m].value == 1) {
                $(`.${hoursListId} .hoursListIcon`).css(
                    "background-image",
                    "url(../images/sunny.png)"
                );
              } else if (apiData.sky[m].value == 3) {
                $(`.${hoursListId} .hoursListIcon`).css(
                    "background-image",
                    "url(../images/cloud.png)"
                );
              } else if (apiData.sky[m].value == 4) {
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

      for(let i=0; i<apiData.tmn.length; i++){
        console.log(apiData.tmn[i]);
      }



      let formattedDate = apiData.tmn[0].date.replace(/(\d{4})(\d{2})(\d{2})/, "$1-$2-$3");

      /*let tomorrowDate = new Date(formattedDate);
      tomorrowDate.setDate(tomorrowDate.getDate()+1);

      let theDayAfterTomorrowDate = new Date(formattedDate);
      theDayAfterTomorrowDate.setDate(theDayAfterTomorrowDate.getDate()+2);

      let tomorrow = tomorrowDate.toISOString().slice(0, 10).replace(/-/g, "");
      let theDayAfterTomorrow = theDayAfterTomorrowDate.toISOString().slice(0, 10).replace(/-/g, "");
      console.log(tomorrow);
      console.log(typeof tomorrow);
      console.log(theDayAfterTomorrow);
      console.log(typeof theDayAfterTomorrow);*/

      for(let i=1; i<3; i++){

        let stringToDate = new Date(formattedDate);
        stringToDate.setDate(stringToDate.getDate()+i);
        let DateToString = stringToDate.toISOString().slice(0, 10).replace(/-/g, "");

        let temperatures = [];
       

        apiData.tmp.forEach(k => {
          if(k.date == DateToString){
              temperatures.push(parseInt(k.value));
          }
        })
        console.log(temperatures);



        let maxTemperatureOfDay = temperatures[0];
        let minTemperatureOfDay = temperatures[0];

        for(let i=0; i<temperatures.length; i++){
          if(temperatures[i]>maxTemperatureOfDay){
            maxTemperatureOfDay = temperatures[i];

          }
        }
        for(let i=0; i<temperatures.length; i++){
          if(temperatures[i]<minTemperatureOfDay){
            minTemperatureOfDay = temperatures[i];
          }
        }

        console.log(maxTemperatureOfDay);
        console.log(minTemperatureOfDay);







      }










//기온
      fetch(apiLong1)
          .then((response) => response.json())
          .then((json) => {
            let data2 = json.response.body.items.item;
            console.log(json);

            for (let k = 1; k < 7; k++) {
              const daysListId = `daysList${k}`;

              $(".daysWeather > ul").append(
                  `<li class='${daysListId}'><h4>${nowDay(k)} 일</h4></li>`
              );

              for (let d = 1; d < 3; d++) {
                $(`.${daysListId}`).append(`<div class="${halfTime(d)}">`);
              }
              $(`.${daysListId} > .am`).append(`<h4>오전</h4>`);
              $(`.${daysListId} > .pm`).append(`<h4>오후</h4>`);
              $(`.${daysListId} > div`).append(`<div class="daysListIcon"></div>`);
            }
            //날씨
      fetch(apiLong2)
          .then((response) => response.json())
          .then((json) => {
            console.log(json);
          })
          })
          .catch((error2) => console.log(error2));
    })
    .catch((error) => console.log(error));