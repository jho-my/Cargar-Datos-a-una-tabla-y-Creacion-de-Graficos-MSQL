package main;

import Conex.Conexion;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class frmCargarDatos extends javax.swing.JFrame {

    Conexion conectar = Conexion.getInstancia();

    JFreeChart grafico;
    DefaultCategoryDataset datos = new DefaultCategoryDataset();

    public frmCargarDatos() {
        initComponents();
        this.setTitle("Cargar Tablas");
        this.setLocationRelativeTo(null);

    }

    private void cargarDatos() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            tblDatos.setModel(modelo);

            Connection conexion = conectar.ConectarBD();
            String select = "Select * from producto";
            PreparedStatement consulta = conexion.prepareStatement(select);
            ResultSet seleccion = consulta.executeQuery();
            ResultSetMetaData datos = seleccion.getMetaData();

            modelo.addColumn("Visitas");
            modelo.addColumn("Negocio");
            modelo.addColumn("Dias");

            int columnas = datos.getColumnCount();

            int anchos[] = {90, 90, 90};

            for (int i = 0; i < columnas; i++) {
                tblDatos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

            }

            //cargamos los datos
            while (seleccion.next()) {
                Object arreglo[] = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    arreglo[i] = seleccion.getObject(i + 1);
                }
                modelo.addRow(arreglo);
            }
            //tblDatos.setModel(modelo);

            conectar.DesconectarBD();
        } catch (SQLException e) {
        }
    }

    private void graficarDatos() {
        try {
            //recorremos cada fila
            for (int i = 0; i < tblDatos.getRowCount(); i++) {
                datos.addValue(Integer.parseInt(tblDatos.getValueAt(i, 0).toString()), tblDatos.getValueAt(i, 1).toString(), tblDatos.getValueAt(i, 2).toString());
            }

            //mostramos los graficos
            grafico = ChartFactory.createBarChart("Grafico de Visitas",
                    "Dias", "Visitas", datos, PlotOrientation.VERTICAL,
                    true, true,
                    false);
            ChartPanel panel = new ChartPanel(grafico);
            this.add(panel);
            panel.setBounds(300, 40, 700, 350);

        } catch (NumberFormatException e) {
            System.out.println("""
                               Error al graficar :
                                """ + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        btncargarDatos = new javax.swing.JButton();
        btncargarDatos1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Visitas", "Negocio", "Dia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        btncargarDatos.setText("Cargar Datos");
        btncargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargarDatosActionPerformed(evt);
            }
        });

        btncargarDatos1.setText("Graficar Datos");
        btncargarDatos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargarDatos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btncargarDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(704, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncargarDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncargarDatos1)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargarDatosActionPerformed
        // TODO add your handling code here:
        cargarDatos();
    }//GEN-LAST:event_btncargarDatosActionPerformed

    private void btncargarDatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargarDatos1ActionPerformed
        // TODO add your handling code here:
        graficarDatos();
    }//GEN-LAST:event_btncargarDatos1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCargarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCargarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCargarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCargarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCargarDatos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncargarDatos;
    private javax.swing.JButton btncargarDatos1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDatos;
    // End of variables declaration//GEN-END:variables
}
