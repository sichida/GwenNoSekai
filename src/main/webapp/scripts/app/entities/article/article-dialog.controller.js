'use strict';

angular.module('gwennosekaiApp').controller('ArticleDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Article',
        function($scope, $stateParams, $uibModalInstance, entity, Article) {

        $scope.article = entity;
        $scope.load = function(id) {
            Article.get({id : id}, function(result) {
                $scope.article = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('gwennosekaiApp:articleUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.article.id != null) {
                Article.update($scope.article, onSaveSuccess, onSaveError);
            } else {
                Article.save($scope.article, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreationDate = {};

        $scope.datePickerForCreationDate.status = {
            opened: false
        };

        $scope.datePickerForCreationDateOpen = function($event) {
            $scope.datePickerForCreationDate.status.opened = true;
        };
        $scope.datePickerForLastUpdateDate = {};

        $scope.datePickerForLastUpdateDate.status = {
            opened: false
        };

        $scope.datePickerForLastUpdateDateOpen = function($event) {
            $scope.datePickerForLastUpdateDate.status.opened = true;
        };
}]);
