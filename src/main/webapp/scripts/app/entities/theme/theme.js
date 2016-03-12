'use strict';

angular.module('gwennosekaiApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('theme', {
                parent: 'entity',
                url: '/themes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'gwennosekaiApp.theme.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/theme/themes.html',
                        controller: 'ThemeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('theme');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('theme.detail', {
                parent: 'entity',
                url: '/theme/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'gwennosekaiApp.theme.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/theme/theme-detail.html',
                        controller: 'ThemeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('theme');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Theme', function($stateParams, Theme) {
                        return Theme.get({id : $stateParams.id});
                    }]
                }
            })
            .state('theme.new', {
                parent: 'theme',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/theme/theme-dialog.html',
                        controller: 'ThemeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    themeId: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('theme', null, { reload: true });
                    }, function() {
                        $state.go('theme');
                    })
                }]
            })
            .state('theme.edit', {
                parent: 'theme',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/theme/theme-dialog.html',
                        controller: 'ThemeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Theme', function(Theme) {
                                return Theme.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('theme', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('theme.delete', {
                parent: 'theme',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/theme/theme-delete-dialog.html',
                        controller: 'ThemeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Theme', function(Theme) {
                                return Theme.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('theme', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
