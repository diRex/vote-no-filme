<!DOCTYPE html>
<html ng-app="votenofilme">
<head>
    <meta charset="utf-8">
    <title>Vote no Filme</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="http://twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
<body>

    <h1>Vote No Filme</h1>
    <div id="votacao" ng-controller="votacaoController">

        <ul>
            <li ng-repeat="filme in ranking">{{ filme.nome }}
            <button class="btn btn-error" ng-click="votar(filme.id)">votar</button>
            </li>

        </ul>
        <button class="btn btn-default" ng-click="listranking()">listar ranking</button>

    </div>

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-modal.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tab.js"></script>
    <script src="/assets/components/angular/angular.min.js"></script>
    <script src="/assets/components/angular-resource/angular-resource.min.js"></script>
    <script src="/assets/scripts/main.js"></script>
    <script src="/assets/scripts/controllers/votacao-controller.js"></script>
</body>
</html>
