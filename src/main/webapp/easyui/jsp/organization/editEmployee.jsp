<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String uuid = "editEmployee_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);
%>

<c:set var="dialog" scope="page" value="dialog_${uuid}"/>
<c:set var="form" scope="page" value="form_${uuid}"/>
<c:set var="employeeIdRow" scope="page" value="employeeIdRow_${uuid}"/>
<c:set var="dialogButton" scope="page" value="dialogButton_${uuid}"/>

<div id="${dialog}" align="center">
    <form id="${form}" name="form" method="post">
        <table style="margin-top:10px;">

            <tr>
                <td>
                    <div id="${employeeIdRow}">
                        <div>ID:<span class="required">*</span></div>
                        <input id="id" name="id" class="easyui-validatebox" type="text" disabled="true"/>
                    </div>

                    <div>
                        <div>Name:<span class="required">*</span></div>
                        <input id="name" name="name" class="easyui-validatebox" required="true" validType="length[0,20]"/>
                    </div>

                    <div>
                        <div>Code:<span class="required">*</span></div>
                        <input id="code" name="code" class="easyui-validatebox" required="true" validType="length[0,20]"/>
                    </div>

                    <div>
                        <div>Create Date:</div>
                        <input id="createdDate" name="createdDate" class="easyui-datetimebox" disabled="true"/>
                    </div>

                    <div>
                        <div>Modified Date:</div>
                        <input id="modifiedDate" name="modifiedDate" class="easyui-datetimebox" disabled="true"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <div>Department:<span class="required">*</span></div>
                        <input id="departmentId" name="departmentId" class="easyui-combotree" required="true"/>
                    </div>

                    <div>
                        <div>Job Title:<span class="required">*</span></div>
                        <input id="jobTitleId" name="jobTitleId" class="easyui-combogrid"/>
                    </div>

                    <div>
                        <div>&nbsp;</div>
                        <table cellpadding="0" cellspacing="0">
                            <tr>
                                <td><input id="disabled" name="disabled" type="checkbox" class="easyui-validatebox" style="width:20px;margin:0 10 0 0px;"/></td>
                                <td>Disabled</td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>

                <td>
                    <div>
                        <div>Email Address:</div>
                        <input id="emailAddress" name="emailAddress" class="easyui-validatebox" validType="email,length[0,20]"/>
                    </div>

                    <div>
                        <div>Mobile Number:</div>
                        <input id="mobileNumber" name="mobileNumber" class="easyui-validatebox" validType="length[0,20]"/>
                    </div>
                </td>
            </tr>

        </table>
    </form>
</div>
<script>
    //public
    function addEmployee(department, onCloseCallback) {
        if (department == null || department.id == null) {
            showEmployeeDialog(CONSTANTS.OPERATION_MODE.ADD, null, null, onCloseCallback);
        } else {
            showEmployeeDialog(CONSTANTS.OPERATION_MODE.ADD, null, department, onCloseCallback);
        }

    }

    //public
    function editEmployee(employee, onCloseCallback) {
        if (employee == null || employee.id == null || employee.name == null) {
            alert("current Employee is null");
            return;
        }

        showEmployeeDialog(CONSTANTS.OPERATION_MODE.EDIT, employee, null, onCloseCallback);
    }

    //private
    function showEmployeeDialog(operationMode, employeeObj, departmentObj, onCloseCallback) {
        switch (operationMode) {
            case CONSTANTS.OPERATION_MODE.ADD:
                var title = "Add Employee";
                if (departmentObj) {
                    var url = CONSTANTS.URL.LOAD_EMPLOYEE + "?departmentId=" + departmentObj.id;
                } else {
                    var url = CONSTANTS.URL.LOAD_EMPLOYEE;
                }
                break;
            case CONSTANTS.OPERATION_MODE.EDIT:
                var title = "Edit Employee [" + employeeObj.name + "]";
                var url = CONSTANTS.URL.LOAD_EMPLOYEE + "?id=" + employeeObj.id;
                break;
            default:
        }

        $("#${dialog}").find("#departmentId").combotree({
            checkbox: false,
            lines: true,
            panelWidth: '200'
        });

        $("#${dialog}").find("#jobTitleId").combogrid({
            idField: 'id',
            textField: 'name',
            panelWidth: '500',
            editable: false,
            columns: [
                [
                    {field: 'name', title: 'Name', width: 200},
                    {field: 'code', title: 'Code', width: 100},
                    {field: 'description', title: 'Description', width: 200}
                ]
            ]
        });

        $("#${dialog}").dialog({
            title: title,
            width: 1000,
            height: 300,
            closed: true,
            cache: false,
            modal: true,
            buttons: [
                {
                    text: 'Save',
                    iconCls: 'icon-save',
                    handler: function () {
                        saveEmployee();
                    }
                },
                {
                    text: 'Close',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        cancelEditEmployee();
                    }
                }
            ],
            onOpen: function () {
                $.get(url, {}, function (data, textStatus) {
                    if (!checkDataStatus(data)) {
                        return;
                    }
                    var employee = data.object.employee;
                    var departmentTreeObj = data.object.departmentTreeObj;
                    var jobTitles = data.object.jobTitles;

                    renderEmployeeForm(operationMode, employee, departmentTreeObj, jobTitles);
                }, "json");
            },
            onClose: function () {
                onCloseCallback();
            }
        });

        $("#${dialog}").dialog('open');
    }

    function renderEmployeeForm(operationMode, employee, departmentTreeObj, jobTitles) {
        $("#${dialog}").find("td > div").css("float", "left").css("display", "inline").css("margin", "10px");

        switch (operationMode) {
            case CONSTANTS.OPERATION_MODE.ADD:
                $("#${dialog}").find("#${employeeIdRow}").hide();
                $("#${dialog}").find("#id").val("");
                break;
            case CONSTANTS.OPERATION_MODE.EDIT:
                $("#${dialog}").find("#${employeeIdRow}").show();
                $("#${dialog}").find("#id").val(employee.id);
                break;
            default:
        }

        // Department
        $("#${dialog}").find("#departmentId").combotree("loadData", [departmentTreeObj]);

        // Job Title
        $("#${dialog}").find("#jobTitleId").combogrid("grid").datagrid("loadData", jobTitles);

        $("#${dialog}").find("#name").val(employee.name);
        $("#${dialog}").find("#code").val(employee.code);
        $("#${dialog}").find("#createdDate").datetimebox('setValue', employee.createdDate);
        $("#${dialog}").find("#modifiedDate").datetimebox('setValue', employee.modifiedDate);
        $("#${dialog}").find("#departmentId").combotree("setValue", employee.department.id);
        $("#${dialog}").find("#jobTitleId").combogrid("setValue", employee.jobTitle ? employee.jobTitle.id : null);
        $("#${dialog}").find("#mailAddress").val(employee.mailAddress);
        $("#${dialog}").find("#mobileNumber").val(employee.mobileNumber);
    }

    function saveEmployee() {
        $("#${form}").form('submit', {
            url: CONSTANTS.URL.SAVE_EMPLOYEE,

            onSubmit: function () {
                var isValid = $(this).form('validate');

                return isValid;
            },
            success: function (data) {
                try {
                    data = eval('(' + data + ')');
                } catch (ex) {
                    alert(data);
                }

                if (checkDataStatus(data)) {
                    $.messager.alert('Info', data.message);
                    $("#${dialog}").dialog('close');
                }
            }
        });
    }

    function cancelEditEmployee() {
        $("#${dialog}").dialog().dialog('close');
    }

</script>


