/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2SO;

import java.util.*;


/**
 *
 * @author Llote
 * 
 * otra prueba bobofdfg
 * 
 */
public class Partida {
    
     private Tablero miTablero;
     private Jugador jugador1;
     private Jugador jugador2;
     private ArrayList<Character> j1Fichas;
     private ArrayList<Character> j2Fichas;
     private int piedrasNegras;
     private String turno;
     private boolean finalizada;
     private String elGanador;
     
    
    public void setTablero(Tablero iTablero){
        this.miTablero = iTablero;
    } 
    public Tablero getTablero(){
        return this.miTablero;
    }
    
    
    public void setJugador1(Jugador iJugador){
        this.jugador1 = iJugador;
    }
     
    public Jugador getJugador1(){
        return this.jugador1;
    }
    
    
    public void setJugador2(Jugador iJugador){
        this.jugador2 = iJugador;
    }
     
    public Jugador getJugador2(){
        return this.jugador2;
    }
    
    public void setFinalizada(boolean iFinal){
        this.finalizada = iFinal;
    }
    
    public boolean getFinal(){
        return this.finalizada;
    }
    
    public void setJ1Fichas(char iFicha){
        this.getJ1Fichas().add(iFicha);
    }
    public ArrayList<Character> getJ1Fichas(){
        return this.j1Fichas;
    }
    
    public void setJ2Fichas(char iFicha){
        this.getJ2Fichas().add(iFicha);
    }
    
    public ArrayList<Character> getJ2Fichas(){
        return this.j2Fichas;
    }
    
     public void setPiedrasNegras(int iPiedrasNegras){
        this.piedrasNegras = iPiedrasNegras;
    } 
     
    public int getPiedrasNegras(){
        return this.piedrasNegras;
    }
    
    public void setTurno (String iTurno){
        this.turno = iTurno;
    }
    
    public String getTurno(){
        return this.turno;
    }
    public void setelGanador (String iElGanador){
        this.elGanador=iElGanador;
    }
    public String getElGanador(){
        return this.elGanador;
    }
    
    public Partida(Jugador iJugador1,Jugador iJugador2,boolean esGenerico){
        this.setJugador1(iJugador1);
        this.setJugador2(iJugador2);
        this.setTablero(new Tablero(esGenerico));
        this.j1Fichas = new ArrayList<Character>();
        this.j2Fichas = new ArrayList<Character>();
        this.setPiedrasNegras(10);
        this.setTurno(iJugador1.getAlias());
        this.setFinalizada(false);
        
    }
    public boolean ejecutarMovimiento(String iMov){
        /*
        Codifica el movimiento con la funcion "codeificarMovimiento".
        Chequear si el movimiento es valido segun cantidad de fichas del jugador
        llamar la funcion usarPatron del _tablero.
        descartar las fichas necesarios segun el movimiento
        */
        boolean ret = false;
        try {
            String[] mov = this.desglozarMovimiento(iMov.toUpperCase());
                //mov [codMov][filcol][filcol][col1col2]
               String codigoDeJugada= mov[0];
               switch (codigoDeJugada){
                   case "PH": {
                       if (mov.length==2){
                       int[] corPiedra = this.coordenadas(mov[1]);
                       if ((this.getPiedrasNegras()!=0)&&(corPiedra[0]!=-1)){
                           char[] fRetiradas = this.miTablero.ponerPiedra(corPiedra[0], corPiedra[1],true);
                           if (fRetiradas[0]!= ' '){
                           //ponerle las respectivas fichas a los jugadores
                               ArrayList<Character> fichasJugActivo = this.getFichasCorrespondientes(true);
                               ArrayList<Character> fichasJugPasivo = this.getFichasCorrespondientes(false);
                               fichasJugActivo.add(fRetiradas[0]);
                               for (int i = 1; i < fRetiradas.length; i++) {
                                   if(fRetiradas[i] != '\u0000' && fRetiradas[i] != ' '){
                                    fichasJugPasivo.add(fRetiradas[i]);
                                    }
                                }
                               ret =true;   
                               this.setPiedrasNegras(this.getPiedrasNegras()-1);
                            }
                       }
                       }
                       //Poner piedra, retirar horizontal
                       break;
                   }
                   case "PD": {
                       //poner piedra retirar diagonal
                       if (mov.length==2){

                       int[] corPiedra = this.coordenadas(mov[1]);
                       if ((this.getPiedrasNegras()!=0)&&(corPiedra[0]!=-1)&&(mov.length==2)){
                           char[] fRetiradas = this.miTablero.ponerPiedra(corPiedra[0], corPiedra[1],false);
                           if (fRetiradas[0]!= ' '){
                           //ponerle las respectivas fichas a los jugadores
                               ArrayList<Character> fichasJugActivo = this.getFichasCorrespondientes(true);
                               ArrayList<Character> fichasJugPasivo = this.getFichasCorrespondientes(false);
                               fichasJugActivo.add(fRetiradas[0]);
                               for (int i = 1; i < fRetiradas.length; i++) {
                                   if(fRetiradas[i]!= '\u0000' && fRetiradas[i] != ' '){
                                       fichasJugPasivo.add(fRetiradas[i]);
                                   }
                               }
                               ret =true;
                               this.setPiedrasNegras(this.getPiedrasNegras()-1);
                           }
                       }
                       }
                       break;
                   }
                   case "PDS": {
                       //patron 2 separadas
                       if (mov.length==4){
                       int[] cor1 = this.coordenadas(mov[1]);
                       int[] cor2 = this.coordenadas(mov[2]);
                       if (cor1[0]!=-1 && cor2[0]!=-1){
                           if (this.fichasXColor(mov[3].toLowerCase().charAt(0),3)){

                               ret = this.getTablero().usarPatron(codigoDeJugada,cor1[0],cor1[1],cor2[0],cor2[1]);
                               if (ret){
                                   this.setPiedrasNegras(this.getPiedrasNegras()+2);
                               }
                               this.descartarFichas(mov[3].toLowerCase().charAt(0),3);
                           }
                       }
                       }
                       break;
                   }
                   case "PDJ": {
                       //patron dos juntas
                       if (mov.length==3){
                       int[] cor1 = this.coordenadas(mov[1]);
                       int[] cor2 = this.coordenadas(mov[2]);
                       if (cor1[0]!=-1 && cor2[0]!=-1){
                           if ((this.fichasXColor('a',1))&&(this.fichasXColor('c',1))&&(this.fichasXColor('v',1))&&(this.fichasXColor('r',1))){

                               ret = this.getTablero().usarPatron(codigoDeJugada,cor1[0],cor1[1],cor2[0],cor2[1]);
                               if (ret){
                                this.setPiedrasNegras(this.getPiedrasNegras()+2);
                                this.descartarFichas('a',1);
                               this.descartarFichas('c',1);
                               this.descartarFichas('v',1);
                               this.descartarFichas('r',1);
                               }

                           }
                       }
                       }
                       break;
                   }
                   case "PDD": {
                       //patron dos diagonal
                       if (mov.length==4){
                       int[] cor1 = this.coordenadas(mov[1]);
                       int[] cor2 = this.coordenadas(mov[2]);
                       if (cor1[0]!=-1 && cor2[0]!=-1){
                           if ((this.fichasXColor(mov[3].toLowerCase().charAt(0),3))&&(this.fichasXColor(mov[3].toLowerCase().charAt(1),2))){

                               ret = this.getTablero().usarPatron(codigoDeJugada,cor1[0],cor1[1],cor2[0],cor2[1]);
                               if (ret){
                                   this.setPiedrasNegras(this.getPiedrasNegras()+2);
                                    this.descartarFichas(mov[3].toLowerCase().charAt(0),3);
                                   this.descartarFichas(mov[3].toLowerCase().charAt(1),2);
                               }

                           }
                       }    
                       }
                       break;
                   }
                   case "PDC": {
                       //patron dos caballo
                       if (mov.length==4){
                       int[] cor1 = this.coordenadas(mov[1]);
                       int[] cor2 = this.coordenadas(mov[2]);
                       if (cor1[0]!=-1 && cor2[0]!=-1){
                           if (this.fichasXColor(mov[3].toLowerCase().charAt(0),5)){

                               ret = this.getTablero().usarPatron(codigoDeJugada,cor1[0],cor1[1],cor2[0],cor2[1]);
                               if (ret){
                                this.setPiedrasNegras(this.getPiedrasNegras()+2);
                                this.descartarFichas(mov[3].toLowerCase().charAt(0),5);
                               }

                           }
                       }
                       }
                       break;
                   }
                   case "D": {
                       //descartar
                       if (mov.length==2){
                       if (this.fichasXColor(mov[1].toLowerCase().charAt(0),1)){

                           this.descartarFichas(mov[1].toLowerCase().charAt(0),1);

                           ret=true;
                       }
                       }
                       break;
                   }
                   case "X": {
                       //Abandonar partida
                           String ganador;
                           if (this.getTurno().equals(this.getJugador1().getAlias())) {
                               ganador = this.getJugador2().getAlias();
                           } else {
                               ganador = this.getJugador1().getAlias();
                           }
                           this.ganoPartida(ganador);
                           ret = true;

                       break;
                   }
                   case "SF":{
                       //nuevo movimiento sacar ficha
                       if (mov.length==2){
                            int[] coord = this.coordenadas(mov[1]);
                            char[][] fRetiradas = this.getTablero().movSacarFicha(coord[0],coord[1]);
                            if (fRetiradas[0][0]!=' '){
                                //si se ejecutó el movimiento, coloco las fichas a los jugadores
                               ArrayList<Character> fichasJugActivo = this.getFichasCorrespondientes(true);
                               ArrayList<Character> fichasJugPasivo = this.getFichasCorrespondientes(false);
                               for (int i = 1; i < fRetiradas[0].length; i++) {
                                    fichasJugPasivo.add(fRetiradas[0][i]);
                               }
                               for (int i = 1; i < fRetiradas[1].length; i++) {
                                    fichasJugActivo.add(fRetiradas[1][i]);
                               }
                                ret=true;
                            }
                       }
                       break;
                   }
           
        }
        } catch (Exception e) {
            ret = false;
        }
        
        
        //Controlo que el jugador activo se quede sin fichas
        if((this.getFichasCorrespondientes(true).isEmpty())&&(ret)&& !(iMov.trim().toUpperCase().startsWith("X"))){
            ganoPartida(this.getTurno());
        }
        return ret;
    }
    
    private String[] desglozarMovimiento(String iMov){
        /*
        Codifica el movimiento del jugador
        [codMov][filcol][filcol][col1col2]
        */
        String[] movimiento = iMov.split(" ");
     
        return movimiento;
    }
    
   public ArrayList<String> mostrarAyuda(){
        /*
        devuelve un string con los movimientos posibles para el jugador dado
        */
        ArrayList<String> ayuda = new ArrayList<String>();
        String colorPDC = colorDeMov("PDC");
        String colorPDD = colorDeMov("PDD");
        String colorPDJ = colorDeMov("PDJ");
        String colorPDS = colorDeMov("PDS");
        
        if (!colorPDC.equals("")) {
            ayuda.add("PDC " + colorPDC);
        }
        if (!colorPDD.equals("")) {
            ayuda.add("PDD " + colorPDD);
        }
        if (!colorPDJ.equals("")) {
            ayuda.add("PDJ " + colorPDJ);
        }
        if (!colorPDS.equals("")) {
            ayuda.add("PDS " + colorPDS);
        }
        ayuda = this.getTablero().posiblesMov(ayuda, (10 - this.getPiedrasNegras()));
        
        return ayuda;
    }
    
    public void ganoPartida(String iAlias){
        //Da ganada la partida a un alias
        if (iAlias.equals(this.getJugador1().getAlias())) {
            this.getJugador1().setGanadas(this.getJugador1().getGanadas()+1);
            this.setelGanador(iAlias);
        } else {
            this.getJugador2().setGanadas(this.getJugador2().getGanadas()+1);
            this.setelGanador(this.getJugador2().getAlias());
        }
        this.setFinalizada(true);
        
        
    }
    
     public void finDeTurno(){
        if (this.getTurno().equals(this.getJugador1().getAlias())) {
            this.setTurno(this.getJugador2().getAlias());
        } else {
            this.setTurno(this.getJugador1().getAlias());
        }
    }


    private void descartarFichas(char iFicha, int iCant){
        /*
        descarta las fichas del movimiento realizado        
        si llega aca ya valido que tenga esa cantidad de fichas
        
        */
        ArrayList<Character> fichas;
        fichas=this.getFichasCorrespondientes(true);
        while (iCant > 0) {         
            boolean borrada = false;
            for (int i = 0; i < fichas.size() && !borrada; i++) {
                if(fichas.get(i).equals(iFicha)){
                    fichas.remove(i);
                    borrada = true;
                }
            }
            iCant--;
        }
        
    }
    
    private int [] coordenadas (String s){
        // si alguna de las coordenada no son correctas. 
        //devuelve -1 en la pos 0.
        int [] ret=new int[2];
        int fila =traductorDeCoordenadas(s.substring(0,1));
        int col =Integer.parseInt(s.substring(1,2))-1;
        if (fila!=-1 && (col>=0 && col <=5)){
            ret[0]= fila;
            ret[1]=col;
        }else{
            ret[0]=-1;
        }       
        return ret;
                
        
    }
    
    private int traductorDeCoordenadas (String s){
        //devuleve -1 si la letra ingresada no corresponde a una coordenada
        int ret;
        switch(s){
            case "A":{
                ret=0;
                break;
            }
            case"B":{
                ret=1;
                break;
            }
            case "C":{
                ret=2;
                break;
            }
            case "D":{
                ret=3;
                break;
            }
            case "E":{
                ret=4;
                break;
            }
            case "F":{
                ret=5;
                break;
            }
            default :{
                ret = -1;
                break;
            }
        }
        
        return ret;
    }
     public ArrayList<Character> getFichasCorrespondientes (boolean esElTurno){
       ArrayList<Character> ret;
       if (esElTurno){
            if ((this.getJugador1().getAlias().equals(this.getTurno()))){
                ret = this.getJ1Fichas();
            }else{
                ret=this.getJ2Fichas();
            }
       }else{
           if (this.getJugador1().getAlias().equals(this.getTurno())){
               ret=this.getJ2Fichas();
           }else{
               ret=this.getJ1Fichas();
           }
       }
       return ret;
    }
    
   private boolean fichasXColor (char iColor,int iCant){
       //valida que se tenga la cantidad de fichas de un color dado
       boolean ret = false;
       int cont =0;
       ArrayList<Character> iFichas = this.getFichasCorrespondientes(true);
       for (int i=0;i<iFichas.size();i++){
           if (iColor==iFichas.get(i)){
               cont++;
           }
       }
       ret = (cont>=iCant);
       return ret;
   }
   
   public String muestroFichas (boolean esDelTurno){
       String s ="";
       ArrayList<Character> fichas = this.getFichasCorrespondientes(esDelTurno);
       Collections.sort(fichas);
       for (int i=0;i<fichas.size();i++){
           s+=this.getTablero().setColores(fichas.get(i));
       }
       return s;
   }
   public String muestroPiedras(){
       String s=""+this.getPiedrasNegras()+" ";
       for (int i=0; i<this.getPiedrasNegras();i++){
           s+="#";
       }
       return s;
   }
   
   private String colorDeMov (String iMov){
       String ret="";
       String colores ="acvr";
         switch(iMov){
           case "PDS": {
                //patron 2 separadas
               for (int i=0;(i<colores.length())&&(ret.equals(""));i++){
                    if ((this.fichasXColor(colores.charAt(i),3))){
                        ret=ret+colores.charAt(i);
                    }
                }
                break;
            }
            case "PDJ": {
                //patron dos juntas
                if (fichasXColor('a',1)&&fichasXColor('c',1)&&fichasXColor('r',1)&&fichasXColor('v',1)) {
                    ret="acrv";
                }
                break;
            }
            case "PDD": {
                //patron dos diagonal
                 for (int i=0;(i<colores.length())&&(ret.equals(""));i++){
                    if (this.fichasXColor(colores.charAt(i),3)){
                        char colorUsado=colores.charAt(i);
                        for (int h =0;(h<colores.length())&&(ret.equals(""));h++){
                            if ((this.fichasXColor(colores.charAt(h),2))&&(colores.charAt(h)!=colorUsado)){
                                ret=ret+colorUsado+colores.charAt(h);
                            }
                        }
                    }
                }   
                break;
            }
            case "PDC": {
                //patron dos caballo
                for (int i=0;(i<colores.length())&&(ret.equals(""));i++){
                    if (this.fichasXColor(colores.charAt(i),5)){
                        ret=ret+colores.charAt(i);
                    }
                }
                break;
            }
            default :{
                ret ="";
                break;
            }
       }
       return ret;
   }
    
    
}
