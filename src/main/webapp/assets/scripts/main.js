
var voteApp = angular.module('votenofilme', ['ngRoute'])
    .config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'assets/templates/init.html',
                controller: 'HomeCtrl'
            }).
            when('/votacao', {
                templateUrl: 'assets/templates/votacao.html',
                controller: 'VotacaoCtrl'
            }).
            when('/ranking', {
                templateUrl: 'assets/templates/ranking.html',
                controller: 'RankingCtrl'
            });
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

    var obterComparacao = function(filme, callback) {
        var callback = callback;
        var data = (filme) ? JSON.stringify(filme) : {};
        $http({method: 'POST', data:data, url: votacao_path+'/obter_comparacao'}).
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

    var listranking = function (callback) {
        $http({
            method: 'GET',
            url: votacao_path+'/ranking'
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
        "votar": votar,
        "listranking": listranking
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

voteApp.controller('VotacaoCtrl', function($scope, $location, VotacaoService) {

    $scope.votar = function(opcao) {
        VotacaoService.votar(opcao, {
            success: function() {
                obterComparacao(opcao);
            }
        });
    }

    function obterComparacao(opcao) {
        VotacaoService.obterComparacao(opcao,{
            success: function(data) {
                $scope.comparacao = data;
            },
            error: function() {
                $location.path("/ranking");
            }
        })
    };

    obterComparacao(null);
});


voteApp.controller('RankingCtrl', function($scope, VotacaoService) {
    $scope.listranking = function() {
        VotacaoService.listranking({
            success:function (data) {
                $scope.ranking = data;
            }
        });
    }

     $scope.listranking();
})
