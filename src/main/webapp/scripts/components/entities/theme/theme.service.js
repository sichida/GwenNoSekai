'use strict';

angular.module('gwennosekaiApp')
    .factory('Theme', function ($resource, DateUtils) {
        return $resource('api/themes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
