/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfasGrafica;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import prog2SO.Juego;

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
    private JButton[] piedras;
    private File fichero;
    private Juego elJuego;

    public VentPartida(Juego iJuego) {
        initComponents();
        this.elJuego = iJuego;
        this.setLayout(null);
        // crear botones y agregarlos al panel
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
        //Probando cosas
        
        this.actualizarTablero();
        
        
        //Fin de prueba de cosas
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

    }
    private void actualizarTablero(){
        char[][] tablero = this.elJuego.getPartida().getTablero().getTabla();
        try {
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    botones[i][j].setText(String.valueOf(tablero[i-1][j-1]));
                    
                }
                
            }
        } catch (Exception e) {
        }
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFichasJ1Layout.setVerticalGroup(
            panelFichasJ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFichasJ2Layout = new javax.swing.GroupLayout(panelFichasJ2);
        panelFichasJ2.setLayout(panelFichasJ2Layout);
        panelFichasJ2Layout.setHorizontalGroup(
            panelFichasJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFichasJ2Layout.setVerticalGroup(
            panelFichasJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblJugador1.setText("lblJugador1");

        lblJugador2.setText("lblJugador2");

        lblPiedras.setText("lblPiedras");

        javax.swing.GroupLayout panelPiedrasLayout = new javax.swing.GroupLayout(panelPiedras);
        panelPiedras.setLayout(panelPiedrasLayout);
        panelPiedrasLayout.setHorizontalGroup(
            panelPiedrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        panelPiedrasLayout.setVerticalGroup(
            panelPiedrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTurno))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblJugador2)
                            .addComponent(lblPiedras)
                            .addComponent(lblJugador1))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelPiedras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelFichasJ2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelFichasJ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panelImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTurno)
                        .addGap(311, 311, 311))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelFichasJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFichasJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblJugador1)
                        .addGap(88, 88, 88)
                        .addComponent(lblJugador2)
                        .addGap(84, 84, 84)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelPiedras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addComponent(panelImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPiedras)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        VentCargarImagen ventana = new VentCargarImagen();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png");
        ventana.jfcCargarImagen.setFileFilter(filtro);
        resultado = ventana.jfcCargarImagen.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == resultado) {
            fichero = ventana.jfcCargarImagen.getSelectedFile();
            try {
                ImageIcon icon = new ImageIcon(fichero.toString());
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(botones[1][1].getWidth(), botones[1][1].getHeight(), Image.SCALE_DEFAULT));
                //recorrer botones fuscar piedra y cambiar icono
                botones[1][1].setIcon(icono);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error abriendo la imagen " + ex);
            }
        }
    }//GEN-LAST:event_btnCargarImgActionPerformed

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
        if (this.botones[fila][columna].getBackground()==Color.lightGray) {
             this.botones[fila][columna].setBackground(null);
        } else {
            this.botones[fila][columna].setBackground(Color.lightGray);
        }
        
    }

    private class ListenerBotonPiedra implements ActionListener {

        private int x;

        public ListenerBotonPiedra(int i) {
            // en el constructor se almacena el indice que se presionó
            x = i;
        }

        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método

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
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblPiedras;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JPanel panelFichasJ1;
    private javax.swing.JPanel panelFichasJ2;
    private javax.swing.JPanel panelImagenes;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelPiedras;
    private javax.swing.JTextField txtBuscarGoogle;
    // End of variables declaration//GEN-END:variables
}
