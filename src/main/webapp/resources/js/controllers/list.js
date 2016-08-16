angular.module("projectApp").controller('listCtrl', function($scope, $http) {
    $scope.removedProjects = [];
    $scope.removeProject = function (projectId) {
        $scope.projects = $scope.projects.filter(function(project) {
            if(project.id == projectId) {
                $scope.removedProjects.push(projectId);
                $http.delete("/api/projects/" + projectId).then(function (result) {
                    console.log(result.data);
                });
                return false;
            }
            return true;
        });
    };
    $http.get("/api/projects").then(function(result) {
        $scope.projects = result.data;
    });
});
