<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String uuid = "code_" + java.util.UUID.randomUUID().toString().replace("-", "");
    pageContext.setAttribute("uuid", uuid);

    String codeType = request.getParameter("codeType");
    pageContext.setAttribute("codeType", codeType);
%>

<c:set var="panel" scope="page" value="panel_${uuid}"/>

<c:set var="codeDatagrid" scope="page" value="codeDatagrid_${uuid}"/>
<c:set var="toolbar" scope="page" value="toolbar_${uuid}"/>

<c:set var="codeTypeInput" scope="page" value="codeTypeInput_${uuid}"/>

<c:set var="editDepartmentDialog" scope="page" value="editDepartmentDialog_${uuid}"/>

<div id="${panel}" class="easyui-panel" data-options="fit:true,border:false">
<input id="${codeTypeInput}" value="${codeType}" type="hidden"/>
<table id="${codeDatagrid}">
</table>

<div id="${toolbar}">
    <table style="width:100%">
        <tr>
            <td>
                <a id="${refreshButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-reload'" onclick="loadCodeDataGrid();">Refresh</a>
            </td>

            <td>
                <a id="${addButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-add'" onclick="editCode_${uuid}(CONSTANTS.OPERATION_MODE.ADD);">Add</a>
            </td>

            <td>
                <a id="${editButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'" onclick="editCode_${uuid}(CONSTANTS.OPERATION_MODE.EDIT);">Edit</a>
            </td>

            <td>
                <a id="${deleteButton}" href="#" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-remove'" onclick="delCode_${uuid}();">Remove</a>
            </td>
            <td width="100%" align="right">
            </td>
        </tr>
    </table>
</div>

<%--<jsp:include page="/jsp/code/editCode.jsp"/>--%>

<script>
    $(function () {
       $("#${codeDatagrid}").datagrid({
            fit: true,
            border: false,
            toolbar: '#${toolbar}',
            autoRowHeight: false,
            rownumbers: true,
            singleSelect: false,
            sortName: "code",
            sortOrder: "asc",
            frozenColumns: [
                [
                    {field: 'ck', checkbox: true}
                ]
            ],
            columns: [
                [
                    {field: 'name', title: 'Name', width: 150, sortable: true},
                    {field: 'code', title: 'Code', width: 100, sortable: true},
                    {field: 'description', title: 'Description', width: 200}
                ]
            ],
            loadFilter: function (data) {
                if (!checkDataStatus(data)) {
                    return;
                }
                var codeDataGrid = data.object.codeDataGrid;
                return codeDataGrid;
            },
            pageSize: 50,
            pageList: [50, 100, 200, 500],

            showFooter: true,
            pagination: true
        });

        loadCodeDataGrid();
    })

    function loadCodeDataGrid() {
        var codeType = $('#${codeTypeInput}').val();

        $("#${codeDatagrid}").datagrid('options').url = CONSTANTS.URL.QUERY_CODE_DATAGRID + "?type=" + codeType;
        $("#${codeDatagrid}").datagrid('load');
    }

    function editCode_${uuid}(operationMode) {
        var codeType = $('#${codeTypeInput}').val();

        var selectedCode = $('#${codeDatagrid}').datagrid('getSelected');

        var errorMsg = "";
        switch (operationMode) {
            case CONSTANTS.OPERATION_MODE.EDIT:
                if (selectedCode == null) {
                    errorMsg += "Please select the code that you want to edit";
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
                addCode(codeType, function () {
                    $("#${codeDatagrid}").datagrid("reload");
                });
                break;
            case CONSTANTS.OPERATION_MODE.EDIT:
                editCode(codeType, selectedCode, function () {
                    $("#${codeDatagrid}").datagrid("reload");
                })
                break;
            default:
        }
    }
</script>
</div>