package model;

public class Asignatura
{
    private long id;
    private String nombre;
    private String curso;
    private String ciclo;
    
    public Asignatura(){}
    
    public Asignatura(long id)
    {
        this.id = id;
        this.nombre = null;
        this.curso = null;
        this.ciclo = null;
    }
    
    public Asignatura(String nombre, String curso, String ciclo)
    {
        this.id = 0;
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
    }
    
    public Asignatura(long id, String nombre, String curso, String ciclo)
    {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Asignatura(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
}
