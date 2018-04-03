myapp.controller('BlogCntrl', function($scope, $http, $location) {
	
	$scope.blog = {
		blogId : '',
		blogName:'',
		blogContent:'',
		createdDate : '',
		likes:0,
		status : '',
		userName : ''
	};
	fetchAllBlog();
	$scope.insertBlog=function()
	{
		console.log("Enter into insertBlog Method");
		$http.post('http://localhost:8085/collaborationMiddleWare/saveBlog',$scope.blog)
		.then(fetchAllBlog(),function(response)
     	{
			console.log('Status Text:'+response.statusText);
			fetchAllBlog();
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
		
		$http.get('http://localhost:8085/collaborationMiddleWare/getBlog/'+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/modifyblog");
		});
	};
	$scope.updateBlog=function()
	{
		console.log('updating', $scope.blog.blogId)

		$http.put('http://localhost:8085/collaborationMiddleWare/updateBlog/'+$scope.blog.blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/blog");
			
		});
	};
	
	$scope.deleteBlog=function(blogId)
	{
		$http.delete('http://localhost:8085/collaborationMiddleWare/deleteBlog/'+blogId)
	.then(fetchAllBlog(),function(response)	
		{
			console.log(response.data);
			 $scope.blogdel=response.data;
			console.log('Status Text:'+response.statusText);
			fetchAllBlog();
			$location.path("/blog");
		});
		
	};
	
	
	$scope.incrementLike=function(blogId)
	{
		$http.put('http://localhost:8085/collaborationMiddleWare/incrementLike/'+blogId)
	.then(fetchAllBlog(),function(response)	
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/blog");
		});
	};
	
	
	
});
	
	
	
