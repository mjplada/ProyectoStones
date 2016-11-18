/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;


import java.io.*;

/**
 *
 * @author mjpla
 */
public class Serializador {
    public static Object Leer(String sRuta) throws ClassNotFoundException, IOException{
        Object aux = new Object();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(sRuta));
            aux = in.readObject();
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (IOException d){
            throw d;
        }
        
        return aux;
    }
    public static void Guardar(Object obj, String sRuta){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sRuta));
            out.writeObject(obj);
        } catch (IOException e) {
            
        }
    }

    public static void Guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        
}
