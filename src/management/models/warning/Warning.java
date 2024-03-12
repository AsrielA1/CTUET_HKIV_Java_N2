package management.models.warning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;


interface IWarning{
    void addWarning(String warningDate, String warningTime, String storageId, String warningType, String levelOfThreat, String warningNote);
    void delWarning(String warningDate, String warningTime);
}

public class Warning implements IWarning{
    private String warningDate;
    private String warningTime;
    private String storageId;
    private String warningType;
    private String levelOfThreat;
    private String warningNote;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");
    
    public Warning(){}

    public Warning(String warningDate, String warningTime, String storageId, String warningType, String levelOfThreat, String warningNote) {
        this.warningDate = warningDate;
        this.warningTime = warningTime;
        this.storageId = storageId;
        this.warningType = warningType;
        this.levelOfThreat = levelOfThreat;
        this.warningNote = warningNote;
    }

    public String getWarningDate() {
        return warningDate;
    }

    public void setWarningDate(String warningDate) {
        this.warningDate = warningDate;
    }

    public String getWarningTime() {
        return warningTime;
    }

    public void setWarningTime(String warningTime) {
        this.warningTime = warningTime;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public String getLevelOfThreat() {
        return levelOfThreat;
    }

    public void setLevelOfThreat(String levelOfThreat) {
        this.levelOfThreat = levelOfThreat;
    }

    public String getWarningNote() {
        return warningNote;
    }

    public void setWarningNote(String warningNote) {
        this.warningNote = warningNote;
    }
    
    @Override
    public void addWarning(String warningDate, String warningTime, String storageId, String warningType, String levelOfThreat, String warningNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO canh_bao VALUES (?, ?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, warningDate);
            pstmt.setString(2, warningTime);
            pstmt.setString(3, storageId);
            pstmt.setString(4, warningType);
            pstmt.setString(5, levelOfThreat);
            pstmt.setString(6, warningNote);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.warning.Warning.addWarning\n" + e);
        }
    }
    
    @Override
    public void delWarning(String warningDate, String warningTime){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM canh_bao WHERE ngay_canhbao = ? AND thoigian_canhbao = ?";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, warningDate);
            pstmt.setString(2, warningTime);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.warning.Warning.delWarning\n" + e);
        }
    }
}
