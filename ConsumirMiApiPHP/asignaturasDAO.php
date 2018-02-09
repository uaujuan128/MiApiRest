<?php
   
    $mysql = new PDO('mysql:host=db4free.net:3307;dbname=clasesdaw', 'oscarnovillo', 'c557ef');
    
    if (isset($_GET['id'])){$id = $_GET['id'];} else {$id=null;}
    if (isset($_GET['nombre'])){$nombre = $_GET['nombre'];} else {$nombre=null;}
    if (isset($_GET['curso'])){$curso = $_GET['curso'];} else {$fecha=null;}
    if (isset($_GET['ciclo'])){$ciclo = $_GET['ciclo']; } else {$mayor=null;}
    if (isset($_GET['op'])){$op = $_GET['op'];} else {$op=null;}
    
    switch ($op)
    {
        case "UPDATE":
		
		try 
		{
			$stmt = $mysql->prepare("UPDATE ASIGNATURAS SET NOMBRE=?, CURSO=?, CICLO=? WHERE ID = ?");
			$stmt->bindParam(1, $nombre);
			$stmt->bindParam(2, $curso);
			$stmt->bindParam(3, $ciclo);
			$stmt->bindParam(4, $id);
			$stmt->execute();

			// echo a message to say the UPDATE succeeded
			echo "El usuario con id ".$id." se ha actualizado correctamente";
		}
		catch(PDOException $e)
		{
			echo $sql . "<br>" . $e->getMessage();
		}

		$conn = null;
		break;
		
		case "DELETE":
		try 
		{
				$stmt = $mysql->prepare("DELETE FROM ASIGNATURAS WHERE ID=?");
				$stmt->bindParam(1, $id);
				$stmt->execute();
				
			echo $stmt->rowCount();
		}
		catch(PDOException $e)
		{
			echo $sql . "<br>" . $e->getMessage();
		}
            
        break;
		
		case "DELETE_CASCADE":
		$stmt = $mysql->prepare("DELETE FROM NOTAS WHERE ID_ASIGNATURA = ?");
		$stmt->bindParam(1, $id);
		$stmt->execute();
		
		$stmt2 = $mysql->prepare("DELETE FROM ASIGNATURAS WHERE ID = ?");
		$stmt2->bindParam(1, $id);
		$stmt2->execute();
		echo $stmt->rowCount();
		break;
		
		case "INSERT":
		$stmt = $mysql->prepare("INSERT ASIGNATURAS (NOMBRE, CURSO, CICLO)  VALUES (?, ?, ?)");
		$stmt->bindParam(1, $nombre);
		$stmt->bindParam(2, $curso);
		$stmt->bindParam(3, $ciclo);
		$stmt->execute();
		echo $stmt->rowCount();

    }
?>
