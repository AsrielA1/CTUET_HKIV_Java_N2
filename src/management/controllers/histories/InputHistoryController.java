package management.controllers.histories;

import management.configs.PropertiesController;
import management.models.histories.InputHistory;

import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

interface IIntputHistoryController{
    void showAllInputHistory(JTable inputHistoryTable);
    void addInputHistory(JTextField inputHistoryIdTF, JDateChooser inputDateChooser, JTextField inputTimeTF, JTextField providerIdTF, JTextField inputHistoryNoteTF);
    void hideInputHistory(JTable inputHistoryTable);
}

public class InputHistoryController implements IIntputHistoryController{
    private final InputHistory inputHistory = new InputHistory();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public InputHistoryController(){}
    
    @Override
    public void showAllInputHistory(JTable inputHistoryTable){
        DefaultTableModel tModel = (DefaultTableModel)inputHistoryTable.getModel();
        tModel.setRowCount(0);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String inputHistoryId, inputDateTime, providerId, totalWeight, totalCost;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ma_lohang, CONCAT(thoigian_nhap, ' ', ngay_nhap), nha_cungcap, tong_khoiluong, tong_chiphi FROM lichsu_nhapkho WHERE ghi_chu NOT LIKE '%Hủy%'";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                System.out.println("This worked");
                
                inputHistoryId = rs.getString(1);
                inputDateTime = rs.getString(2);
                providerId = rs.getString(3);
                totalWeight = String.valueOf(rs.getFloat(4));
                totalCost = String.valueOf(rs.getFloat(5));
                
                String inputHistoryList[] = {inputHistoryId, inputDateTime, providerId, totalWeight, totalCost};
                
                tModel.addRow(inputHistoryList);
            }
                        
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.IntputHistoryController.showAllInputHistory\n" + e);
        }
    }
    
    @Override
    public void addInputHistory(JTextField inputHistoryIdTF, JDateChooser inputDateChooser, JTextField inputTimeTF, JTextField providerIdTF, JTextField inputHistoryNoteTF){
        String inputHistoryId, inputDate, inputTime, providerId, inputHistoryNote;
        
        SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        inputHistoryId = inputHistoryIdTF.getText();
        inputDate = desiredFormat.format(inputDateChooser.getDate());
        inputTime = inputTimeTF.getText();
        providerId = providerIdTF.getText();
        inputHistoryNote = inputHistoryNoteTF.getText();
        
        inputHistory.addInputHistory(inputHistoryId, inputDate, inputTime, providerId, inputHistoryNote);
    }
    
    @Override
    public void hideInputHistory(JTable inputHistoryTable){
        int row = inputHistoryTable.getSelectedRow();
        DefaultTableModel tModel = (DefaultTableModel)inputHistoryTable.getModel();
        
        String inputId = tModel.getValueAt(row, 0).toString();
        inputHistory.delInputHistory(inputId);
    }
    
}
