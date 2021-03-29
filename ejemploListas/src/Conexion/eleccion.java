/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author vanes
 */
public class eleccion extends clsConexion {
    
    public ResultSet Ejecutar(String Sql) {

        abrirConexion();

        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(Sql);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch (SQLException e) {
            throw new IllegalStateException("No se puede ejecutar el  query: " + Sql, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    cerrarConexion();

                }
            } catch (SQLException e) {
                return null;
            }
        }

        return crs;

    }//end Seleccion
  
}
