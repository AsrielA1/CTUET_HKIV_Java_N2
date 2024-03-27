package management.controllers.histories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import management.configs.PropertiesController;
import management.models.histories.InputHistory;

interface IInputDetailController{
    void showAllInputDetail(JTable inputDetailTable);
    void addInputHistoryChooser(JComboBox inputHistoryComboBox);
}

public class InputDetailController {
    private final InputHistory inputHistory = new InputHistory();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public InputDetailController(){}
    
    public void showAllInputDetail(JTable inputDetailTable, String _inputHistoryId){
        DefaultTableModel tModel = (DefaultTableModel)inputDetailTable.getModel();
        tModel.setRowCount(0);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String inputDetailId, storageId, weight, cost, costPerWeight;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT so_thutu, ma_kho, khoi_luong, chi_phi, chiphi_theodv FROM chitiet_nhapkho WHERE ghi_chu NOT LIKE '%Hủy%' AND ma_lohang = '" + _inputHistoryId + "';";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            
            while (rs.next()){                
                inputDetailId = rs.getString(1);
                storageId = rs.getString(2);
                weight = String.valueOf(rs.getFloat(3));
                cost = String.valueOf(rs.getFloat(4));
                costPerWeight = String.valueOf(rs.getFloat(5));
                
                String inputHistoryList[] = {inputDetailId, storageId, weight, cost, costPerWeight};
                
                tModel.addRow(inputHistoryList);
            }
                        
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.InputDetailController.showAllInputDetail\n" + e);
        }
    }
   
    
    public void addInputHistoryChooser(JComboBox inputHistoryComboBox){
        inputHistoryComboBox.removeAllItems();
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ma_lohang FROM lichsu_nhapkho WHERE ghi_chu NOT LIKE '%Hủy%'";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                inputHistoryComboBox.addItem(rs.getString(1));
            }
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.InputDetailController.addInputHistoryChooser\n" + e);
        }
    }
}
