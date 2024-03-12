package management.models.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;

interface IStorage{
    void addStorage(String storageId, float currentWeight, float maxWeight, String storageNote);
    void delStorage(String storageId);
}

public class Storage implements IStorage{
    private String storageId;
    private float currentWeight;
    private float maxWeight;
    private String storageNote;
    
    public Storage(){}

    public Storage(String storageId, float currentWeight, float maxWeight, String storageNote) {
        this.storageId = storageId;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.storageNote = storageNote;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getStorageNote() {
        return storageNote;
    }

    public void setStorageNote(String storageNote) {
        this.storageNote = storageNote;
    }
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    @Override
    public void addStorage(String storageId, float currentWeight, float maxWeight, String storageNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO danhmuc_kho VALUES (?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, storageId);
            pstmt.setFloat(2, currentWeight);
            pstmt.setFloat(3, maxWeight);
            pstmt.setString(4, storageNote);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Storage.addStorage\n" + e);
        }
    }
    
    @Override
    public void delStorage(String storageId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM danhmuc_kho WHERE ma_kho = '" + storageId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.delEmployee\n" + e);
        }
    }
}
