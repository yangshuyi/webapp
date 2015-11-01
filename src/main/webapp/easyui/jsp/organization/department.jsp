<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String uuid = "department_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);
%>

<c:set var="panel" scope="page" value="panel_${uuid}"/>

<c:set var="treegrid" scope="page" value="treegrid_${uuid}"/>

<c:set var="toolbar" scope="page" value="toolbar_${uuid}"/>
<c:set var="addButton" scope="page" value="addButton_${uuid}"/>
<c:set var="editButton" scope="page" value="editButton_${uuid}"/>
<c:set var="deleteButton" scope="page" value="deleteButton_${uuid}"/>

<c:set var="departmentName" scope="page" value="departmentName_${uuid}"/>
<c:set var="applyFilterButton" scope="page" value="applyFilterButton_${uuid}"/>

<c:set var="editDepartmentDialog" scope="page" value="editDepartmentDialog_${uuid}"/>

<div id="${panel}" class="easyui-panel" data-options="fit:true,border:false">

    <table id="${treegrid}" class="easyui-treegrid" data-options="fit:true,border:false,toolbar:'#${toolbar}'">
    </table>

    <div id="${toolbar}">
        <table style="width:100%">
            <tr>
                <td>
                    <a id="${refreshButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-reload'" onclick="loadDepartmentTreeGrid();">Refresh</a>
                </td>

                <td>
                    <a id="${addButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" onclick="editDepartment_${uuid}(CONSTANTS.OPERATION_MODE.ADD);">Add</a>
                </td>

                <td>
                    <a id="${editButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'" onclick="editDepartment_${uuid}(CONSTANTS.OPERATION_MODE.EDIT);">Edit</a>
                </td>

                <td>
                    <a id="${deleteButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'" onclick="delDepartment_${uuid}();">Remove</a>
                </td>
                <td width="100%" align="right">
                </td>
            </tr>
        </table>
    </div>
    <jsp:include page="/jsp/organization/editDepartment.jsp"/>
    <jsp:include page="/jsp/user/loginUser.jsp"/>

    <script>
        $(function () {
            $("#${treegrid}").treegrid({
                idField:'id',
                treeField:'name',
                singleSelect:true,
                rownumbers:false,
                remoteSort: false,
                sortName: "code",
                sortOrder: "asc",
                columns:[
                    [
                        {field:'name',title:'Name',width:200},
                        {field:'code',title:'Code',width:100},
                        {field:'manager',title:'Manager Name',width:150, formatter:function(manager, rec, index) {
                            return manager != null ? manager.name : "";
                        }},
                        {field:'employeeNum',title:'Employee Num',width:150,align:'right'}
                    ]
                ]
            });

            loadDepartmentTreeGrid();
        })


        function loadDepartmentTreeGrid() {

            $.getJSON(CONSTANTS.URL.QUERY_DEPARTMENT_TREEGRID, function(data) {
                if (!checkDataStatus(data)) {
                    return;
                }
                var departmentTreeObj = data.object.departmentTreeObj;
                if (departmentTreeObj) {
                    $("#${treegrid}").treegrid("loadData", [departmentTreeObj]);
                }
            });
        }

        function editDepartment_${uuid}(operationMode) {
            var selectedDepartment = $('#${treegrid}').treegrid('getSelected');
            var errorMsg = "";
            switch (operationMode) {
                case CONSTANTS.OPERATION_MODE.ADD:
                    if (selectedDepartment == null) {
                        errorMsg += "Please select the department which new department will belong to";
                    }
                    break;
                case CONSTANTS.OPERATION_MODE.EDIT:
                    if (selectedDepartment == null) {
                        errorMsg += "Please select the department that you want to edit";
                    }
                    break;
                default:
            }

            if (errorMsg) {
                $.messager.alert('Error', errorMsg);
                return;
            }

            switch (operationMode) {
                case CONSTANTS.OPERATION_MODE.ADD:
                    addDepartment(selectedDepartment, function () {
                        loadDepartmentTreeGrid();
                    });
                    break;
                case CONSTANTS.OPERATION_MODE.EDIT:
                    editDepartment(selectedDepartment, function () {
                        loadDepartmentTreeGrid();
                    })
                    break;
                default:
            }
        }
    </script>
</div>