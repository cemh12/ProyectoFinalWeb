<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Proyecto Final Web</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/dist/css/bootstrap.css"/>



</head>
<body>
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
        <h1 class="display-3">Modificar</h1>
    </header>
    <hr>
    <form method="post" action="/EditarUsuario/${usuario.usuario}">
        ${usuario.nombre}
        <div class="form-group">
            <label for="rol">Rol</label>
            <select name="Rol" id="Rol">
                <option title='Ninguno' value='Ninguno' selected='selected'>Ninguno</option>
                <option title='Administrador' value='Administrador'>Administrador</option>
            </select>
        </div>
        <input type="submit" class="btn btn-primary" value="Guardar">

    </form>
</div>
</body>
<script src="jquery/dist/jquery.js"></script>
<script src="bootstrap/dist/js/bootstrap.js"></script>
<script src="jquery-validation/dist/jquery.validate.js"></script>
<script src="jquery-validation-unobtrusive/jquery.validate.unobtrusive.js"></script>

</html>
