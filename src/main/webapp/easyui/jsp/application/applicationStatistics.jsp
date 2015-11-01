<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String uuid = "applicationStatistics_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);
%>

<c:set var="panel" scope="page" value="panel_${uuid}"/>

<c:set var="hibernateStatisticsContainer" scope="page" value="hibernateStatisticsContainer_${uuid}"/>

<div id="${panel}" class="easyui-panel" data-options="fit:true,border:false">
    <a onclick="loadApplicationStatistics()" href="#">xxx</a>

    <div id="${hibernateStatisticsContainer}">

    </div>

    <script>
        $(function () {
            $("#${hibernateStatisticsContainer}").highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Hibernate Statistics'
                },

                xAxis: {
                    categories: ['Hit Count', 'Miss Count', 'Put Count']
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Count',
                        align: 'high'
                    },
                    labels: {
                        overflow: 'justify'
                    }
                },
                credits: {
                    enabled: false
                },
                series: [
                    {
                        name: 'Year 1800',
                        data: []
                    }
                ]
            });

            loadApplicationStatistics();
        })

        function loadApplicationStatistics() {

            $.getJSON(CONSTANTS.URL.QUERY_APPLICATION_STATISTICS, function(data) {
                if (!checkDataStatus(data)) {
                    return;
                }

                var hibernateStatistics = data.object.hibernateStatistics;
                if (hibernateStatistics) {
                    var cache = hibernateStatistics.cacheStatisticsMap["quality.cache.ehcache.org.hibernate.cache.internal.StandardQueryCache"];

                    var dataArray = new Array();
                    dataArray.push(cache.hitCount);
                    dataArray.push(cache.missCount);
                    dataArray.push(cache.putCount);
                    $("#${hibernateStatisticsContainer}").highcharts().series[0].setData(dataArray);
                }
            });
        }
    </script>
</div>