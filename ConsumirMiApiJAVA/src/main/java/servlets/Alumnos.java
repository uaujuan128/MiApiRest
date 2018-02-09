package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Long.parseLong;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import servicios.AlumnosServicios;

@WebServlet(name = "Alumnos", urlPatterns = {"/alumnos"})
public class Alumnos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        String op = request.getParameter("alumno");
        if (op == null)
        {
            op = "GETALL";
        }
        
        switch (op) {
            case "GETALL":
                AlumnosServicios as = new AlumnosServicios();
                request.setAttribute("alumnos", as.getAllAlumnos());
                request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);
                break;
            case "UPDATE":
                Alumno alumnoNuevo = new Alumno();
                alumnoNuevo.setId(parseLong(request.getParameter("id"), 10));
                alumnoNuevo.setNombre(request.getParameter("nombre"));
                
                String fecha = request.getParameter("fecha");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNacimiento = LocalDate.parse(fecha, dtf);
                alumnoNuevo.setFecha_nacimiento(Date.from(fechaNacimiento.atStartOfDay().toInstant(ZoneOffset.UTC)));
                
                alumnoNuevo.setMayor_edad(parseBoolean(request.getParameter("mayor")));
                AlumnosServicios alumnoServicio = new AlumnosServicios();
                response.getWriter().print(alumnoServicio.updateAlumno(alumnoNuevo));
                break;
                
            case "INSERT":
                String fecha2 = request.getParameter("fecha");
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNacimiento2 = LocalDate.parse(fecha2, dtf2);
                
                Alumno alumnoNuevo3 = new Alumno(request.getParameter("nombre"), Date.from(fechaNacimiento2.atStartOfDay().toInstant(ZoneOffset.UTC)), parseBoolean(request.getParameter("mayor")));
                
                AlumnosServicios alumnoServicio3 = new AlumnosServicios();
                response.getWriter().print(alumnoServicio3.insertAlumno(alumnoNuevo3));
                break;  
                
            case "DELETE":
                Alumno alumnoNuevo2 = new Alumno(parseLong(request.getParameter("id"), 10));
                AlumnosServicios alumnoServicio2 = new AlumnosServicios();
                alumnoServicio2.deleteAlumno(alumnoNuevo2);
                break;     
        }
    }

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
        try
        {
            processRequest(request, response);
        } catch (ParseException ex)
        {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try
        {
            processRequest(request, response);
        } catch (ParseException ex)
        {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
