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
import javax.imageio.ImageIO;

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
    private JButton[] btnColores;
    private JButton[] piedras;
    private Juego elJuego;
    private int PiedrasSelec;
    private int selFila;
    private int selCol;
    private String movimiento;
    private String coloresDeMov;
    
    //movimiento del jugador "codMov fil1col1 fil2col2 color1color2

    public VentPartida(Juego iJuego) {
        initComponents();
        this.elJuego = iJuego;
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setResizable(false);
        
        // crear botones y agregarlos al panel
        //////////////////////////////////////
        //Colores para elegir
        panelColores.setLayout(new GridLayout(1,4));
        btnColores = new JButton[4];
        String strColores ="ravc";
        for (int i=0;i<4;i++){
            JButton unBoton = new JButton();
            panelColores.add(unBoton);
            btnColores[i]=unBoton;
            btnColores[i].setName(""+strColores.charAt(i));
            btnColores[i].addActionListener(new ListenerBotonColor(btnColores[i].getName(), i));
        }
        btnColores[0].setBackground(Color.red);
        btnColores[1].setBackground(Color.blue);
        btnColores[2].setBackground(Color.green);
        btnColores[3].setBackground(Color.cyan);
        
        //////////////////////////////////////
        //TABLERO//////
        panelJuego.setLayout(new GridLayout(7, 7));
        botones = new JButton[7][7];
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                
                if (i==0 && j>0){
                    JButton jButton = new JButton();
                    panelJuego.add(jButton);
                    botones[i][j] = jButton;
//                    ImageIcon icon = new ImageIcon("src\\Imagenes\\"+j+".png");
//                    Icon icono= new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
                    try {
                        Image img=ImageIO.read(getClass().getResource("/"+j+".png"));
                        botones[i][j].setIcon(new ImageIcon(img.getScaledInstance(30,30, Image.SCALE_DEFAULT)));
                    } catch (IOException e) {
                        System.out.println("InterfasGrafica.VentPartida.<init>()");
                    }
                    //botones[i][j].setIcon(icono);
                   
                }else{
                    if (j==0 && i>0){
                        JButton jButton = new JButton();
                        panelJuego.add(jButton);
                        botones[i][j] = jButton;
                        //ImageIcon icon = new ImageIcon("src\\Imagenes\\Letra"+i+".png");
                        //Icon icono= new ImageIcon(icon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
                        try {
                            Image img=ImageIO.read(getClass().getResource("/Letra"+i+".png"));
                            botones[i][j].setIcon(new ImageIcon(img.getScaledInstance(30,30, Image.SCALE_DEFAULT)));
                        } catch (IOException e) {
                            lblAvisos.setText("Problema al cargar algunas imagenee.");
                        }
                        //botones[i][j].setIcon(icono);
                        botones[i][j].setBorderPainted(true);
                        
                    }else{
                        if ( j==0 && i==0){
                            JButton jButton = new JButton();
                            panelJuego.add(jButton);
                            botones[i][j] = jButton;
                            botones[i][j].setVisible(false);
                        }else{
                                JButton jButton = new JButton();
                                jButton.addActionListener(new ListenerBoton(i, j));
                                panelJuego.add(jButton);
                                botones[i][j] = jButton;
                        } 
                    }
                }
            }
        }
        ////////
        //FICHAS DE JUGADORES
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
        /////////////////////////////
        ////BOTONES DE PIEDRAS
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
        
        this.actualizarTablero();

    }
    
    
    private void actualizarTablero(){
        this.elJuego.getPartida().finDeTurno();
        this.lblTurno.setText("Turno de "+ this.elJuego.getPartida().getTurno());
        
        int cant;
        char[][] tablero = this.elJuego.getPartida().getTablero().getTabla();
        ArrayList<Character> auxFichasJ1 = this.elJuego.getPartida().getJ1Fichas();
        ArrayList<Character> auxFichasJ2 = this.elJuego.getPartida().getJ2Fichas();
        int auxPiedras = this.elJuego.getPartida().getPiedrasNegras();
            this.lblTurno.setText("Turno de "+ this.elJuego.getPartida().getTurno());
            
            this.jListAyuda.setVisible(false);
            
            btnPD.setEnabled(false);
            btnPDC.setEnabled(false);
            btnPDD.setEnabled(false);
            btnPDS.setEnabled(false);
            btnPDJ.setEnabled(false);
            btnPH.setEnabled(false);
            btnSF.setEnabled(false);
            this.btnDescartar.setEnabled(false);
            this.desHabilitarColores();
            movimiento="";
            coloresDeMov="";
            lblColores.setText("Clic sobre el color del movimiento");
            lblAvisos.setVisible(false);
            Pintar(selFila,selCol);
            //tablero
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (tablero[i-1][j-1] != ' '){
                        botones[i][j].setName(String.valueOf(tablero[i-1][j-1]));
                        botones[i][j].setIcon(this.elJuego.getPartida().getTablero().setColores(tablero[i-1][j-1]));
                        //botones[i][j].setRolloverIcon(this.elJuego.getPartida().getTablero().setColores(tablero[i-1][j-1]));
                    } else {
                        botones[i][j].setName(" ");
                        botones[i][j].setIcon(null);
                    }
                    
                    botones[i][j].setBackground(null);
                }
            }
            
           //piedras
           Icon iconoPiedras = new ImageIcon(this.elJuego.getPartida().getTablero().getIconoPiedra().getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
            for (int i =0;i<10;i++){
                if (i < auxPiedras) {
                    this.piedras[i].setIcon(iconoPiedras);
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
                        this.fichasJ1[i][j].setName(" ");
                        this.fichasJ1[i][j].setIcon(null);
                    }
                    //Jugador 2
                    if (j<cantJ2) {
                        this.fichasJ2[i][j].setName(String.valueOf(tipo));
                        this.fichasJ2[i][j].setIcon(this.elJuego.getPartida().getTablero().setColores(tipo));
                    } else {
                        this.fichasJ2[i][j].setName(" ");
                        this.fichasJ2[i][j].setIcon(null);
                    }
                }
            }
            if (this.elJuego.getPartida().getTurno().equals(this.elJuego.getPartida().getJugador1().getAlias())) {
                this.matrizEnable(this.fichasJ2, false);
                this.matrizEnable(this.fichasJ1, true);
            } else {
                this.matrizEnable(this.fichasJ2, true);
                this.matrizEnable(this.fichasJ1, false);
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
        panelColores = new javax.swing.JPanel();
        lblColores = new javax.swing.JLabel();
        lblAvisos = new javax.swing.JLabel();
        btnAyuda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAyuda = new javax.swing.JList<>();
        lblAyuda = new javax.swing.JLabel();
        btnAbandonar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setSize(new java.awt.Dimension(600, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        btnBuscarGoogle.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnBuscarGoogle.setText("Buscar en Google");
        btnBuscarGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGoogleActionPerformed(evt);
            }
        });

        txtBuscarGoogle.setToolTipText("Buscar imagen en google");

        btnCargarImg.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
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
                .addComponent(btnBuscarGoogle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnCargarImg)
                .addContainerGap())
        );
        panelImagenesLayout.setVerticalGroup(
            panelImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagenesLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(panelImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarGoogle)
                    .addComponent(txtBuscarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarImg))
                .addContainerGap())
        );

        lblTurno.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 153, 102));
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
            .addGap(0, 407, Short.MAX_VALUE)
        );
        panelFichasJ2Layout.setVerticalGroup(
            panelFichasJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        lblJugador1.setFont(new java.awt.Font("Verdana", 3, 18)); // NOI18N
        lblJugador1.setForeground(new java.awt.Color(51, 102, 255));
        lblJugador1.setText("Jugador1");

        lblJugador2.setFont(new java.awt.Font("Verdana", 3, 18)); // NOI18N
        lblJugador2.setForeground(new java.awt.Color(255, 0, 51));
        lblJugador2.setText("Jugador2");

        lblPiedras.setFont(new java.awt.Font("Verdana", 3, 18)); // NOI18N
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

        btnPH.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPH.setText("Poner Horizontal");
        btnPH.setEnabled(false);
        btnPH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPHActionPerformed(evt);
            }
        });

        btnPD.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPD.setText("Poner Diagonal");
        btnPD.setEnabled(false);
        btnPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDActionPerformed(evt);
            }
        });

        btnPDS.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPDS.setText("Patrón dos separadas");
        btnPDS.setEnabled(false);
        btnPDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDSActionPerformed(evt);
            }
        });

        btnPDJ.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPDJ.setText("Patrón dos juntas");
        btnPDJ.setEnabled(false);
        btnPDJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDJActionPerformed(evt);
            }
        });

        btnPDD.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPDD.setText("Patrón dos diagonal");
        btnPDD.setEnabled(false);
        btnPDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDDActionPerformed(evt);
            }
        });

        btnPDC.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPDC.setText("Patrón dos caballo");
        btnPDC.setEnabled(false);
        btnPDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDCActionPerformed(evt);
            }
        });

        btnDescartar.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnDescartar.setText("Descartar ficha");
        btnDescartar.setEnabled(false);
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });

        btnSF.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnSF.setText("Sacar Ficha");
        btnSF.setEnabled(false);
        btnSF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelColoresLayout = new javax.swing.GroupLayout(panelColores);
        panelColores.setLayout(panelColoresLayout);
        panelColoresLayout.setHorizontalGroup(
            panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        panelColoresLayout.setVerticalGroup(
            panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lblColores.setFont(new java.awt.Font("Verdana", 3, 13)); // NOI18N
        lblColores.setForeground(new java.awt.Color(255, 0, 0));
        lblColores.setText("Clic sobre el color del movimiento");

        lblAvisos.setFont(new java.awt.Font("Verdana", 3, 13)); // NOI18N
        lblAvisos.setForeground(new java.awt.Color(255, 51, 51));
        lblAvisos.setText("lblAvisos");

        btnAyuda.setText("Ayuda");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);

        jListAyuda.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListAyuda);

        lblAyuda.setFont(new java.awt.Font("Verdana", 3, 18)); // NOI18N
        lblAyuda.setForeground(new java.awt.Color(255, 0, 51));
        lblAyuda.setText("Ayuda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblAyuda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnAbandonar.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnAbandonar.setForeground(new java.awt.Color(255, 0, 51));
        btnAbandonar.setText("Abandonar");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAvisos, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelFichasJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPiedras)
                                            .addComponent(panelPiedras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelFichasJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnPH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPDS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPDJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDescartar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblColores, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAyuda))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panelColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(124, 124, 124)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAbandonar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPD))
                            .addComponent(btnAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(btnDescartar)
                        .addGap(18, 18, 18)
                        .addComponent(lblColores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAvisos)
                        .addGap(36, 36, 36)
                        .addComponent(lblJugador1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFichasJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblJugador2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFichasJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPiedras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelPiedras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbandonar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG o PNG", "jpg", "png");
        ventana.jfcCargarArchivos.setFileFilter(filtro);
        resultado = ventana.jfcCargarArchivos.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == resultado) {
            fichero = ventana.jfcCargarArchivos.getSelectedFile();
            
            if( (fichero.toString().contains(".jpg")) || (fichero.toString().contains(".png")) ||
                 (fichero.toString().contains(".JPG")) || (fichero.toString().contains(".PNG"))){
                
                this.elJuego.getPartida().getTablero().setIconoPiedra(new ImageIcon(fichero.toString()));
                ImageIcon icon = this.elJuego.getPartida().getTablero().getIconoPiedra();
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                //recorrer botones fuscar piedra y cambiar icono
                for (int i=1;i<=6;i++){
                    for(int j=1;j<=6;j++){
                           if(botones[i][j].getName().equals("#")){
                            botones[i][j].setIcon(icono);
                            } 
                    }
                }
                int auxPiedras = this.elJuego.getPartida().getPiedrasNegras();
                for (int i =0;i<auxPiedras;i++){
                     this.piedras[i].setIcon(icono);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Solamente se aceptan archivos \"jpg\" o \"png\".","Error al cargr archivo",0);
            }   
        }
        
    }//GEN-LAST:event_btnCargarImgActionPerformed

    private void btnPHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPHActionPerformed
        // TODO add your handling code here
        String coor = this.getCoordenadasDelTablero();
        this.enviarMov("PH "+ coor);
        
        
    }//GEN-LAST:event_btnPHActionPerformed

    private void btnPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDActionPerformed
        // TODO add your handling code here:
        String coor = this.getCoordenadasDelTablero();
        this.enviarMov("PD "+ coor);
        
        
    }//GEN-LAST:event_btnPDActionPerformed

    private void btnSFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSFActionPerformed
        // TODO add your handling code here:
        String coor = this.getCoordenadasDelTablero();
        this.enviarMov("SF "+ coor);
    }//GEN-LAST:event_btnSFActionPerformed

    private void btnPDJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDJActionPerformed
        // TODO add your handling code here:
        String codMov = "PDJ";
        if(this.elJuego.getPartida().colorDeMov(codMov).equals("acrv")){
            codMov+=" "+this.getCoordenadasDelTablero();
            this.enviarMov(codMov);
        }else{
            this.lblAvisos.setText("No tiene las fichas necesarias para realizar el movimiento");
            this.lblAvisos.setVisible(true);
        }
    }//GEN-LAST:event_btnPDJActionPerformed

    private void btnPDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDSActionPerformed
         // TODO add your handling code here:
         this.patronesSimilares("PDS");
         
    }//GEN-LAST:event_btnPDSActionPerformed

    private void btnPDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDDActionPerformed
        // TODO add your handling code here:
        lblColores.setText("Clic en color con 3 o más fichas");
        this.patronesSimilares("PDD");
    }//GEN-LAST:event_btnPDDActionPerformed

    private void btnPDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDCActionPerformed
         // TODO add your handling code here:
         this.patronesSimilares("PDC");
    }//GEN-LAST:event_btnPDCActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
         // TODO add your handling code here:
         movimiento = "d"+" "+this.coloresDeMov;
         this.enviarMov(movimiento);
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        elJuego.getPartida().ejecutarMovimiento("X");
        JOptionPane.showMessageDialog(null, "Ganador " +elJuego.getPartida().getElGanador(), "" + "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_formWindowClosing

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        // TODO add your handling code here:
        ArrayList<String> ayuda =  elJuego.getPartida().mostrarAyuda();
        if (!ayuda.isEmpty()) {
            //JOptionPane.showMessageDialog(null,ayuda.toArray() , "Ayuda" , JOptionPane.QUESTION_MESSAGE);
            DefaultListModel<String> modelo = new DefaultListModel<String>();
            
            //this.jListAyuda = new JList(ayuda.toArray());
            for (int i = 0; i < ayuda.size(); i++) {
                modelo.addElement(ayuda.get(i));
            }
            this.jListAyuda.setModel(modelo);
            this.jListAyuda.setVisible(true);
        }else{
            lblAvisos.setText("No hay patrones posibles");
            lblAvisos.setVisible(true);
        }
        
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
        // TODO add your handling code here:
        if ((JOptionPane.showConfirmDialog(null,"¿Quiere abandonar la partida?","Confirmación",2))==0){
             elJuego.getPartida().ejecutarMovimiento("X");
            JOptionPane.showMessageDialog(null, "Ganador " +elJuego.getPartida().getElGanador(), "" + "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        
        }
    }//GEN-LAST:event_btnAbandonarActionPerformed

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
        btnDescartar.setEnabled(false);
        OcultarComandos();
        String tipo = botones[fila][columna].getName();
        if(tipo.equals("#")){
            if (PiedrasSelec == 0) {
                DespintarTablero();     
                Pintar(fila, columna);
                PiedrasSelec = 1;
//                Pintar(this.selFila, this.selCol);
//                Pintar(fila, columna);
//                this.selFila = fila;
//                this.selCol = columna;
//                this.esPiedra = true;
            }else if (PiedrasSelec == 1){
                Pintar(fila, columna);
                PiedrasSelec = 2;
                String[] coor = getCoordenadasDelTablero().split(" ");
                int fila1 = Integer.parseInt(coor[0].substring(0, 1));
                int col1 = Integer.parseInt(coor[0].substring(1, 2));
                int fila2 = Integer.parseInt(coor[1].substring(0, 1));
                int col2 = Integer.parseInt(coor[1].substring(1, 2));
                btnPDC.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDC", fila1, col1, fila2, col2));
                btnPDD.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDD", fila1, col1, fila2, col2));
                btnPDJ.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDJ", fila1, col1, fila2, col2));
                btnPDS.setEnabled(this.elJuego.getPartida().getTablero().validarPatron("PDS", fila1, col1, fila2, col2));
            } else{
                DespintarTablero();     
                Pintar(fila, columna);
                PiedrasSelec = 1;
            }
            //Pintar(fila, columna);
            //this.esPiedra = true;
        }else if (!tipo.equals(" ")) {
            DespintarTablero();
            Pintar(fila, columna);
            PiedrasSelec = 0;
            btnPH.setEnabled(true);
            btnPD.setEnabled(true);
            btnSF.setEnabled(true);
//            Pintar(this.selFila, this.selCol);
//            Pintar(fila, columna);
//            this.selFila = fila;
//            this.selCol = columna;
//            this.esPiedra = false;
//            btnPH.setEnabled(true);
//            btnPD.setEnabled(true);
//            btnSF.setEnabled(true);
        }

        
        
        
    }
    private void enviarMov(String mov){
        
        if (elJuego.getPartida().ejecutarMovimiento(mov)) {
            
            actualizarTablero();
        
        }else{
        
            lblAvisos.setText("No se pudo ejecutar el movimiento");
            lblAvisos.setVisible(true);
            coloresDeMov="";
        
        }
        
        if (elJuego.getPartida().getFinal()) {
            //Terminar partida
            JOptionPane.showMessageDialog(null, "Ganador " +elJuego.getPartida().getElGanador(), "" + "Fin de partida", JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose();
        }
        
    }
    private void OcultarComandos(){
        btnPH.setEnabled(false);
        btnPD.setEnabled(false);
        btnSF.setEnabled(false);
        btnPDC.setEnabled(false);
        btnPDD.setEnabled(false);
        btnPDJ.setEnabled(false);
        btnPDS.setEnabled(false);
        btnDescartar.setEnabled(false);
    }
    
    private void Pintar(int fila, int columna){
        
        if (this.botones[fila][columna].getBackground()==Color.lightGray) {
            this.botones[fila][columna].setBackground(null);
       } else {
           this.botones[fila][columna].setBackground(Color.lightGray);
       }
            
    }
    
     private void DespintarTablero(){
        for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    botones[i][j].setBackground(null);
                }
        }
    }
     
     private String getCoordenadasDelTablero(){
         /*recorre el tablero y devuele un string con las coordenadas
          seleccionadas en el formato correcto para realizar
         un movimiento*/
         String coord="";
         for(int i=1;i<=6;i++){
            for (int j=1;j<=6;j++){
                if (botones[i][j].getBackground()==Color.lightGray){
                    coord +=i+""+j+" ";
                }
            }
                    
         }
         return coord;
     }
     
     private void habilitarColores(String colores){
         /*habilita los botones de colores
         aptos para un movimiento seleccionado
         */
         desHabilitarColores();
            panelColores.setVisible(true);
            lblColores.setVisible(true);
         for (int i=0;i<colores.length();i++){
             //recorro colores aptos
            for (int j =0; j <= 3; j++){
                //recorro los botones de colores
                if (btnColores[j].getName().equals(String.valueOf(colores.charAt(i)))){
                    btnColores[j].setVisible(true);
                }
            }
         }
     }
     private void desHabilitarColores(){
         for (int j =0; j <= 3; j++){
                //recorro los botones de colores
                btnColores[j].setVisible(false);
          }
          panelColores.setVisible(false);
          lblColores.setVisible(false);
     }
     
     private void patronesSimilares(String codMov){
         //para pds, pdc,pdd
         String coloresDisponibles = this.elJuego.getPartida().colorDeMov(codMov);
         
         if (!coloresDisponibles.equals("")){
             
            this.habilitarColores(coloresDisponibles);
            this.movimiento= codMov+" "+this.getCoordenadasDelTablero();
         }else{
             lblAvisos.setText("No tiene las fichas necesarias para realizar el movimiento");
             lblAvisos.setVisible(true);
         }
     }
     
     private void matrizEnable(JButton[][] matriz,boolean habilitar){
         for (int i = 0; i < matriz.length;i++){
             for (int j =0; j < matriz[i].length ; j++){
                 matriz[i][j].setEnabled(habilitar);
             }
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
            clickFicha(x,y,esElJugador1);
        }
        private void clickFicha(int fila, int col, boolean esJugador1){
            
            JButton[][] aux ;
            if (esJugador1){
              aux=fichasJ1;  
            }else{
                aux=fichasJ2;
            }   
            coloresDeMov = aux[fila][col].getName();
            if (!coloresDeMov.equals(" ")){
                OcultarComandos();
                DespintarTablero();
                btnDescartar.setEnabled(true);
            }
        }
    }
    private class ListenerBotonColor implements ActionListener {

        private String color;
        private int indice;
        

        public ListenerBotonColor(String unColor, int unIndice) {
            // en el constructor se almacena el indice que se presionó
            color=unColor;
            indice = unIndice;
            
        }

        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método
           clickColor(color,indice);
        }
        
        private void clickColor(String color,int posBtn) {
            
           
           coloresDeMov+=color;
           String[] aux = movimiento.split(" ");
           //separo el movimietno para saber que movimiento quiere hacer
           String codMovAux= aux[0];
           if((codMovAux.equals("PDS")  ||  codMovAux.equals("PDC"))    &&  coloresDeMov.length()   ==  1){
               //pds y pdc necesitan un solo color
               movimiento   +=coloresDeMov;
               //armo la cadena para ejecutar mov
               enviarMov(movimiento);
           }else{
               //caso PDD
               if (coloresDeMov.length()==2){
                   movimiento+=coloresDeMov;
                   //armo la cadena para ejecutar mov
                   enviarMov(movimiento);
                }else{
                   //solicito el proximo color y deshabilito el boton que recién seleccionó
                   //chequeo si el primer color seleccionado tiene 3 o más fichas disponibles
                   ArrayList<Character> fichasAux = elJuego.getPartida().getFichasCorrespondientes(true);
                   if ( cuantasFichas(fichasAux , btnColores[posBtn].getName().charAt(0) ) >= 3){
                       lblColores.setText("Necesita un color más");
                       btnColores[posBtn].setVisible(false);
                   }else{
                       
                      lblColores.setText("Seleccione color con mas de 3 fichas");
                      coloresDeMov="";
                      JOptionPane.showMessageDialog(null, "Debe seleccionar primero un color con más de 3 fichas.\n Vuelva a seleccionar un color");
                   }
                   
               }
            }
              
           
         }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnAyuda;
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
    private javax.swing.JList<String> jListAyuda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvisos;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JLabel lblColores;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblPiedras;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel panelColores;
    private javax.swing.JPanel panelFichasJ1;
    private javax.swing.JPanel panelFichasJ2;
    private javax.swing.JPanel panelImagenes;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelPiedras;
    private javax.swing.JTextField txtBuscarGoogle;
    // End of variables declaration//GEN-END:variables
}
