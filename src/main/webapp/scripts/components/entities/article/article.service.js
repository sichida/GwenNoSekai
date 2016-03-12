'use strict';

angular.module('gwennosekaiApp')
    .factory('Article', function ($resource, DateUtils) {
        return $resource('api/articles/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                    data.lastUpdateDate = DateUtils.convertDateTimeFromServer(data.lastUpdateDate);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
