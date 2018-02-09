<%-- 
    Document   : pintarListaAsignaturas
    Created on : 10-nov-2017, 9:16:59
    Author     : DAW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery-3.2.1.js"></script>
        <script>
            
            function editar(id,nombre,curso,ciclo)
            {
                document.getElementById(id).innerHTML=
			'<td><input name="nombre'+id+'" type="text" value="'+nombre+'"></td>'+
			'<td><input name="curso'+id+'" type="text" value="'+curso+'"></td>'+
			'<td><input name="ciclo'+id+'" type="text" value="'+ciclo+'"></td>'+
			'<td><button onclick="guardar('+id+')">guardar</button></td>';
            }
            function guardar(id)
            {
                var nombre = document.getElementsByName("nombre"+id)[0].value;
                var curso = document.getElementsByName("curso"+id)[0].value;
                var ciclo = document.getElementsByName("ciclo"+id)[0].value;
                
                
                document.getElementById(id).innerHTML=
                        "<td>"+nombre+"</td>"+
                        "<td>"+curso+"</td>"+
                        "<td>"+ciclo+"</td>"+
                        "<td><button onclick=\"editar('"+id+"','"+nombre+"','"+curso+"','"+ciclo+"')\">editar</button></td>"+
                        "<td><button onclick=\"borrar('"+id+"','"+nombre+")\">borrar</button></td>";
                        
                var datos = "id="+id+"&nombre="+nombre+"&curso="+curso+"&ciclo="+ciclo+"&op=UPDATE";
                
                $.ajax({
                        type:'get',
                        url:'Asignaturas',
                        data:datos,
                        success:function(resp)
                        {
                                $("#respuesta").resp;
                        }
                });
                return false;
            }
            
            function borrar(id, nombre)
            {
                var confirmacion = prompt("Seguro que quieres eliminar al usuario: "+nombre+"? (s/n)");
                
                if(confirmacion == "s")
                {
                    var fila = document.getElementById(id);
                    fila.parentNode.removeChild(fila);
                
                
                    var datos = "id="+id+"&op=DELETE";
                    $.ajax({
                            type:'get',
                            url:'Asignaturas',
                            data:datos,
                            success:function(resp)
                            {
                                    $("#respuesta").resp;
                            }
                    });
                    return false;
                }
            }
            
            function insertar()
            {
                var nombre_insertar = document.getElementsByName("nombre_insertar")[0].value;
                var curso_insertar = document.getElementsByName("curso_insertar")[0].value;
                var ciclo_insertar = document.getElementsByName("ciclo_insertar")[0].value;
                
                
                var datos = "nombre="+nombre_insertar+"&curso="+curso_insertar+"&ciclo="+ciclo_insertar+"&op=INSERT";
                $.ajax({
                        type:'get',
                        url:'Asignaturas',
                        data:datos,
                        success:function(resp)
                        {
                                $("#respuesta").html(resp);
                        }
                });
                
            }
            
            function recargar()
            {
                setTimeout(document.location.href = document.location.href, 6000);
            }
            </script>
    </head>
    <body>
        <h1>Asignaturas</h1>
        <br>
        <a href="alumnos">alumnos</a>
        <a href="notas">notas</a>
        <br>
        <p id="respuesta"></p>
        
        <table border="1"> 
            
        <tr name="nuevo">
            <td><input type="text" name="nombre_insertar"></td>
            <td><input type="text" name="curso_insertar"></td>
            <td><input type="text" name="ciclo_insertar"></td>
            <td colspan="2"><button onclick="insertar();">añadir elemento</button></td>
        </tr>
        
        <c:forEach items="${asignaturas}" var="asignatura">  
            <tr id="${asignatura.id}">
                
                <td>
                    ${asignatura.nombre}
                </td>
   
                <td>
                     ${asignatura.curso}
                </td>

                <td>
                     ${asignatura.ciclo}
                </td>
                <td>
                        <button onclick="editar('${asignatura.id}','${asignatura.nombre}', '${asignatura.curso}', '${asignatura.ciclo}')">editar</button>
                </td>
                <td>
                        <button onclick="borrar('${asignatura.id}','${asignatura.nombre}')">borrar</button>
                </td>
            </tr>
        </c:forEach> 
        </table>
        
    </body>
</html>
