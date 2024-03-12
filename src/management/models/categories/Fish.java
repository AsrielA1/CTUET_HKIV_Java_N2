package management.models.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


interface IFish{    
    void addFishData(String fishId, String fishName, float tempCUp, float tempCLow, float humidityUp, float humidityLow, String fishNote);
    void delFishData(String fishId);
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
    
    @Override
    public void addFishData(String fishId, String fishName, float tempCUp, float tempCLow, float humidityUp, float humidityLow, String fishNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        String id = fishId;
        String name = fishName;
        
        float _tempC_up = tempCUp;
        float _tempC_low = tempCLow;
        float _humidity_up = humidityUp;
        float _humidity_low = humidityLow;
        
        String note = fishNote;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Postgres");
            
            String query = "INSERT INTO danhmuc_ca VALUES(?, ?, ?, ?, ?, ?, ?); "; 
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setFloat(3, _tempC_up);
            pstmt.setFloat(4, _tempC_low);
            pstmt.setFloat(5, _humidity_up);
            pstmt.setFloat(6, _humidity_low);
            pstmt.setString(7, note);
            
            int status = pstmt.executeUpdate();
            if (status > 0)
                System.out.println("INSERTED");
            else 
                System.out.println("SOMETHING WRONG");
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void delFishData(String fishId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            
            String query = "DELETE FROM danhmuc_ca WHERE ma_loaica = '" + fishId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
        }
    }
}
