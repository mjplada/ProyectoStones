/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2SO;
import java.util.*;

/**
 *
 * @author mjpla
 */
public class Tablero {
    
    private char[][] tabla;
    
    public char[][] getTabla(){
        return this.tabla;
    }
    public void setTabla(char[][] iTabla){
        this.tabla = iTabla;
    }
    public Tablero(){
        this.setTabla(this.generarTabla(true));
    }
    
    public Tablero(boolean esGenerico){
     
          this.setTabla(this.generarTabla(esGenerico));
        
    }
    
    
    public char[] ponerPiedra(int iFila, int iColu, boolean esHorizontal){
        /*Este metodo recibe; Fila, columna y un codigo de tipo de movimiento
        por ej. Diagonal = 1.
        Y va a devolver un array de char con las posibles 4 fichas que "retira"
        siendo -1 cuando no hay fichas en la posicion
        */
        char[] retorno = new char[5];
        char[][] miTabla = this.getTabla();

        if((miTabla[iFila][iColu] != ' ')&&(miTabla[iFila][iColu] != '#')){
            retorno[0] = miTabla[iFila][iColu];
            miTabla[iFila][iColu]='#';
            if(esHorizontal){
                if(validarPosicion(iColu-1) && miTabla[iFila][iColu-1] != '#'){
                    retorno[1] = miTabla[iFila][iColu-1];
                    miTabla[iFila][iColu-1]=' ';
                }
                if(validarPosicion(iColu+1) && miTabla[iFila][iColu+1] != '#'){
                    retorno[2] = miTabla[iFila][iColu+1];
                    miTabla[iFila][iColu+1]=' ';
                }
                if(validarPosicion(iFila-1)&& miTabla[iFila-1][iColu] != '#'){
                    retorno[3] = miTabla[iFila-1][iColu];
                    miTabla[iFila-1][iColu]=' ';
                }
                if(validarPosicion(iFila+1) && miTabla[iFila+1][iColu] != '#'){
                    retorno[4] = miTabla[iFila+1][iColu];
                    miTabla[iFila+1][iColu]=' ';
                }
            } else {
                if(validarPosicion(iColu-1) && validarPosicion(iFila-1) && miTabla[iFila-1][iColu-1] != '#'){
                    retorno[1] = miTabla[iFila-1][iColu-1];
                    miTabla[iFila-1][iColu-1]=' ';
                }
                if(validarPosicion(iColu+1) && validarPosicion(iFila+1) && miTabla[iFila+1][iColu+1] != '#'){
                    retorno[2] = miTabla[iFila+1][iColu+1];
                    miTabla[iFila+1][iColu+1]=' ';
                }
                if(validarPosicion(iColu-1) && validarPosicion(iFila+1) && miTabla[iFila+1][iColu-1] !='#'){
                    retorno[3] = miTabla[iFila+1][iColu-1];
                    miTabla[iFila+1][iColu-1]=' ';
                }
                if(validarPosicion(iColu+1) && validarPosicion(iFila-1) && miTabla[iFila-1][iColu+1] != '#'){
                    retorno[4] = miTabla[iFila-1][iColu+1];
                    miTabla[iFila-1][iColu+1]=' ';
                }
            }
        } else {
            retorno[0] = ' ';
        }
       
        return retorno;       
    }
    
    public boolean usarPatron(String iTipoMov, int iFila1, int iColu1, int iFila2, int iColu2){
        /*
        Este metodo recibe un tipo de movimiento, fila y columna de la primera piedra 
        y fila y columna de la segunda piedra.
        Devuelve un boolean si se pudo o no hacer le movimiento
        (Hay que aclarar que la verificacion de que el Player tenga las fichas para
        hacer el movimiento se deben hacer en la clase "Partida"
        */
        boolean correcto = false;
        char[][] miTabla = this.getTabla();
        if(validarPatron(iTipoMov, iFila1, iColu1, iFila2, iColu2)){
            if((miTabla[iFila1][iColu1] == '#')&&(miTabla[iFila2][iColu2] == '#')){
                miTabla[iFila1][iColu1] = ' ';
                miTabla[iFila2][iColu2] = ' ';
                correcto = true;
            }
        }
        
        return correcto;
    }
    
    public ArrayList<String> posiblesMov(ArrayList<String> iTipos, int cantPiedras){
        /*
        Este metodo recibe un array de boolanos con los tipos de movimientos que puede hacer el jugador
        que solicito la ayuda, siendo el largo el maximo de movimientos que acepta el juego (4) y
        True cuando dicho jugador tiene las fichas posibles a descartar y ejecutar ese movimiento
        ---------------------------------------------------------------------------------------------------
        Va a devolver una matriz de int´s siendo las columnas:
        Col 1: Codigo de movimienot
        Col 2: Fila de la primera piedra
        Col 3: Columna de la primera piedra
        Col 4: Fila de la segunda piedra
        Col 5: Columna de la segunda piedra
        */
        ArrayList<String> ret = new ArrayList<String>();
        char[][] miTabla = this.getTabla();
        int[][] piedras = new int[cantPiedras][2];
        int cant = 0;
        if(iTipos.size() > 0){
            
            //Buscamos las posiciones de las piedras
            for (int i = 0; i < miTabla.length; i++) {
                for (int j = 0; j < miTabla[i].length; j++) {
                    if(miTabla[i][j] == '#'){
                        piedras[cant][0] = i;
                        piedras[cant][1] = j;
                        cant ++;
                    }
                }
            }
            //Buscamos los posibles patrones entre ellas
            for (int i = 0; i < piedras.length; i++) {
                for (int j = i + 1; j < piedras.length; j++) {
                    for (int k = 0; k < iTipos.size(); k++) {
                        String tipo = iTipos.get(k).substring(0, 3);
                        String color = (iTipos.get(k).substring(4)).trim();
                        
                        if (validarPatron(tipo, piedras[i][0], piedras[i][1], piedras[j][0], piedras[j][1])) {
                            //ret.add(this.patronAPalabra(tipo) + " " +  this.filaALetra(piedras[i][0]) + (piedras[i][1]+ 1) + " a " + this.filaALetra(piedras[j][0]) + (piedras[j][1] + 1) + " color" + this.colorAPalabra(color));
                            String linea = "";
                            linea = this.patronAPalabra(tipo) + " " +  this.filaALetra(piedras[i][0]) + (piedras[i][1]+ 1) + " a " + this.filaALetra(piedras[j][0]) + (piedras[j][1] + 1) ;
                            if (!tipo.equals("PDJ")) {
                                linea = linea + " color" + this.colorAPalabra(color);
                            }
                            ret.add(linea);
                        }
                        
                        
                    }
                }
            }
            
        }
        
        
        return ret;
    }
    public String filaALetra(int iFila){
        String ret = "";
        switch(iFila){
            case 0:{
                ret="A";
                break;
            }
            case 1:{
                ret="B";
                break;
            }
            case 2:{
                ret="C";
                break;
            }
            case 3:{
                ret="D";
                break;
            }
            case 4:{
                ret="E";
                break;
            }
            case 5:{
                ret="F";
                break;
            }
        }
        return ret;
    }
    public String colorAPalabra(String iColor){
        String nuevo = "";
        if(iColor.length()>1){
            nuevo="es ";
        }
        for (int i = 0; i < iColor.length(); i++) {
            String aux="";
            switch(iColor.substring(i, i+1)){
            case "c":{
                aux="Celeste";
                break;
            }
            case "a":{
                aux="Azul";
                break;
            }
            case "v":{
                aux="Verde";
                break;
            }
            case "r":{
                aux="Rojo";
                break;
            }
            }
            if (i == 0) {
                nuevo = nuevo + " " +aux;
            }else{
                nuevo = nuevo + " y " + aux;
            }
        }
        return nuevo;
    }
    public String patronAPalabra(String iPatron){
        String nuevo = "";
        
        switch (iPatron.substring(0, 3)){
            case "PDC":{
                nuevo = iPatron.replaceFirst("PDC", "CABALLO");
                break;
            }
            case "PDJ":{
                nuevo = iPatron.replaceFirst("PDJ", "JUNTAS");        
                break;
            }
            case "PDD":{
                nuevo = iPatron.replaceFirst("PDD", "DIAGONAL");
                break;
            }
            case "PDS":{
                nuevo = iPatron.replaceFirst("PDS", "SEPARADAS"); 
                break;
            }
        }
        
        return nuevo;
    }
    
    public char[][] dibujaTabla(){
        
        /*
        Este metodo toma la matriz de la clase (tabla) y la "mete" dentro de otra
        dibujada como fue solicitado en la letra
        */
       
        char [][] tableroOut = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
                                {'-','|','1','|','2','|','3','|','4','|','5','|','6','|'},
                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
                                {'A','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
                                {'B','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
                                {'C','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
                                {'D','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
                                {'E','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
                                {'F','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
                                {'-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
        char [][] aux = this.getTabla();
        int iAux =0;
        
        for (int i = 3; i < tableroOut.length; i += 2) {
        int jAux=0;
            for (int j = 2; j < tableroOut[0].length; j += 2){
                tableroOut[i][j]=aux[iAux][jAux];
                jAux++;
            }
            iAux++;
        }
        
        return tableroOut;
    }
    
    private char[][] generarTabla (boolean esGenerico){
        //genera el tablero por defecto o uno random
        char[][] ret=new char[6][6];
        if (esGenerico){
           char[][] aux = {{'c','c','c','c','c','c'},
                           {'a','a','a','a','a','a'},
                           {'a','a','a','a','r','r'},
                           {'r','r','r','r','r','r'},
                           {'r','r','v','v','v','v'},
                           {'v','v','v','v','v','v'}};
           ret=aux;
        }else {
           int[] fichas = {10,10,10,10};
           //pos o = celeste, pos 1 = azul pos 2 = verde pos 3= rojas
           Random r = new Random();
            
            for (int i = 0; i < ret.length ; i++){
                for (int j = 0 ; j < ret[i].length; j++){
                    //recorro la matriz
                    
                    int pos = r.nextInt(4);
                    while (fichas[pos]==0){
                       pos = r.nextInt(4); 
                    }
                    
                    //en cada posicion, si me quedan fichas del color
                    //coloco una ficha random y resto
                        switch (pos){
                            case 0: {
                                ret[i][j]='c';
                                break;
                            }
                            case 1: {
                                ret[i][j]='a';
                                break;
                            }
                            case 2: {
                                ret[i][j]='v';
                                break;
                            }
                            case 3: {
                                ret[i][j]='r';
                                break;
                            }
                        }
                        fichas[pos]--;
                    
                
                }
            }
        }
        
        return ret;
        
    }
    public boolean validarPosicion(int iPos){
        //Valida que la columna o fila este dentro del rango del tablero
        boolean correcto = false;
        if (iPos >= 0 && iPos <=5) {
            correcto = true;
        }
        return correcto;
    }
    
    
    
    public boolean validarPatron(String iTipoMov, int iFila1, int iColu1, int iFila2, int iColu2){
        boolean correcto = false;
        char[][] miTabla = this.getTabla();
        switch (iTipoMov){
            case "PDS":{
                if((iFila1 == iFila2)&&((iColu1 == iColu2 -2)||(iColu1 == iColu2 +2))||(iColu1 == iColu2)&&((iFila1 == iFila2 -2)||(iFila1 == iFila2 +2)) ){
                    correcto = true;
                }
                break;
            }
            case "PDJ":{
                if((iFila1 == iFila2)&&((iColu1 == iColu2 -1)||(iColu1 == iColu2 +1))||(iColu1 == iColu2)&&((iFila1 == iFila2 -1)||(iFila1 == iFila2 +1)) ){
                    correcto = true;
                }                
                break;
            }
            case "PDD":{
                if((iFila1 == (iFila2-1))&&((iColu1 == iColu2 -1)||(iColu1 == iColu2 +1))||(iFila1 == (iFila2+1))&&((iColu1 == iColu2 -1)||(iColu1 == iColu2 +1))){
                    correcto = true;
                }                
                break;
            }
            case "PDC":{
                if ((iFila1 == (iFila2-2))&&((iColu1 == (iColu2-1))||(iColu1 == (iColu2+1)))||(iFila1 == (iFila2+2))&&((iColu1 == (iColu2-1))||(iColu1 == (iColu2+1)))||(iColu1 == (iColu2-2))&&((iFila1 == (iFila2-1))||(iFila1 == (iFila2+1)))||(iColu1 == (iColu2+2))&&((iFila1 == (iFila2-1))||(iFila1 == (iFila2+1)))) {
                    correcto = true;
                }
                break;
            }
        }
        
        return correcto;
    }
    @Override
    public String toString(){
        String ret = "";
        char[][] eltablero = this.dibujaTabla();
        for (int i =0; i<eltablero.length;i++){
            for(int j=0; j<eltablero[i].length;j++){
                ret = ret + this.setColores(eltablero[i][j]);
            }
            ret = ret + "\n";
        }
        return ret;
    }
    
    public String setColores(char iChar){
        String s="";
//    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_BLUE = "\u001B[34m";
//    public static final String ANSI_CYAN = "\u001B[36m";
//    public static final String ANSI_WHITE = "\u001B[37m";
        if ((iChar=='-')||(iChar=='+')||(iChar=='|')){
            s="\u001B[37m"+iChar;
        }else{
            if (((iChar=='1')||(iChar=='2')||(iChar=='3'))||(iChar=='4')||(iChar=='5')||(iChar=='6')||(iChar=='A')||(iChar=='B')||(iChar=='C')||(iChar=='D')||(iChar=='E')||(iChar=='F')){
                s="\u001B[33m"+iChar;
            }else{
                if(iChar=='a'){
                    s="\u001B[34m"+'\u0398';
                }else{
                    if(iChar=='c'){
                    s="\u001B[36m"+'\u0398';
                    }else{
                        if(iChar=='v'){
                        s="\u001B[32m"+'\u0398';
                        }else{
                            if(iChar=='r'){
                            s="\u001B[31m"+'\u0398';
                            }else {
                                if(iChar=='#'){
                                s="\033[30m"+iChar;
                                }else{
                                    if(iChar==' '){
                                        s=""+iChar;
                                    }
                                }
                                    
                            }
                            
                        }
                        
                    }
                }
                        
            }
        }
            

        return s;
    }
    
    
}