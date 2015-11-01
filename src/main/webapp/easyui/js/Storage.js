var Storage;
(function(_CONSTANTS) {
    var _URL = (function () {
        var MODE_DYNAMIC = 1;
        var MODE_STATIC = 2;

        var MODE = MODE_DYNAMIC;

        var PREFIX = "/";
        var SUFFIX = ".action";
        if (MODE == MODE_STATIC) {
            PREFIX = "/json/";
            SUFFIX = ".json";
        }

        function URL() {
        }

        URL.LOGIN_USER = PREFIX + "loginUser" + SUFFIX;
        URL.LOAD_MAVIGATOR = PREFIX + "loadNavigator" + SUFFIX;
        URL.QUERY_DEPARTMENT_TREEGRID = PREFIX + "queryDepartmentTreeGrid" + SUFFIX;
        URL.QUERY_DEPARTMENT_SIMPLETREE = PREFIX + "queryDepartmentSimpleTree" + SUFFIX;
        URL.LOAD_DEPARTMENT = PREFIX + "loadDepartment" + SUFFIX;
        URL.SAVE_DEPARTMENT = PREFIX + "saveDepartment" + SUFFIX;

        URL.QUERY_EMPLOYEE_DATAGRID = PREFIX + "queryEmployeeDataGrid" + SUFFIX;
        URL.LOAD_EMPLOYEE = PREFIX + "loadEmployee" + SUFFIX;
        URL.SAVE_EMPLOYEE = PREFIX + "saveEmployee" + SUFFIX;

        URL.QUERY_APPLICATION_STATISTICS = PREFIX + "queryApplicationStatistics" + SUFFIX;

        URL.LIST_CODE_COMBOGRID = PREFIX + "listCodeByType" + SUFFIX;
        URL.QUERY_CODE_DATAGRID = PREFIX + "queryCodeDataGrid" + SUFFIX;

        return URL;
    })();

    var _OPERATION_MODE = (function () {
        function OPERATION_MODE() {
        }

        OPERATION_MODE.ADD = 1;
        OPERATION_MODE.EDIT = 2;

        return OPERATION_MODE;
    })();

    var _CODE_TYPE = (function () {
        function CODE_TYPE() {
        }

        CODE_TYPE.JOB_TITLE = "JobTitle";

        return CODE_TYPE;
    })();

    _CONSTANTS.URL = _URL;
    _CONSTANTS.OPERATION_MODE = _OPERATION_MODE;
    _CONSTANTS.CODE_TYPE = _CODE_TYPE;
    _CONSTANTS.INVALID_ID = "";
})(CONSTANTS || (CONSTANTS = {}));

