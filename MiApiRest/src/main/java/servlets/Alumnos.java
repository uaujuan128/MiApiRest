/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import servicios.AlumnosServicios;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/alumnos"})
public class Alumnos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

        AlumnosServicios as = new AlumnosServicios();
        String op = request.getParameter("op");
        if (op == null)
        {
            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            final JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory
                    = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer()
                    {
                        @Override
                        public void initialize(HttpRequest request)
                        {
                            request.setParser(new JsonObjectParser(JSON_FACTORY));
                        }
                    });
            ObjectMapper objectMapper = new ObjectMapper();
            GenericUrl url = new GenericUrl("http://localhost:8080/baseDatos/rest/cutre");
            HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
            //Obtener Json y parsearlo a lista
            List<Alumno> alumnos = objectMapper.readValue(
            requestGoogle.execute().parseAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));

            //Asignar variables y redirigir a .jsp
            request.setAttribute("alumnos", alumnos);
            request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);
        }
        else
        {
            switch (op) {
            case "GETALL":

                request.setAttribute("alumnos", as.getAllAlumnos());
                request.getRequestDispatcher("/pintarListaAlumnos.jsp").forward(request, response);
                break;
            case "INSERT":
                Alumno a = new Alumno();
                a.setNombre("NOMBRE NUEVO" + LocalDateTime.now().toString());
                LocalDate local = LocalDate.of(1910, Month.MARCH, 12);
                a.setFecha_nacimiento(Date.from(local.atStartOfDay().toInstant(ZoneOffset.UTC)));
                a.setMayor_edad(Boolean.FALSE);
                a = as.addAlumno(a);

                List<Alumno> alumnos = new ArrayList();
                alumnos.add(a);
                request.setAttribute("alumnos", alumnos);
                request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("alumnos", as.getAllAlumnos());
                request.getRequestDispatcher("pintarListaAlumnos.jsp").forward(request, response);
                break;
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
