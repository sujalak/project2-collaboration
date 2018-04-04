myapp.controller("UserCntrl",function($scope,$http,$location,$rootScope,$cookieStore)
{
	$scope.user={userName:'',password:'',role:'',emailId:'',contactNo:'',isOnline:''};
	
	$rootScope.login=function()
	{
		console.log("Logging Function");
		
		$http.post('http://localhost:8085/collaborationMiddleWare/login',$scope.user)
			.then(function(response)
			{
				console.log(response.status);
				$scope.user=response.data;
				$rootScope.currentUser=response.data;
				$cookieStore.put('userInfo',response.data);
				console.log($rootScope.currentUser.role);
					if($rootScope.currentUser.role=="ROLE_ADMIN")
					{
						console.log('AdminPage');
					}
					else
					{
						console.log('UserPage');
					}
				$location.path("/UserHome");
			});
	};
	$rootScope.logout=function()
	{
		console.log('Logout Function');
		delete $rootScope.currentUser;
		$cookieStore.remove('userInfo');
		$location.path("/logout");
	};
	
	

		$scope.SignUpuser = function()
	{
		console.log("Enter into SingupUser Method");
		$http.post('http://localhost:8085/collaborationMiddleWare/SignUp',$scope.user)
		.then(function(response)
     	{
			console.log('Status Text:'+response.statusText);
	     });			
	};
	
	
	
	$scope.uploadimage = function()
	{
		console.log("Enter into Upload image method");
		$http.post('http://localhost:8085/collaborationMiddleWare/doUpload')
		.then(function(response)
     	{
			console.log(response);
			console.log('Status Text:'+response.statusText);
	     });			
	};
	
	
	
});