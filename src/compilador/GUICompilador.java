/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import automatas.Lexico;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Frank
 */
public class GUICompilador extends javax.swing.JFrame {
    Utils utils =new Utils();
    //Lista_desordenada lista = new Lista_desordenada();
    DefaultTableModel tabla = new DefaultTableModel();
   // Lexico lexico;
    /**
     * Creates new form GUICompilador
     */
    public GUICompilador() {
        initComponents();
        
        tabla.addColumn("Llave");
        tabla.addColumn("Categoria");
        tabla.addColumn("Tipo");
        tabla.addColumn("Longitud");
        tabla.addColumn("Valor");
        jTable.setModel(tabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTAPrompt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextErrores = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextOut = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCompilar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JTAPrompt.setColumns(20);
        JTAPrompt.setRows(5);
        jScrollPane1.setViewportView(JTAPrompt);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Language Valyrio");

        jTable.setModel(tabla);
        jScrollPane2.setViewportView(jTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tabla de Simbolos", jPanel2);

        jTextErrores.setEditable(false);
        jTextErrores.setColumns(20);
        jTextErrores.setRows(5);
        jScrollPane3.setViewportView(jTextErrores);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Errores", jPanel3);

        jTextOut.setEditable(false);
        jTextOut.setColumns(20);
        jTextOut.setRows(5);
        jScrollPane4.setViewportView(jTextOut);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Output", jPanel1);

        jMenu1.setText("Run");

        jMenuItemCompilar.setText("A lexico");
        jMenuItemCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCompilarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCompilar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem2.setText("AcercaDe");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Output");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCompilarActionPerformed
        Lexico lexico = new Lexico();
        String texto = JTAPrompt.getText(),delimitadores= "[\n]",resulOut="",resultErr="",clasificar;
        int conE=0;
        ArrayList tokens;
        String[] cadenas =texto.split(delimitadores);
        for (int i = 0; i < cadenas.length; i++) 
        {
            tokens = new ArrayList();
            utils.separaTokens(cadenas[i], tokens);
            for (int j = 0; j < tokens.size(); j++) 
            {
            clasificar = lexico.clasificar((String) tokens.get(j));
           
            if(!clasificar.equals("COMEN"))
                   resulOut+=clasificar+" ";
        
                if (clasificar.equals("ERROR")) 
                {
                    conE++;
                }
            }
            if (conE>0) 
            {
                String Err;
                if(conE==1)
                {
                    Err=conE+" error en linea " +(i+1);
                }else
                {
                    Err=conE+" errores en linea " +(i+1);
                }
                
                resultErr+=Err+"\n";
                conE=0;
            }
            
            resulOut+="\n";
        }
        System.out.println(resultErr);
        lexico.tablaSimbolos.limpiarTabla(tabla); /* limpia contenido previo de defaulftable model */
        lexico.tablaSimbolos.llenarTabla(tabla);/* asigan los valores de la lista al modelo que se asigna al jtable*/
        jTextErrores.setText(resultErr);
        jTextOut.setText(resulOut);
        
        
        
    }//GEN-LAST:event_jMenuItemCompilarActionPerformed

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
            java.util.logging.Logger.getLogger(GUICompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICompilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICompilador().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTAPrompt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemCompilar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea jTextErrores;
    private javax.swing.JTextArea jTextOut;
    // End of variables declaration//GEN-END:variables
}
