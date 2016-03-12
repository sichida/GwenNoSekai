 'use strict';

angular.module('gwennosekaiApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-gwennosekaiApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-gwennosekaiApp-params')});
                }
                return response;
            }
        };
    });
