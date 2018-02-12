<?php
	require_once 'asignaturasDAO.php';
?>

<html>
<head>
    <meta charset="UTF-8">
    <title> ASIGNATURAS </title>
    <script src="jquery-3.2.1.js"></script>
    <style>
        td {padding: 3px;}
        tr {padding: 3px;}
    </style>
    <script>
        function editar(id,nombre,curso,ciclo)
        {
            document.getElementById(id).innerHTML=
                    '<td><input size="10" name="nombre'+id+'" type="text" value="'+nombre+'"></td>'+
                    '<td><input size="10" name="curso'+id+'" type="text" value='+curso+'></td>'+
                    '<td><input size="10" name="ciclo'+id+'" type="text" value='+ciclo+' ></td>'+
                    '<td><button onclick="guardar('+id+')">guardar</button></td>';
        }
        function guardar(id)
        {
            var nombre = document.getElementsByName("nombre"+id)[0].value;
            var curso = document.getElementsByName("curso"+id)[0].value;
            var ciclo = document.getElementsByName("ciclo"+id)[0].value;;

            document.getElementById(id).innerHTML=
                    "<td>"+nombre+"</td>"+
                    "<td>"+curso+"</td>"+
                    "<td>"+ciclo+"</td>"+
                    "<td><button onclick=\"editar('"+id+"','"+nombre+"','"+curso+"',"+ciclo+")\">Editar</button></td>"+
                    "<td><button onclick=\"borrar('"+id+"','"+nombre+")\">Borrar</button></td>";

            var datos = "id="+id+"&nombre="+nombre+"&curso="+curso+"&ciclo="+ciclo+"&op=UPDATE";

            $.ajax({
                    type:'get',
                    url:'asignaturasDAO.php',
                    data:datos,
                    success:function(resp)
                    {
                        if (resp > 0 )
                        {
                            $("#respuesta").html("Asignatura actualizada correctamente");
                        }
                        else
                        {
                            $("#respuesta").html("Ha habido un problema al actualizar la asignatura");
                        }
                    }
            });
        }

        function borrar(id, nombre)
        {
            var confirmacion = confirm("Seguro que quieres eliminar a la asignatura: "+nombre+"?");

            if(confirmacion == true)
            {
                var fila = document.getElementById(id);
                


                var datos = "id="+id+"&op=DELETE";
                $.ajax({
                        type:'get',
                        url:'asignaturasDAO.php',
                        data:datos,
                        beforeSend: function () 
                        {
                                $("#respuesta").html("Procesando, espere por favor...");
                        },
                        success:  function (response) 
                        {
                            if (response == 1)
                            {
                                    fila.parentNode.removeChild(fila);
                                    response = "Asignatura "+nombre+" borrado correctamente";
                            }
                            else if (response == 0)
                            {
                                    var confirmacion2 = confirm("Esta asignatura tiene una nota asignada. ¿Quieres borrarla también?");
                                    if (confirmacion2 == true)
                                    {
                                            delete_cascade(id);
                                    }
                                    else if (confirmacion2 == false)
                                    {
                                            document.getElementById("respuesta").innerHTML = "La asignatura: "+nombre+" no ha sido eliminada";
                                    }
                            }
                        }
						
                });
            }
        }
		
		function delete_cascade(id)
		{
			var fila = document.getElementById(id);
			var datos = "id="+id+"&op=DELETE_CASCADE";
                $.ajax({
                        type:'get',
                        url:'asignaturasDAO.php',
                        data:datos,
                        beforeSend: function () 
                        {
                                $("#respuesta").html("Procesando, espere por favor...");
                        },
                        success:  function (response) 
                        {
                                if (response > 0)
                                {
                                        fila.parentNode.removeChild(fila);
                                        response = "Asignatura y nota borradas correctamente";
                                }
                                else if (response == 0)
                                {
                                        response = "Ha habido un error";
                                }
                                $("#respuesta").html(response);
                        }
						
                });
		}

        function insertar()
        {
            var nombre_insertar = document.getElementsByName("nombre_insertar")[0].value;
            var curso_insertar = document.getElementsByName("curso_insertar")[0].value;
			var ciclo_insertar = document.getElementsByName("ciclo_insertar")[0].value;


            var datos = "nombre="+nombre_insertar+"&curso="+curso_insertar+"&ciclo="+ciclo_insertar+"&op=INSERT";
			
            $.ajax({
                    type:'get',
                    url:'asignaturasDAO.php',
                    data:datos,
                    beforeSend: function () 
                    {
                        $("#resultado").html("Procesando, espere por favor...");
                    },
                    success:  function (response) 
                    {
                        $("#resultado").html(response);
                        recargar();
                    }
            });

        }

        function recargar()
        {
            setTimeout(document.location.href = document.location.href, 2000);
        }
        </script>
</head>
<body>
    <h1>ASIGNATURAS</h1>
	<a href="alumnos.php">Alumnos</a>
	<a href="notas.php">Notas</a>
    <p  id="respuesta"></p><br><br>
    <table border = 1>
        <tr><th>NOMBRE</th><th>CURSO</th><th>CICLO</th><th colspan='2'>EDITAR/BORRAR</th></tr>
		<tr>
			<td><input size="10" type="text" name="nombre_insertar" size="12"></td>
			<td><input size="10" type="text" name="curso_insertar"></td>
			<td><input size="10" type="text" name="ciclo_insertar"></td>
			<td colspan="2"><button onclick="insertar()">Añadir Asignatura</button></td>
		</tr>    
<?php
		
     $uri = 'http://localhost:8080/MiApiRest/rest/RestNotas';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->request('GET', $uri);
    $asignaturas = json_decode($response->getBody());

    foreach ($asignaturas as $asignatura)
    {
                echo "<tr id='".$asignatura->id."'>\n"
                . "<td>".$asignatura->nombre."</td>\n"
                . "<td>".$asignatura->curso."</td>\n"
                . "<td>".$asignatura->ciclo."</td>\n"
                . "<td><button onclick=\"editar(".$asignatura->id.", '".$asignatura->nombre."', '".$asignatura->curso."', '".$asignatura->ciclo."')\">Editar</td>\n"
                . "<td><button onclick=\"borrar(".$asignatura->id.", '".$asignatura->nombre."')\">Borrar</td>\n"
                . "</tr>\n\n";
        }
    echo "</table>";
    $mysql = null;
    
   
?>
</body>
</html>
