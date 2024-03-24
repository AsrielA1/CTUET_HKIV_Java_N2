package management.controllers.categories;

import management.models.categories.Provider;
import management.views.categories.provider.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import management.views.categories.employee.UpdateEmployeeJFrame;
import management.models.categories.Employee;
import management.configs.PropertiesController;


interface IProviderController{
    void showSingleProviderData(UpdateProviderJFrame frame, JTable providerTable);
    void addProviderData(JTextField providerIdTF, JTextField providerNameTF, JTextField providerMailTF, JTextField providerNumberTF, JTextField providerNoteTF);
    void showAllProviderData(JTable providerTable);   
    void hideProviderData(JTable providerTable);
}

public class ProviderController implements IProviderController {
    private final Provider providerModel = new Provider();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public ProviderController(){}
    
    @Override
    public void showSingleProviderData(UpdateProviderJFrame frame, JTable providerTable){
        int row = providerTable.getSelectedRow();
        DefaultTableModel tModel = (DefaultTableModel)providerTable.getModel();
        String providerId = tModel.getValueAt(row, 0).toString();
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        
        String providerName, providerMail, providerNumber, providerNote;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "SELECT ma_nhacungcap, ten_nhacungcap, diachi_email, so_dienthoai, ghi_chu FROM nha_cungcap WHERE ma_nhacungcap = ?;";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, providerId);
            rs = pstmt.executeQuery();
            while (rs.next()){
                providerName = rs.getString(1);
                providerMail = rs.getString(2);
                providerNumber = rs.getString(3);
                providerNote = rs.getString(4);
                
                frame.showSingleProvider(providerId, providerName, providerMail, providerNumber, providerNote);
            }
        }
        
        catch (Exception e){
        }
        
        
    }
    
    @Override
    public void addProviderData(JTextField providerIdTF, JTextField providerNameTF, JTextField providerMailTF, JTextField providerNumberTF, JTextField providerNoteTF){
        try {
            String providerId, providerName, providerMail, providerNumber, providerNote;

            providerId = providerIdTF.getText();
            providerName = providerNameTF.getText();
            providerMail = providerMailTF.getText();
            providerNumber = providerNumberTF.getText();
            providerNote = providerNoteTF.getText();
            
            providerModel.addProvider(providerId, providerName, providerMail, providerNumber, providerNote);
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.ProviderController.addProviderData\n" + e);
        }
        
    }
    
    @Override
    public void showAllProviderData(JTable providerTable){
        DefaultTableModel tModel = (DefaultTableModel) providerTable.getModel();
        tModel.setRowCount(0);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String providerId, providerName, providerMail, providerNumber;
        
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ma_nhacungcap, ten_nhacungcap, diachi_email, so_dienthoai FROM nha_cungcap WHERE ghi_chu NOT LIKE '%Ngừng%';";
            rs = stmt.executeQuery(query);

            
            while (rs.next()){
                providerId = String.valueOf(rs.getString(1));
                providerName = String.valueOf(rs.getString(2));
                providerMail = String.valueOf(rs.getString(3));
                providerNumber = String.valueOf(rs.getString(4));
                
                String providerDataList[] = {providerId, providerName, providerMail, providerNumber};
                
                tModel.addRow(providerDataList);
            }
        }
        catch (Exception e) {
            System.out.println("Error in management.controllers.categories.ProviderController.showAllProviderData\n" + e);
        }
    }
    
    @Override
    public void hideProviderData(JTable providerTable){
        try {
            int row = providerTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel)providerTable.getModel();
            
            String providerId = model.getValueAt(row, 0).toString();
            providerModel.delProvider(providerId);
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.ProviderController.hideProviderData\n" + e);
        }
    }
}
