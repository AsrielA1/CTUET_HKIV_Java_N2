/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package management;
import java.sql.Connection;
import java.io.BufferedReader; 
import java.io.File; 
import java.io.IOException; 
import java.io.InputStreamReader; 
/**
 *
 * @author Asriel
 */
public class ColdStorage {
    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
    }
}
    
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("ping www.stackabuse.com");
            printResults(process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
