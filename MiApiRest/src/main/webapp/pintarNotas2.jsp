<%-- 
    Document   : pintarNotas2
    Created on : 17-nov-2017, 9:15:49
    Author     : DAW
--%>
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
        <style>
            td{
                padding: 3px;
            }
            th{
                padding: 3px;
            }
        </style>
        <script src="js/jquery-3.2.1.js"></script>
        <script>
            function editar(id_alumno, nombre_asignatura, id_asignatura, nota)
            {
                document.getElementById(id_asignatura).innerHTML=
			'<td>'+nombre_asignatura+'</td>'+
                        '<td><input id="nota_editada" type="number" value="'+nota+'"></td>'+
			'<td><button onclick=\'guardar("'+id_alumno+'", "'+nombre_asignatura+'", "'+id_asignatura+'", "'+nota+'")\'>guardar</button></td>';
            }
            
            function guardar(id_alumno, nombre_asignatura, id_asignatura, nota)
            {
                nota = document.getElementById("nota_editada").value;
                
                document.getElementById(id_asignatura).innerHTML=
                        "<td>"+nombre_asignatura+"</td>"+
                        "<td>"+nota+"</td>"+
                        "<td><button onclick=\"editar('"+id_alumno+"','"+nombre_asignatura+"','"+id_asignatura+"',"+nota+")\">editar</button></td>";
                        
                var datos = "alumno="+id_alumno+"&asignatura="+id_asignatura+"&nota="+nota+"&op=UPDATE_NOTA";

                $.ajax({
                        type:'get',
                        url:'notas',
                        data:datos,
                        success:function(resp)
                        {
                                $("#respuesta").resp;
                        }
                });
                return false;
            }
        </script>
    </head>
    <body>
        <h2>NOTAS DEL ALUMNO</h2>
        <table border="1">
            <tr><th>Asignatura</th><th>Nota</th></tr>
            <c:forEach items="${asignaturas_de_alumno}" var="nota">
                <tr id='${nota.id_asignatura}'><td>${nota.nombre_asignatura}</td>
                    <td>${nota.nota}</td>
                    <td><button onclick="editar('${nota.id_alumno}','${nota.nombre_asignatura}', '${nota.id_asignatura}','${nota.nota}')">Editar</button></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
