app.controller('BlogCntrl', function($scope, $http, $location) {
	
	$scope.blog = {
		blogId : 0,
		blogName:'',
		blogContent:'',
		createdDate : '',
		likes:0,
		status : 'A',
		userName : 'sujala'
	};
	$scope.insertBlog=function()
	{
		console.log("Enter into insertBlog Method");
		$http.post('http://localhost:8085/collaborationMiddleWare/saveBlog',$scope.blog)
		.then(fetchAllBlog(),function(response)
     	{
			console.log('Status Text:'+response.statusText);
	     });			
	};
	
	function fetchAllBlog()
	{
		console.log('Fetching All Blogs');
		$http.get('http://localhost:8085/collaborationMiddleWare/listBlogs')
		.then(function(response)
				{
			            $scope.blogdata=response.data;
				});
	};
	
	$scope.editBlog=function(blogId)
	{
		$http.get('http://localhost:8085/collaborationMiddleWare/getBlog',+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("updateBlog");
		});
	};
	
	$scope.editBlog=function(blogId)
	{
		$http.get('http://localhost:8085/collaborationMiddleWare/getBlog',+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("deleteBlog");
		});
	};
	
	
	
});
	
	
	
