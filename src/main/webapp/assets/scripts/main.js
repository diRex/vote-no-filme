
var voteApp = angular.module('votenofilme', ['ngRoute']);

voteApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'assets/templates/init.html',
                controller: 'HomeCtrl'
            }).
            when('/votacao', {
                templateUrl: 'assets/templates/votacao.html',
                controller: 'VotacaoCtrl'
            })
    }
]);


voteApp.factory('VotacaoService', function($http) {
    var votacao_path = "votacao";

    var loadFilmes = function(callback) {
        var callback = callback;
        $http({method: 'POST', url: votacao_path+'/load_filmes'}).
            success(function (data, status, headers, config) {
                if (callback && callback.success) callback.success(data)
            }).
            error(function (data, status, headers, config) {
                if (callback && callback.error) callback.error(data)
            });
    }

    var obterComparacao = function(callback) {
        var callback = callback;
        $http({method: 'POST', url: votacao_path+'/obter_comparacao'}).
            success(function (data, status, headers, config) {
                if (callback && callback.success) callback.success(data)
            }).
            error(function (data, status, headers, config) {
                if (callback && callback.error) callback.error(data)
            });
    }

    var votar = function(filme, callback) {
        var data = JSON.stringify(filme);
        $http({
            method: 'POST',
            data:data,
            url: votacao_path+'/votar'
            }).
            success(function (data, status, headers, config) {
                if (callback && callback.success) callback.success(data)
            }).
            error(function (data, status, headers, config) {
                if (callback && callback.error) callback.error(data)
            });
    }

    return {
        "loadFilmes": loadFilmes,
        "obterComparacao": obterComparacao,
        "votar": votar
    }
});

voteApp.controller('HomeCtrl', function($scope, $location, VotacaoService) {
    $scope.iniciar = function() {
        VotacaoService.loadFilmes({
            success: function(data) {
                $location.path("/votacao");
            }
        });
    }
});

voteApp.controller('VotacaoCtrl', function($scope, VotacaoService) {

    $scope.listranking = function() {
        $scope.ranking = Votacao.query({uri:"ranking"});
    }

    $scope.votar = function(opcao, $event) {
        $event.stopPropagation();
        VotacaoService.votar(opcao, {
            success: obterComparacao
        });
    }

    function obterComparacao() {
        VotacaoService.obterComparacao({
            success: function(data) {
                $scope.comparacao = data;
            }
        })
    }

    obterComparacao();
});
