/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.*;

/**
 *
 * @author mjpla
 */
public class ArchivoLectura {
    String linea = "";
    BufferedReader in;
    
    public ArchivoLectura(String sNombre){
        try {
            this.in = new BufferedReader(new FileReader(sNombre));
        } catch (IOException e) {
        }
    }
    
    public boolean hayMasLineas(){
        try {
            this.linea = in.readLine();
        } catch (IOException e) {
            this.linea = null;
        }
        return (this.linea != null);
    }
    
    public String linea(){
        return this.linea;
    }
    
    public boolean cerrar(){
        boolean ok = true;
        try {
            in.close();
        } catch (IOException e) {
            ok = false;
        }
        return ok;
    }
}
