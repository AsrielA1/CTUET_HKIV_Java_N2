package management.controllers.histories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import management.configs.PropertiesController;

import management.models.details.OutputDetail;

interface IOutputDetailController{
    void showAllOutputDetail(JTable outputDetailTable, String outputHistoryId);
    void addOutHistoryIdChooser(JComboBox outputHistoryIdChooser);
    void addStorageIdChooser(JComboBox storageIdChooser);
}

public class OutputDetailController implements IOutputDetailController{
    private final OutputDetail outputDetail = new OutputDetail();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public OutputDetailController(){};
    
    @Override
    public void showAllOutputDetail(JTable outputDetailTable, String outputHistoryId){
        DefaultTableModel tModel = (DefaultTableModel)outputDetailTable.getModel();
        tModel.setRowCount(0);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String outputDetailId, storageId, weight, income, incomePerWeight;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT so_thutu, ma_kho, khoi_luong, doanh_thu, doanhthu_theodv FROM chitiet_xuatkho WHERE ghi_chu NOT LIKE '%Hủy%' AND ma_xuatkho = '" + outputHistoryId + "';";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                outputDetailId = rs.getString(1);
                storageId = rs.getString(2);
                weight = String.valueOf(rs.getFloat(3));
                income = String.valueOf(rs.getFloat(4));
                incomePerWeight = String.valueOf(rs.getFloat(5));
                
                String outputDetailData[] = {outputDetailId, storageId, weight, income, incomePerWeight};
                
                tModel.addRow(outputDetailData);
            }
            
            
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.OutputDetailController.showAllOutputDetail\n" + e);
        }
    }
    
    @Override
    public void addOutHistoryIdChooser(JComboBox outputHistoryIdChooser){
    
    }
    
    @Override
    public void addStorageIdChooser(JComboBox storageIdChooser){
        storageIdChooser.removeAllItems();
        
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
                storageIdChooser.addItem(rs.getString(1));
            }
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.InputDetailController.addStorageIdChooser\n" + e);
        }
    }
}
