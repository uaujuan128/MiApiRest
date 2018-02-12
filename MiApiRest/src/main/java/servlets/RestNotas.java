/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.Asignatura;
import model.Nota;
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;
import servicios.NotasServicios;

/**
 *
 * @author user
 */
@WebServlet(name = "RestNotas", urlPatterns = {"/rest/RestNotas"})
public class RestNotas extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
//        List<Alumno> alumnos = new ArrayList<>();
//        Alumno alumno = new Alumno();
//        alumno.setNombre("Juan");
//        alumnos.add(alumno);
//        alumno = new Alumno();
//        alumno.setNombre("KIKO");
//        alumnos.add(alumno);
//        request.setAttribute("json", alumnos);
        NotasServicios a = new NotasServicios();
        Nota nota = (Nota) request.getAttribute("nota");
        
        if("alumnos".equals(request.getParameter("accion")))
        {
            List<Alumno> alumnos = a.todos_los_alumnos();
            request.setAttribute("json", alumnos);
        }
        else if("asignaturas".equals(request.getParameter("accion")))
        {
            List<Asignatura> asignaturas = a.todas_las_asignaturas();    
            request.setAttribute("json", asignaturas);
        }
        else if("comprobar".equals(request.getParameter("accion")))
        {
            request.setAttribute("json", a.consultar_nota(nota));
        }
    }

    
    
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
       
        AsignaturasServicios as = new AsignaturasServicios();
        Asignatura asignatura = (Asignatura) request.getAttribute("asignatura");
        
        String respuesta = request.getParameter("borrar_notas");
        
        if (asignatura.getNombre() == null)
        {
            if(respuesta.equals("si"))
            {
                 request.setAttribute("json", as.deleteCascadeAsignatura(asignatura));
            }
            else
            {
                request.setAttribute("json", as.deleteAsignatura(asignatura));
            }
        }
        
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        NotasServicios a = new NotasServicios();
        Nota nota = (Nota) request.getAttribute("nota");
        request.setAttribute("json", a.insert_nota(nota));

        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        NotasServicios a = new NotasServicios();
        Nota nota = (Nota) request.getAttribute("nota");
        request.setAttribute("json", a.update_nota(nota));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
