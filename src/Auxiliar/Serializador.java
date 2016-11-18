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
    public Object Leer(String sRuta) throws ClassNotFoundException{
        Object aux = new Object();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(sRuta));
            aux = in.readObject();
        } catch (IOException e) {
            
        }
        return aux;
    }
    public void Guardar(Object obj, String sRuta){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sRuta));
            out.writeObject(obj);
        } catch (IOException e) {
            
        }
    }
    
        
}
