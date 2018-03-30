myapp.controller('BlogCntrl', function($scope, $http, $location) {
	
	$scope.blog = {
		blogId : 0,
		blogName:'',
		blogContent:'',
		createdDate : '',
		likes:0,
		status : 'A',
		userName : 'sujala'
	};
	$scope.reset=function(){
		alert("hi");
	}
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
	fetchAllBlog();
	$scope.editBlog=function(blogId)
	{
		$http.get('http://localhost:8085/collaborationMiddleWare/getBlog/'+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.mblog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("#/updateBlog");
		});
	};
	$scope.updateBlog=function(blogId)
	{
		$http.put('http://localhost:8085/collaborationMiddleWare/updateBlog/'+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			
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
	
	
	
