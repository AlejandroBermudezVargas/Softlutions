'use strict';
angular.module('dondeEs.contact', ['ngRoute'])
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/contact', {
	    templateUrl: 'resources/Contact/Contact.html',
	    controller: 'ContactCtrl'
	  });
	}])
	.controller('ContactCtrl', ['$scope','$http',function($scope,$http) {

		$scope.sendMessage = function () {
			var data = {
				userName: $scope.name,
				userEmail: $scope.email,
				message: $scope.message
			}
			
			console.log(data);
			
			$http({method: 'POST',url:'rest/protected/sendEmail/sendMessage', data, headers: {'Content-Type': 'application/json'}})
					.success(function(response) {
			}); 
		}
		
	}]);