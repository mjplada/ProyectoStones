/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfasGrafica;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
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
    
    public VentPartida(Juego iJuego) {
        initComponents();
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
        btnBuscarGoogle = new javax.swing.JButton();
        txtBuscarGoogle = new javax.swing.JTextField();

        setSize(new java.awt.Dimension(600, 400));

        panelJuego.setName("panelJuego"); // NOI18N

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        btnBuscarGoogle.setText("Buscar en Google");
        btnBuscarGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGoogleActionPerformed(evt);
            }
        });

        txtBuscarGoogle.setToolTipText("Buscar imagen en google");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnBuscarGoogle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarGoogle)
                    .addComponent(txtBuscarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarGoogleActionPerformed
        // TODO add your handling code here:
        String url = "https://www.google.com.uy/?gws_rd=ssl#q="+this.txtBuscarGoogle.getText().replace(' ','+');
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException ex) {
        }catch(IOException e){
        }
    }//GEN-LAST:event_btnBuscarGoogleActionPerformed

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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarGoogle;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JTextField txtBuscarGoogle;
    // End of variables declaration//GEN-END:variables
}
