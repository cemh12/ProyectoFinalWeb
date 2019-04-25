<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Proyecto Final Web</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/dist/css/bootstrap.css"/>



</head>
<body>
<br><br><br><br>
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
<div class="col-md-12">
    <div class="col-md-4"></div>
    <div class="col-md-8"><h3>Su link es: https://www.cemh12.me/${url}</h3></div>

</div>

<div class="container body-content">
    <hr/>
            <form id="form" method="post">

                <div class="form-group">
                    <label class="control-label" for="Url">Url</label>
                    <input class="form-control" type="text" id="URL" name="URL" value=""/>
                    <span class="text-danger field-validation-valid" data-valmsg-for="URL"
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
<script src="jquery/dist/jquery.js"></script>
<script src="bootstrap/dist/js/bootstrap.js"></script>
<script src="jquery-validation/dist/jquery.validate.js"></script>
<script src="jquery-validation-unobtrusive/jquery.validate.unobtrusive.js"></script>
</html>
