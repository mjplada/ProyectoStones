/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Image;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author mjpla
 */
public class Tablero {
    
    private char[][] tabla;
    private ImageIcon iconoPiedra; 
    
    
    public char[][] getTabla(){
        return this.tabla;
    }
    public void setTabla(char[][] iTabla){
        this.tabla = iTabla;
    }
    
    public void setIconoPiedra(ImageIcon unIconoPiedra){
        this.iconoPiedra=unIconoPiedra;
    }
    public ImageIcon getIconoPiedra(){
        return this.iconoPiedra;
    }
    
    public Tablero(){

        
        this.setTabla(this.generarTabla(true));
        
    }
    
    public Tablero(boolean esGenerico) throws IOException{
        try {
            Image img = ImageIO.read(getClass().getResource("/piedra.png"));
            ImageIcon icon = new ImageIcon(img);
            this.setIconoPiedra(icon);
        } catch (IOException e) {
            throw e;
        }
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
    
public char[][] movSacarFicha (int iFila, int iColu){
        // Nuevo movimiento SF - sacar ficha
        //si no se puede realizar la jugada, deuelve retorno[0][0]=' '
        /*si se puede realizar la jugada juardo en la fila 0, las fichas del jugador pasivo
        y en la fila 1, las fichas del jugador ativo
        */
        char[][] retorno = new char[2][6];
        char[][] miTabla = this.getTabla();
        if (!(((miTabla[iFila][iColu]=='#')||(miTabla[iFila][iColu]==' ')||(miTabla[iFila][iColu]=='\u0000')))&&((validarPosicion(iColu) && validarPosicion(iFila)))){
            retorno[0][0] = miTabla[iFila][iColu];//la ficha seleccionada va para el jugador pasivo
            miTabla[iFila][iColu]=' ';
            int cont =1;//contador para columnas, arranco en 1 por que la pos 0 fue usada para la ficha inicial
            //recorro fila y saco fichas para el jugador pasivo
            for (int i=0; i<miTabla.length;i++){
                if (miTabla[iFila][i]!=' ' && miTabla[iFila][i]!='#' && miTabla[iFila][i]!='\u0000'){
                    retorno[0][cont] = miTabla[iFila][i];
                    miTabla[iFila][i]=' ';
                    cont++;
                }
            }
            cont=0;
            //recorro columna y saco fichas para el jugador activo
            for (int i=0; i<miTabla[0].length;i++){
                if (miTabla[i][iColu] != ' ' && miTabla[i][iColu] != '#' && miTabla[i][iColu]!='\u0000'){
                    retorno[1][cont] = miTabla[i][iColu];
                    miTabla[i][iColu]=' ';
                    cont++;
                }
            }
        }else {
            retorno[0][0]=' ';
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
                            if (tipo.equals("PDS")||tipo.equals("PDC")) {
                                linea = linea + " color" + this.colorAPalabra(color.substring(0, 1));
                            } else if (tipo.equals("PDD")&&color.length()>=2){
                                linea = linea + " color" + this.colorAPalabra(color.substring(0, 2));
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
    
//    public char[][] dibujaTabla(){
//        
//        /*
//        Este metodo toma la matriz de la clase (tabla) y la "mete" dentro de otra
//        dibujada como fue solicitado en la letra
//        */
//       
//        char [][] tableroOut = {{'-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
//                                {'-','|','1','|','2','|','3','|','4','|','5','|','6','|'},
//                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
//                                {'A','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
//                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
//                                {'B','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
//                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
//                                {'C','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
//                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
//                                {'D','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
//                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
//                                {'E','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
//                                {'-','+','-','+','-','+','-','+','-','+','-','+','-','+'},
//                                {'F','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|'},
//                                {'-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
//        char [][] aux = this.getTabla();
//        int iAux =0;
//        
//        for (int i = 3; i < tableroOut.length; i += 2) {
//        int jAux=0;
//            for (int j = 2; j < tableroOut[0].length; j += 2){
//                tableroOut[i][j]=aux[iAux][jAux];
//                jAux++;
//            }
//            iAux++;
//        }
//        
//        return tableroOut;
//    }
    
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
//    @Override
//    public String toString(){
//        String ret = "";
////        char[][] eltablero = this.dibujaTabla();
////        for (int i =0; i<eltablero.length;i++){
////            for(int j=0; j<eltablero[i].length;j++){
////                ret = ret + this.setColores(eltablero[i][j]);
////            }
////            ret = ret + "\n";
////        }
//        return ret;
//    }
    
//    public Icon setColores(char iChar){
//        ImageIcon icon = new ImageIcon("src\\Imagenes\\vacio.png");
//        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
//                if(iChar=='a'){
//                    icon = new ImageIcon("src\\Imagenes\\azul.png");
//                    icono= new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
//                   
//                }else{
//                    if(iChar=='c'){
//                      icon = new ImageIcon("src\\Imagenes\\celeste.png");
//                      icono= new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
//                    }else{
//                        if(iChar=='v'){
//                            icon = new ImageIcon("src\\Imagenes\\verde.png");
//                            icono= new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
//                        }else{
//                            if(iChar=='r'){
//                                 icon = new ImageIcon("src\\Imagenes\\rojo.png");
//                                icono= new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
//                            }else {
//                                if(iChar=='#'){
//                                     icono= new ImageIcon(this.getIconoPiedra().getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
//
//                                }
//     
//                            }
//                            
//                        }
//                        
//                    }
//                }
//                        
//        return icono;
//    }
    
    public ImageIcon setColores(char iChar){
        String ruta = "/vacio.png";
        Image img = null;
        ImageIcon ret = null;
        if (iChar != '#') {
                switch (iChar) {
                case 'a':{
                    ruta = "/azul.png";
                    break;
                }
                case 'c':{
                    ruta = "/celeste.png";
                    break;
                }
                case 'v':{
                    ruta = "/verde.png";
                    break;
                }
                case 'r':{
                    ruta = "/rojo.png";
                    break;
                }
                case '#':{
                    ruta = "/piedra.png";
                    break;
                }

            }
            try {
                img=ImageIO.read(getClass().getResource(ruta));
                //botones[i][j].setIcon(new ImageIcon(img.getScaledInstance(30,30, Image.SCALE_DEFAULT)));
            } catch (Exception e) {
            }
            //ImageIcon img = new ImageIcon(getClass().getResource(ruta));
            //ImageIcon imgIcon = 
            ret = new ImageIcon(img.getScaledInstance(30,30, Image.SCALE_DEFAULT));
        } else {
            ret = new ImageIcon(this.getIconoPiedra().getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        }
        
        return ret;
    }
    
    
}
