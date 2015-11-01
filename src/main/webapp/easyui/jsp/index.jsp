<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="../js/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.3.3/locale/easyui-lang-en.js"></script>
    <script type="text/javascript" src="../js/Highcharts-3.0.9/highcharts.js"></script>
    <script type="text/javascript" src="../js/Constants.js"></script>

    <link rel="stylesheet" href="../js/jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css"/>
    <link rel="stylesheet" href="../js/jquery-easyui-1.3.3/themes/icon.css" type="text/css"/>
    <link rel="stylesheet" href="/style/default.css" type="text/css"/>

    <script>

        $(function () {

        });

        function serializeObject(form) {
            var o = {};
            $.each(form.serializeArray(), function(index) {
                if (o[this['name']]) {
                    o[this['name']] = o[this['name']] + "," + this['value'];
                } else {
                    o[this['name']] = this['value'];
                }
            });
            return o;
        }


        function checkDataStatus(data) {
            if (!data) {
                return;
            }

            var status = data.status;
            var message = data.message;
            var suggestion = data.suggestion;

            if (status==null || status == -1) {
                $.messager.alert('error',message, suggestion);
                return false;
            }
            return true;
        }

        function openModule(name, url) {
            var workspaceContent = $("#workspaceContent");
            if (workspaceContent.tabs("exists", name)) {
                workspaceContent.tabs("select", name);
            } else {
                workspaceContent.tabs("add", {
                    title: name,
                    href: url,
                    closable:true
                });
            }
        }

    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north', border:false" style="height:50px;"></div>
<div data-options="region:'west', split:true" title="Navigation" style="width:200px;">
    <jsp:include page="/jsp/user/navigator.jsp"/>
</div>
<div data-options="region:'center'">
    <div id="workspaceContent" class="easyui-tabs" data-options="fit:true, border:false">
        <jsp:include page="/jsp/user/welcome.jsp"/>
    </div>
</div>
</body>
</html>