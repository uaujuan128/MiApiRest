
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function poner_nota()
            {
                var alumno = document.getElementById("alumno").value;
                var asignatura = document.getElementById("asignatura").value;
                var nota = document.getElementById("nota").value;
                
                var datos = "alumno="+alumno+"&asignatura="+asignatura+"&nota="+nota+"&op=INSERT_NOTA";

                $.ajax({
                        type:'get',
                        url:'notas',
                        data:datos,
                        success:function(resp)
                        {
                                $("#resultado").resp;
                        }
                });
            }
        </script>
    </head>
    <body>
        <h1>Notas</h1>
        <br>
        <a href="alumnos">alumnos</a>
        <a href="asignaturas">asignaturas</a>
        <br>
        <h4>Seleccione un alumno para editar sus notas: (solo se muestran alumnos con notas):</h4>
        
        <form action="/baseDatos/notas" method="get">
            <select name="alumno">
                <c:forEach items="${alumnos_con_notas}" var="notas">
                    <option value="${notas.id_alumno}">${notas.nombre_alumno}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="op" value="GET_ASIGNATURAS">
            <input type="submit" value="enviar">
        </form>
        
        <h4>Poner notas nuevas(si la nota ya existe no se creará, tendrá que editarse arriba.):</h4>
        
        <form action="/baseDatos/notas" method="get">
        Alumnos:
        <select name="alumno">
            <c:forEach items="${todos_los_alumnos}" var="nota">
                <option value="${nota.id_alumno}">${nota.nombre_alumno}</option>
            </c:forEach>
        </select>
        
        <br>Asignaturas:
        
        <select name="asignatura">
            <c:forEach items="${todas_las_asignaturas}" var="nota">
                <option value="${nota.id_asignatura}">${nota.nombre_asignatura}</option>
            </c:forEach>
        </select>
        <br>Nota: <input type="number" name="nota">
        <input type="hidden" name="op" value="INSERT_NOTA">
        <button onclick="poner_nota()">Poner nota</button>
        </form>
        
        <br><p id="resultado"></p>
    </body>
</html>
