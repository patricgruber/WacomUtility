package wrapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author patric
 */
public class TerminalWrapper {
     private static Process execute(final List<String> commandList) 
            throws WrapperException{
         
        final String command = 
        commandList.stream()
            .map(s -> s+" ")
            .reduce((s1,s2) -> s1+s2)
            .orElseThrow();
        
        final Process p;
        try {
            p = new ProcessBuilder(commandList).start();
        } catch (IOException ioe) {
            throw new WrapperException("Couldn't start process for command:"
                    + " \""+command+"\"");
        }
        
        try {
            p.waitFor();
        } catch (InterruptedException ie) {
            throw new WrapperException("Couldn't wait for process for command:"
                    + " \""+command+"\"");
        }
        return p;
    }
    
    public static List<String> getAllTabletNames() throws WrapperException{
        final List<String> commandList = Arrays.asList("xsetwacom", "list", "devices");
        final Process p = execute(commandList);

        final InputStreamReader isr = new InputStreamReader(p.getInputStream());
        
        final StringBuilder out = new StringBuilder();
        try {
            while (isr.ready()) out.append((char)isr.read());
        } catch (IOException ioe) {
            out.delete(0, out.length());
        }
        
        final String returned = out.toString().trim();
        if (returned.length() == 0) return new ArrayList<>();
        
        final List<String> tabletNames = new ArrayList<>();
        String[] lines = returned.split("\n");
        for (String line : lines) {
            tabletNames.add(line.split("id")[0].trim());
        }            
        
        return tabletNames;
    }

    public static void set(final String tabletName, final String property, final String value) 
            throws WrapperException {
        final String escapedTabletName = '"'+tabletName+'"';
        final String escapedValue = '"'+value+'"';
        
        final List<String> commandList = 
                Arrays.asList("xsetwacom", "set", escapedTabletName, property, escapedValue);
        
        final String command = 
                commandList.stream()
                    .map(s -> s+" ")
                    .reduce((s1,s2) -> s1+s2)
                    .orElseThrow();

        System.out.print("Command to execute: \""+command+"\" -> ");
        
        final Process p = execute(commandList);
       
        final int exitValue = p.exitValue();
        if (exitValue == 0) {
            System.out.println("Success");
        } else {
            System.out.println("Error("+exitValue+")");
        }
    }

    public static String get(String tabletName, String property) 
            throws WrapperException{
        
        final String escapedTabletName = '"'+tabletName+'"';
        
        final List<String> commandList = 
                Arrays.asList("xsetwacom", "get", escapedTabletName, property);
        
        final String command = commandList.stream()
                                        .map(s -> s+" ")
                                        .reduce((s1, s2) -> s1+s2)
                                        .orElseThrow();
        
        final Process p = execute(commandList);

        final InputStreamReader isr = new InputStreamReader(p.getInputStream());
        
        final StringBuilder out = new StringBuilder();
        try {
            while (isr.ready()) out.append((char)isr.read());
        } catch (IOException ioe) {
            out.delete(0, out.length());
        }
        
        final String returnedString = out.toString().trim();
        
        if (returnedString.length() == 0) {
            throw new WrapperException("Couldn't get a return value for command:"
                    + " \""+command+"\"");
        }       
        
        return returnedString;
    }
}
