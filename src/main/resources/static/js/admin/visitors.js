const ctx = $("#myChart");
let myChart = new Chart(ctx, {
    type   : 'line',
    data   : {
        labels  : [],
        datasets: [
            {
                label      : '방문자 수 집계',
                data       : [],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor:'rgba(75, 192, 192, 1)',
                borderWidth: 2,
                fill: true,

            }
        ],

    },
    options: {
        plugins: {
          padding: 10,
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
})

$(document).ready(function() {
    updateChart($(".select").val());
});
$(".select").on("change",function (e) {
    updateChart(e.target.value);
});

function updateChart(values) {
    let value = {
        num : values
    }
    // Ajax를 사용하여 서버에서 데이터를 가져옴
    $.ajax({
        url: "/admin/visitors", // 서버의 엔드포인트 URL
        method: 'POST',
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(value),
        success: function(data) {
            myChart.data.labels = [];
            myChart.data.datasets[0].data = [];
            console.log(data)
            // 서버에서 받은 데이터를 차트에 추가
            for(let i = 0; i < data.length; i++){
                if(data[0].date == null){
                    let logDate = data[i].year + '-' + data[i].month;
                    console.log(logDate)
                    myChart.data.labels.push(logDate);
                    myChart.data.datasets[0].data.push(data[i].averageVisitors);
                    myChart.update();
                } else {
                    let logDate = data[i].date[0] + '-' + data[i].date[1] + '-' + data[i].date[2];
                    myChart.data.labels.push(logDate);
                    myChart.data.datasets[0].data.push(data[i].visitors);
                    myChart.update();
                }

            }
        },
        error: function(error) {
            console.error(error);
        }
    });
}