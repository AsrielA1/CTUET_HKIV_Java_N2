package management.models.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;

interface IInputDetail{
    void addInputDetail(String supplyId, int supplyNumber, String storageId, String fishId, float weight);
    void delInputDetail(String supplyId, int supplyNumber);
}

public class InputDetail {
    private String supplyId;
    private int supplyNumber;
    private String storageId;
    private String fishId;
    private float weight;
    
    public InputDetail(){}

    public InputDetail(String supplyId, int supplyNumber, String storageId, String fishId, float weight) {
        this.supplyId = supplyId;
        this.supplyNumber = supplyNumber;
        this.storageId = storageId;
        this.fishId = fishId;
        this.weight = weight;
    }
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    
    public void addInputDetail(String supplyId, int supplyNumber, String storageId, String fishId, float weight){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nhan_vien VALUES (?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, supplyId);
            pstmt.setInt(2, supplyNumber);
            pstmt.setString(3, storageId);
            pstmt.setFloat(4, weight);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.addInputDetail\n" + e);
        }
    }
    
    public void delInputDetail(String supplyId, int supplyNumber){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM chitiet_nhapkho WHERE ma_nhapkho = ? AND so_thutu = ?;";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, supplyId);
            pstmt.setInt(2, supplyNumber);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.delInputeDetail\n" + e);
        }
    }
}
