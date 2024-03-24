package management.views.categories.employee;

import javax.swing.table.DefaultTableModel;

import management.controllers.categories.EmployeeController;

public class EmployeeJFrame extends javax.swing.JFrame {
    public final EmployeeController employeeController = new EmployeeController();
    public EmployeeJFrame() {
        initComponents();
        
        employeeController.showAllEmployee(employeeTable);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        allPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        functionPanel = new javax.swing.JPanel();
        addEmployeeButton = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();
        viewDetailButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(employeeTable);

        allPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 610, 300));

        functionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addEmployeeButton.setText("Thêm");
        addEmployeeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEmployeeButtonMouseClicked(evt);
            }
        });
        addEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeButtonActionPerformed(evt);
            }
        });
        functionPanel.add(addEmployeeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        filterButton.setText("Bộ lọc");
        functionPanel.add(filterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        viewDetailButton.setText("Xem chi tiết");
        viewDetailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewDetailButtonMouseClicked(evt);
            }
        });
        functionPanel.add(viewDetailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        delButton.setText("Xóa");
        delButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delButtonMouseClicked(evt);
            }
        });
        functionPanel.add(delButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        refreshButton.setText("Làm mới");
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });
        functionPanel.add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        allPanel.add(functionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 110));

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

    
    private void addEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addEmployeeButtonActionPerformed

    private void addEmployeeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeButtonMouseClicked
        UpdateEmployeeJFrame frame = new UpdateEmployeeJFrame(true);
        frame.hideUpdateButton();        
        frame.setEditable(true, true);
        frame.setVisible(true);
    }//GEN-LAST:event_addEmployeeButtonMouseClicked

    private void delButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delButtonMouseClicked
        int row = employeeTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)employeeTable.getModel();
        
        String employeeId = model.getValueAt(row, 0).toString();
        employeeController.hideEmployeeData(employeeId);
        employeeController.showAllEmployee(employeeTable);
    }//GEN-LAST:event_delButtonMouseClicked

    private void viewDetailButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewDetailButtonMouseClicked
        UpdateEmployeeJFrame frame = new UpdateEmployeeJFrame(false);
        frame.hidePasswordTF();
        frame.setEditable(false, false);
        
        int row = employeeTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)employeeTable.getModel();
        String employeeId = model.getValueAt(row, 1).toString();
                
        employeeController.showSingleEmployee(frame, employeeId);
        frame.setVisible(true);
    }//GEN-LAST:event_viewDetailButtonMouseClicked

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        employeeController.showAllEmployee(employeeTable);
    }//GEN-LAST:event_refreshButtonMouseClicked

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
            java.util.logging.Logger.getLogger(EmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEmployeeButton;
    private javax.swing.JPanel allPanel;
    private javax.swing.JButton delButton;
    private javax.swing.JTable employeeTable;
    private javax.swing.JButton filterButton;
    private javax.swing.JPanel functionPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton viewDetailButton;
    // End of variables declaration//GEN-END:variables
}
