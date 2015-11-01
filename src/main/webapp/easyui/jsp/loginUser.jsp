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
            showLoginUserDialog(onUserLoginCallback);
        })

        function onUserLoginCallback(user) {
//            $.messager.show({
//                title: 'Login Success!',
//                msg: ' Welcome, ' + user.name + ', now redirect to home page.',
//                timeout: 5000,
//                showType: 'slide'
//            });

            $.messager.progress({
                title: 'Login Success!',
                msg: ' Welcome, ' + user.name+'.',
                text: 'Redirect to home page.',
                interval: 500
            });

            setTimeout(function () {
                $.messager.progress('close');
                location.href = "/jsp/index.jsp";
            }, 5000);
        }
    </script>
</head>
<body>
<table width="100%" height="100%">
    <tr>
        <td align="center" valign="middle">
            <jsp:include page="/jsp/user/loginUser.jsp"/>
        </td>
    </tr>
</table>
</body>
</html>