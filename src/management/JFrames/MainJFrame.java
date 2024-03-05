/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package management.JFrames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import java.util.concurrent.TimeUnit;

import java.awt.Dimension;

public class MainJFrame extends javax.swing.JFrame {
    private int xMouse;
    private int yMouse;
    boolean showDashboard = false;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        dashboard.setVisible(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        DefaultTableModel tModel = (DefaultTableModel) storageDataTable.getModel();
        tModel.setRowCount(0);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        minmaxclose = new javax.swing.JPanel();
        minimize = new javax.swing.JPanel();
        minButton = new javax.swing.JLabel();
        maximize = new javax.swing.JPanel();
        maxButton = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        closeButton = new javax.swing.JLabel();
        background = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        workplace = new javax.swing.JLayeredPane();
        layeredWorkplace = new javax.swing.JLayeredPane();
        storagePanel = new javax.swing.JPanel();
        functionPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        storageScrollPane = new javax.swing.JScrollPane();
        storageDataTable = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        header.setBackground(new java.awt.Color(0, 153, 0));
        header.setPreferredSize(new java.awt.Dimension(800, 50));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });
        header.setLayout(new java.awt.BorderLayout());

        minmaxclose.setPreferredSize(new java.awt.Dimension(150, 50));
        minmaxclose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimize.setBackground(new java.awt.Color(0, 102, 51));
        minimize.setPreferredSize(new java.awt.Dimension(50, 50));
        minimize.setLayout(new java.awt.BorderLayout());

        minButton.setForeground(new java.awt.Color(255, 255, 255));
        minButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minButton.setText("______");
        minButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minButtonMouseClicked(evt);
            }
        });
        minimize.add(minButton, java.awt.BorderLayout.CENTER);

        minmaxclose.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        maximize.setBackground(new java.awt.Color(42, 105, 33));
        maximize.setPreferredSize(new java.awt.Dimension(50, 50));
        maximize.setLayout(new java.awt.BorderLayout());

        maxButton.setBackground(new java.awt.Color(42, 105, 33));
        maxButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/full_screen_32px.png"))); // NOI18N
        maxButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        maxButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxButtonMouseClicked(evt);
            }
        });
        maximize.add(maxButton, java.awt.BorderLayout.CENTER);

        minmaxclose.add(maximize, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 50, -1));

        close.setBackground(new java.awt.Color(42, 105, 33));
        close.setPreferredSize(new java.awt.Dimension(50, 50));
        close.setLayout(new java.awt.BorderLayout());

        closeButton.setBackground(new java.awt.Color(42, 105, 33));
        closeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete_32px.png"))); // NOI18N
        closeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });
        close.add(closeButton, java.awt.BorderLayout.CENTER);

        minmaxclose.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 50, -1));

        header.add(minmaxclose, java.awt.BorderLayout.LINE_END);

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        background.setBackground(new java.awt.Color(204, 255, 204));
        background.setPreferredSize(new java.awt.Dimension(1280, 720));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebar.setBackground(new java.awt.Color(0, 153, 0));
        sidebar.setPreferredSize(new java.awt.Dimension(50, 50));
        sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        background.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 780));

        dashboard.setBackground(new java.awt.Color(0, 102, 51));
        dashboard.setPreferredSize(new java.awt.Dimension(400, 710));
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(350, 50));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Danh mục");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        dashboard.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 40));

        background.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 220, 780));

        layeredWorkplace.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        storagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout functionPanelLayout = new javax.swing.GroupLayout(functionPanel);
        functionPanel.setLayout(functionPanelLayout);
        functionPanelLayout.setHorizontalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addGap(486, 486, 486)
                .addComponent(jButton1)
                .addContainerGap(749, Short.MAX_VALUE))
        );
        functionPanelLayout.setVerticalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, functionPanelLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        storagePanel.add(functionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, -1));

        storageDataTable.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        storageDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã kho", "Nhiệt độ", "Độ ẩm", "Khối lượng hiện tại", "Sức chứa"
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
        storageDataTable.setRowHeight(50);
        storageDataTable.setShowGrid(true);
        storageScrollPane.setViewportView(storageDataTable);
        if (storageDataTable.getColumnModel().getColumnCount() > 0) {
            storageDataTable.getColumnModel().getColumn(0).setResizable(false);
            storageDataTable.getColumnModel().getColumn(1).setResizable(false);
            storageDataTable.getColumnModel().getColumn(2).setResizable(false);
            storageDataTable.getColumnModel().getColumn(3).setResizable(false);
            storageDataTable.getColumnModel().getColumn(4).setResizable(false);
        }

        storagePanel.add(storageScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 107, 1310, 670));

        layeredWorkplace.add(storagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 780));

        workplace.setLayer(layeredWorkplace, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout workplaceLayout = new javax.swing.GroupLayout(workplace);
        workplace.setLayout(workplaceLayout);
        workplaceLayout.setHorizontalGroup(
            workplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workplaceLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(layeredWorkplace, javax.swing.GroupLayout.PREFERRED_SIZE, 1896, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        workplaceLayout.setVerticalGroup(
            workplaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredWorkplace)
        );

        background.add(workplace, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1310, 770));

        getContentPane().add(background, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        //Nút tắt 
        dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    
    private void maxButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxButtonMouseClicked
        if (this.getExtendedState() != MainJFrame.MAXIMIZED_BOTH){
            this.setExtendedState(MainJFrame.MAXIMIZED_BOTH);
        } else {
            this.setExtendedState(MainJFrame.NORMAL);
        }
    }//GEN-LAST:event_maxButtonMouseClicked

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void changeImage(JLabel button, String srcImage){
        ImageIcon img = new ImageIcon(getClass().getResource(srcImage));
        button.setIcon(img);
    }
    
    private void refreshStorageData(String username, String password){
        Connection connection = null;
        
        DefaultTableModel tModel = (DefaultTableModel) storageDataTable.getModel();
        tModel.setRowCount(0);
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qlkhoca", username, password);
            
            Statement st = connection.createStatement();
            String query = "SELECT * FROM danhmuc_kho;";
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()){
                String id = String.valueOf(rs.getString(1));
                String tempC = String.valueOf(String.format("%.2g%n", rs.getFloat(2)));
                String humidity  = String.valueOf(String.format("%.2g%n", rs.getFloat(3)));
                String current_kg = String.valueOf(String.format("%.2g%n", rs.getFloat(4)));
                String max_kg = String.valueOf(String.format("%.2g%n", rs.getFloat(5)));
                
                String storageData[] = {id, tempC, humidity, current_kg, max_kg};
                
                tModel.addRow(storageData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    private void minButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minButtonMouseClicked
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minButtonMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        refreshStorageData("postgres", "postgres");
    }//GEN-LAST:event_jButton1MouseClicked

    
    
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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainJFrame mainFrame = new MainJFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel close;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel dashboard;
    private javax.swing.JPanel functionPanel;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLayeredPane layeredWorkplace;
    private javax.swing.JLabel maxButton;
    private javax.swing.JPanel maximize;
    private javax.swing.JLabel minButton;
    private javax.swing.JPanel minimize;
    private javax.swing.JPanel minmaxclose;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable storageDataTable;
    private javax.swing.JPanel storagePanel;
    private javax.swing.JScrollPane storageScrollPane;
    private javax.swing.JLayeredPane workplace;
    // End of variables declaration//GEN-END:variables
}
