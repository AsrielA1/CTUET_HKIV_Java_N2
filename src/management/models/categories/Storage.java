package management.models.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import management.configs.PropertiesController;

interface IStorage{
    void addStorage(String storageId, float currentWeight, float maxWeight, String storageNote);
    void delStorage(String storageId);
    void updateStorage(String storageId, float currentWeight, float maxWeight, String storageNote);
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
            
            String query = "UPDATE danhmuc_kho SET ghi_chu = 'Hủy' WHERE ma_kho = '" + storageId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Employee.delEmployee\n" + e);
        }
    }
    
    @Override
    public void updateStorage(String storageId, float currentWeight, float maxWeight, String storageNote){
        Connection connection = null;
        Statement stmt = null;
        List<String> updateList = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            if (storageNote != null){
                updateList.add("ghi_chu = " + storageNote);
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
            query += "WHERE ma_nhacungcap = " + storageId;
            
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            
            System.out.println(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.updateProvider\n" + e);
        }
    }
}
