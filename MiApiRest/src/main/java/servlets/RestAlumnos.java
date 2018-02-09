/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.GenericJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import model.ErrorHttp;
import servicios.AlumnosServicios;
import util.PasswordHash;

/**
 *
 * @author user
 */
@WebServlet(name = "RestAlumnos", urlPatterns = {"/rest/RestAlumnos"})
public class RestAlumnos extends HttpServlet {

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
        AlumnosServicios a = new AlumnosServicios();
        List<Alumno> alumnos = a.getAllAlumnos();
        request.setAttribute("json", alumnos);
    }

    
    
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
       
        AlumnosServicios as = new AlumnosServicios();
        Alumno alumno = (Alumno) request.getAttribute("alumno");
        
        if(request.getAttribute("alumno") == "si")
        {
             request.setAttribute("json", as.deleteCascadeAlumno(alumno));
        }
        
        
        request.setAttribute("json", as.deleteAlumno(alumno));
        
        
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alumno a = (Alumno) req.getAttribute("alumno");

        a.setNombre("PUT");
        req.setAttribute("json", a);
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
        Alumno a = (Alumno) request.getAttribute("alumno");

        a.setNombre("conseguido");
        request.setAttribute("json", a);
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
