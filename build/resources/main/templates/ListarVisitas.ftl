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
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Fecha', 'Visitas'],
                <#list visitaDia as VisitaDia>
                    ['Dia ${VisitaDia.dia} del mes ${VisitaDia.mes} ',  ${VisitaDia.contador}],
                </#list>
                ]);

                var options = {
                    title: 'Visitas por dia',
                    hAxis: {title: 'Fecha',  titleTextStyle: {color: '#333'}},
                    vAxis: {minValue: 0}
                };

                var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script>
    <div id="chart_div" style="width: 100%; height: 500px;"></div>

    <div >
        <table class="table">
            <thead>
            <tr>
                <th>IP</th>
                <th>Sistema</th>
                <th>Fecha</th>
            </tr>
            </thead>
            <tbody>
            <#if rol == "Administrador">
            <#list visitas as Visita>
                <tr class="animated fadeInUp">
                    <td>${Visita.ip}</td>
                    <td> ${Visita.navegador}</td>
                    <td> ${Visita.fecha}</td>
                </tr>
            </#list>
            </#if>
            <#if rol != "Administrador">
                Usted no esta autorizado.
            </#if>
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
