var app = angular.module("myapp",["ngRoute"]);
app.config(function ($routeProvider,$locationProvider) {
            	
                $routeProvider
                .when("/", {
                    templateUrl : "Views/home.html",
                    controller: "homeController"
                })
                    .when('/home', {
                        templateUrl: 'Views/home.html',
                        controller: 'homeController'
                    })
                    .when('/login', {
                        templateUrl: 'Views/login.html',
                        controller: 'loginController'
                    })
                    .when('/contactUs', {
                        templateUrl: 'Views/contactUs.html',
                        controller: 'contactUsController'
                    })
                    .when('/aboutUs', {
                        templateUrl: 'Views/aboutUs.html',
                        controller: 'aboutUsController'
                    })
                    .when('/SignUp', {
                        templateUrl: 'Views/SignUp.html',
                        controller: 'SignUpController'
                    })
                    .when('/blog', {
                        templateUrl: 'c_blog/blog.html',
                        controller: 'c_blog/BlogCntrl'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
                    
                //$locationProvider.html5mode(true);
                $locationProvider.hashPrefix('');
                    
                   

            });

//           app.controller('homeController', function ($scope) {
//                $scope.message = "Home Page";
//            });
           app.controller('contactUsController', function ($scope) {
            	  $scope.message = "Conatct Us Page";
            })
            app.controller('aboutUsController', function ($scope) {
            	  $scope.message = "About Us";
            })