<?php
   
    $mysqli = new mysqli('db4free.net:3307', 'oscarnovillo', 'c557ef',  'clasesdaw');
    $alumnos = $mysqli->query("SELECT * FROM ALUMNOS");
	$asignaturas = $mysqli->query("SELECT * FROM ASIGNATURAS");
    
    if (isset($_GET['alumno'])){$alumno = $_GET['alumno'];} else {$id=null;}
    if (isset($_GET['asignatura'])){$asignatura = $_GET['asignatura'];} else {$nombre=null;}
    if (isset($_GET['nota'])){$nota = $_GET['nota'];} else {$fecha=null;}
    if (isset($_GET['op'])){$op = $_GET['op'];} else {$op=null;}
    
    switch ($op)
    {
        case "GET_NOTA":
		
		try 
		{
			$nota = $mysqli->query("SELECT NOTA FROM NOTAS WHERE ID_ALUMNO=$alumno AND ID_ASIGNATURA=$asignatura");
			
			if ($nota->num_rows === 0)
				{
				
					echo "El alumno no tiene un nota puesta para esta asignatura. Introduce una: ";
					echo "<input type='number' id='poner_nota'><br>";
					echo "<button onclick=\"insertar(".$alumno.", ".$asignatura.")\">Introducir</button>";
				}
			
			while ($fila = $nota->fetch_row()) 
			{
				if (isset($fila[0]))
				{
					echo "El alumno tiene esta nota, puedes modificarla si quieres: ";
					echo "<input type='number' value='".$fila[0]."' id='poner_nota'><br>";
					echo "<button onclick=\"actualizar(".$alumno.", ".$asignatura.")\">Actualizar</button>";
				}
			}
			
				
		}
		catch(PDOException $e)
		{
			echo $sql . "<br>" . $e->getMessage();
		}

		$mysqli->close();
		break;
		
		case "INSERT_NOTA":
		$stmt = $mysqli->prepare("INSERT NOTAS (ID_ALUMNO, ID_ASIGNATURA, NOTA) VALUES (?, ?, ?)");
		$stmt->bind_param("iii", $alumno, $asignatura, $nota);
		$stmt->execute();
		
		
		if ($mysqli->errno != 0)
		{
			echo("Error description: " . mysqli_error($mysqli));
		}
		else
		{
			echo "Se ha añadido la nota: ".$nota.".";
		}  
		
		$mysqli->close();
        break;
		
		case "UPDATE_NOTA":
		
		$stmt = $mysqli->prepare("UPDATE NOTAS SET NOTA = ? WHERE ID_ALUMNO=? AND ID_ASIGNATURA=?");
		$stmt->bind_param("iii", $nota, $alumno, $asignatura);
		$stmt->execute();
		
		
		if ($mysqli->errno != 0)
		{
			echo("Error description: " . mysqli_error($mysqli));
		}
		else
		{
			echo "Se ha actualizado la nota: ".$nota.".";
		}  
		
		$mysqli->close();
        break;
		
    }
?>
