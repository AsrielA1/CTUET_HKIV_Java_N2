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
    void setEditable(JTextField employeeNameTF, JTextField employeeNumberTF, JTextField employeeNoteTF, boolean b);
    void showSingleEmployee(String employeeId, JTextField employeeIdTF, JTextField employeeNameTF, JTextField employeeNumberTF, JTextField employeeNoteTF);
    void showAllEmployee(JTable employeeTable);
    void addEmployeeData(JTextField TFemployeeId, JTextField TFpassword, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote);
    void hideEmployeeData(String employeeId);
    void updateEmployeeData(JTextField TFemployeeId, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote);
}

public class EmployeeController implements IEmployeeController{
    private final Employee employeeFunction = new Employee();
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public void EmployeeController(){}
    
    @Override
    public void setEditable(JTextField employeeNameTF, JTextField employeeNumberTF, JTextField employeeNoteTF, boolean b){
        employeeNameTF.setEditable(b);
        employeeNumberTF.setEditable(b);
        employeeNoteTF.setEditable(b);
    }
    
    @Override
    public void showSingleEmployee(String employeeId, JTextField employeeIdTF, JTextField employeeNameTF, JTextField employeeNumberTF, JTextField employeeNoteTF){
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        String employeeName, employeeNumber, employeeNote;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            stmt = connection.createStatement();
            query = "SELECT ho_ten, so_dienthoai, ghi_chu FROM nhan_vien WHERE ma_nhanvien = '" + employeeId + "'";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                employeeName = String.valueOf(rs.getString(1));
                employeeNumber = String.valueOf(rs.getString(2));
                employeeNote = String.valueOf(rs.getString(3));
                
                employeeIdTF.setText(employeeId);
                employeeNameTF.setText(employeeName);
                employeeNumberTF.setText(employeeNumber);
                employeeNoteTF.setText(employeeNote);
            }
            
            
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.EmployeeController.showSingleEmployee\n" + e);
        }
    }
    
    
    @Override
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
            query = "SELECT ma_nhanvien, ho_ten, so_dienthoai FROM nhan_vien WHERE ghi_chu NOT LIKE '%Nghỉ%';";
            rs = stmt.executeQuery(query);
            
            while (rs.next()){
                employeeId = String.valueOf(rs.getString(1));
                employeeName = String.valueOf(rs.getString(2));
                employeeNumber = String.valueOf(rs.getString(3));
                
                String employeeDataList[] = {employeeId, employeeName, employeeNumber};
                
                tModel.addRow(employeeDataList);
            }
        }
        catch (Exception e){
            System.out.println("Error in management.controllers.categories.EmployeeController.showAllEmployee\n" + e);
        }
    }
    
    @Override
    public void addEmployeeData(JTextField TFemployeeId, JTextField TFpassword, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote){
        try {
        
            String employeeId, password, employeeName, employeeNumber, employeeNote;

            employeeId = String.valueOf(TFemployeeId.getText());
            password = String.valueOf(TFpassword.getText());
            employeeName = String.valueOf(TFemployeeName.getText());
            employeeNumber = String.valueOf(TFemployeeNumber.getText());
            employeeNote = String.valueOf(TFemployeeNote.getText());

            employeeFunction.addEmployee(employeeId, password, employeeName, employeeNumber, employeeNote);
        }
        catch (Exception e){
            System.out.println("Error in Error in management.controllers.categories.EmployeeController.addEmployeeData\n" + e);
        }
    }
    
    @Override
    public void hideEmployeeData(String employeeId){
        employeeFunction.delEmployee(employeeId);
    }
    
    @Override
    public void updateEmployeeData(JTextField TFemployeeId, JTextField TFemployeeName, JTextField TFemployeeNumber, JTextField TFemployeeNote){
        String employeeId, employeeName, employeeNumber, employeeNote;
        
        employeeId = String.valueOf(TFemployeeId.getText());
        employeeName = String.valueOf(TFemployeeName.getText());
        employeeNumber = String.valueOf(TFemployeeNumber.getText());
        employeeNote = String.valueOf(TFemployeeNote.getText());
        
        employeeFunction.updateEmployee(employeeId, employeeName, employeeNumber, employeeNote);
    }

}
    
