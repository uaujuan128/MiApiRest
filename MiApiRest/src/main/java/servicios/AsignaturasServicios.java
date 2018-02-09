package servicios;

import dao.AsignaturasDAO;
import java.util.List;
import model.Asignatura;

public class AsignaturasServicios
{
    public List<Asignatura> obternerAsignaturas()
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.obternerAsignaturas();
    }
    
    public int updateAsignatura (Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.actualizarAsignatura(asignaturaNuevo);
    }
    
    public int deleteAsignatura (Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.borrarAsignatura(asignaturaNuevo);
    }
    
    public int insertAlumno(Asignatura asignaturaNuevo)
    {
        AsignaturasDAO dao = new AsignaturasDAO();
        return dao.insertarAsignatura(asignaturaNuevo);
    }
}
