package management.models.histories;

import management.configs.PropertiesController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.HashMap;


interface IInputHistory{
    void addInputHistory(String supplyId, String inputDate, String inputTime, float totalWeight, float totalCost, String providerId, String inputNote);
    void delInputHistory(String supplyId);
    
}

public class Supply implements IInputHistory{
    private String supplyId;
    private String inputDate;
    private String inputTime;
    private float totalWeight;
    private float totalCost;
    private String providerId;
    private String inputNote;
    
    private final HashMap<String, String> properties = PropertiesController.getProperties();
    private final String url = properties.get("url");
    private final String dbUsername = properties.get("username");
    private final String dbPassword = properties.get("password");

    public Supply(String supplyId, String inputDate, String inputTime, float totalWeight, float totalCost, String providerId, String inputNote) {
        this.supplyId = supplyId;
        this.inputDate = inputDate;
        this.inputTime = inputTime;
        this.totalWeight = totalWeight;
        this.totalCost = totalCost;
        this.providerId = providerId;
        this.inputNote = inputNote;
    }

    public String getInputId() {
        return supplyId;
    }

    public void setInputId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getInputNote() {
        return inputNote;
    }

    public void setInputNote(String inputNote) {
        this.inputNote = inputNote;
    }
    
    @Override
    public void addInputHistory(String supplyId, String inputDate, String inputTime, float totalWeight, float totalCost, String providerId, String inputNote){
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            String query = "INSERT INTO nha_cungcap VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, providerId);
            pstmt.setString(2, inputDate);
            pstmt.setString(3, inputTime);
            pstmt.setFloat(4, totalWeight);
            pstmt.setFloat(5, totalCost);
            pstmt.setString(6, providerId);
            pstmt.setString(7, inputNote);
            
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println("Error in management.models.histories.InputHistory.addInputHistory\n" + e);
        }
    }
    
    //Không xóa nhà cung cấp trong bất kỳ tình huống nào
    @Override
    public void delInputHistory(String supplyId){
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
