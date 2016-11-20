/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.*;
import java.util.*;

/**
 *
 * @author Llote
 */
public class Jugador extends Observable implements Comparable<Jugador> , Serializable {
    private String nombre;
    private String alias;
    private int edad;
    private int ganadas;
    private int jugadas;
    
    //Gets, Sets
    
    public void setNombre(String iNombre){
        this.nombre = iNombre;
        
            
    } 
    public String getNombre(){
        return this.nombre;
    }
     public void setAlias(String iAlias){
        this.alias = iAlias;
    } 
    public String getAlias(){
        return this.alias;
    }
            
     public void setEdad(int iEdad){
        this.edad = iEdad;
        this.setChanged();
        this.notifyObservers();
    } 
    public int getEdad(){
        return this.edad;
    }
     public void setGanadas(int iGanadas){
        this.ganadas = iGanadas;
        this.setChanged();
        this.notifyObservers();
    } 
    public int getGanadas(){
        return this.ganadas;
    }
    
    public void setJugadas(int iJugadas){
        this.jugadas=iJugadas;
        this.setChanged();
        this.notifyObservers();
    }
    public int getJugadas(){
        return this.jugadas;
    }
    
    public Jugador (){
        this.setAlias("Chule");
        this.setNombre("Ruperto");
        this.setEdad(31);
        this.setGanadas(0);
        this.setJugadas(0);
    }
    
    public Jugador (String iNombre, String iAlias, int iEdad){
        this.setAlias(iAlias);
        this.setNombre(iNombre);
        this.setEdad(iEdad);
        this.setGanadas(0);
        this.setJugadas(0);
    }
    
    
     @Override
    public String toString(){
        return "Jugador: "+this.getNombre()+" \t\tAlias: "+this.getAlias()+" \t\tEdad: "+this.getEdad()+" \tGanadas: "+this.getGanadas()+" \tPerdidas: "+this.getPerdidas();
    }
    
    
    public int compareTo(Jugador iJugador){
        return (this.getGanadas()- iJugador.getGanadas())*-1;
    }
    
     @Override
    public boolean equals(Object obj){
        return this.getAlias().toUpperCase().equals(((Jugador)obj).getAlias().toUpperCase());
    }
    
    public int getPerdidas(){
        return this.getJugadas()-this.getGanadas();
    }
    
    
}
