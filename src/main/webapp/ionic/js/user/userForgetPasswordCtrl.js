angular.module('userModule').controller('userForgetPasswordCtrl', ['$scope', '$stateParams', '$ionicPopup', '$interval', '$window', '$ionicHistory', 'userService',
    function ($scope, $stateParams, $ionicPopup, $interval, $window, $ionicHistory, userService) {
        $scope.sendVerificationCode = function () {
            $ionicPopup.alert({
                title: 'Hint',
                template: '验证码发送成功。'
            }).then(function (res) {
                $scope.forgetPasswordModel.timeCountDown = 30;

                $interval(function () {
                    $scope.forgetPasswordModel.timeCountDown--;
                }, 1000, 30);

            });
        };

        $scope.togglePassword = function () {
            if ($scope.forgetPasswordModel.showPasswordFlag) {
                $scope.forgetPasswordModel.passwordType = "text";
            } else {
                $scope.forgetPasswordModel.passwordType = "password";

            }
            $scope.forgetPasswordModel.showPasswordFlag = !$scope.forgetPasswordModel.showPasswordFlag;

        };

        $scope.navBack = function () {
            //$window.history.back();
            $ionicHistory.goBack();

        };


        $scope.init = function () {
            $scope.forgetPasswordModel = {};
            $scope.forgetPasswordModel.mobileNo = $stateParams.mobileNo;
            $scope.forgetPasswordModel.timeCountDown = '';
            $scope.togglePassword();
        };


        $scope.init();
    }]);
