package management.configs;

import java.io.FileReader;

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
        
        try (FileReader reader = new FileReader("./db.properties")) {
            prop.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        HashMap<String, String> retMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            retMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return retMap;
    }

}
