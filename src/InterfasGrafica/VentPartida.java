/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfasGrafica;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Dominio.Juego;

/**
 *
 * @author mjpla
 */
public class VentPartida extends javax.swing.JFrame {

    /**
     * Creates new form VentPartida
     */
    private JButton[][] botones;
    private JButton[][] fichasJ1;
    private JButton[][] fichasJ2;
    private JLabel[][] lblLetras;
    private JLabel[][] lblNumeros;
    private JButton[] piedras;
    private Juego elJuego;
    private boolean esPiedra;
    private int selFila;
    private int selCol;


    public VentPartida(Juego iJuego) {
        initComponents();
        this.elJuego = iJuego;
        this.setLayout(null);
        
        // crear botones y agregarlos al panel
        panelNumeros.setLayout(new GridLayout(1,7));
        panelLetras.setLayout(new GridLayout(7,1));
        lblLetras = new JLabel[7][2];
        lblNumeros = new JLabel[2][7];
        for (int i= 1;i<=6;i++){
            JLabel jLabel = new JLabel();
            panelNumeros.add(jLabel);
            lblNumeros[1][i]=jLabel;
            lblNumeros[1][i].setText(""+i);
            JLabel jLabel2 = new JLabel();
            panelLetras.add(jLabel2);
            lblLetras[i][1]=jLabel2;
            lblLetras[i][1].setText(""+i);
        }
        panelJuego.setLayout(new GridLayout(7, 7));
        botones = new JButton[7][7];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                
                JButton jButton = new JButton();
                jButton.addActionListener(new ListenerBoton(i, j));
                panelJuego.add(jButton);
                botones[i][j] = jButton;
                
                
            }
        }
        this.panelFichasJ1.setLayout(new GridLayout(4, 10));
        this.panelFichasJ2.setLayout(new GridLayout(4, 10));
        fichasJ1 = new JButton[4][10];
        fichasJ2 = new JButton[4][10];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                JButton jButton = new JButton();
                JButton jButton2 = new JButton();
                jButton.addActionListener(new ListenerBotonFicha(i, j, true));
                jButton2.addActionListener(new ListenerBotonFicha(i, j, false));
                this.panelFichasJ1.add(jButton);
                this.panelFichasJ2.add(jButton2);
                fichasJ1[i][j] = jButton;
                fichasJ2[i][j] = jButton2;
            }

        }

        this.panelPiedras.setLayout(new GridLayout(1, 10));
        piedras = new JButton[10];
        for (int i = 0; i < 10; i++) {
            JButton jButton = new JButton();
            jButton.addActionListener(new ListenerBotonPiedra(i));
            this.panelPiedras.add(jButton);
            piedras[i] = jButton;
        }
        this.lblJugador1.setText("Fichas de "+ this.elJuego.getPartida().getJugador1().getAlias());
        this.lblJugador2.setText("Fichas de "+ this.elJuego.getPartida().getJugador2().getAlias());
       //Probando cosas
        
        this.actualizarTablero();
        
        
        //Fin de prueba de cosas

    }
    private void actualizarTablero(){
        this.elJuego.getPartida().finDeTurno();
        this.lblTurno.setText("Turno de "+ this.elJuego.getPartida().getTurno());
        int cant;
        char[][] tablero = this.elJuego.getPartida().getTablero().getTabla();
        ArrayList<Character> auxFichasJ1 = this.elJuego.getPartida().getJ1Fichas();
        ArrayList<Character> auxFichasJ2 = this.elJuego.getPartida().getJ2Fichas();
        int auxPiedras = this.elJuego.getPartida().getPiedrasNegras();
        try {
            this.lblTurno.setText("Turno de "+ this.elJuego.getPartida().getTurno());
            
            btnPD.setEnabled(false);
            btnPDC.setEnabled(false);
            btnPDD.setEnabled(false);
            btnPDS.setEnabled(false);
            btnPDJ.setEnabled(false);
            btnPH.setEnabled(false);
            btnSF.setEnabled(false);
            btnDescartar.setEnabled(false);
            Pintar(selFila,selCol);
            //tablero
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (tablero[i-1][j-1] != ' '){
                        botones[i][j].setName(String.valueOf(tablero[i-1][j-1]));
                        botones[i][j].setIcon(this.elJuego.getPartida().getTablero().setColores(tablero[i-1][j-1]));
                    } else {
                        botones[i][j].setName(null);
                        botones[i][j].setIcon(null);
                    }
                    
                    
                }
            }
           //piedras
            ImageIcon icon = new ImageIcon("piedra.png");
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
            for (int i =0;i<10;i++){
                if (i < auxPiedras) {
                    this.piedras[i].setIcon(icono);
                } else {
                    this.piedras[i].setIcon(null);
                }
               
           }
            //Fichas de los jugadores
            for (int i = 0; i < 4; i++) {
                    char tipo = ' ';
                    switch (i){
                        case 0:{
                            tipo = 'a';
                            break;
                        }
                        case 1:{
                            tipo = 'r';
                            break;
                        }
                        case 2:{
                            tipo = 'c';
                            break;
                        }
                        case 3:{
                            tipo = 'v';
                            break;
                        }
                    }
                    int cantJ1 = this.cuantasFichas(auxFichasJ1, tipo);
                    int cantJ2 = this.cuantasFichas(auxFichasJ2, tipo);
                for (int j = 0; j < 10; j++) {
                    //Jugador 1
                    if (j<cantJ1) {
                        this.fichasJ1[i][j].setName(String.valueOf(tipo));
                        this.fichasJ1[i][j].setIcon(this.elJuego.getPartida().getTablero().setColores(tipo));
                    } else {
                        this.fichasJ1[i][j].setName(null);
                        this.fichasJ1[i][j].setIcon(null);
                    }
                    //Jugador 2
                    if (j<cantJ2) {
                        this.fichasJ2[i][j].setName(String.valueOf(tipo));
                        this.fichasJ2[i][j].setIcon(this.elJuego.getPartida().getTablero().setColores(tipo));
                    } else {
                        this.fichasJ2[i][j].setName(null);
                        this.fichasJ2[i][j].setIcon(null);
                    }
                }
            }
            if (this.elJuego.getPartida().getTurno().equals(this.elJuego.getPartida().getJugador1().getAlias())) {
                this.panelFichasJ2.setEnabled(false);
                this.panelFichasJ1.setEnabled(true);
            } else {
                this.panelFichasJ1.setEnabled(false);
                this.panelFichasJ2.setEnabled(true);
            }
//            Iterator<Character> I1 = auxFichasJ1.iterator();
//            cant = 0;
//            while(I1.hasNext()){                
//                char aux = I1.next();
//                switch (aux){
//                    case 'a':{
//                        this.fichasJ1[1][cant].setName(String.valueOf(aux));
//                        this.fichasJ1[1][cant].setIcon(this.elJuego.getPartida().getTablero().setColores(aux));
//                        break;
//                    }
//                }
//                cant++;
//                    
//                
//            }
                   
            
         
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        
        
    }
    
    private int cuantasFichas(ArrayList<Character> list, char tipo){
        int ret = 0;
        Iterator<Character> Iter = list.iterator();
        while(Iter.hasNext()){
            char aux = Iter.next();
            if (aux == tipo) {
                ret++;
            }
        }
        return ret;
    }
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelJuego = new javax.swing.JPanel();
        panelImagenes = new javax.swing.JPanel();
        btnBuscarGoogle = new javax.swing.JButton();
        txtBuscarGoogle = new javax.swing.JTextField();
        btnCargarImg = new javax.swing.JButton();
        lblTurno = new javax.swing.JLabel();
        panelFichasJ1 = new javax.swing.JPanel();
        panelFichasJ2 = new javax.swing.JPanel();
        lblJugador1 = new javax.swing.JLabel();
        lblJugador2 = new javax.swing.JLabel();
        lblPiedras = new javax.swing.JLabel();
        panelPiedras = new javax.swing.JPanel();
        btnPH = new javax.swing.JButton();
        btnPD = new javax.swing.JButton();
        btnPDS = new javax.swing.JButton();
        btnPDJ = new javax.swing.JButton();
        btnPDD = new javax.swing.JButton();
        btnPDC = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnSF = new javax.swing.JButton();
        panelLetras = new javax.swing.JPanel();
        panelNumeros = new javax.swing.JPanel();

        setSize(new java.awt.Dimension(600, 400));

        panelJuego.setName("panelJuego"); // NOI18N

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        btnBuscarGoogle.setText("Buscar en Google");
        btnBuscarGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGoogleActionPerformed(evt);
            }
        });

        txtBuscarGoogle.setToolTipText("Buscar imagen en google");

        btnCargarImg.setText("Cargar Imagen de piedra");
        btnCargarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImagenesLayout = new javax.swing.GroupLayout(panelImagenes);
        panelImagenes.setLayout(panelImagenesLayout);
        panelImagenesLayout.setHorizontalGroup(
            panelImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnBuscarGoogle)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnCargarImg)
                .addGap(45, 45, 45))
        );
        panelImagenesLayout.setVerticalGroup(
            panelImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagenesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarGoogle)
                    .addComponent(txtBuscarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarImg))
                .addContainerGap())
        );

        lblTurno.setText("lblTurno");

        javax.swing.GroupLayout panelFichasJ1Layout = new javax.swing.GroupLayout(panelFichasJ1);
        panelFichasJ1.setLayout(panelFichasJ1Layout);
        panelFichasJ1Layout.setHorizontalGroup(
            panelFichasJ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
        panelFichasJ1Layout.setVerticalGroup(
            panelFichasJ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFichasJ2Layout = new javax.swing.GroupLayout(panelFichasJ2);
        panelFichasJ2.setLayout(panelFichasJ2Layout);
        panelFichasJ2Layout.setHorizontalGroup(
            panelFichasJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFichasJ2Layout.setVerticalGroup(
            panelFichasJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        lblJugador1.setText("Jugador1");

        lblJugador2.setText("Jugador2");

        lblPiedras.setText("Piedras");

        javax.swing.GroupLayout panelPiedrasLayout = new javax.swing.GroupLayout(panelPiedras);
        panelPiedras.setLayout(panelPiedrasLayout);
        panelPiedrasLayout.setHorizontalGroup(
            panelPiedrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
        panelPiedrasLayout.setVerticalGroup(
            panelPiedrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        btnPH.setText("Poner Horizontal");
        btnPH.setEnabled(false);
        btnPH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPHActionPerformed(evt);
            }
        });

        btnPD.setText("Poner Diagonal");
        btnPD.setEnabled(false);
        btnPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDActionPerformed(evt);
            }
        });

        btnPDS.setText("Patrón dos separadas");
        btnPDS.setEnabled(false);

        btnPDJ.setText("Patrón dos juntas");
        btnPDJ.setEnabled(false);
        btnPDJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDJActionPerformed(evt);
            }
        });

        btnPDD.setText("Patrón dos diagonal");
        btnPDD.setEnabled(false);

        btnPDC.setText("Patrón dos caballo");
        btnPDC.setEnabled(false);

        btnDescartar.setText("Descartar ficha");
        btnDescartar.setEnabled(false);

        btnSF.setText("Sacar Ficha");
        btnSF.setEnabled(false);
        btnSF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLetrasLayout = new javax.swing.GroupLayout(panelLetras);
        panelLetras.setLayout(panelLetrasLayout);
        panelLetrasLayout.setHorizontalGroup(
            panelLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );
        panelLetrasLayout.setVerticalGroup(
            panelLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelNumerosLayout = new javax.swing.GroupLayout(panelNumeros);
        panelNumeros.setLayout(panelNumerosLayout);
        panelNumerosLayout.setHorizontalGroup(
            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelNumerosLayout.setVerticalGroup(
            panelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(panelPiedras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelFichasJ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelFichasJ2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(lblPiedras)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(panelLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelNumeros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPDS, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(btnPDJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDescartar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnPH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPD)
                        .addGap(7, 7, 7)
                        .addComponent(btnSF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDJ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDescartar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(panelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelLetras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblJugador1)))
                .addGap(18, 18, 18)
                .addComponent(panelFichasJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lblJugador2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFichasJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPiedras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPiedras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarGoogleActionPerformed
        // TODO add your handling code here:
        String url = "https://www.google.com.uy/?gws_rd=ssl#q=" + this.txtBuscarGoogle.getText().replace(' ', '+');
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException ex) {
        } catch (IOException e) {
        }
    }//GEN-LAST:event_btnBuscarGoogleActionPerformed

    private void btnCargarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImgActionPerformed
        int resultado;
        File fichero;
        VentBrowser ventana = new VentBrowser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png");
        ventana.jfcCargarArchivos.setFileFilter(filtro);
        resultado = ventana.jfcCargarArchivos.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == resultado) {
            fichero = ventana.jfcCargarArchivos.getSelectedFile();
                ImageIcon icon = new ImageIcon(fichero.toString());
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(botones[1][1].getWidth(), botones[1][1].getHeight(), Image.SCALE_DEFAULT));
                //recorrer botones fuscar piedra y cambiar icono
                botones[1][1].setIcon(icono);
                int auxPiedras = this.elJuego.getPartida().getPiedrasNegras();
                for (int i =0;i<auxPiedras;i++){
                     this.piedras[i].setIcon(icono);
                }
        }
        
    }//GEN-LAST:event_btnCargarImgActionPerformed

    private void btnPHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPHActionPerformed
        // TODO add your handling code here
        this.enviarMov("PH "+ this.selFila+this.selCol);
        
        
    }//GEN-LAST:event_btnPHActionPerformed

    private void btnPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDActionPerformed
        // TODO add your handling code here:
        this.enviarMov("PD "+ this.selFila+this.selCol);
        
        
    }//GEN-LAST:event_btnPDActionPerformed

    private void btnSFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSFActionPerformed
        // TODO add your handling code here:
        this.enviarMov("SF "+ this.selFila+this.selCol);
    }//GEN-LAST:event_btnSFActionPerformed

    private void btnPDJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDJActionPerformed
        // TODO add your handling code here:
        // usar colorDeMov de la clase partida
        //habilitar botones para seleccionar color según los colores que devuelva colorDeMov
        //al hacer click en ese botón, ejecutar el movimiento según las piedras seleccionadas
        //recorrer botones del tablero y segun las pintadas obtener las coordenadas
        
    }//GEN-LAST:event_btnPDJActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VentPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VentPartida().setVisible(true);
//            }
//        });
//    }
    private class ListenerBoton implements ActionListener {

        private int x;
        private int y;

        public ListenerBoton(int i, int j) {
            // en el constructor se almacena la fila y columna que se presionó
            x = i;
            y = j;
        }

        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método
            clickBoton(x, y);
        }
    }

    private void clickBoton(int fila, int columna) {
        // Método a completar!.
        // En fila y columna se reciben las coordenas donde presionó el usuario, relativas al comienzo de la grilla
        // fila 1 y columna 1 corresponden a la posición de arriba a la izquierda.
        // Debe indicarse cómo responder al click de ese botón.
        OcultarComandos();
        String tipo = botones[fila][columna].getName();
        if(tipo.equals("#")){
            if (!this.esPiedra) {
                Pintar(this.selFila, this.selCol);
                Pintar(fila, columna);
                this.selFila = fila;
                this.selCol = columna;
                this.esPiedra = true;
            }else{
                btnPDC.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDC", this.selFila, this.selCol, fila, columna));
                btnPDD.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDD", this.selFila, this.selCol, fila, columna));
                btnPDJ.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDJ", this.selFila, this.selCol, fila, columna));
                btnPDS.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDS", this.selFila, this.selCol, fila, columna));
            }
        }else if (!tipo.equals(" ")) {
            Pintar(this.selFila, this.selCol);
            Pintar(fila, columna);
            this.selFila = fila;
            this.selCol = columna;
            this.esPiedra = false;
            btnPH.setEnabled(true);
            btnPD.setEnabled(true);
            btnSF.setEnabled(true);
        }

        
        
        
    }
    private void enviarMov(String mov){
        
        if (!elJuego.getPartida().getFinal()) {
            if (elJuego.getPartida().ejecutarMovimiento(mov)) {
                actualizarTablero();
            }
        } else{
            
        }
        
    }
    private void OcultarComandos(){
        btnPH.setEnabled(false);
        btnPD.setEnabled(false);
        btnPDC.setEnabled(false);
        btnPDD.setEnabled(false);
        btnPDJ.setEnabled(false);
        btnPDS.setEnabled(false);
    }
    
    private void Pintar(int fila, int columna){
        try{
            if (this.botones[fila][columna].getBackground()==Color.lightGray) {
                this.botones[fila][columna].setBackground(null);
           } else {
               this.botones[fila][columna].setBackground(Color.lightGray);
           }
        }catch (Exception ex) {
            
        }
        
    }

    private class ListenerBotonPiedra implements ActionListener {

        private int indice;
        private float coordX;
        private float coordY;

        public ListenerBotonPiedra(int i) {
            // en el constructor se almacena el indice que se presionó
            indice = i;
           
        }
        
        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método    
          
        }
        
        private void clickBoton() {
        // Método a completar!.
        // En fila y columna se reciben las coordenas donde presionó el usuario, relativas al comienzo de la grilla
        // fila 1 y columna 1 corresponden a la posición de arriba a la izquierda.
        // Debe indicarse cómo responder al click de ese botón.
         }
    }
    private void DespintarTablero(){
        for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    botones[i][j].setBackground(null);
                }
        }
    }

    private class ListenerBotonFicha implements ActionListener {

        private int x;
        private int y;
        private boolean esElJugador1;

        public ListenerBotonFicha(int i, int j, boolean esJugador1) {
            // en el constructor se almacena el indice que se presionó
            x = i;
            y = j;
            esElJugador1 = esJugador1;
            
        }

        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarGoogle;
    private javax.swing.JButton btnCargarImg;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnPD;
    private javax.swing.JButton btnPDC;
    private javax.swing.JButton btnPDD;
    private javax.swing.JButton btnPDJ;
    private javax.swing.JButton btnPDS;
    private javax.swing.JButton btnPH;
    private javax.swing.JButton btnSF;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblPiedras;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel panelFichasJ1;
    private javax.swing.JPanel panelFichasJ2;
    private javax.swing.JPanel panelImagenes;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelLetras;
    private javax.swing.JPanel panelNumeros;
    private javax.swing.JPanel panelPiedras;
    private javax.swing.JTextField txtBuscarGoogle;
    // End of variables declaration//GEN-END:variables
}
