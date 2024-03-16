package management.models.categories;

import management.configs.PropertiesController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


interface IEmployee{
    void addEmployee(String employeeId, String employeePassword, String employeeName, String employeeNumber);
    void delEmployee(String employeeId);
    void updateEmployee(String employeeId, String employeeName, String employeeNumber);
}

public class Employee implements IEmployee{
    private String employeeId;
    private String password;
    private String employeeName;
    private String employeeNumber;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public Employee(){}

    public Employee(String employeeId, String password, String employeeName, String employeeNumber) {
        this.employeeId = employeeId;
        this.password = password;
        this.employeeName = employeeName;
        this.employeeNumber = employeeNumber;
    }
    
    @Override
    public void addEmployee(String employeeId, String employeePassword, String employeeName, String employeeNumber){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nhan_vien VALUES (?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, employeeId);
            pstmt.setString(2, employeePassword);
            pstmt.setString(3, employeeName);
            pstmt.setString(4, employeeNumber);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.addEmployee\n" + e);
        }
    }
    
    @Override
    public void delEmployee(String employeeId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "UPDATE nhan_vien SET ghi_chu = 'Nghỉ' WHERE ma_nhanvien = '" + employeeId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.delEmployee\n" + e);
        }
    }
    
    
    @Override
    public void updateEmployee(String employeeId, String employeeName, String employeeNumber){
        Connection connection = null;
        Statement stmt = null;
        List<String> updateList = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            if (employeeName != null){
                updateList.add("ho_ten = " + employeeName);
            }
            if (employeeNumber != null){
                updateList.add("so_dienthoai = " + employeeNumber);
            }
            
            String query = "UPDATE nhan_vien ";
            if (!updateList.isEmpty()){
                query += " SET ";
                for (int i = 0; i < updateList.size(); i++) {
                    query += updateList.get(i);
                    if (i < updateList.size() - 1) {
                        query += ", ";
                    }
                }
            }
            query += "WHERE ma_nhanvien = " + employeeId;
            
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            
            System.out.println(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.updateEmployee\n" + e);
        }
    }
}
