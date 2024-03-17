package management.models.categories;

import management.configs.PropertiesController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


interface IEmployee{
    void addEmployee(String employeeId, String employeePassword, String employeeName, String employeeNumber, String employeeNote);
    void delEmployee(String employeeId);
    void updateEmployee(String employeeId, String employeeName, String employeeNumber, String employeeNote);
}

public class Employee implements IEmployee{
    private String employeeId;
    private String password;
    private String employeeName;
    private String employeeNumber;
    private String employeeNote;
    
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
    public void addEmployee(String employeeId, String employeePassword, String employeeName, String employeeNumber, String employeeNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nhan_vien VALUES (?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, employeeId);
            pstmt.setString(2, employeePassword);
            pstmt.setString(3, employeeName);
            pstmt.setString(4, employeeNumber);
            pstmt.setString(5, employeeNote);
            
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
    public void updateEmployee(String employeeId, String employeeName, String employeeNumber, String employeeNote){
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            List<String> updateList = new ArrayList<>();
            if (employeeName != null){
                updateList.add("ho_ten = ?");
            }
            if (employeeNumber != null){
                updateList.add("so_dienthoai = ?");
            }
            if (employeeNote != null){
                updateList.add("ghi_chu = ?");
            }

            if (!updateList.isEmpty()){
                String query = "UPDATE nhan_vien SET " + String.join(", ", updateList) + " WHERE ma_nhanvien = ?";
                System.out.println(query);
                pstmt = connection.prepareStatement(query);
                
                int parameterIndex = 1;
                if (employeeName != null){
                    pstmt.setString(parameterIndex++, employeeName);
                }
                if (employeeNumber != null){
                    pstmt.setString(parameterIndex++, employeeNumber);
                }
                if (employeeNote != null){
                    pstmt.setString(parameterIndex++, employeeNote);
                }
                pstmt.setString(parameterIndex, employeeId);

                pstmt.executeUpdate();
            }
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.updateEmployee\n" + e);
        }
    }

}
