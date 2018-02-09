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
	
	Seleccionar alumnos:
	<select id="alumno">
	<?php
		while ($fila = $alumnos->fetch_row()) 
			{
				echo "<option value=".$fila[0].">".$fila[1]."</option>";
			}
	?>
	</select>
	<br>
	Seleccionar asignaturas:
	<select id="asignatura">
	<?php
		while ($fila = $asignaturas->fetch_row()) 
			{
				echo "<option value=".$fila[0].">".$fila[1]."</option>";
			}
	?>
	</select>
	<br>
	<button onclick="comprobacion()">Comprobar nota</button><br><br>
	<div id="contenedor"></div>
</body>
</html>
