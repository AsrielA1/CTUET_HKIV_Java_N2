package management.JFrames;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginJFrame extends javax.swing.JFrame {

 
    public LoginJFrame() {
        initComponents();
        loginFail.setVisible(true);
        loginButton.setVisible(true);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        loginArea = new javax.swing.JPanel();
        loginFail = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        someIcon = new javax.swing.JPanel();
        loginPassword = new javax.swing.JTextField();
        loginUsername = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 250));

        jPanel2.setLayout(new java.awt.BorderLayout());

        loginFail.setText("Sai tên đăng nhập hoặc mật khẩu");

        javax.swing.GroupLayout loginAreaLayout = new javax.swing.GroupLayout(loginArea);
        loginArea.setLayout(loginAreaLayout);
        loginAreaLayout.setHorizontalGroup(
            loginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginAreaLayout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(loginFail, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        loginAreaLayout.setVerticalGroup(
            loginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginAreaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(loginFail)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel2.add(loginArea, java.awt.BorderLayout.PAGE_END);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.add(someIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel3.add(loginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 160, -1));
        jPanel3.add(loginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 160, -1));

        loginButton.setText("Đăng nhập");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        jPanel3.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));

        jLabel1.setText("Mật khẩu");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 90, 20));

        jLabel2.setText("Tên đăng nhập");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 90, 20));

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        Connection connection;
        try {
            String username = loginUsername.getText();
            String password = loginPassword.getText();
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qlkhoca", username, password);
            
            if (connection != null)
                new MainJFrame().setVisible(true);
            
        } catch (SQLException se){
            System.out.println(se);
            loginFail.setVisible(true);
        } catch (Exception e){
            System.out.println(e);
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel loginArea;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginFail;
    private javax.swing.JTextField loginPassword;
    private javax.swing.JTextField loginUsername;
    private javax.swing.JPanel someIcon;
    // End of variables declaration//GEN-END:variables
}
