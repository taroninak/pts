angular.module("projectApp").controller('editCtrl', function ($scope, $http, $route, $routeParams) {

    $scope.title = 'Project Edit Form';
    var id = $route.current.params.id;
    $scope.removedContacts = [];

    $http.get("/api/projects/" + id).then(function (result) {
        $scope.statuses = ["--Select Status--", "NEW", "ACTIVE", "ONGOING", "CLOSED"];
        $scope.project = result.data ? result.data : {status: "--Select Status--"};

        $http.get("/api/projects/" + id + "/contacts").then(function (result) {
            $scope.contacts = result.data ? result.data : [];
        });

    });


    $scope.removeContact = function (contactId) {
        $scope.contacts = $scope.contacts.filter(function (contact) {
            if (contact.id == contactId) {
                $scope.removedContacts.push(contact);
                return false;
            }
            return true;
        });
    };

    $scope.cancel = function () {
        window.history.back();
        //[].push.apply($scope.contacts, $scope.removedContacts);
        //$scope.removedContacts = [];
    };

    $scope.save = function () {
        if($scope.project.status == "--Select Status--" || !$scope.project.title) {
            return alert("Project's Title and Status should be specified!");
        }

        ///TODO validate contacts list

        $http.patch("/api/projects/" + $scope.project.id, JSON.stringify($scope.project)).then(function (result) {
            for (var index in $scope.removedContacts) {
                $http.delete("/api/contacts/" + $scope.removedContacts[index].id).then(function () {
                    
                });
            }
            for (var index in $scope.contacts) {
                $scope.contacts[index].projectId = $scope.project.id;
                if(!$scope.contacts[index].phone) {
                    $scope.contacts[index].phone = '';
                }
                if ($scope.contacts[index].id) {
                    $http.patch("/api/contacts/" + $scope.contacts[index].id, JSON.stringify($scope.contacts[index])).then(function (result) {
                        console.log(result);
                    });
                } else {
                    $http.post("/api/contacts", JSON.stringify($scope.contacts[index])).then(function () {
                        
                    });
                }
            }
            window.history.back();
        });
    };

    $scope.add = function () {
        $scope.contacts.push({});
    }
});
