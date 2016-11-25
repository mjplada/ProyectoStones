/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Auxiliar.ArchivoLectura;
import java.io.IOException;
import java.util.*;
import java.io.Serializable;

/**
 *
 * @author mjpla
 */
public class Juego extends Observable implements Serializable{
    private transient Partida partidaActual;
    private ArrayList<Jugador> listJugadores = new ArrayList<>();
    private boolean esGenerico;//determina si el tablero sera generco o random
    
    
    public Partida getPartida(){
        return this.partidaActual;
    }
    public void setPartida(Partida iPartida){
        this.partidaActual = iPartida;
    }
    public ArrayList<Jugador> getJugadores(){
        return this.listJugadores;
    }
    public void setJugador(Jugador iJugador){
        this.listJugadores.add(iJugador);
        this.setChanged();
        this.notifyObservers();
    }
    public void setEsGenerico(boolean iEsGenerico){
        this.esGenerico= iEsGenerico;
    }
    public boolean getEsGenerico (){
        return this.esGenerico;
    }
    
    public Juego(){
        this.listJugadores = new ArrayList<Jugador>();
        this.setEsGenerico(true);
    }
    
    public ArrayList<Jugador> getRanking(){
        ArrayList<Jugador> rank = this.getJugadores();
        Collections.sort(rank);
        return rank;
    }
    
    public void iniciarPartida(Jugador iJugador1, Jugador iJugador2, boolean iAleatoria) throws IOException{
        this.setPartida(new Partida(iJugador1,iJugador2,iAleatoria));
    }
   
    
    public boolean unicoAlias(String iAlias){
        //devuelve false su existe el alias
        boolean ret=true ;
        Jugador j  = new Jugador();
        j.setAlias(iAlias);
            Iterator<Jugador> iter = this.getJugadores().iterator();
            while( (iter.hasNext()) && ret){
                Jugador aux = iter.next();
                ret=!aux.equals(j);
            }
        return ret;
    }
    
    public int[] cargarArchivoJugadores(String sArchivo){
        
                int[]cantLeidos = new int[2];
                //[0] correctos , [1] incorrectos
                String nombre="";
                int edad =0;
                String alias ="";
                ArchivoLectura archLec = new ArchivoLectura(sArchivo);
                while(archLec.hayMasLineas()){
                    boolean ok = true;
                    try{
                        String[] datos = archLec.linea().split("#");
                        nombre = datos[0];
                        edad = Integer.parseInt(datos[1]);
                        alias = datos[2];
                    }catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
                       ok=false;
                    }
                    if (ok) {
                        Jugador j = new Jugador();
                        j.setAlias(alias);
                        if(this.unicoAlias(alias)){
                        
                            j.setEdad(edad);
                            j.setNombre(nombre);
                            this.getJugadores().add(j);
                        
                        } else{
                        
                            Iterator<Jugador> iter = this.getJugadores().iterator();
                            while( (iter.hasNext())){
                            Jugador aux = iter.next();
                                if (aux.equals(j)) {
                                    aux.setEdad(edad);
                                    aux.setNombre(nombre);
                                }
                            }
                        }
                        cantLeidos[0]++;
                    }else{
                        cantLeidos[1]++;
                    }

                }
                return cantLeidos;
    }
//    
//    public String ganoPartida(){
//        this.setChanged();
//        this.notifyObservers();
//        return this.getPartida().getElGanador();
//    }
    
    
    
}
