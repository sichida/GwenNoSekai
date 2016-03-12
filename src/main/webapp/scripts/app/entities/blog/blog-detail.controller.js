'use strict';

angular.module('gwennosekaiApp')
    .controller('BlogDetailController', function ($scope, $rootScope, $stateParams, entity, Blog) {
        $scope.blog = entity;
        $scope.load = function (id) {
            Blog.get({id: id}, function(result) {
                $scope.blog = result;
            });
        };
        var unsubscribe = $rootScope.$on('gwennosekaiApp:blogUpdate', function(event, result) {
            $scope.blog = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
