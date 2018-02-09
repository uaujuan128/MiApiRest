/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Asignatura;

/**
 *
 * @author oscar
 */
public class AsignaturasDAO {

    public List<Asignatura> obternerAsignaturas() {
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
            sql = "SELECT * FROM ASIGNATURAS";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                String curso = rs.getString("curso");
                String ciclo = rs.getString("ciclo");
                nuevo = new Asignatura(id, nombre, curso, ciclo);
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
    
    public int actualizarAsignatura(Asignatura u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="UPDATE ASIGNATURAS SET NOMBRE=?, CURSO=?, CICLO=? WHERE ID=?;";
            
            pstm = con.prepareStatement(sql);
            
            String nombre = u.getNombre();
            String curso = u.getCurso();
            String ciclo = u.getCiclo();
            long id = u.getId();
            
            pstm.setString(1, nombre);
            pstm.setString(2, curso);
            pstm.setString(3, ciclo);
            pstm.setLong(4, id);
            
            filas = pstm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int borrarAsignatura(Asignatura u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="delete from ASIGNATURAS where id=?;";
            
            pstm = con.prepareStatement(sql);
            
            pstm.setLong(1, u.getId());
            
            filas = pstm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int insertarAsignatura(Asignatura u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        PreparedStatement pstm = null;
        int filas = 0;
        
        try {
            con = db.getConnection();
            
            String sql="INSERT INTO ASIGNATURAS (NOMBRE, CURSO, CICLO) VALUES (?, ?, ?);";
            
            pstm = con.prepareStatement(sql);
            
            String nombre = u.getNombre();
            String curso = u.getCurso();
            String ciclo = u.getCiclo();
            
            pstm.setString(1, nombre);
            pstm.setString(2, curso);
            pstm.setString(3, ciclo);
            
            filas = pstm.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
}
