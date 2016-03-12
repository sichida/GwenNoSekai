'use strict';

angular.module('gwennosekaiApp')
    .controller('ArticleDetailController', function ($scope, $rootScope, $stateParams, entity, Article) {
        $scope.article = entity;
        $scope.load = function (id) {
            Article.get({id: id}, function(result) {
                $scope.article = result;
            });
        };
        var unsubscribe = $rootScope.$on('gwennosekaiApp:articleUpdate', function(event, result) {
            $scope.article = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
