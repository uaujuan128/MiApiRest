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
import servicios.AlumnosServicios;
import servicios.AsignaturasServicios;

/**
 *
 * @author user
 */
@WebServlet(name = "RestAsignaturas", urlPatterns = {"/rest/RestAsignaturas"})
public class RestAsignaturas extends HttpServlet {

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
        AsignaturasServicios a = new AsignaturasServicios();
        List<Asignatura> asignaturas = a.obternerAsignaturas();
        request.setAttribute("json", asignaturas);
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
        AsignaturasServicios as = new AsignaturasServicios();
        Asignatura asignatura = (Asignatura) request.getAttribute("asignatura");
        request.setAttribute("json", as.insertAsignatura(asignatura));

        
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
        AsignaturasServicios as = new AsignaturasServicios();
        Asignatura asignatura = (Asignatura) request.getAttribute("asignatura");
        request.setAttribute("json", as.updateAsignatura(asignatura));
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
