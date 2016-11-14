/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfasGrafica;

import Dominio.Juego;
import Dominio.Jugador;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mjpla
 */
public class VentRanking extends javax.swing.JFrame {
    
    private Juego elJuego;

    /**
     * Creates new form VentRanking
     */
    public VentRanking(Juego unJuego) {
        initComponents();
        this.elJuego = unJuego;
        ranking();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRanking = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRanking = new javax.swing.JTable();

        setTitle("Ranking de Jugadres");
        getContentPane().setLayout(null);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        tablaRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Alias", "Edad", "Ganadas", "Perdidas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRanking.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaRanking);

        javax.swing.GroupLayout panelRankingLayout = new javax.swing.GroupLayout(panelRanking);
        panelRanking.setLayout(panelRankingLayout);
        panelRankingLayout.setHorizontalGroup(
            panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRankingLayout.createSequentialGroup()
                .addGroup(panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRankingLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRankingLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelRankingLayout.setVerticalGroup(
            panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRankingLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        getContentPane().add(panelRanking);
        panelRanking.setBounds(10, 19, 560, 380);

        setBounds(0, 0, 619, 462);
    }// </editor-fold>//GEN-END:initComponents

    private void ranking(){
       ArrayList<Jugador> aux = this.elJuego.getRanking();
        Iterator<Jugador> iter = aux.iterator();
        DefaultTableModel modelo=(DefaultTableModel) this.tablaRanking.getModel();
        while (iter.hasNext()){
            Jugador unJugador = iter.next();
            Object [] fila=new Object[5];
            fila[0]=unJugador.getNombre();
            fila[1]=unJugador.getAlias();
            fila[2]=unJugador.getEdad();
            fila[3]=unJugador.getGanadas();
            fila[4]=unJugador.getPerdidas();
            modelo.addRow(fila);
        }
        this.tablaRanking.setModel(modelo);
    }
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(VentRanking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentRanking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentRanking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentRanking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VentRanking().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelRanking;
    private javax.swing.JTable tablaRanking;
    // End of variables declaration//GEN-END:variables
}
