/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader; 
import java.io.File; 
import java.io.IOException; 
import java.io.InputStreamReader; 
/**
 *
 * @author Asriel
 */

interface IDatabaseControl{
    public boolean checkPostgres();
}

public class  DatabaseControl implements IDatabaseControl{
    // Kiểm tra đã cài Postgres chưa
    @Override
    public boolean checkPostgres() {
        try {
            Process process = Runtime.getRuntime().exec("psql --version");
            int exitCode = process.waitFor();  // Wait for the process to finish

            if (exitCode == 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("psql")) {
                        return true;
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
