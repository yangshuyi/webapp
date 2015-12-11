angular.module('dashboardModule', [])
    .config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider',
        function ($stateProvider, $urlRouterProvider, $ionicConfigProvider) {

            $stateProvider.state('dashboard.profile', {
                url: '/dashboardProfile',
                views: {
                    'tab-profile': {
                        templateUrl: 'templates/dashboard/dashboardProfile.html',
                        controller: 'dashboardProfileCtrl'
                    }
                }
            }).state('dashboard.product', {
                url: '/dashboardProduct',
                views: {
                    'tab-product': {
                        templateUrl: 'templates/dashboard/dashboardProduct.html',
                        controller: 'dashboardProductCtrl'
                    }
                }
            }).state('dashboard.setting', {
                url: '/dashboardSetting',
                views: {
                    'tab-setting': {
                        templateUrl: 'templates/dashboard/dashboardSetting.html',
                        controller: 'dashboardSettingCtrl'
                    }
                }
            });

            $urlRouterProvider.otherwise("/dashboard/dashboardProfile");
        }
    ]);
