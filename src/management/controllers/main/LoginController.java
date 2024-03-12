package management.controllers.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;

import management.views.LoginJFrame;

public class LoginController {
    public LoginController(){
        
    }
    
    public static boolean isTextFieldEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }
    
    public boolean loginIsSuccess(JTextField usernameField, JTextField passwordField){
        String username = String.valueOf(usernameField.getText());
        String password = String.valueOf(passwordField.getText());
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            
            String query = "SELECT * FROM nhan_vien WHERE ma_nhanvien = ? AND mat_khau = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            if (rs.wasNull()){
                JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu", "Đăng nhập thất bại", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.main.LoginController.login()\n" + e);
        }
        
        return false;
    }
}
