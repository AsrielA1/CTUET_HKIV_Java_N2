package management.models.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import management.configs.PropertiesController;


interface IProvider{
    void addProvider(String providerId, String providerName, String providerEmail, String providerNumber);
    void delProvider(String providerId);
    void updateProvider(String providerId, String providerName, String providerEmail, String providerNumber);
}

public class Provider implements IProvider{
    private String providerId;
    private String providerName;
    private String providerEmail;
    private String providerNumber;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public Provider(){}

    public Provider(String providerId, String providerName, String providerEmail, String providerNumber) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerEmail = providerEmail;
        this.providerNumber = providerNumber;
    }

    @Override
    public void addProvider(String providerId, String providerName, String providerEmail, String providerNumber){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nha_cungcap VALUES (?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, providerId);
            pstmt.setString(2, providerName);
            pstmt.setString(3, providerEmail);
            pstmt.setString(4, providerNumber);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.addProvider\n" + e);
        }
    }
    
    //Không xóa nhà cung cấp trong bất kỳ tình huống nào
    @Override
    public void delProvider(String providerId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM nha_cungcap WHERE ma_nhacungcap = '" + providerId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.delProvider\n" + e);
        }
    }
    
    @Override
    public void updateProvider(String providerId, String providerName, String providerEmail, String providerNumber){
        Connection connection = null;
        Statement stmt = null;
        List<String> updateList = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            if (providerName != null){
                updateList.add("ten_nhacungcap = " + providerName);
            }
            if (providerEmail != null){
                updateList.add("so_dienthoai = " + providerEmail);
            }
            if (providerNumber != null){
                updateList.add("so_dienthoai = " + providerNumber);
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
            query += "WHERE ma_nhacungcap = " + providerId;
            
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            
            System.out.println(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.updateProvider\n" + e);
        }
    }
}
