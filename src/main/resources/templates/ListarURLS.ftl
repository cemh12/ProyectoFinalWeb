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
                <li><a href="/Api/urls/">Urls REST</a></li>
                <#if rol == "No registrado">
                    <li style="alignment: right"> <a href="/Registrar/">Registrarse</a></li>
                    <li style="alignment: right"><a href="/login/">Login</a></li>
                </#if>
                <#if rol != "No registrado">
                    <li style="alignment: right"> <a>${nombre} (${rol})</a></li>
                </#if>
            </ul>

        </div>
    </div>
</nav>

<body>

<br><br><br><br>
<div class="container">

    <header class="jumbotron my-4 text-center">
    </header>

    <div>
        <table class="table">
            <thead>
            <tr>
                <th>URL</th>
                <th>Acortada</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <#list urls as Url>
                <tr class="animated fadeInUp">
                    <td><a href="${Url.url}">${Url.url}</a></td>
                    <td><a href="https://cemh12.me/${Url.hashMaked}">https://cemh12.me/${Url.hashMaked}</a>
                    </td>
                    <td><img src="
        https://chart.googleapis.com/chart?chs=150x150&cht=qr&chl=https://cemh12.me/${Url.hashMaked}"</td>

                    <td>
                        <a href="/${Url.hashMaked}/estadisticas/" class="btn btn-light" role="button">Estadisticas </a>
                        <a href="/Api/urls/${Url.hashMaked}/">Ver resumen json</a>
                    </td>
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
