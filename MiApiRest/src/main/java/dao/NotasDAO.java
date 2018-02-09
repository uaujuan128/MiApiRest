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
    
    public List<Nota> todos_los_alumnos() {
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
            sql = "SELECT ID, NOMBRE FROM ALUMNOS ORDER BY NOMBRE";
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
    
    public List<Nota> todas_las_asignaturas() {
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
            sql = "SELECT id, NOMBRE FROM ASIGNATURAS ORDER BY NOMBRE";
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
    
    
            
    public String insert_nota(Nota notasNuevas2)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="INSERT INTO NOTAS VALUES (?, ?, ?)";
            
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1,notasNuevas2.getId_alumno());
            pstm.setInt(2, notasNuevas2.getId_asignatura());
            pstm.setInt(3, notasNuevas2.getNota());
            
            filas = pstm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        if (filas == 1)
        {
            return "La nota se ha introducido correctamente";
        }
        else
        {
            return "La nota ya está puesta. Para borrar o editar ir arriba.";
        }
    }
    
    public int update_nota(Nota notasNuevas3)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="UPDATE NOTAS SET NOTA=? WHERE ID_ALUMNO = ? AND ID_ASIGNATURA=?";
            
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
