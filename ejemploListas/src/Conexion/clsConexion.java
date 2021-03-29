/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
/**
 *
 * @author vanes
 */
import configuracion.clsPropiedades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import modelos.PropiedadesModel;

public class clsConexion {
    public Connection conn = null;
    private String basedatos="";
    private String user="";
    private String password="";
    
    public clsConexion(){
        PropiedadesModel prop = new clsPropiedades().cargarPropiedad();
        basedatos = prop.getBasedatos();
        user=prop.getUsuario();
        password = prop.getClave();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e){
            System.out.print("Error, con el driver"+e.getMessage());
        }
    }//constructor
    
    public String abrirConexion(){
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+basedatos,user,password);
            return "OK";
        } catch (SQLException e){
            System.out.print("Error en base de datos"+e.getMessage());
            return "error en conexion DB "+e.getMessage();
        }
    }//abrir conexion
    
    public String cerrarConexion(){
        try{
            conn.close();
            return "OK";
        } catch (SQLException e){
            System.out.print("Error en base de datos"+e.getMessage());
            return "error en conexion DB "+e.getMessage();
        }
    }//cerrar
    
    
    
     
}//clase
