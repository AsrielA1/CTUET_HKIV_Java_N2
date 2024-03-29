package management.models.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.HashMap;
import management.configs.PropertiesController;

interface IInputDetail{
    void addWeightToStorage(String storageId, float weight);
    void addWeightToInputHistory(String supplyId, float weight, float cost);
    void addInputDetail(String supplyId, String storageId, float costPerWeight, float weight, String inputDetailNote);
    
    void minusWeightFromStorage(String storageId, float weight);
    void minusWeightFromInputHistory(String supplyId, float weight, float cost);
    void delInputDetail(String supplyId, int supplyNumber);
}

public class InputDetail implements IInputDetail{
    private String supplyId;
    private int supplyNumber;
    private String storageId;
    private float costPerWeight;
    private float weight;
    private String inputDetailNote;
    
    public InputDetail(){}

        public InputDetail(String supplyId, int supplyNumber, String storageId, float weight, float costPerWeight, String inputDetailNote) {
        this.supplyId = supplyId;
        this.supplyNumber = supplyNumber;
        this.storageId = storageId;
        this.weight = weight;
        this.inputDetailNote = inputDetailNote;
    }
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    @Override
    public void addWeightToInputHistory(String supplyId, float weight, float cost){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "UPDATE lichsu_nhapkho SET tong_khoiluong = tong_khoiluong + ? WHERE ma_lohang = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, weight);
            pstmt.setString(2, supplyId);            
            pstmt.executeUpdate();
            
            query = "UPDATE lichsu_nhapkho SET tong_chiphi = tong_chiphi + ? WHERE ma_lohang = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, cost);
            pstmt.setString(2, supplyId);
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.addWeightToInputHistory\n" + e);
        }
    }
    
    @Override
    public void addWeightToStorage(String storageId, float weight){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "UPDATE danhmuc_kho SET khoiluong_hientai = khoiluong_hientai + ? WHERE ma_kho = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setFloat(1, weight);
            pstmt.setString(2, storageId);
            
            pstmt.executeUpdate();
            
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.addWeightToStorage\n" + e);
        }
    }
    
    @Override
    public void addInputDetail(String supplyId, String storageId, float costPerWeight, float weight, String inputDetailNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO chitiet_nhapkho(ma_lohang, ma_kho, chiphi_theodv, khoi_luong, chi_phi, ghi_chu) VALUES (?, ?, ?, ?, ?, ?);";
            
            float cost = costPerWeight * weight;
            
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, supplyId);
            pstmt.setString(2, storageId);
            pstmt.setFloat(3, costPerWeight);
            pstmt.setFloat(4, weight);
            pstmt.setFloat(5, cost);
            pstmt.setString(6, inputDetailNote);
            
            pstmt.executeUpdate();
            
            addWeightToStorage(storageId, weight);
            addWeightToInputHistory(supplyId, weight, cost);
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.addInputDetail\n" + e);
        }
    }
    
    @Override
    public void minusWeightFromStorage(String storageId, float weight){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "UPDATE danhmuc_kho SET khoiluong_hientai = khoiluong_hientai - ? WHERE ma_kho = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setFloat(1, weight);
            pstmt.setString(2, storageId);
            
            pstmt.executeUpdate();
            
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.minusWeightFromStorage\n" + e);
        }
    }
    
    @Override
    public void minusWeightFromInputHistory(String supplyId, float weight, float cost){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "UPDATE lichsu_nhapkho SET tong_khoiluong = tong_khoiluong - ? WHERE ma_lohang = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, weight);
            pstmt.setString(2, supplyId);
            pstmt.executeUpdate();
            
            query = "UPDATE lichsu_nhapkho SET tong_chiphi = tong_chiphi - ? WHERE ma_lohang = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, cost);
            pstmt.setString(2, supplyId);
            pstmt.executeUpdate();
            
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.InputDetail.minusWeightFromInputHistory\n" + e);
        }
    }
    
    @Override
    public void delInputDetail(String supplyId, int supplyNumber){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String _storageId;
        float _weight;
        float _cost;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "SELECT ma_kho, khoi_luong, chi_phi FROM chitiet_nhapkho WHERE ma_lohang = ? AND so_thutu = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, supplyId);
            pstmt.setInt(2, supplyNumber);
            
            rs = pstmt.executeQuery();
            if (rs.next()){
                _storageId = rs.getString(1);
                _weight = rs.getFloat(2);
                _cost = rs.getFloat(3);
                
                
                minusWeightFromStorage(_storageId, _weight);
                minusWeightFromInputHistory(supplyId, _weight, _cost);
            }
            
            query = "UPDATE chitiet_nhapkho SET ghi_chu = 'Hủy' WHERE ma_lohang = ? AND so_thutu = ?;";
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
