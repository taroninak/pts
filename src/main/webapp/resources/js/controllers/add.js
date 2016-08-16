angular.module("projectApp").controller('addCtrl', function($scope, $http, $route) {
    $scope.removeContact = function (contactId) {
        console.log("click");
        $scope.contacts = $scope.contacts.filter(function(contact) {
            return (contact.id == contactId) ? false : true;
        });
    };

    $scope.cancel = function () {

    };

    $scope.save = function () {
        for(var index in $scope.contacts) {
            $http.post("/api/contacts", JSON.stringify($scope.contacts[index]))
            .success(function() {

            });
        }
    };

    $scope.statuses = ["--Select Status--", "NEW", "ACTIVE", "ONGOING", "CLOSED"];
    $scope.selectedStatus = "--Select Status--";
});
