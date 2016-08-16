angular.module("projectApp").controller('editCtrl', function($scope, $http, $route, $routeParams) {
    var id = $route.current.params.id;
    $scope.removedContacts = [];
    $scope.removeContact = function (contactId) {
        console.log("click");
        $scope.contacts = $scope.contacts.filter(function(contact) {
            if(contact.id == contactId) {
                $scope.removedContacts.push(contact);
                return false;
            }
            return true;
        });
    };

    $scope.cancel = function () {
        [].push.apply($scope.contacts, $scope.removedContacts);
        $scope.removedContacts = [];
    };

    $scope.save = function () {
        for(var index in $scope.removedContacts) {
            $http.delete("/api/contacts/" + $scope.removedContacts[index].id).success(function () {
                for(var index in $scope.contacts) {
                    $http.put("/api/contacts", JSON.stringify($scope.contacts[index])).success(function() {

                    });
                }
            });
        }
    };

    $http.get("/api/projects/"+id).then(function(result) {
        $scope.statuses = ["--Select Status--", "NEW", "ACTIVE", "ONGOING", "CLOSED"];
        $scope.project = result.data;
        if($scope.project.status) {
            $scope.selectedStatus = $scope.project.status;
        } else {
            $scope.selectedStatus = "--Select Status--";
        }
        $http.get("/api/projects/" + id + "/contacts").then(function(result) {
            $scope.contacts = result.data;
        });

    });
});
