package management.views.categories.employee;

import management.controllers.categories.EmployeeController;

public class UpdateEmployeeJFrame extends javax.swing.JFrame {
    
    public final EmployeeController employeeController = new EmployeeController();
    
    public UpdateEmployeeJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        informationPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        employeeIdTF = new javax.swing.JTextField();
        employeeNameTF = new javax.swing.JTextField();
        employeeNumberTF = new javax.swing.JTextField();
        employeeNoteTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JPasswordField();
        functionPanel = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        informationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã nhân viên");
        informationPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 160, 30));

        jLabel2.setText("Mật khẩu");
        informationPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 170, 50));

        jLabel3.setText("Họ tên");
        informationPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 170, 70));

        jLabel4.setText("Số điện thoại");
        informationPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 80));

        jLabel5.setText("Ghi chú");
        informationPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 160, 50));
        informationPanel.add(employeeIdTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 230, -1));
        informationPanel.add(employeeNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 220, -1));
        informationPanel.add(employeeNumberTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 230, -1));
        informationPanel.add(employeeNoteTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 270, 90));
        informationPanel.add(passwordTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 230, -1));

        getContentPane().add(informationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 500));

        updateButton.setText("Cập nhật");

        confirmButton.setText("Xác nhận");
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Hủy");

        javax.swing.GroupLayout functionPanelLayout = new javax.swing.GroupLayout(functionPanel);
        functionPanel.setLayout(functionPanelLayout);
        functionPanelLayout.setHorizontalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        functionPanelLayout.setVerticalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(functionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 270, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked
        employeeController.addEmployeeData(employeeIdTF, employeeIdTF, employeeNameTF, employeeNumberTF, employeeNoteTF);
    }//GEN-LAST:event_confirmButtonMouseClicked

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
            java.util.logging.Logger.getLogger(UpdateEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateEmployeeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField employeeIdTF;
    private javax.swing.JTextField employeeNameTF;
    private javax.swing.JTextField employeeNoteTF;
    private javax.swing.JTextField employeeNumberTF;
    private javax.swing.JPanel functionPanel;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField passwordTF;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
