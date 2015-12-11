angular.module('dashboardModule').controller('dashboardSettingCtrl', ['$scope', '$stateParams', 'dashboardService',
    function ($scope, $stateParams, dashboardService) {

        $scope.navToEditUserInfoPage = function () {

        };


        $scope.init = function () {
            $scope.dashboardModel =
            {
                "riskLevel": 1,
                "riskLevelDisp": "AAA",
                "score": 100,
                "scoreDisp": "100",
                "earningYesterday": 500,
                "earningYesterdayDisp": "500",
                "rank": 23,
                "cash": 9000,
                "cashDisp": "9,000",
                "earningTotal": 1000,
                "earningTotalDisp": "1,000",
                "prodOwnedCount": 4,
                "prodOwnedCountDisp": "4",
                "earningPercent": 40,
                "earningPercentDisp": "40%"


            };
            $scope.userInfo =
            {
                "userId": $stateParams.userId,
                "userName": "superyang_xp",
                "mobileNo": "13167050551"
            };
        };


        $scope.init();
    }]);
