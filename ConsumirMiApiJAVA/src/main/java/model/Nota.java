
package model;
public class Nota 
{
    private int id_alumno;
    private int id_asignatura;
    private String nombre_alumno;
    private String nombre_asignatura;
    private int nota;

    public Nota(int id, String nombre) {
        this.id_asignatura = id;
        this.nombre_asignatura = nombre;
        this.id_alumno = id;
        this.nombre_alumno = nombre;
    }

    public Nota(String nombre_asignatura, int nota) {
        this.nombre_asignatura = nombre_asignatura;
        this.nota = nota;
    }

    public Nota(int id_alumno, int id_asignatura, int nota)
    {
        this.id_alumno = id_alumno;
        this.id_asignatura = id_asignatura;
        this.nota = nota;
    }
    public Nota(int id_alumno, int id_asignatura)
    {
        this.id_alumno = id_alumno;
        this.id_asignatura = id_asignatura;
    }

    public Nota(int id_alumno, String nombre_alumno, int id_asignatura, String nombre_asignatura, int nota) {
        this.id_alumno = id_alumno;
        this.id_asignatura = id_asignatura;
        this.nombre_alumno = nombre_alumno;
        this.nombre_asignatura = nombre_asignatura;
        this.nota = nota;
    }
    
    

    public Nota(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Nota()
    {
        
    }
    
    

    /**
     * @return the id_alumno
     */
    public int getId_alumno() {
        return id_alumno;
    }

    /**
     * @param id_alumno the id_alumno to set
     */
    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    /**
     * @return the id_asignatura
     */
    public int getId_asignatura() {
        return id_asignatura;
    }

    /**
     * @param id_asignatura the id_asignatura to set
     */
    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    /**
     * @return the nombre_alumno
     */
    public String getNombre_alumno() {
        return nombre_alumno;
    }

    /**
     * @param nombre_alumno the nombre_alumno to set
     */
    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    /**
     * @return the nombre_asignatura
     */
    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    /**
     * @param nombre_asignatura the nombre_asignatura to set
     */
    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    /**
     * @return the nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

}
