angular.module('userModule').factory('userService', ['$q', '$ionicPopup', '$http', 'md5',
    function ($q, $ionicPopup, $http, md5) {
        var defaultResult = {status: true, msg: '', data: null};

        var validateMobileNo = function (loginModel) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);
            if (!loginModel.mobileNo) {
                result.status = false;
                result.msg = 'Please enter mobile no';

                $ionicPopup.alert({
                    title: 'Hint',
                    template: result.msg
                }).then(function(){
                    deferred.resolve(result);
                });
            }else{
                deferred.resolve(result);
            }
            return promise;
        };

        var validatePassword = function (loginModel) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);
            if (!loginModel.password) {
                result.status = false;
                result.msg = "Please enter password";

                $ionicPopup.alert({
                    title: 'Hint',
                    template: result.msg
                }).then(function(){
                    deferred.resolve(result);
                });
            }else{
                deferred.resolve(result);
            }
            return promise;
        };


        var validateLogin = function (loginModel) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);
            if (loginModel.mobileNo != loginModel.password) {
                result.status = false;
                result.msg = "用户信息有误";
                $ionicPopup.alert({
                    title: 'Hint',
                    template: result.msg
                }).then(function(){
                    deferred.resolve(result);
                });
            }else{
                deferred.resolve(result);
            }
            return promise;
        };

        var doLogin = function(loginModel){

            var deferred = $q.defer();
            var promise = deferred.promise;

            var result = _.clone(defaultResult);

            var url = '/user/login.do';
            var param = 'mobileNo='+loginModel.mobileNo+"&encyptPassword="+md5.md5(loginModel.password);
            $http.post(url, param, {'Content-Type': 'application/x-www-form-urlencoded'}).then(function(response){
                response = response.data;
                if(response.result == 'success'){
                    _.merge(result.data, response.data);
                    deferred.resolve(result);
                }else{
                    result.status = false;
                    result.msg = response.message;

                    $ionicPopup.alert({
                        title: 'Hint',
                        template: result.msg
                    }).then(function(){
                        deferred.resolve(result);
                    });
                }
            });
            return promise;
        };

        return {
            validateMobileNo: validateMobileNo,
            validatePassword: validatePassword,
            validateLogin: validateLogin,
            doLogin: doLogin
        }
    }]);
