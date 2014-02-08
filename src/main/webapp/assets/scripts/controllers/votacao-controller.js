
voteApp.controller('votacaoController', function($scope, Votacao) {

    $scope.comparacao = {
        opcao1:"",
        opcao2:""
    }

    $scope.votar = function(filmeId) {
        var a = Votacao.post({uri:"votar"}, function() {
            a.filmeId = 1;
            a.$save();
        });
    }

    $scope.listranking = function() {
        $scope.ranking = Votacao.query({uri:"ranking"});
    }

});