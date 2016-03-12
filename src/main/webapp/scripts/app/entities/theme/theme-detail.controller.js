'use strict';

angular.module('gwennosekaiApp')
    .controller('ThemeDetailController', function ($scope, $rootScope, $stateParams, entity, Theme) {
        $scope.theme = entity;
        $scope.load = function (id) {
            Theme.get({id: id}, function(result) {
                $scope.theme = result;
            });
        };
        var unsubscribe = $rootScope.$on('gwennosekaiApp:themeUpdate', function(event, result) {
            $scope.theme = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
