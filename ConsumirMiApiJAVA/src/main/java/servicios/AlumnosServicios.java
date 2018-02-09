package servicios;

import dao.AlumnosDAO;
import java.util.List;
import model.Alumno;
public class AlumnosServicios {
    
    
    public List<Alumno> getAllAlumnos()
    {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.getAllAlumnosJDBC();
    }
    
    public int insertAlumno(Alumno alumnoNuevo)
    {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.insertarAlumno(alumnoNuevo);
    }
    
    public int updateAlumno (Alumno alumnoNuevo)
    {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.actualizarAlumno(alumnoNuevo);
    }
    public int deleteAlumno (Alumno alumnoNuevo)
    {
        AlumnosDAO dao = new AlumnosDAO();
        return dao.borrarAlumno(alumnoNuevo);
    }
}
