angular.module('userModule').factory('userService', ['$q',
    function ($q) {
        var defaultResult = {status: true, msg: '', data: null};

        var validateMobileNo = function (loginModel) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);
            if (!loginModel.mobileNo) {
                result.status = false;
                result.msg = "Please enter mobile no";
            }

            deferred.resolve(result);
            return promise;
        };

        var validatePassword = function (loginModel) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);
            if (!loginModel.password) {
                result.status = false;
                result.msg = "Please enter password";
            }

            deferred.resolve(result);
            return promise;
        };


        var validateLogin = function (loginModel) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);
            if (loginModel.mobileNo != loginModel.password) {
                result.status = false;
                result.msg = "用户信息有误";
            } else {
                result.data = {"userId": 1001, "userName": "superyang_xp", "mobileNo": loginModel.mobileNo};
            }

            deferred.resolve(result);
            return promise;
        };

        return {
            validateMobileNo: validateMobileNo,
            validatePassword: validatePassword,
            validateLogin: validateLogin
        }
    }]);
