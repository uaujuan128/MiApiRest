package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.NotasServicios;
import model.Nota;

@WebServlet(name = "Notas", urlPatterns = {"/notas"})
public class Notas extends HttpServlet {

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String op = request.getParameter("op");
        if (op == null)
        {
            op = "GET_INFORMACION";
        }

        switch (op) {
            case "GET_INFORMACION":
                NotasServicios as1 = new NotasServicios();
                request.setAttribute("alumnos_con_notas", as1.alumnos_con_notas());
                
                NotasServicios as2 = new NotasServicios();
                request.setAttribute("todos_los_alumnos", as2.todos_los_alumnos());
                
                NotasServicios as3 = new NotasServicios();
                request.setAttribute("todas_las_asignaturas", as3.todas_las_asignaturas());
                
                request.getRequestDispatcher("pintarNotas.jsp").forward(request, response);
                break;
            case "GET_ASIGNATURAS":
                NotasServicios as4 = new NotasServicios();
                
                int id_alumno = Integer.parseInt(request.getParameter("alumno"));
                Nota notasNuevas = new Nota(id_alumno);
                
                
                request.setAttribute("asignaturas_de_alumno", as4.asignaturas_de_alumno(notasNuevas));
                request.getRequestDispatcher("pintarNotas2.jsp").forward(request, response);
                break;
            case "INSERT_NOTA":
                NotasServicios as5 = new NotasServicios();
                
                int id_alumno2 = Integer.parseInt(request.getParameter("alumno"));
                int id_asignatura = Integer.parseInt(request.getParameter("asignatura"));
                int nota = Integer.parseInt(request.getParameter("nota"));
                Nota notasNuevas2 = new Nota(id_alumno2, id_asignatura, nota);
                
                
                response.getWriter().write(NotasServicios.insert_nota(notasNuevas2));
                break;
                
            case "UPDATE_NOTA":
                NotasServicios as6 = new NotasServicios();
                
                int id_alumno3 = Integer.parseInt(request.getParameter("alumno"));
                int id_asignatura2 = Integer.parseInt(request.getParameter("asignatura"));
                int nota2 = Integer.parseInt(request.getParameter("nota"));
                Nota notasNuevas3 = new Nota(id_alumno3, id_asignatura2, nota2);
                
                
                response.getWriter().write(NotasServicios.update_nota(notasNuevas3));
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
