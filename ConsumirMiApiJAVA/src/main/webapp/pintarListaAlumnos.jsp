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
                fecha = formatDate(fecha);
                document.getElementById(id).innerHTML=
			'<td><input name="nombre'+id+'" type="text" value="'+nombre+'"></td>'+
			'<td><input name="fecha'+id+'" type="date" value='+fecha+' pattern="dd-MM-yyyy"></td>'+
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
                    mayor=false;
                }
                
                document.getElementById(id).innerHTML=
                        "<td>"+nombre+"</td>"+
                        "<td>"+formatDateInverso(fecha)+"</td>"+
                        "<td><input type='checkbox' "+verifica_check(mayor)+"></td>"+
                        "<td><button onclick=\"editar('"+id+"','"+nombre+"','"+formatDateInverso(fecha)+"',"+mayor+")\">editar</button></td>"+
                        "<td><button onclick=\"borrar('"+id+"','"+nombre+")\">borrar</button></td>";
                        
                var datos = "id="+id+"&nombre="+nombre+"&fecha="+fecha+"&mayor="+mayor+"&op=UPDATE";

                $.ajax({
                        type:'get',
                        url:'alumnos',
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
                            url:'alumnos',
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
                        url:'alumnos',
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
        <h1>Alumnos</h1>
        <br>
        <a href="asignaturas">asignaturas</a>
        <a href="notas">notas</a>
        <br>
        <p id="respuesta"></p>
        
        <table border="1">   
        <tr name="nuevo">
            <td><input type="text" name="nombre_insertar"></td>
            <td><input type="date" name="fecha_insertar" pattern="dd-MM-yyyy" ></td>
            <td><input type="checkbox" name="mayor_insertar"></td>
            <td colspan="2"><button onclick="insertar();">añadir elemento</button></td>
        </tr>
        <c:forEach items="${alumnos}" var="alumno">  
            <tr id="${alumno.id}" name="no_editado">
                
                <td>
                    ${alumno.nombre}
                </td>
   
                <td>
                    <fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>
                </td>

                <td>
                    <input type="checkbox" <c:if test="${alumno.mayor_edad}" >checked</c:if> />
                </td>
                <td>
                        <button onclick="editar('${alumno.id}','${alumno.nombre}','<fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>',${alumno.mayor_edad})">editar</button>
                </td>
                <td>
                        <button onclick="borrar('${alumno.id}','${alumno.nombre}')">borrar</button>
                </td>
            </tr>
        </c:forEach> 
        </table>
        
    </body>
</html>
