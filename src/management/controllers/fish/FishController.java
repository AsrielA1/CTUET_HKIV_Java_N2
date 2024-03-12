package management.controllers.fish;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


interface IFishController{
    void refreshFishData(String username, String password, JTable fishDataTable);
    void addFishData(String username, String password, JTextField fishId, JTextField fishName, JTextField tempCUp, JTextField tempCLow, JTextField humidityUp, JTextField humidityLow, JTextField fishNote);
    void delFishData(String username, String password, String fishId);
    void searchFishData(JTable fishDataTable, String username, String password, JTextField idSearch, JTextField nameSearch, JTextField tempCHighSearch, JTextField tempCLowSearch, JTextField humidityHighSearch, JTextField humidityLowSearch);
}   

public class FishController implements IFishController{
    
    public void refreshFishData(String username, String password, JTable fishDataTable){
        Connection connection = null;
        
        DefaultTableModel tModel = (DefaultTableModel) fishDataTable.getModel();
        tModel.setRowCount(0);
        
        try {            
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
            
            Statement st = connection.createStatement();
            String query = "SELECT * FROM danhmuc_ca;";
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()){
                String id = String.valueOf(rs.getString(1));
                String name = String.valueOf(rs.getString(2));
                String tempC_high = String.valueOf(String.format("%.2g%n", rs.getFloat(3)));
                String tempC_low  = String.valueOf(String.format("%.2g%n", rs.getFloat(4)));
                String humidity_high = String.valueOf(String.format("%.2g%n", rs.getFloat(5)));
                String humidity_low  = String.valueOf(String.format("%.2g%n", rs.getFloat(6)));
                
                String storageData[] = {id, name, tempC_high, tempC_low, humidity_high, humidity_low};
                
                tModel.addRow(storageData);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void addFishData(String username, String password, JTextField fishId, JTextField fishName, JTextField tempCUp, JTextField tempCLow, JTextField humidityUp, JTextField humidityLow, JTextField fishNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        String id = String.valueOf(fishId.getText());
        String name = String.valueOf(fishName.getText());
        
        float _tempC_up = Float.parseFloat(tempCUp.getText());
        float _tempC_low = Float.parseFloat(tempCLow.getText());
        float _humidity_up = Float.parseFloat(humidityUp.getText());
        float _humidity_low = Float.parseFloat(humidityLow.getText());
        
        String note = String.valueOf(fishNote.getText());
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
            
            String query = "INSERT INTO danhmuc_ca VALUES(?, ?, ?, ?, ?, ?, ?); "; 
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setFloat(3, _tempC_up);
            pstmt.setFloat(4, _tempC_low);
            pstmt.setFloat(5, _humidity_up);
            pstmt.setFloat(6, _humidity_low);
            pstmt.setString(7, note);
            
            int status = pstmt.executeUpdate();
            if (status > 0)
                System.out.println("INSERTED");
            else 
                System.out.println("SOMETHING WRONG");
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void delFishData(String username, String password, String fishId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
            
            String query = "DELETE FROM danhmuc_ca WHERE ma_loaica = '" + fishId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
        }
    }
    
    private static boolean isTextFieldEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }
    
    public void searchFishData(JTable fishDataTable, String username, String password, JTextField idSearch, JTextField nameSearch, JTextField tempCHighSearch, JTextField tempCLowSearch, JTextField humidityHighSearch, JTextField humidityLowSearch){
        Connection connection = null;
        String query = "";
        
        boolean id_null = isTextFieldEmpty(idSearch);
        boolean name_null = isTextFieldEmpty(nameSearch);
        boolean tempC_high_null = isTextFieldEmpty(tempCHighSearch);
        boolean tempC_low_null = isTextFieldEmpty(tempCLowSearch);
        boolean humidity_high_null = isTextFieldEmpty(humidityHighSearch);
        boolean humidity_low_null = isTextFieldEmpty(humidityLowSearch);
        
        
        List<String> conditions = new ArrayList<>();
        if (!id_null){
            conditions.add("ma_loaica = '" + String.valueOf(idSearch.getText()) + "'");
        }
        if (!name_null){
            conditions.add("ten_loaica = '" + String.valueOf(nameSearch.getText()) + "'");
        }
        
        if (tempC_high_null && !tempC_low_null || !tempC_high_null && tempC_low_null){
            JOptionPane.showMessageDialog(null, "Đôi với nhiệt độ cần cả 2 ngưỡng trên và dưới", "Nhập thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
        }
        if (!tempC_high_null && !tempC_low_null){
            conditions.add("nhietdo_tren <= " + String.valueOf(tempCHighSearch.getText()) + " AND " + "nhietdo_duoi >= " + String.valueOf(tempCLowSearch.getText()));
        }
        
        if (humidity_high_null && !humidity_low_null || !humidity_high_null && humidity_low_null){
            JOptionPane.showMessageDialog(null, "Đôi với độ ẩm cần cả 2 ngưỡng trên và dưới", "Nhập thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
        }
        if (!humidity_high_null && !humidity_low_null){
            conditions.add("doam_tren <= " + String.valueOf(humidityHighSearch.getText()) + " AND " + "doam_duoi >= " + String.valueOf(humidityLowSearch.getText()));
        }
        
        DefaultTableModel tModel = (DefaultTableModel) fishDataTable.getModel();
        tModel.setRowCount(0);
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
            
            
            Statement st = connection.createStatement();
            query = "SELECT * FROM danhmuc_ca";
            if (!conditions.isEmpty()) {
                query += " WHERE ";
                query += String.join(" AND ", conditions);
            } 
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()){
                String id = String.valueOf(rs.getString(1));
                String name = String.valueOf(rs.getString(2));
                String tempC_high = String.valueOf(String.format("%.2g%n", rs.getFloat(3)));
                String tempC_low  = String.valueOf(String.format("%.2g%n", rs.getFloat(4)));
                String humidity_high = String.valueOf(String.format("%.2g%n", rs.getFloat(5)));
                String humidity_low  = String.valueOf(String.format("%.2g%n", rs.getFloat(6)));
                
                String storageData[] = {id, name, tempC_high, tempC_low, humidity_high, humidity_low};
                
                tModel.addRow(storageData);
            }
            
        }
        
        catch (Exception e){
            System.out.println(e);
        }
    }
    
}
