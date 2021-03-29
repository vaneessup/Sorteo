/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.PropiedadesModel;
/**
 *
 * @author vanes
 */
public class clsPropiedades {
    private static final String pathConfiguracion="C:\\Users\\vanes\\OneDrive\\Escritorio\\5to.Semestre\\Progra\\ejemploListas (1)\\ejemploListas\\src\\configuracion\\configuracion.properties";
    //Cargamos las propiedades de la base de datos que estan en el archivo de properties 
    public PropiedadesModel cargarPropiedad(){
        PropiedadesModel prop = new PropiedadesModel();
        Properties p = new Properties();
        try {
            p.load(new FileReader(pathConfiguracion));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(clsPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(clsPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        prop.setBasedatos(p.getProperty("BaseDatos"));
        prop.setUsuario(p.getProperty("usuario"));
        prop.setClave(p.getProperty("clave"));
        return prop;
    } 
}
