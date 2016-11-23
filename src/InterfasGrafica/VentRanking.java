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
public class VentRanking extends javax.swing.JFrame implements Observer{
    
    private Juego elJuego;
    

    /**
     * Creates new form VentRanking
     */
    public VentRanking(Juego unJuego) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.elJuego = unJuego;
        this.elJuego.addObserver(this);
        agregoObserversDeJugadores ();
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
        jLabel1 = new javax.swing.JLabel();

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ranking.png"))); // NOI18N

        javax.swing.GroupLayout panelRankingLayout = new javax.swing.GroupLayout(panelRanking);
        panelRanking.setLayout(panelRankingLayout);
        panelRankingLayout.setHorizontalGroup(
            panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRankingLayout.createSequentialGroup()
                .addGroup(panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRankingLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRankingLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRankingLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelRankingLayout.setVerticalGroup(
            panelRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRankingLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        getContentPane().add(panelRanking);
        panelRanking.setBounds(10, 19, 560, 380);

        setBounds(0, 0, 592, 462);
    }// </editor-fold>//GEN-END:initComponents

    private void ranking(){
       
        
//        for (int i =0;i<this.tablaRanking.getRowCount();i++){
//            this.tablaRanking.getModel()
//        }
        
        ArrayList<Jugador> aux = this.elJuego.getRanking();
        Iterator<Jugador> iter = aux.iterator();
        DefaultTableModel modelo=(DefaultTableModel) this.tablaRanking.getModel();
        modelo.setRowCount(0);
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
    
    private void agregoObserversDeJugadores (){
        for (int i =0; i < this.elJuego.getJugadores().size();i++){
            this.elJuego.getJugadores().get(i).addObserver(this);
        }
    }
    @Override
    public void update(Observable o, Object arg){
        agregoObserversDeJugadores ();
        ranking();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelRanking;
    private javax.swing.JTable tablaRanking;
    // End of variables declaration//GEN-END:variables
}
