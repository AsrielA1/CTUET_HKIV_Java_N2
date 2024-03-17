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
    void addProvider(String providerId, String providerName, String providerEmail, String providerNumber, String providerNote);
    void delProvider(String providerId);
    void updateProvider(String providerId, String providerName, String providerEmail, String providerNumber, String providerNote);
}

public class Provider implements IProvider{
    private String providerId;
    private String providerName;
    private String providerEmail;
    private String providerNumber;
    private String providerNote;
    
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
    public void addProvider(String providerId, String providerName, String providerEmail, String providerNumber, String providerNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nha_cungcap VALUES (?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, providerId);
            pstmt.setString(2, providerName);
            pstmt.setString(3, providerEmail);
            pstmt.setString(4, providerNumber);
            pstmt.setString(5, providerNote);
            
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
            
            String query = "UPDATE nha_cungcap SET ghi_chu = 'Ngừng hợp tác' WHERE ma_nhacungcap = '" + providerId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.delProvider\n" + e);
        }
    }
    
    @Override
    public void updateProvider(String providerId, String providerName, String providerEmail, String providerNumber, String providerNote){
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            List<String> updateList = new ArrayList<>();
            if (providerName != null){
                updateList.add("ten_nhacungcap = ?");
            }
            if (providerEmail != null){
                updateList.add("diachi_email = ?");
            }
            if(providerNumber != null){
                updateList.add("so_dienthoai = ?");
            }
            if (providerNote != null){
                updateList.add("ghi_chu = ?");
            }

            if (!updateList.isEmpty()){
                String query = "UPDATE nha_cungcap SET " + String.join(", ", updateList) + " WHERE ma_nhacungcap = ?";
                System.out.println(query);
                pstmt = connection.prepareStatement(query);
                
                int parameterIndex = 1;
                if (providerName != null){
                    pstmt.setString(parameterIndex++, providerName);
                }
                if (providerEmail != null){
                    pstmt.setString(parameterIndex++, providerEmail);
                }
                if (providerNumber != null){
                    pstmt.setString(parameterIndex++, providerNumber);
                }
                if (providerNote != null){
                    pstmt.setString(parameterIndex++, providerNote);
                }
                
                pstmt.setString(parameterIndex, providerId);
                
                pstmt.executeUpdate();
            }
        }
        catch (Exception e){
            System.out.println("Error in management.models.catagories.Provider.updateProvider\n" + e);
        }
    }
}
