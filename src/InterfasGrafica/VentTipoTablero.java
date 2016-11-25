/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfasGrafica;

import java.awt.Dimension;
import Dominio.Juego;
import Dominio.Jugador;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mjpla
 */

public class VentTipoTablero extends javax.swing.JFrame  {
    private VentPartida vPartida;
    private Juego miJuego;
    private int cantJugadores;
    private Jugador jugador1;
    private Jugador jugador2;
    
    /**
     * Creates new form VentTipoTablero
     */
    public VentTipoTablero(Juego iJuego) {
        
        initComponents();
        this.setSize(new Dimension(517,372));
        setResizable(false);
         this.setLocationRelativeTo(null);
        miJuego=iJuego;
        cargoTabla();
        
        cantJugadores=0;
        this.tablaJugadores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int fila =tablaJugadores.getSelectedRow(); 
                String alias ="";
                if ( fila > -1) {
                    if (JOptionPane.showConfirmDialog(null, "¿Seleciona este Jugador?")==0){
                         alias = tablaJugadores.getValueAt(fila,0).toString();
                         ArrayList<Jugador> aux = miJuego.getJugadores();
                         Iterator<Jugador> iter = aux.iterator();
                         DefaultTableModel modelo = (DefaultTableModel)tablaJugadores.getModel();
                         while (iter.hasNext()){
                             Jugador unJugador= iter.next();
                             if (unJugador.getAlias().equals(alias)){
                                 if (cantJugadores==0){
                                     jugador1=unJugador;
                                     cantJugadores++;
                                     modelo.removeRow(fila); 
                                     lblAviso.setText("Jugador1: "+alias+". Selleccione al jugador 2");
                                 }else{
                                     jugador2=unJugador;
                                      modelo.removeRow(fila);
                                     lblAviso.setText("¡Elija el tablero para comenzar!");
                                     tablaJugadores.enable(false);
                                 }
                             }
                         }
                    }
                }
            }
}       );
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     **/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTipoTablero = new javax.swing.JPanel();
        btnPorDefecto = new javax.swing.JButton();
        btnRandom = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaJugadores = new javax.swing.JTable();
        lblAviso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar Tablero");
        setResizable(false);
        setSize(new java.awt.Dimension(400, 400));
        getContentPane().setLayout(null);

        btnPorDefecto.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnPorDefecto.setText("Tablero por Defecto");
        btnPorDefecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorDefectoActionPerformed(evt);
            }
        });

        btnRandom.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        btnRandom.setText("Tablero Random");
        btnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomActionPerformed(evt);
            }
        });

        tablaJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alias", "Edad", "Partidas Ganadas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaJugadores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaJugadores.setColumnSelectionAllowed(true);
        tablaJugadores.getTableHeader().setReorderingAllowed(false);
        tablaJugadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaJugadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaJugadores);
        tablaJugadores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lblAviso.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        lblAviso.setText("Seleccione un jugdor de la lista ");

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Stones.png"))); // NOI18N

        javax.swing.GroupLayout panelTipoTableroLayout = new javax.swing.GroupLayout(panelTipoTablero);
        panelTipoTablero.setLayout(panelTipoTableroLayout);
        panelTipoTableroLayout.setHorizontalGroup(
            panelTipoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoTableroLayout.createSequentialGroup()
                .addGroup(panelTipoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTipoTableroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTipoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelTipoTableroLayout.createSequentialGroup()
                                .addComponent(btnRandom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPorDefecto))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTipoTableroLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(lblAviso))
                    .addGroup(panelTipoTableroLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        panelTipoTableroLayout.setVerticalGroup(
            panelTipoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoTableroLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTipoTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPorDefecto)
                    .addComponent(btnRandom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(82, 82, 82))
        );

        getContentPane().add(panelTipoTablero);
        panelTipoTablero.setBounds(10, 10, 680, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargoTabla(){

        ArrayList<Jugador> aux = this.miJuego.getJugadores();
        Iterator<Jugador> iter = aux.iterator();
        DefaultTableModel modelo=(DefaultTableModel) this.tablaJugadores.getModel();
        while (iter.hasNext()){
            Jugador unJugador = iter.next();
            Object [] fila=new Object[3];
            fila[0]=unJugador.getAlias();
            fila[1]=unJugador.getEdad();
            fila[2]=unJugador.getGanadas();
            modelo.addRow(fila);
        }
        this.tablaJugadores.setModel(modelo);
    }   
    
    
    private void btnPorDefectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorDefectoActionPerformed
        // TODO add your handling code here:
        try {
            if(this.jugador2!=null){
                this.miJuego.setEsGenerico(true);
                miJuego.iniciarPartida(jugador1,jugador2,miJuego.getEsGenerico());
                vPartida = new VentPartida(miJuego);
                this.vPartida.setVisible(true);
                this.dispose();
           }else{
               this.lblAviso.setText("Debe seleccionar jugadores");
           }
        } catch (IOException e) {
            
        }
       
    }//GEN-LAST:event_btnPorDefectoActionPerformed

    
    private void btnRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomActionPerformed
        // TODO add your handling code here:
        try {
            if(this.jugador2!=null){
                this.miJuego.setEsGenerico(false);
                miJuego.iniciarPartida(jugador1,jugador2,miJuego.getEsGenerico());
                vPartida = new VentPartida(miJuego);
                this.vPartida.setVisible(true);
                this.dispose();
            }else{
               this.lblAviso.setText("Debe seleccionar jugadores");
           }
        } catch (IOException e) {
            lblAviso.setText("Error al cargar las imagenes.");
        }
        
    }//GEN-LAST:event_btnRandomActionPerformed

    private void tablaJugadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaJugadoresMouseClicked

    }//GEN-LAST:event_tablaJugadoresMouseClicked

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
//            java.util.logging.Logger.getLogger(VentTipoTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentTipoTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentTipoTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentTipoTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VentTipoTablero().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPorDefecto;
    private javax.swing.JButton btnRandom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JPanel panelTipoTablero;
    private javax.swing.JTable tablaJugadores;
    // End of variables declaration//GEN-END:variables
}
