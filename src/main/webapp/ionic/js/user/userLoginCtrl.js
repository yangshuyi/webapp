angular.module('userModule').controller('userLoginCtrl', ['$scope', '$ionicPopup', '$state', 'userService',
    function ($scope, $ionicPopup, $state, userService) {

        $scope.navToForgetPassword = function () {
            var errorMsg = userService.validateMobileNo($scope.loginModel);
            if (errorMsg) {
                $scope.showErrorMsg(errorMsg);
                return;
            }

            $state.go('userForgetPassword', {'mobileNo': $scope.loginModel.mobileNo});
        };

        $scope.showErrorMsg = function (msg) {
            $ionicPopup.alert({
                title: 'Hint',
                template: msg
            }).then(function (res) {
                console.log(res);
            });
        };

        $scope.init = function () {
            $scope.loginModel = {};


        };

        $scope.init();

    }]);
