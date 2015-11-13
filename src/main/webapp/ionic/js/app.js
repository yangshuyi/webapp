angular.module('rootApp', ['ionic', 'userModule'])
    .config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider',
        function ($stateProvider, $urlRouterProvider, $ionicConfigProvider) {

            $stateProvider.state('userLogin', {
                url: '/user/login',
                templateUrl: 'templates/user/login.html',
                controller: 'userLoginCtrl'
            }).state('userForgetPassword', {
                url: '/user/forgetPassword',
                templateUrl: 'templates/user/forgetPassword.html',
                controller: 'userForgetPasswordCtrl',
                params: {
                    'mobileNo': ''
                }
            }).state('dashboard', {
                url: '/dashboard',
                templateUrl: 'templates/dashboard.html',
                controller: 'dashboardCtrl'
            });

            $urlRouterProvider.otherwise("/user/login");
            console.log("done1");
        }]);
