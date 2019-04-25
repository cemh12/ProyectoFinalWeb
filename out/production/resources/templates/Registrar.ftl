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
<div class="container body-content">
    <hr/>
            <form id="form" method="post">

                <div class="form-group">
                    <label class="control-label" for="Usuario">Usuario</label>
                    <input class="form-control" type="text" id="Usuario" name="Usuario" value=""/>
                    <span class="text-danger field-validation-valid" data-valmsg-for="Usuario"
                          data-valmsg-replace="true"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="Email">Email</label>
                    <input class="form-control" type="text" id="Email" name="Email" value=""/>
                    <span class="text-danger field-validation-valid" data-valmsg-for="Email"
                          data-valmsg-replace="true"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="Nombre">Nombre</label>
                    <input class="form-control" type="text" id="Nombre" name="Nombre" value=""/>
                    <span class="text-danger field-validation-valid" data-valmsg-for="Nombre"
                          data-valmsg-replace="true"></span>
                </div>
                <div class="form-group">
                    <label class="control-label" for="Password">Password</label>
                    <input class="form-control" type="Password" id="Password" name="Password" value=""/>
                    <span class="text-danger field-validation-valid" data-valmsg-for="Password"
                          data-valmsg-replace="true"></span>
                </div>
                <input type="submit" value="Guardar" class="btn btn-default"/>
            </form>
            <table id="tabla" class="display" style="width:100%">

                <thead>
                </thead>
            </table>
    <hr/>
    <footer>
    </footer>
</div>
<script src="jquery/dist/jquery.js"></script>
<script src="bootstrap/dist/js/bootstrap.js"></script>
<script src="jquery-validation/dist/jquery.validate.js"></script>
<script src="jquery-validation-unobtrusive/jquery.validate.unobtrusive.js"></script>
</body>
</html>
