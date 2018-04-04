var myapp = angular.module("myapp",["ngRoute"]);
myapp.config(function ($routeProvider,$locationProvider) {

	$routeProvider
	.when("/", {templateUrl : "Views/home.html"})
	.when("/home", {templateUrl: "Views/home.html" })
	.when("/login", {templateUrl: "Views/login.html"})
	.when("/contactUs", {templateUrl: "/Views/contactUs.html"})  
	.when("/aboutUs", {  templateUrl: "/Views/aboutUs.html" })
	.when("/SignUp", { templateUrl: "c_user/SignUp.html"})
	.when("/blog", { templateUrl: "c_blog/blog.html"})
	.when("/modifyblog", { templateUrl: "c_blog/editBlog.html"})
	.otherwise({  redirectTo: "/"  });

	$locationProvider.hashPrefix('');
});

myapp.run(function($rootScope,$cookieStore)
		{
			console.log('I am in run function');
			console.log($rootScope.currentUser);
			
				if($rootScope.currentUser==undefined)
				{
				$rootScope.currentUser=$cookieStore.get('userInfo');
				}
				else
				{
				console.log($rootScope.currentUser.username);
				console.log($rootScope.currentUser.role);
				}
		});