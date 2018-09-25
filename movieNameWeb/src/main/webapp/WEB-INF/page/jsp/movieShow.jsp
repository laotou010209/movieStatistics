
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电影票房统计</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/sources/js/echarts.min.js"></script>
    <!-- 引入jquery.js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/sources/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript">
            function loadWin() {
                //2秒自动刷新一次,2秒取得一次数据.
               setInterval("window.location.reload()",50000);
            }
    </script>

</head>
<body onload="loadWin();">
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="pie" style="width: 600px;height:400px;float: right;margin-top: -400px"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: '电影票房统计'
        },
        tooltip: {},
        legend: {
            data:['电影名']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        xAxis: {
            data: [],
            axisLabel: {
                interval:0,
                rotate:30
            }
        },
        yAxis: {},
        series: [{
            name: '电影名',
            type: 'bar',
            data: []
        }]
    });
    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var names=[];    //类别数组（实际用来盛放X轴坐标值）
    var nums=[];    //销量数组（实际用来盛放Y坐标值）
    $.post(
        "${pageContext.request.contextPath}/movieName/findName",
        {        },
        function(list){
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                    for(var i=0;i<list.length;i++){
                        names.push(list[i].movieName);    //挨个取出类别并填入类别数组
                        nums.push(list[i].ticketPrice);    //挨个取出销量并填入销量数组
                    }
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            data: names
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '电影名',
                            data: nums
                        }]
                    });

        }
    );
</script>
<script type="text/javascript">
    var myChart1 = echarts.init(document.getElementById('pie'));

    //myChart1.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var area=[];    //销量数组（实际用来盛放Y坐标值）
    var areaName=[];
    $.post(
        "${pageContext.request.contextPath}/movieName/findArea",
        {        },
        function(movieList){
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            for(var i=0;i<movieList.length;i++){
                movieList[i].name=movieList[i].areaName;
                movieList[i].value=movieList[i].ticketPrice;
                areaName.push(movieList[i].areaName);
            }
            area=movieList;
            console.log(area);
            myChart1.setOption({
                title : {
                    text: '地区名票房统计',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: areaName
                },
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data: area,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }
    );
</script>



</body>
</html>
