<%-- 
    Document   : ver_paradas
    Created on : 20-ene-2018, 10:43:11
    Author     : Juan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            td{
                padding: 6px;
            }
            th{
                padding: 6px;
            }
            div{
                padding: 0%;
            }
        </style>
    </head>
    <body>
       
        <div class="container">
        <div class="row">
                
            <div class="col-sm-4">
                  <h1>Alumnos</h1> 
                <table class="table table-hover">
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Fecha</th>
                        <th>Mayor</th>
                    </tr>
                <c:forEach items="${alumnos}" var="alumno"> 
                    <tr>
                        <td>${alumno.id}</td> 
                        <td>${alumno.nombre}</td>
                        <td>${alumno.fecha_nacimiento}</td>
                        <td>${alumno.mayor_edad}</td>
                </c:forEach>            
            </table>
                  </div></div>
    </body>
</html>
