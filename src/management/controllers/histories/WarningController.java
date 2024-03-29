package management.controllers.histories;

import management.models.warning.Warning;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import management.configs.PropertiesController;

interface IWarningController{
    
}

public class WarningController {
    private final Warning warningModel = new Warning();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public WarningController(){}
    
    public void showAllWarning(JTable warningTable, JDateChooser dateChooser){
        DefaultTableModel tModel = (DefaultTableModel)warningTable.getModel();
        tModel.setRowCount(0);
        
        SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String warningDate = desiredFormat.format(dateChooser.getDate());
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String warningNumber, warningDateTime, storageId, warningType, levelOfThreat, warningNote;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT so_thutu, STR(thoigian_canhbao + ' ' + ngay_canhbao), ma_kho, loai_canhbao, mucdo_nghiemtrong, noidung_canhbao WHERE ngay_canhbao = '" + warningDate +"';";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                warningNumber = rs.getString(1);
                warningDateTime = rs.getString(2);
                storageId = rs.getString(3);
                warningType = rs.getString(4);
                levelOfThreat = rs.getString(5);
                warningNote = rs.getString(6);
                
                String warningData[] = {warningNumber, warningDateTime, storageId, warningType, levelOfThreat, warningNote};
                
                tModel.addRow(warningData);
            }
                        
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.histories.WarningController.showAllWarning\n" + e);
        }
    }
}
