package management.models.inspections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;


interface IQualityInspection{
    void addQualityInspection(String inspectionId, String storageId, String inspectionDate, float lossWeight, float lossRatio, String inspectionNote);
    void delQualityInspection(String inspectionId);
}

public class QualityInspection implements IQualityInspection{
    private String inspectionId;
    private String storageId;
    private String inspectionDate;
    private float lossWeight;
    private float lossRatio;
    private String inspectionNote;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");

    public QualityInspection(String inspectionId, String storageId, String inspectionDate, float lossWeight, float lossRatio, String inspectionNote) {
        this.inspectionId = inspectionId;
        this.storageId = storageId;
        this.inspectionDate = inspectionDate;
        this.lossWeight = lossWeight;
        this.lossRatio = lossRatio;
        this.inspectionNote = inspectionNote;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public float getLossWeight() {
        return lossWeight;
    }

    public void setLossWeight(float lossWeight) {
        this.lossWeight = lossWeight;
    }

    public float getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(float lossRatio) {
        this.lossRatio = lossRatio;
    }

    public String getInspectionNote() {
        return inspectionNote;
    }

    public void setInspectionNote(String inspectionNote) {
        this.inspectionNote = inspectionNote;
    }
    
    @Override
    public void addQualityInspection(String inspectionId, String storageId, String inspectionDate, float lossWeight, float lossRatio, String inspectionNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO danhgia_chatluong VALUES (?, ?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, inspectionId);
            pstmt.setString(2, storageId);
            pstmt.setString(3, inspectionDate);
            pstmt.setFloat(4, lossWeight);
            pstmt.setFloat(5, lossRatio);
            pstmt.setString(6, inspectionNote);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.inspections.QualityInspection.addQualityInspection\n" + e);
        }
    }
    
    @Override
    public void delQualityInspection(String inspectionId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM danhgia_chatluong WHERE ma_xuatkho = '" + inspectionId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.inspections.QualityInspection.delQualityInspection\n" + e);
        }
    }
}
