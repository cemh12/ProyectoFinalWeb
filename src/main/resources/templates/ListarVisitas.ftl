<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Proyecto Final Web</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/dist/css/bootstrap.css"/>



</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Acortador URL</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="/Usuarios/">Usuarios</a></li>
                <li><a href="/Api/usuarios/">Usuarios REST</a></li>
                <li><a href="/ws/usuarioWebService?wsdl">Soap</a></li>
                <li><a href="/Registrar/">Registrarse</a></li>
                <li><a href="/login/">Login</a></li>
            </ul>

        </div>
    </div>
</nav>

<body>
<br><br><br><br>

<div class="container">

    <header class="jumbotron my-4 text-center">
    </header>

    <div class="bg-primary text-center text-white">
        <table class="table">
            <thead>
            <tr>
                <th>IP</th>
                <th>Sistema</th>
                <th>Fecha</th>
            </tr>
            </thead>
            <tbody>
            <#list visitas as Visita>
                <tr class="animated fadeInUp">
                    <td>${Visita.ip}</td>
                    <td> ${Visita.navegador}</td>
                    <td> ${Visita.fecha}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>
<script src="jquery/dist/jquery.js"></script>
<script src="bootstrap/dist/js/bootstrap.js"></script>
<script src="jquery-validation/dist/jquery.validate.js"></script>
<script src="jquery-validation-unobtrusive/jquery.validate.unobtrusive.js"></script>
</html>
