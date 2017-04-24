<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/common/taglibs.jsp" %>
<div class="place">
    <ul class="placeul">
        <li><a href="#">我的首页</a></li>
    </ul>
</div>

<div style="padding: 10px;">
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main1" style="height:400px;width: 700px;"></div>
    <div id="main2" style="height:400px;width: 700px;"></div>
</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main1'));

    var option = {
        title: {
            text: '电量分析表',
            subtext: '12月各小区电量统计'
        },
        tooltip: {
            show: true
        },
        legend: {
            data: ['电量']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: [
            {
                type: 'category',
                data: ["小区", "小区", "小区", "小区", "小区", "小区", "小区", "小区", "小区"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                "name": "电量",
                "type": "bar",
                "data": [5, 20, 40, 10, 10, 20, 12, 11, 9],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);

    var myChart2 = echarts.init(document.getElementById('main2'));
    var option2 = {
        title: {
            text: '电量对比',
            subtext: '预期电量/实际电量'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['预期电量', '实际电量']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '预期电量',
                type: 'line',
                data: [20, 49, 70, 23, 25, 76, 13, 16, 32, 20, 64, 33],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '实际电量',
                type: 'line',
                data: [26, 59, 90, 26, 28, 70, 17, 18, 48, 18, 60, 23],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    myChart2.setOption(option2);

</script>
