
       function createChart() {
            $("#chart").kendoChart({
                title: {
                    text: "Electricity Consumption Thrends (kWh)"
                },
                legend: {
                    visible: true,
                    position: 'bottom'
                },
                seriesDefaults: {
                    type: "column",
                    stack: true,
                    labels: {
                        visible: true,
                        background: "transparent"
                    }
                },
                series: [{
                    name: "Peak",
                    data: [33, 34, 83, 36, 37, 44],
                    color: "#b8b8b8"
                }, {
                    name: "Non-Peak",
                    data: [34, 32, 24, 40, 38, 29],
                    color: "#1f9cde"
                }],
                valueAxis: {
                    max: 140,
                    line: {
                        visible: false
                    },
                    minorGridLines: {
                        visible: true
                    }
                },
                categoryAxis: {
                    categories: ['Apr-17', 'May-17', 'Jul-17', 'Aug-17', 'Sep-17', 'Oct-17'],
                    majorGridLines: {
                        visible: false
                    }
                },
                tooltip: {
                    visible: true,
                    template: "#= series.name #: #= value #"
                }
            });
        }

        $(document).ready(createChart);
        $(document).bind("kendo:skinChange", createChart);