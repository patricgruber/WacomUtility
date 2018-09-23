package sys;

import general.Settings;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author patric
 */
public class SystemInterface {
    private final static Runtime rt = Runtime.getRuntime();
    
    public static boolean setWacomProperty(final String property, final String[] arguments){
        final String tablet;
        tablet = Settings.get("TabletName");
        if (tablet == null) return false;
        
        final List<String> commandList = new ArrayList<>(
                    Arrays.asList("xsetwacom", "set", tablet, property));
        commandList.addAll(Arrays.asList(arguments));

        final StringBuilder sb = new StringBuilder();
        commandList.forEach(cmd -> sb.append(cmd+" "));
        
        final String command = sb.toString().substring(0,sb.length()-1);
        
        System.out.println("Command to execute: \""+command+"\"");
        
        final Process p;
       
        try {
            p = new ProcessBuilder(commandList).start();
        } catch (IOException ioe) {
            System.err.println("Couldn't start process for command: \""+command+"\"");
            return false;
        }
        
        try {
            p.waitFor();
        } catch (InterruptedException ie) {
            System.err.println("Couldn't wait for process for command: \""+command+"\"");
            return false;
        }
                        
        return p.exitValue() == 0;
    }
    
    public static List<String> getTabletNames() {
        final List<String> tabletNames = new ArrayList<>();
        final Process p;
        
        try {
            p = new ProcessBuilder(Arrays.asList("xsetwacom", "list"))
                    .start();
        } catch (IOException ioe) {
            System.err.println("Couldn't start command: \"xsetwacom list\"");
            return tabletNames;
        }
        
        try {
            p.waitFor();
        } catch (InterruptedException ie) {
            System.err.println("Couldn't wait for command: \"xsetwacom list\"");
            return tabletNames;
        }

        final InputStreamReader isr = new InputStreamReader(p.getInputStream());
        
        String out = "";
        try {
            while (isr.ready()) out += (char)isr.read();
        } catch (IOException ioe) {
            out = "";
        }

        String[] lines = out.split("\n");
        for (String line : lines) {
            tabletNames.add(line.split("id")[0].trim());
        }            
        
        //return new ArrayList<>();
        return tabletNames;
    }
}
