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
    /**
     * Executes the given command and returns the process. 
     * The method already calls ``waitFor`` on the process
     * 
     * @param commandList is the command list that is given to the process on creation
     * @return the created process
     * @throws WrapperException will be thrown if there is a problem during 
     *                          the process creation or waiting
     */
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
     
    /**
     * @return a List of all available tablet names
     * @throws WrapperException if there was a problem during execution 
     *                          of the command ``xsetwacom list devices``
     */
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

    /**
     * Sets a property of the tablet with the given name to the given value
     * 
     * @param tabletName is the name of the tablet
     * @param property is the name of the property
     * @param value is the value the property should be set to
     * @throws WrapperException will be thrown if there is a problem during command execution
     */
    public static void set(final String tabletName, final String property, final String value) 
            throws WrapperException {
        final List<String> commandList = 
                Arrays.asList("xsetwacom", "set", tabletName, property, value);
        
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

    /**
     * Returns the value of a property of the given tablet
     * 
     * @param tabletName is the name of the tablet
     * @param property is the name of the property
     * @return the value of the property of the given tablet
     * @throws WrapperException will be thrown if there is a problem during the command execution
     */
    public static String get(String tabletName, String property) 
            throws WrapperException{
        final List<String> commandList = 
                Arrays.asList("xsetwacom", "get", tabletName, property);
        
        final String command = commandList.stream()
                                        .map(s -> s+" ")
                                        .reduce((s1, s2) -> s1+s2)
                                        .orElseThrow();
        
        final Process p = execute(commandList);

        final InputStreamReader isr = new InputStreamReader(p.getInputStream());
        
        final StringBuilder out = new StringBuilder();
        try {
            while (isr.ready()) out.append((char)isr.read());
        } catch (IOException ioe) {}
        
        
        final String returnedString = out.toString().trim();
        
        if (returnedString.length() == 0) {
            throw new WrapperException("Couldn't get a return value for command:"
                    + " \""+command+"\"");
        }       
        
        return returnedString;
    }
}
