<?php
   
    require 'vendor/autoload.php';

    use GuzzleHttp\Client;
    use GuzzleHttp\Exception\ClientException;

    $client = new Client();

    $uri = 'http://localhost:8080/baseDatos/rest/RestAlumnos';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));

    
    if (isset($_GET['id'])){$id = $_GET['id'];} else {$id=null;}
    if (isset($_GET['nombre'])){$nombre = $_GET['nombre'];} else {$nombre=null;}
    if (isset($_GET['fecha'])){$fecha = $_GET['fecha'];} else {$fecha=null;}
    if (isset($_GET['mayor'])){$mayor = $_GET['mayor']; } else {$mayor=null;}
    if (isset($_GET['op'])){$op = $_GET['op'];} else {$op=null;}
    
    $alumno = new \stdClass;
    
    
    switch ($op) {
    case "UPDATE":
        $stmt = $mysqli->prepare("UPDATE ALUMNOS SET NOMBRE=?, FECHA_NACIMIENTO=?, MAYOR_EDAD=? WHERE ID=?");
        $stmt->bind_param("sssi", $nombre, $fecha, $mayor, $id);
        $stmt->execute();

        if ($mysqli->errno != 0) {
            echo "Ha habido un error";
        } else {
            echo "El usuario con id:" . $id . " ha sido actualizado correctamente";
        }
        break;

    case "DELETE":
        $alumno->id = $id;

        $uri = 'http://localhost:8080/MiApiRest/rest/RestAlumnos';
        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->delete($uri, ['query' => ['alumno' => json_encode($alumno), 'borrar_notas' => "no"]]);
        $alumnos = json_decode($request->getBody());
        echo $alumnos;
        break;

    case "DELETE_CASCADE":
        $alumno->id = $id;

        $uri = 'http://localhost:8080/MiApiRest/rest/RestAlumnos';
        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->delete($uri, ['query' => ['alumno' => json_encode($alumno), 'borrar_notas' => "si"]]);
        $alumnos = json_decode($request->getBody());
        echo $alumnos;
        break;

    case "INSERT":
        
        
        $alumno->nombre = $nombre;
        $alumno->fecha_nacimiento = $fecha;
        $alumno->mayor_edad = $mayor;
        
        $uri = 'http://localhost:8080/MiApiRest/rest/RestAlumnos';
        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->request('PUT', $uri, ['query' => ['alumno' => json_encode($alumno)]]);
        $alumnos = json_decode($request->getBody());
        echo $alumnos;
        break;
}
?>
