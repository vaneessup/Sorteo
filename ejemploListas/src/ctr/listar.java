/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import Conexion.eleccion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import modelos.modelName;

/**
 *
 * @author vanes
 */
public class listar extends modelName{

    eleccion elec = new eleccion();
    public void sorteo() throws IOException, SQLException {
        int contador = 100;
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(System.in));
        String igualar = "";
        String respose = "";
        //Igualamos un String a el Array de listar
        for (int i = 0; i < totalFilas; i++) {
            //miLista = new ArrayList<>(Arrays.asList(lista[i].getName()));
            igualar += lista[i].getName() + ",";
            igualar = igualar.replace("[", "").replace("]", "").replace("null", "");
        }
        //Igualamos una variable tipo List a la cadena de texto, utiliza un Split para separar cada uno de los nombres
        List<String> myList = new ArrayList<>(Arrays.asList(igualar.split(",")));

        do {
            System.out.println("\n\t\tMENU");
            System.out.println("\n 1). Realizar sorteo.");
            System.out.println("\n 2). Ver participantes.");
            System.out.println("\n 0). Salir\n");
            respose = br.readLine();
            if (!"1".equals(respose) && !"2".equals(respose) && !"1".equals(respose) && !"0".equals(respose)) {
                System.out.println("\n ELECCION ERRONEA, VUELVE A ELEGIR.\n");
                respose = br.readLine();
            }
            try {
                if ("1".equals(respose)) {
                    //hacemos un shuffle para mostrar un orden de los nombres aleatorios.
                    Collections.shuffle(myList);
                    System.out.print("\nEligiendo a los ganadores, espera un momento.");
                    for(int i = 0; i < 4; i++){
                        System.out.print(".");
                        Thread.sleep(1 * 2000);
                    }
                    System.out.println("\n***GANADORES***");
                    for (int i = 0; i < 10; i++) {
                        //Imprimimos en consola nombre por nombre de los 10 lugares 
                        String win = myList.get(i);
                        System.out.println("\n-|Lugar #" + (i + 1) + ": " + win);
                        contador--;
                        //vamos eliminando los nombres en esa posición de la lista para que ya no se vuelva a repetir 
                        myList.remove(i);
                    }
                    if (contador <= 10) {
                        System.out.println("-| Al parecer no hay suficientes personas para participar.\n -|Si desea "
                                + "agregar mas personas en el sorteo presione '3' de lo contrario presione [0].");
                        respose = br.readLine();
                        if ("1".equals(respose)) {
                            new listar().obtiene();
                        }
                    }
                }
                //Mostramos la lista de participantes 
                if ("2".equals(respose)) {
                    Collections.sort(myList);
                    int contadore = 0;
                    System.out.println("\n******PARTICIPANTES******");
                    for (String m : myList) {
                        contadore++;
                        System.out.print("\n  -"+contadore+". "+ m);
                    }
                    System.out.println("\n");
                }
            } catch (InterruptedException e) {
                System.out.println("Hubo un error. Intentalo de nuevo. " + e.getMessage());
                
            }
            
 
        } while ("1".equals(respose) || "2".equals(respose));
        
             
    }
    
        //clsSorteo sorteo1 = new clsSorteo();
    //Metodo tipo listar[] donde cargaremos los nombres a un Array 
    public listar[] obtiene() throws IOException {
        ResultSet rs1 = null;
        //clsConexion conexion = new clsConexion();
        eleccion query = new eleccion();
        rs1 = query.Ejecutar("SELECT * FROM nombres");//Igualamos la variable al metodo de EjecutarSelección 
        //y le pasamos como parametro el query
        int filaActual = 0;
        try {
            //while para saber el numero de filas en la tabla 
            while (rs1.next()) {
                ++totalFilas;
            }
            if (totalFilas != 0) {
                rs1.beforeFirst();
                lista = new listar[totalFilas];
                //Agregamos los datos de la columna Nombres al Array
                while (rs1.next()) {
                    try {
                        lista[filaActual] = new listar();
                        lista[filaActual].setName(rs1.getString("nombrescol"));
                        filaActual++;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }// end whilen 
                
                sorteo();  //Llamamos al void sorteo
            } //end if
        } catch (SQLException e) {
            System.out.print("error" + e.getMessage());
        }
        return lista;
    }

    
}
