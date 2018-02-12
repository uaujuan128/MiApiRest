<?php

    require 'vendor/autoload.php';

    use GuzzleHttp\Client;
    use GuzzleHttp\Exception\ClientException;

    $client = new Client();

    $uri = 'http://localhost:8080/MiApiRest/rest/RestNotas';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));

    if (isset($_GET['id'])) 
    {
        $id = $_GET['id'];
    } 
    else 
    {
        $id = null;
    }
    
    if (isset($_GET['nombre'])) 
    {
        $nombre = $_GET['nombre'];
    } 
    else 
    {
        $nombre = null;
    }
    
    if (isset($_GET['curso'])) 
    {
        $curso = $_GET['curso'];
    } 
    else 
    {
        $curso = null;
    }
    
    if (isset($_GET['ciclo'])) 
    {
        $ciclo = $_GET['ciclo'];
    } 
    else 
    {
        $ciclo = null;
    }
    
    if (isset($_GET['op'])) 
    {
        $op = $_GET['op'];
    } 
    else 
    {
        $op = null;
    }

    $asignatura = new \stdClass;

    switch ($op) {
        case "UPDATE":

            $asignatura->id= $id;
            $asignatura->nombre = $nombre;
            $asignatura->curso = $curso;
            $asignatura->ciclo = $ciclo;

            //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
            $request = $client->request('POST', $uri, ['query' => ['asignatura' => json_encode($asignatura)]]);
            $asignaturas = json_decode($request->getBody());
            echo $asignaturas;
            break;
        
        case "DELETE":
            $asignatura->id= $id;

            //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
            $request = $client->delete($uri, ['query' => ['asignatura' => json_encode($asignatura), 'borrar_notas' => "no"]]);
            $asignaturas = json_decode($request->getBody());
            echo $asignaturas;
            break;
        
        case "DELETE_CASCADE":
            $asignatura->id= $id;

            //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
            $request = $client->delete($uri, ['query' => ['asignatura' => json_encode($asignatura), 'borrar_notas' => "si"]]);
            $asignaturas = json_decode($request->getBody());
            echo $asignaturas;
            break;

        case "INSERT":
            $asignatura->nombre = $nombre;
            $asignatura->curso = $curso;
            $asignatura->ciclo = $ciclo;

            //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
            $request = $client->request('PUT', $uri, ['query' => ['asignatura' => json_encode($asignatura)]]);
            $asignaturas = json_decode($request->getBody());
            echo $asignaturas;
            break;
    }
?>
