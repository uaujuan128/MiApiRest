<?php
	require_once 'alumnosDAO.php';
?>

<html>
<head>
    <meta charset="UTF-8">
    <title> ALUMNOS </title>
    <script src="jquery-3.2.1.js"></script>
    <style>
        td {padding: 3px;}
        tr {padding: 3px;}
    </style>
    <script>
        function formatDate(fecha) 
        { 
            var dia = fecha.substring(0, 2);
            var mes = fecha.substring(3, 5);
            var ano = fecha.substring(6, 10);
            return [ano, mes, dia].join('-');
        }
        function formatDateInverso(fecha) 
        { 
            var ano = fecha.substring(0, 4);
            var mes = fecha.substring(5, 7);
            var dia = fecha.substring(8, 10);
            return [dia, mes, ano].join('-');
        }
        function verifica_check (mayor)
        {
            if (mayor == true)
            {
                return "checked";
            }
            else
            {
                return "unchecked";
            }
        }
        function editar(id,nombre,fecha,mayor)
        {
            document.getElementById(id).innerHTML=
                    '<td><input name="nombre'+id+'" type="text" value="'+nombre+'"></td>'+
                    '<td><input name="fecha'+id+'" type="date" value='+fecha+'></td>'+
                    '<td><input name="mayor'+id+'" type="checkbox"'+verifica_check(mayor)+' ></td>'+
                    '<td><button onclick="guardar('+id+')">guardar</button></td>';
        }
        function guardar(id)
        {
            var nombre = document.getElementsByName("nombre"+id)[0].value;
            var fecha = document.getElementsByName("fecha"+id)[0].value;
            var mayor;

            if (document.getElementsByName("mayor"+id)[0].checked == true)
            {
                mayor = true;
            }
            else
            {
                mayor = false;
            }

            document.getElementById(id).innerHTML=
                    "<td>"+nombre+"</td>"+
                    "<td>"+formatDateInverso(fecha)+"</td>"+
                    "<td><input type='checkbox' "+verifica_check(mayor)+"></td>"+
                    "<td><button onclick=\"editar('"+id+"','"+nombre+"','"+fecha+"',"+mayor+")\">Editar</button></td>"+
                    "<td><button onclick=\"borrar('"+id+"','"+nombre+")\">Borrar</button></td>";

            var datos = "id="+id+"&nombre="+nombre+"&fecha="+fecha+"&mayor="+mayor+"&op=UPDATE";

            $.ajax({
                    type:'get',
                    url:'alumnosDAO.php',
                    data:datos,
                    success:function(resp)
                    {
                        if (resp > 0)
                        {
                            $("#respuesta").html("El alumno ha sido editado correctamente");
                        }
                        else
                        {
                            $("#respuesta").html("Ha habido un error al actualizar el alumno");
                        }
                    }
            });
            return false;
        }

        function borrar(id, nombre)
        {
            var confirmacion = confirm("Seguro que quieres eliminar al alumno: "+nombre+", "+id+"?");
            

            if(confirmacion == true)
            {
                var fila = document.getElementById(id);
                


                var datos = "id="+id+"&op=DELETE";
                $.ajax({
                type:'get',
                url:'alumnosDAO.php',
                data:datos,
                beforeSend: function () 
                    {
                            $("#respuesta").html("Procesando, espere por favor...");
                    },
                    success:  function (response) 
                    {
                            if (response == "1")
                            {
                                    fila.parentNode.removeChild(fila);
                                    document.getElementById("respuesta").innerHTML = "El alumno: "+nombre+" ha sido eliminado correctamente";
                            }
                            else if (response == "0")
                            {
                                    var confirmacion2 = confirm("Este usuario tiene una nota asignada. ¿Quieres borrarla también?");
                                    if (confirmacion2 == true)
                                    {
                                            delete_cascade(id, nombre);
                                    }
                                    else if (confirmacion2 == false)
                                    {
                                            document.getElementById("respuesta").innerHTML = "El alumno: "+nombre+" no ha sido eliminado";
                                    }
                            }
                    }
						
                });
            }
        }
		
		function delete_cascade(id, nombre)
		{
			var fila = document.getElementById(id);
			var datos = "id="+id+"&op=DELETE_CASCADE";
                $.ajax({
                        type:'get',
                        url:'alumnosDAO.php',
                        data:datos,
                        beforeSend: function () 
                        {
                                $("#respuesta").html("Procesando, espere por favor...");
                        },
                        success:  function (response) 
                        {
                            if(response > 0)
                            {
                                $("#respuesta").html("El alumno: "+nombre+" ha sido eliminado correctamente");
                                fila.parentNode.removeChild(fila);
                            }
                            else
                            {
                                $("#respuesta").html("Ha habido un error al borrrar");
                            }
                                
                        }
						
                });
		}

        function insertar()
        {
            var nombre_insertar = document.getElementsByName("nombre_insertar")[0].value;
            var fecha_insertar = document.getElementsByName("fecha_insertar")[0].value;
            var mayor_insertar;

            if (document.getElementsByName("mayor_insertar")[0].checked == true)
            {
                mayor_insertar = true;
            }
            else
            {
                mayor_insertar = false;
            }


            var datos = "nombre="+nombre_insertar+"&fecha="+fecha_insertar+"&mayor="+mayor_insertar+"&op=INSERT";
			
            $.ajax({
                    type:'get',
                    url:'alumnosDAO.php',
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
    <h1>ALUMNOS</h1>
	<a href="asignaturas.php">Asignaturas</a>
	<a href="notas.php">Notas</a>
    <p id="respuesta"></p><br><br>
    <table border = 1>
        <tr><th>Nombre</th><th>Fecha</th><th>Mayor</th><th>Editar</th><th>Borrar</th></tr>
		<tr>
			<td><input type="text" name="nombre_insertar" size="12"></td>
			<td><input type="date" name="fecha_insertar"></td>
			<td><input type="checkbox" name="mayor_insertar"></td>
			<td colspan="2"><button onclick="insertar()">Añadir Alumno</button></td>
		</tr>    
<?php
	
	function formatear_fecha ($fecha)
	{
		$ano = substr($fecha, 0, 4);
		$mes = substr($fecha, 5, 2);
		$dia = substr($fecha, 8, 2);		 
		return $dia."-".$mes."-".$ano;
	}
	
    $uri = 'http://localhost:8080/MiApiRest/rest/RestAlumnos';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->request('GET', $uri);
    $alumnos = json_decode($response->getBody());

    foreach ($alumnos as $alumno)
    {
        echo "<tr id='".$alumno->id."'>\n"
        . "<td>".$alumno->nombre."</td>\n"
        . "<td>".formatear_fecha($alumno->fecha_nacimiento)."</td>\n"
        . "<td><input type='checkbox' "; if ($alumno->mayor_edad== 1){echo"checked";} echo"></td>\n"
        . "<td><button onclick=\"editar(".$alumno->id.", '".$alumno->nombre."', '".$alumno->fecha_nacimiento."', '".$alumno->mayor_edad."')\">Editar</td>\n"
        . "<td><button onclick=\"borrar(".$alumno->id.", '".$alumno->nombre."')\">Borrar</td>\n"
        . "</tr>\n\n";
    }
?>
    </table>



</body>
</html>
