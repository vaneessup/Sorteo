/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import ctr.listar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vanes
 */
public class modelName {
     /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    public String name;
    public listar[] lista = null;
    public String[] n = null;
    public List<String> miLista;
    public Scanner r = new Scanner(System.in);
    public int totalFilas = 0;
}
