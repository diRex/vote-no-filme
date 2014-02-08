

var voteApp = angular.module('votenofilme', ['ngResource']);

voteApp.factory('Votacao', function($resource) {
    return $resource('/votacao/:uri', {}, {
        queryList:{method:'GET', isArray:true},
        post:{method:'POST'}

    });
});