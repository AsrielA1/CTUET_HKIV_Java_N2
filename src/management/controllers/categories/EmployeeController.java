package management.controllers.categories;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import management.models.categories.Employee;
import management.configs.PropertiesController;

interface IEmployeeController{
    void showAllEmployeeData(JTable employeeTable);
    void addEmployeeData(JTextField TFemployeeId, JTextField TFpassword, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote);
    void hideEmployeeData(JTextField TFemployeeId);
    void updateEmployeeData(JTextField TFemployeeId, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote);
}

public class EmployeeController {
    private final Employee employeeFunction = new Employee();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public void EmployeeController(){}
    
    public void showAllEmployee(JTable employeeTable){
        DefaultTableModel tModel = (DefaultTableModel) employeeTable.getModel();
        tModel.setRowCount(0);
        
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String employeeId, employeeName, employeeNumber, employeeNote;
        
        try {            
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ma_nhanvien, ho_ten, so_dienthoai FROM nhan_vien;";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                employeeId = String.valueOf(rs.getString(1));
                employeeName = String.valueOf(rs.getString(2));
                employeeNumber = String.valueOf(rs.getString(3));
                employeeNote = String.valueOf(rs.getString(4));
                
                String employeeDataList[] = {employeeId, employeeName, employeeNumber, employeeNote};
                
                tModel.addRow(employeeDataList);
            }
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.EmployeeController.showAllEmployee" + e);
        }
    }
    
    public void addEmployeeData(JTextField TFemployeeId, JTextField TFpassword, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote){
        String employeeId, password, employeeName, employeeNumber, employeeNote;
        
        employeeId = String.valueOf(TFemployeeId.getText());
        password = String.valueOf(TFpassword.getText());
        employeeName = String.valueOf(TFemployeeName);
        employeeNumber = String.valueOf(TFemployeeNumber.getText());
        employeeNote = String.valueOf(TFemployeeNote.getText());
        
        employeeFunction.addEmployee(employeeId, password, employeeName, employeeNumber, employeeNote);
    }
    
    public void hideEmployeeData(JTextField TFemployeeId){
        String employeeId = String.valueOf(TFemployeeId.getText());
        
        employeeFunction.delEmployee(employeeId);
    }
    
    public void updateEmployeeData(JTextField TFemployeeId, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote){
        String employeeId, employeeName, employeeNumber, employeeNote;
        
        employeeId = String.valueOf(TFemployeeId.getText());
        employeeName = String.valueOf(TFemployeeName.getText());
        employeeNumber = String.valueOf(TFemployeeNumber.getText());
        employeeNote = String.valueOf(TFemployeeNote.getText());
        
        employeeFunction.updateEmployee(employeeId, employeeName, employeeNumber, employeeNote);
    }

}
    
