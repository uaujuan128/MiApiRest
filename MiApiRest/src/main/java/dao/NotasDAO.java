package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;
import model.Asignatura;
import model.Nota;

public class NotasDAO 
{
    public List<Nota> alumnos_con_notas() {
        List<Nota> lista = new ArrayList<>();
        Nota nuevo = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "SELECT DISTINCT(A.ID), NOMBRE FROM ALUMNOS A JOIN NOTAS N ON A.ID = N.ID_ALUMNO ORDER BY NOMBRE";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                nuevo = new Nota(rs.getInt("id"), rs.getString("nombre"));
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;
    }
    
    public List<Nota> asignaturas_de_alumno(Nota notasNuevas) {
        List<Nota> lista = new ArrayList<>();
        Nota nuevo = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            con = db.getConnection();
            String sql = null;
            stmt = con.prepareStatement("SELECT A.ID, A.NOMBRE, S.ID AS ID_ASIGNATURA, S.NOMBRE AS NOMBRE_ASIGNATURA, N.NOTA FROM ALUMNOS A JOIN NOTAS N ON A.ID = N.ID_ALUMNO JOIN ASIGNATURAS S ON N.ID_ASIGNATURA=S.id WHERE A.ID = ?");
            
           
            stmt.setInt(1, notasNuevas.getId_alumno());
            rs = stmt.executeQuery();
            
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                nuevo = new Nota(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_asignatura"), rs.getString("nombre_asignatura"), rs.getInt("nota"));
                lista.add(nuevo);
            }
        } catch (Exception ex) {          
              String excepcion = "Message:  " + ex.getMessage(); 
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;
    }
    
    public List<Alumno> todos_los_alumnos() {
        List<Alumno> lista = new ArrayList<>();
        Alumno nuevo = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "select id, nombre from alumnos order by nombre";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                nuevo = new Alumno(rs.getLong("id"), rs.getString("nombre"));
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;
    }
    
    public List<Asignatura> todas_las_asignaturas() {
        List<Asignatura> lista = new ArrayList<>();
        Asignatura nuevo = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "select id, nombre from asignaturas order by nombre";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                nuevo = new Asignatura(rs.getLong("id"), rs.getString("nombre"));
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;
    }
    
    public List<Nota> consultar_nota(Nota nota) {
        List<Nota> lista = new ArrayList<>();
        Nota nuevo = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            String sql;
            stmt = con.prepareStatement("select nota from notas where id_alumno =? and id_asignatura = ?");
            stmt.setInt(1, nota.getId_alumno());
            stmt.setInt(2, nota.getId_asignatura());
            rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                nuevo = new Nota();
                nuevo.setNota(rs.getInt("nota"));
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;
    }
    
    
            
    public int insert_nota(Nota notasNuevas2)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="insert into notas values (?, ?, ?)";
            
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1, notasNuevas2.getId_alumno());
            pstm.setInt(2, notasNuevas2.getId_asignatura());
            pstm.setInt(3, notasNuevas2.getNota());
            
            filas = pstm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int update_nota(Nota notasNuevas3)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="update notas set nota=? where id_alumno = ? and id_asignatura=?";
            
            pstm = con.prepareStatement(sql);
            
            
            pstm.setInt(1, notasNuevas3.getNota());
            pstm.setInt(2, notasNuevas3.getId_alumno());
            pstm.setInt(3, notasNuevas3.getId_asignatura());
            
            filas = pstm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
}
