package management.views.categories.provider;

import management.controllers.categories.EmployeeController;
import management.controllers.categories.ProviderController;

public class ProviderJFrame extends javax.swing.JFrame {
    
    private final ProviderController providerController = new ProviderController();
 
    public ProviderJFrame() {
        initComponents();
        
        providerController.showAllProviderData(providerTable);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        allPanel = new javax.swing.JPanel();
        functionPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        providerTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 600));

        allPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        functionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addButton.setText("Thêm");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });
        functionPanel.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        updateButton.setText("Xem chi tiết");
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });
        functionPanel.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        filterButton.setText("Bộ lọc");
        functionPanel.add(filterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, -1));

        deleteButton.setText("Xóa");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });
        functionPanel.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));

        refreshButton.setText("Làm mới");
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });
        functionPanel.add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        allPanel.add(functionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 600, 150));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        allPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));

        providerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên đơn vị", "Email", "Số điện thoại"
            }
        ));
        jScrollPane1.setViewportView(providerTable);

        allPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 600, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        providerController.showAllProviderData(providerTable);
    }//GEN-LAST:event_refreshButtonMouseClicked

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        providerController.hideProviderData(providerTable);
        providerController.showAllProviderData(providerTable);
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        UpdateProviderJFrame frame = new UpdateProviderJFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_addButtonMouseClicked

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        UpdateProviderJFrame frame = new UpdateProviderJFrame(false);
        providerController.showSingleProviderData(frame, providerTable);
        frame.setEditable(false);
        frame.setVisible(true);
    }//GEN-LAST:event_updateButtonMouseClicked

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
            java.util.logging.Logger.getLogger(ProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProviderJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProviderJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel allPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton filterButton;
    private javax.swing.JPanel functionPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable providerTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
