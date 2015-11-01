<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String uuid = "department_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);
%>

<c:set var="panel" scope="page" value="panel_${uuid}"/>

<c:set var="datagrid" scope="page" value="datagrid_${uuid}"/>

<c:set var="toolbar" scope="page" value="toolbar_${uuid}"/>
<c:set var="addButton" scope="page" value="addButton_${uuid}"/>
<c:set var="editButton" scope="page" value="editButton_${uuid}"/>
<c:set var="deleteButton" scope="page" value="deleteButton_${uuid}"/>
<c:set var="showFilterButton" scope="page" value="showFilterButton_${uuid}"/>

<c:set var="filterTable" scope="page" value="filterTable_${uuid}"/>
<c:set var="filterForm" scope="page" value="filterForm_${uuid}"/>
<c:set var="departmentName" scope="page" value="departmentName_${uuid}"/>
<c:set var="applyFilterButton" scope="page" value="applyFilterButton_${uuid}"/>

<c:set var="editDepartmentDialog" scope="page" value="editDepartmentDialog_${uuid}"/>

<div id="${panel}" class="easyui-panel" data-options="fit:true,border:false">

    <table id="${datagrid}" class="easyui-datagrid" data-options="fit:true,border:false,toolbar:'#${toolbar}'">
    </table>

    <div id="${toolbar}">
        <table style="width:100%">
            <tr>
                <td>
                    <a id="${addButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" onclick="editDepartment_${uuid}(true);">Add</a>
                </td>
                <td>
                    <div class="datagrid-btn-separator"></div>
                </td>
                <td>
                    <a id="${editButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'" onclick="editDepartment_${uuid}(false);">Edit</a>
                </td>
                <td>
                    <div class="datagrid-btn-separator"></div>
                </td>
                <td>
                    <a id="${deleteButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'">Remove</a>
                </td>
                <td width="100%" align="right">
                    <a id="${showFilterButton}" href="#" class="easyui-linkbutton" data-options="plain:true, toggle:true, iconCls:'icon-search', iconAlign:'right'" style="border-radius:0px;">Show Filter</a>
                </td>
            </tr>
        </table>
        <table id="${filterTable}" style="width:100%;display:none;" cellpadding="10" cellspacing="0">
            <form id="${filterForm}" name="${filterForm}" method="post">
                <tr style="background-color:white;height:40px;">
                    <td nowrap>Dpt Name:</td>
                    <td nowrap>
                        <input id="${departmentName}" name="departmentName" class="easyui-validatebox" type="text" data-options="validType:length[0,20]"/>
                    </td>
                    <td width="100%" align="right">
                        <a id="${applyFilterButton}" href="#" class="easyui-linkbutton" data-options="plain:false, iconCls:'icon-ok'" style="border-radius:0px;">Apply</a>
                    </td>
                </tr>
            </form>
        </table>
    </div>
    <jsp:include page="/jsp/organization/editDepartment.jsp"/>


    <script>
        $(function () {
            $('#${showFilterButton}').bind('click', function () {
                var selectedButton = !( $('#${showFilterButton}').linkbutton('options').selected  );
                <%--var currentDataGridViewHeight  = $('#${panel}').find(".datagrid-view").height();--%>
                <%--var datagrid-body--%>
                if (selectedButton) {
                    $('#${filterTable}').show();
                    $('#${datagrid}').datagrid().resize();
                } else {
                    $('#${filterTable}').hide();
                    $('#${datagrid}').datagrid().resize();
                }
            });

            $('#${applyFilterButton}').bind('click', function () {
                var data = {departmentName: $("#${departmentName}").val()};

                $("#${datagrid}").datagrid('load', data);
            });

            $("#${datagrid}").datagrid({
                url: CONSTANTS.URL.LIST_DEPARTMENT,
                autoRowHeight: false,
                rownumbers: true,
                singleSelect: false,
                sortName: "name",
                sortOrder: "asc",
                frozenColumns: [
                    [
                        {field: 'ck', checkbox: true},
                        {field: 'name', title: 'Name', width: 150, sortable: true}
                    ]
                ],
                columns: [
                    [
                        {field: 'manager', title: 'Manager', width: 150}  ,
                        {field: 'employeeNum', title: 'Employee Num', width: 150, align: 'right'}
                    ]
                ],
                rowStyler: function (value, row, index) {
                    return "<span title='" + value + "'>" + value + "</span>";
                },
                loadFilter: function (data) {
                    if (!checkDataStatus(data)) {
                        return;
                    }
                    var departmentArray = data.object;
                    return departmentArray;
                },
                pageSize: 50,
                pageList: [50, 100, 200, 500],

                showFooter: true,
                pagination: true
            });
        })

        function editDepartment_${uuid}(addFlag) {
            var selectedDepartmentId = CONSTANTS.INVALID_ID;
            var selectedDepartmentName = "";

            var title = "";
            if (addFlag) {
            } else {
                var checkedRows = $('#${datagrid}').datagrid('getChecked');
                if (checkedRows.length == 0) {
                    $.messager.alert('Error', 'Please select one row to edit.');
                    return;
                }
                if (checkedRows.length > 1) {
                    $.messager.alert('Error', 'Please select only one row to edit.');
                    return;
                }

                selectedDepartmentId = checkedRows[0].id;
                selectedDepartmentName = checkedRows[0].name;
            }

            showDepartmentDialog(selectedDepartmentId, selectedDepartmentName, function () {
                $("#${datagrid}").datagrid("reload");
            });
        }
    </script>
</div>