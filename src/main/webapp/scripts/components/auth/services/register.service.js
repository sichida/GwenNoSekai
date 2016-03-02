'use strict';

angular.module('gwennosekaiApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


