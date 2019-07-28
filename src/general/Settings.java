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
import wrapper.Tablet;

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
    
    private static void saveDefault() {
        createDefaults();
        save();
    }
    
    private static void createDefaults() {
        settings = new HashMap<>();
        
        settings.put("AreaXOffset", "0");
        settings.put("AreaYOffset", "0");
        settings.put("AreaWidth", "15200");
        settings.put("AreaHeight", "9800");
        
        settings.put("Touch", "off");
        settings.put("Suppress", "0");
        settings.put("RawSample", "1");
        
        settings.put("MappedXOffset", "0");
        settings.put("MappedYOffset", "0");
        settings.put("MappedWidth", "1920");
        settings.put("MappedHeight", "1080");
        
        settings.put("TabletName", "Wacom Intuos S Pen stylus");
        
        customProperties = new HashSet<>();
    }
    
    public static void apply() {
        final String name = settings.get("TabletName");
        final Tablet tablet = new Tablet(name);
        
        final String rawSample = settings.get("RawSample");
        if (rawSample != null) {
            tablet.setRawSample(Integer.parseInt(rawSample));
        }
        
        final String suppress = settings.get("Suppress");
        if (suppress != null) {
            tablet.setSuppress(Integer.parseInt(suppress));
        }
        
        {
            final String areaXOffset = settings.get("AreaXOffset");
            final String areaYOffset = settings.get("AreaYOffset");
            final String areaWidth = settings.get("AreaWidth");
            final String areaHeight = settings.get("AreaHeight");
            if (areaXOffset != null && areaYOffset != null && 
                    areaWidth != null && areaHeight != null) {
                tablet.setArea(Integer.parseInt(areaXOffset), 
                        Integer.parseInt(areaYOffset), 
                        Integer.parseInt(areaWidth), 
                        Integer.parseInt(areaHeight));
            }
        }
        
        {
            final String mappedXOffset = settings.get("MappedXOffset");
            final String mappedYOffset = settings.get("MappedYOffset");
            final String mappedWidth = settings.get("MappedWidth");
            final String mappedHeight = settings.get("MappedHeight");
            if (mappedXOffset != null && mappedYOffset != null && 
                    mappedWidth != null && mappedHeight != null) {
                tablet.setMapToOutput(Integer.parseInt(mappedXOffset), 
                        Integer.parseInt(mappedYOffset), 
                        Integer.parseInt(mappedWidth), 
                        Integer.parseInt(mappedHeight));
            }
        }
        
        final String threshold = settings.get("Threshold");
        if (threshold != null) {
            tablet.setThreshold(Integer.parseInt(threshold));
        }
        
        final String touch = settings.get("Touch");
        if (touch != null) {
            tablet.setTouch(touch.equals("on"));
        }
        
        customProperties.stream()
                .filter((key) -> (settings.containsKey(key)))
                .forEach((key) -> {
                    tablet.setCustom(key,settings.get(key));
                });
        
        System.out.println();
    }    
}
