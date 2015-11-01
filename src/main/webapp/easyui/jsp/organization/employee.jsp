<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String uuid = "employee_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);
%>

<c:set var="panel" scope="page" value="panel_${uuid}"/>

<c:set var="depSubPanel" scope="page" value="depSubPanel_${uuid}"/>
<c:set var="depSubPanelTools" scope="page" value="depSubPanelTools_${uuid}"/>
<c:set var="depTree" scope="page" value="depTree_${uuid}"/>

<c:set var="employeeSubPanel" scope="page" value="employeeSubPanel_${uuid}"/>
<c:set var="employeeDatagrid" scope="page" value="employeeDatagrid_${uuid}"/>
<c:set var="toolbar" scope="page" value="toolbar_${uuid}"/>

<c:set var="showFilterButton" scope="page" value="showFilterButton_${uuid}"/>
<c:set var="filterTable" scope="page" value="filterTable_${uuid}"/>
<c:set var="filterForm" scope="page" value="filterForm_${uuid}"/>
<c:set var="departmentName" scope="page" value="departmentName_${uuid}"/>
<c:set var="applyFilterButton" scope="page" value="applyFilterButton_${uuid}"/>

<c:set var="editDepartmentDialog" scope="page" value="editDepartmentDialog_${uuid}"/>

<div id="${panel}" class="easyui-layout" data-options="fit:true, border:false">

    <div id="${depSubPanel}" data-options="region:'west', split:true, collapsible:false" style="width:200px;">
        <div class="easyui-panel" data-options="fit:true, border:false, tools:'#${depSubPanelTools}'" title="Department">
            <ul id="${depTree}"></ul>
        </div>

        <div id="${depSubPanelTools}">
            <a href="javascript:void(0)" class="icon-reload" onclick="loadDepartmentTree();"></a>
        </div>
    </div>

    <div id="${employeeSubPanel}" data-options="region:'center'">
        <table id="${employeeDatagrid}">
        </table>

        <div id="${toolbar}">
            <table style="width:100%">
                <tr>
                    <td>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" onclick="editEmployee_${uuid}(CONSTANTS.OPERATION_MODE.ADD);">Add</a>
                    </td>
                    <td>
                        <div class="datagrid-btn-separator"></div>
                    </td>
                    <td>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'" onclick="editEmployee_${uuid}(CONSTANTS.OPERATION_MODE.EDIT);">Edit</a>
                    </td>
                    <td>
                        <div class="datagrid-btn-separator"></div>
                    </td>
                    <td>
                        <a href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'">Remove</a>
                    </td>
                    <td width="100%" align="right">
                        <a id="${showFilterButton}" href="#" class="easyui-linkbutton" data-options="plain:true, toggle:true, iconCls:'icon-search', iconAlign:'right'" style="border-radius:0px;" onclick="toggleFilter_${uuid}();">Show Filter</a>
                    </td>
                </tr>
            </table>
            <form id="${filterForm}" name="${filterForm}" method="post">
                <input id="departmentIds" name="departmentIds" type="hidden" value=""/>
                <table id="${filterTable}" style="width:100%;display:none;" cellpadding="10" cellspacing="0">
                    <tr style="background-color:white;height:40px;">
                        <td>
                            <div>
                                <div>Employee Name:</div>
                                <input id="employeeName" name="employeeName" class="easyui-validatebox" type="text"/>
                            </div>

                            <div>
                                <div>Job Title:</div>
                                <input id="employeeJobTitles" name="employeeJobTitles" class="easyui-combogrid"/>
                            </div>

                            <div>
                                <div>Disabled:</div>
                                <select id="disabled" name="disabled" class="easyui-combobox">
                                    <option value="">Show All</option>
                                    <option value="true">Show Disabled</option>
                                    <option value="false">Show Enabled</option>
                                </select>
                            </div>
                        </td>
                        <td width="100px" align="right">
                            <a href="#" class="easyui-linkbutton" data-options="plain:false, iconCls:'icon-ok'" style="border-radius:0px;" onclick="loadEmployeeDataGrid();">Apply</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/jsp/organization/editEmployee.jsp"/>


<script>
    $(function () {
        $("#${filterTable}").find("td > div").css("float", "left").css("display", "inline").css("margin", "10px");

        $("#${depTree}").tree({
            fit: true,
            border: false,
            checkbox: true,
            cascadeCheck: false,
            lines: true,
            onCheck: function () {
                var ignoreCheckEventFlag = $("#${depTree}").tree('options').ignoreCheckEventFlag;
                if (!ignoreCheckEventFlag) {
                    loadEmployeeDataGrid();
                }
            }
        });

        $("#${filterTable}").find("#employeeJobTitles").combogrid({
            idField: 'id',
            textField: 'name',
            multiple: true,
            panelWidth: '500',
            columns: [
                [
                    {field: 'name', title: 'Name', width: 200},
                    {field: 'code', title: 'Code', width: 100},
                    {field: 'description', title: 'Description', width: 200}
                ]
            ]
        });


        $("#${employeeDatagrid}").datagrid({
            fit: true,
            border: false,
            toolbar: '#${toolbar}',
            autoRowHeight: false,
            rownumbers: true,
            singleSelect: false,
            sortName: "name",
            sortOrder: "asc",
            frozenColumns: [
                [
                    {field: 'ck', checkbox: true}
                ]
            ],
            columns: [
                [
                    {field: 'name', title: 'Name', width: 150, sortable: true},
                    {field: 'code', title: 'Code', width: 100},
                    {field: 'department', title: 'Department', width: 100, sortable: true, formatter:function(department, rec, index) {
                        return department != null ? department.name : "";
                    }},
                    {field: 'jobTitle', title: 'Job Title', width: 100, sortable: true, formatter:function(jobTitle, rec, index) {
                        return jobTitle != null ? jobTitle.name : "";
                    }},
                    {field: 'disabled', title: 'Disabled', width: 100, sortable: true},
                    {field: 'mailAddress', title: 'Mail Address', width: 300},
                    {field: 'mobileNumber', title: 'Mobile Number', width: 100}
                ]
            ],
            rowStyler: function (value, row, index) {
                return "<span title='" + value + "'>" + value + "</span>";
            },
            loadFilter: function (data) {
                if (!checkDataStatus(data)) {
                    return;
                }
                var employeeDataGrid = data.object.employeeDataGrid;
                return employeeDataGrid;
            },
            pageSize: 50,
            pageList: [50, 100, 200, 500],

            showFooter: true,
            pagination: true
        });

        loadJobTitleComboGrid();

        loadDepartmentTree();
    })

    function loadJobTitleComboGrid() {
        $.getJSON(CONSTANTS.URL.LIST_CODE_COMBOGRID + "?type=" + CONSTANTS.CODE_TYPE.JOB_TITLE, function (data) {
            if (!checkDataStatus(data)) {
                return;
            }

            var codeModelList = data.object.codeModelList;
            $("#${filterTable}").find("#employeeJobTitles").combogrid('grid').datagrid("loadData", codeModelList);
        });
    }


    function loadDepartmentTree() {
        $.getJSON(CONSTANTS.URL.QUERY_DEPARTMENT_SIMPLETREE, function (data) {
            if (!checkDataStatus(data)) {
                return;
            }
            var departmentTreeObj = data.object.departmentTreeObj;
            $("#${depTree}").tree("loadData", [departmentTreeObj]);

            $("#${depTree}").tree('options').ignoreCheckEventFlag = true;
            var nodes = $("#${depTree}").tree('getChecked', 'unchecked');
            for (var i = 0; i < nodes.length; i++) {
                $("#${depTree}").tree("check", nodes[i].target);
            }
            $("#${depTree}").tree('options').ignoreCheckEventFlag = false;

            loadEmployeeDataGrid();
        });
    }

    function loadEmployeeDataGrid() {
        var selectedNodes = $("#${depTree}").tree('getChecked');
        var departmentIds = new Array();
        for (var i = 0; i < selectedNodes.length; i++) {
            departmentIds.push(selectedNodes[i].id);
        }

        var employeeName = $("#employeeName").val();

        var jobTitleIds = $("#${filterTable}").find("#employeeJobTitles").combogrid("getValues");

        var disabled = $("#${filterTable}").find("#disabled").combogrid("getValue");

        var departmentIdsParam = {name: "department.id", alias: "departmentId", type: "String", operationType: "in", value: departmentIds};
        var employeeNameParam = {name: "name", alias: "name", type: "String", operationType: "like", value: employeeName};
        var jobTitlesParam = {name: "jobTitle.id", alias: "jobTitleId", type: "String", operationType: "in", value: jobTitleIds};
        var disabledParam = {name: "disabled", alias: "disabled", type: "Boolean", operationType: "eq", value: disabled};

        if (jobTitleIds.length <= 0) {
            jobTitlesParam.name = "";
        }

        if (disabled == "") {
            disabledParam.name = "";
        }

        var paramJSON = [departmentIdsParam, employeeNameParam, jobTitlesParam, disabledParam];
        $("#${employeeDatagrid}").datagrid('options').url = CONSTANTS.URL.QUERY_EMPLOYEE_DATAGRID + "?paramJSONStr=" + JSON.stringify(paramJSON);
        $("#${employeeDatagrid}").datagrid('load');
    }

    function editEmployee_${uuid}(operationMode) {
        var selectedEmployee = $('#${employeeDatagrid}').datagrid('getSelected');

        var errorMsg = "";
        switch (operationMode) {
            case CONSTANTS.OPERATION_MODE.EDIT:
                if (selectedEmployee == null) {
                    errorMsg += "Please select the emloyee that you want to edit";
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
                var selectedDepartment = $("#${depTree}").tree('getSelected');
                addEmployee(selectedDepartment, function () {
                    $("#${employeeDatagrid}").datagrid("reload");
                });
                break;
            case CONSTANTS.OPERATION_MODE.EDIT:
                editEmployee(selectedEmployee, function () {
                    $("#${employeeDatagrid}").datagrid("reload");
                })
                break;
            default:
        }
    }


    function toggleFilter_${uuid}() {

        var visible = $("#${showFilterButton}").linkbutton('options').selected;
        if (visible) {
            $('#${filterTable}').hide();
        } else {
            $('#${filterTable}').show();
        }
        $('#${employeeDatagrid}').datagrid("resize");
    }

</script>
