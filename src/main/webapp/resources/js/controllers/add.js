angular.module("projectApp").controller('addCtrl', function ($scope, $http, $route) {

    $scope.title = 'Project Add Form';
    $scope.project = {};
    $scope.contacts = [];
    $scope.statuses = ["--Select Status--", "NEW", "ACTIVE", "ONGOING", "CLOSED"];
    $scope.project.status = "--Select Status--";

    $scope.removeContact = function (contactId) {
        console.log("click");
        $scope.contacts = $scope.contacts.filter(function (contact) {
            return (contact.id == contactId) ? false : true;
        });
    };

    $scope.cancel = function () {
        window.history.back();
    };

    $scope.save = function () {
        if($scope.project.status == "--Select Status--" || !$scope.project.title) {
            return alert("Project's Title and Status should be specified!");
        }

        ///TODO validate contacts list

        $http.post("/api/projects", JSON.stringify($scope.project)).then(function (result) {
            if(!$scope.contacts || !$scope.contacts.length) return window.history.back();
            for (var index in $scope.contacts) {
                $scope.contacts[index].projectId = result.dtata.id;
                if(!$scope.contacts[index].phone) {
                    $scope.contacts[index].phone = '';
                }
                $http.post("/api/contacts", JSON.stringify($scope.contacts[index]));
            }
            window.history.back();
        });
    };



    $scope.add = function () {
        $scope.contacts.push({});
    }
});
