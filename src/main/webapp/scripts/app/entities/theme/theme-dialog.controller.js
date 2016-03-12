'use strict';

angular.module('gwennosekaiApp').controller('ThemeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Theme',
        function($scope, $stateParams, $uibModalInstance, entity, Theme) {

        $scope.theme = entity;
        $scope.load = function(id) {
            Theme.get({id : id}, function(result) {
                $scope.theme = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('gwennosekaiApp:themeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.theme.id != null) {
                Theme.update($scope.theme, onSaveSuccess, onSaveError);
            } else {
                Theme.save($scope.theme, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
