/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Auxiliar.ArchivoLectura;
import java.util.*;
import java.io.Serializable;

/**
 *
 * @author mjpla
 */
public class Juego implements Serializable{
    private transient Partida partidaActual;
    private ArrayList<Jugador> listJugadores = new ArrayList<>();
    boolean esGenerico;//determina si el tablero sera generco o random
    
    
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
    
    public void iniciarPartida(Jugador iJugador1, Jugador iJugador2, boolean iAleatoria){
        this.setPartida(new Partida(iJugador1,iJugador2,iAleatoria));
    }
    
    public void cargarTest(){
        Jugador j1 = new Jugador("Guillermo", "Llotet", 55);
        j1.setGanadas(15);
        j1.setJugadas(30);
        this.listJugadores.add(j1);
        Jugador j2 = new Jugador("Marcelo", "Chule", 28);
        j2.setGanadas(15);
        j2.setJugadas(30);
        this.listJugadores.add(j2);
        Jugador j3 = new Jugador("Roberto", "Beto", 43);
        j3.setGanadas(10);
        j3.setJugadas(40);
        this.listJugadores.add(j3);
        Jugador j4 = new Jugador("Maria", "Mary", 23);
        j4.setGanadas(60);
        j4.setJugadas(100);
        this.listJugadores.add(j4);
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
    
    
    
}
