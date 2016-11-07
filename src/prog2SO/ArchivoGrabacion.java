/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2SO;

import java.io.*;
/**
 *
 * @author mjpla
 */
public class ArchivoGrabacion {
    private BufferedWriter out;
    
    public ArchivoGrabacion(String sNombre){
        try{
            this.out = new BufferedWriter(new FileWriter(sNombre));
        } catch (IOException e){
            
        }
    }
    
    public boolean grabarLinea(String sLinea){
        boolean ok = true;
        try {
            this.out.write(sLinea);
            this.out.newLine();
        } catch (IOException e) {
            ok = false;
        }
        
        return ok;
    }
    public boolean cerrar(){
        boolean ok = true;
        try {
            this.out.flush();
            this.out.close();
        } catch (IOException e) {
            ok = false;
        }
        return ok;
    }
}
