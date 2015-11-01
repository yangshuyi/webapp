<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String uuid = "department_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);
%>

<c:set var="dialog" scope="page" value="dialog_${uuid}"/>
<c:set var="form" scope="page" value="form_${uuid}"/>
<c:set var="departmentIdRow" scope="page" value="departmentIdRow_${uuid}"/>
<c:set var="parentIdRow" scope="page" value="parentRow_${uuid}"/>
<c:set var="dialogButton" scope="page" value="dialogButton_${uuid}"/>

<div id="${dialog}" align="center">
<form id="${form}" name="form" method="post">
    <table>
        <tr>
            <td>
                <div id="${departmentIdRow}">
                    <div>ID:<span class="required">*</span></div>
                    <input id="id" name="id" class="easyui-validatebox" type="text" readonly="true"/>
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
                    <input id="createdDate" name="createdDate" class="easyui-datetimebox" readonly="true"/>
                </div>

                <div>
                    <div>Modified Date:</div>
                    <input id="modifiedDate" name="modifiedDate" class="easyui-datetimebox" readonly="true"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div id="${parentIdRow}">
                    <div>Parent Department:<span class="required">*</span></div>
                    <input id="parentId" name="parentId" class="easyui-combotree" required="true"/>
                </div>

                <div>
                    <div>Manager:</div>
                    <input id="managerId" name="managerId" class="easyui-combogrid"/>
                </div>
            </td>
        </tr>
        <tr>

            <td>
                <div>
                    <div>Description:</div>
                    <textarea id="description" name="description" class="easyui-validatebox" validType="length[0,200]"/>
                </div>
            </td>
        </tr>

    </table>
</form>
<script>
    //public
    function addDepartment(parentDepartment, onCloseCallback) {
        if (parentDepartment == null || parentDepartment.id == null || parentDepartment.name == null) {
            alert("parent department is null");
            return;
        }

        showDepartmentDialog(CONSTANTS.OPERATION_MODE.ADD, parentDepartment, onCloseCallback);
    }

    //public
    function editDepartment(currentDepartment, onCloseCallback) {
        if (currentDepartment == null || currentDepartment.id == null || currentDepartment.name == null) {
            alert("current department is null");
            return;
        }

        showDepartmentDialog(CONSTANTS.OPERATION_MODE.EDIT, currentDepartment, onCloseCallback);
    }

    //private
    function showDepartmentDialog(operationMode, departmentObj, onCloseCallback) {

        switch (operationMode) {
            case CONSTANTS.OPERATION_MODE.ADD:
                var title = "Add Department";
                var url = CONSTANTS.URL.LOAD_DEPARTMENT + "?parentId=" + departmentObj.id;
                break;
            case CONSTANTS.OPERATION_MODE.EDIT:
                var title = "Edit Department [" + departmentObj.name + "]";
                var url = CONSTANTS.URL.LOAD_DEPARTMENT + "?id=" + departmentObj.id;
                break;
            default:
        }

        $("#${dialog}").find("#parentId").combotree({
            checkbox: false,
            lines: true
        });

        $("#${dialog}").find("#managerId").combogrid({
            idField: 'id',
            textField: 'name',
            panelWidth: '500',
            columns: [
                [
                    {field: 'name', title: 'Name', width: 200},
                    {field: 'code', title: 'Code', width: 100},
                    {field: 'jobTitle', title: 'Job Title', width: 100, formatter: function (jobTitle, rec, index) {
                        return jobTitle != null ? jobTitle.name : "";
                    }}
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
                        saveDepartment();
                    }
                },
                {
                    text: 'Close',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        cancelEditDepartment();
                    }
                }
            ],
            onOpen: function () {
                $.get(url, {}, function (data, textStatus) {
                    if (!checkDataStatus(data)) {
                        return;
                    }
                    var department = data.object.department;
                    var departmentTreeObj = data.object.departmentTreeObj;
                    var employees = data.object.employees;

                    renderDepartmentForm(operationMode, department, departmentTreeObj, employees);
                }, "json");
            },
            onClose: function () {
                onCloseCallback();
            }
        });

        $("#${dialog}").dialog('open');
    }

    function renderDepartmentForm(operationMode, department, departmentTreeObj, employees) {
        $("#${dialog}").find("td > div").css("float", "left").css("display", "inline").css("margin", "10px");

        switch (operationMode) {
            case CONSTANTS.OPERATION_MODE.ADD:
                $("#${dialog}").find("#${departmentIdRow}").hide();
                $("#${dialog}").find("#id").val("");
                break;
            case CONSTANTS.OPERATION_MODE.EDIT:
                $("#${dialog}").find("#${departmentIdRow}").show();
                $("#${dialog}").find("#id").val(department.id);
                break;
            default:
        }

        //Parent Department
        if (department.parent) {
            $("#${dialog}").find("#${parentIdRow}").show();
            $("#${dialog}").find("#parentId").combotree({required: true});
        } else {
            $("#${dialog}").find("#${parentIdRow}").hide();
            $("#${dialog}").find("#parentId").combotree({required: false});
        }
        $("#${dialog}").find("#parentId").combotree("loadData", [departmentTreeObj]);

        // Manager
        $("#${dialog}").find("#managerId").combogrid("grid").datagrid("loadData", employees);

        $("#${dialog}").find("#name").val(department.name);
        $("#${dialog}").find("#code").val(department.code);
        $("#${dialog}").find("#createdDate").datetimebox('setValue', department.createdDate);
        $("#${dialog}").find("#modifiedDate").datetimebox('setValue', department.modifiedDate);
        $("#${dialog}").find("#parentId").combotree("setValue", department.parentId);
        $("#${dialog}").find("#managerId").combogrid("setValue", department.manager ? department.manager.id : null);
        $("#${dialog}").find("#description").val(department.description);
    }

    function saveDepartment() {

        $("#${form}").form('submit', {
            url: CONSTANTS.URL.SAVE_DEPARTMENT,

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

    function cancelEditDepartment() {
        $("#${dialog}").dialog().dialog('close');
    }

</script>
</div>

