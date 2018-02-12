package servicios;

import dao.NotasDAO;
import java.util.List;
import model.Alumno;
import model.Asignatura;
import model.Nota;

public class NotasServicios 
{

     public List<Nota> alumnos_con_notas()
    {
        NotasDAO dao = new NotasDAO();
        return dao.alumnos_con_notas();
    }
     public List<Nota> asignaturas_de_alumno(Nota notasNuevas)
    {
        NotasDAO dao = new NotasDAO();
        return dao.asignaturas_de_alumno(notasNuevas);
    }
     public List<Alumno> todos_los_alumnos()
    {
        NotasDAO dao = new NotasDAO();
        return dao.todos_los_alumnos();
    }
     public List<Asignatura> todas_las_asignaturas()
    {
        NotasDAO dao = new NotasDAO();
        return dao.todas_las_asignaturas();
    }
     
             
    public static int insert_nota (Nota notasNuevas2)
    {
        NotasDAO dao = new NotasDAO();
        return dao.insert_nota(notasNuevas2);
    }
    
    public static int update_nota (Nota notasNuevas3)
    {
        NotasDAO dao = new NotasDAO();
        return dao.update_nota(notasNuevas3);
    }
    
    public static List<Nota> consultar_nota (Nota nota)
    {
        NotasDAO dao = new NotasDAO();
        return dao.consultar_nota(nota);
    }
}
