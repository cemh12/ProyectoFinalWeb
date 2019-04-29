<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Proyecto Final Web</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/dist/css/bootstrap.css"/>
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
</head>


<body>
<br><br><br><br>
<div class="container">
    <div>
            <table class="table">
                <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <#list usuarios as Usuario>
                    <tr class="animated fadeInUp">
                        <td>${Usuario.usuario}</td>
                        <td>${Usuario.rol}
                        </td>

                        <td>
                            <a href="/Usuarios/${Usuario.usuario}/"  class="btn btn-light" role="button">Urls </a>
                            <a href="/EditarUsuario/${Usuario.usuario}" class="btn btn-light" role="button">Editar </a>
                            <a href="/BorrarUsuario/${Usuario.usuario}"  class="btn btn-light" role="button">Borrar </a>
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
