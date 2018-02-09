package servlets;

import java.io.IOException;
import static java.lang.Long.parseLong;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Asignatura;
import servicios.AsignaturasServicios;

@WebServlet(name = "Asignaturas", urlPatterns =
{
    "/asignaturas"
})
public class Asignaturas extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String op = request.getParameter("op");
        if (op == null)
        {
            op = "GETALL";
        }
        
        switch (op) {
            case "GETALL":
                AsignaturasServicios as = new AsignaturasServicios();
                request.setAttribute("asignaturas", as.obternerAsignaturas());
                request.getRequestDispatcher("pintarListaAsignaturas.jsp").forward(request, response);
                break;  
                
            case "UPDATE":
                Asignatura asignaturaNuevo = new Asignatura();
                asignaturaNuevo.setId(parseLong(request.getParameter("id"), 10));
                asignaturaNuevo.setNombre(request.getParameter("nombre"));
                asignaturaNuevo.setCurso(request.getParameter("curso"));
                asignaturaNuevo.setCiclo(request.getParameter("ciclo"));
                
                
                AsignaturasServicios asignaturaServicio = new AsignaturasServicios();
                response.getWriter().print(asignaturaServicio.updateAsignatura(asignaturaNuevo));
                break;
            
            case "DELETE":
                Asignatura asignaturaNuevo2 = new Asignatura(parseLong(request.getParameter("id"), 10));
                AsignaturasServicios asignaturaServicio2 = new AsignaturasServicios();
                asignaturaServicio2.deleteAsignatura(asignaturaNuevo2);
                break;
                
            case "INSERT":
                Asignatura asignaturaNuevo3 = new Asignatura(request.getParameter("nombre"), request.getParameter("curso"), request.getParameter("ciclo"));
                
                AsignaturasServicios asignaturaServicio3 = new AsignaturasServicios();
                response.getWriter().print(asignaturaServicio3.insertAlumno(asignaturaNuevo3));
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
