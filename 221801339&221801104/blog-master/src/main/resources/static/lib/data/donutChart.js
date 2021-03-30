var data = [{ name: 'Shiny', id: 1, quantity: 86, percentage: 5 }, { name: 'Blazing', id: 2, quantity: 300, percentage: 18 }, { name: 'Dazzling', id: 3, quantity: 276, percentage: 16 }, { name: 'Radiant', id: 4, quantity: 195, percentage: 11 }, { name: 'Sparkling', id: 5, quantity: 36, percentage: 2 }, { name: 'Other', id: 0, quantity: 814, percentage: 48 }];
function createDonutChart() {
    var donutChart = britecharts.donut();

    donutChart.width(400).height(300);

    d3.select('.js-donut-container').datum(data).call(donutChart);
}