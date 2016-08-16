var app = angular.module("projectApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "templates/list.html",
        controller: "listCtrl"
    }).when("/add", {
        templateUrl : "templates/edit.html",
        controller: "addCtrl"
    })
    .when("/:id", {
        templateUrl : "templates/edit.html",
        controller: "editCtrl"
    });

});
