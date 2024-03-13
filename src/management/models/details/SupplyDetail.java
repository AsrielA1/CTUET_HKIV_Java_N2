/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management.models.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;

interface ISupplyDetail{
    void addSupplyDetail(String supplyId, int supplyNumber, String fishId, float weight, float cost);
    void delSupplyDetail(String supplyId, int supplyNumber);
}

public class SupplyDetail implements ISupplyDetail{
    private String supplyId;
    private int supplyNumber;
    private String fishId;
    private float weight;
    private float cost;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");

    public SupplyDetail(String supplyId, int supplyNumber, String fishId, float weight, float cost) {
        this.supplyId = supplyId;
        this.supplyNumber = supplyNumber;
        this.fishId = fishId;
        this.weight = weight;
        this.cost = cost;
    }
    
    @Override
    public void addSupplyDetail(String supplyId, int supplyNumber, String fishId, float weight, float cost){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO chitiet_lohang VALUES (?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, supplyId);
            pstmt.setInt(2, supplyNumber);
            pstmt.setString(3, fishId);
            pstmt.setFloat(4, weight);
            pstmt.setFloat(5, cost);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.addEmployee\n" + e);
        }
    }
    
    public void delSupplyDetail(String supplyId, int supplyNumber){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = ""
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.delEmployee\n" + e);
        }
    }
}
