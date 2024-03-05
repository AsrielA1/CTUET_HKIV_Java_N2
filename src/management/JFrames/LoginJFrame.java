package management.JFrames;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginJFrame extends javax.swing.JFrame {

 
    public LoginJFrame() {
        initComponents();
        loginFail.setVisible(false);
        loginButton.setVisible(true);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        loginArea = new javax.swing.JPanel();
        someIcon = new javax.swing.JPanel();
        loginPassword = new javax.swing.JTextField();
        loginUsername = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        loginFail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 250));
        setResizable(false);

        loginArea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout someIconLayout = new javax.swing.GroupLayout(someIcon);
        someIcon.setLayout(someIconLayout);
        someIconLayout.setHorizontalGroup(
            someIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        someIconLayout.setVerticalGroup(
            someIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        loginArea.add(someIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        loginArea.add(loginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 160, -1));
        loginArea.add(loginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));

        loginButton.setText("Đăng nhập");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        loginArea.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jLabel1.setText("Mật khẩu");
        loginArea.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 90, 20));

        jLabel2.setText("Tên đăng nhập");
        loginArea.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 90, 20));

        jPanel1.setLayout(new java.awt.BorderLayout());

        loginFail.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
        jPanel1.add(loginFail, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(loginArea, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(loginArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void disposeResources() {
        this.dispose();
    }
    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        Connection connection = null;
        try {
            String username = loginUsername.getText();
            String password = loginPassword.getText();
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qlkhoca", username, password);

            if (connection != null){
                new MainJFrame().setVisible(true);
                disposeResources();
            }
        } catch (Exception e){
            System.out.println(e);
            loginFail.setVisible(true);
        } 
        
    }//GEN-LAST:event_loginButtonMouseClicked

    
    
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
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginJFrame frame = new LoginJFrame();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel loginArea;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginFail;
    private javax.swing.JTextField loginPassword;
    private javax.swing.JTextField loginUsername;
    private javax.swing.JPanel someIcon;
    // End of variables declaration//GEN-END:variables
}
