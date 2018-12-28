package general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import sys.SystemInterface;

public class Settings {
    private static Map<String, String> settings;
    private static Set<String> customProperties;
    
    public static void init() {
        File settingsFile = new File(Constants.DEFAULT_SETTINGS_FILE);
        if (!settingsFile.exists()) {
            new File(Constants.DEFAULT_SETTINGS_PATH).mkdirs();
            saveDefault();
        }
        
        load();
    }
    
    public static String get(String name){
        String val = settings.get(name);
        return val;
    }
    
    public static Set<String> getCustomProperties() {
        return customProperties==null?new HashSet<>():new HashSet<>(customProperties);
    }
    
    public static void set(String name, String prop) {
        if (settings == null) settings = new HashMap<>();
        settings.put(name, prop);
    }
    
    public static void setCustom(String name, String prop) {
        if (customProperties == null) customProperties = new HashSet<>();
        settings.put(name, prop);
        customProperties.add(name);
    }
    
    public static void load() {
        load(Constants.DEFAULT_SETTINGS_FILE);
    }
    
    private static boolean load(String path) {
        try (final Scanner sc = new Scanner(new File(path))) {
            final Map<String, String> newSettings = new HashMap<>();
            final Set<String> newCustomProperties = new HashSet<>();
            
            String[] line;
            if (!sc.hasNextLine()) {
                return false;
            }
            
            line = sc.nextLine().split(":");
            
            String key = line[0];
            String value = line[1];
            
            if (key.equals("Load")) {
                sc.close();
                return load(line[1]);
            }
            
            do {
                if (key.startsWith("_")) {
                    key = key.substring(1,key.length());
                    newCustomProperties.add(key);
                }
                newSettings.put(key, value);
                line = sc.nextLine().split(":");
                key = line[0];
                value = line[1];
            } while (sc.hasNextLine());
            
             if (key.startsWith("_")) {
                key = key.substring(1,key.length());
                newCustomProperties.add(key);
            }
            newSettings.put(key, value);
            
            settings = new HashMap<>(newSettings);
            customProperties = new HashSet<>(newCustomProperties);
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("Couldn't load config file \""+path+"\"");
            return false;
        }
    }
    
    public static boolean save() {
        return save(Constants.DEFAULT_SETTINGS_FILE);
    }
    
    public static boolean save(String path) {
        if (!path.equals(Constants.DEFAULT_SETTINGS_FILE)) {
            try {
                final FileWriter fw = new FileWriter(Constants.DEFAULT_SETTINGS_FILE);
                fw.write("Load:"+path);
                fw.close();
            } catch (IOException ex) {
                System.err.println("Couldn't write \"Load\" property to \""
                        +Constants.DEFAULT_SETTINGS_FILE+"\"");
                return false;
            }
        }
        
        FileWriter fw;
        try {
            fw = new FileWriter(path);
            
            for (String key : settings.keySet()) {
                if (customProperties.contains(key)) fw.write("_");
                fw.write(key+":"+settings.get(key)+"\n");
            }
            
            fw.close();
            
            return true;
        } catch (IOException ioe) {
            System.err.println("Couldn't write config file to \""+path+"\"");
            return false;
        }
    }
    
    public static void createDefault() {
        settings = new HashMap<>();
        settings.put("TabletName", "Wacom Intuos S Pen stylus");
        settings.put("RawSample", "4");
        settings.put("Suppress", "2");
        settings.put("Touch", "true");
        settings.put("Area", "0 0 15200 9500");
        settings.put("MapToOutput", "0 0 1920 1080");
        
        customProperties = new HashSet<>();
    }
    
    private static void saveDefault() {
        createDefault();
        save();
    }
    
    public static void apply() {
        settings.keySet().stream().filter(key -> !key.equals("TabletName"))
                .forEach(key -> SystemInterface.setWacomProperty(key, 
                        new String[]{settings.get(key)}));
    }    
}
