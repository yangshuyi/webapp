angular.module('rootApp', ['ionic', 'common.component', 'common.utils', 'userModule', 'dashboardModule'])
    .config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider', '$httpProvider',
        function ($stateProvider, $urlRouterProvider, $ionicConfigProvider, $httpProvider) {

               $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
                $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

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
                abstract: true,
                templateUrl: 'templates/dashboard/dashboard.html',
                params: {
                    'userId': 0
                }
            }).state('editUserInfo', {
                url: '/user/editUserInfo',
                abstract: true,
                templateUrl: 'templates/user/editUser.html',
                params: {
                    'userId': 0
                }
            });

            $urlRouterProvider.otherwise("/user/login");


            $ionicConfigProvider.platform.ios.tabs.style('standard');  // 参数可以是: standard | striped
            $ionicConfigProvider.platform.ios.tabs.position('bottom');
            $ionicConfigProvider.platform.android.tabs.style('standard');  // 参数可以是: standard | striped
            $ionicConfigProvider.platform.android.tabs.position('bottom');
        }]);
