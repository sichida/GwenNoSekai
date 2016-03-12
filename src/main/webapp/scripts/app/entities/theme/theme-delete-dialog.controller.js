'use strict';

angular.module('gwennosekaiApp')
	.controller('ThemeDeleteController', function($scope, $uibModalInstance, entity, Theme) {

        $scope.theme = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Theme.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
