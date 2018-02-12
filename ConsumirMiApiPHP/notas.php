<?php
	require_once 'notasDAO.php';
?>

<html>
<head>
    <meta charset="UTF-8">
    <title> Notas </title>
    <script src="jquery-3.2.1.js"></script>
    <style>
        td {padding: 3px;}
        tr {padding: 3px;}
    </style>
    <script>
        function comprobacion()
		{
			var alumno = document.getElementById("alumno").value;
			var asignatura = document.getElementById("asignatura").value;
			
			var datos = "alumno="+alumno+"&asignatura="+asignatura+"&op=GET_NOTA";
			
			$.ajax({
                        type:'get',
                        url:'notasDAO.php',
                        data:datos,
                        beforeSend: function () 
						{
							$("#contenedor").html("Procesando, espere por favor...");
						},
						success:  function (response) 
						{
							$("#contenedor").html(response);
						}
						
                });
		}
		
		function insertar (alumno, asignatura)
		{
			var nota = document.getElementById("poner_nota").value;
			
			var datos = "alumno="+alumno+"&asignatura="+asignatura+"&nota="+nota+"&op=INSERT_NOTA";
			
			$.ajax({
                        type:'get',
                        url:'notasDAO.php',
                        data:datos,
                        beforeSend: function () 
						{
							$("#contenedor").html("Procesando, espere por favor...");
						},
						success:  function (response) 
						{
							$("#contenedor").html(response);
						}
						
                });
		}
		
		function actualizar (alumno, asignatura)
		{
			var nota = document.getElementById("poner_nota").value;
			
			var datos = "alumno="+alumno+"&asignatura="+asignatura+"&nota="+nota+"&op=UPDATE_NOTA";
			
			$.ajax({
                        type:'get',
                        url:'notasDAO.php',
                        data:datos,
                        beforeSend: function () 
						{
							$("#contenedor").html("Procesando, espere por favor...");
						},
						success:  function (response) 
						{
							$("#contenedor").html(response);
						}
						
                });
		}
	</script>
</head>
<body>
    <h1>NOTAS</h1>
	<a href="alumnos.php">Alumnos</a>
	<a href="asignaturas.php">Asignaturas</a>
	
    <span  id="respuesta"></span><br><br>
    <?php
    $uri = 'http://localhost:8080/MiApiRest/rest/RestNotas';
    
    
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->request('GET', $uri, ['query' => ['accion' => 'alumnos']]);
    $alumnos = json_decode($response->getBody());
    
    
    $response2 = $client->request('GET', $uri, ['query' => ['accion' => 'asignaturas']]);
    $asignaturas = json_decode($response2->getBody());
    ?>

	Seleccionar alumnos:
	<select id="alumno">
            <?php
		foreach ($alumnos as $alumno)
			{
				echo "<option value=".$alumno->id.">".$alumno->nombre."</option>";
			}
	?>
	</select>
	<br>
	Seleccionar asignaturas:
	<select id="asignatura">
	<?php
		foreach ($asignaturas as $asignatura) 
			{
				echo "<option value=".$asignatura->id.">".$asignatura->nombre."</option>";
			}
	?>
	</select>
	<br>
	<button onclick="comprobacion()">Comprobar nota</button><br><br>
	<div id="contenedor"></div>
</body>
</html>
