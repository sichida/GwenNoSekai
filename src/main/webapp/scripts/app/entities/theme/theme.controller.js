'use strict';

angular.module('gwennosekaiApp')
    .controller('ThemeController', function ($scope, $state, Theme) {

        $scope.themes = [];
        $scope.loadAll = function() {
            Theme.query(function(result) {
               $scope.themes = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.theme = {
                themeId: null,
                id: null
            };
        };
    });
