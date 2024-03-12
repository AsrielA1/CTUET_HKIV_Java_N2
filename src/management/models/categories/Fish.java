package management.models.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;


interface IFish{    
    void addFish(String fishId, String fishName, float tempCUp, float tempCLow, float humidityUp, float humidityLow, String fishNote);
    void delFish(String fishId);
}

public class Fish implements IFish{
    private String fishId;
    private String fishName;
      
    private String fishNote;
    
    public Fish(){}

    public Fish(String fishId, String fishName, String fishNote) {
        this.fishId = fishId;
        this.fishName = fishName;
        this.fishNote = fishNote;
    }

    public String getFishId() {
        return fishId;
    }

    public void setFishId(String fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }
    
    public String getFishNote(){
        return fishNote;
    }

    public void setFishNote(String fishNote) {
        this.fishNote = fishNote;
    }
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    @Override
    public void addFish(String fishId, String fishName, float tempCUp, float tempCLow, float humidityUp, float humidityLow, String fishNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        String id = fishId;
        String name = fishName;
        
        String note = fishNote;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO danhmuc_ca VALUES(?, ?, ?, ?, ?, ?, ?); "; 
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setFloat(3, tempCUp);
            pstmt.setFloat(4, tempCLow);
            pstmt.setFloat(5, humidityUp);
            pstmt.setFloat(6, humidityLow);
            pstmt.setString(7, note);
            
            int status = pstmt.executeUpdate();
            if (status > 0)
                System.out.println("INSERTED");
            else 
                System.out.println("SOMETHING WRONG");
            
        } catch (Exception e){
            System.out.println("Error in management.models.categories.Fish.addFishData\n" + e);
        }
    }
    
    @Override
    public void delFish(String fishId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM danhmuc_ca WHERE ma_loaica = '" + fishId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.categories.Fish.delFish\n" + e);
        }
    }
}
