package management.models.histories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import management.configs.PropertiesController;


interface IOutputHistory{
    void addOutputHistory(String outputId, String outputDate, String outputTime, float totalOutputWeight, float totalOutputIncome, String outputNote);
    void delOutputHistory(String outputId);
}

public class OutputHistory implements IOutputHistory{
    private String outputId;
    private String outputDate;
    private String outputTime;
    private float totalOutputWeight;
    private float totalOutputIncome;
    private String outputNote;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");

    public OutputHistory(String outputId, String outputDate, String outputTime, float totalOutputWeight, float totalOutputIncome, String outputNote) {
        this.outputId = outputId;
        this.outputDate = outputDate;
        this.outputTime = outputTime;
        this.totalOutputWeight = totalOutputWeight;
        this.totalOutputIncome = totalOutputIncome;
        this.outputNote = outputNote;
    }

    public String getOutputId() {
        return outputId;
    }

    public void setOutputId(String outputId) {
        this.outputId = outputId;
    }

    public String getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(String outputDate) {
        this.outputDate = outputDate;
    }

    public String getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(String outputTime) {
        this.outputTime = outputTime;
    }

    public float getTotalOutputWeight() {
        return totalOutputWeight;
    }

    public void setTotalOutputWeight(float totalOutputWeight) {
        this.totalOutputWeight = totalOutputWeight;
    }

    public float getTotalOutputIncome() {
        return totalOutputIncome;
    }

    public void setTotalOutputIncome(float totalOutputIncome) {
        this.totalOutputIncome = totalOutputIncome;
    }

    public String getOutputNote() {
        return outputNote;
    }

    public void setOutputNote(String outputNote) {
        this.outputNote = outputNote;
    }
    
    @Override
    public void addOutputHistory(String outputId, String outputDate, String outputTime, float totalOutputWeight, float totalOutputIncome, String outputNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO lichsu_xuatkho VALUES (?, ?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, outputId);
            pstmt.setString(2, outputDate);
            pstmt.setString(3, outputTime);
            pstmt.setFloat(4, totalOutputWeight);
            pstmt.setFloat(5, totalOutputIncome);
            pstmt.setString(6, outputNote);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.histories.OutputHistory.addOutputHistory\n" + e);
        }
    }
    
    @Override
    public void delOutputHistory(String outputId){
        Connection connection = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "DELETE FROM lichsu_xuatkho WHERE ma_xuatkho = '" + outputId + "';";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("Error in management.models.hítories.OutputHistory.delOutputHistory\n" + e);
        }
    }
    
}
