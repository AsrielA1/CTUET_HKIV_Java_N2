package management.models.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;


interface IProvider{
    void addProvider(String providerId, String providerName, String providerEmail, String providerNumber);
    void delProvider(String providerId);
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

    public Provider(String providerId, String providerName, String providerEmail, String providerNumber) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerEmail = providerEmail;
        this.providerNumber = providerNumber;
    }
    
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(String providerNumber) {
        this.providerNumber = providerNumber;
    }

    @Override
    public void addProvider(String providerId, String providerName, String providerEmail, String providerNumber){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nhan_vien VALUES (?, ?, ?, ?);";
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
            
            String query = "DELETE FROM danhmuc_ca WHERE ma_nhanvien = '" + providerId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.delProvider\n" + e);
        }
    }
    
    
}
