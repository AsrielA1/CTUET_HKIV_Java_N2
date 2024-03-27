package management.exceptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.util.HashMap;
import management.configs.PropertiesController;

public class InvalidLoginException extends Exception{
    
    
    public InvalidLoginException(){}
    
    public InvalidLoginException(String message){
        super(message);
    }
    
    public static void checkValidLogin(String username, String password) throws InvalidLoginException{
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String query = null;
        
        try {
            HashMap<String, String> properties = PropertiesController.getProperties();
            String url = properties.get("url");
            String dbUsername = properties.get("username");
            String dbPassword = properties.get("password");
            
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            query = "SELECT ma_nhanvien, matkhau FROM nhan_vien WHERE ma_nhanvien = ? AND matkhau = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            if (!rs.next()){
                throw new InvalidLoginException("\nInvalid login\n");
            }
        
        }
        catch (Exception e) {
        
        }
    }
    
    public static void checkValidLogin(JFrame frame, String username, String password) throws InvalidLoginException{
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String query = null;
        
        try {
            HashMap<String, String> properties = PropertiesController.getProperties();
            String url = properties.get("url");
            String dbUsername = properties.get("username");
            String dbPassword = properties.get("password");
            
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            query = "SELECT ma_nhanvien, matkhau FROM nhan_vien WHERE ma_nhanvien = ? AND matkhau = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            if (!rs.next()){
                throw new InvalidLoginException("\nInvalid login\n");
            }
        
        }
        catch(SQLException e){
            System.out.print("Error with SQL\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            JOptionPane.showMessageDialog(frame, "");
        }
    }
}
