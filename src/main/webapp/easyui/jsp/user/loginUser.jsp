<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="userLoginDialog" align="center">
    <form id="userLoginForm" name="userLoginForm" method="post">
        <table width="100%" height="100%">
            <tr>
                <td align="center" valign="middle">
                    <table>
                        <tr>
                            <td>Unit Code:</td>
                            <td>
                                <input class="easyui-validatebox" type="text" name="unitCode" placeholder="Type unit code" required="true" validType="length[0,4]"/>
                            </td>
                        </tr>
                        <tr>
                            <td>User Name:</td>
                            <td>
                                <input class="easyui-validatebox" type="text" name="name" placeholder="Type user name" required="true" validType="length[0,20]"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td>
                                <input class="easyui-validatebox" type="password" name="password" placeholder="Type password" required="true" validType="length[0,20]"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
    $(function () {
    })

    function showLoginUserDialog(onLoginSuccessCallback) {
        $("#userLoginDialog").dialog({
            title: "Welcome, please login",
            width: 400,
            height: 200,
            closable: false,
            cache: false,
            modal: true,
            buttons: [
                {
                    text: 'Login',
                    iconCls: 'icon-save',
                    handler: function () {
                        loginUser(onLoginSuccessCallback);
                    }
                },
                {
                    text: 'Clear',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        clearForm();
                    }
                }
            ],
            onOpen: function () {
                var storage = window.localStorage;
                if (storage) {
                    var unitCode = storage.getItem(CONSTANTS.CACHE_KEY.USER_UNIT_CODE);
                    var name = storage.getItem(CONSTANTS.CACHE_KEY.USER_NAME);

                    if (unitCode) {
                        $("#userLoginForm input[name=unitCode]").val(unitCode);
                    }
                    if (name) {
                        $("#userLoginForm input[name=name]").val(name);
                    }
                }
            }
        });

        $("userLoginDialog").dialog('open');
    }


    function loginUser(onLoginSuccessCallback) {
        $('#userLoginDialog form').form('submit', {
            url: CONSTANTS.URL.LOGIN_USER,

            onSubmit: function () {
                var isValid = $(this).form('validate');

                return isValid;
            },
            success: function (data) {
                try {
                    var data = eval('(' + data + ')');

                    var status = data.status;
                    var message = data.message;
                    var suggestion = data.suggestion;

                    if (status == null || status == -1) {
                        $.messager.alert('error', message, suggestion);
                        return false;
                    }

                    $("userLoginDialog").dialog('close');

                    var user = data.object.user;

                    var storage = window.localStorage;
                    if (storage) {
                        var unitCode = storage.setItem(CONSTANTS.CACHE_KEY.USER_UNIT_CODE, user.unit.code);
                        var name = storage.setItem(CONSTANTS.CACHE_KEY.USER_NAME, user.name);
                    }
                    onLoginSuccessCallback(user);
                } catch (ex) {
                    alert(data);
                }
            }
        });
    }

    function clearForm() {
        $("#index_user_login_form input").val("");
    }
</script>
