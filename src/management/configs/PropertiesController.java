package management.configs;

import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropertiesController {
    public PropertiesController(){}
    
    public static HashMap<String, String> getProperties(Properties prop) {
        HashMap<String, String> retMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            retMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return retMap;
    }
    
    public static HashMap<String, String> getProperties() {
        Properties prop = new Properties();
        
        String parent_dir = System.getProperty("user.dir");
        
        try (FileReader reader = new FileReader(parent_dir + "\\src\\management\\configs\\db.properties")) {
            prop.load(reader);
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
        } catch (Exception e){
            System.out.println(e);
        }
        
        HashMap<String, String> retMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            retMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return retMap;
    }

}
