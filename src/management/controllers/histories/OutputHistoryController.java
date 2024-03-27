package management.controllers.histories;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;
import management.configs.PropertiesController;


import management.models.histories.OutputHistory;
import management.views.histories.output.*;

interface IOutputHistoryController{
    void showAllOutputData(JTable outputHistoryTable);
    void addOutputHistoryData(JTextField outputHistoryIdTF, JDateChooser outputDateChooser, JTextField outputTimeTF, JTextField outputHistoryNote);
}

public class OutputHistoryController {
    private final OutputHistory outputHistory = new OutputHistory();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public OutputHistoryController(){}
    
    public void showAllOutputData(JTable outputHistoryTable){
        DefaultTableModel tModel = (DefaultTableModel)outputHistoryTable.getModel();
        tModel.setRowCount(0);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String outputId, outputTimeDate, totalWeight, totalIncome;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ma_xuatkho, CONCAT(thoigian_xuat, ' ', ngay_xuat), tong_khoiluong, tong_doanhthu FROM lichsu_xuatkho WHERE ghi_chu NOT LIKE '%Hủy%'";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                System.out.println("This worked");
                
                outputId = rs.getString(1);
                outputTimeDate = rs.getString(2);
                totalWeight = rs.getString(3);
                totalIncome = String.valueOf(rs.getFloat(4));
                
                String inputHistoryList[] = {outputId, outputTimeDate, totalWeight, totalIncome};
                
                tModel.addRow(inputHistoryList);
            }
                        
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.histories.OutputHistoryController.showAllOutputData\n" + e);
        }
    }
    
    public void addOutputHistoryData(JTextField outputHistoryIdTF, JDateChooser outputDateChooser, JTextField outputTimeTF, JTextField outputHistoryNoteTF){
        String outputHistoryId,  outputDate, outputTime, outputHistoryNote;
        
        SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        outputHistoryId = outputHistoryIdTF.getText();
        outputDate = desiredFormat.format(outputDateChooser.getDate());
        outputTime = outputTimeTF.getText();
        outputHistoryNote = outputHistoryNoteTF.getText();
        
        outputHistory.addOutputHistory(outputHistoryId, outputDate, outputTime, outputHistoryNote);
    }
}
