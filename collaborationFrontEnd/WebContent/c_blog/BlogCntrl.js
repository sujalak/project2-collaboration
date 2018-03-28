myapp.controller('blogCntrl', function($scope, $http, $location) {
	
	$scope.blog = {
		blogId : 0,
		blogName:'',
		blogContent:'',
		createdDate : '',
		likes:0,
		status : 'A',
		userName : 'sujala'
	};
	$scope.insertBlog()=function()
	{
		console.log('entering  insert blog ')
}
	}
	
	
	
