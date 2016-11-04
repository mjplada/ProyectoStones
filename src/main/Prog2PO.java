/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import InterfasGrafica.VentInicio;
//import java.util.*;
import java.io.*;
import prog2SO.Juego;
//import prog2SO.Jugador;
//import prog2SO.Partida;



public class Prog2PO {
    
    public static void main(String[] args) {
        
        Juego j = new Juego();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("DatosGuardados"));
            j = (Juego)in.readObject();
        } catch (Exception e) {
         
        }
        
        VentInicio inicio =  new VentInicio(j);   
         inicio.setVisible(true);
        //MenuInicio(j);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DatosGuardados"));
            out.writeObject(j);
        } catch (Exception e) {
        }
 
    }
//   public static void MenuInicio(Juego iJ){        
//        String s;
//        boolean bSalida=false;
//        while (!bSalida){
//            System.out.println("\n\n\n********-STONES-********\n\n a- Registro de Jugador \n b- Elegir carga de Tablero \n c- Jugar \n d- Ranking \n e- Salir \n f- Cargar datos de prueba \n\n ***************************");
//            s= pidoMenu("Seleccione una opción");    
//            switch (s){
//                case "a":{
//                    //registro jugador
//                    registrarJugador(iJ);
//                    break;
//                }
//                case "b" :{
//                    //elegir tablero
//                    elegirTablero(iJ);
//                    break;
//                }
//                case "c" :{
//                    //empieza el juego
//                   cargarPartida(iJ);
//                    break;
//                }     
//                case "d" :{
//                    //Ranking
//                    listarRanking(iJ);
//                    break;
//                }
//                case "e" :{
//                    //Salir
//                    bSalida = true;
//                    break;
//                }
//                case "f" :{
//                    iJ.cargarTest();
//                    System.out.println("Datos de prueba cargados.");
//                    break;
//                }
//            }    
//        }
//        
//    }
//    
//    public static int PideNumero(int min,int max, String unString){
//        /* verifica que el valor esté entre el mínimo y el máximo asignado.
//        en caso contrario pide nuevamente el valor, preguntando con el String asignado.
//        */
//        boolean bSalida= false;
//        Scanner in = new Scanner(System.in);
//        int valor = 0;
//        while (!bSalida){
//            try{
//                System.out.println(unString + " (Entre " + min + " y " + max+ " ): ");
//                valor = in.nextInt();
//                if(valor<min||valor>max){
//                    System.out.println("El valor ingresado no esta dentro de los parametros correctos.");
//                }else{
//                    bSalida=true;
//                }
//            }catch(InputMismatchException e){
//                System.out.println("Datos erróneos, ingrese nuevamente");
//            }
//            in.nextLine();
//        }    
//       return valor;
//    }
//    public static String PideTexto(String sConsulta){
//         // método para pedir un texto
//        boolean bSalida = false;
//        Scanner in = new Scanner(System.in);
//        String unaEntrada = "";
//        while(!bSalida){
//            try{
//                System.out.println(sConsulta);
//                unaEntrada = in.nextLine();
//                bSalida = true;
//            }catch(Exception e){
//                System.out.println("Datos erróneos, ingreselos nuevamente.");
//            }
//        }
//        return unaEntrada;
//    }
//    
//    public static String pidoMenu(String sConsulta){
//        boolean bSalida = false;
//        Scanner in = new Scanner(System.in);
//        String unaEntrada = "";
//        while(!bSalida){
//                System.out.println(sConsulta);
//                unaEntrada = in.nextLine();
//                if((unaEntrada.length()==1)&&(unaEntrada.charAt(0)=='a'|| unaEntrada.charAt(0)=='b'|| unaEntrada.charAt(0)=='c' || unaEntrada.charAt(0)=='d' || unaEntrada.charAt(0)=='e' || unaEntrada.charAt(0)=='f')){
//                    bSalida = true;   
//                }else{
//                    System.out.println("La opción ingresada no es correcta");
//                }
//        }
//        return unaEntrada;
//    }
//    
//    public static void registrarJugador(Juego iJ){
//        String nombre;
//        String alias="";
//        int edad; 
//        Jugador unJugador;
//        System.out.println("Nuevo Jugador");
//        nombre = PideTexto("Ingrese el nombre");
//        boolean ok =false;
//        while (!ok){
//            alias = PideTexto("Ingrese Alias del jugador");
//            ok=iJ.unicoAlias(alias);
//            if(!ok){
//                System.out.println("El Alias ya está en uso");
//            }
//        }
//        edad = PideNumero(10,99, "Ingrese la edad");
//       unJugador = new Jugador(nombre,alias,edad);
//        iJ.setJugador(unJugador);
//        System.out.println("Se ingreso el Jugador: " + unJugador.toString());
//    }
//    
//    public static void elegirTablero(Juego iJ){
//        int opcion;
//        System.out.println("Tablero para comenzar el juego:\n  1- Tablero por defecto \n 2- Tablero aleatorio");
//        opcion = PideNumero(1,2,"Seleccione una opcion");
//        iJ.setEsGenerico(opcion==1);
//    }
//    
//    public static void cargarPartida(Juego iJ){
//        int cant = iJ.getJugadores().size();
//        int pos;
//        Jugador jugador1;
//        Jugador jugador2;
//        if (cant>=2){
//            System.out.println("Lista de Jugadores ingresados: \n");
//            listarJugadores(iJ);
//            pos = PideNumero(1,cant,"Elija al Jugador 1 para la partida:");
//            jugador1 = iJ.getJugadores().get(pos-1);
//            System.out.println("Jugador 1 = "+jugador1.getAlias());
//            boolean ok=true;
//            while (ok){
//                listarJugadores(iJ);
//                pos = PideNumero(1,cant,"Elija al Jugador 2 para la partida:");
//                ok=jugador1.equals(iJ.getJugadores().get(pos-1));
//                if (ok){
//                    System.out.println("Ya eligio al jugador, vuelva a seleccionar Jugador 2");
//                }
//            }
//            jugador2 = iJ.getJugadores().get(pos-1);
//            System.out.println("Jugador 2 = "+jugador2.getAlias());
//            iJ.setPartida(new Partida(jugador1,jugador2,iJ.getEsGenerico()));
//            comenzarPartida(iJ.getPartida());
//                    
//            
//        }else {
//            System.out.println("Debe haber por lo menos 2 jugadores ingresados para poder empezar la partida");
//        }
//    }
//    
//    public static void listarJugadores(Juego iJ){
//        int num=1;
//            
//            Iterator<Jugador> iter = iJ.getRanking().iterator();
//            while(iter.hasNext()){
//                Jugador aux = iter.next();
//                System.out.println(num + ")- "+ aux.getAlias());
//                num++;
//            }        
//    }
//    
//    public static void comenzarPartida(Partida iP){
// 
//        boolean bSalir;        
//        while (!iP.getFinal()) {   
//            
//            System.out.println("\n Turno de " + iP.getTurno());
//            System.out.println(iP.getTablero().toString());
//            System.out.println("Piedras disponibles: "+iP.muestroPiedras());
//            if (iP.getJugador1().getAlias().equals(iP.getTurno())){
//                System.out.println("Fichas de "+iP.getJugador2().getAlias()+":\n"+iP.muestroFichas(false));
//                System.out.println("Fichas de "+iP.getTurno()+":\n"+iP.muestroFichas(true));
//            }else{
//                System.out.println("Fichas de "+iP.getJugador1().getAlias()+":\n"+iP.muestroFichas(false));
//                System.out.println("Fichas de "+iP.getTurno()+":\n"+iP.muestroFichas(true));
//            }
//            
//            bSalir=false;
//            while (!bSalir) {          
//                String jugada =PideTexto("Jugador "+iP.getTurno()+" Ingrese su jugada:");
//                if (jugada.toUpperCase().equals("A")){
//                  ArrayList<String> ayuda = iP.mostrarAyuda();
//                  System.out.println("\u001B[32m Los patrones posibles son:");
//                  if (!ayuda.isEmpty()){
//                    for (int i =0;i<ayuda.size();i++){
//                        System.out.println(ayuda.get(i));
//                    }
//                    }else{
//                        System.out.println("\u001B[32m No hay patrones posibles");
//                    }
//                }else{
//                    if (iP.ejecutarMovimiento(jugada)) {
//                        bSalir = true;
//                        iP.finDeTurno();
//                    } else {
//                        System.out.println("Movimiento incorrecto.");
//                    }            
//                }
//            }
//        }
//        System.out.println("\u001B[32m¡¡¡ \u001B[31mFELICITACIONES \u001B[34m"+iP.getElGanador()+" \u001B[31mHA GANADO LA PARTIDA \u001B[32m!!!");
//    }
//    public static void listarRanking(Juego iJ){
//        ArrayList<Jugador> list = iJ.getRanking();
//        if (list.isEmpty()) {
//            System.out.println("No hay jugadores para listar.");
//        } else {
//            System.out.println("Ranking de jugadores:");
//            for(int i = 0; i< list.size(); i++){
//                System.out.println((i+1) + ")- " + list.get(i).toString());
//            }
//        }
//        
//    }
//                 
}
