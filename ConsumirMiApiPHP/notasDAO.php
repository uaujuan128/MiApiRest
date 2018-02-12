<?php
   
    require 'vendor/autoload.php';

    use GuzzleHttp\Client;
    use GuzzleHttp\Exception\ClientException;

    $client = new Client();

    $uri = 'http://localhost:8080/MiApiRest/rest/RestNotas';
    
    if (isset($_GET['alumno'])){$alumno = $_GET['alumno'];} else {$id=null;}
    if (isset($_GET['asignatura'])){$asignatura = $_GET['asignatura'];} else {$nombre=null;}
    if (isset($_GET['nota'])){$nota = $_GET['nota'];} else {$fecha=null;}
    if (isset($_GET['op'])){$op = $_GET['op'];} else {$op=null;}
    
    $notas = new \stdClass;
    
    switch ($op) {
    case "GET_NOTA":

        $notas->id_alumno = $alumno;
        $notas->id_asignatura = $asignatura;

        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->request('GET', $uri, ['query' => ['nota' => json_encode($notas), 'accion' => 'comprobar']]);
        $nota = json_decode($request->getBody());
        $entrar = false;
        foreach ($nota as $calificacion) {
            if ($calificacion->nota != null) {
                echo "El alumno tiene esta nota, puedes modificarla si quieres: ";
                echo "<input type='number' value='" . $calificacion->nota . "' id='poner_nota'><br>";
                echo "<button onclick=\"actualizar(" . $alumno . ", " . $asignatura . ")\">Actualizar</button>";
            }
            $entrar = true;
        }

        if ($entrar == false) {
            echo "El alumno no tiene un nota puesta para esta asignatura. Introduce una: ";
            echo "<input type='number' id='poner_nota'><br>";
            echo "<button onclick=\"insertar(" . $alumno . ", " . $asignatura . ")\">Introducir</button>";
        }
        break;

    case "INSERT_NOTA":

        $notas->id_alumno = $alumno;
        $notas->id_asignatura = $asignatura;
        $notas->nota = $nota;

        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->request('PUT', $uri, ['query' => ['nota' => json_encode($notas)]]);
        $nota = json_decode($request->getBody());

        if ($nota != 1)
        {
                echo("Ha habido un error al añadir la nota");
        }
        else
        {
                echo "Se ha añadido la nota: ".$nota.".";
        }  
        break;

    case "UPDATE_NOTA":

        $notas->id_alumno = $alumno;
        $notas->id_asignatura = $asignatura;
        $notas->nota = $nota;

        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->request('POST', $uri, ['query' => ['nota' => json_encode($notas)]]);
        $nota2 = json_decode($request->getBody());

        if ($nota2 != 1)
        {
                echo("Ha habido un error al añadir la nota");
        }
        else
        {
                echo "Se ha añadido la nota: ".$nota.".";
        }  
        break;
}
?>
