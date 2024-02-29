/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management.Database;
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

public class DatabaseControl implements IDatabaseControl{
    // Kiểm tra đã cài Postgres chưa
    @Override
    public boolean checkPostgres(){
        
    }
    
}
