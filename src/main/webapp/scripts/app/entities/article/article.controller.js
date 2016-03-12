'use strict';

angular.module('gwennosekaiApp')
    .controller('ArticleController', function ($scope, $state, Article) {

        $scope.articles = [];
        $scope.loadAll = function() {
            Article.query(function(result) {
               $scope.articles = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.article = {
                title: null,
                content: null,
                permalink: null,
                creationDate: null,
                lastUpdateDate: null,
                isPublished: false,
                enablePublicPreview: null,
                author: null,
                thumbnail: null,
                id: null
            };
        };
    });
