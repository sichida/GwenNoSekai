'use strict';

angular.module('gwennosekaiApp')
    .controller('BlogController', function ($scope, $state, Blog) {

        $scope.blogs = [];
        $scope.loadAll = function() {
            Blog.query(function(result) {
               $scope.blogs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.blog = {
                blogId: null,
                blogTitle: null,
                blogSeoDescription: null,
                id: null
            };
        };
    });
