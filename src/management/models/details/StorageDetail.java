package management.models.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.HashMap;
import management.configs.PropertiesController;
import management.models.categories.Storage;
import management.models.categories.Fish;

//Không được xóa dữ liệu trong database
interface IStorageDetail{
    void addStorageDetail(String storageId, String fishId);
    void delStorageDetail(String storageId, String fishId);
}

public class StorageDetail implements IStorageDetail{
    private Fish fishId;
    private Storage storageId;
    private float weight;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");

    public StorageDetail(Fish fishId, Storage storageId) {
        this.fishId = fishId;
        this.storageId = storageId;
    }

    public Fish getFishId() {
        return fishId;
    }

    public void setFishId(Fish fishId) {
        this.fishId = fishId;
    }

    public Storage getStorageId() {
        return storageId;
    }

    public void setStorageId(Storage storageId) {
        this.storageId = storageId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    @Override
    public void addStorageDetail(String storageId, String fishId){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nhan_vien VALUES (?, ?, 0.0);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, storageId);
            pstmt.setString(2, fishId);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.details.StorageDetail.addStorageDetail\n" + e);
        }
    }
    
    @Override
    public void delStorageDetail(String storageId, String fishId){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM chitiet_kho WHERE ma_kho = ? AND ma_loaica = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, storageId);
            pstmt.setString(2, fishId);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.delEmployee\n" + e);
        }
    }
}
