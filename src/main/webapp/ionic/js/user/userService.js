angular.module('userModule').factory('userService', ['$q',
    function ($q) {
        var validateMobileNo = function (loginModel) {
            if (!loginModel.mobileNo) {
                return "Please enter mobile no";
            }
            return;
        };

        var validatePassword = function (loginModel) {
            if (!loginModel.password) {
                return "Please enter password";
            }
            return;
        };

        return {
            validateMobileNo: validateMobileNo,
            validatePassword: validatePassword
        }
    }]);
