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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import management.configs.PropertiesController;
import management.models.details.InputDetail;

interface IInputDetailController{
    void showAllInputDetail(JTable inputDetailTable, String _inputHistoryId);
    void addInputHistoryIdChooser(JComboBox inputHistoryComboBox);
    void addStorageIdChooser(JComboBox storageComboBox);
    void addInputDetail(JComboBox inputHistoryComboBox, JComboBox storageComboBox, JTextField costPerWeightTF, JTextField weightTF, JTextField inputDetailNoteTF);
    void hideInputDetail(JTable inputDetailTable, JComboBox inputHistoryIdChooser);
}

public class InputDetailController implements IInputDetailController{
    private final InputDetail inputDetail = new InputDetail();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public InputDetailController(){}
    
    @Override
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
   
    @Override
    public void addInputHistoryIdChooser(JComboBox inputHistoryComboBox){
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
    
    @Override
    public void addStorageIdChooser(JComboBox storageComboBox){
        storageComboBox.removeAllItems();
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ma_kho FROM danhmuc_kho WHERE ghi_chu NOT LIKE '%Hủy%'";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                storageComboBox.addItem(rs.getString(1));
            }
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.InputDetailController.addStorageIdChooser\n" + e);
        }
    }
    
    @Override
    public void addInputDetail(JComboBox inputHistoryComboBox, JComboBox storageComboBox, JTextField costPerWeightTF, JTextField weightTF, JTextField inputDetailNoteTF){
        try {        
            String inputId, storageId, inputDetailNote;
            float costPerWeight, weight;

            inputId = inputHistoryComboBox.getSelectedItem().toString();
            storageId = storageComboBox.getSelectedItem().toString();
            costPerWeight = Float.parseFloat(costPerWeightTF.getText());
            weight = Float.parseFloat(weightTF.getText());
            inputDetailNote = inputDetailNoteTF.getText();

            inputDetail.addInputDetail(inputId, storageId, costPerWeight, weight, inputDetailNote);
            
            System.out.println(inputId + "\n" + storageId +"\n" + String.valueOf(costPerWeight) + "\n" + String.valueOf(weight) + "\n" + inputDetailNote);
        }
        catch (Exception e) {
            System.out.println("Error in management.controllers.categories.histories.InputDetailController.addInputDetail\n" + e);
        }
    }
    
    @Override
    public void hideInputDetail(JTable inputDetailTable, JComboBox inputHistoryIdChooser){
        try {
            int row = inputDetailTable.getSelectedRow();
            DefaultTableModel tModel = (DefaultTableModel)inputDetailTable.getModel();

            String inputHistoryId = inputHistoryIdChooser.getSelectedItem().toString();
            int inputDetailNumber = Integer.parseInt(tModel.getValueAt(row, 0).toString());

            inputDetail.delInputDetail(inputHistoryId, inputDetailNumber);
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.InputDetailController.hideInputDetail\n" + e);
        }
    }
}
