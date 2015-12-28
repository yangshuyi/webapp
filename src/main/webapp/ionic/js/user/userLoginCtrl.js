angular.module('userModule').controller('userLoginCtrl', ['$scope', '$ionicPopup', '$state', '$q', 'userService',
    function ($scope, $ionicPopup, $state, $q, userService) {

        $scope.navToForgetPassword = function () {
            userService.validateMobileNo($scope.loginModel)
                .then(function (result) {
                    if (!result.status) {
                        $scope.showErrorMsg(result.msg);
                        return;
                    }
                    $state.go('userForgetPassword', {'mobileNo': $scope.loginModel.mobileNo});
                });
        };

        $scope.login = function () {
            userService.validateMobileNo($scope.loginModel)
                .then(function (result) {
                    if(result.status==false) {
                        return result;
                    }
                    return userService.validatePassword($scope.loginModel);
                })
                .then(function (result) {
                    if(result.status==false) {
                        return result;
                    }
                    return userService.validateLogin($scope.loginModel);
                })
                .then(function (result) {
                    if(result.status==false) {
                        return result;
                    }
                    return userService.doLogin($scope.loginModel);
                })
                .then(function (result) {
                    if(result.status==false) {
                        return result;
                    }
                    return userService.doLogin($scope.loginModel);
                    return $state.go('dashboard.profile', {'mobileNo': result.data});
                });


        };


        $scope.init = function () {
            $scope.loginModel = {};


        };

        $scope.init();

    }]);
